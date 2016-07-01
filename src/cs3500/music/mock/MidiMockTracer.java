package cs3500.music.mock;

/**
 * The class that holds a StringBuilder for tracing the MidiViewer's actions as a composition is
 * built and played.
 */
public class MidiMockTracer {
  private static StringBuilder trace = new StringBuilder();

  public MidiMockTracer() {
  }

  /**
   * EFFECT: records an action initiated by the midiview to the string builder
   *
   * @param s the action that needs to be recorded
   */
  public static void updateTrace(String s) {
    trace.append(s);
  }

  public static void resetTrace() {
    trace = new StringBuilder();
  }

  /**
   * Get the actions that have occurred so far in an execution using the mock midi.
   *
   * @return a string of all actions so far
   */
  public static String getTrace() {
    return trace.toString();
  }
}
