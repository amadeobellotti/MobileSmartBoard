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

import renderableObject.RenderableObject.ObjectType;

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
		f.println("<Type> Text </Type>");
		f.println("<Location>" + location.x+","+location.y +"</Location>");
		f.println("<Text>" +text+ "</Text>");
		
		f.println("<Font>");
		f.println("<Name>" +font.getName()+ "</Name>");
		f.println("<Style>" +font.getStyle()+ "</Style>");
		f.println("<Size>" +font.getSize()+ "</Size>");
		f.println("</Font>");
		
		f.println("<Color>" + c.getRed()+","+c.getGreen() +"," +c.getBlue() +"</Color>");
		f.println("</Object>");
	}

	@Override
	public void setupBoundary() {
		Scanner s = new Scanner(text);
		int lineNumber = 1;
		String currentLine = s.nextLine();
		int lineLength = currentLine.length();
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 
		int textwidth = (int)(font.getStringBounds(currentLine, frc).getWidth());
		//int textheight = (int)(font.getStringBounds(currentLine, frc).getHeight());		
		while (s.hasNext()) {
			while (currentLine.length() > LINE_LENGTH) {
				lineLength = LINE_LENGTH;
				textwidth = (int)(font.getStringBounds(currentLine.substring(0,LINE_LENGTH), frc).getWidth());
				currentLine = currentLine.substring(LINE_LENGTH);
				
				lineNumber++;
			}
			currentLine = s.nextLine();
			lineNumber++;
		}
		s.close();
		
		
		
		dimension = new Dimension(textwidth+font.getSize()/2,font.getSize()*lineNumber);
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
