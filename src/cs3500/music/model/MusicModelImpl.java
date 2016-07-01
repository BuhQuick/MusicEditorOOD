package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cs3500.music.util.CompositionBuilder;

/**
 * This is an implementation of the MusicModel, parametrized over my implemnentation of a Note. It
 * implements all of the desired features specified by the MusicModel interface.
 */
// CHANGELOG: Made pitchRangeAsList public, added to interface
public final class MusicModelImpl implements MusicModel<INote> {
  private List<INote> composition;
  // :: NEW :: a composition now has a tempo.
  private int tempo;

  /**
   * Constructs a MusicModelImpl with a blank slate of notes.
   */
  public MusicModelImpl() {
    this.composition = new ArrayList<INote>();
    this.tempo = 0;
  }

  /**
   * Constructs a MusicModelImpl with a given sheet of notes
   *
   * @param composition the notes for this composition
   */
  public MusicModelImpl(List<INote> composition) {
    this.composition = composition;
    this.tempo = 0;
  }

  /**
   * private Builder constructor the the music model.
   *
   * @param b the builder
   */
  private MusicModelImpl(Builder b) {
    this.composition = b.compositionBuild;
    this.setTempo(b.tempoBuild);
  }

  @Override
  public void addNote(INote note) {
    this.composition.add(note);
  }

  @Override
  public void removeNote(INote note) {
    if (!this.composition.remove(note)) {
      throw new IllegalArgumentException("Note " + note.toString() + " does not exist in " +
              "the composition! Can not be removed.");
    }
  }

  @Override
  public void interweaveMusic(MusicModel<INote> composition) {
    this.composition.addAll(composition.getComposition());
  }

  @Override
  public void extendMusic(MusicModel<INote> composition) {
    List<INote> compositionToExtend = composition.getComposition();
    int extension = this.maxBeat();
    for (INote n : compositionToExtend) {
      INote noteCopy = new Note(n.getPitch(), n.getOctave(), n.getDuration(),
              extension + n.getStartingBeat(), n.getVolume(), n.getInstrumentMIDI());
      this.composition.add(noteCopy);
    }
  }

  @Override
  public List<INote> getCompositionAtBeat(int beat) {
    List<INote> notesAtBeat = new ArrayList<INote>();
    for (INote n : this.composition) {
      if (n.getStartingBeat() <= beat && beat <= (n.getStartingBeat() + n.getDuration())) {
        notesAtBeat.add(n);
      }
    }
    return notesAtBeat;
  }

  @Override
  public List<INote> getComposition() {
    List<INote> compositionCopy = new ArrayList<INote>(this.composition);
    Collections.sort(compositionCopy, new NoteComparator());
    return compositionCopy;
  }

  @Override
  public int maxBeat() {
    int maxSoFar = 0;
    for (INote n : this.composition) {
      if (n.getStartingBeat() + n.getDuration() > maxSoFar) {
        maxSoFar = n.getStartingBeat() + n.getDuration();
      }
    }
    return maxSoFar;
  }

  /**
   * Finds the lowest tone note in the composition
   *
   * @return the lowest tone note
   */
  private INote findLowestTone() {
    return Collections.min(this.getComposition(), new NoteComparator());
  }

  /**
   * Finds the highest tone note in the composition
   *
   * @return the highest tone note
   */
  private INote findHighestTone() {
    return Collections.max(this.getComposition(), new NoteComparator());
  }

  /**
   * Gets a String representation of the pitch range for the composition, attempting to space each
   * pitch relatively centered to a 5 character column.
   *
   * @return a String that has all pitches from the lowest to highest in the composition.
   */
  private String getPitchRange() {
    String pitchRange = "";
    String beatBuffer = Integer.toString(maxBeat() - 1);
    while (pitchRange.length() != beatBuffer.length()) {
      pitchRange += " ";
    }
    for (String s : pitchRangeAsList()) {
      if (s.length() == 2) {
        pitchRange = pitchRange + "  " + s + " ";
      } else if (s.length() == 3) {
        pitchRange = pitchRange + " " + s + " ";
      } else {
        pitchRange = pitchRange + " " + s;
      }
    }
    pitchRange += "\n";
    return pitchRange;
  }

