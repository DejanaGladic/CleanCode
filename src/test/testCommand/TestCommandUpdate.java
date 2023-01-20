package test.testCommand;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import commands.CommandUpdateShape;
import shapes.Point;

public class TestCommandUpdate {

	private Point oldState;
	private Point newState;
	private Point saveOldStatePoint;
	private CommandUpdateShape commandUpdatePoint;

	@Before
	public void setUp() {
		oldState = new Point(10, 20, Color.BLACK, true);
		newState = new Point(20, 30, Color.BLACK, true);
		commandUpdatePoint = new CommandUpdateShape(oldState, newState);
	}

	@Test
	public void testExecuteUpdatePointCommandExpectTrue() {
		commandUpdatePoint.executeCommand();
		assertTrue(oldState.equals(newState));
	}

	@Test
	public void testUnexecuteUpdatePointCommandExpectTrue() {
		commandUpdatePoint.executeCommand();
		commandUpdatePoint.unexecuteCommand();
		saveOldStatePoint = oldState.clone();
		assertTrue(oldState.equals(saveOldStatePoint));
	}

	@Test
	public void testcommandToString() {
		String expectedStringValue = "Updated shape: ---> Point: (10, 20), Color: (-16777216) to Point: (20, 30), Color: (-16777216)";
		commandUpdatePoint.executeCommand();
		assertEquals(expectedStringValue, commandUpdatePoint.commandToString());
	}

}
