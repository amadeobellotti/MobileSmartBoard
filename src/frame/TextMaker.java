package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;


import frameListener.TextMakerListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import panels.ColorPanel;
import renderableObject.RenderableObject;
import renderableObject.Text;

import javax.swing.JLabel;

public class TextMaker extends DefaultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 185806512927502326L;
	private MainFrame parent;
	private Font font;
	private JLabel fontLabel;
	private JTextArea textArea;
	private Text text;
	private ColorPanel colorPanel;

	/**
	 * @wbp.parser.constructor
	 */
	public TextMaker(MainFrame parent, Text ro) {
		this(parent);
		ro.setSelected(false);
		ro.setLocation(new Point(0,0));
		text = ro;
		textArea.setText(text.getText());
		font = text.getFont();
		textArea.setFont(font);
	}

	public TextMaker(MainFrame parent) {
		super(new TextMakerListener(), new Dimension(800, 600));
		setTitle("Text Maker");
		frameListener.setFrame(this);
		this.parent = parent;
		textArea = new JTextArea();
		font = textArea.getFont();
		JButton btnSetFont = new JButton("Set Font");
		btnSetFont.addActionListener(frameListener);
		colorPanel = new ColorPanel();

		JButton colorButton = new JButton("Set Color");
		colorButton.addActionListener(frameListener);

		fontLabel = new JLabel(font.getFontName() + " " + font.getSize2D());

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(frameListener);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addComponent(colorButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSetFont)
						.addComponent(fontLabel)
						.addComponent(btnFinish))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(colorButton)
							.addGap(15)
							.addComponent(fontLabel)
							.addGap(18)
							.addComponent(btnSetFont)
							.addGap(119)
							.addComponent(btnFinish))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		setVisible(true);
	}

	public Font getCurrentFont() {
		return font;
	}

	public void setCurrentFont(Font currentFont) {
		font = currentFont;
		fontLabel = new JLabel(font.getFontName() + " " + font.getSize2D());
		textArea.setFont(font);

	}

	public RenderableObject getText() {
		text = new Text(textArea.getText(), font, colorPanel.getColor());

		return text;
	}

	public MainFrame getParentFrame() {
		return parent;
	}

	public Color getColor() {
		return colorPanel.getColor();
	}

	public void setColor(Color c) {
		colorPanel.setColor(c);
	}

	public void appendText(String s) {
		System.out.println(s);
		textArea.setText(textArea.getText() + " " +s);
	}
}
