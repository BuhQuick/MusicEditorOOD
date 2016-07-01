package cs3500.music;

import org.junit.Test;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.ICompositionView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Test view builder.
 */
public class ViewBuilderTest {
  private ICompositionView view = null;

  @Test
  public void testBuilder() {
    assertEquals(null, view);
    view = ViewBuilder.build("midi");
    assertEquals(true, view instanceof MidiViewImpl);
    view = ViewBuilder.build("console");
    assertEquals(true, view instanceof ConsoleView);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIncorrectInput() {
    view = ViewBuilder.build("butts");
  }
}