package test.testShapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import org.junit.Before;
import org.junit.Test;

import shapes.Circle;
import shapes.Donut;
import shapes.Point;

public class TestDonut {

	private Graphics2D graphics2d;
	private Point center;
	private int outsideRadius;
	private int innerRadius;
	private Color borderColor;
	private Color insideColor;
	private Donut testedDonut;

	@Before
	public void setUp() {
		graphics2d = mock(Graphics2D.class);
		center = new Point(10, 20);
		innerRadius = 10;
		outsideRadius = 30;
		borderColor = Color.black;
		insideColor = Color.white;
		testedDonut = new Donut(center, outsideRadius, innerRadius, borderColor, insideColor);

		testedDonut.createDonutFromCircles();
	}

	@Test
	public void testCloneDonutEqualsValues() {
		assertEquals(testedDonut.clone(), testedDonut);
	}

	@Test
	public void testClonePointDifferentReference() {
		assertNotSame(testedDonut.clone(), testedDonut);
	}

	@Test
	public void testSetNewValuesForOldStateExpectFalseTrue() {
		Donut newStateDonut = new Donut(new Point(10, 30), 20, 10, Color.black, Color.white);
		assertFalse(testedDonut.equals(newStateDonut));
		testedDonut.setNewValuesForOldState(newStateDonut);
		assertTrue(testedDonut.equals(newStateDonut));
	}

	@Test
	public void testSetNewValuesForOldStateDifferenteReference() {
		Donut newStateDonut = new Donut(new Point(20, 30), 10, 25, Color.black, Color.white);
		testedDonut.setNewValuesForOldState(newStateDonut);
		assertNotSame(testedDonut, newStateDonut);
	}

	@Test
	public void testEqualsSameDonutExpectTrue() {
		Donut forwardedDonut = new Donut(new Point(10, 20), 30, 10, Color.black, Color.white);
		assertTrue(testedDonut.equals(forwardedDonut));
	}

	@Test
	public void testEqualsNotSameDonutExpectFalse() {
		Donut forwardedDonut = new Donut(new Point(10, 20), 10, 10, Color.black, Color.white);
		assertFalse(testedDonut.equals(forwardedDonut));
	}

	@Test
	public void testEqualsForwardCircleExpectFalse() {
		Circle forwardedCircle = new Circle(new Point(30, 40), 15, Color.black, Color.white);
		assertFalse(testedDonut.equals(forwardedCircle));
	}

	@Test
	public void testDoesDonutContainPointExpectTrue() {
		Point forwardedPoint = new Point(20, 30);
		assertTrue(testedDonut.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDoesDonutContainPointExpectFalse() {
		Point forwardedPoint = new Point(50, 100);
		assertFalse(testedDonut.doesShapeContainPoint(forwardedPoint));
	}

	@Test
	public void testDrawNonSelectedDonut() {
		testedDonut.createDonutFromCircles();
		testedDonut.draw(graphics2d);

		verify(graphics2d).setColor(borderColor);
		verify(graphics2d).draw(testedDonut.getDonutArea());

		testedDonut.fill(graphics2d);
	}

	@Test
	public void testCreateDonutFromCirclesExpectTrue() {
		int outsideCircleXCoordinate = testedDonut.getCenter().getXCoordinate() - testedDonut.getRadius();
		int outsideCircleYCoordinate = testedDonut.getCenter().getYCoordinate() - testedDonut.getRadius();
		Ellipse2D outsideCircle = new Ellipse2D.Double(outsideCircleXCoordinate, outsideCircleYCoordinate,
				testedDonut.getRadius() * 2, testedDonut.getRadius() * 2);

		int innerCircleXCoordinate = testedDonut.getCenter().getXCoordinate() - testedDonut.getInnerRadius();
		int innerCircleYCoordinate = testedDonut.getCenter().getYCoordinate() - testedDonut.getInnerRadius();
		Ellipse2D innerCircle = new Ellipse2D.Double(innerCircleXCoordinate, innerCircleYCoordinate,
				testedDonut.getInnerRadius() * 2, testedDonut.getInnerRadius() * 2);

		Area donutAreaForTest = new Area(outsideCircle);
		Area innerCircleAreaForTest = new Area(innerCircle);
		donutAreaForTest.subtract(innerCircleAreaForTest);

		testedDonut.createDonutFromCircles();

		assertTrue(donutAreaForTest.equals(testedDonut.getDonutArea()));
	}

	public void testDrawSelectionRectangle() {
		testedDonut.drawSelectionRectangle(graphics2d);

		verify(graphics2d).setColor(Color.BLUE);

		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() + testedDonut.getInnerRadius() - 3,
				testedDonut.getCenter().getYCoordinate() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - testedDonut.getInnerRadius() - 3,
				testedDonut.getCenter().getYCoordinate() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - 3,
				testedDonut.getCenter().getYCoordinate() + testedDonut.getInnerRadius() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - 3,
				testedDonut.getCenter().getYCoordinate() - testedDonut.getInnerRadius() - 3, 6, 6);

		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() + testedDonut.getRadius() - 3,
				testedDonut.getCenter().getYCoordinate() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - testedDonut.getRadius() - 3,
				testedDonut.getCenter().getYCoordinate() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - 3,
				testedDonut.getCenter().getYCoordinate() + testedDonut.getRadius() - 3, 6, 6);
		verify(graphics2d).drawRect(testedDonut.getCenter().getXCoordinate() - 3,
				testedDonut.getCenter().getYCoordinate() - testedDonut.getRadius() - 3, 6, 6);
	}

	@Test
	public void testToString() {
		String expectedStringValue = "Donut: Center= (10,20), Radius=30, OuterColor=-16777216, InnerColor=-1, InnerRadius=10";
		assertEquals(expectedStringValue, testedDonut.toString());
	}

}
