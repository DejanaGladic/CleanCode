package commands;

import java.util.Stack;

public class CommandUndoRedo implements Command {

	private Stack<Command> commands = new Stack<Command>();
	private Stack<Command> redoCommands = new Stack<Command>();
	private String typeOfCommand;
	private Command commandToUndoRedo;

	@Override
	public void executeCommand() {
		if (getRedoCommands().isEmpty() == false) {
			typeOfCommand = "Redo command: ";
			commandToUndoRedo = getRedoCommands().pop();
			commandToUndoRedo.executeCommand();
			getCommands().push(commandToUndoRedo);
		}
	}

	@Override
	public void unexecuteCommand() {
		if (commands.isEmpty() == false) {
			typeOfCommand = "Undo command: ";
			commandToUndoRedo = getCommands().pop();
			commandToUndoRedo.unexecuteCommand();
			getRedoCommands().push(commandToUndoRedo);
		}
	}

	@Override
	public String commandToString() {
		return typeOfCommand + commandToUndoRedo.commandToString();
	}

	public Stack<Command> getCommands() {
		return commands;
	}

	public void setCommands(Stack<Command> commands) {
		this.commands = commands;
	}

	public Stack<Command> getRedoCommands() {
		return redoCommands;
	}

	public void setRedoCommands(Stack<Command> redoCommands) {
		this.redoCommands = redoCommands;
	}
}
