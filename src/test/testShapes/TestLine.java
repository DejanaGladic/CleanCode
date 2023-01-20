package test.testShapes;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;
import shapes.Point;

import static org.mockito.Mockito.*;

public class TestLine {

	private Graphics graphic;
	private Point startPointOfLine;
	private Point endPointOfLine;
	private Color borderColor;
	private Line testedLine;

	@Before
	public void setUp() {
		graphic = mock(Graphics.class);
		startPointOfLine = new Point(10, 20);
		endPointOfLine = new Point(30, 40);
		borderColor = Color.black;
		testedLine = new Line(startPointOfLine, endPointOfLine, borderColor);
	}

	@Test
	public void testCloneLineEqualsValues() {
		assertEquals(testedLine, testedLine.clone());
	}

	@Test
	public void testCloneLineDifferentReference() {
		assertNotSame(testedLine.clone(), testedLine);
	}

	@Test
	public void testSetNewValuesForOldStateExpectTrue() {
		Line newStateLine = new Line(new Point(30, 40), new Point(40, 50), Color.blue, true);
		assertFalse(testedLine.equals(newStateLine));
		testedLine.setNewValuesForOldState(newStateLine);
		assertTrue(testedLine.equals(newStateLine));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		Line newStateLine = new Line(new Point(30, 40), new Point(40, 50), Color.blue, true);
		testedLine.setNewValuesForOldState(newStateLine);
		assertNotSame(testedLine, newStateLine);
	}

	@Test
	public void testEqualsSameLineExpectTrue() {
		Line forwardedLine = new Line(new Point(10, 20), new Point(30, 40));
		assertTrue(testedLine.equals(forwardedLine));
	}

	@Test
	public void testEqualsNotSameLineExpectFalse() {
		Line forwardedLine = new Line(new Point(50, 40), new Point(20, 15));
		assertFalse(testedLine.equals(forwardedLine));
	}

	@Test
	public void testEqualsForwardPointExpectFalse() {
		Point forwardedPoint = new Point(10, 20);
		assertFalse(testedLine.equals(forwardedPoint));
	}

	@Test
	public void testEqualsFalseExpectBothStartAndEndPointEquals() {
		assertFalse(testedLine.equals(new Line(new Point(0, 20), new Point(30, 40))));
		assertFalse(testedLine.equals(new Line(new Point(10, 20), new Point(40, 50))));
	}

	@Test
	public void testDoesLineContainPointExpectFalse() {
		Point forwardedPoint = new Point(50, 60);
		assertFalse(testedLine.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesLineContainPointExpectTrue() {
		Point forwardedPoint = new Point(10, 20);
		assertTrue(testedLine.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testLengthOfLine() {
		assertEquals(Math.sqrt(800), testedLine.length(), 0.001);
	}

	@Test
	public void testDrawNonSelectedLine() {
		testedLine.draw(graphic);

		verify(graphic).setColor(borderColor);
		verify(graphic).drawLine(startPointOfLine.getXCoordinate(), startPointOfLine.getYCoordinate(),
				endPointOfLine.getXCoordinate(), endPointOfLine.getYCoordinate());
	}

	@Test
	public void testDrawSelectionRectangleForLine() {
		testedLine.drawSelectionRectangle(graphic);

		verify(graphic).setColor(Color.BLUE);
		verify(graphic).drawRect(startPointOfLine.getXCoordinate() - 3, startPointOfLine.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(endPointOfLine.getXCoordinate() - 3, endPointOfLine.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(testedLine.middleOfLine().getXCoordinate() - 3,
				testedLine.middleOfLine().getYCoordinate() - 3, 6, 6);
	}

	@Test
	public void testMiddleOfLineExpectTrue() {
		Point expectedMiddlePoint = new Point(20, 30);
		assertTrue(expectedMiddlePoint.equals(testedLine.middleOfLine()));
	}

	@Test
	public void testMiddleOfLineExpectFalse() {
		Point expectedMiddlePoint = new Point(60, 70);
		assertFalse(expectedMiddlePoint.equals(testedLine.middleOfLine()));
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Line: (10, 20) (30, 40), Color: (-16777216)";
		assertEquals(expectedStringValue, testedLine.toString());
	}

}
