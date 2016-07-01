package cs3500.music.model;

import java.util.List;

/**
 * This interface is used to represent a model for music compositions. It contains certain methods
 * that may be useful for the composition. It is parametrized over K, a given Note implementation.
 */
public interface MusicModel<K> extends MusicModelObserver<K> {

  /**
   * EFFECT: add a note to the current composition
   *
   * @param note the note to be added
   */
  void addNote(K note);

  /**
   * EFFECT: remove a note from the current composition.
   *
   * @param note the note to be removed
   */
  void removeNote(K note);

  /**
   * EFFECT: merge one music composition with this one such that it plays simultaneously
   *
   * @param music the music composition to be merged
   */
  void interweaveMusic(MusicModel<K> music);

  /**
   * EFFECT: extend this music composition to play the given one immediately after
   *
   * @param music the music composition that will be used to extend this composition.
   */
  void extendMusic(MusicModel<K> music);
}
