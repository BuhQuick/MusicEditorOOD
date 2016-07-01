package cs3500.music.view;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import javax.sound.midi.*;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.NoteComparatorForInstrument;
import cs3500.music.model.Note;

/**
 * This class is used as a Midi implementation of the composition view. All of the notes in the
 * composition are added to a sequencer and played.
 */
// made sequencer protected
public class MidiViewImpl implements ICompositionView<INote> {
  protected final Sequencer sequencer;
  protected Sequence sequence;
  private int tempo;

  /**
   * Constructs a MidiView
   */
  public MidiViewImpl() {
    Sequencer seq;
    try {
      seq = MidiSystem.getSequencer();
      seq.open();
      this.sequence = new Sequence(Sequence.PPQ, 16);
    } catch (Exception e) {
      seq = null;
      e.printStackTrace();
    }
    this.sequencer = seq;
    this.tempo = 0;
  }

  /**
   * Conveience constructor for creating a mock object to test
   *
   * @param s mock sequencer
   */
  public MidiViewImpl(Sequencer s) {
    try {
      s.open();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.sequencer = s;
  }

  @Override
  public void displayComposition() {
    this.sequencer.start();
  }

  /**
   * Sets the sequence tempo
   *
   * @param tempo the tempo of this sequence
   */
  private void setSequencerTempo(int tempo) {
    this.sequencer.setTempoInMPQ(tempo);
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    List<INote> composition = model.getComposition();
    Collections.sort(composition, new NoteComparatorForInstrument());
    this.tempo = model.getTempo();
    if (composition.size() == 0) {
      throw new IllegalArgumentException("There is no composition to play!");
    }
    try {
      this.sequence = new Sequence(Sequence.PPQ, 16);
      Track track = this.sequence.createTrack();
      int channel = 0;
      int curInstr = composition.get(0).getInstrumentMIDI();
      byte[] tempoBytes = new byte[]{
              (byte) (model.getTempo() >>> 16),
              (byte) (model.getTempo() >>> 8),
              (byte) model.getTempo()};
      MetaMessage setTempo = new MetaMessage();
      setTempo.setMessage(0x51, tempoBytes, 3);
      track.add(new MidiEvent(setTempo, 0));
      MidiMessage instrChange = new ShortMessage(
              ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
      track.add(new MidiEvent(instrChange,
              (long) (sequence.getResolution() * composition.get(0).getStartingBeat())));
      for (INote n : composition) {
        long startBeat = (long) (sequence.getResolution() * n.getStartingBeat());
        long stopBeat = (long) (sequence.getResolution() *
                (n.getStartingBeat() + n.getDuration()));
        int pitchToMidiValue = (n.getPitch().ordinal() + ((n.getOctave() + 1) * 12));
        if (curInstr != n.getInstrumentMIDI()) {
          channel += 1;
          curInstr = n.getInstrumentMIDI();
          instrChange = new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
          track.add(new MidiEvent(instrChange, startBeat));
        }
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.CONTROL_CHANGE, channel, 7, n.getVolume()), startBeat));
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.NOTE_ON, channel, pitchToMidiValue, 64), startBeat));
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.NOTE_OFF, channel, pitchToMidiValue, 64), stopBeat));
      }
      this.sequencer.setSequence(this.sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

}
