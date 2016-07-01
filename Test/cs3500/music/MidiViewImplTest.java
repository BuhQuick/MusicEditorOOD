package cs3500.music;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.music.mock.MidiMockDevice;
import cs3500.music.mock.MidiMockTracer;
import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ICompositionView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test MidiViewImpl.
 */
public class MidiViewImplTest {
  private ICompositionView mview;
  private MusicModel<INote> model;
  private List<INote> shortComp;
  private FileInputStream mary;

  void initData() {
    mview = new MidiViewImpl(new MidiMockDevice());
    try {
      mary = new FileInputStream("mary-little-lamb.txt");
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    model = MusicReader.parseFile(new BufferedReader(
                    new InputStreamReader(mary)), new MusicModelImpl.Builder());
    shortComp = new ArrayList<INote>();
    shortComp.add(new Note(Pitch.A, 1, 5, 0));
    shortComp.add(new Note(Pitch.C, 1, 3, 5));
    shortComp.add(new Note(Pitch.B, 2, 5, 0, 64, 2));
    shortComp.add(new Note(Pitch.C, 2, 10, 5, 64, 2));
  }

  @Test
  public void testConstructorOpensSequencer() {
    MidiMockTracer.resetTrace();
    assertEquals("", MidiMockTracer.getTrace());
    initData();
    assertEquals("Sequencer opened.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testBuildCompositionWithMary() {
    MidiMockTracer.resetTrace();
    assertEquals("", MidiMockTracer.getTrace());
    initData();
    mview.buildComposition(model);
    mview.displayComposition();
    assertEquals("Sequencer opened.\n" +
            "TempoInMPQ set.\n" +
            "Sequence set.\n" +
            "Sequence has MS length: 32000000, resolution: 16\n" +
            "MidiMessage \"Control change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 2 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 2 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 2 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 4 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 4 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 4 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 6 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 6 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 6 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 7 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 10 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 10 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 10 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 12 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 12 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 12 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 15 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 15 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 16 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 16 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 16 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 16 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 18 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 18 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 18 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 20 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 20 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 20 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 24 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 26 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 26 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 26 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 26 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 28 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 28 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 28 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 32 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 32 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 32 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 32 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 32 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 34 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 34 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 34 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 36 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 36 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 36 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 38 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 38 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 38 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 40 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 42 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 42 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 42 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 44 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 44 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 44 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 46 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 46 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 46 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 48 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 50 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 50 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 50 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 52 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 52 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 52 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 54 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 54 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 54 was logged successfully.\n" +
            "MidiMessage \"Program change\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 56 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 64 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 64 was logged successfully.\n" +
            "MidiMessage \"End sequence\" at beat 64 was logged successfully.\n" +
            "Playback started by sequencer.\n" +
            "Sequencer closed.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testMidiViewNoFileOneNote() {
    MidiMockTracer.resetTrace();
    assertEquals("", MidiMockTracer.getTrace());
    shortComp = new ArrayList<INote>();
    shortComp.add(new Note(Pitch.C, 4, 2, 0, 60, 0));
    mview = new MidiViewImpl(new MidiMockDevice());
    mview.buildComposition(new MusicModelImpl(shortComp));
    mview.displayComposition();
    assertEquals("Sequencer opened.\n" +
            "TempoInMPQ set.\n" +
            "Sequence set.\n" +
            "Sequence has MS length: 1000000, resolution: 16\n" +
            "MidiMessage \"Program change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 2 was logged successfully.\n" +
            "MidiMessage \"End sequence\" at beat 2 was logged successfully.\n" +
            "Playback started by sequencer.\n" +
            "Sequencer closed.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testMidiViewNoFileMultNotes() {
    MidiMockTracer.resetTrace();
    assertEquals("", MidiMockTracer.getTrace());
    initData();
    model = new MusicModelImpl(shortComp);
    mview.buildComposition(model);
    mview.displayComposition();
    assertEquals("Sequencer opened.\n" +
            "TempoInMPQ set.\n" +
            "Sequence set.\n" +
            "Sequence has MS length: 7500000, resolution: 16\n" +
            "MidiMessage \"Control change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 0 was logged successfully.\n" +
            "MidiMessage \"Program change\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Program change\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Control change\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Note on\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 5 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 8 was logged successfully.\n" +
            "MidiMessage \"Note off\" at beat 15 was logged successfully.\n" +
            "MidiMessage \"End sequence\" at beat 15 was logged successfully.\n" +
            "Playback started by sequencer.\n" +
            "Sequencer closed.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testSequencerStartAndCloseInIsolation() {
    MidiMockTracer.resetTrace();
    assertEquals("", MidiMockTracer.getTrace());
    initData();
    mview.displayComposition();
    assertEquals("Sequencer opened.\n" +
            "Playback started by sequencer.\n" +
            "Sequencer closed.\n", MidiMockTracer.getTrace());
  }

}