package commands;

import java.util.ArrayList;

import model.DrawingModel;
import shapes.Shape;

public class CommandRemoveSelectedShapes implements Command {

	private DrawingModel model;
	private ArrayList<Shape> shapesToDelete;
	private ArrayList<Shape> shapesToReturn = new ArrayList<Shape>();

	public CommandRemoveSelectedShapes(DrawingModel model, ArrayList<Shape> shapesToDelete) {
		this.model = model;
		this.shapesToDelete = shapesToDelete;
	}

	@Override
	public void executeCommand() {
		if (shapesToDelete.isEmpty())
			model.getShapes().removeAll(shapesToReturn);
		else {
			shapesToReturn.addAll(shapesToDelete);
			model.getShapes().removeAll(shapesToDelete);
		}
	}

	@Override
	public void unexecuteCommand() {
		model.getShapes().addAll(shapesToReturn);
	}

	@Override
	public String commandToString() {
		if (shapesToDelete.isEmpty())
			return "Removed:" + shapesToReturn.toString();
		return "Removed:" + shapesToDelete.toString();
	}
}
