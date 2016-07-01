package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.view.ICompositionView;

/**
 * A class for default composition controller
 */
public class CompositionControllerImpl implements ICompositionController<INote> {
  MusicModel<INote> model;
  ICompositionView<INote> view;

  public CompositionControllerImpl(MusicModel<INote> model, ICompositionView<INote> view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void constructView() {
    this.view.buildComposition(this.model);
  }

  @Override
  public void displayView() {
    this.view.displayComposition();
  }

  @Override
  public void removeNote(int x, int y) {
    throw new UnsupportedOperationException("Can not remove a note in this view");
  }

  @Override
  public void addNote(int[] vars) {
    throw new UnsupportedOperationException("Can not add a note in this view");
  }
}
