package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandBringToFront implements Command {

	private DrawingModel model;
	private Shape shapeToManipulate;
	private int indexOfShapeToManipulate;

	public CommandBringToFront(DrawingModel model, Shape shapeToBringFront) {
		this.model = model;
		this.shapeToManipulate = shapeToBringFront;
		this.indexOfShapeToManipulate = model.getIndexOfShape(shapeToBringFront);
	}

	@Override
	public void executeCommand() {
		model.getShapes().remove(shapeToManipulate);
		model.getShapes().add(shapeToManipulate);
	}

	@Override
	public void unexecuteCommand() {
		model.getShapes().remove(shapeToManipulate);
		model.getShapes().add(indexOfShapeToManipulate, shapeToManipulate);
	}

	@Override
	public String commandToString() {
		return "Bring To Front: " + shapeToManipulate.toString();
	}
}
