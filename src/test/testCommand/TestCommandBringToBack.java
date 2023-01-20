package test.testCommand;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandBringToBack;
import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class TestCommandBringToBack {
	private DrawingModel model;
	private Shape shapeToManipulate;
	private CommandBringToBack commandBringToBack;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapeToManipulate = new Rectangle(new Point(10, 20), 10, 20, Color.white, Color.black);
		model.add(shapeToManipulate);
	}

	@Test
	public void testExecuteToBackCommandZeroIndex() {
		commandBringToBack = new CommandBringToBack(model, shapeToManipulate);

		commandBringToBack.executeCommand();

		int expectedIndexOfFirstElement = 0;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testExecuteToBackCommandFirstAddedShape() {
		Point lastAddedShape = new Point(15, 25);
		model.add(lastAddedShape);

		commandBringToBack = new CommandBringToBack(model, shapeToManipulate);

		commandBringToBack.executeCommand();

		int expectedIndexOfFirstElement = 0;
		int expectedIndexOfLastElement = 1;
		assertEquals(expectedIndexOfFirstElement, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfLastElement, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testExecutedExecuteToBackCommand() {
		Point secondAddedShape = new Point(15, 25);
		model.add(secondAddedShape);
		Point lastAddedShape = new Point(35, 45);
		model.add(lastAddedShape);

		commandBringToBack = new CommandBringToBack(model, lastAddedShape);

		commandBringToBack.executeCommand();

		int expectedIndexOfFirstElementAfterExecution = 1;
		int expectedIndexOfSecondElementAfterExecution = 2;
		int expectedIndexOfLastElementAfterExecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterExecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfSecondElementAfterExecution, model.getIndexOfShape(secondAddedShape));
		assertEquals(expectedIndexOfLastElementAfterExecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testUnexecuteToBackCommandZeroIndex() {
		commandBringToBack = new CommandBringToBack(model, shapeToManipulate);

		commandBringToBack.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
	}

	@Test
	public void testUnexecuteToBackCommand() {
		Point secondAddedShape = new Point(15, 25);
		model.add(secondAddedShape);
		Point lastAddedShape = new Point(35, 45);
		model.add(lastAddedShape);

		commandBringToBack = new CommandBringToBack(model, shapeToManipulate);

		commandBringToBack.executeCommand();
		commandBringToBack.unexecuteCommand();

		int expectedIndexOfFirstElementAfterUnexecution = 0;
		int expectedIndexOfSecondElementAfterUnexecution = 1;
		int expectedIndexOfLastElementAfterUnexecution = 2;
		assertEquals(expectedIndexOfFirstElementAfterUnexecution, model.getIndexOfShape(shapeToManipulate));
		assertEquals(expectedIndexOfSecondElementAfterUnexecution, model.getIndexOfShape(secondAddedShape));
		assertEquals(expectedIndexOfLastElementAfterUnexecution, model.getIndexOfShape(lastAddedShape));
	}

	@Test
	public void testToString() {
		commandBringToBack = new CommandBringToBack(model, shapeToManipulate);

		String expectedStringValue = "Bring To Back: Rectangle: UpperLeftPoint= (10,20), Height=10, Width=20, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, commandBringToBack.commandToString());
	}
}
