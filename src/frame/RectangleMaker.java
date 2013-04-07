package frame;

import renderableObject.RenderableObject;

import frameListener.RectangleMakerListener;

public class RectangleMaker extends ShapeMaker {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6143144260925025341L;

	public RectangleMaker(MainFrame parent, RenderableObject ro) {
		this(parent);
		shapeDrawer.setCurrentShape(ro);
	}

	public RectangleMaker(MainFrame parent) {
		super(parent,new RectangleMakerListener());
		frameListener.setFrame(this);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		setTitle("Rectangle Maker");

	}
}
