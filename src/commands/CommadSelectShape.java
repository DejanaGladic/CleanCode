package commands;

import model.DrawingModel;
import shapes.Shape;

public class CommadSelectShape implements Command {

	private DrawingModel model;
	private Shape shape;

	public CommadSelectShape(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void executeCommand() {
		shape.setSelected(true);
		model.getSelected().add(shape);
	}

	@Override
	public void unexecuteCommand() {
		shape.setSelected(false);
		model.getSelected().remove(shape);
	}

	@Override
	public String commandToString() {
		return "Selected: " + shape.toString();
	}
}
