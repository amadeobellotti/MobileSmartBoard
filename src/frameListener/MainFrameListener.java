package frameListener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import renderableObject.RenderableObject;
import renderableObject.RenderableShape;

import frame.EllipseMaker;
import frame.MainFrame;
import frame.RectangleMaker;
import frame.ShapeMaker;
import frame.TextMaker;

public class MainFrameListener extends FrameListener {

	private RenderableObject selectedObject = null;
	private boolean moving = false;
	private boolean leftClick = false;

	private Point start,end;

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
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().contains("Custom")) {
			((MainFrame) frame).getRightClickMenu().setVisible(false);
			ShapeMaker shapeMaker = new ShapeMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Ellipse")) {
			((MainFrame) frame).getRightClickMenu().setVisible(false);
			EllipseMaker elipseMaker = new EllipseMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Rectangle")) {
			((MainFrame) frame).getRightClickMenu().setVisible(false);
			RectangleMaker rectangleMaker = new RectangleMaker(
					(MainFrame) frame);
		} else if (e.getActionCommand().contains("Text")) {
			((MainFrame) frame).getRightClickMenu().setVisible(false);
			TextMaker textMaker = new TextMaker((MainFrame) frame);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftClick = true;
			selectItem(e);
			System.out.println("Left click");
			break;
		case MouseEvent.BUTTON2:
			leftClick = false;
			System.out.println("Middle Click");
			break;
		case MouseEvent.BUTTON3:
			leftClick = false;

			// System.out.println("Right click");
			// ((MainFrame)
			// frame).getRightClickMenu().setLocation(e.getX()+frame.getX(),
			// e.getY()+frame.getY());
			// ((MainFrame) frame).getRightClickMenu().setVisible(true);
			// ((MainFrame) frame).setClickLocation(new
			// Point(e.getX()+frame.getX(), e.getY()+frame.getY()));

			break;
		}
	}

	private void selectItem(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			int xMax = o.getLocation().x + o.getDimension().width;
			int yMax = o.getLocation().y + o.getDimension().height;

			if ((x > o.getLocation().x && y > o.getLocation().y)
					&& (x < xMax && y < yMax)) {
				selectedObject = o;
				o.setSelected(true);
				return;
			}else{
				o.setSelected(false);
			}
		}
		selectedObject = null;
		frame.repaint();
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
		if (e.isPopupTrigger()) {
			showMenu(e);
		}else{
			selectItem(e);
			if(selectedObject != null){
				moving = true;
			}
			leftClick = false;
			start = new Point(e.getX(),e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			showMenu(e);
		}else if(moving && !leftClick){
			end = new Point(e.getX(),e.getY());
			int xMove = start.x - end.x;
			int yMove = start.y - end.y;
			Point newLoc = selectedObject.getLocation();
			newLoc.x -= xMove;
			newLoc.y -= yMove;
			selectedObject.move(newLoc);
			moving = false;
			frame.repaint();
		}
	}

	private void showMenu(MouseEvent e) {
		((MainFrame) frame).getRightClickMenu().show(e.getComponent(),
				e.getX(), e.getY());
		((MainFrame) frame).setClickLocation(new Point(e.getX() + frame.getX(),
				e.getY() + frame.getY()));

	}

}
