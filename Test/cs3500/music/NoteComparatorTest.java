package cs3500.music;

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.NoteComparator;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Test class used to ensure correctness of NoteComparator class.
 */
public class NoteComparatorTest {
  NoteComparator nc;
  Note bOne;
  Note bOneC;
  Note bTwo;
  Note bOneDifDur;
  Note bOneDifStart;
  Note cSharpOne;
  Note aSharpThree;

  private void initData() {
    nc = new NoteComparator();
    bOne = new Note(Pitch.B, 1, 5, 0);
    bOneC = new Note(Pitch.B, 1, 5, 0);
    bTwo = new Note(Pitch.B, 2, 5, 0);
    bOneDifDur = new Note(Pitch.B, 1, 3, 0);
    bOneDifStart = new Note(Pitch.B, 1, 5, 2);
    cSharpOne = new Note(Pitch.C_SHARP, 1, 5, 0);
    aSharpThree = new Note(Pitch.A_SHARP, 3, 5, 0);
  }

  @Test
  public void TestCompare() {
    initData();
    assertEquals(0, nc.compare(bOne, bOneC));
    assertEquals(0, nc.compare(bOne, bOneDifDur));
    assertEquals(0, nc.compare(bOne, bOneDifStart));
    assertEquals(-100, nc.compare(bOne, bTwo));
    assertEquals(100, nc.compare(bTwo, bOne));
    assertEquals(199, nc.compare(aSharpThree, bOne));
    assertEquals(-199, nc.compare(bOne, aSharpThree));
    assertEquals(99, nc.compare(aSharpThree, bTwo));
    assertEquals(-99, nc.compare(bTwo, aSharpThree));
    assertEquals(-10, nc.compare(cSharpOne, bOne));
    assertEquals(10, nc.compare(bOne, cSharpOne));

  }
}