package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandToBack implements Command {

	private DrawingModel model;
	private Shape shapeToBack;
	private int indexOfShape;

	public CommandToBack(DrawingModel model, Shape shapeToBack) {
		this.model = model;
		this.shapeToBack = shapeToBack;
		this.indexOfShape = model.getIndexOfShape(shapeToBack);
	}

	@Override
	public void executeCommand() {
		if (indexOfShape == 0) {
			return;
		}
		model.getShapes().remove(shapeToBack);
		model.getShapes().add(indexOfShape - 1, shapeToBack);
	}

	@Override
	public void unexecuteCommand() {
		model.getShapes().remove(shapeToBack);
		model.getShapes().add(indexOfShape, shapeToBack);
	}

	@Override
	public String commandToString() {
		return "To Back: " + shapeToBack.toString();
	}
}
