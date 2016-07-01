package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.view.GuiView;

/**
 * A runnable class used to pause the given view of a composition
 */
public class PauseMusic implements Runnable {
  GuiView<INote> view;

  public PauseMusic(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    try {
      this.view.pausePlayback();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
