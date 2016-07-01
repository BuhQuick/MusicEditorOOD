package cs3500.music;

import org.junit.Test;

import javax.sound.midi.Sequence;

import cs3500.music.mock.MidiMockSequence;
import cs3500.music.mock.MidiMockTracer;

import static org.junit.Assert.assertEquals;

/**
 * Test MidiMockSequence.
 */
public class MidiMockSequenceTest {
  MidiMockSequence c;

  void initData() {
    MidiMockTracer.resetTrace();
    try {
      c = new MidiMockSequence(Sequence.PPQ, 16);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateTrackUpdatesTracer() {
    initData();
    assertEquals("", MidiMockTracer.getTrace());
    c.createTrack();
    assertEquals("Track created for sequence.", MidiMockTracer.getTrace());
  }
}