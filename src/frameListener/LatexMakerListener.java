package frameListener;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import renderableObject.RenderableLatex;
import renderableObject.RenderableObject;
import frame.MainFrame;
import frame.LatexMaker;
import frame.ShapeMaker;

public class LatexMakerListener extends FrameListener {
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		addToText(e.getActionCommand());
		if(e.getActionCommand().equals("Save")){
			finish();
		}
	}
	
	private void finish() {
		RenderableLatex lm = new RenderableLatex(((LatexMaker) frame).getTextArea().getText());
		frame.setVisible(false);		
		lm.setLocation(((LatexMaker) frame).getParentFrame()
				.getClickLocation());
		
		((LatexMaker) frame).getParentFrame().getWorld().add(lm);
	}

	private void addToText(String command){
		LatexMaker lm = ((LatexMaker) frame);

		switch(command){
		case "abs":
			lm.addText("\\abs*{}\\\\\n");
			break;
		case "sin":
			lm.addText("\\sin{}\\\\\n");
			break;
		case "cos":
			lm.addText("\\cos{}\\\\\n");
			break;
		case "tan":
			lm.addText("\\tan{}\\\\\n");
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
