package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_P;

/**
 * A keyboard handler made to only handle events specified in it's map fields
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> typedEvent;
  private Map<Integer, Runnable> pressedEvent;
  private Map<Integer, Runnable> releasedEvent;

  public KeyboardHandler() {
    this.typedEvent = new HashMap<Integer, Runnable>();
    this.pressedEvent = new HashMap<Integer, Runnable>();
    this.releasedEvent = new HashMap<Integer, Runnable>();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (this.typedEvent.get(e.getKeyCode()) != null) {
      this.typedEvent.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (this.pressedEvent.get(e.getKeyCode()) != null) {
      this.pressedEvent.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.releasedEvent.get(e.getKeyCode()) != null) {
      this.releasedEvent.get(e.getKeyCode()).run();
    }
  }

  public void addTypedEvent(int ke, Runnable r) {
    this.typedEvent.put(ke, r);
  }

  public void addPressedEvent(int ke, Runnable r) {
    this.pressedEvent.put(ke, r);
  }

  public void addReleasedEvent(int ke, Runnable r) {
    this.releasedEvent.put(ke, r);
  }
}
