package commands;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Command {
	private String commandText;
	private ArrayList<Command> nextCommands;
	public boolean isTerminating;
	private ActionEvent action;
	
	public Command(String commandText, boolean isTerminating) {
		this.commandText = commandText;
		this.isTerminating = isTerminating;
		if (isTerminating) {
			nextCommands = null;
		}
		else {
			nextCommands = new ArrayList<Command>();
		}
		action = null;
	}
	
	public ActionEvent getAction() {
		return action;
	}
	
	public void setAction(ActionEvent e) {
		action = e;
	}
	
	public boolean hasAction() {
		if (action != null) {
			return true;
		}
		else return false;
	}
	
	//returns a matching Command object to given nextCommand string
	//if one exists, otherwise returns null
	public Command matchNext(String nextCommand) {
		if (!isTerminating) {
			for (int i = 0; i < nextCommands.size(); i++) {
				if (nextCommands.get(i).getText().equalsIgnoreCase(nextCommand)) {
					return nextCommands.get(i);
				}
			}
			return null;
		}
		return null;
	}
	
	public String getText() {
		return commandText;
	}
	
	public boolean addNextCommand(Command newCommand) {
		if (!isTerminating) {
			nextCommands.add(newCommand);
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s;
		s = "Command: ";
		s += commandText;
		if (nextCommands != null) {
			s += "\n";
			s += "Children: ";
			for (int i= 0; i < nextCommands.size(); i++) {
				if (i != 0) s += ", ";
				s += nextCommands.get(i).getText();
			}
		}
		s += "\n";
		s += "Terminating: " + isTerminating;
		return s;
	}
}
