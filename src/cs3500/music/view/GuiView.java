package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import cs3500.music.model.MusicModelObserver;

/**
 * This interface represents additional functionality for the GUI view.
 *
 * @param <K> represents the type note used in the view
 */
public interface GuiView<K> extends ICompositionView<K> {

  /**
   * Updates the view such that it scrolls once the red line is off the screen
   */
  void updateScroll();


  /**
   * Returns the width of the main panel
   *
   * @return the width of the main panel
   */
  int getWidth();

  /**
   * Sets the key listener in the gui to the desired key listener
   *
   * @param kl a keylistener
   */
  void setKeyListener(KeyListener kl);

  /**
   * Sets the mouse listener in the gui to the desired mouse listener
   *
   * @param ml a keylistener
   */
  void setMouseListener(MouseListener ml);

  /**
   * Pauses the display of the composition.
   */
  void pausePlayback();

  /**
   * Resumes display of the composition.
   */
  void resumePlayback();

  /**
   * recomposes the gui based on the given model
   */
  void recompose(MusicModelObserver<K> model);


  /**
   * Scrolls the panel to the left or right by a given increment
   *
   * @param x how many pixels to scroll the panel by
   */
  void scrollX(int x);

  /**
   * Scrolls the panel up or down by a given increment
   *
   * @param y how many pixels to scroll the panel by
   */
  void scrollY(int y);

  /**
   * EFFECT: brings the display to the beginning of the composition
   */
  void jumpToStart();

  /**
   * EFFECT: toggles pausing for this view
   */
  void updatePause();

  /**
   * Sets the position of this line
   *
   * @param pos the new position of the red line
   */
  void linePosition(int pos);

  /**
   * outputs whether the given gui view is playing a composition currently
   *
   * @return true if this gui view is playing a composition currently
   */
  boolean isPlaying();

}
