package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import commands.CommandAddShape;
import commands.CommandUndoRedo;
import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;

public class TestCommandUndoRedo {
	private DrawingModel model;
	private Command firstCommandExecuted;
	private CommandUndoRedo commandUndoRedo = new CommandUndoRedo();

	@Before
	public void setUp() {
		model = new DrawingModel();
		Rectangle rectangleToManipulate = new Rectangle(new Point(30, 40), 35, 15, Color.black, Color.white);
		firstCommandExecuted = new CommandAddShape(model, rectangleToManipulate);
	}

	@Test
	public void testCommandUndoEmptyCommandListExpectTrue() {
		commandUndoRedo.unexecuteCommand();

		assertTrue(commandUndoRedo.getCommands().isEmpty());
		assertTrue(commandUndoRedo.getRedoCommands().isEmpty());
	}

	@Test
	public void testCommandUndo() {
		commandUndoRedo.getCommands().add(firstCommandExecuted);

		assertEquals(commandUndoRedo.getCommands().size(), 1);
		assertTrue(commandUndoRedo.getRedoCommands().isEmpty());

		commandUndoRedo.unexecuteCommand();

		assertEquals(commandUndoRedo.getCommands().size(), 0);
		assertEquals(commandUndoRedo.getRedoCommands().size(), 1);
	}

	@Test
	public void testCommandRedoEmptyRedoCommandListExpectTrue() {
		commandUndoRedo.executeCommand();

		assertTrue(commandUndoRedo.getCommands().isEmpty());
		assertTrue(commandUndoRedo.getRedoCommands().isEmpty());
	}

	@Test
	public void testCommandRedo() {
		commandUndoRedo.getCommands().add(firstCommandExecuted);

		commandUndoRedo.unexecuteCommand();

		assertFalse(commandUndoRedo.getRedoCommands().isEmpty());
		assertEquals(commandUndoRedo.getCommands().size(), 0);

		commandUndoRedo.executeCommand();

		assertTrue(commandUndoRedo.getRedoCommands().isEmpty());
		assertEquals(commandUndoRedo.getCommands().size(), 1);
	}
}
