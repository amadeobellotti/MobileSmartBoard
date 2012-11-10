package frame;

import java.awt.Dimension;

import environment.DefaultEnviornment;
import environment.Enviornment;
import frameListener.FrameListener;
import frameListener.MainFrameListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MainFrame extends DefaultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2501040976700814999L;
	private DefaultEnviornment world;

	public MainFrame() {
		super(new MainFrameListener(), new Dimension(1024,768));
		setSize(frameSize);
		
		world = new DefaultEnviornment();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(world, GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(world, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		world.addMouseListener(frameListener);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		setupFrame();
		
		setVisible(true);
	}

	private void setupFrame() {
		// TODO Auto-generated method stub
		
	}

	public DefaultEnviornment getWorld() {
		return world;
	}

	public void setWorld(DefaultEnviornment world) {
		this.world = world;
	}

}
