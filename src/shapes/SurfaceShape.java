package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color insideColor;

	public abstract void fill(Graphics g);

	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		if (insideColor == null) {
			this.insideColor = Color.white;
		} else {
			this.insideColor = insideColor;
		}
	}

}
