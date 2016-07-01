package cs3500.music;

import org.junit.Test;

import cs3500.music.controller.CompositeController;
import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for testing CompositeController
 */
public class CompositeControllerTest {
  private GuiView<INote> view;
  private MusicModel<INote> model;
  private CompositeController controller;

  private void initData() {
    this.view = new GuiViewFrame();
    this.model = new MusicModelImpl.Builder().build();
    this.model.addNote(new Note(Pitch.C, 1, 5, 0));

    this.controller = new CompositeController(model, view);
    this.controller.constructView();
    this.controller.displayView();
  }

  @Test
  public void testAddNoteIncreasesSizeOfComposition() {
    initData();
    int[] noteVals = {0, 1, 2, 3, 4, 5};
    assertTrue(model.getComposition().size() == 1);
    this.controller.addNote(noteVals);
    assertTrue(model.getComposition().size() == 2);

  }

  @Test
  public void testAddNoteMakesGivenNote() {
    initData();
    int[] noteVals = {0, 1, 2, 3, 4, 5};
    this.controller.addNote(noteVals);
    assertTrue(model.getComposition().contains(new Note(Pitch.integerToPitch(0), 1, 2, 3, 4, 5)));
  }

  @Test
  public void testRemoveNoteDecreasesSizeOfComposition() {
    initData();
    int[] noteVals = {11, 1, 2, 0, 4, 5};
    this.controller.addNote(noteVals);
    this.controller.removeNote(0, 15);
    assertTrue(model.getComposition().size() == 1);
  }

  @Test
  public void testRemoveNoteRemovesNoteFromModel() {
    initData();
    int[] noteVals = {11, 1, 2, 0, 4, 5};
    this.controller.addNote(noteVals);
    assertTrue(model.getComposition().contains(
            new Note(Pitch.integerToPitch(11), 1, 2, 0, 4, 5)));
    this.controller.removeNote(0, 15);
    assertFalse(model.getComposition().contains(
            new Note(Pitch.integerToPitch(11), 1, 2, 0, 4, 5)));
  }

}