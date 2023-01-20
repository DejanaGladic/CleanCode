package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, Color insideColor, Color borderColor) {
		this(upperLeftPoint, height, width);
		setInsideColor(insideColor);
		setBorderColor(borderColor);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, Color insideColor, Color borderColor,
			boolean selected) {
		this(upperLeftPoint, height, width, insideColor, borderColor);
		setSelected(selected);
	}

	@Override
	public Shape clone() {
		Rectangle clonedRectangle = new Rectangle(this.upperLeftPoint, this.height, this.width, this.getInsideColor(),
				this.getBorderColor(), this.isSelected());
		return clonedRectangle;
	}

	@Override
	public void setNewValuesForOldState(Shape state) {
		Rectangle newState = (Rectangle) state;
		setUpperLeftPoint(newState.getUpperLeftPoint());
		setHeight(newState.getHeight());
		setWidth(newState.getWidth());
		setInsideColor(newState.getInsideColor());
		setBorderColor(newState.getBorderColor());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle forwardedObjectToRectangle = (Rectangle) obj;
			if (this.upperLeftPoint.equals(forwardedObjectToRectangle.getUpperLeftPoint())
					&& this.height == forwardedObjectToRectangle.getHeight()
					&& this.width == forwardedObjectToRectangle.getWidth()) {
				return true;
			}
		}
		return false;
	}

	public boolean doesShapeContainPoint(Point p) {
		if (this.getUpperLeftPoint().getXCoordinate() <= p.getXCoordinate()
				&& p.getXCoordinate() <= this.getUpperLeftPoint().getXCoordinate() + width
				&& this.getUpperLeftPoint().getYCoordinate() <= p.getYCoordinate()
				&& p.getYCoordinate() <= this.getUpperLeftPoint().getYCoordinate() + height) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		fill(g);
		g.setColor(getBorderColor());
		g.drawRect(this.getUpperLeftPoint().getXCoordinate(), this.getUpperLeftPoint().getYCoordinate(),
				this.getWidth(), this.height);
		if (isSelected()) {
			drawSelectionRectangle(g);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillRect(getUpperLeftPoint().getXCoordinate() + 1, getUpperLeftPoint().getYCoordinate() + 1, getWidth() - 1,
				getHeight() - 1);
	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getUpperLeftPoint().getXCoordinate() - 3, getUpperLeftPoint().getYCoordinate() - 3, 6, 6);
		g.drawRect(this.getUpperLeftPoint().getXCoordinate() - 3 + getWidth(),
				this.getUpperLeftPoint().getYCoordinate() - 3, 6, 6);
		g.drawRect(this.getUpperLeftPoint().getXCoordinate() - 3,
				this.getUpperLeftPoint().getYCoordinate() - 3 + getHeight(), 6, 6);
		g.drawRect(this.getUpperLeftPoint().getXCoordinate() + getWidth() - 3,
				this.getUpperLeftPoint().getYCoordinate() + getHeight() - 3, 6, 6);
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (width >= 0)
			this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (height >= 0)
			this.height = height;
	}

	public String toString() {
		return "Rectangle: " + "UpperLeftPoint= (" + upperLeftPoint.getXCoordinate() + ","
				+ upperLeftPoint.getYCoordinate() + "), " + "Height=" + height + ", " + "Width=" + width + ", "
				+ "OuterColor=" + Integer.toString(this.getBorderColor().getRGB()) + ", " + "InnerColor="
				+ Integer.toString(getInsideColor().getRGB());
	}

}
