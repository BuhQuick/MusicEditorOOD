package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * The class for representing a model in the console.
 */
public class ConsoleView implements ICompositionView<INote> {
  private String view;
  private Appendable output;

  /**
   * Constructs a ConsoleView
   */
  public ConsoleView() {
    this.view = "";
    this.output = System.out;
  }

  public ConsoleView(Appendable output) {
    this.view = "";
    this.output = output;
  }

  @Override
  public void displayComposition() {
    try {
      this.output.append(this.view);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.view = model.viewComposition();

  }

}
