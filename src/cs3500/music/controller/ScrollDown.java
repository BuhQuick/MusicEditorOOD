package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.view.GuiView;

/**
 * A runnable class that scrolls the view down 5 pixels every time it is run.
 */
public class ScrollDown implements Runnable {
  private GuiView<INote> view;

  public ScrollDown(GuiView<INote> view) {
    this.view = view;
  }

  @Override
  public void run() {
    this.view.scrollY(5);
  }
}
