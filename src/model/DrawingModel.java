package model;

import java.util.ArrayList;
import shapes.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	public void addSelected(Shape shape) {
		shape.setSelected(true);
		selectedShapes.add(shape);
	}

	public void removeSelected(Shape shape) {
		selectedShapes.remove(shape);
		shape.setSelected(false);
	}

	public Shape getSelectedShape() {
		for (Shape shape : shapes) {
			if (shape != null && shape.isSelected())
				return shape;
		}
		return null;
	}

	public void add(Shape p) {
		shapes.add(p);
	}

	public void remove(Shape p) {
		shapes.remove(p);
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	public boolean doesModelContainShape(Shape shape) {
		return shapes.contains(shape);
	}

	public boolean doesModelContainSelectedShape(Shape shapeToSelect) {
		return selectedShapes.contains(shapeToSelect);
	}

	public boolean doesModelContainSelectedShapes(ArrayList<Shape> shapesToDelete) {
		return shapes.containsAll(shapesToDelete);
	}

	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}

	public ArrayList<Shape> getSelected() {
		return selectedShapes;
	}

	public void setSelected(ArrayList<Shape> selected) {
		this.selectedShapes = selected;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

}
