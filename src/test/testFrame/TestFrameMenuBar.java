package test.testFrame;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import controller.DrawingController;
import frame.DrawingFrame;
import frame.FrameMenuBar;

public class TestFrameMenuBar {
	private DrawingFrame frame = new DrawingFrame();
	private DrawingController controller;
	private FrameMenuBar frameMenuBar;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame.setController(controller);
		frameMenuBar = frame.getTopMenuBar();
	}

	@Test
	public void testSaveAsLogOption() {
		frameMenuBar.getOptionSaveAsLog().doClick();
		verify(controller).exportLog();
	}

	@Test
	public void testSaveAsDrawingOption() {
		frameMenuBar.getOptionSaveAsDrawing().doClick();
		verify(controller).exportShapes();
	}

	@Test
	public void testOpenLogFileOption() {
		frameMenuBar.getOptionOpenLogFile().doClick();
		verify(controller).importLog();
	}

	@Test
	public void testOpenDrawingFileOption() {
		frameMenuBar.getOptionOpenDrawingFile().doClick();
		verify(controller).importDrawing();
	}

	@Test
	public void testCreateNewDrawingOption() {
		frameMenuBar.getOptionNewDraw().doClick();
		verify(controller).newDrawing();
	}

}
