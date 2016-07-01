package cs3500.music;

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Test class used to ensure correctness of the Note class.
 */
public class NoteTest {
  Note bOne;
  Note bOneC;
  Note bTwo;
  Note bOneDifDur;
  Note bOneDifStart;
  Note aSharpThree;

  private void initData() {
    bOne = new Note(Pitch.B, 1, 5, 0);
    bOneC = new Note(Pitch.B, 1, 5, 0);
    bTwo = new Note(Pitch.B, 2, 5, 0);
    bOneDifDur = new Note(Pitch.B, 1, 3, 0);
    bOneDifStart = new Note(Pitch.B, 1, 5, 2);
    aSharpThree = new Note(Pitch.A_SHARP, 3, 5, 0);
  }

  @Test
  public void testEquals() {
    initData();
    assertEquals(false, bOne.equals(bTwo));
    assertEquals(true, bOne.equals(bOneC));
    assertEquals(false, bOneDifDur.equals(bOne));
    assertEquals(false, bOneDifStart.equals(bOne));
    assertEquals(false, aSharpThree.equals(bTwo));
  }

  @Test
  public void testToString() {
    initData();
    assertEquals("B1", bOne.toString());
    assertEquals("B1", bOneC.toString());
    assertEquals("B2", bTwo.toString());
    assertEquals("B1", bOneDifDur.toString());
    assertEquals(true, bOne.toString().equals(bOneDifDur.toString()));
    assertEquals("A#3", aSharpThree.toString());
  }

  @Test
  public void testUpdateDuration() {
    initData();
    assertEquals(5, bOne.getDuration());
    bOne.updateDuration(420);
    assertEquals(420, bOne.getDuration());
    initData();
    assertEquals(3, bOneDifDur.getDuration());
    bOneDifDur.updateDuration(5);
    assertEquals(5, bOneDifDur.getDuration());
    assertEquals(true, bOneDifDur.getDuration() == bOne.getDuration());
  }

  @Test
  public void testUpdateStartingBeat() {
    initData();
    assertEquals(0, aSharpThree.getStartingBeat());
    aSharpThree.updateStartingBeat(5);
    assertEquals(5, aSharpThree.getStartingBeat());
    bOne.updateStartingBeat(5);
    assertEquals(true, bOne.getStartingBeat() == aSharpThree.getStartingBeat());
  }
}