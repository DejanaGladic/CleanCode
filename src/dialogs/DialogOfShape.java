package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class DialogOfShape extends JDialog implements Dialogs {

	private static final long serialVersionUID = 1L;
	private JSeparator separator;
	private final JPanel contentPanel;
	private GroupLayout gl_contentPanel;
	private JLabel labelTitleOfDialog;
	private JLabel labelXCoordinate;
	private JTextField textFieldXCoordinate;
	private JLabel labelYCoordinate;
	private JTextField textFieldYCoordinate;
	private KeyAdapter keyListener;
	private JButton buttonBorderColor;
	private Color pickedBorderColor;
	private JButton okButton;
	private JButton cancelButton;
	private boolean valuesAreAccepted;

	public DialogOfShape() {
		separator = new JSeparator();
		contentPanel = new JPanel();
		gl_contentPanel = new GroupLayout(getContentPanel());
		labelTitleOfDialog = new JLabel();
		labelXCoordinate = new JLabel("Coordinate X:");
		textFieldXCoordinate = new JTextField();
		labelYCoordinate = new JLabel("Coordinate Y:");
		textFieldYCoordinate = new JTextField();
		buttonBorderColor = new JButton("Border color of shape");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		initializeKeyListener();
		basicLayoutSetUp();
		borderColorSetUp();
		okButtonSetUp();
		cancelButtonSetUp();
	}

	public void initializeKeyListener() {
		keyListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eventOfKeyPressed) {
				validateChars(eventOfKeyPressed);
			}
		};
	}

	private void validateChars(KeyEvent eventOfKeyPressed) {
		if (areKeysValid(eventOfKeyPressed)) {
			eventOfKeyPressed.consume();
			getToolkit().beep();
		}
	}

	private boolean areKeysValid(KeyEvent eventOfKeyPressed) {
		char pressedChar = eventOfKeyPressed.getKeyChar();
		return pressedChar < '0' || pressedChar > '9' || pressedChar == KeyEvent.VK_BACK_SPACE;
	}

	public void basicLayoutSetUp() {
		setResizable(false);
		setBounds(100, 100, 327, 346);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);

		contentPanel.setBackground(new Color(245, 246, 247));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		labelTitleOfDialog.setForeground(new Color(128, 128, 128));
		labelTitleOfDialog.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));

		labelXCoordinate.setForeground(new Color(128, 128, 128));
		labelXCoordinate.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldXCoordinate.setForeground(new Color(128, 128, 128));
		textFieldXCoordinate.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldXCoordinate.setColumns(10);
		textFieldXCoordinate.addKeyListener(keyListener);

		labelYCoordinate.setForeground(new Color(128, 128, 128));
		labelYCoordinate.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldYCoordinate.setForeground(new Color(128, 128, 128));
		textFieldYCoordinate.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldYCoordinate.setColumns(10);
		textFieldYCoordinate.addKeyListener(keyListener);

		buttonBorderColor.setBackground(new Color(199, 209, 225));
		buttonBorderColor.setForeground(Color.GRAY);
		buttonBorderColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 255, 255));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton.setBackground(new Color(199, 209, 225));
		okButton.setForeground(Color.GRAY);
		okButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		cancelButton.setBackground(new Color(199, 209, 225));
		cancelButton.setForeground(Color.GRAY);
		cancelButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private void borderColorSetUp() {
		buttonBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pickBorderColor();
			}
		});
	}

	private void pickBorderColor() {
		pickedBorderColor = JColorChooser.showDialog(null, "Choose color", null);
		buttonBorderColor.setBackground(pickedBorderColor);
	}

	public void okButtonSetUp() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					checkIfValuesAreFilled();
					checkIfValuesAreValid();
					valuesAreAccepted = true;
					setVisible(false);
				} catch (Exception e) {
					errorMessage(e);
				}
			}
		});
	}

	public void checkIfValuesAreFilled() throws Exception {
		if (getTextFieldXCoordinate().getText().isEmpty() || getTextFieldYCoordinate().getText().isEmpty()) {
			throw new Exception("All fields must be filled");
		}
	}

	public void checkIfValuesAreValid() throws Exception {
		if (getXCoordinateValue() == 0 || getYCoordinateValue() == 0) {
			throw new Exception("Values for POINT are not valid");
		}
	}

	public void errorMessage(Exception e) {
		JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.WARNING_MESSAGE);
	}

	private void cancelButtonSetUp() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	public GroupLayout getGl_contentPanel() {
		return gl_contentPanel;
	}

	public void setGl_contentPanel(GroupLayout gl_contentPanel) {
		this.gl_contentPanel = gl_contentPanel;
	}

	public JLabel getTitleOfDialog() {
		return labelTitleOfDialog;
	}

	public void setTitleOfDialog(JLabel titleOfDialog) {
		this.labelTitleOfDialog = titleOfDialog;
	}

	public JLabel getLabelXCoordinate() {
		return labelXCoordinate;
	}

	public void setLabelXCoordinate(JLabel labelXCoordinate) {
		this.labelXCoordinate = labelXCoordinate;
	}

	public JTextField getTextFieldXCoordinate() {
		return textFieldXCoordinate;
	}

	public int getXCoordinateValue() {
		return Integer.parseInt(textFieldXCoordinate.getText());
	}

	public void setXCoordinateValue(int valueXCoordinate) {
		this.textFieldXCoordinate.setText(String.valueOf(valueXCoordinate));
	}

	public void setTextFieldXCoordinate(JTextField textFieldXCoordinate) {
		this.textFieldXCoordinate = textFieldXCoordinate;
	}

	public JLabel getLabelYCoordinate() {
		return labelYCoordinate;
	}

	public void setLabelYCoordinate(JLabel labelYCoordinate) {
		this.labelYCoordinate = labelYCoordinate;
	}

	public JTextField getTextFieldYCoordinate() {
		return textFieldYCoordinate;
	}

	public int getYCoordinateValue() {
		return Integer.parseInt(textFieldYCoordinate.getText());
	}

	public void setTextFieldYCoordinate(JTextField textFieldYCoordinate) {
		this.textFieldYCoordinate = textFieldYCoordinate;
	}

	public void setYCoordinateValue(int valueYCoordinate) {
		this.textFieldYCoordinate.setText(String.valueOf(valueYCoordinate));
	}

	public JButton getButtonBorderColor() {
		return buttonBorderColor;
	}

	public void setButtonBorderColor(JButton buttonBorderColor) {
		this.buttonBorderColor = buttonBorderColor;
	}

	public Color getPickedBorderColor() {
		return pickedBorderColor;
	}

	public void setPickedBorderColor(Color pickedBorderColor) {
		this.pickedBorderColor = pickedBorderColor;
		getButtonBorderColor().setBackground(pickedBorderColor);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public boolean isValuesAreAccepted() {
		return valuesAreAccepted;
	}

	public void setValuesAreAccepted(boolean valuesAreAccepted) {
		this.valuesAreAccepted = valuesAreAccepted;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public KeyAdapter getKeyListener() {
		return keyListener;
	}

	public void setKeyListener(KeyAdapter keyListener) {
		this.keyListener = keyListener;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

}
