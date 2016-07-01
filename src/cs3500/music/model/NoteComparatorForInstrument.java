package cs3500.music.model;

import java.util.Comparator;

/**
 * Comparator to sort notes by the instrument that plays them.
 */
public class NoteComparatorForInstrument implements Comparator<INote> {

  public NoteComparatorForInstrument() {

  }

  @Override
  public int compare(INote n1, INote n2) {
    return n1.getInstrumentMIDI() - n2.getInstrumentMIDI();
  }

}
