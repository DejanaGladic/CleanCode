package dialogs;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogOfHexagon extends DialogOfSurfaceShape {

	private static final long serialVersionUID = 1L;
	private JLabel labelHexagon;
	private JTextField textFieldRadius;
	private JLabel labelRadius;

	public DialogOfHexagon() {
		setTitle("Hexagon");
		setBounds(100, 100, 400, 400);
		setShapeDialogLayout();
	}

	@Override
	public void setShapeDialogLayout() {
		getLabelXCoordinate().setText("Coordinate X of center:");
		getLabelYCoordinate().setText("Coordinate Y of center:");
		labelHexagon = new JLabel("Hexagon");
		labelHexagon.setForeground(new Color(128, 128, 128));
		labelHexagon.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));
		labelRadius = new JLabel("Radius:");
		labelRadius.setForeground(new Color(128, 128, 128));
		labelRadius.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldRadius = new JTextField();
		textFieldRadius.setForeground(new Color(128, 128, 128));
		textFieldRadius.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldRadius.setColumns(10);
		textFieldRadius.addKeyListener(getKeyListener());

		getGl_contentPanel().setHorizontalGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addGroup(getGl_contentPanel()
						.createParallelGroup(Alignment.LEADING)
						.addGroup(getGl_contentPanel().createSequentialGroup().addGap(32).addGroup(getGl_contentPanel()
								.createParallelGroup(Alignment.LEADING)
								.addComponent(labelHexagon, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGroup(getGl_contentPanel().createSequentialGroup().addGroup(getGl_contentPanel()
										.createParallelGroup(Alignment.LEADING)
										.addComponent(getLabelYCoordinate(), GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING)
												.addComponent(getButtonInsideColor(), GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(getButtonBorderColor(), GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(labelRadius, GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(getLabelXCoordinate(), GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
												.addGroup(getGl_contentPanel().createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(getGl_contentPanel()
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(textFieldRadius)
																.addComponent(getTextFieldYCoordinate())
																.addComponent(getTextFieldXCoordinate(),
																		GroupLayout.DEFAULT_SIZE, 136,
																		Short.MAX_VALUE)))
												.addGroup(getGl_contentPanel().createSequentialGroup().addGap(37)))))
								.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE))
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		getGl_contentPanel().setVerticalGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addGap(19)
						.addComponent(labelHexagon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
						.addGap(24)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING, false)
								.addComponent(getTextFieldXCoordinate(), 0, 0, Short.MAX_VALUE)
								.addComponent(getLabelXCoordinate(), GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelYCoordinate(), GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE))
						.addGap(43)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE).addComponent(labelRadius)
								.addComponent(textFieldRadius, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getButtonBorderColor()))
						.addGap(18).addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getButtonInsideColor()))
						.addContainerGap()));
		getContentPanel().setLayout(getGl_contentPanel());
	}

	@Override
	public void checkIfValuesAreFilled() throws Exception {
		super.checkIfValuesAreFilled();
		if (textFieldRadius.getText().isEmpty()) {
			throw new Exception("Not valid input. Check if all fields have values!");
		}
	}

	@Override
	public void checkIfValuesAreValid() throws Exception {
		super.checkIfValuesAreValid();
		if (getValueHexRadius() <= 0) {
			throw new Exception("Radius must be positive!");
		}
	}

	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}

	public int getValueHexRadius() {
		return Integer.parseInt(textFieldRadius.getText());
	}

	public void setValueHexRadius(int valueOfHexRadius) {
		this.textFieldRadius.setText(String.valueOf(valueOfHexRadius));
	}

	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}

	public void setTextFieldRadius(String textFieldRadius) {
		this.textFieldRadius.setText(textFieldRadius);
	}
}
