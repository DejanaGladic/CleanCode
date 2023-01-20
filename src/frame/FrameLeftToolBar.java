package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.DrawingController;

public class FrameLeftToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private DrawingController controller;

	private JSeparator separator;
	private JSeparator separator_1;

	private JButton buttonInsideColor;
	private JButton buttonBorderColor;
	private JLabel labelInsideColor;
	private JLabel labelBorderColor;

	private JButton buttonFront;
	private JButton buttonBack;
	private JButton buttonToFront;
	private JButton buttonToBack;

	private JButton buttonUndo;
	private JButton buttonRedo;

	private JButton buttonExecuteLog;

	public FrameLeftToolBar() {
		setColorButtons();
		setUndoRedo();
		setPositionButtons();
		setButtonExecuteLog();
		addButtons();
		addColorButtonListener();
		addUndoRedoButtonListener();
		addPositionButtonListener();
		addButtonExecuteLogListener();
	}

	private void setColorButtons() {
		labelInsideColor = new JLabel("Fill color");
		labelInsideColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelInsideColor.setPreferredSize(new Dimension(80, 30));
		labelInsideColor.setMaximumSize(new Dimension(81, 39));
		labelInsideColor.setBackground(new Color(245, 246, 247));
		labelInsideColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		labelInsideColor.setForeground(Color.GRAY);
		buttonInsideColor = new JButton("");
		buttonInsideColor.setPreferredSize(new Dimension(80, 30));
		buttonInsideColor.setMaximumSize(new Dimension(81, 39));
		buttonInsideColor.setBackground(Color.white);
		buttonInsideColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonInsideColor.setForeground(Color.BLACK);

		labelBorderColor = new JLabel("Line color");
		labelBorderColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelBorderColor.setPreferredSize(new Dimension(80, 30));
		labelBorderColor.setMaximumSize(new Dimension(81, 39));
		labelBorderColor.setBackground(new Color(245, 246, 247));
		labelBorderColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		labelBorderColor.setForeground(Color.GRAY);
		buttonBorderColor = new JButton("");
		buttonBorderColor.setPreferredSize(new Dimension(80, 30));
		buttonBorderColor.setMaximumSize(new Dimension(81, 39));
		buttonBorderColor.setBackground(Color.black);
		buttonBorderColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonBorderColor.setForeground(Color.WHITE);
	}

	private void setUndoRedo() {
		separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(0, 10));
		add(separator_1);

		buttonUndo = new JButton("Undo");
		buttonUndo.setEnabled(false);
		buttonUndo.setPreferredSize(new Dimension(80, 30));
		buttonUndo.setMaximumSize(new Dimension(80, 39));
		buttonUndo.setBackground(new Color(245, 246, 247));
		buttonUndo.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonUndo.setForeground(Color.GRAY);

		buttonRedo = new JButton("Redo");
		buttonRedo.setPreferredSize(new Dimension(80, 30));
		buttonRedo.setMaximumSize(new Dimension(80, 39));
		buttonRedo.setBackground(new Color(245, 246, 247));
		buttonRedo.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonRedo.setForeground(Color.GRAY);
		buttonRedo.setEnabled(false);
	}

	private void setPositionButtons() {
		buttonFront = new JButton("Front");
		buttonFront.setPreferredSize(new Dimension(80, 30));
		buttonFront.setMaximumSize(new Dimension(80, 39));
		buttonFront.setBackground(new Color(245, 246, 247));
		buttonFront.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonFront.setForeground(Color.GRAY);
		buttonFront.setEnabled(false);

		buttonBack = new JButton("Back");
		buttonBack.setPreferredSize(new Dimension(80, 30));
		buttonBack.setMaximumSize(new Dimension(80, 39));
		buttonBack.setBackground(new Color(245, 246, 247));
		buttonBack.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonBack.setForeground(Color.GRAY);
		buttonBack.setEnabled(false);

		buttonToFront = new JButton("Bring To Front");
		buttonToFront.setPreferredSize(new Dimension(80, 30));
		buttonToFront.setMaximumSize(new Dimension(80, 39));
		buttonToFront.setBackground(new Color(245, 246, 247));
		buttonToFront.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonToFront.setForeground(Color.GRAY);
		buttonToFront.setEnabled(false);

		buttonToBack = new JButton("Bring To Back");
		buttonToBack.setPreferredSize(new Dimension(80, 30));
		buttonToBack.setMaximumSize(new Dimension(80, 39));
		buttonToBack.setBackground(new Color(245, 246, 247));
		buttonToBack.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonToBack.setForeground(Color.GRAY);
		buttonToBack.setEnabled(false);
	}

	private void setButtonExecuteLog() {
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 10));
		add(separator);
		buttonExecuteLog = new JButton("Execute Log");
		buttonExecuteLog.setPreferredSize(new Dimension(80, 30));
		buttonExecuteLog.setMaximumSize(new Dimension(81, 39));
		buttonExecuteLog.setBackground(new Color(245, 246, 247));
		buttonExecuteLog.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		buttonExecuteLog.setForeground(Color.GRAY);
		buttonExecuteLog.setEnabled(false);
	}

	private void addButtons() {
		add(labelInsideColor);
		add(buttonInsideColor);
		add(labelBorderColor);
		add(buttonBorderColor);
		add(buttonUndo);
		add(buttonRedo);
		add(buttonFront);
		add(buttonBack);
		add(buttonToFront);
		add(buttonToBack);
		add(buttonExecuteLog);
	}

	private void addColorButtonListener() {
		buttonInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setFillColor();
			}
		});

		buttonBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setLineColor();
			}
		});
	}

	private void addUndoRedoButtonListener() {
		buttonUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undoCommand();
			}
		});

		buttonRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redoCommand();
			}
		});
	}

	private void addPositionButtonListener() {
		buttonFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toFrontCommand();
				controller.enablingMovingOnZButtons();
			}
		});

		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBackCommand();
				controller.enablingMovingOnZButtons();
			}
		});

		buttonToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToFrontCommand();
				controller.enablingMovingOnZButtons();
			}
		});

		buttonToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBackCommand();
				controller.enablingMovingOnZButtons();
			}
		});
	}

	private void addButtonExecuteLogListener() {
		buttonExecuteLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.executeLog();
			}
		});
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JButton getButtonInsideColor() {
		return buttonInsideColor;
	}

	public void setButtonInsideColor(JButton buttonFill) {
		this.buttonInsideColor = buttonFill;
	}

	public JButton getButtonBorderColor() {
		return buttonBorderColor;
	}

	public void setButtonBorderColor(JButton buttonLineColor) {
		this.buttonBorderColor = buttonLineColor;
	}

	public JButton getButtonFront() {
		return buttonFront;
	}

	public void setButtonFront(JButton buttonFront) {
		this.buttonFront = buttonFront;
	}

	public JButton getButtonBack() {
		return buttonBack;
	}

	public void setButtonBack(JButton buttonBack) {
		this.buttonBack = buttonBack;
	}

	public JButton getButtonToFront() {
		return buttonToFront;
	}

	public void setButtonToFront(JButton buttonToFront) {
		this.buttonToFront = buttonToFront;
	}

	public JButton getButtonToBack() {
		return buttonToBack;
	}

	public void setButtonToBack(JButton buttonToBack) {
		this.buttonToBack = buttonToBack;
	}

	public JButton getButtonUndo() {
		return buttonUndo;
	}

	public void setButtonUndo(JButton buttonUndo) {
		this.buttonUndo = buttonUndo;
	}

	public JButton getButtonRedo() {
		return buttonRedo;
	}

	public void setButtonRedo(JButton buttonRedo) {
		this.buttonRedo = buttonRedo;
	}

	public JButton getButtonExecuteLog() {
		return buttonExecuteLog;
	}

	public void setButtonExecuteLog(JButton buttonExecuteLog) {
		this.buttonExecuteLog = buttonExecuteLog;
	}
}
