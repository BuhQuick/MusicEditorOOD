package cs3500.music;

import org.junit.Test;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class used to ensure correctness of the MusicModelImpl class.
 */
public class MusicModelImplTest {
  MusicModel<INote> model;
  MusicModel<INote> modelTwo;
  Note bOne;
  Note bOneC;
  Note bTwo;
  Note bOneDifDur;
  Note bOneDifStart;
  Note cSharpOne;
  Note aSharpThree;

  private void initData() {
    model = new MusicModelImpl.Builder().build();
    modelTwo = new MusicModelImpl.Builder().build();
    bOne = new Note(Pitch.B, 1, 5, 0);
    bOneC = new Note(Pitch.B, 1, 5, 0);
    bTwo = new Note(Pitch.B, 2, 5, 0);
    bOneDifDur = new Note(Pitch.B, 1, 3, 0);
    bOneDifStart = new Note(Pitch.B, 1, 5, 2);
    cSharpOne = new Note(Pitch.C_SHARP, 1, 5, 0);
    aSharpThree = new Note(Pitch.A_SHARP, 3, 5, 0);
  }

  @Test
  public void testAddNote() {
    initData();
    assertEquals(0, model.getComposition().size());
    model.addNote(bOne);
    assertEquals(1, model.getComposition().size());
    assertTrue(model.getComposition().contains(bOne));
    model.addNote(bOneC);
    assertEquals(2, model.getComposition().size());
    assertTrue(model.getComposition().contains(bOneC));
  }

