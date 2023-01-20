package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import frame.FrameLeftToolBar;
import frame.FrameTopToolBar;

public class EnableButtonListener implements PropertyChangeListener {

	private FrameLeftToolBar frameLeftToolBar;
	private FrameTopToolBar frameTopToolBar;

	public EnableButtonListener(FrameTopToolBar frameTopToolBar, FrameLeftToolBar frameLeftToolBar) {
		this.frameLeftToolBar = frameLeftToolBar;
		this.frameTopToolBar = frameTopToolBar;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("buttonRemove")) {
			frameTopToolBar.getToggleButtonRemove().setEnabled((boolean) evt.getNewValue());
		}

		if (evt.getPropertyName().equals("buttonModify")) {
			frameTopToolBar.getToggleButtonEdit().setEnabled((boolean) evt.getNewValue());
		}

		if (evt.getPropertyName().equals("buttonToFront")) {
			frameLeftToolBar.getButtonToFront().setEnabled((boolean) evt.getNewValue());
		}

		if (evt.getPropertyName().equals("buttonToBack")) {
			frameLeftToolBar.getButtonToBack().setEnabled((boolean) evt.getNewValue());
		}

		if (evt.getPropertyName().equals("buttonFront")) {
			frameLeftToolBar.getButtonFront().setEnabled((boolean) evt.getNewValue());
		}

		if (evt.getPropertyName().equals("buttonBack")) {
			frameLeftToolBar.getButtonBack().setEnabled((boolean) evt.getNewValue());
		}
	}
}
