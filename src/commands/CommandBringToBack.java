package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommandBringToBack implements Command {

	private DrawingModel model;
	private Shape shapeToBringBack;
	private int indexOfShapeToBringBack;

	public CommandBringToBack(DrawingModel model, Shape shapeToBringBack) {
		this.model = model;
		this.shapeToBringBack = shapeToBringBack;
		this.indexOfShapeToBringBack = model.getIndexOfShape(shapeToBringBack);
	}

	@Override
	public void executeCommand() {
		model.getShapes().remove(shapeToBringBack);
		model.getShapes().add(0, shapeToBringBack);
	}

	@Override
	public void unexecuteCommand() {
		model.getShapes().remove(shapeToBringBack);
		model.getShapes().add(indexOfShapeToBringBack, shapeToBringBack);
	}

	@Override
	public String commandToString() {
		return "Bring To Back: " + shapeToBringBack.toString();

	}
}
