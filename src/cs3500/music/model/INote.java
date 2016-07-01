package cs3500.music.model;

/**
 * :: NEW :: Previously, a note was a concrete class. Interface added. An interface for
 * representing a Note.
 */
public interface INote {

  /**
   * Returns the pitch of this note
   *
   * @return the pitch of this note
   */
  Pitch getPitch();

  /**
   * Return the octave of this note
   *
   * @return the octave of this note
   */
  int getOctave();

  /**
   * Return the duration of this note
   *
   * @return the duration of this note
   */
  int getDuration();

  /**
   * Return when this note should start
   *
   * @return the starting beat for this note
   */
  int getStartingBeat();

  /**
   * Return the instrument by which this note is played
   *
   * @return the MIDI value for this instrument
   */
  int getInstrumentMIDI();

  /**
   * Return the volume of this note
   *
   * @return the volume of this note
   */
  int getVolume();

  /**
   * EFFECT: Updates the duration to a new specified duration
   *
   * @param duration the desired duration
   */
  void updateDuration(int duration);

  /**
   * EFFECT: Updates the starting beat of the note
   *
   * @param beat the desired starting beat
   */
  void updateStartingBeat(int beat);

  /**
   * EFFECT: Updates the instrument by which this note is played
   *
   * @param instrument the MIDI value for the new instrument
   */
  void updateInstrumentMIDI(int instrument);
}
