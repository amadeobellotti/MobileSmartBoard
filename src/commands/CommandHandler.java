package commands;

import java.awt.event.ActionEvent;

import frameListener.*;
import frame.*;

public class CommandHandler extends Thread {
	private FrameListener mainFrame;
	public boolean quit;
	Command rootCmd, currentCmd;

	public CommandHandler(FrameListener mainFrame) {
		voce.SpeechInterface.init("voce-0.9.1/lib", false, true, "./gram",
				"commands");
		this.mainFrame = mainFrame;
		quit = false;
		rootCmd = setupCommandTree();
	}

	@Override
	public void run() {
		String s;
		Command next;

		currentCmd = rootCmd;

		while (!quit) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
				s = voce.SpeechInterface.popRecognizedString();
				if (s != null) {
					System.out.println("Interpreted command as: " + s);
					next = currentCmd.matchNext(s);
					if (next != null) {
						System.out.println(next);
						int start = next.toString().indexOf("Children: ") + 10;
						int end = next.toString().indexOf("\n", start);
						String screen = next.toString().substring(start, end);

						((MainFrame) mainFrame.getFrame()).getWorld()
								.setCommandsAvailble(screen);
						((MainFrame) mainFrame.getFrame()).repaint();
						currentCmd = next;
						if (next.hasAction()) {
							doAction(next);
						}
						if (next.isTerminating) {
							currentCmd = rootCmd;
						}
					} else
						System.out
								.println("Command not recognized / out of order");
					System.out.println();
				}
			}
		}
	}

	public void doAction(Command next) {
		mainFrame.actionPerformed(next.getAction());
	}

	public Command setupCommandTree() {
		Command cancelCmd = new Command("cancel", false);
		cancelCmd.setAction(new ActionEvent(this, 420, "Cancel"));
		cancelCmd.addNextCommand(cancelCmd);
		// environment open/save/save as
		Command openCmd = new Command("open", true);
		openCmd.setAction(new ActionEvent(this, 420, "Open"));
		cancelCmd.addNextCommand(openCmd);
		Command saveCmd = new Command("save", true);
		saveCmd.setAction(new ActionEvent(this, 420, "Save"));
		cancelCmd.addNextCommand(saveCmd);
		Command saveAsCmd = new Command("save as", true);
		saveAsCmd.setAction(new ActionEvent(this, 420, "Save As"));
		cancelCmd.addNextCommand(saveAsCmd);
		// entity selection
		Command selectCmd = new Command("select", false);
		selectCmd.setAction(new ActionEvent(this, 420, "Identify"));
		selectCmd.addNextCommand(cancelCmd);
		cancelCmd.addNextCommand(selectCmd);
		// add an identifier to each entity in the 'identify' code bloc
		String[] nums = { "zero", "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine", "ten" };
		for (int i = 0; i < nums.length; i++) {
			Command tempCmd = new Command(nums[i], true);
			tempCmd.setAction(new ActionEvent(this, 420, "select"
					+ Integer.toString(i)));
			tempCmd.addNextCommand(cancelCmd);
			selectCmd.addNextCommand(tempCmd);
		}
		// make sure 'cancel' deselects anything selected
		// entity copy, paste, modify, delete, cut children of 'cancel'
		Command copyCmd = new Command("copy", true);
		copyCmd.setAction(new ActionEvent(this, 420, "Copy"));
		cancelCmd.addNextCommand(copyCmd);
		Command cutCmd = new Command("cut", true);
		cutCmd.setAction(new ActionEvent(this, 420, "Cut"));
		cancelCmd.addNextCommand(cutCmd);
		Command pasteCmd = new Command("paste", true);
		pasteCmd.setAction(new ActionEvent(this, 420, "Paste"));
		cancelCmd.addNextCommand(pasteCmd);
		Command modifyCmd = new Command("modify", true);
		modifyCmd.setAction(new ActionEvent(this, 420, "Modify"));
		cancelCmd.addNextCommand(modifyCmd);
		Command deleteCmd = new Command("delete", true);
		deleteCmd.setAction(new ActionEvent(this, 420, "Delete"));
		cancelCmd.addNextCommand(deleteCmd);
		// be sure to check if anythings selected
		// be sure to check if theres anything on clipboard before pasting
		// entity creation
		Command newCmd = new Command("new", false);
		newCmd.addNextCommand(cancelCmd);
		cancelCmd.addNextCommand(newCmd);
		Command ellipseCmd = new Command("ellipse", true);
		ellipseCmd.setAction(new ActionEvent(this, 420, "Ellipse"));
		newCmd.addNextCommand(ellipseCmd);
		Command rectangleCmd = new Command("rectangle", true);
		rectangleCmd.setAction(new ActionEvent(this, 420, "Rectangle"));
		newCmd.addNextCommand(rectangleCmd);
		Command textCmd = new Command("text", true);
		textCmd.setAction(new ActionEvent(this, 420, "Text"));
		newCmd.addNextCommand(textCmd);
		Command latexCmd = new Command("latex", true);
		latexCmd.setAction(new ActionEvent(this, 420, "Latex"));
		newCmd.addNextCommand(latexCmd);
		Command imageCmd = new Command("image", true);
		imageCmd.setAction(new ActionEvent(this, 420, "Image"));
		newCmd.addNextCommand(imageCmd);
		Command shapeCmd = new Command("shape", true);
		shapeCmd.setAction(new ActionEvent(this, 420, "Custom"));
		newCmd.addNextCommand(shapeCmd);
		// exit
		// REMINDER: add System.exit(0) to MainFrameListener
		Command exitCmd = new Command("exit", true);
		exitCmd.setAction(new ActionEvent(this, 420, "Exit"));
		cancelCmd.addNextCommand(exitCmd);

		return cancelCmd;
	}
}
