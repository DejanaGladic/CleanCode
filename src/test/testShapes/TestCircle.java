package test.testShapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Circle;
import shapes.Line;
import shapes.Point;

public class TestCircle {

	private Graphics graphic;
	private Point center;
	private int radius;
	private Color borderColor;
	private Color insideColor;
	private Circle testedCircle;

	@Before
	public void setUp() {
		graphic = mock(Graphics.class);
		radius = 10;
		borderColor = Color.black;
		insideColor = Color.white;
		center = new Point(10, 20);
		testedCircle = new Circle(center, radius, insideColor, borderColor);
	}

	@Test
	public void testCloneCircleEqualsValues() {
		assertEquals(testedCircle.clone(), testedCircle);
	}

	@Test
	public void testCloneCircleDifferentReference() {
		assertNotSame(testedCircle.clone(), testedCircle);
	}

	@Test
	public void testSetNewValuesForOldStateExpectFalseTrue() {
		Circle newStateCircle = new Circle(new Point(20, 30), 20, Color.black, Color.white);
		assertFalse(testedCircle.equals(newStateCircle));
		testedCircle.setNewValuesForOldState(newStateCircle);
		assertTrue(testedCircle.equals(newStateCircle));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		Circle newStateCircle = new Circle(new Point(30, 40), 15, Color.black, Color.white);
		testedCircle.setNewValuesForOldState(newStateCircle);
		assertNotSame(testedCircle, newStateCircle);
	}

	@Test
	public void testEqualsSameCircleExpectTrue() {
		Circle forwardedCircle = new Circle(new Point(10, 20), 10, Color.black, Color.white);
		assertTrue(testedCircle.equals(forwardedCircle));
	}

	@Test
	public void testEqualsNotSameCircleExpectFalse() {
		Circle forwardedCircle = new Circle(new Point(30, 40), 15, Color.black, Color.white);
		assertFalse(testedCircle.equals(forwardedCircle));
	}

	@Test
	public void testEqualsForwardLineExpectFalse() {
		Line forwardedLine = new Line(new Point(10, 10), new Point(20, 20));
		assertFalse(testedCircle.equals(forwardedLine));
	}

	@Test
	public void testDoesCircleContainPointExpectTrue() {
		Point forwardedPoint = new Point(15, 25);
		assertTrue(testedCircle.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesCircleContainPointExpectFalse() {
		Point forwardedPoint = new Point(5, 10);
		assertFalse(testedCircle.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDrawNonSelectedCircle() {
		testedCircle.draw(graphic);

		testedCircle.fill(graphic);

		verify(graphic).setColor(borderColor);
		verify(graphic).drawOval(center.getXCoordinate() - radius, center.getYCoordinate() - radius, radius * 2,
				radius * 2);
	}

	@Test
	public void testFillCircle() {
		testedCircle.fill(graphic);

		verify(graphic).setColor(insideColor);
		verify(graphic).fillOval(center.getXCoordinate() + 1 - radius, center.getYCoordinate() + 1 - radius,
				(radius - 1) * 2, (radius - 1) * 2);
	}

	@Test
	public void testDrawSelectionCircle() {
		testedCircle.drawSelectionRectangle(graphic);

		verify(graphic).setColor(Color.BLUE);
		verify(graphic).drawRect(center.getXCoordinate() - 3, center.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(center.getXCoordinate() + radius - 3, center.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(center.getXCoordinate() - radius - 3, center.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(center.getXCoordinate() - 3, center.getYCoordinate() + radius - 3, 6, 6);
		verify(graphic).drawRect(center.getXCoordinate() - 3, center.getYCoordinate() - radius - 3, 6, 6);
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Circle: Center= (10,20), Radius=10, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, testedCircle.toString());
	}
}