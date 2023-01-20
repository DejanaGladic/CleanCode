package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, Color borderColor) {
		this(startPoint, endPoint);
		setBorderColor(borderColor);
	}

	public Line(Point startPoint2, Point endPoint2, Color borderColor, boolean selected) {
		this(startPoint2, endPoint2, borderColor);
		setSelected(selected);
	}

	public Line clone() {
		Line clonedLine = new Line(getStartPoint(), getEndPoint(), getBorderColor(), isSelected());
		return clonedLine;
	}

	@Override
	public void setNewValuesForOldState(Shape state) {
		Line newState = (Line) state;
		setStartPoint(newState.getStartPoint());
		setEndPoint(newState.getEndPoint());
		setBorderColor(newState.getBorderColor());
		setSelected(newState.isSelected());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line forwardedObjectToLine = (Line) obj;
			if (this.startPoint.equals(forwardedObjectToLine.getStartPoint())
					&& this.endPoint.equals(forwardedObjectToLine.getEndPoint())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesShapeContainPoint(Point p) {
		if ((startPoint.calculateDistance(p.getXCoordinate(), p.getYCoordinate())
				+ endPoint.calculateDistance(p.getXCoordinate(), p.getYCoordinate())) - length() <= 0.05)
			return true;
		return false;
	}

	public double length() {
		return startPoint.calculateDistance(endPoint.getXCoordinate(), endPoint.getYCoordinate());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawLine(this.getStartPoint().getXCoordinate(), getStartPoint().getYCoordinate(),
				this.getEndPoint().getXCoordinate(), this.getEndPoint().getYCoordinate());

		if (isSelected()) {
			drawSelectionRectangle(g);
		}
	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getStartPoint().getXCoordinate() - 3, getStartPoint().getYCoordinate() - 3, 6, 6);
		g.drawRect(getEndPoint().getXCoordinate() - 3, getEndPoint().getYCoordinate() - 3, 6, 6);
		g.drawRect(middleOfLine().getXCoordinate() - 3, middleOfLine().getYCoordinate() - 3, 6, 6);
	}

	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getXCoordinate() + this.getEndPoint().getXCoordinate()) / 2;
		int middleByY = (this.getStartPoint().getYCoordinate() + this.getEndPoint().getYCoordinate()) / 2;
		Point middlePointOfLine = new Point(middleByX, middleByY);
		return middlePointOfLine;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line: " + "(" + startPoint.getXCoordinate() + ", " + startPoint.getYCoordinate() + ") " + "("
				+ endPoint.getXCoordinate() + ", " + endPoint.getYCoordinate() + "), " + "Color: ("
				+ Integer.toString(getBorderColor().getRGB()) + ")";
	}

}
