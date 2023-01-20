package test.testCommand;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandBringToFront;
import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class TestCommandBringToFront {
	private DrawingModel model;
	private Shape shapeToManipulate;
	private CommandBringToFront commandBringToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToManipulate = new Rectangle(new Point(10, 20), 10, 20, Color.white, Color.black);
		model.add(shapeToManipulate);
	}

	@Test
	public void testExecuteToFrontCommandZeroIndex() {
		commandBringToFront = new CommandBringToFront(model, shapeToManipulate);

		commandBringToFront.executeCommand();

		int expectedIndexOfFirstElement = 0;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testExecuteToFrontCommandLastAddedShape() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandBringToFront = new CommandBringToFront(model, lastAddedShape);

		commandBringToFront.executeCommand();

		int expectedIndexOfFirstElement = 0;
		int expectedIndexOfLastElement = 1;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElement, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testExecutedExecuteToFrontCommand() {
		Point secondAddedShape = new Point(15, 25);
		model.add(secondAddedShape);
		Point lastAddedShape = new Point(35, 45);
		model.add(lastAddedShape);

		commandBringToFront = new CommandBringToFront(model, shapeToManipulate);

		commandBringToFront.executeCommand();

		int expectedIndexOfFirstElementAfterExecution = 2;
		int expectedIndexOfSecondElementAfterExecution = 0;
		int expectedIndexOfLastElementAfterExecution = 1;
		assertEquals(expectedIndexOfFirstElementAfterExecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfSecondElementAfterExecution, model.getIndexOfShape(secondAddedShape));
		assertEquals(expectedIndexOfLastElementAfterExecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testUnexecuteToFrontCommandZeroIndex() {
		commandBringToFront = new CommandBringToFront(model, shapeToManipulate);

		commandBringToFront.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testUnexecuteToFrontCommand() {
		Point secondAddedShape = new Point(15, 25);
		model.add(secondAddedShape);
		Point lastAddedShape = new Point(35, 45);
		model.add(lastAddedShape);

		commandBringToFront = new CommandBringToFront(model, shapeToManipulate);

		commandBringToFront.executeCommand();
		commandBringToFront.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		int expectedIndexOfSecondElementAfterUnexecution = 1;
		int expectedIndexOfLastElementAfterUnexecution = 2;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfSecondElementAfterUnexecution, model.getIndexOfShape(secondAddedShape));
		assertEquals(expectedIndexOfLastElementAfterUnexecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testToString() {
		commandBringToFront = new CommandBringToFront(model, shapeToManipulate);

		String expectedStringValue = "Bring To Front: Rectangle: UpperLeftPoint= (10,20), Height=10, Width=20, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, commandBringToFront.commandToString());
	}
}
