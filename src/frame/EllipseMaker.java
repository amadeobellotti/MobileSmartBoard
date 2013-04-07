package frame;

import frameListener.EllipseMakerListener;
import renderableObject.RenderableObject;

public class EllipseMaker extends ShapeMaker {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8493436658850288596L;

	public EllipseMaker(MainFrame parent, RenderableObject ro) {
		this(parent);
		shapeDrawer.setCurrentShape(ro);
	}

	public EllipseMaker(MainFrame parent) {
		super(parent, new EllipseMakerListener());
		frameListener.setFrame(this);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		setTitle("Ellipse Maker");

	}
}
