package test.testCommand;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandToBack;
import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class TestCommandToBack {
	private DrawingModel model;
	private Shape shapeToManipulate;
	private CommandToBack commandToBack;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToManipulate = new Rectangle(new Point(10, 20), 10, 20, Color.white, Color.black);
		model.add(shapeToManipulate);
	}

	@Test
	public void testExecuteToBackCommandZeroIndex() {
		commandToBack = new CommandToBack(model, shapeToManipulate);

		commandToBack.executeCommand();

		int expectedIndexOfFirstElement = 0;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testExecuteToBackCommandFirstAddedShape() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandToBack = new CommandToBack(model, shapeToManipulate);

		commandToBack.executeCommand();

		int expectedIndexOfFirstElement = 0;
		int expectedIndexOfLastElement = 1;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElement, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testExecutedExecuteToBackCommand() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandToBack = new CommandToBack(model, lastAddedShape);

		commandToBack.executeCommand();

		int expectedIndexOfFirstElementAfterExecution = 1;
		int expectedIndexOfLastElementAfterExecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterExecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElementAfterExecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testUnexecuteToBackCommandZeroIndex() {
		commandToBack = new CommandToBack(model, shapeToManipulate);

		commandToBack.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testUnexecuteToFrontCommand() {
		Point newAddedShape = new Point(15, 25);
		model.add(newAddedShape);

		commandToBack = new CommandToBack(model, newAddedShape);

		commandToBack.executeCommand();
		commandToBack.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		int expectedIndexOfLastElementAfterUnexecution = 1;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElementAfterUnexecution, model.getIndexOfShape(newAddedShape));
	}

	@Test
	public void testToString() {
		commandToBack = new CommandToBack(model, shapeToManipulate);

		String expectedStringValue = "To Back: Rectangle: UpperLeftPoint= (10,20), Height=10, Width=20, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, commandToBack.commandToString());
	}
}
