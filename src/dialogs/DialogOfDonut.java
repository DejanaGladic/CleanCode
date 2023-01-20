package dialogs;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DialogOfDonut extends DialogOfCircle {

	private static final long serialVersionUID = 1L;
	private JLabel labelInnerRadius;
	private JTextField textFieldInnerRadius;

	public DialogOfDonut() {
		setBounds(100, 100, 364, 436);
		setTitle("Donut");
		getTitleOfDialog().setText("Donut");
		setDonutLayout();
	}

	public void setDonutLayout() {
		getLabelCircle().setText("Donut");
		labelInnerRadius = new JLabel("Inner radius:");
		labelInnerRadius.setForeground(new Color(128, 128, 128));
		labelInnerRadius.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldInnerRadius = new JTextField();
		textFieldInnerRadius.setForeground(new Color(128, 128, 128));
		textFieldInnerRadius.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldInnerRadius.setColumns(10);
		textFieldInnerRadius.addKeyListener(getKeyListener());

		getGl_contentPanel().setHorizontalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addGroup(getGl_contentPanel()
						.createParallelGroup(Alignment.LEADING)
						.addGroup(getGl_contentPanel().createSequentialGroup().addGap(33).addGroup(getGl_contentPanel()
								.createParallelGroup(Alignment.LEADING).addComponent(getLabelCircle())
								.addGroup(getGl_contentPanel().createSequentialGroup().addGroup(getGl_contentPanel()
										.createParallelGroup(Alignment.LEADING)
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING, false)
												.addComponent(getLabelXCoordinate(), Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(getLabelYCoordinate(), Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(getLabelRadius(), Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(getLabelRadius(), GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelInnerRadius, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(getButtonInsideColor()).addComponent(getButtonBorderColor()))
										.addGap(40).addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)

												.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldInnerRadius, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getTextFieldRadius(), GroupLayout.PREFERRED_SIZE, 93,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		getGl_contentPanel().setVerticalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addContainerGap().addComponent(getLabelCircle())
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
						.addGap(19)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelXCoordinate())
								.addComponent(getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelYCoordinate()).addComponent(getTextFieldYCoordinate(),
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelRadius(), GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getTextFieldRadius(), GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)

						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(labelInnerRadius, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldInnerRadius, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
								.addComponent(getLabelRadius(), GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getTextFieldRadius(), GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		if (textFieldInnerRadius.getText().isEmpty()) {
			throw new Exception("Not valid input. Check if all fields have values!");
		}
	}

	@Override
	public void checkIfValuesAreValid() throws Exception {
		super.checkIfValuesAreValid();
		if (getValueInnerRadius() <= 0) {
			throw new Exception("Inner radius must be positive!");
		}
		if (getValueInnerRadius() >= getValueRadius()) {
			throw new Exception("Radius must be greater than inner radius!");
		}
	}

	public JLabel getLabelInnerRadius() {
		return labelInnerRadius;
	}

	public void setLabelInnerRadius(JLabel labelInnerRadius) {
		this.labelInnerRadius = labelInnerRadius;
	}

	public JTextField getTextFieldInnerRadius() {
		return textFieldInnerRadius;
	}

	public void setValueInnerRadius(int valueOfInnerRadius) {
		this.textFieldInnerRadius.setText(String.valueOf(valueOfInnerRadius));
	}

	public int getValueInnerRadius() {
		return Integer.parseInt(textFieldInnerRadius.getText());
	}

	public void setTextFieldInnerRadius(JTextField textFieldInnerRadius) {
		this.textFieldInnerRadius = textFieldInnerRadius;
	}

	public void setTextFieldInnerRadius(String textFieldInnerRadius) {
		this.textFieldInnerRadius.setText(textFieldInnerRadius);
	}

}
