package test.testFrame;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import controller.DrawingController;
import frame.DrawingFrame;
import frame.FrameLeftToolBar;

public class TestLeftToolBar {
	private DrawingFrame frame = new DrawingFrame();
	private DrawingController controller;
	private FrameLeftToolBar frameLeftToolBar;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame.setController(controller);
		frameLeftToolBar = frame.getLeftToolBar();
	}

	@Test
	public void testPickedInsideColor() {
		frameLeftToolBar.getButtonInsideColor().doClick();
		verify(controller).setFillColor();
	}

	@Test
	public void testPickedBorderColor() {
		frameLeftToolBar.getButtonBorderColor().doClick();
		verify(controller).setLineColor();
	}

	@Test
	public void testUndoCommand() {
		frameLeftToolBar.getButtonUndo().setEnabled(true);
		frameLeftToolBar.getButtonUndo().doClick();
		verify(controller).undoCommand();
	}

	@Test
	public void testRedoCommand() {
		frameLeftToolBar.getButtonRedo().setEnabled(true);
		frameLeftToolBar.getButtonRedo().doClick();
		verify(controller).redoCommand();
	}

	@Test
	public void testPositionFrontButton() {
		frameLeftToolBar.getButtonFront().setEnabled(true);
		frameLeftToolBar.getButtonFront().doClick();
		verify(controller).toFrontCommand();
		verify(controller).enablingMovingOnZButtons();
	}

	@Test
	public void testPositionBackButton() {
		frameLeftToolBar.getButtonBack().setEnabled(true);
		frameLeftToolBar.getButtonBack().doClick();
		verify(controller).toBackCommand();
		verify(controller).enablingMovingOnZButtons();
	}

	@Test
	public void testPositionToFrontButton() {
		frameLeftToolBar.getButtonToFront().setEnabled(true);
		frameLeftToolBar.getButtonToFront().doClick();
		verify(controller).bringToFrontCommand();
		verify(controller).enablingMovingOnZButtons();
	}

	@Test
	public void testExecuteLogButton() {
		frameLeftToolBar.getButtonExecuteLog().setEnabled(true);
		frameLeftToolBar.getButtonExecuteLog().doClick();
		verify(controller).executeLog();
	}
}
