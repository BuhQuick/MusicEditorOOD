package cs3500.music.model;

import java.util.Comparator;

/**
 * This class is a comparator used to compare two Notes.
 */
public class NoteComparator implements Comparator<INote> {

  @Override
  public int compare(INote o1, INote o2) {
    return (o1.getPitch().ordinal() + (o1.getOctave()) * 100) -
            (o2.getPitch().ordinal() + (o2.getOctave() * 100));
  }
}
