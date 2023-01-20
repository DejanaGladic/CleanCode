package test.testCommand;

import static org.junit.Assert.*;
import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommadSelectShape;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class TestCommandSelect {
	private DrawingModel model;
	private Shape shapeToSelect;
	private CommadSelectShape commandSelectShape;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToSelect = new Point(10, 20, Color.black);
		commandSelectShape = new CommadSelectShape(model, shapeToSelect);
	}

	@Test
	public void testExecuteSelectCommandExpectTrue() {
		commandSelectShape.executeCommand();
		assertTrue(shapeToSelect.isSelected());
		assertTrue(model.doesModelContainSelectedShape(shapeToSelect));
	}

	@Test
	public void testUnexecuteSelectCommandExpectFalse() {
		commandSelectShape.unexecuteCommand();
		assertFalse(shapeToSelect.isSelected());
		assertFalse(model.doesModelContainSelectedShape(shapeToSelect));
	}

	@Test
	public void testToStringIfShapeIsSelected() {
		commandSelectShape.executeCommand();
		String expectedStringValue = "Selected: Point: (10, 20), Color: (-16777216)";
		assertEquals(expectedStringValue, commandSelectShape.commandToString());
	}
}
