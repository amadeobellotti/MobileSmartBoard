package frame;

import java.awt.Dimension;
import frameListener.LatexMakerListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import panels.ButtonsPanel;
import renderableObject.RenderableLatex;
import renderableObject.RenderableObject;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LatexMaker extends DefaultFrame {

	MainFrame parent;
	private JTextArea txtArea;
	private int caretPosition;

	// private Equation equationPanel;

	public LatexMaker(MainFrame parent) {
		super(new LatexMakerListener(), new Dimension(800, 600));
		this.parent = parent;
		setTitle("Text and Equation Editor");

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 542);

		JScrollPane scrollPane = new JScrollPane();
		txtArea = new JTextArea();
		// txtArea.setText("\\begin{document}\n\\usepackage{mathtools}\n");
		caretPosition = txtArea.getText().length();
		txtArea.setWrapStyleWord(true);
		txtArea.setCaretPosition(caretPosition);
		txtArea.setLineWrap(true);
		scrollPane.setViewportView(txtArea);
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(frameListener);
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(frameListener);
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(frameListener);

		JButton btnbegin = new JButton("\\begin");
		btnbegin.addActionListener(frameListener);

		JButton btnend = new JButton("\\end");
		btnend.addActionListener(frameListener);

		JPanel panel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
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
																		.addGap(1)
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				641,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnUndo,
																								GroupLayout.PREFERRED_SIZE,
																								136,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnSave,
																								GroupLayout.PREFERRED_SIZE,
																								136,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnRedo,
																								GroupLayout.PREFERRED_SIZE,
																								136,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				321,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnend,
																								GroupLayout.PREFERRED_SIZE,
																								72,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnbegin,
																								GroupLayout.PREFERRED_SIZE,
																								72,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(10, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(1)
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
																		.addComponent(
																				btnRedo,
																				GroupLayout.PREFERRED_SIZE,
																				101,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnUndo,
																				GroupLayout.PREFERRED_SIZE,
																				101,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																315,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																190,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnbegin,
																				GroupLayout.PREFERRED_SIZE,
																				50,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnend,
																				GroupLayout.PREFERRED_SIZE,
																				50,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(8, Short.MAX_VALUE)));

		GroupLayout latexButtons = new ButtonsPanel(frameListener, panel)
				.getButtonsPanel();

		panel.setLayout(latexButtons);
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
}
