package frameListener;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import renderableObject.Line;
import renderableObject.Shape;

import frame.MainFrame;
import frame.ShapeDrawer;
import frame.ShapeMaker;

public class ShapeMakerListener extends FrameListener {

	private Point start, end;

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
			((ShapeMaker) frame).setVisible(false);
			((ShapeMaker) frame).setFinished(true);

			Shape shape = ((ShapeMaker) frame).getShapeDrawer()
					.getCurrentShape();
			
			shape.setLocation(((ShapeMaker) frame).getParentFrame()
					.getClickLocation());
			((ShapeMaker) frame).getParentFrame().getWorld().add(shape);
		}else if (e.getActionCommand().equals("Set Color")) {
			System.out.println("Set Color");
			Color newColor = JColorChooser.showDialog(
                    frame,"test",
                    ((ShapeMaker) frame).getColor());
			((ShapeMaker) frame).setColor(newColor);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
		start = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		end = e.getPoint();
		int thickness = ((ShapeMaker) frame).getThickness();
		Color c = ((ShapeMaker) frame).getColor();
		ShapeDrawer sdrawer = ((ShapeMaker) frame).getShapeDrawer();
		sdrawer.addLine(new Line(start.x, start.y, end.x, end.y, thickness, c));
	}

}
