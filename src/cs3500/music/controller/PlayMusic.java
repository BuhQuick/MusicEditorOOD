package cs3500.music.controller;


import cs3500.music.model.INote;
import cs3500.music.view.GuiView;

/**
 * A runnable class that resumes playback on a given view of the composition
 */
public class PlayMusic implements Runnable {
  private GuiView<INote> view;

  public PlayMusic(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    try {
      this.view.resumePlayback();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
