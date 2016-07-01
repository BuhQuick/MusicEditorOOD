package cs3500.music;

import org.junit.Test;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.mock.MidiMockDevice;
import cs3500.music.mock.MidiMockTracer;

import static org.junit.Assert.assertEquals;

/**
 * Test MidiMockDevice.
 */
public class MidiMockDeviceTest {
  MidiMockDevice seq;

  void initData() {
    seq = new MidiMockDevice();
    MidiMockTracer.resetTrace();
  }

  @Test
  public void testOpenUpdatesTracer() {
    initData();
    assertEquals("", MidiMockTracer.getTrace());
    try {
      seq.open();
    }
    catch (Exception e) {

    }
    assertEquals("Sequencer opened.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testCloseUpdatesTrace() {
    initData();
    assertEquals("", MidiMockTracer.getTrace());
    try {
      seq.close();
    }
    catch (Exception e) {

    }
    assertEquals("Sequencer closed.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testSetSequenceUpdatesTraceNoNotes() {
    initData();
    try {
      Sequence c = new Sequence(Sequence.PPQ, 16);
      seq.setSequence(c);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Sequence set.\n" +
            "Sequence has MS length: 0, resolution: 16\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testSetSequenceWithNOTE_ON() {
    initData();
    try {
      Sequence c = new Sequence(Sequence.PPQ, 16);
      Track t = c.createTrack();
      t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 3, 0), 40));
      seq.setSequence(c);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Sequence set.\n" +
                    "Sequence has MS length: 1250000, resolution: 16\n" +
                    "MidiMessage \"Note on\" at beat 2 was logged successfully.\n" +
                    "MidiMessage \"End sequence\" at beat 2 was logged successfully.\n",
            MidiMockTracer.getTrace());
  }

  @Test
  public void testSetSequenceWithNOTE_OFF() {
    initData();
    try {
      Sequence c = new Sequence(Sequence.PPQ, 16);
      Track t = c.createTrack();
      t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, 3, 0), 40));
      seq.setSequence(c);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Sequence set.\n" +
                    "Sequence has MS length: 1250000, resolution: 16\n" +
                    "MidiMessage \"Note off\" at beat 2 was logged successfully.\n" +
                    "MidiMessage \"End sequence\" at beat 2 was logged successfully.\n",
            MidiMockTracer.getTrace());
  }

  @Test
  public void testSetSequenceWithCONTROL_CHANGE() {
    initData();
    try {
      Sequence c = new Sequence(Sequence.PPQ, 16);
      Track t = c.createTrack();
      t.add(new MidiEvent(new ShortMessage(
              ShortMessage.CONTROL_CHANGE, 0, 7, 40), 40));
      seq.setSequence(c);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Sequence set.\n" +
                    "Sequence has MS length: 1250000, resolution: 16\n" +
                    "MidiMessage \"Control change\" at beat 2 was logged successfully.\n" +
                    "MidiMessage \"End sequence\" at beat 2 was logged successfully.\n",
            MidiMockTracer.getTrace());
  }

  @Test
  public void testSetSequenceWithPROGRAM_CHANGE() {
    initData();
    try {
      Sequence c = new Sequence(Sequence.PPQ, 16);
      Track t = c.createTrack();
      t.add(new MidiEvent(new ShortMessage(
              ShortMessage.PROGRAM_CHANGE, 0, 7, 40), 40));
      seq.setSequence(c);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Sequence set.\n" +
                    "Sequence has MS length: 1250000, resolution: 16\n" +
                    "MidiMessage \"Program change\" at beat 2 was logged successfully.\n" +
                    "MidiMessage \"End sequence\" at beat 2 was logged successfully.\n",
            MidiMockTracer.getTrace());
  }

  @Test
  public void testStartUpdateTracer() {
    initData();
    assertEquals("", MidiMockTracer.getTrace());
    try {
      seq.start();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals("Playback started by sequencer.\n", MidiMockTracer.getTrace());
  }

  @Test
  public void testSetTempoInMPQUpdateTracer() {
    initData();
    assertEquals("", MidiMockTracer.getTrace());
    seq.setTempoInMPQ(12312412);
    assertEquals("TempoInMPQ set.\n", MidiMockTracer.getTrace());
  }
}