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
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

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
			text.setLocation(((TextMaker) frame).getParentFrame()
					.getClickLocation());
			((TextMaker) frame).getParentFrame().getWorld().add(text);
		} else if (e.getActionCommand().equals("Set Color")) {
			System.out.println("Set Color");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
