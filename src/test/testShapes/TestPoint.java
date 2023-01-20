package test.testShapes;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;
import shapes.Point;

import static org.mockito.Mockito.*;

public class TestPoint {

	private Graphics graphic;
	private int xCoordinate;
	private int yCoordinate;
	private Color borderColor;
	private Point testedPoint;

	@Before
	public void setUp() {
		graphic = mock(Graphics.class);
		xCoordinate = 10;
		yCoordinate = 20;
		borderColor = Color.black;
		testedPoint = new Point(xCoordinate, yCoordinate, borderColor);
	}

	@Test
	public void testClonePointEqualsValues() {
		assertEquals(testedPoint, testedPoint.clone());
	}

	@Test
	public void testClonePointDifferentReference() {
		assertNotSame(testedPoint, testedPoint.clone());
	}

	@Test
	public void testSetNewValuesForOldStateExpectTrue() {
		Point newStatePoint = new Point(30, 40, Color.blue, true);
		assertFalse(testedPoint.equals(newStatePoint));
		testedPoint.setNewValuesForOldState(newStatePoint);
		assertTrue(testedPoint.equals(newStatePoint));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		Point newStatePoint = new Point(30, 40, Color.blue, true);
		testedPoint.setNewValuesForOldState(newStatePoint);
		assertNotSame(testedPoint, newStatePoint);
	}

	@Test
	public void testEqualsSamePointExpectTrue() {
		Point forwardedPoint = new Point(10, 20);
		assertTrue(testedPoint.equals(forwardedPoint));
	}

	@Test
	public void testEqualsNotSamePointExpectFalse() {
		Point forwardedPoint = new Point(15, 25);
		assertFalse(testedPoint.equals(forwardedPoint));
	}

	@Test
	public void testEqualsForwardLineExpectFalse() {
		Line forwardedLine = new Line(new Point(10, 10), new Point(20, 20));
		assertFalse(testedPoint.equals(forwardedLine));
	}

	@Test
	public void testEqualsFalseExpectBothCoordinates() {
		assertFalse(testedPoint.equals(new Point(0, 20)));
		assertFalse(testedPoint.equals(new Point(10, 0)));
	}

	@Test
	public void testDoesPointContainPointExpectTrue() {
		Point forwardedPoint = new Point(11, 22);
		assertTrue(testedPoint.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesPointContainPointExpectFalse() {
		Point forwardedPoint = new Point(20, 30);
		assertFalse(testedPoint.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testCalculateDistance() {
		Point forwardedPoint = new Point(20, 30);
		double expectedDistanceValue = Math.sqrt(200);
		assertEquals(expectedDistanceValue,
				testedPoint.calculateDistance(forwardedPoint.getXCoordinate(), forwardedPoint.getYCoordinate()), 0.001);
	}

	@Test
	public void testDrawNonSelectedPoint() {
		testedPoint.draw(graphic);

		verify(graphic).setColor(borderColor);
		verify(graphic).drawLine(this.xCoordinate - 2, this.yCoordinate, this.xCoordinate + 2, yCoordinate);
		verify(graphic).drawLine(this.xCoordinate, this.yCoordinate - 2, this.xCoordinate, this.yCoordinate + 2);
	}

	@Test
	public void testDrawSelectionRectangleForPoint() {
		testedPoint.drawSelectionRectangle(graphic);

		verify(graphic).setColor(Color.BLUE);
		verify(graphic).drawRect(this.xCoordinate - 3, this.yCoordinate - 3, 6, 6);
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Point: (10, 20), Color: (-16777216)";
		assertEquals(expectedStringValue, testedPoint.toString());
	}

}
