:: READ ME ::

IN ORDER TO SELECT WHICH TYPE OF COMPOSITION:
Type after the textfile name in console either:
- "console" - provides console view
- "midi" - provides just midi (sound) view
- "visual" - provides just visual view
- "composite" - provides the main view, with both midi and visual.

:: Instructions ::
[P] - pauses the playback of the composition
[S] - resumes playback of the composition
[HOME] - jumps to the beginning of the composition in the gui view
[END] - jumps to the end of the composition in the gui view
[1] - opens up a new window for note construction. If in composite view, the composition MUST be paused. Jump to :: Note Builder Instructions :: for more info.
left-click on the head of a note - removes that note from the composition. the composition MUST be pause if composite view, and the composition will restart with the changes you've made.
On a simple visual view, the note will be removed from the screen.

:: END-OF instructions ::

 :: Note Builder Instructions ::
left-click the desired pitch
input the desired midi instrument (>= 0, <= 127)
input the desired octave (range: 0-10)
input the desired start beat (>= 0)
input the desired duration (>= 0)
input the desired volume (>= 0, <= 127)

Once your inputs are valid, you may click the "build" button to insert the note.
If your inputs to do not match the given specifications, the note will not be built, and the button will do nothing. This was inspired by the submission server's "report time" field.

The composition restarts after each change, so you can hear the composition with your new notes.
:: END-OF note builder instructions::

:: DESIGN ::

The overall architecture of this program is based on the Model-View-Controller pattern.

Controller:

Interface:

ICompositionController - represents the basic controller for a composition.

Class:

CompositionController - Implementation of ICompositionController.

KeyHandler - Implementation of KeyListener.
RemoveNoteOnClickListener - Implementation of MouseListener, specifically to remove notes on click.
JumpToEnd - Implementation of Runnable in order to cause the GUI to jump to the end of the composition.
JumpToStart - Implementation of Runnable in order to cause the GUI to jump to the start of the composition.
PauseMusic - Implementation of Runnable in order to cause the composition to pause.
PlayMusic - Implementation of Runnable in order to cause the composition to play after being paused.
ScrollDown - Implementation of Runnable in order to scroll the GUI downward if possible.
ScrollUp - Implementation of Runnable in order to scroll the GUI upward if possible.
ScrollLeft - Implementation of Runnable in order to scroll the GUI left if possible.
ScrollRight - Implementation of Runnable in order to scroll the GUI right if possible.
AddNote - Implementation of Runnable in order to add a node from the view to the model

Changes + Justification:

- Interface ICompositionController is generic so it is not tied to any specific Note implementation

- CompositionController takes in a Model and View in order to operate.
This is because it delegates actions performed to and from the view and imposes the consequences of the actions to and from the model, as well as the view.
For example, if a note is deleted, the note information is sent from the view to the controller, and then from the controller to the model where it is deleted.
The view then updates in order to show that the note is now removed.This loosens coupling overall as now the view does not need to know of the model's existence and vice-verse as the controller serves as the middle man.

- KeyHandlers - represent a collection of runnables which perform a function whenever their specific key combination is pressed.
This allows for a much more organized way to have actions based on user input, as each action is mapped to a key which is bounded in the controller.

- RemoveNoteOnClickListeners - represent a MouseListener to specifically remove notes from the composition.
This was not in a full-fledged Handler type class since information on the type of mouse event does not provide enough information for this Listener to operate.
Concretely, this Listener required the x and y position of the mouse when it was clicked, and a general Map for mouse events or buttons to runnables
would not provide the runnable with this information. As a result, we felt that this was the simplist approach in order to achieve this type of functioanlity.

- JumpToEnd / JumpToStart / PauseMusic / PlayMusic / ScrollDown / ScrollUp / ScrollLeft / ScrollRight - These are all runnable implementations which are mapped by keys in the KeyHandler.
They all primarily operate by calling methods defined in the Interface GuiView in order to apply modifications to the view through key input.
These runnables all therefore contain an instance of the GuiView from the controller in order to modify it through its methods in their respective run methods.

- AddNote - In order to have the user be able to create a note in a GUI, we created this runnable as well as a GUI specifically for note creation.
This Runnable in particular causes the GUI to appear and sends the information from the GUI to the controller. This information contains data for the newly created note. This loosens coupling as now
the GUI specifically does not need an instance of a model or controller in order to allow the user to create notes.

Model:

- We did not make any changes to the model for this assignment.

Interface:

MusicModelObserver - represents the functionality for a read-only music model.
MusicModel - represents the functionality of a read-only music model plus the ability to modify the model.
INote - represents a note object.

Class:

MusicModelImpl - implementation of musicmodel and musicmodelobserver.
MusicModelImpl.Builder - Builder class for music model to allow for constructing music models based on text input.
Note - implementation of INote
NoteComparator - allowed for comparing two notes based on pitch an octave.
NoteComparatorForInstrument - allowed for comparing two notes based on instrument.
Pitch - enum representing all of the pitches of a note.

View:

- We did not make any changes to interfaces or classes from the previous assignment aside from those which required the new interface GuiView and subsequent classes to support its functionality.

Interface:
GuiView - Represents all of the functionality for a GUI view of a composition.
ICompositionView - Represents all of the functionality for a General view of a composition.

Class:

CompositionView - Implementation of GuiView. Represents combined view of midi and visual
ConsoleView - Implementation of ICompositionView. Represents a textual representation of a composition
GuiViewFrame - Implementation of GuiView. Represents the GUI view
MidiViewImpl - Implementation of ICompositionView. Represents the midi view
NoteBuilderFrame - Represents a GUI specifically for note creation.
ViewBuilder- Constructs an ICompositionView
GuiViewBuilder - Constructs a GUIView

Changes + Justification:
- GuiView - provided this interface in order to add functionality specific to the GUI and Composite view. This includes functions to scroll the viewing screen as well as pausing and unpausing.
We made this interface Generic so it is not tied to a specific Note implementation.

- CompositionView - needed to be created in order to union both the midi and gui view. This is done in order to help synchronize the events in both views easier, while allowing us to delegate
actions to both views through one central class.

- GuiViewFrame - made a couple methods within private nested classes for specific JPanels protected in order to allow for modifications from the outer class GUIViewFrame to the panels individually.
Added a number of fields for scrolling and pausing / unpausing, all of which are private.

- GuiViewBuilder - we created this builder to allow the user to switch between different GUI specific views such as the Composite view and the GUI view in particular.

- NoteBuilderFrame - we created this GUI so that creating notes is intuitive. In order to send the information from the GUI to the controller, we created a Runnable AddNote within the controller which
is responsible for the creation of this GUI as well as the information being sent back to the controller. This Runnable invokes a method in the GUI, buildFrame, which provides the GUI with the controller
and allows it to subsequently call a method within the controller to add the note to the model. This allows control to flow from the controller, to the view, and then back to the controller, which loosens
coupling overall, and allows each component to be as separate as possible, while allowing functionality from one component to be communicated by others.

- ViewBuilder - we made this builder specific to the console view and the midi view since they contain different functions than the GUI view and require a separate controller to operate.
This is because of the advent of the GuiView interface, which extends ICompositionView and adds extra functionality which requires classes to contain objects of type GuiView in order to have
access to the functionality in GuiView.


None of the functions in our view require a direct instance of a controller or model to operate. As a result, we felt that our implementation of the view was sufficient since it is not dependent
on specific functions in the controller and model. The functions in this interface primarily exist so that the controller can "inject" modifications in our view through Runnables, without requiring the view
to know of the controller's existence.

Main
- allows the user to run the program via console. given the text file, and then the type of view as parameters


