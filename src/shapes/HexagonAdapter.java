package shapes;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.*;

public class HexagonAdapter extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter() {

	}

	public HexagonAdapter(Point center, int radius, Color insideColor, Color borderColor) {
		this.hexagon = new Hexagon(center.getXCoordinate(), center.getYCoordinate(), radius);
		this.hexagon.setAreaColor(insideColor);
		this.hexagon.setBorderColor(borderColor);
	}

	public HexagonAdapter(Point center, int radius, Color insideColor, Color borderColor, boolean selected) {
		this(center, radius, insideColor, borderColor);
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public Shape clone() {
		HexagonAdapter clonedHexagon = new HexagonAdapter(
				new Point(getXCoordinateOfHexagon(), getYCoordinateOfHexagon()), getRadius(), getInsideColor(),
				getBorderColor(), isSelected());
		return clonedHexagon;
	}

	@Override
	public void setNewValuesForOldState(Shape state) {
		HexagonAdapter newState = (HexagonAdapter) state;
		this.setXCoordinateOfHexagon(newState.getXCoordinateOfHexagon());
		this.setYCoordinateOfHexagon(newState.getYCoordinateOfHexagon());
		this.setRadius(newState.getRadius());
		this.setBorderColor(newState.getBorderColor());
		this.setInsideColor(newState.getInsideColor());
	}

	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter forwardedObjectToHexagon = (HexagonAdapter) obj;
			if (this.getXCoordinateOfHexagon() == forwardedObjectToHexagon.getXCoordinateOfHexagon()
					&& this.getYCoordinateOfHexagon() == forwardedObjectToHexagon.getYCoordinateOfHexagon()
					&& this.getRadius() == forwardedObjectToHexagon.getRadius()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean doesShapeContainPoint(Point p) {
		return hexagon.doesContain(p.getXCoordinate(), p.getYCoordinate());
	}

	@Override
	public void draw(Graphics g) {
		if (getInsideColor() == null) {
			setInsideColor(Color.white);
		}
		if (getBorderColor() == null) {
			setBorderColor(Color.black);
		}
		hexagon.paint(g);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}

	public int getXCoordinateOfHexagon() {
		return hexagon.getX();
	}

	public void setXCoordinateOfHexagon(int x) {
		hexagon.setX(x);
	}

	public int getYCoordinateOfHexagon() {
		return hexagon.getY();
	}

	public void setYCoordinateOfHexagon(int y) {
		hexagon.setY(y);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	@Override
	public Color getInsideColor() {
		return hexagon.getAreaColor();
	}

	@Override
	public void setInsideColor(Color fillColor) {
		hexagon.setAreaColor(fillColor);
	}

	@Override
	public Color getBorderColor() {
		return hexagon.getBorderColor();
	}

	@Override
	public void setBorderColor(Color borderColor) {
		hexagon.setBorderColor(borderColor);
	}

	public String toString() {
		return "Hexagon: x=" + getXCoordinateOfHexagon() + ", y=" + getYCoordinateOfHexagon() + ", " + "radius="
				+ getRadius() + ", " + "borderColor=" + Integer.toString(getBorderColor().getRGB()) + ", innerColor="
				+ Integer.toString(getInsideColor().getRGB());
	}

	@Override
	public void fill(Graphics g) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		throw new UnsupportedOperationException();
	}

}
