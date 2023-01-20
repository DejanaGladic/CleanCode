package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	public Circle(Point center, int radius, Color insideColor, Color borderColor) {
		this.center = center;
		this.radius = radius;
		setInsideColor(insideColor);
		setBorderColor(borderColor);
	}

	public Circle(Point center, int radius, Color insideColor, Color borderColor, boolean selected) {
		this(center, radius, insideColor, borderColor);
		setSelected(selected);
	}

	@Override
	public Shape clone() {
		Circle clonedCircle = new Circle(this.center, this.radius, this.getInsideColor(), this.getBorderColor(),
				this.isSelected());
		return clonedCircle;
	}

	@Override
	public void setNewValuesForOldState(Shape state) {
		Circle newState = (Circle) state;
		setCenter(newState.getCenter());
		setRadius(newState.getRadius());
		setBorderColor(newState.getBorderColor());
		setInsideColor(newState.getInsideColor());
		setSelected(newState.isSelected());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle forwardedObjectToCircle = (Circle) obj;
			if (this.center.equals(forwardedObjectToCircle.getCenter())
					&& this.radius == forwardedObjectToCircle.getRadius())
				return true;
		}
		return false;
	}

	public boolean doesShapeContainPoint(Point p) {
		return center.calculateDistance(p.getXCoordinate(), p.getYCoordinate()) <= radius;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(this.getCenter().getXCoordinate() - this.radius, getCenter().getYCoordinate() - getRadius(),
				this.getRadius() * 2, this.getRadius() * 2);

		fill(g);
		if (isSelected()) {
			drawSelectionRectangle(g);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillOval(this.getCenter().getXCoordinate() + 1 - this.radius, getCenter().getYCoordinate() + 1 - getRadius(),
				(this.getRadius() - 1) * 2, (this.getRadius() - 1) * 2);
	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3, 6, 6);
		g.drawRect(getCenter().getXCoordinate() + getRadius() - 3, getCenter().getYCoordinate() - 3, 6, 6);
		g.drawRect(getCenter().getXCoordinate() - getRadius() - 3, getCenter().getYCoordinate() - 3, 6, 6);
		g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() + getRadius() - 3, 6, 6);
		g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - getRadius() - 3, 6, 6);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String toString() {
		return "Circle: " + "Center= (" + center.getXCoordinate() + "," + center.getYCoordinate() + "), " + "Radius="
				+ radius + ", " + "OuterColor=" + Integer.toString(getBorderColor().getRGB()) + ", " + "InnerColor="
				+ Integer.toString(getInsideColor().getRGB());
	}

}
