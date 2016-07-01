package cs3500.music.model;

/**
 * This enumeration represents all of the pitches a note can have.
 */
public enum Pitch {
  C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, B;

  /**
   * :: NEW :: provided a means to convert from integer to an enumeration representation
   *
   * @param p the integer-valued pitch to convert from
   * @return the pitch corresponding to the given integer
   */
  public static Pitch integerToPitch(int p) {
    switch (p % 12) {
      case 0:
        return C;
      case 1:
        return C_SHARP;
      case 2:
        return D;
      case 3:
        return D_SHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return F_SHARP;
      case 7:
        return G;
      case 8:
        return G_SHARP;
      case 9:
        return A;
      case 10:
        return A_SHARP;
      default:
        return B;
    }
  }

  @Override
  public String toString() {
    switch (ordinal()) {
      case 0:
        return "C";
      case 1:
        return "C#";
      case 2:
        return "D";
      case 3:
        return "D#";
      case 4:
        return "E";
      case 5:
        return "F";
      case 6:
        return "F#";
      case 7:
        return "G";
      case 8:
        return "G#";
      case 9:
        return "A";
      case 10:
        return "A#";
      default:
        return "B";
    }
  }
}
