package frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import frameListener.DefaultFrameListener;
import frameListener.FrameListener;

public class DefaultFrame extends JFrame {
	protected FrameListener frameListener;

	protected Dimension frameSize;
	protected Point location;

	protected JMenuBar menuBar = new JMenuBar();

	public DefaultFrame(FrameListener fListener, Dimension fSize) {
		/*if (fListener == null) {
			fListener = new DefaultFrameListener();
		}*/
		/*if(fSize == null){
			fSize = new Dimension(1024,768);
		}*/
		this.frameListener = fListener;
		fListener.setFrame(this);
		this.frameSize = fSize;

		addWindowListener(frameListener);
		setSize(frameSize);
		setTitle();
	}

	private void setTitle() {
		super.setTitle("Mobile Smart Board");

	}

	protected void setLocation() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();

		int x, y;

		x = (dim.width - frameSize.width) / 2;
		y = (dim.height - frameSize.height) / 2;

		Point location = new Point(x, y);
		super.setLocation(location);

	}

	
	protected String string2html(String input) {
		String output = "<html>" + input.replace("\n", "<br>") + "</html>";
		return output;
	}

	public FrameListener getFrameListener() {
		return frameListener;
	}

}