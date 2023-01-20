package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandRemoveShape implements Command {

	private DrawingModel model;
	private Shape shapeToRemove;
	private int indexOfRemovedShape;

	public CommandRemoveShape(DrawingModel model, Shape shapeToRemove, int index) {
		this.model = model;
		this.shapeToRemove = shapeToRemove;
		this.indexOfRemovedShape = index;
	}

	public void executeCommand() {
		model.remove(shapeToRemove);
	}

	public void unexecuteCommand() {
		model.getShapes().add(indexOfRemovedShape, shapeToRemove);
	}

	public String commandToString() {
		return "Removed: " + shapeToRemove.toString();
	}
}