  /**
   * Gets a list containing the range of pitches used in this composition in their String
   * representation.
   *
   * @return a list of strings that represent the pitch range for the composition.
   */
  @Override
  public List<String> pitchRangeAsList() {
    List<String> rangeAsList = new ArrayList<String>();
    int lowestOctave = findLowestTone().getOctave();
    Pitch lowestPitch = findLowestTone().getPitch();
    List<Pitch> allPitches = Arrays.asList(Pitch.values());
    boolean pitchSeen = false;
    while ((lowestOctave != findHighestTone().getOctave()) ||
            (!lowestPitch.equals(findHighestTone().getPitch()))) {
      for (Pitch p : allPitches) {
        if (lowestPitch.equals(p)) {
          pitchSeen = true;
        }
        if (pitchSeen) {
          String toBeAdded = p.toString() + Integer.toString(lowestOctave);
          lowestPitch = p;
          rangeAsList.add(toBeAdded);
          if (lowestPitch.equals(findHighestTone().getPitch()) &&
                  (lowestOctave == findHighestTone().getOctave())) {
            break;
          }
        }
      }
      if (lowestOctave != findHighestTone().getOctave()) {
        lowestOctave += 1;
        if (!allPitches.get(0).equals(findHighestTone().getPitch())) {
          lowestPitch = allPitches.get(0);
        }
      }
    }
    return rangeAsList;
  }

  @Override
  public String viewComposition() {
    if (this.composition.size() == 0) {
      throw new RuntimeException("There is no composition to view!");
    }
    String view = getPitchRange();
    String maxBeat = Integer.toString(this.maxBeat() - 1);
    int beatSoFar = 0;

    while (beatSoFar != this.maxBeat()) {
      for (int n = Integer.toString(beatSoFar).length(); n < maxBeat.length(); n += 1) {
        view += " ";
      }
      view += Integer.toString(beatSoFar);
      for (String s : pitchRangeAsList()) {
        boolean added = false;
        for (INote n : this.composition) {
          if (s.equals(n.toString())) {
            if (beatSoFar == n.getStartingBeat() && !added) {
              view += "  X  ";
              added = true;
            }
          }
        }
        for (INote n : this.composition) {
          if (s.equals(n.toString())) {
            if (n.getStartingBeat() < beatSoFar && !added
                    && beatSoFar <= (n.getStartingBeat() + n.getDuration())) {
              view += "  |  ";
              added = true;
            }
          }
        }
        if (!added) {
          view += "     ";
        }
      }
      view += "\n";
      beatSoFar += 1;
    }
    return view;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   * :: NEW :: ... because music has a tempo EFFECT: set the tempo of this composition to the
   * desired tempo
   *
   * @param tempo the desired tempo
   */
  protected void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * Static builder class used to construct a MusicModel from text file input translated by
   * MusicReader
   */
  public static final class Builder implements CompositionBuilder<MusicModel<INote>> {
    private List<INote> compositionBuild;
    private int tempoBuild;

    public Builder() {
      this.compositionBuild = new ArrayList<INote>();
      this.tempoBuild = 0;
    }

    @Override
    public MusicModel<INote> build() {
      return new MusicModelImpl(this);
    }

    @Override
    public CompositionBuilder<MusicModel<INote>> setTempo(int tempo) {
      this.tempoBuild = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<MusicModel<INote>> addNote(int start, int end, int instrument,
                                                         int pitch, int volume) {
      this.compositionBuild.add(new Note(Pitch.integerToPitch(pitch),
              (int) (Math.floor(pitch / 12)) - 1,
              end - start, start, volume, instrument));
      return this;
    }
  }
}
