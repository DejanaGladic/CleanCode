package test.testModel;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class TestModel {

	private DrawingModel model;
	private Point point_one;
	private Point point_two;
	private ArrayList<Shape> shapes;
	private ArrayList<Shape> selectedShapes;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapes = new ArrayList<Shape>();
		selectedShapes = new ArrayList<Shape>();
		model.setShapes(shapes);
		model.setSelected(selectedShapes);
		point_one = new Point(150, 250, Color.black, true);
		shapes.add(point_one);
		selectedShapes.add(point_one);
		point_two = new Point(350, 564, Color.black);
		shapes.add(point_two);
	}

	@Test
	public void testAddSelected() {
		model.addSelected(point_two);

		assertEquals(point_two, selectedShapes.get(1));
	}

	@Test
	public void testRemoveSelected() {
		model.removeSelected(point_two);

		assertEquals(1, selectedShapes.size());
	}

	@Test
	public void testGetSelectedShape() {
		assertEquals(point_one, model.getSelectedShape());
	}

	@Test
	public void testAdd() {
		Shape shapeToAdd = new Point(361, 574, Color.black);
		model.add(shapeToAdd);

		assertTrue(model.getShapes().contains(shapeToAdd));
	}

	@Test
	public void testRemove() {
		Shape shapeToRemove = new Point(361, 574, Color.black);
		model.add(shapeToRemove);
		model.remove(shapeToRemove);

		assertFalse(model.getShapes().contains(shapeToRemove));
	}

	@Test
	public void testDoesModelContainShapeExpectedTrue() {
		assertTrue(model.doesModelContainShape(point_one));
	}

	@Test
	public void testDoesModelContainShapeExpectedFalse() {
		Shape shape = new Point(361, 574, Color.black);

		assertFalse(model.doesModelContainShape(shape));
	}

	@Test
	public void testDoesModelContainSelectedShapeExpectedTrue() {
		assertTrue(model.doesModelContainSelectedShape(point_one));
	}

	@Test
	public void testDoesModelContainSelectedShapeExpectedFalse() {
		assertFalse(model.doesModelContainSelectedShape(point_two));
	}

}
