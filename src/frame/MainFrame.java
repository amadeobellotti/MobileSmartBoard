package frame;

import java.awt.Dimension;
import java.awt.Point;

import environment.DefaultEnviornment;
import environment.Enviornment;
import frameListener.FrameListener;
import frameListener.MainFrameListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends DefaultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2501040976700814999L;
	private DefaultEnviornment world;

	private JMenuBar menuBar;
	private JPopupMenu rightClickMenu;
	private Point frameLocation;

	public MainFrame() {
		super(new MainFrameListener(), new Dimension(1024, 768));
		setSize(frameSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frameLocation=new Point(0,0);
		world = new DefaultEnviornment();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(world,
				GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(world,
				GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE));

		getContentPane().setLayout(groupLayout);
		world.addMouseListener(frameListener);

		setupFrame();
		setupMenu();
		setupPopup();

		setVisible(true);
	}

	private void setupPopup() {
		rightClickMenu = new JPopupMenu();
		rightClickMenu.addMouseListener(frameListener);
		//addPopup(world, rightClickMenu,this);

		JMenu mnAdd = new JMenu("Add");
		rightClickMenu.add(mnAdd);
		JMenuItem mntmShape = new JMenuItem("Shape");
		mnAdd.add(mntmShape);
		mntmShape.addActionListener(frameListener);
	}

	private void setupFrame() {
		// TODO Auto-generated method stub

	}

	public void setupMenu() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}

	public DefaultEnviornment getWorld() {
		return world;
	}

	public void setWorld(DefaultEnviornment world) {
		this.world = world;
	}

	public JPopupMenu getRightClickMenu() {
		return rightClickMenu;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void setClickLocation(Point loc) {
		frameLocation = loc;
		System.out.println(frameLocation);
	}

	public Point getClickLocation() {
		return frameLocation;
	}
}
