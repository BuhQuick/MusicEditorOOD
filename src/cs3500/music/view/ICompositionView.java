package cs3500.music.view;


import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * An interface for the different types of view for a given music model.
 */
public interface ICompositionView<K> {

  /**
   * EFFECT: display the composition based on this view type.
   */
  void displayComposition();

  /**
   * EFFECT: builds the view for a composition through a read only model.
   *
   * @param model a read-only version of the model to be built.
   */
  void buildComposition(MusicModelObserver<K> model);

}
