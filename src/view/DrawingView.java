package view;

import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

import model.DrawingModel;
import shapes.Shape;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();

	public DrawingView() {

	}

	public void paint(Graphics g) {
		super.paint(g);
		ListIterator<Shape> iteratorList = model.getShapes().listIterator();
		while (iteratorList.hasNext()) {
			((Shape) iteratorList.next()).draw(g);
		}
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
}
