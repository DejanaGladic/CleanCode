package test.testFrame;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import controller.DrawingController;
import frame.DrawingFrame;
import frame.FrameTopToolBar;

public class TestFrameTopToolBar {
	private DrawingFrame frame = new DrawingFrame();
	private DrawingController controller;
	private FrameTopToolBar frameTopToolBar;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame.setController(controller);
		frameTopToolBar = frame.getTopToolBar();
	}

	@Test
	public void testButtonEditShape() {
		frameTopToolBar.getToggleButtonEdit().setEnabled(true);
		frameTopToolBar.getToggleButtonEdit().doClick();
		verify(controller).editDrawnShape();
	}

	@Test
	public void testButtonDeleteShape() {
		frameTopToolBar.getToggleButtonRemove().setEnabled(true);
		frameTopToolBar.getToggleButtonRemove().doClick();
		verify(controller).deleteDrawnShape();
	}
}
