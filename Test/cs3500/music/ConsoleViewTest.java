package cs3500.music;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.ICompositionView;

import static org.junit.Assert.assertEquals;

/**
 * Test for console view.
 */
public class ConsoleViewTest {
  ICompositionView view;
  MusicModel<INote> mary;
  MusicModel<INote> std;
  ByteArrayOutputStream bytes;
  PrintStream output;

  void initData() {
    bytes = new ByteArrayOutputStream();
    output = new PrintStream(bytes);
    view = new ConsoleView(output);
    try {
      mary = MusicReader.parseFile(new BufferedReader(
                      new InputStreamReader(new FileInputStream("mary-little-lamb.txt"))),
              new MusicModelImpl.Builder());
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    std = new MusicModelImpl();
    std.addNote(new Note(Pitch.F_SHARP, 1, 4, 0));
    std.addNote(new Note(Pitch.D_SHARP, 2, 4, 0));
    std.addNote(new Note(Pitch.F_SHARP, 1, 3, 2));
    std.addNote(new Note(Pitch.B, 1, 4, 10));
    std.addNote(new Note(Pitch.C, 2, 4, 12));
    std.addNote(new Note(Pitch.C_SHARP, 1, 8, 26));
    std.addNote(new Note(Pitch.F_SHARP, 1, 4, 10));
  }

  @Test
  public void testConsoleViewFromFile() {
    initData();
    assertEquals("", bytes.toString());
    view.buildComposition(mary);
    view.displayComposition();
    assertEquals("    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4" +
            "   G4 \n" +
            " 0                 X                                            X                 " +
            "\n" +
            " 1                 |                                            |                 " +
            "\n" +
            " 2                 |                                  X         |                 " +
            "\n" +
            " 3                 |                                  |                           " +
            "\n" +
            " 4                 |                        X         |                           " +
            "\n" +
            " 5                 |                        |                                     " +
            "\n" +
            " 6                 |                        |         X                           " +
            "\n" +
            " 7                 |                                  |                           " +
            "\n" +
            " 8                 X                                  |         X                 " +
            "\n" +
            " 9                 |                                            |                 " +
            "\n" +
            "10                 |                                            X                 " +
            "\n" +
            "11                 |                                            |                 " +
            "\n" +
            "12                 |                                            X                 " +
            "\n" +
            "13                 |                                            |                 " +
            "\n" +
            "14                 |                                            |                 " +
            "\n" +
            "15                 |                                            |                 " +
            "\n" +
            "16                 X                                  X                           " +
            "\n" +
            "17                 |                                  |                           " +
            "\n" +
            "18                 |                                  X                           " +
            "\n" +
            "19                 |                                  |                           " +
            "\n" +
            "20                 |                                  X                           " +
            "\n" +
            "21                 |                                  |                           " +
            "\n" +
            "22                 |                                  |                           " +
            "\n" +
            "23                 |                                  |                           " +
            "\n" +
            "24                 X                                  |         X                 " +
            "\n" +
            "25                 |                                            |                 " +
            "\n" +
            "26                 |                                            |              X  " +
            "\n" +
            "27                                                                             |  " +
            "\n" +
            "28                                                                             X  " +
            "\n" +
            "29                                                                             |  " +
            "\n" +
            "30                                                                             |  " +
            "\n" +
            "31                                                                             |  " +
            "\n" +
            "32                 X                                            X              |  " +
            "\n" +
            "33                 |                                            |                 " +
            "\n" +
            "34                 |                                  X         |                 " +
            "\n" +
            "35                 |                                  |                           " +
            "\n" +
            "36                 |                        X         |                           " +
            "\n" +
            "37                 |                        |                                     " +
            "\n" +
            "38                 |                        |         X                           " +
            "\n" +
            "39                 |                                  |                           " +
            "\n" +
            "40                 X                                  |         X                 " +
            "\n" +
            "41                 |                                            |                 " +
            "\n" +
            "42                 |                                            X                 " +
            "\n" +
            "43                 |                                            |                 " +
            "\n" +
            "44                 |                                            X                 " +
            "\n" +
            "45                 |                                            |                 " +
            "\n" +
            "46                 |                                            X                 " +
            "\n" +
            "47                 |                                            |                 " +
            "\n" +
            "48                 X                                  X         |                 " +
            "\n" +
            "49                 |                                  |                           " +
            "\n" +
            "50                 |                                  X                           " +
            "\n" +
            "51                 |                                  |                           " +
            "\n" +
            "52                 |                                  |         X                 " +
            "\n" +
            "53                 |                                            |                 " +
            "\n" +
            "54                 |                                  X         |                 " +
            "\n" +
            "55                 |                                  |                           " +
            "\n" +
            "56  X              |                        X         |                           " +
            "\n" +
            "57  |                                       |                                     " +
            "\n" +
            "58  |                                       |                                     " +
            "\n" +
            "59  |                                       |                                     " +
            "\n" +
            "60  |                                       |                                     " +
            "\n" +
            "61  |                                       |                                     " +
            "\n" +
            "62  |                                       |                                     " +
            "\n" +
            "63  |                                       |                                     " +
            "\n", bytes.toString());
  }

  @Test
  public void testConsoleViewStd() {
    initData();
    assertEquals("", bytes.toString());
    view.buildComposition(std);
    view.displayComposition();
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
            bytes.toString());
    initData();
    std = new MusicModelImpl();
    std.addNote(new Note(Pitch.C, 3, 4, 0));
    std.addNote(new Note(Pitch.C, 3, 2, 2));
    std.addNote(new Note(Pitch.C_SHARP, 2, 6, 4));
    std.addNote(new Note(Pitch.A, 2, 4, 4));
    view.buildComposition(std);
    view.displayComposition();
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
            bytes.toString());
  }


}