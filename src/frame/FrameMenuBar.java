package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.DrawingController;

public class FrameMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private DrawingController controller;
	private JMenu fileMenu;

	private JMenu menuOpen;
	private JMenuItem optionOpenLogFile;
	private JMenuItem optionOpenDrawingFile;

	private JMenu menuSave;
	private JMenuItem optionSaveAsLog;
	private JMenuItem optionSaveAsDrawing;

	private JMenu menuNew;
	private JMenuItem optionNewDraw;

	private JMenu menuHelp;
	private JMenuItem optionShowAppDetails;

	public FrameMenuBar() {

		fileMenu = new JMenu("File");
		add(fileMenu);
		fileMenu.setVisible(true);

		menuOpen = new JMenu("   Open   ");
		fileMenu.add(menuOpen);
		optionOpenLogFile = new JMenuItem(" Open Log File ");
		menuOpen.add(optionOpenLogFile);
		optionOpenDrawingFile = new JMenuItem(" Open Drawing File ");
		menuOpen.add(optionOpenDrawingFile);
		openMenuListeners();

		menuSave = new JMenu("   Save   ");
		fileMenu.add(menuSave);
		optionSaveAsLog = new JMenuItem(" Save As Log File ");
		menuSave.add(optionSaveAsLog);
		optionSaveAsDrawing = new JMenuItem(" Save As Drawing File ");
		menuSave.add(optionSaveAsDrawing);
		saveMenuListeners();

		menuNew = new JMenu("New");
		add(menuNew);
		optionNewDraw = new JMenuItem("New Drawing");
		menuNew.add(optionNewDraw);
		createNewDrawingListener();

		menuHelp = new JMenu("Help");
		add(menuHelp);
		optionShowAppDetails = new JMenuItem(" App details ");
		menuHelp.add(optionShowAppDetails);
		helpMenuListeners();
	}

	private void saveMenuListeners() {
		optionSaveAsLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.exportLog();
			}
		});
		optionSaveAsDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.exportShapes();
			}
		});
	}

	private void openMenuListeners() {
		optionOpenLogFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.importLog();
			}
		});

		optionOpenDrawingFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.importDrawing();
			}
		});
	}

	private void createNewDrawingListener() {
		optionNewDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newDrawing();
			}
		});
	}

	private void helpMenuListeners() {
		optionShowAppDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "You are using a drawing app!", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JMenuItem getOptionOpenLogFile() {
		return optionOpenLogFile;
	}

	public JMenuItem getOptionOpenDrawingFile() {
		return optionOpenDrawingFile;
	}

	public JMenuItem getOptionSaveAsLog() {
		return optionSaveAsLog;
	}

	public JMenuItem getOptionSaveAsDrawing() {
		return optionSaveAsDrawing;
	}

	public JMenuItem getOptionNewDraw() {
		return optionNewDraw;
	}

	public JMenuItem getOptionShowAppDetails() {
		return optionShowAppDetails;
	}
}
