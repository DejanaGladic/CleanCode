package test.testShapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

public class TestRectangle {

	private Graphics graphic;
	private Point upperLeftPointOfRectangle;
	private int height;
	private int width;
	private Color borderColor;
	private Color insideColor;
	private Rectangle testedRectangle;

	@Before
	public void setUp() {
		graphic = mock(Graphics.class);
		height = 10;
		width = 20;
		borderColor = Color.black;
		insideColor = Color.white;
		upperLeftPointOfRectangle = new Point(10, 20);
		testedRectangle = new Rectangle(upperLeftPointOfRectangle, height, width, insideColor, borderColor);
	}

	@Test
	public void testCloneRectangleEqualsValues() {
		assertEquals(testedRectangle.clone(), testedRectangle);
	}

	@Test
	public void testCloneRectangleDifferentReference() {
		assertNotSame(testedRectangle.clone(), testedRectangle);
	}

	@Test
	public void testSetNewValuesForOldStateExpectFalseTrue() {
		Rectangle newStateRectangle = new Rectangle(new Point(20, 30), 20, 10, Color.black, Color.white);
		assertFalse(testedRectangle.equals(newStateRectangle));
		testedRectangle.setNewValuesForOldState(newStateRectangle);
		assertTrue(testedRectangle.equals(newStateRectangle));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		Rectangle newStateRectangle = new Rectangle(new Point(30, 40), 35, 15, Color.black, Color.white);
		testedRectangle.setNewValuesForOldState(newStateRectangle);
		assertNotSame(testedRectangle, newStateRectangle);
	}

	@Test
	public void testEqualsSameRectangleExpectTrue() {
		Rectangle forwardedRectangle = new Rectangle(new Point(10, 20), 10, 20, Color.black, Color.white);
		assertTrue(testedRectangle.equals(forwardedRectangle));
	}

	@Test
	public void testEqualsNotSameRectangleExpectFalse() {
		Rectangle forwardedRectangle = new Rectangle(new Point(30, 40), 35, 15, Color.black, Color.white);
		assertFalse(testedRectangle.equals(forwardedRectangle));
	}

	@Test
	public void testEqualsForwardLineExpectFalse() {
		Line forwardedLine = new Line(new Point(10, 10), new Point(20, 20));
		assertFalse(testedRectangle.equals(forwardedLine));
	}

	@Test
	public void testDoesRectangleContainPointExpectTrue() {
		Point forwardedPoint = new Point(15, 25);
		assertTrue(testedRectangle.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesRectangleContainPointExpectFalse() {
		Point forwardedPoint = new Point(5, 10);
		assertFalse(testedRectangle.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDrawNonSelectedRectangle() {
		testedRectangle.draw(graphic);

		testedRectangle.fill(graphic);
		verify(graphic).setColor(borderColor);
		verify(graphic).drawRect(upperLeftPointOfRectangle.getXCoordinate(), upperLeftPointOfRectangle.getYCoordinate(),
				width, height);
	}

	@Test
	public void testFillRectangle() {
		testedRectangle.fill(graphic);

		verify(graphic).setColor(insideColor);
		verify(graphic).fillRect(upperLeftPointOfRectangle.getXCoordinate() + 1,
				upperLeftPointOfRectangle.getYCoordinate() + 1, width - 1, height - 1);
	}

	@Test
	public void testDrawSelectionRectangleForRectangle() {
		testedRectangle.drawSelectionRectangle(graphic);

		verify(graphic).setColor(Color.blue);
		verify(graphic).drawRect(upperLeftPointOfRectangle.getXCoordinate() - 3,
				upperLeftPointOfRectangle.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(upperLeftPointOfRectangle.getXCoordinate() - 3 + width,
				upperLeftPointOfRectangle.getYCoordinate() - 3, 6, 6);
		verify(graphic).drawRect(upperLeftPointOfRectangle.getXCoordinate() - 3,
				upperLeftPointOfRectangle.getYCoordinate() - 3 + height, 6, 6);
		verify(graphic).drawRect(upperLeftPointOfRectangle.getXCoordinate() + width - 3,
				upperLeftPointOfRectangle.getYCoordinate() + height - 3, 6, 6);
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Rectangle: UpperLeftPoint= (10,20), Height=10, Width=20, OuterColor=-16777216, InnerColor=-1";
		assertEquals(expectedStringValue, testedRectangle.toString());
	}

}
