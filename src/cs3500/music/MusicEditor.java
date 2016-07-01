package cs3500.music;

import cs3500.music.controller.CompositeController;
import cs3500.music.controller.CompositionControllerImpl;
import cs3500.music.controller.ICompositionController;
import cs3500.music.model.*;
import cs3500.music.util.MusicReader;
import cs3500.music.view.*;

import java.io.*;

import javax.sound.midi.InvalidMidiDataException;

/**
 * A class used for playing the midiview / displaying gui view
 */
public class MusicEditor {
  /**
   * The main() event ...
   *
   * @param args should take two arguments, first the desired file, second the desired view type.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    FileInputStream file = new FileInputStream(args[0]);
    MusicModel<INote> model = MusicReader.parseFile(new BufferedReader(
                    new InputStreamReader(file)),
            new MusicModelImpl.Builder());
    ICompositionController<INote> controller;
    if (args[1].equals("visual") || args[1].equals("composite")) {
      GuiView<INote> viewgui = GuiViewBuilder.build(args[1]);
      controller = new CompositeController(model, viewgui);
    }
    else {
      ICompositionView<INote> view = ViewBuilder.build(args[1]);
      controller = new CompositionControllerImpl(model, view);
    }
    controller.constructView();
    controller.displayView();

  }
}
