package renderableObject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class RenderableLatex extends RenderableObject {

	String latexCode;
	BufferedImage renderedImage;

	public RenderableLatex(String text) {
		super(new Point(0, 0));

		latexCode = text;
		objectType = ObjectType.LATEX;

		renderLatex();
		setupBoundary();

	}

	private void renderLatex() {
		TeXFormula fomule = new TeXFormula(latexCode);
		TeXIcon ti = fomule.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		renderedImage = new BufferedImage(ti.getIconWidth(),
				ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		 ti.paintIcon(new JLabel(), renderedImage.getGraphics(), 0, 0);

	}

	@Override
	public void setupBoundary() {
		dimension = new Dimension(renderedImage.getWidth(),
				renderedImage.getHeight());
	}

	@Override
	public RenderableObject makeCopy() {
		RenderableLatex rl = new RenderableLatex(latexCode);
		rl.setLocation(location);
		// TODO Auto-generated method stub
		return rl;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(renderedImage, null, location.x, location.y);
		if(selected){
			drawBoundingBox(g);
		}
	drawID(g);
	}

	@Override
	public void save(PrintWriter f) {
		f.println("<Object>");
		f.println("<Type>\n Latex \n</Type>");
		f.println("<Location>\n" + location.x + "\n" + location.y
				+ "\n</Location>");
		f.println("<Source>\n" + latexCode + "\n</Source>");
		f.print("</Object>\n");
	}

	public String getCode() {
		return latexCode;
	}

	public BufferedImage getLatexImg() {
		return renderedImage;
	}

}
