package cs3500.music.view;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.*;

import javax.sound.midi.Sequencer;
import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;

/**
 * To represent the main GUI View
 */
// CHANGELOG: Now implements GuiView instead.
public class GuiViewFrame extends javax.swing.JFrame
        implements GuiView<INote> {

  private java.util.List<String> pitches;
  private java.util.List<INote> notes;
  private int maxBeats;
  private JPanel mainPanel;
  private JScrollPane scroll;
  private JScrollBar horizontalsb;
  private JScrollBar verticalsb;
  private NotePanel notePanel;
  private PitchPanel pitchPanel;
  private MusicModelObserver<INote> model;
  private int shift;
  private boolean isPaused = false;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.shift = 0;
  }

  @Override
  public void updatePause() {
    isPaused = !isPaused;
  }

  @Override
  public void displayComposition() {
    this.scroll = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
    this.scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    horizontalsb = scroll.getHorizontalScrollBar();
    verticalsb = scroll.getVerticalScrollBar();
    this.getContentPane().add(scroll);
    this.setSize(1200, (this.pitches.size() + 5) * 15);
    this.setVisible(true);
    mainPanel.setVisible(true);
    this.setFocusable(true);
  }

  @Override
  public void linePosition(int s) {
    this.shift = s;
  }

  @Override
  public void updateScroll() {
    if (this.notePanel.shift > this.horizontalsb.getWidth()
            && this.notePanel.shift % (this.horizontalsb.getWidth()) - 45 < 15) {
      horizontalsb.setValue(this.notePanel.shift);
    }
  }

  @Override
  public void jumpToStart() {
    horizontalsb.setValue(0);
  }

  @Override
  public int getWidth() {
    return this.maxBeats * 15;
  }


  @Override
  public void buildComposition(MusicModelObserver<INote> model) {
    this.model = model;
    this.pitches = model.pitchRangeAsList();
    this.notes = model.getComposition();
    this.maxBeats = model.maxBeat();
    this.mainPanel = new JPanel(new BorderLayout());
    this.notePanel = new NotePanel(this.pitches, this.notes, maxBeats);
    this.pitchPanel = new PitchPanel(this.pitches);
    mainPanel.add(this.notePanel, BorderLayout.CENTER);
    mainPanel.add(this.pitchPanel, BorderLayout.WEST);
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    this.addKeyListener(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    this.addMouseListener(ml);
    this.notePanel.addMouseListener(ml);
  }

  @Override
  public void resumePlayback() {
    throw new UnsupportedOperationException("Resuming playback is not supported for GUI view.");
  }

  @Override
  public void pausePlayback() {
    throw new UnsupportedOperationException("Pausing playback is not supported for GUI view.");
  }

  @Override
  public void scrollX(int x) {
    horizontalsb.setValue(horizontalsb.getValue() + x);
  }

  @Override
  public void scrollY(int y) {
    verticalsb.setValue(verticalsb.getValue() + y);
  }

  @Override
  public void recompose(MusicModelObserver<INote> model) {
    this.model = model;
    this.notePanel.setNotes(this.model.getComposition());
    this.notePanel.removeAll();
    this.notes = this.model.getComposition();
    this.notePanel.shift = 0;
    if (!isPaused) {
      this.jumpToStart();
    }
    this.pitchPanel.setPitches(model.pitchRangeAsList());
    this.notePanel.setPitches(model.pitchRangeAsList());
    this.notePanel.setMaxBeat(model.maxBeat());
    this.mainPanel.setPreferredSize(new Dimension(this.notePanel.getWidth(),
            (this.pitches.size() + 5) * 15));
    this.revalidate();
    this.pitchPanel.repaint();
    this.notePanel.repaint();
  }

  @Override
  public boolean isPlaying() {
    return this.isPaused;
  }

  /**
   * To represent the notes visualized
   */
  private class NotePanel extends JPanel {
    protected int shift;
    private java.util.List<INote> notes;
    private java.util.List<String> pitches;
    private int numPitches;
    private int maxBeat;

    /**
     * Construct a note panel
     *
     * @param pitches pitches to use to vertically align the notes
     * @param notes   the notes to display
     * @param maxBeat the maximum beat of the notes
     */
    private NotePanel(java.util.List<String> pitches, java.util.List<INote> notes, int maxBeat) {
      super();
      this.notes = notes;
      this.pitches = pitches;
      this.numPitches = pitches.size();
      this.maxBeat = maxBeat;
      this.setPreferredSize(new Dimension(maxBeat * 15, (numPitches) * 15));
      this.setFocusable(true);
      this.shift = 0;

    }

    /**
     * Set the notes in the view to the new list of notes. Repaints to update.
     *
     * @param notes the new list of notes
     */
    protected void setNotes(java.util.List<INote> notes) {
      this.notes = notes;
      this.repaint();
    }

    /**
     * Set the max beat for the view. Repaints to update.
     *
     * @param beat the max beat
     */
    protected void setMaxBeat(int beat) {
      this.maxBeat = beat;
      this.repaint();
    }

    /**
     * Set the pitches for the pitch list to the new list of pitches. Repaints to update.
     *
     * @param pitches the new list of pitches
     */
    protected void setPitches(java.util.List<String> pitches) {
      this.pitches = pitches;
      this.numPitches = pitches.size();
      this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.generateGrid(g);
      if (!isPaused) {
        GuiViewFrame.this.updateScroll();
        this.shift = GuiViewFrame.this.shift;
      }

      this.repaint();


    }


    /**
     * Generates the grid of notes, aligned to the pitches and labeled for each 16th beat.
     *
     * @param g the graphic onwhich everything is drawn on
     */
    private void generateGrid(Graphics g) {
      for (int j = 0; j <= maxBeat; j++) {
        if (j % 16 == 0)
          g.drawString(Integer.toString(j), j * 15, 10);
      }
      for (int i = 1; i <= numPitches; i++) {
        for (int j = 0; j < maxBeat; j++) {
          if (j % 4 == 0) {
            g.drawRect(j * 15, i * 15, 60, 15);
          }
        }
      }
      for (INote n : notes) {
        int yPos = this.pitches.size() -
                (this.pitches.indexOf(n.getPitch().toString() + n.getOctave()));
        int xPos = n.getStartingBeat();
        g.setColor(Color.BLACK);
        g.fillRect(xPos * 15, yPos * 15, 15, 15);
        xPos += 1;
        g.setColor(Color.GREEN);
        for (int i = xPos; i < xPos + n.getDuration() - 1; i++) {
          g.fillRect(i * 15, yPos * 15, 15, 15);
        }
      }
      g.setColor(Color.RED);
      g.drawLine(shift, 15, shift, (this.pitches.size() + 1) * 15);
    }
  }

  /**
   * To represent a JPanel for the list of pitches
   */
  private class PitchPanel extends JPanel {
    private java.util.List<String> pitches;

    /**
     * To construct the Panel which displays the Pitch
     *
     * @param pitches the pitches to display on the panel
     */
    private PitchPanel(java.util.List<String> pitches) {
      super();
      this.pitches = pitches;
      this.setPreferredSize(new Dimension(25, (pitches.size() + 1) * 15));
    }

    /**
     * Set the pitches for the pitch list to the new list of pitches
     *
     * @param pitches the new list of pitches
     */
    protected void setPitches(java.util.List<String> pitches) {
      this.pitches = pitches;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int yPos = (pitches.size() + 1) * 15;
      for (String s : pitches) {
        g.drawString(s, 0, yPos);
        yPos -= 15;
      }
    }

  }

}
