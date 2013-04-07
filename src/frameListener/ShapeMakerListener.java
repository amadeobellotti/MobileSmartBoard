package frameListener;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import renderableObject.Line;
import renderableObject.RenderableObject;

import frame.ShapeDrawer;
import frame.ShapeMaker;

public class ShapeMakerListener extends FrameListener {

	protected Point start;
	protected Point end;

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
			((ShapeMaker) frame).setVisible(false);
			((ShapeMaker) frame).setFinished(true);

			RenderableObject shape = ((ShapeMaker) frame).getShapeDrawer()
					.getCurrentShape();
			if(((ShapeMaker) frame).getParentFrame().getModfiedLoc() == null){
				shape.setLocation(((ShapeMaker) frame).getParentFrame()
					.getClickLocation());
			}else {
				shape.setLocation(((ShapeMaker) frame).getParentFrame().getModfiedLoc());
				((ShapeMaker) frame).getParentFrame().setModfiedLoc(null);
			}

			((ShapeMaker) frame).getParentFrame().getWorld().add(shape);
		} else if (e.getActionCommand().equals("Set Color")) {
			System.out.println("Set Color");
			Color newColor = JColorChooser.showDialog(frame, "Select Color",
					((ShapeMaker) frame).getColor());
			((ShapeMaker) frame).setColor(newColor);
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
