package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandAddShape implements Command {

	private DrawingModel model;
	private Shape shapeToAdd;

	public CommandAddShape(DrawingModel model, Shape shapeToAdd) {
		this.model = model;
		this.shapeToAdd = shapeToAdd;
	}

	@Override
	public void executeCommand() {
		model.add(shapeToAdd);
	}

	@Override
	public void unexecuteCommand() {
		model.remove(shapeToAdd);
	}

	@Override
	public String commandToString() {
		return "Added: " + shapeToAdd.toString();
	}
}
