package renderableObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.PrintWriter;
import java.util.Scanner;

public class Text extends RenderableObject {
	protected static String className = "Text";
	protected int classCount = 0;
	public final int LINE_LENGTH = 80;

	private String text;
	private Font font;
	private Color c;

	public Text(String text) {
		this(text, new Font(null, 15, Font.BOLD), Color.BLACK);
	}

	public Text(String text, Font font, Color c) {
		super(new Point(0, 0));
		this.font = font;
		this.c = c;
		this.text = text;

		setupBoundary();
		objectType = ObjectType.TEXT;

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.setFont(font);

		Scanner s = new Scanner(text);
		int lineNumber = 1;
		while (s.hasNext()) {
			String currentLine = s.nextLine();
			while (currentLine.length() > LINE_LENGTH) {
				String cline = currentLine.substring(0, LINE_LENGTH);
				currentLine = currentLine.substring(LINE_LENGTH);
				g.drawString(cline, location.x, location.y
						+ (font.getSize() * lineNumber));
				lineNumber++;
			}
			g.drawString(currentLine, location.x, location.y
					+ (font.getSize() * lineNumber));
			lineNumber++;
		}
		s.close();

		if (selected) {
			drawBoundingBox(g);
		}

	}

	@Override
	public void save(PrintWriter f) {
		f.println("<Object>");
		f.println("<Type>\n Text \n</Type>");
		f.println("<Location>\n" + location.x + "\n" + location.y
				+ "\n</Location>");
		f.println("<Text>\n" + text + "\n</Text>");

		f.println("<Font>");
		f.println(font.getName());
		f.println(font.getStyle());
		f.println(font.getSize());
		f.println("</Font>");

		f.println("<Color>\n" + c.getRed() + "\n" + c.getGreen() + "\n"
				+ c.getBlue() + "\n</Color>");
		f.println("</Object>");
	}

	@Override
	public void setupBoundary() {
		Scanner s = new Scanner(text);
		int lineNumber = 1;
		String currentLine = "";
		if (s.hasNextLine())
			currentLine = s.nextLine();
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true,
				true);
		int textwidth = (int) (font.getStringBounds(currentLine, frc)
				.getWidth());
		// int textheight = (int)(font.getStringBounds(currentLine,
		// frc).getHeight());
		while (s.hasNext()) {
			while (currentLine.length() > LINE_LENGTH) {
				textwidth = (int) (font.getStringBounds(
						currentLine.substring(0, LINE_LENGTH), frc).getWidth());
				currentLine = currentLine.substring(LINE_LENGTH);

				lineNumber++;
			}
			currentLine = s.nextLine();
			lineNumber++;
		}
		s.close();

		dimension = new Dimension(textwidth + font.getSize() / 2,
				font.getSize() * lineNumber);
	}

	@Override
	public RenderableObject makeCopy() {
		String text = this.text;

		Text copy = new Text(text, font, c);
		copy.setLocation(this.getLocation());
		return copy;
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		text = t;
	}

	public Font getFont() {
		return font;
	}

}
