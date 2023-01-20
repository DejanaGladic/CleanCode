package app;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;
import view.DrawingView;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingFrame frame = new DrawingFrame();
		frame.setSize(700, 600);
		frame.setMinimumSize(new Dimension(700, 650));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DrawingView view = frame.getView();
		DrawingModel model = new DrawingModel();
		view.setModel(model);

		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		frame.setVisible(true);
	}
}
