package strategy;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileDialog {

	private JFrame parentFrame;
	private JFileChooser fileChooser;
	private int userSelection;
	private File fileToSave;

	public FileDialog() {
		parentFrame = new JFrame();
		fileChooser = new JFileChooser();
	}

	public String showSaveFileDialog(String extensionForFile) {
		fileChooser.setDialogTitle("Save file");
		userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath() + "." + extensionForFile;
		}
		return null;
	}

	public String showOpenFileDialog() {
		userSelection = fileChooser.showOpenDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		return null;
	}
}
