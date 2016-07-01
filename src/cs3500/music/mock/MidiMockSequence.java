package cs3500.music.mock;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

/**
 * This class a mock sequence for Midi playback, functions as a normal sequence, but records when a
 * track is created.
 */
public class MidiMockSequence extends Sequence {

  public MidiMockSequence(float divisionType, int resolution) throws InvalidMidiDataException {
    super(divisionType, resolution);
  }

  @Override
  public Track createTrack() {
    MidiMockTracer.updateTrace("Track created for sequence.");
    return super.createTrack();
  }
}
