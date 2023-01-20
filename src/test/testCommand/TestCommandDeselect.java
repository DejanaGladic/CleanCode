package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommadDeselectShape;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class TestCommandDeselect {
	private DrawingModel model;
	private Shape shapeToSelect;
	private CommadDeselectShape commadDeselectShape;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToSelect = new Point(10, 20, Color.black);
		commadDeselectShape = new CommadDeselectShape(model, shapeToSelect);
	}

	@Test
	public void testExecuteDeselectCommandExpectTrue() {
		commadDeselectShape.executeCommand();
		assertFalse(shapeToSelect.isSelected());
		assertFalse(model.doesModelContainSelectedShape(shapeToSelect));
	}

	@Test
	public void testUnexecuteDeselectCommandExpectFalse() {
		commadDeselectShape.unexecuteCommand();
		assertTrue(shapeToSelect.isSelected());
		assertTrue(model.doesModelContainSelectedShape(shapeToSelect));
	}

	@Test
	public void testToStringIfShapeIsDeselected() {
		commadDeselectShape.executeCommand();
		String expectedStringValue = "Deselected: Point: (10, 20), Color: (-16777216)";
		assertEquals(expectedStringValue, commadDeselectShape.commandToString());
	}
}
