package commands;

import shapes.Shape;

public class CommandUpdateShape implements Command {

	private Shape oldState;
	private Shape newState;
	private Shape saveOldStateShape;

	public CommandUpdateShape(Shape oldState, Shape newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void executeCommand() {
		saveOldStateShape = oldState.clone();
		oldState.setNewValuesForOldState(newState);
	}

	@Override
	public void unexecuteCommand() {
		oldState.setNewValuesForOldState(saveOldStateShape);
	}

	@Override
	public String commandToString() {
		return "Updated shape: ---> " + saveOldStateShape.toString() + " to " + newState.toString();
	}
}
