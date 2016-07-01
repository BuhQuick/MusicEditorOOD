package cs3500.music.controller;

import java.util.ArrayList;

import cs3500.music.controller.ICompositionController;
import cs3500.music.model.INote;
import cs3500.music.view.GuiView;
import cs3500.music.view.NoteBuilderFrame;

/**
 * A runnable class used to add a note to the panel.
 */
public class AddNote implements Runnable {
  private ICompositionController<INote> controller;
  private GuiView<INote> view;

  /**
   * Constructs an AddNote Runnable
   *
   * @param controller the controller to give the note information
   * @param view       the view to retrieve the new note from
   */
  public AddNote(ICompositionController<INote> controller, GuiView<INote> view) {
    this.controller = controller;
    this.view = view;
  }

  @Override
  public void run() {
    if (!this.view.isPlaying()) {
      NoteBuilderFrame nb = new NoteBuilderFrame();
      nb.buildFrame(controller);
    }
  }
}
