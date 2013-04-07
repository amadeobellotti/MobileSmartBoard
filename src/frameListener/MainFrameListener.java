package frameListener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;

import renderableObject.RenderableImage;
import renderableObject.RenderableObject;
import renderableObject.Text;

import environment.Environment;
import frame.EllipseMaker;
import frame.LatexMaker;
import frame.MainFrame;
import frame.RectangleMaker;
import frame.ShapeMaker;
import frame.TextMaker;

public class MainFrameListener extends FrameListener {

	private RenderableObject selectedObject = null;
	private RenderableObject clipboard = null;
	private boolean moving = false;
	private boolean leftClick = false;
	private boolean shapeSelected = false;

	private File file;
	private String loc = null;

	private Point start, end;
	private Point modifiedObjectLoc;

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		((MainFrame) frame).getRightClickMenu(shapeSelected).setVisible(false);
		if (e.getActionCommand().contains("Custom")) {
			new ShapeMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Ellipse")) {
			new EllipseMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Rectangle")) {
			new RectangleMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Text")) {
			new TextMaker((MainFrame) frame);
		} else if (e.getActionCommand().contains("Modify")) {
			modify();
		} else if (e.getActionCommand().contains("Copy")) {
			copy();
		} else if (e.getActionCommand().contains("Cut")) {
			cut();
		} else if (e.getActionCommand().contains("Paste")) {
			paste();
		} else if (e.getActionCommand().contains("Delete")) {
			delete();
		} else if (e.getActionCommand().contains("Save")) {
			save();
		} else if (e.getActionCommand().contains("Save As")) {
			saveAs();
		} else if (e.getActionCommand().contains("Open")) {
			open();
		} else if (e.getActionCommand().contains("Image")) {
			image();
		} else if (e.getActionCommand().contains("Latex")) {
			latex();
		} else if (e.getActionCommand().contains("Identify")) {
			identifyItems();
		} else if (e.getActionCommand().contains("Cancel")) {
			cancel();
		} else if (e.getActionCommand().contains("select")) {
			select(e.getActionCommand().substring(6));
		} else if (e.getActionCommand().contains("Quit")) {
			System.out.println("Goodbye");
			System.exit(0);
		} else if (e.getActionCommand().contains("New")) {
			newWorld();
		}

		frame.repaint();
	}

	private void select(String s) {
		int index = Integer.parseInt(s);
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			if (o.getID() == index) {
				selectItem(index);
			}
		}
		clearIdentities();
	}

	private void cancel() {
		clearIdentities();
	}

	private void identifyItems() {
		int id = 0;
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			o.setID(id);
			o.setIdentified(true);
			id++;
		}
	}

	private void clearIdentities() {
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			o.setIdentified(false);
		}
	}

	private void newWorld() {
		((MainFrame) frame).newWorld();
	}

	private void latex() {
		if (selectedObject != null) {
			new LatexMaker((MainFrame) frame, selectedObject);
		} else {
			new LatexMaker((MainFrame) frame);
		}
	}

	private void image() {
		JFileChooser chooser;

		if (loc == null) {
			chooser = new JFileChooser(System.getProperty("user.home"));

		} else {
			chooser = new JFileChooser(loc);
		}

		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			loc = file.getAbsolutePath();
			RenderableImage img = new RenderableImage(file.getAbsolutePath());
			img.setLocation(((MainFrame) frame).getClickLocation());
			((MainFrame) frame).getWorld().add(img);

		}
	}

	private void open() {
		JFileChooser chooser;

		if (loc == null) {
			chooser = new JFileChooser(System.getProperty("user.home"));

		} else {
			chooser = new JFileChooser(loc);
		}

		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			loc = file.getAbsolutePath();
			Environment env = ((MainFrame) frame).getWorld().open(
					file.getAbsolutePath());
			((MainFrame) frame).setWorld(env);
		}
	}

	private void saveAs() {
		JFileChooser chooser;

		if (loc == null) {
			chooser = new JFileChooser(System.getProperty("user.home"));

		} else {
			chooser = new JFileChooser(loc);
		}

		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showSaveDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			loc = file.getAbsolutePath();
			((MainFrame) frame).getWorld().saveAs(file.getAbsolutePath());
		}
	}

	private void save() {
		if (((MainFrame) frame).getWorld().getFilename() == null) {
			saveAs();
		} else {
			((MainFrame) frame).getWorld().save();
		}
	}

	private void delete() {
		for (int i = 0; i < ((MainFrame) frame).getObjects().size(); i++) {
			if (((MainFrame) frame).getObjects().get(i) == selectedObject) {
				((MainFrame) frame).getObjects().remove(i);
			}
		}
	}

	private void paste() {
		RenderableObject o = clipboard.makeCopy();

		o.setLocation(((MainFrame) frame).getClickLocation());
		((MainFrame) frame).getWorld().add(o);
	}

	private void cut() {
		copy();
		delete();
	}

	private void copy() {
		clipboard = selectedObject;
	}

	private void modify() {
		delete();
		modifiedObjectLoc = selectedObject.getLocationCopy();
		switch (selectedObject.getObjectType()) {
		case DEFAULT:
			break;
		case SHAPE:
			new ShapeMaker((MainFrame) frame, selectedObject);
			break;
		case RECTANGLE:
			new RectangleMaker((MainFrame) frame, selectedObject);
			break;
		case ELLIPSE:
			new EllipseMaker((MainFrame) frame, selectedObject);
			break;
		case TEXT:
			new TextMaker((MainFrame) frame, (Text) selectedObject);
			break;
		case IMAGE:
			image();
			break;
		case LATEX:
			latex();
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
		shapeSelected = false;
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			int xMax = o.getLocation().x + o.getDimension().width;
			int yMax = o.getLocation().y + o.getDimension().height;

			if ((x > o.getLocation().x && y > o.getLocation().y)
					&& (x < xMax && y < yMax)) {
				selectedObject = o;
				for (RenderableObject ro : ((MainFrame) frame).getObjects()) {
					ro.setSelected(false);
				}
				o.setSelected(true);
				shapeSelected = true;
				frame.repaint();
				return;
			}
		}
		selectedObject = null;
		frame.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		selectItem(e);

		if (e.isPopupTrigger()) {
			showMenu(e);
		} else {
			if (selectedObject != null) {
				moving = true;
			}
			leftClick = false;
			start = new Point(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			showMenu(e);
		} else if (moving && !leftClick && selectedObject != null) {
			end = new Point(e.getX(), e.getY());
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
		((MainFrame) frame).getRightClickMenu(shapeSelected).show(
				e.getComponent(), e.getX(), e.getY());
		((MainFrame) frame).setClickLocation(new Point(e.getX() + frame.getX(),
				e.getY() + frame.getY()));

	}

	public Point getModifiedLoc() {
		return modifiedObjectLoc;
	}

	public void setModifiedLoc(Point p) {
		modifiedObjectLoc = p;
	}

	// /NEW NEW NEW
	public void selectItem(int itemIndex) {
		for (RenderableObject o : ((MainFrame) frame).getObjects()) {
			o.setSelected(false);
		}
		selectedObject = ((MainFrame) frame).getObjects().get(itemIndex);
		selectedObject.setSelected(true);
		shapeSelected = true;
		frame.repaint();
		return;
	}

}
