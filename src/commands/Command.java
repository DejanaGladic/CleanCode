package commands;

public interface Command {
	void executeCommand();

	void unexecuteCommand();

	String commandToString();
}