  @Test
  public void testRemoveNoteSuccess() {
    initData();
    assertEquals(0, model.getComposition().size());
    model.addNote(bOne);
    assertEquals(1, model.getComposition().size());
    model.removeNote(bOne);
    assertEquals(0, model.getComposition().size());
    assertEquals(false, model.getComposition().contains(bOne));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNoteFailure() {
    initData();
    model.addNote(bOne);
    model.removeNote(bTwo);
  }

  @Test
  public void testInterweaveMusic() {
    initData();
    model.addNote(bOne);
    model.addNote(bOneDifStart);
    model.addNote(cSharpOne);
    modelTwo.addNote(bOneC);
    modelTwo.addNote(aSharpThree);
    modelTwo.addNote(bOneDifDur);
    assertEquals(3, model.getComposition().size());
    assertEquals(3, modelTwo.getComposition().size());
    model.interweaveMusic(modelTwo);
    assertEquals(6, model.getComposition().size());
    assertEquals(3, modelTwo.getComposition().size());
    for (INote n : modelTwo.getComposition()) {
      assertTrue(model.getComposition().contains(n));
    }
  }

  @Test
  public void testExtendMusic() {
    initData();
    model.addNote(bOne);
    model.addNote(bOneDifStart);
    model.addNote(cSharpOne);
    modelTwo.addNote(bOneC);
    modelTwo.addNote(aSharpThree);
    modelTwo.addNote(bOneDifDur);
    assertEquals(3, model.getComposition().size());
    assertEquals(3, modelTwo.getComposition().size());
    model.extendMusic(modelTwo);
    for (INote n : modelTwo.getComposition()) {
      assertTrue(model.getComposition().contains(
              new Note(n.getPitch(), n.getOctave(), n.getDuration(), n.getStartingBeat() + 7)));
    }
  }

  @Test
  public void testGetCompositionAtBeat() {
    initData();
    model.addNote(bOne);
    model.addNote(bOneDifStart);
    model.addNote(cSharpOne);
    assertEquals(0, model.getCompositionAtBeat(420).size());
    assertEquals(1, model.getCompositionAtBeat(6).size());
    assertEquals(3, model.getCompositionAtBeat(2).size());
  }

  @Test
  public void testGetComposition() {
    initData();
    assertEquals(0, model.getComposition().size());
    model.addNote(bOne);
    assertEquals(1, model.getComposition().size());
    model.addNote(cSharpOne);
    assertTrue(model.getComposition().contains(bOne));
    assertTrue(model.getComposition().contains(cSharpOne));
  }

  @Test
  public void testViewComposition() {
    initData();
    model.addNote(new Note(Pitch.A_SHARP, 1, 5, 0));
    model.addNote(new Note(Pitch.D, 2, 7, 0));
    model.addNote(new Note(Pitch.B, 1, 5, 1));
    assertEquals("  A#1   B1   C2  C#2   D2 \n" +
                 "0  X                   X  \n" +
                 "1  |    X              |  \n" +
                 "2  |    |              |  \n" +
                 "3  |    |              |  \n" +
                 "4  |    |              |  \n" +
                 "5  |    |              |  \n" +
                 "6       |              |  \n", model.viewComposition());
    modelTwo.addNote(new Note(Pitch.C, 3, 4, 0));
    modelTwo.addNote(new Note(Pitch.C, 3, 2, 2));
    modelTwo.addNote(new Note(Pitch.C_SHARP, 2, 6, 4));
    modelTwo.addNote(new Note(Pitch.A, 2, 4, 4));
    assertEquals("  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2   C3 \n" +
                 "0                                                         X  \n" +
                 "1                                                         |  \n" +
                 "2                                                         X  \n" +
                 "3                                                         |  \n" +
                 "4  X                                       X              |  \n" +
                 "5  |                                       |                 \n" +
                 "6  |                                       |                 \n" +
                 "7  |                                       |                 \n" +
                 "8  |                                       |                 \n" +
                 "9  |                                                         \n",
            modelTwo.viewComposition());
    model.extendMusic(modelTwo);
    assertEquals("   A#1   B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2   C3 \n"
            +
                 " 0  X                   X                                                    \n"
            +
                 " 1  |    X              |                                                    \n"
            +
                 " 2  |    |              |                                                    \n"
            +
                 " 3  |    |              |                                                    \n"
            +
                 " 4  |    |              |                                                    \n"
            +
                 " 5  |    |              |                                                    \n"
            +
                 " 6       |              |                                                    \n"
            +
                 " 7                      |                                                 X  \n"
            +
                 " 8                                                                        |  \n"
            +
                 " 9                                                                        X  \n"
            +
                 "10                                                                        |  \n"
            +
                 "11                 X                                       X              |  \n"
            +
                 "12                 |                                       |                 \n"
            +
                 "13                 |                                       |                 \n"
            +
                 "14                 |                                       |                 \n"
            +
                 "15                 |                                       |                 \n"
            +
                 "16                 |                                                         \n",
            model.viewComposition());
    initData();
    model.addNote(new Note(Pitch.F_SHARP, 1, 4, 0));
    model.addNote(new Note(Pitch.D_SHARP, 2, 4, 0));
    model.addNote(new Note(Pitch.F_SHARP, 1, 3, 2));
    model.addNote(new Note(Pitch.B, 1, 4, 10));
    model.addNote(new Note(Pitch.C, 2, 4, 12));
    model.addNote(new Note(Pitch.C_SHARP, 1, 8, 26));
    model.addNote(new Note(Pitch.F_SHARP, 1, 4, 10));
    assertEquals("   C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1   B1   C2  C#2   D2  D#2 \n"
            +
                 " 0                           X                                            X  \n"
            +
                 " 1                           |                                            |  \n"
            +
                 " 2                           X                                            |  \n"
            +
                 " 3                           |                                            |  \n"
            +
                 " 4                           |                                            |  \n"
            +
                 " 5                           |                                               \n"
            +
                 " 6                                                                           \n"
            +
                 " 7                                                                           \n"
            +
                 " 8                                                                           \n"
            +
                 " 9                                                                           \n"
            +
                 "10                           X                        X                      \n"
            +
                 "11                           |                        |                      \n"
            +
                 "12                           |                        |    X                 \n"
            +
                 "13                           |                        |    |                 \n"
            +
                 "14                           |                        |    |                 \n"
            +
                 "15                                                         |                 \n"
            +
                 "16                                                         |                 \n"
            +
                 "17                                                                           \n"
            +
                 "18                                                                           \n"
            +
                 "19                                                                           \n"
            +
                 "20                                                                           \n"
            +
                 "21                                                                           \n"
            +
                 "22                                                                           \n"
            +
                 "23                                                                           \n"
            +
                 "24                                                                           \n"
            +
                 "25                                                                           \n"
            +
                 "26  X                                                                        \n"
            +
                 "27  |                                                                        \n"
            +
                 "28  |                                                                        \n"
            +
                 "29  |                                                                        \n"
            +
                 "30  |                                                                        \n"
            +
                 "31  |                                                                        \n"
            +
                 "32  |                                                                        \n"
            +
                 "33  |                                                                        \n",
            model.viewComposition());
  }
}