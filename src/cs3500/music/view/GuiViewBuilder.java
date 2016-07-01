package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * Builder class for a GuiView.
 */
public class GuiViewBuilder {

  public GuiViewBuilder() {

  }

  public static GuiView<INote> build(String arg) {
    if(arg.equals("visual")) {
      return new GuiViewFrame();
    }
    else if (arg.equals("composite")) {
      return new CompositionView(new GuiViewFrame(), new MidiViewImpl());
    }
    else {
      throw new IllegalArgumentException("Unsupported GUI view");
    }
  }
}
