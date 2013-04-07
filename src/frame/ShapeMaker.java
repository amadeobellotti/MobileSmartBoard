package frame;

import java.awt.Color;
import java.awt.Dimension;

import frameListener.FrameListener;
import frameListener.ShapeMakerListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import panels.ColorPanel;
import renderableObject.RenderableObject;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class ShapeMaker extends DefaultFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 683849122870826131L;
	private ColorPanel colorPanel;
	protected ShapeDrawer shapeDrawer;
	private JSpinner spinner;
	private boolean finished = false;
	MainFrame parent;
	protected JButton btnRedo, btnUndo;

	public ShapeMaker(MainFrame parent, FrameListener fl, RenderableObject ro) {
		this(parent, fl);
		shapeDrawer.setCurrentShape(ro);
	}

	public ShapeMaker(MainFrame parent, FrameListener fl) {
		super(fl, new Dimension(800, 600));
		this.parent = parent;

		setupFrame();
	}

	public ShapeMaker(MainFrame parent, RenderableObject ro) {
		this(parent);
		shapeDrawer.setCurrentShape(ro);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ShapeMaker(MainFrame parent) {
		super(new ShapeMakerListener(), new Dimension(800, 600));
		this.parent = parent;

		setupFrame();
	}

	private void setupFrame() {

		setTitle("Shape Maker");

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(frameListener);

		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(frameListener);

		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(frameListener);

		JButton btnNewButton = new JButton("Set Color");
		btnNewButton.addActionListener(frameListener);

		colorPanel = new ColorPanel();

		shapeDrawer = new ShapeDrawer();
		shapeDrawer.addMouseListener(frameListener);

		spinner = new JSpinner();
		spinner.setValue(2);

		JLabel lblThickness = new JLabel("Thickness");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(shapeDrawer,
												GroupLayout.PREFERRED_SIZE,
												437, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(9)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												colorPanel,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												94,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnRedo,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												94,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnUndo,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												94,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnNewButton,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												94,
																												Short.MAX_VALUE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblThickness)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												spinner,
																												GroupLayout.PREFERRED_SIZE,
																												44,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnFinish,
																				GroupLayout.DEFAULT_SIZE,
																				93,
																				Short.MAX_VALUE)))
										.addGap(239)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				colorPanel,
																				GroupLayout.PREFERRED_SIZE,
																				82,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								spinner,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblThickness))
																		.addGap(9)
																		.addComponent(
																				btnUndo)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnRedo)
																		.addGap(127)
																		.addComponent(
																				btnFinish))
														.addComponent(
																shapeDrawer,
																GroupLayout.DEFAULT_SIZE,
																540,
																Short.MAX_VALUE))
										.addContainerGap()));
		getContentPane().setLayout(groupLayout);

		setVisible(true);
	}

	public ColorPanel getColorPanel() {
		return colorPanel;
	}

	public void setColorPanel(ColorPanel colorPanel) {
		this.colorPanel = colorPanel;
	}

	public ShapeDrawer getShapeDrawer() {
		return shapeDrawer;
	}

	public void setShapeDrawer(ShapeDrawer shapeDrawer) {
		this.shapeDrawer = shapeDrawer;
	}

	public Color getColor() {
		return colorPanel.getColor();
	}

	public void setColor(Color c) {
		colorPanel.setColor(c);
	}

	public int getThickness() {
		return (Integer) spinner.getValue();
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean fin) {
		finished = fin;
	}

	public RenderableObject getCurrentShape() {
		return shapeDrawer.getCurrentShape();
	}

	public MainFrame getParentFrame() {
		return parent;
	}
}
