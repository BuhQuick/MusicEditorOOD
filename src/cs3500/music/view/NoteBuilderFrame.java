package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.controller.ICompositionController;
import cs3500.music.model.INote;
import cs3500.music.model.Pitch;

/**
 * A class used to createa a JFrame that allows the user to build a note.
 */
public class NoteBuilderFrame extends javax.swing.JFrame {
  private JPanel mainPanel;
  private JList<String> pitchList;
  private JTextField instrument, octave, startBeat, duration, volume;
  private JButton noteBuilder;
  private int[] noteVars;

  /**
   * Constructs a note builder frame. The frame has a JList that provides the user the option to
   * selected a pitch for the note. It also has 5 text fields, each of which is unique to one
   * of the note's parameters. Once each text field has sufficient input to be passed along to
   * the note builder, the "build" button can be pressed to add a note.
   * noteVars is an array that holds the parameters for building a note.
   */
  public NoteBuilderFrame() {
    this.setSize(400, 400);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setLayout(new FlowLayout());
    this.instrument = new JTextField("Enter instrument here (0-127)");
    this.octave = new JTextField("Enter octave here (0-10)");
    this.startBeat = new JTextField("Enter start beat here (0-X)");
    this.duration = new JTextField("Enter duration of beat here (0-X)");
    this.volume = new JTextField("Enter volume of beat here (0-127)");
    this.noteBuilder = new JButton("Build");
    this.noteBuilder.setActionCommand("Build");
    this.noteVars = new int[6];
  }

  /**
   * Ensures that there are valid inputs for each button. If there is not, the button does
   * nothing.
   *
   * @return whether the user has given valid inputs for a note
   */
  private boolean validBuild() {
    try {
      int oct = Integer.parseInt(octave.getText());
      int dur = Integer.parseInt(duration.getText());
      int start = Integer.parseInt(startBeat.getText());
      int vol = Integer.parseInt(volume.getText());
      int instr = Integer.parseInt(instrument.getText());
      return oct >= 0 && oct <= 10 && dur >= 0 && start >= 0 && vol >= 0 && vol <= 127 &&
              instr >= 0 && instr <= 127;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * EFFECT: creates a pop up window with an interface for creating notes If the fields given as
   * input are not valid, the note will not be built.
   *
   * @param controller the controller through which the note will be built.
   */
  public void buildFrame(ICompositionController<INote> controller) {
    String[] pitches = new String[Pitch.values().length];
    this.mainPanel = new JPanel();
    this.setLayout(new FlowLayout());
    int n = 0;
    for (Pitch p : Pitch.values()) {
      pitches[n] = p.toString();
      n += 1;
    }
    this.pitchList = new JList<String>(pitches);
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
          noteVars[0] = pitchList.locationToIndex(e.getPoint());
        }
      }
    };
    this.pitchList.addMouseListener(mouseListener);
    this.mainPanel.add(this.pitchList);
    this.mainPanel.add(this.instrument);
    this.mainPanel.add(this.octave);
    this.mainPanel.add(this.startBeat);
    this.mainPanel.add(this.duration);
    this.mainPanel.add(this.volume);
    this.noteBuilder.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
          case "Build":
            if (validBuild()) {
              noteVars[1] = Integer.parseInt(octave.getText());
              noteVars[2] = Integer.parseInt(duration.getText());
              noteVars[3] = Integer.parseInt(startBeat.getText());
              noteVars[4] = Integer.parseInt(volume.getText());
              noteVars[5] = Integer.parseInt(instrument.getText());
              controller.addNote(noteVars);
            }
            break;
          default:
            System.err.print("This shouldn't happen");
            break;
        }
      }
    });
    this.mainPanel.add(this.noteBuilder);
    this.add(mainPanel);
    this.pack();
    this.setVisible(true);
  }
}
