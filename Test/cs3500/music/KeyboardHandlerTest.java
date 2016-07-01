package cs3500.music;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import cs3500.music.controller.KeyboardHandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To test the KeyboardHandler
 */
public class KeyboardHandlerTest {
  private boolean hasRun = false;
  private KeyboardHandler kh;
  private Component sc;
  private KeyEvent ke;

  private void initData() {
    this.kh = new KeyboardHandler();
    this.sc = new Button("mock");
    this.ke = new KeyEvent(sc, 1, 20, 10, KeyEvent.VK_0, '2', 2);
  }

  @Test
  public void testRunnablesDontRunWhenNotCorrectInput() {
    initData();
    kh.addPressedEvent(KeyEvent.VK_0, new MockRunnable());
    kh.addTypedEvent(KeyEvent.VK_0, new MockRunnable());
    kh.addReleasedEvent(KeyEvent.VK_0, new MockRunnable());
    assertFalse(hasRun);
    kh.keyPressed(new KeyEvent(sc, 1, 20, 10, KeyEvent.VK_2, '2', 2));
    assertFalse(hasRun);
    kh.keyTyped(new KeyEvent(sc, 1, 20, 10, KeyEvent.VK_2, '2', 2));
    assertFalse(hasRun);
    kh.keyReleased(new KeyEvent(sc, 1, 20, 10, KeyEvent.VK_2, '2', 2));
    assertFalse(hasRun);

  }

  @Test
  public void testAddedRunnablesRunOnKeyPressed() {
    initData();
    kh.addPressedEvent(KeyEvent.VK_0, new MockRunnable());
    assertFalse(hasRun);
    kh.keyPressed(ke);
    assertTrue(hasRun);
  }

  @Test
  public void testAddedRunnablesRunOnKeyTypes() {
    initData();
    kh.addTypedEvent(KeyEvent.VK_0, new MockRunnable());
    assertFalse(hasRun);
    kh.keyTyped(ke);
    assertTrue(hasRun);
  }

  @Test
  public void testAddedRunnablesRunOnKeyReleased() {
    initData();
    kh.addReleasedEvent(KeyEvent.VK_0, new MockRunnable());
    assertFalse(hasRun);
    kh.keyReleased(ke);
    assertTrue(hasRun);
  }

  /**
   * To represent a mock runnable object for testing the KeyHandler
   */
  private class MockRunnable implements Runnable {
    private MockRunnable() {
      KeyboardHandlerTest.this.hasRun = false;
    }

    public void run() {
      KeyboardHandlerTest.this.hasRun = true;
    }

  }
}