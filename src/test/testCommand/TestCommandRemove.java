package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandRemoveShape;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class TestCommandRemove {
	private DrawingModel model;
	private Shape shapeToRemove;
	private int indexOfShapeToRemove;
	private CommandRemoveShape commandRemove;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToRemove = new Point(10, 20, Color.black);
		commandRemove = new CommandRemoveShape(model, shapeToRemove, indexOfShapeToRemove);
	}

	@Test
	public void testExecuteRemoveCommandExpectFalse() {
		commandRemove.executeCommand();
		assertFalse(model.doesModelContainShape(shapeToRemove));
	}

	@Test
	public void testUnexecuteRemoveCommandExpectTrue() {
		commandRemove.unexecuteCommand();
		assertTrue(model.doesModelContainShape(shapeToRemove));
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Removed: Point: (10, 20), Color: (-16777216)";
		assertEquals(expectedStringValue, commandRemove.commandToString());
	}
}
