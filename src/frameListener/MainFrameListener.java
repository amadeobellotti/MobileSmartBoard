package frameListener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import renderableObject.RenderableShape;

import frame.MainFrame;
import frame.ShapeMaker;

public class MainFrameListener extends FrameListener {

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
		if(e.getActionCommand().contains("Shape")){
			((MainFrame) frame).getRightClickMenu().setVisible(false);
			ShapeMaker shapeMaker = new ShapeMaker((MainFrame) frame);
			
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		switch(e.getButton()){
		case MouseEvent.BUTTON1:
			System.out.println("Left click");
			break;
		case MouseEvent.BUTTON2:
			System.out.println("Middle Click");
			break;
		case MouseEvent.BUTTON3:
		//	System.out.println("Right click");
			//((MainFrame) frame).getRightClickMenu().setLocation(e.getX()+frame.getX(), e.getY()+frame.getY());
			//((MainFrame) frame).getRightClickMenu().setVisible(true);
		//	((MainFrame) frame).setClickLocation(new Point(e.getX()+frame.getX(), e.getY()+frame.getY()));

			break;
		}
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
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			showMenu(e);
		}
	}
	
	private void showMenu(MouseEvent e) {
		((MainFrame) frame).getRightClickMenu().show(e.getComponent(), e.getX(), e.getY());
		((MainFrame) frame).setClickLocation(new Point(e.getX()+frame.getX(), e.getY()+frame.getY()));

	}

}
