package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private Area donutArea;
	private Graphics2D graphics2d;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		setInnerRadius(innerRadius);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, Color borderColor, Color insideColor) {
		super(center, radius);
		this.innerRadius = innerRadius;
		setInsideColor(insideColor);
		setBorderColor(borderColor);

	}

	public Donut(Point center, int radius, int innerRadius, Color borderColor, Color insideColor, boolean selected) {
		this(center, radius, innerRadius, borderColor, insideColor);
		setSelected(selected);
	}
	
	@Override
	public Shape clone() {
		Donut clonedDonut = new Donut(getCenter(), getRadius(), innerRadius, getBorderColor(), getInsideColor(),
				isSelected());
		return clonedDonut;
	}

	@Override
	public void setNewValuesForOldState(Shape state) {
		Donut newState = (Donut) state;
		setCenter(newState.getCenter());
		setRadius(newState.getRadius());
		setInnerRadius(newState.getInnerRadius());
		setBorderColor(newState.getBorderColor());
		setInsideColor(newState.getInsideColor());
		setSelected(newState.isSelected());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut forwardedObjectToDonut = (Donut) obj;
			if (super.equals(forwardedObjectToDonut) && this.innerRadius == forwardedObjectToDonut.getInnerRadius())
				return true;
		}
		return false;
	}

	public boolean doesShapeContainPoint(Point p) {
		double distanceFromCenter = this.getCenter().calculateDistance(p.getXCoordinate(), p.getYCoordinate());
		return distanceFromCenter > innerRadius && super.doesShapeContainPoint(p);
	}

	public void draw(Graphics g) {
		createDonutFromCircles();

		g.setColor(getBorderColor());

		graphics2d = (Graphics2D) g;
		graphics2d.draw(donutArea);
		fill(g);

		if (isSelected()) {
			drawSelectionRectangle(g);
		}
	}

	public void createDonutFromCircles() {
		int outsideCircleXCoordinate = this.getCenter().getXCoordinate() - this.getRadius();
		int outsideCircleYCoordinate = this.getCenter().getYCoordinate() - this.getRadius();
		Ellipse2D outsideCircle = new Ellipse2D.Double(outsideCircleXCoordinate, outsideCircleYCoordinate,
				this.getRadius() * 2, this.getRadius() * 2);

		int innerCircleXCoordinate = this.getCenter().getXCoordinate() - this.getInnerRadius();
		int innerCircleYCoordinate = this.getCenter().getYCoordinate() - this.getInnerRadius();
		Ellipse2D innerCircle = new Ellipse2D.Double(innerCircleXCoordinate, innerCircleYCoordinate,
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);

		donutArea = new Area(outsideCircle);
		Area innerCircleArea = new Area(innerCircle);
		donutArea.subtract(innerCircleArea);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		graphics2d.fill(getDonutArea());
	}

	@Override
	public void drawSelectionRectangle(Graphics g) {
		g.setColor(Color.BLUE);

		g.drawRect(this.getCenter().getXCoordinate() + getInnerRadius() - 3, this.getCenter().getYCoordinate() - 3, 6,
				6);
		g.drawRect(this.getCenter().getXCoordinate() - getInnerRadius() - 3, this.getCenter().getYCoordinate() - 3, 6,
				6);
		g.drawRect(this.getCenter().getXCoordinate() - 3, this.getCenter().getYCoordinate() + getInnerRadius() - 3, 6,
				6);
		g.drawRect(this.getCenter().getXCoordinate() - 3, this.getCenter().getYCoordinate() - getInnerRadius() - 3, 6,
				6);

		g.drawRect(this.getCenter().getXCoordinate() + getRadius() - 3, this.getCenter().getYCoordinate() - 3, 6, 6);
		g.drawRect(this.getCenter().getXCoordinate() - getRadius() - 3, this.getCenter().getYCoordinate() - 3, 6, 6);
		g.drawRect(this.getCenter().getXCoordinate() - 3, this.getCenter().getYCoordinate() + getRadius() - 3, 6, 6);
		g.drawRect(this.getCenter().getXCoordinate() - 3, this.getCenter().getYCoordinate() - getRadius() - 3, 6, 6);
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public Area getDonutArea() {
		return donutArea;
	}

	public String toString() {
		return "Donut: " + "Center= (" + super.getCenter().getXCoordinate() + "," + super.getCenter().getYCoordinate()
				+ "), " + "Radius=" + super.getRadius() + ", " + "OuterColor="
				+ Integer.toString(getBorderColor().getRGB()) + ", " + "InnerColor="
				+ Integer.toString(getInsideColor().getRGB()) + ", InnerRadius=" + innerRadius;
	}
}
