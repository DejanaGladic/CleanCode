package dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

public abstract class DialogOfSurfaceShape extends DialogOfShape {
	static final long serialVersionUID = 1L;
	private JButton buttonInsideColor;
	private Color pickedInsideColor;

	public DialogOfSurfaceShape() {
		setSurfaceShapeDialogLayout();
		insideColorSetUp();
	}

	public void setSurfaceShapeDialogLayout() {
		buttonInsideColor = new JButton("Fill color of shape");
		buttonInsideColor.setBackground(new Color(199, 209, 225));
		buttonInsideColor.setForeground(new Color(105, 105, 105));
		buttonInsideColor.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
	}

	private void insideColorSetUp() {
		buttonInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickInsideColor();
			}
		});
	}

	private void pickInsideColor() {
		pickedInsideColor = JColorChooser.showDialog(null, "Choose color", Color.WHITE);
		buttonInsideColor.setBackground(pickedInsideColor);
	}

	public JButton getButtonInsideColor() {
		return buttonInsideColor;
	}

	public void setButtonInsideColor(JButton buttonInsideColor) {
		this.buttonInsideColor = buttonInsideColor;
	}

	public Color getPickedInsideColor() {
		return pickedInsideColor;
	}

	public void setPickedInsideColor(Color pickedInsideColor) {
		this.pickedInsideColor = pickedInsideColor;
		getButtonInsideColor().setBackground(pickedInsideColor);
	}

}
