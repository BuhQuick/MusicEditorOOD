package cs3500.music.model;

import java.util.Objects;

/**
 * This class is used to represent a Note of music.
 */
public class Note implements INote {
  private final Pitch pitch;
  private final int octave;
  private int duration;
  private int startingBeat;
  // :: NEW :: to represent instrument and volume.
  private int instrumentMIDI;
  private int volume;

  /**
   * Construct a note based on the given parameters. Assigns default values to volume and
   * instrumentMIDI to ensure capatability with old tests.
   *
   * @param pitch        the pitch of the note
   * @param octave       the octave the note is being played at
   * @param duration     the duration the note is played for
   * @param startingBeat when the note should start being played
   */
  public Note(Pitch pitch, int octave, int duration, int startingBeat) {
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.startingBeat = startingBeat;
    this.volume = 60;
    this.instrumentMIDI = 0;
  }

  /**
   * :: NEW :: Constructs a note with all parameters assigned.
   *
   * @param pitch        the pitch of the note
   * @param octave       the octave the note is being played at
   * @param duration     the duration the note is played for
   * @param startingBeat when the note should start being played
   * @param volume       the volume at which the note should be played
   * @param instrument   the instrument by which the instrument should be played
   */
  public Note(Pitch pitch, int octave, int duration,
              int startingBeat, int volume, int instrument) {
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.startingBeat = startingBeat;
    this.volume = volume;
    this.instrumentMIDI = instrument;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch, this.octave, this.duration, this.startingBeat);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Note)) {
      return false;
    } else {
      Note n = (Note) o;
      return (this.getPitch().equals(n.getPitch())) && (this.getOctave() == n.getOctave()) &&
              (this.getDuration() == n.getDuration()) &&
              (this.getStartingBeat() == n.getStartingBeat());
    }
  }

  @Override
  public String toString() {
    return this.pitch.toString() + Integer.toString(this.octave);
  }

  @Override
  public Pitch getPitch() {
    return this.pitch;
  }

  @Override
  public int getOctave() {
    return this.octave;
  }

  @Override
  public int getDuration() {
    return this.duration;
  }

  @Override
  public int getStartingBeat() {
    return this.startingBeat;
  }

  @Override
  public int getInstrumentMIDI() {
    return this.instrumentMIDI;
  }

  @Override
  public int getVolume() {
    return this.volume;
  }

  @Override
  public void updateDuration(int newDuration) {
    this.duration = newDuration;
  }

  @Override
  public void updateStartingBeat(int newStart) {
    this.startingBeat = newStart;
  }

  @Override
  public void updateInstrumentMIDI(int instrument) {
    this.instrumentMIDI = instrument;
  }
}