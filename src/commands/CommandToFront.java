package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandToFront implements Command {

	private DrawingModel model;
	private Shape shapeToFront;
	private int indexOfShape;

	public CommandToFront(DrawingModel model, Shape shapeToFront) {
		this.model = model;
		this.shapeToFront = shapeToFront;
		this.indexOfShape = model.getIndexOfShape(shapeToFront);
	}

	@Override
	public void executeCommand() {
		if (indexOfShape < model.getShapes().size() - 1) {
			model.getShapes().remove(shapeToFront);
			model.getShapes().add(indexOfShape + 1, shapeToFront);
		}
	}

	@Override
	public void unexecuteCommand() {
		model.getShapes().remove(shapeToFront);
		model.getShapes().add(indexOfShape, shapeToFront);
	}

	@Override
	public String commandToString() {
		return "To Front: " + shapeToFront.toString();
	}
}
