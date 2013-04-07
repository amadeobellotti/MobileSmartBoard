package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import frameListener.LatexMakerListener;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import renderableObject.RenderableLatex;
import renderableObject.RenderableObject;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;

public class LatexMaker extends DefaultFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079423659426584357L;
	MainFrame parent;
	private static JTextArea txtArea;
	private static JTextField autoComplete1;
	private static JTextField autoComplete2;
	private static JTextField autoComplete3;
	private static JTextField autoComplete4;
	private static JScrollPane latexPane;
	private JLabel latexLabel;
	private boolean highlightIsOn;

	/**
	 * @wbp.parser.constructor
	 */
	public LatexMaker(MainFrame parent) {
		super(new LatexMakerListener(), new Dimension(800, 600));
		this.parent = parent;

		setTitle("Equation Editor");
		setResizable(false);
		setBounds(100, 100, 800, 542);

		Font btnFont = new Font("Lucida Grande", Font.PLAIN, 5);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(btnFont);
		btnSave.setForeground(UIManager.getColor("Button.background"));
		btnSave.setIcon(new ImageIcon(LatexMaker.class
				.getResource("/images/save.gif")));
		btnSave.setMargin(new Insets(0, 16, 0, 0));
		btnSave.addActionListener(frameListener);

		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(btnFont);
		btnUndo.setForeground(UIManager.getColor("Button.background"));
		btnUndo.setIcon(new ImageIcon(LatexMaker.class
				.getResource("/images/undo.gif")));
		btnUndo.setMargin(new Insets(0, 16, 0, 0));
		btnUndo.addActionListener(frameListener);

		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(btnFont);
		btnRedo.setForeground(UIManager.getColor("Button.background"));
		btnRedo.setIcon(new ImageIcon(LatexMaker.class
				.getResource("/images/redo.gif")));
		btnRedo.setMargin(new Insets(0, 16, 0, 0));
		btnRedo.addActionListener(frameListener);

		JButton btnPrev = new JButton("Prev");
		btnPrev.setFont(btnFont);
		btnPrev.setForeground(UIManager.getColor("Button.background"));
		btnPrev.setIcon(new ImageIcon(LatexMaker.class
				.getResource("/images/back.gif")));
		btnPrev.setMargin(new Insets(0, 16, 0, 0));
		btnPrev.addActionListener(frameListener);

		JButton btnNext = new JButton("Next");
		btnNext.setFont(btnFont);
		btnNext.setForeground(UIManager.getColor("Button.background"));
		btnNext.setIcon(new ImageIcon(LatexMaker.class
				.getResource("/images/forward.gif")));
		btnNext.setMargin(new Insets(0, 16, 0, 0));
		btnNext.addActionListener(frameListener);

		JScrollPane scrollPane = new JScrollPane();
		latexPane = new JScrollPane();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																latexPane,
																GroupLayout.DEFAULT_SIZE,
																586,
																Short.MAX_VALUE)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																586,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnSave,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																196,
																Short.MAX_VALUE)
														.addComponent(
																panel,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																196,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnPrev,
																								GroupLayout.PREFERRED_SIZE,
																								95,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnUndo,
																								GroupLayout.PREFERRED_SIZE,
																								95,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								btnRedo,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnNext,
																								GroupLayout.DEFAULT_SIZE,
																								95,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnSave,
																				GroupLayout.PREFERRED_SIZE,
																				101,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnRedo,
																								GroupLayout.PREFERRED_SIZE,
																								101,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnUndo,
																								GroupLayout.PREFERRED_SIZE,
																								101,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				scrollPane,
																				GroupLayout.DEFAULT_SIZE,
																				202,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnPrev,
																								GroupLayout.PREFERRED_SIZE,
																								101,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnNext,
																								GroupLayout.PREFERRED_SIZE,
																								101,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				panel,
																				GroupLayout.DEFAULT_SIZE,
																				193,
																				Short.MAX_VALUE))
														.addComponent(
																latexPane,
																GroupLayout.DEFAULT_SIZE,
																300,
																Short.MAX_VALUE))
										.addContainerGap()));
		txtArea = new JTextArea();
		txtArea.getDocument().addDocumentListener(
				(DocumentListener) frameListener);
		txtArea.setText("");
		txtArea.setWrapStyleWord(true);
		txtArea.setCaretPosition(0);
		txtArea.setLineWrap(true);

		latexLabel = new JLabel("");
		latexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		latexPane.setViewportView(latexLabel);

		autoComplete1 = new JTextField();
		autoComplete1.addFocusListener((FocusListener) frameListener);
		autoComplete1.setEditable(false);
		autoComplete1.setDragEnabled(false);
		autoComplete1.setAutoscrolls(false);
		autoComplete1.setBorder(null);
		autoComplete1.setColumns(10);
		autoComplete1.setBounds(0, 0, 193, 50);

		autoComplete2 = new JTextField();
		autoComplete2.addFocusListener((FocusListener) frameListener);
		autoComplete2.setEditable(false);
		autoComplete2.setDragEnabled(false);
		autoComplete2.setAutoscrolls(false);
		autoComplete2.setBorder(null);
		autoComplete2.setColumns(10);
		autoComplete2.setBounds(0, 47, 193, 50);

		autoComplete3 = new JTextField();
		autoComplete3.addFocusListener((FocusListener) frameListener);
		autoComplete3.setEditable(false);
		autoComplete3.setDragEnabled(false);
		autoComplete3.setAutoscrolls(false);
		autoComplete3.setBorder(null);
		autoComplete3.setColumns(10);
		autoComplete3.setBounds(0, 94, 193, 50);

		autoComplete4 = new JTextField();
		autoComplete4.addFocusListener((FocusListener) frameListener);
		autoComplete4.setEditable(false);
		autoComplete4.setDragEnabled(false);
		autoComplete4.setAutoscrolls(false);
		autoComplete4.setBorder(null);
		autoComplete4.setColumns(10);
		autoComplete4.setBounds(0, 142, 193, 50);

		scrollPane.setViewportView(txtArea);
		panel.setLayout(null);
		panel.add(autoComplete1);
		panel.add(autoComplete2);
		panel.add(autoComplete3);
		panel.add(autoComplete4);
		getContentPane().setLayout(groupLayout);

		setVisible(true);
	}

	public LatexMaker(MainFrame frame, RenderableObject selectedObject) {
		this(frame);
		txtArea.setText(((RenderableLatex) selectedObject).getCode());
	}

	public JTextArea getTextArea() {
		return txtArea;
	}

	public void addText(String text) {
		txtArea.setText(txtArea.getText() + text);
	}

	public MainFrame getParentFrame() {
		return parent;
	}

	public void setAutoCompleteOptions(String[] autoCompleteSuggestion) {
		autoComplete1.setText(autoCompleteSuggestion[0]);
		autoComplete2.setText(autoCompleteSuggestion[1]);
		autoComplete3.setText(autoCompleteSuggestion[2]);
		autoComplete4.setText(autoCompleteSuggestion[3]);
	}

	public void setLatexLabel(ImageIcon latexIcon) {
		latexLabel.setIcon(latexIcon);
	}

	public void clearLatexLabel() {
		latexLabel.setIcon(null);
	}

	public void setHighlighter(int startHighlight, int endHighlight) {
		if (startHighlight <= endHighlight && startHighlight >= 0
				&& endHighlight <= txtArea.getText().length()) {
			try {
				txtArea.getHighlighter().addHighlight(
						startHighlight,
						endHighlight,
						new DefaultHighlighter.DefaultHighlightPainter(Color
								.decode("#CEE6FA")));
				highlightIsOn = true;
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	public Highlighter getHighlightedText() {
		return txtArea.getHighlighter();
	}

	public void removeHighlights() {
		txtArea.getHighlighter().removeAllHighlights();
		highlightIsOn = false;
	}

	public boolean checkIfHighlight() {
		return highlightIsOn;
	}
}
