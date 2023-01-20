package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommadDeselectShape implements Command {

	private DrawingModel model;
	private Shape shape;

	public CommadDeselectShape(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void executeCommand() {
		shape.setSelected(false);
		model.getSelected().remove(shape);
	}

	@Override
	public void unexecuteCommand() {
		shape.setSelected(true);
		model.getSelected().add(shape);
	}

	@Override
	public String commandToString() {
		return "Deselected: " + shape.toString();
	}
}
