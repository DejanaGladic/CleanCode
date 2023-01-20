package test.testObserver;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import frame.DrawingFrame;
import frame.FrameLeftToolBar;
import frame.FrameTopToolBar;
import observer.EnableButtonFireChange;
import observer.EnableButtonListener;

public class TestObserver {
	private DrawingFrame frame = new DrawingFrame();
	private FrameTopToolBar frameTopToolBar = frame.getTopToolBar();
	private FrameLeftToolBar frameLeftToolBar = frame.getLeftToolBar();
	private EnableButtonFireChange enableButtonFireChangeObserver;
	private EnableButtonListener enableButtonListenerObserver;

	@Before
	public void setUp() {
		enableButtonFireChangeObserver = new EnableButtonFireChange();
		enableButtonListenerObserver = new EnableButtonListener(frameTopToolBar, frameLeftToolBar);
		enableButtonFireChangeObserver.addPropertyChangeListener(enableButtonListenerObserver);
	}

	@Test
	public void testDeleteButtonEnabled() {
		enableButtonFireChangeObserver.setDeleteEnabled(true);
		assertTrue(frameTopToolBar.getToggleButtonRemove().isEnabled());
	}

	@Test
	public void testDeleteButtonDisabled() {
		enableButtonFireChangeObserver.setDeleteEnabled(false);
		assertFalse(frameTopToolBar.getToggleButtonRemove().isEnabled());
	}

	@Test
	public void testEditButtonEnabled() {
		enableButtonFireChangeObserver.setEditEnabled(true);
		assertTrue(frameTopToolBar.getToggleButtonEdit().isEnabled());
	}

	@Test
	public void testEditButtonDisabled() {
		enableButtonFireChangeObserver.setEditEnabled(false);
		assertFalse(frameTopToolBar.getToggleButtonEdit().isEnabled());
	}

	@Test
	public void testBringToFrontEnabledEnabled() {
		enableButtonFireChangeObserver.setBringToFrontEnabled(true);
		assertTrue(frameLeftToolBar.getButtonToFront().isEnabled());
	}

	@Test
	public void testBringToFrontEnabledDisabled() {
		enableButtonFireChangeObserver.setBringToFrontEnabled(false);
		assertFalse(frameLeftToolBar.getButtonToFront().isEnabled());
	}

	@Test
	public void testBringToBackEnabledEnabled() {
		enableButtonFireChangeObserver.setBringToBackEnabled(true);
		assertTrue(frameLeftToolBar.getButtonToBack().isEnabled());
	}

	@Test
	public void testBringToBackEnabledDisabled() {
		enableButtonFireChangeObserver.setBringToBackEnabled(false);
		assertFalse(frameLeftToolBar.getButtonToBack().isEnabled());
	}

	@Test
	public void testToFrontEnabledEnabled() {
		enableButtonFireChangeObserver.setToFrontEnabled(true);
		assertTrue(frameLeftToolBar.getButtonFront().isEnabled());
	}

	@Test
	public void testToFrontEnabledDisabled() {
		enableButtonFireChangeObserver.setToFrontEnabled(false);
		assertFalse(frameLeftToolBar.getButtonFront().isEnabled());
	}

	@Test
	public void testToBackEnabledEnabled() {
		enableButtonFireChangeObserver.setToBackEnabled(true);
		assertTrue(frameLeftToolBar.getButtonBack().isEnabled());
	}

	@Test
	public void testToBackEnabledDisabled() {
		enableButtonFireChangeObserver.setToBackEnabled(false);
		assertFalse(frameLeftToolBar.getButtonBack().isEnabled());
	}

	@After
	public void tearDown() {
		enableButtonFireChangeObserver.removePropertyChangeListener(enableButtonListenerObserver);
	}
}
