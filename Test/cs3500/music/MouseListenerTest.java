package cs3500.music;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for MouseListener.
 */
public class MouseListenerTest {

  private MouseListenerTest.MockMouseListener mml;
  private Component sc;

  private boolean hasActivated = false;

  private void initData() {
    this.mml = new MouseListenerTest.MockMouseListener();
    this.sc = new Button("mock");

  }

  @Test
  public void testMouseClicked() {
    initData();
    assertFalse(hasActivated);
    mml.mouseClicked(new MouseEvent(sc, 0, 0, 0, 100, 100, 1, false));
    assertTrue(hasActivated);
  }

  @Test
  public void testMousePressed() {
    initData();
    assertFalse(hasActivated);
    mml.mousePressed(new MouseEvent(sc, 0, 0, 0, 100, 100, 1, false));
    assertTrue(hasActivated);
  }

  @Test
  public void testMouseReleased() {
    initData();
    assertFalse(hasActivated);
    mml.mouseReleased(new MouseEvent(sc, 0, 0, 0, 100, 100, 1, false));
    assertTrue(hasActivated);
  }

  @Test
  public void testMouseEntered() {
    initData();
    assertFalse(hasActivated);
    mml.mouseEntered(new MouseEvent(sc, 0, 0, 0, 100, 100, 1, false));
    assertTrue(hasActivated);
  }

  @Test
  public void testMouseExisted() {
    initData();
    assertFalse(hasActivated);
    mml.mouseExited(new MouseEvent(sc, 0, 0, 0, 100, 100, 1, false));
    assertTrue(hasActivated);
  }

  private class MockMouseListener implements MouseListener {
    private MockMouseListener() {
      MouseListenerTest.this.hasActivated = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      MouseListenerTest.this.hasActivated = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      MouseListenerTest.this.hasActivated = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      MouseListenerTest.this.hasActivated = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      MouseListenerTest.this.hasActivated = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
      MouseListenerTest.this.hasActivated = true;
    }
  }

}

