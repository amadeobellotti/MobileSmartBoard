package frame;

import java.awt.Dimension;
import java.awt.Point;

import environment.DefaultEnvironment;
import environment.Environment;
import frameListener.FrameListener;
import frameListener.MainFrameListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import renderableObject.RenderableObject;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainFrame extends DefaultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2501040976700814999L;
	private Environment world;

	private JMenuBar menuBar;
	private JPopupMenu unselectedRightClickMenu;

	private Point frameLocation;
	private JPopupMenu selectedRightClickMenu;

	public MainFrame() {
		super(new MainFrameListener(), new Dimension(1024, 768));
		setSize(frameSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frameLocation = new Point(0, 0);
		world = new DefaultEnvironment();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(world,
				GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(world,
				GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE));

		getContentPane().setLayout(groupLayout);
		world.addMouseListener(frameListener);
		setupFrame();
		setupMenu();
		setupPopup();

		setVisible(true);
	}

	private void setupPopup() {
		// Menu if something isnt selected
		unselectedRightClickMenu = new JPopupMenu();
		unselectedRightClickMenu.addMouseListener(frameListener);

		JMenu mnAdd = new JMenu("Add");
		unselectedRightClickMenu.add(mnAdd);

		JMenu mnEdit = new JMenu("Edit");
		unselectedRightClickMenu.add(mnEdit);

		JMenuItem mntmPaste1 = new JMenuItem("Paste");
		mnEdit.add(mntmPaste1);
		mntmPaste1.addActionListener(frameListener);

		JMenuItem mntmRectangle = new JMenuItem("Rectangle");
		mnAdd.add(mntmRectangle);
		mntmRectangle.addActionListener(frameListener);

		JMenuItem mntmElipse = new JMenuItem("Ellipse");
		mnAdd.add(mntmElipse);
		mntmElipse.addActionListener(frameListener);

		JMenuItem mntmText = new JMenuItem("Text");
		mnAdd.add(mntmText);
		mntmText.addActionListener(frameListener);

		JMenuItem mntmLatex = new JMenuItem("Latex");
		mnAdd.add(mntmLatex);
		mntmLatex.addActionListener(frameListener);

		JMenuItem mntmImage = new JMenuItem("Image");
		mnAdd.add(mntmImage);
		mntmImage.addActionListener(frameListener);

		JMenuItem mntmShape = new JMenuItem("Custom...");
		mntmShape.setActionCommand("Custom");
		mnAdd.add(mntmShape);
		mntmShape.addActionListener(frameListener);

		// menu if something is selected

		selectedRightClickMenu = new JPopupMenu();
		selectedRightClickMenu.addMouseListener(frameListener);

		JMenuItem mntmModify = new JMenuItem("Modify");
		selectedRightClickMenu.add(mntmModify);
		mntmModify.addActionListener(frameListener);

		JMenuItem mntmCopy = new JMenuItem("Copy");
		selectedRightClickMenu.add(mntmCopy);
		mntmCopy.addActionListener(frameListener);

		JMenuItem mntmCut = new JMenuItem("Cut");
		selectedRightClickMenu.add(mntmCut);
		mntmCut.addActionListener(frameListener);

		JMenuItem mntmPaste = new JMenuItem("Paste");
		selectedRightClickMenu.add(mntmPaste);
		mntmPaste.addActionListener(frameListener);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		selectedRightClickMenu.add(mntmDelete);
		mntmDelete.addActionListener(frameListener);

	}

	private void setupFrame() {
		// TODO Auto-generated method stub

	}

	public void setupMenu() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnAdd_1 = new JMenu("Add");
		mnFile.add(mnAdd_1);

		JMenuItem mntmElipse = new JMenuItem("Ellipse");
		mnAdd_1.add(mntmElipse);
		mntmElipse.addActionListener(frameListener);

		JMenuItem mntmRectangle = new JMenuItem("Rectangle");
		mnAdd_1.add(mntmRectangle);
		mntmRectangle.addActionListener(frameListener);

		JMenuItem mntmText = new JMenuItem("Text");
		mnAdd_1.add(mntmText);
		mntmText.addActionListener(frameListener);

		JMenuItem mntmLatex = new JMenuItem("Latex");
		mnAdd_1.add(mntmLatex);
		mntmLatex.addActionListener(frameListener);

		JMenuItem mntmImage = new JMenuItem("Image");
		mnAdd_1.add(mntmImage);
		mntmImage.addActionListener(frameListener);

		JMenuItem mntmCustom = new JMenuItem("Custom...");
		mnAdd_1.add(mntmCustom);
		mntmCustom.setActionCommand("Custom");
		mntmCustom.addActionListener(frameListener);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.addActionListener(frameListener);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);
		mntmSaveAs.setActionCommand("Save As");
		mntmSaveAs.addActionListener(frameListener);

		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		mntmOpen.setActionCommand("Open");
		mntmOpen.addActionListener(frameListener);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnEdit.add(mntmSettings);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmList = new JMenuItem("List");
		mnView.add(mntmList);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}

	public Environment getWorld() {
		return world;
	}

	public void setWorld(Environment w) {
		world.clean();
		for (RenderableObject o : w.getObjects()) {
			world.add(o);
		}

		this.world.repaint();
	}

	public JPopupMenu getRightClickMenu(boolean shapeSelected) {
		if (!shapeSelected)
			return unselectedRightClickMenu;
		else
			return selectedRightClickMenu;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void setClickLocation(Point loc) {
		frameLocation = loc;
	}

	public Point getClickLocation() {
		return frameLocation;
	}

	public ArrayList<RenderableObject> getObjects() {
		return world.getObjects();
	}

	protected JPopupMenu getSelectedpopupMenu() {
		return selectedRightClickMenu;
	}
}
