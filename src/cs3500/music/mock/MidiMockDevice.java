package cs3500.music.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.*;

/**
 * A mock midi sequencer used to record actions made by the MidiView.
 */
public class MidiMockDevice implements Sequencer {
  private Sequence sequence;

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {
    MidiMockTracer.updateTrace("Sequencer opened.\n");
  }

  @Override
  public void close() {
    MidiMockTracer.updateTrace("Sequencer closed.\n");
  }

  @Override
  public boolean isOpen() {
    return false;
  }

  /**
   * Updates sequence, and records all of the messages that were sent to the track in the
   * sequence.
   *
   * @param sequence the sequence to add to the mock sequencer.
   */
  @Override
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    this.sequence = sequence;

    MidiMockTracer.updateTrace("Sequence set." + "\nSequence has MS length: "
            + sequence.getMicrosecondLength()
            + ", resolution: " + sequence.getResolution() + "\n");

    for (Track t : sequence.getTracks()) {
      for (int n = 0; n < t.size(); n += 1) {
        MidiMessage m = t.get(n).getMessage();
        String status = Integer.toBinaryString(m.getStatus());
        status = status.substring(0, 4);
        /** taken from:
         * https://www.midi.org/specifications/item/table-1-summary-of-midi-message
         **/
        switch (status) {
          case "1011":
            status = "Control change";
            break;
          case "1001":
            status = "Note on";
            break;
          case "1000":
            status = "Note off";
            break;
          case "1100":
            status = "Program change";
            break;
          default:
            status = "End sequence";
        }
        MidiMockTracer.updateTrace("MidiMessage \"" + status + "\" at beat "
                + (t.get(n).getTick() / sequence.getResolution()) +
                " was logged successfully." + "\n");
      }
    }
  }

  @Override
  public Sequence getSequence() {
    return this.sequence;
  }

  @Override
  public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {

  }

  @Override
  public void start() {
    MidiMockTracer.updateTrace("Playback started by sequencer.\n");
  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void startRecording() {

  }

  @Override
  public void stopRecording() {

  }

  @Override
  public boolean isRecording() {
    return false;
  }

  @Override
  public void recordEnable(Track track, int channel) {

  }

  @Override
  public void recordDisable(Track track) {

  }

  @Override
  public float getTempoInBPM() {
    return 0;
  }

  @Override
  public void setTempoInBPM(float bpm) {

  }

  @Override
  public float getTempoInMPQ() {
    return 0;
  }

  @Override
  public void setTempoInMPQ(float mpq) {
    MidiMockTracer.updateTrace("TempoInMPQ set.\n");
  }

  @Override
  public float getTempoFactor() {
    return 0;
  }

  @Override
  public void setTempoFactor(float factor) {

  }

  @Override
  public long getTickLength() {
    return 0;
  }

  @Override
  public long getTickPosition() {
    return 0;
  }

  @Override
  public void setTickPosition(long tick) {

  }

  @Override
  public long getMicrosecondLength() {
    return 0;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public void setMicrosecondPosition(long microseconds) {

  }

  @Override
  public SyncMode getMasterSyncMode() {
    return null;
  }

  @Override
  public void setMasterSyncMode(SyncMode sync) {

  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    return null;
  }

  @Override
  public void setSlaveSyncMode(SyncMode sync) {

  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public void setTrackMute(int track, boolean mute) {

  }

  @Override
  public boolean getTrackMute(int track) {
    return false;
  }

  @Override
  public void setTrackSolo(int track, boolean solo) {

  }

  @Override
  public boolean getTrackSolo(int track) {
    return false;
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener listener) {
    return false;
  }

  @Override
  public void removeMetaEventListener(MetaEventListener listener) {

  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
    return new int[0];
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
    return new int[0];
  }

  @Override
  public long getLoopStartPoint() {
    return 0;
  }

  @Override
  public void setLoopStartPoint(long tick) {

  }

  @Override
  public long getLoopEndPoint() {
    return 0;
  }

  @Override
  public void setLoopEndPoint(long tick) {

  }

  @Override
  public int getLoopCount() {
    return 0;
  }

  @Override
  public void setLoopCount(int count) {

  }

  @Override
  public int getMaxReceivers() {
    return 1;
  }

  @Override
  public int getMaxTransmitters() {
    return 1;
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
