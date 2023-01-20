package test.testShapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import hexagon.Hexagon;
import shapes.HexagonAdapter;
import shapes.Line;
import shapes.Point;

public class TestHexagonAdapter {
	private Hexagon hexagon;
	private Graphics graphic;
	private Point center;
	private int radius;
	private Color borderColor;
	private Color insideColor;
	private HexagonAdapter testedHexagon;

	@Before
	public void setUp() {
		graphic = mock(Graphics.class);
		radius = 10;
		borderColor = Color.black;
		insideColor = Color.white;
		center = new Point(10, 20);
		testedHexagon = new HexagonAdapter(center, radius, insideColor, borderColor);
		hexagon = mock(Hexagon.class);
	}

	@Test
	public void testCloneHexagonEqualsValues() {
		assertEquals(testedHexagon.clone(), testedHexagon);
	}

	@Test
	public void testCloneHexagonDifferentReference() {
		assertNotSame(testedHexagon.clone(), testedHexagon);
	}

	@Test
	public void testSetNewValuesForOldStateExpectFalseTrue() {
		HexagonAdapter newStateHexagon = new HexagonAdapter(new Point(20, 30), 10, Color.black, Color.green);
		assertFalse(testedHexagon.equals(newStateHexagon));
		testedHexagon.setNewValuesForOldState(newStateHexagon);
		assertTrue(testedHexagon.equals(newStateHexagon));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		HexagonAdapter newStateHexagon = new HexagonAdapter(new Point(20, 30), 15, Color.green, Color.black);
		testedHexagon.setNewValuesForOldState(newStateHexagon);
		assertNotSame(testedHexagon, newStateHexagon);
	}

	@Test
	public void testEqualsSameHexagonExpectTrue() {
		HexagonAdapter forwardedHexagon = new HexagonAdapter(new Point(10, 20), 10, Color.black, Color.white);
		assertTrue(testedHexagon.equals(forwardedHexagon));
	}

	@Test
	public void testEqualsNotSameHexagonExpectFalse() {
		HexagonAdapter forwardedHexagon = new HexagonAdapter(new Point(10, 30), 15, Color.black, Color.white);
		assertFalse(testedHexagon.equals(forwardedHexagon));
	}

	@Test
	public void testEqualsForwardLineExpectFalse() {
		Line forwardedLine = new Line(new Point(10, 10), new Point(20, 20));
		assertFalse(testedHexagon.equals(forwardedLine));
	}

	@Test
	public void testDoesHexagonContainPointExpectTrue() {
		Point forwardedPoint = new Point(15, 25);
		assertTrue(testedHexagon.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesHexagonContainPointExpectFalse() {
		Point forwardedPoint = new Point(5, 10);
		assertFalse(testedHexagon.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDrawNonSelectedHexagon() {
		HexagonAdapter testedHexagon = new HexagonAdapter(hexagon);
		testedHexagon.draw(graphic);
		verify(hexagon).paint(graphic);
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Hexagon: x=10, y=20, radius=10, borderColor=-16777216, innerColor=-1";
		assertEquals(expectedStringValue, testedHexagon.toString());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testFillNotSupported() {
		testedHexagon.fill(graphic);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDrawSelectionRectangleNotSupported() {
		testedHexagon.drawSelectionRectangle(graphic);
	}

}
