package panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	private Color color = Color.BLACK;

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawRect(0, 0, getWidth(), getHeight());

	}

}
