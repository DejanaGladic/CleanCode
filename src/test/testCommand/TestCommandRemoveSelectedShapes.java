package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import commands.CommandRemoveSelectedShapes;
import model.DrawingModel;
import shapes.Line;
import shapes.Point;
import shapes.Shape;

public class TestCommandRemoveSelectedShapes {
	private DrawingModel model;
	private ArrayList<Shape> shapesToDelete;
	private CommandRemoveSelectedShapes commandRemoveSelectedShapes;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(new Point(10, 20, Color.black));
		shapesToDelete.add(new Line(new Point(10, 20), new Point(30, 40), Color.black));
		commandRemoveSelectedShapes = new CommandRemoveSelectedShapes(model, shapesToDelete);
	}

	@Test
	public void testExecuteRemoveShapesCommandExpectFalse() {
		commandRemoveSelectedShapes.executeCommand();
		assertFalse(model.doesModelContainSelectedShapes(shapesToDelete));
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Removed:[Point: (10, 20), Color: (-16777216), Line: (10, 20) (30, 40), Color: (-16777216)]";
		assertEquals(expectedStringValue, commandRemoveSelectedShapes.commandToString());
	}
}
