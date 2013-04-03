package renderableObject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class RenderableImage extends RenderableObject {
	BufferedImage img = null;
	String filename;

	public RenderableImage(String filename) {
		super(new Point(0, 0));
		this.filename = filename;
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
		}
		
		objectType = ObjectType.IMAGE;
		setupBoundary();
	}

	@Override
	public void setupBoundary() {
		dimension = new Dimension(img.getWidth(), img.getHeight());
	}

	@Override
	public RenderableObject makeCopy() {
		RenderableImage i = new RenderableImage(filename);
		i.setLocation(location);
		return i;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, null, location.x, location.y);
	}

	@Override
	public void save(PrintWriter f) {
		f.println("<Object>");
		f.println("<Type>\n Image \n</Type>");
		f.println("<Location>\n" + location.x + "\n" + location.y
				+ "\n</Location>");
		f.println("<Source>\n" + filename + "\n</Source>");
		f.print("</Object>\n");
	}

}
