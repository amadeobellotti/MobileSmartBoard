package frameListener;

import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import autoComplete.AutoComplete;

import renderableObject.RenderableLatex;
import frame.LatexMaker;

public class LatexMakerListener extends FrameListener implements FocusListener,
		DocumentListener {
	private final static int UNDO_REDO_STACK_LIMIT = 100;
	private final static char[] PAIRS = new char[] { '{', '}', '(', ')', '[', ']' };
	private int stackPointer;
	private int cmdStartPos;
	private int startHighlight;
	private int endHighlight;
	private boolean allowInsert;
	private boolean undoRedoFlag;
	private boolean wasLastInsertNext;
	private ArrayList<String> txtStack;
	private AutoComplete autoComp;
	private String lastValidLatexString = "";

	/*
	 * 
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ WINDOW EVENTS
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	public LatexMakerListener(){
		setNewTxtAreaVars();

	}
	
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	// run at launch, initialize vars, render arbitrary latex string so no
	// hesitation on first user input
	public void setNewTxtAreaVars() {
		(txtStack = new ArrayList<String>()).add("");
		stackPointer = 0;
		allowInsert = true;
		undoRedoFlag = false;
		cmdStartPos = -1;
		autoComp = new AutoComplete();
		wasLastInsertNext = false;
		new RenderableLatex("x");
	}

	// saves most-recent valid latex and closes window
	private void finish() {
		frame.setVisible(false);
		if (lastValidLatexString.length() > 0) {
			RenderableLatex lm = new RenderableLatex(lastValidLatexString);
			lm.setLocation(((LatexMaker) frame).getParentFrame()
					.getClickLocation());
			((LatexMaker) frame).getParentFrame().getWorld().add(lm);
		}
	}

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ ACTION EVENTS
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			finish();
		} else if (e.getActionCommand().equals("Undo")) {
			undoredo(e.getActionCommand());
			setFocusOnTxtArea();
		} else if (e.getActionCommand().equals("Redo")) {
			undoredo(e.getActionCommand());
			setFocusOnTxtArea();
		} else if (e.getActionCommand().equals("Next")) {
			// getNext();
			wasLastInsertNext = true;
			moveInsert(1);
			setFocusOnTxtArea();
		} else if (e.getActionCommand().equals("Prev")) {
			// getPrev();
			wasLastInsertNext = false;
			moveInsert(0);
			setFocusOnTxtArea();
		}
	}

	// (undoRedoFlag==true) forces document listener to ignore textArea changes
	private void setUndoRedoFlag(boolean trufa) {
		undoRedoFlag = trufa;
	}

	// replace existing textArea text with a different version from stack
	private void undoredo(String whatDo) {
		setUndoRedoFlag(true);
		if ((whatDo == "Undo") && (stackPointer > 0)) {
			stackPointer--;
			updateTxtArea();
		} else if ((whatDo == "Redo") && (stackPointer < txtStack.size() - 1)) {
			stackPointer++;
			updateTxtArea();
		}
		setUndoRedoFlag(false);
	}

	// NO TOUCH
	private void moveInsert(int prev_next) {
		int endOne = getCaretLocation();
		String texStr = getAllText();
		int texStrLen = texStr.length();
		int numSign = (prev_next - (1 - prev_next));// prev=-1, next=1
		int endTwo = endOne + numSign;

		if ((prev_next == 0 && endOne == 0)
				|| (prev_next == 1 && endOne == texStrLen)) {
			((LatexMaker) frame).removeHighlights();
			setCaretFrozen(false);
		} else if ((prev_next == 0 && endOne > 0)
				|| (prev_next == 1 && endOne < texStrLen - 1)) {
			setCaretFrozen(true);
			int duplicateCounter = 1;

			int totDiff = texStrLen - endOne;
			endOne = endOne + numSign;

			masterLoop: while ((endOne >= 0) && (endOne < texStrLen)) {
				for (int j = 0; j < PAIRS.length; j += 2) {
					boolean innerFlag = false;
					if ((endOne < texStrLen - 1) && (endOne > 0)) {

						if (texStr.charAt(endOne + numSign) == PAIRS[j
								+ prev_next]) {
							innerFlag = true;
						}
					}
					if (texStr.charAt(endOne) == PAIRS[j + prev_next]
							|| innerFlag) {
						for (int k = 1; k < totDiff - 1; k++) {
							if ((endOne + k * (-numSign) >= 0)
									&& (texStr.charAt(endOne + k * (-numSign)) == PAIRS[j
											+ 1 - prev_next])) {
								duplicateCounter--;
								if (duplicateCounter == 0) {
									endTwo = endOne + k * (-numSign) - numSign
											+ prev_next;
									if (innerFlag) {
										endOne += prev_next;
										endTwo -= (-numSign);
									}
									break masterLoop;
								}
							} else if ((endOne + k * (-numSign) >= 0)
									&& (texStr.charAt(endOne + k * (-numSign)) == PAIRS[j
											+ prev_next])) {
								duplicateCounter++;
							} else if ((endOne + k * (-numSign) >= 0)
									&& ((endOne + k * (-numSign)) == (prev_next * (texStrLen - 1)))) {
								endTwo = prev_next * texStrLen;
								break masterLoop;
							}
						}// for(k)
						innerFlag = false;
					} else if (endOne == prev_next * texStrLen) {
						for (int k = 0; k < totDiff; k++) {
							endTwo = endOne + k * (-numSign);
							if ((!Character.isLetter(texStr.charAt(endOne + k
									* (-numSign))))) {
								break;
							}
						}
						break masterLoop;
					} else if (texStr.charAt(endOne) == '\\') {
						// search library for match
					} else {
						// do whatever
					}

				}
				endOne = endOne + numSign;
				totDiff = totDiff + 1;
			}
			setHighlighter((endOne * (1 - prev_next) + endTwo * prev_next),
					(endOne * prev_next + endTwo * (1 - prev_next)));
		} else if ((endOne == texStr.length() - 1) && (prev_next == 1)) {
			setCaretLocation(texStr.length());
			setHighlighter(texStr.length(), texStr.length());
		} else if ((endOne == texStr.length()) && (prev_next == 1)) {
			setCaretLocation(texStr.length());
			setHighlighter((endOne * (1 - prev_next) + endTwo * prev_next),
					(endOne * prev_next + endTwo * (1 - prev_next)));
		}

	}

	// highlights text in between points given by Prev or Next command
	private void setHighlighter(int startPosition, int endPosition) {
		if (startPosition >= 0 && endPosition >= 0) {
			startHighlight = startPosition;
			endHighlight = endPosition;
			if (wasLastInsertNext) {
				setCaretLocation(endPosition);
			} else {
				setCaretLocation(startPosition);
			}
			((LatexMaker) frame).removeHighlights();
			((LatexMaker) frame).setHighlighter(startPosition, endPosition);
		}
	}

	/*
	 * 
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ MOUSE EVENTS
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	/*
	 * 
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ FOCUS EVENTS
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	@Override
	public void focusLost(java.awt.event.FocusEvent e) {
	}

	@Override
	public void focusGained(java.awt.event.FocusEvent e) {
		insertAutocompleteText(((JTextField) (e.getComponent())).getText());
		setFocusOnTxtArea();
	}

	// inserts the user-clicked autocomplete suggestion into the text area at
	// caret location
	private void insertAutocompleteText(String newText) {
		String txtArea = getAllText();

		newText = txtArea.substring(0, cmdStartPos) + newText;
		newText += txtArea.substring(getCaretLocation(), txtArea.length());

		updateTxtArea(newText);
		((LatexMaker) frame).removeHighlights();
		setCaretFrozen(false);
		setAutoCompleteInsetLocation();

	}

	private void setAutoCompleteInsetLocation() {
		String txtArea = getAllText();
		int caretPos = getCaretLocation() - 1;
		int startPos = caretPos - 1;
		int endPos = startPos;
		int pair = 0;

		while (txtArea.charAt(caretPos) != '\\') {
			for (int i = 0; i < PAIRS.length; i += 2) {
				if (txtArea.charAt(caretPos) == PAIRS[i]) {
					startPos = caretPos + 1;
					pair = i + 1;
				}
			}
			caretPos -= 1;
		}
		if (endPos > startPos) {
			endPos = txtArea.indexOf(PAIRS[pair], startPos);
			setHighlighter(startPos, endPos);
		}
	}

	/*
	 * 
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ DOCUMENT EVENTS
	 * \\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		// cmdStartPos = -1;
		onDocEvent();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// cmdStartPos = -1;
		onDocEvent();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (allowInsert && (((LatexMaker) frame).checkIfHighlight())) {
			allowInsert = false;
			insertText();
		} else {
			onDocEvent();
			allowInsert = true;
		}
	}

	// on textArea changes: update Latex image, undo/redo stack, and
	// autocomplete suggestions
	public void onDocEvent() {
		updateAutoCompleteOptions();
		if (!undoRedoFlag) {
			updateStack();
		}
		paintLatexLabel();
	}

	private String getInsertedChar() {
		if (wasLastInsertNext) {
			return getAllText().substring(endHighlight, endHighlight + 1);
		}
		return getAllText().substring(startHighlight, startHighlight + 1);
	}

	// insert new text at highlighted location, use new runnable to keep GUI
	// happy
	private void insertText() {
		Runnable InsertTextRunnable = new Runnable() {
			@Override
			public void run() {
				String oldStr = txtStack.get(stackPointer);
				String insertedChar = getInsertedChar();
				String newStr = oldStr.substring(0, startHighlight)
						+ insertedChar
						+ oldStr.substring(endHighlight, oldStr.length());
				((LatexMaker) frame).getTextArea().setText(newStr);
				((LatexMaker) frame).removeHighlights();
				setCaretLocation(startHighlight + 1);
			}
		};
		SwingUtilities.invokeLater(InsertTextRunnable);

	}

	// fill auto complete suggestion boxes
	private void updateAutoCompleteOptions() {
		String txtArea = getAllText();
		if ((getCaretLocation() - 1 >= 0) && (cmdStartPos < getCaretLocation())
				&& (getCaretLocation() <= txtArea.length())) {
			if (getAllText().charAt(getCaretLocation() - 1) == '\\') {
				cmdStartPos = getCaretLocation() - 1;
			}
			if (cmdStartPos >= 0) {// && ((getCaretLocation()-cmdStartPos)>1)
				String txtOpts[] = autoComp.getBestGuess(txtArea.substring(
						cmdStartPos, getCaretLocation() + 1));
				((LatexMaker) frame).setAutoCompleteOptions(txtOpts);
				if (txtOpts[0] == "") {
					cmdStartPos = -1;
				}
			}
		}
	}

	// try to render new latex with textArea text--if render fails, then leave
	// previous image displayed
	private void paintLatexLabel() {
		String currentLatexString = getAllText();
		if (currentLatexString.length() > 0) {
			try {
				BufferedImage latexImg = (new RenderableLatex(
						currentLatexString)).getLatexImg();
				ImageIcon latexIcon = new ImageIcon();
				latexIcon.setImage(latexImg);
				((LatexMaker) frame).setLatexLabel(latexIcon);
				lastValidLatexString = currentLatexString;
			} catch (Exception e) {
				// ///////////////////////
				// NOTE TO SELF: Make an "invalid syntax" notification for user
				// ///////////////////////
			}
		} else {
			setCaretFrozen(false);
			((LatexMaker) frame).clearLatexLabel();
		}
	}

	// add current textArea to undo/redo stack--push out bottom of stack if
	// limit is exceeded
	private void updateStack() {
		trimStack();
		if ((txtStack.size() >= UNDO_REDO_STACK_LIMIT) && (stackPointer > 0)) {
			txtStack.remove(0);
			stackPointer--;
		}
		txtStack.add(getAllText());
		stackPointer++;
	};

	// trim top of stack down to pointer--needed if user makes text changes
	// after an undo event
	private void trimStack() {
		if (stackPointer != (txtStack.size() - 1)) {
			for (int i = txtStack.size() - 1; i > stackPointer; i--) {
				txtStack.remove(i);
			}
		}
	}

	//
	// random setters and getters
	//
	private void updateTxtArea() {
		((LatexMaker) frame).getTextArea().setText(txtStack.get(stackPointer));
	};

	private void updateTxtArea(String newText) {
		((LatexMaker) frame).getTextArea().setText(newText);
	};

	private String getAllText() {
		return ((LatexMaker) frame).getTextArea().getText();
	}

	private void setFocusOnTxtArea() {
		((LatexMaker) frame).getTextArea().requestFocus();
	}

	private int getCaretLocation() {
		return ((LatexMaker) frame).getTextArea().getCaretPosition();
	}

	private void setCaretLocation(int caretPosition) {
		((LatexMaker) frame).getTextArea().setCaretPosition(caretPosition);
	}

	public int getStartHighlight() {
		return startHighlight;
	}

	public int getEndHighlight() {
		return endHighlight;
	}

	private void setCaretFrozen(boolean setIt) {
		int newBlinkRate;
		if (setIt == true) {
			newBlinkRate = 0;
		} else {
			newBlinkRate = 500;
		}
		((LatexMaker) frame).getTextArea().getCaret()
				.setBlinkRate(newBlinkRate);
	}
}
