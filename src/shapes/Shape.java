package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Color borderColor;
	private boolean selected;

	public Shape() {

	}

	public Shape(boolean selected) {
		this.selected = selected;
	}

	public abstract boolean doesShapeContainPoint(Point p);

	public abstract void draw(Graphics g);

	public abstract void drawSelectionRectangle(Graphics g);

	public abstract void setNewValuesForOldState(Shape state);

	public abstract Shape clone();

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		if (borderColor == null) {
			this.borderColor = Color.black;
		}
		this.borderColor = borderColor;
	}
}
