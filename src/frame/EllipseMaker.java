package frame;

import java.awt.Dimension;

import javax.swing.JFrame;

import frameListener.EllipseMakerListener;
import frameListener.ShapeMakerListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import renderableObject.RenderableObject;

public class EllipseMaker extends ShapeMaker {
	public EllipseMaker(MainFrame parent, RenderableObject ro) {
		this(parent);
		shapeDrawer.setCurrentShape(ro);
	}

	public EllipseMaker(MainFrame parent) {
		super(parent, new EllipseMakerListener());
		frameListener.setFrame(this);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);

	}
}
