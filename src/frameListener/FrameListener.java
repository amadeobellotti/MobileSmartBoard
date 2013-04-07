package frameListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import frame.DefaultFrame;

public abstract class FrameListener implements WindowListener, ActionListener, MouseListener {

	protected DefaultFrame frame;

	public void setFrame(DefaultFrame f) {
		frame = f;
	}


	public void refresh() {

	}


	public DefaultFrame getFrame() {
		return frame;
	}



}