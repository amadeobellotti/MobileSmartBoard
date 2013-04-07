package environment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import renderableObject.RenderableObject;
import renderableObject.Text;

public abstract class Environment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8865750200234550261L;
	protected ArrayList<RenderableObject> objects;
	protected Dimension dimension;
	private String filename = null;
	private String commandsAvailble;

	public Environment(Dimension d) {
		objects = new ArrayList<RenderableObject>();
		dimension = d;
		setSize(dimension);
		commandsAvailble = "";

	}

	public void saveAs(String filename) {
		this.filename = filename;
		PrintWriter f = null;
		try {
			f = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		f.println("<Enviorment>\n\r"
				+ "<Header>\n\r"
				+ "<Name>"
				+ filename.substring(filename.lastIndexOf(System
						.getProperty("file.separator"))) + "</Name>\n\r"
				+ "</Header>\n\r" + "<Objects>");

		for (RenderableObject ro : objects) {
			ro.save(f);
		}
		f.println("</Objects>\n\r" + "</Enviorment>");
		f.flush();
		f.close();

	}

	public void save() {
		saveAs(filename);
	}

	public Environment open(String absolutePath) {
		XMLParser xml = new XMLParser();
		try {
			return xml.parseEnviorment(new Scanner(new File(absolutePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFilename() {
		return filename;
	}

	public void add(RenderableObject item) {
		item.setPriority(objects.size());
		objects.add(item);
		sort();
		repaint();

	}

	public void clean() {
		objects = new ArrayList<RenderableObject>();
	}

	public void remove(RenderableObject item) {
		objects.remove(item);
		sort();
	}

	private void sort() {
		int n = objects.size();

		for (int pass = 1; pass < n; pass++) { // count how many times
			// This next loop becomes shorter and shorter
			for (int i = 0; i < n - pass; i++) {
				if (objects.get(i).getPriority() > objects.get(i + 1)
						.getPriority()) {
					// exchange elements
					RenderableObject temp = objects.get(i);
					objects.set(i, objects.get(i + 1));
					objects.set(i + 1, temp);

				}
			}
		}

	}

	public void paint(Graphics g) {
		drawBackground(g);
		for (RenderableObject o : objects) {
			o.draw(g);
		}
		drawCommands(g);

	}

	private void drawCommands(Graphics g) {
		Text t = new Text(commandsAvailble, new Font(null, Font.BOLD, 15),
				Color.BLACK);
		t.draw(g);
	}

	public void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(0, 0, (int) dimension.getWidth(), (int) dimension.getWidth());
		g.fillRect(0, 0, (int) dimension.getWidth(), (int) dimension.getWidth());
	}

	public ArrayList<RenderableObject> getObjects() {
		return objects;
	}

	public String getCommandsAvailble() {
		return commandsAvailble;
	}

	public void setCommandsAvailble(String commandsAvailble) {
		if (commandsAvailble == null)
			commandsAvailble = "";
		this.commandsAvailble = commandsAvailble;
	}
	
	public void newWorld(){
		objects = new ArrayList<RenderableObject>();
		repaint();
	}

}
