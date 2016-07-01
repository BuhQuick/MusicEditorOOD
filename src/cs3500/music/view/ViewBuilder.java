package cs3500.music.view;

/**
 * A View builder for the various types of composition views.
 */
public class ViewBuilder {

  public ViewBuilder() {

  }

  /**
   * Constructs an ICompositionView based on the given param
   *
   * @param view the view to construct
   * @return an ICompositionView.
   */
  public static ICompositionView build(String view) {
    if (view.equals("console")) {
      return new ConsoleView();
    }
    else if (view.equals("midi")) {
      return new MidiViewImpl();
    }
    else {
      throw new IllegalArgumentException("Unsupported view implementation");
    }
  }
}
