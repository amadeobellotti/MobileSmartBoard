package renderableObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
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

		Scanner s = new Scanner(text);
		int lineNumber = 1;
		String currentLine = s.nextLine();
		int lineLength = currentLine.length();
		while (s.hasNext()) {
			while (currentLine.length() > LINE_LENGTH) {
				lineLength = LINE_LENGTH;
				currentLine = currentLine.substring(LINE_LENGTH);
				lineNumber++;
			}
			currentLine = s.nextLine();
			lineNumber++;
		}
		s.close();

		dimension = new Dimension(font.getSize() * lineLength*2/3, lineNumber
				* font.getSize());
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
	public void save(String filename) {
		// TODO Auto-generated method stub

	}

}
