package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EnableButtonFireChange {

	private boolean deleteEnabled;
	private boolean editEnabled;
	private boolean bringToFrontEnabled;
	private boolean bringToBackEnabled;
	private boolean toBackEnabled;
	private boolean toFrontEnabled;

	private PropertyChangeSupport propertyChangeSupport;

	public EnableButtonFireChange() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void setDeleteEnabled(boolean deleteEnabled) {
		propertyChangeSupport.firePropertyChange("buttonRemove", this.deleteEnabled, deleteEnabled);
		this.deleteEnabled = deleteEnabled;
	}

	public void setEditEnabled(boolean editEnabled) {
		propertyChangeSupport.firePropertyChange("buttonModify", this.editEnabled, editEnabled);
		this.editEnabled = editEnabled;
	}

	public void setBringToFrontEnabled(boolean bringToFrontEnabled) {
		propertyChangeSupport.firePropertyChange("buttonToFront", this.bringToFrontEnabled, bringToFrontEnabled);
		this.bringToFrontEnabled = bringToFrontEnabled;
	}

	public void setBringToBackEnabled(boolean bringToBackEnabled) {
		propertyChangeSupport.firePropertyChange("buttonToBack", this.bringToBackEnabled, bringToBackEnabled);
		this.bringToBackEnabled = bringToBackEnabled;
	}

	public void setToBackEnabled(boolean toBackEnabled) {
		propertyChangeSupport.firePropertyChange("buttonBack", this.toBackEnabled, toBackEnabled);
		this.toBackEnabled = toBackEnabled;
	}

	public void setToFrontEnabled(boolean toFrontEnabled) {
		propertyChangeSupport.firePropertyChange("buttonFront", this.toFrontEnabled, toFrontEnabled);
		this.toFrontEnabled = toFrontEnabled;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
}
