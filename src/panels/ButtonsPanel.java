package panels;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import frame.LatexMaker;
import frameListener.FrameListener;

public class ButtonsPanel extends JPanel {

	private GroupLayout gl_panel;
	private FrameListener frameListener;

	public ButtonsPanel(FrameListener fl, JPanel panel) {

		frameListener = fl;

		JButton btnEq1 = new JButton("sin");
		btnEq1.addActionListener(frameListener);

		JButton btnEq2 = new JButton("cos");
		btnEq2.addActionListener(frameListener);

		JButton btnEq3 = new JButton("tan");
		btnEq3.addActionListener(frameListener);

		JButton btnEq4 = new JButton("eq4");
		btnEq4.addActionListener(frameListener);

		JButton btnEq5 = new JButton("eq5");
		btnEq5.addActionListener(frameListener);

		JButton btnEq6 = new JButton("eq6");
		btnEq6.addActionListener(frameListener);

		JButton btnEq7 = new JButton("eq7");
		btnEq7.addActionListener(frameListener);

		JButton btnEq8 = new JButton("eq8");
		btnEq8.addActionListener(frameListener);

		JButton btnEq9 = new JButton("eq9");
		btnEq9.addActionListener(frameListener);

		JButton btnEq10 = new JButton("abs");
		btnEq10.addActionListener(frameListener);

		JButton btnEq11 = new JButton("Eq");
		btnEq11.addActionListener(frameListener);

		JButton btnEq12 = new JButton("Fig");
		btnEq12.addActionListener(frameListener);

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnEq1)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq4,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq7,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq10,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnEq2,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq5,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq8,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq11,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnEq3,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq6,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq9,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnEq12,
																		GroupLayout.PREFERRED_SIZE,
																		75,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(72, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEq1,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq4,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq7,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq10,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEq2,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq5,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq8,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq11,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEq12,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq9,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq6,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEq3,
												GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(1, Short.MAX_VALUE)));
	}

	public GroupLayout getButtonsPanel() {
		return gl_panel;
	}

}
