package frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import frameListener.DefaultFrameListner;
import frameListener.FrameListener;

public class DefaultFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5099785717479598469L;

	public enum Mode {
		HARMONICS, IMPACT
	}

	protected Mode mode;
	protected FrameListener frameListener;

	protected Dimension frameSize = new Dimension(600, 600);
	protected Point location;

	protected JMenuBar menuBar = new JMenuBar();

	public DefaultFrame(Mode mode, FrameListener fListener, Dimension fSize) {
		this.setMode(mode);
		if (fListener == null) {
			fListener = new DefaultFrameListner();
		}
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

	public void setMode(Mode mode) {
		if (mode == null) {
			mode = Mode.IMPACT;
		}

		this.mode = mode;
		switch (mode) {
		case IMPACT:
			setIconImage(Toolkit.getDefaultToolkit().getImage(
					"./icon/impact.png"));
			break;
		case HARMONICS:
			setIconImage(Toolkit.getDefaultToolkit().getImage(
					"./icon/harmonics.png"));
			break;

		}

	}

	public Mode getMode() {
		return mode;
	}

	protected String string2html(String input) {
		String output = "<html>" + input.replace("\n", "<br>") + "</html>";
		return output;
	}

	public FrameListener getFrameListener() {
		return frameListener;
	}

}