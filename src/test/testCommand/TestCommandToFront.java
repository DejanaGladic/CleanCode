package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandToFront;
import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class TestCommandToFront {
	private DrawingModel model;
	private Shape shapeToManipulate;
	private CommandToFront commandToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToManipulate = new Rectangle(new Point(10, 20), 10, 20, Color.white, Color.black);
		model.add(shapeToManipulate);
	}

	@Test
	public void testExecuteToFrontCommandZeroIndex() {
		commandToFront = new CommandToFront(model, shapeToManipulate);

		commandToFront.executeCommand();

		int expectedIndexOfFirstElement = 0;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testExecuteToFrontCommandLastAddedShape() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandToFront = new CommandToFront(model, lastAddedShape);

		commandToFront.executeCommand();

		int expectedIndexOfFirstElement = 0;
		int expectedIndexOfLastElement = 1;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElement, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testExecutedExecuteToFrontCommand() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandToFront = new CommandToFront(model, shapeToManipulate);

		commandToFront.executeCommand();

		int expectedIndexOfFirstElementAfterExecution = 1;
		int expectedIndexOfLastElementAfterExecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterExecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElementAfterExecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testUnexecuteToFrontCommandZeroIndex() {
		commandToFront = new CommandToFront(model, shapeToManipulate);

		commandToFront.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testUnexecuteToFrontCommand() {
		Point newAddedShape = new Point(15, 25);
		model.add(newAddedShape);

		commandToFront = new CommandToFront(model, shapeToManipulate);

		commandToFront.executeCommand();
		commandToFront.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		int expectedIndexOfLastElementAfterUnexecution = 1;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElementAfterUnexecution, model.getIndexOfShape(newAddedShape));
	}

	@Test
	public void testToString() {
		commandToFront = new CommandToFront(model, shapeToManipulate);

		String expectedStringValue = "To Front: Rectangle: UpperLeftPoint= (10,20), Height=10, Width=20, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, commandToFront.commandToString());
	}
}
