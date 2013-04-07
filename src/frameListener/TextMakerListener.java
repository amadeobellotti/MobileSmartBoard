package frameListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import renderableObject.RenderableObject;
import frame.JFontChooser;
import frame.ShapeMaker;
import frame.TextMaker;

public class TextMakerListener extends FrameListener {

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Undo")) {
			((ShapeMaker) frame).getShapeDrawer().undo();
		} else if (e.getActionCommand().equals("Redo")) {
			((ShapeMaker) frame).getShapeDrawer().redo();
		} else if (e.getActionCommand().equals("Finish")) {
			System.out.println("Finish");
			((TextMaker) frame).setVisible(false);

			RenderableObject text = ((TextMaker) frame).getText();

			if (((TextMaker) frame).getParentFrame().getModfiedLoc() == null) {
				text.setLocation(((TextMaker) frame).getParentFrame()
						.getClickLocation());
			} else {
				text.setLocation(((TextMaker) frame).getParentFrame()
						.getModfiedLoc());
				((TextMaker) frame).getParentFrame().setModfiedLoc(null);
			}

			((TextMaker) frame).getParentFrame().getWorld().add(text);
		} else if (e.getActionCommand().equals("Set Color")) {
			Color newColor = JColorChooser.showDialog(frame, "Select Color",
					((TextMaker) frame).getColor());
			((TextMaker) frame).setColor(newColor);
		} else if (e.getActionCommand().equals("Set Font")) {
			Font currentFont = ((TextMaker) frame).getCurrentFont();
			currentFont = JFontChooser.showDialog(frame, "Pick a Font",
					currentFont);
			((TextMaker) frame).setCurrentFont(currentFont);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
