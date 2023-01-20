package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandAddShape;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class TestCommandAdd {

	private DrawingModel model;
	private Shape shapeToAdd;
	private CommandAddShape commandAdd;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToAdd = new Point(10, 20, Color.black);
		commandAdd = new CommandAddShape(model, shapeToAdd);
	}

	@Test
	public void testExecuteAddCommandExpectTrue() {
		commandAdd.executeCommand();
		assertTrue(model.doesModelContainShape(shapeToAdd));
	}

	@Test
	public void testUnexecuteAddCommandExpectFalse() {
		commandAdd.unexecuteCommand();
		assertFalse(model.doesModelContainShape(shapeToAdd));
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Added: Point: (10, 20), Color: (-16777216)";
		assertEquals(expectedStringValue, commandAdd.commandToString());
	}
}
