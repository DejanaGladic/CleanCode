package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private static final long serialVersionUID = 1L;
	private int xCoordinate;
	private int yCoordinate;

	public Point() {

	}

	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate);
		setSelected(selected);
	}

	public Point(int xCoordinate, int yCoordinate, Color borderColor) {
		this(xCoordinate, yCoordinate);
		setBorderColor(borderColor);
	}

	public Point(int xCoordinate, int yCoordinate, Color borderColor, boolean selected) {
		this(xCoordinate, yCoordinate);
		setBorderColor(borderColor);
		setSelected(selected);
	}

	public Point clone() {
		Point clonedPoint = new Point(this.getXCoordinate(), this.getYCoordinate(), this.getBorderColor(),
				this.isSelected());
		return clonedPoint;
	}

	public void setNewValuesForOldState(Shape state) {
		Point newState = (Point) state;
		setXCoordinate(newState.getXCoordinate());
		setYCoordinate(newState.getYCoordinate());
		setSelected(newState.isSelected());
		setBorderColor(newState.getBorderColor());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point forwardedObjectToPoint = (Point) obj;
			if (this.xCoordinate == forwardedObjectToPoint.getXCoordinate()
					&& this.yCoordinate == forwardedObjectToPoint.getYCoordinate()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean doesShapeContainPoint(Point p) {
		return this.calculateDistance(p.getXCoordinate(), p.getYCoordinate()) <= 3;
	}

	public double calculateDistance(int xCoordinate, int yCoordinate) {
		double divergenceXCoordinates = this.xCoordinate - xCoordinate;
		double divergenceYCoordinates = this.yCoordinate - yCoordinate;
		double calculatedDistance = Math.sqrt(
				divergenceXCoordinates * divergenceXCoordinates + divergenceYCoordinates * divergenceYCoordinates);
		return calculatedDistance;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawLine(this.xCoordinate - 2, this.yCoordinate, this.xCoordinate + 2, yCoordinate);
		g.drawLine(xCoordinate, this.yCoordinate - 2, xCoordinate, this.yCoordinate + 2);

		if (isSelected()) {
			drawSelectionRectangle(g);
		}
	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.xCoordinate - 3, this.yCoordinate - 3, 6, 6);
	}

	public int getXCoordinate() {
		return this.xCoordinate;
	}

	public void setXCoordinate(int x) {
		this.xCoordinate = x;
	}

	public int getYCoordinate() {
		return this.yCoordinate;
	}

	public void setYCoordinate(int y) {
		this.yCoordinate = y;
	}

	public String toString() {
		return "Point: (" + xCoordinate + ", " + yCoordinate + "), " + "Color: ("
				+ Integer.toString(getBorderColor().getRGB()) + ")";
	}

}
