package dialogs;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogOfRectangle extends DialogOfSurfaceShape {

	private static final long serialVersionUID = 1L;
	private JLabel labelOfRectangle;
	private JLabel labelOfHeight;
	private JTextField textFieldHeight;
	private JLabel labelOfWidth;
	private JTextField textFieldWidth;

	public DialogOfRectangle() {
		setTitle("Rectangle");
		setBounds(100, 100, 450, 450);
		getTitleOfDialog().setText("Rectangle");
		setShapeDialogLayout();
	}

	@Override
	public void setShapeDialogLayout() {
		labelOfRectangle = new JLabel("Rectangle");
		labelOfRectangle.setForeground(Color.GRAY);
		labelOfRectangle.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));
		getLabelXCoordinate().setText("Coordinate X of upper left point:");
		getLabelXCoordinate().setHorizontalTextPosition(SwingConstants.LEFT);
		getLabelXCoordinate().setHorizontalAlignment(SwingConstants.LEFT);
		getLabelYCoordinate().setText("Coordinate Y of upper left point:");
		labelOfHeight = new JLabel("Height:");
		labelOfHeight.setForeground(Color.GRAY);
		labelOfHeight.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldHeight = new JTextField();
		textFieldHeight.setForeground(new Color(105, 105, 105));
		textFieldHeight.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldHeight.setColumns(10);
		textFieldHeight.addKeyListener(getKeyListener());
		labelOfWidth = new JLabel("Width:");
		labelOfWidth.setForeground(Color.GRAY);
		labelOfWidth.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldWidth = new JTextField();
		textFieldWidth.setForeground(new Color(105, 105, 105));
		textFieldWidth.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldWidth.setText("");
		textFieldWidth.setColumns(10);
		textFieldWidth.addKeyListener(getKeyListener());

		getGl_contentPanel()
				.setHorizontalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
						.addGroup(getGl_contentPanel().createSequentialGroup().addGap(32).addGroup(getGl_contentPanel()
								.createParallelGroup(Alignment.LEADING).addComponent(labelOfRectangle)
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
												.addComponent(getLabelXCoordinate()).addComponent(getLabelYCoordinate())
												.addComponent(labelOfHeight).addComponent(labelOfWidth,
														GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
										.addGap(37)
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
												.addComponent(getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE,
														129, GroupLayout.PREFERRED_SIZE)
												.addComponent(getTextFieldYCoordinate(), 129, 129, 129)
												.addComponent(textFieldHeight, 129, 129, 129)
												.addComponent(textFieldWidth, 129, 129, 129)))
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addComponent(getButtonBorderColor()).addGap(39))
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addComponent(getButtonInsideColor())))
								.addContainerGap(68, Short.MAX_VALUE))
						.addComponent(getSeparator(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 357,
								Short.MAX_VALUE));

		getGl_contentPanel().setVerticalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				getGl_contentPanel().createSequentialGroup().addGap(25).addComponent(labelOfRectangle).addGap(18)
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelXCoordinate()).addComponent(getTextFieldXCoordinate(),
										GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getLabelYCoordinate()))
						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelOfHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))

						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelOfWidth, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getButtonBorderColor()))
						.addGap(18).addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getButtonInsideColor()))
						.addGap(27)));
		getContentPanel().setLayout(getGl_contentPanel());
	}

	@Override
	public void checkIfValuesAreFilled() throws Exception {
		super.checkIfValuesAreFilled();
		if (textFieldHeight.getText().isEmpty() || textFieldWidth.getText().isEmpty()) {
			throw new Exception("Not valid input. Check if all fields have values!");
		}
	}

	@Override
	public void checkIfValuesAreValid() throws Exception {
		super.checkIfValuesAreValid();
		if (getValueHeight() <= 0) {
			throw new Exception("Heigth must be positive!");
		}
		if (getValueWidth() <= 0) {
			throw new Exception("Width must be positive!");
		}
	}

	public JTextField getTextFieldHeight() {
		return textFieldHeight;
	}

	public int getValueHeight() {
		return Integer.parseInt(textFieldHeight.getText());
	}

	public void setValueHeight(int valueHeight) {
		this.textFieldHeight.setText(String.valueOf(valueHeight));
	}

	public void setTextFieldHeight(JTextField textFieldHeight) {
		this.textFieldHeight = textFieldHeight;
	}

	public void setTextFieldHeight(String textFieldHeight) {
		this.textFieldHeight.setText(textFieldHeight);
	}

	public JTextField getTextFieldWidth() {
		return textFieldWidth;
	}

	public int getValueWidth() {
		return Integer.parseInt(textFieldWidth.getText());
	}

	public void setValueWidth(int valueWidth) {
		this.textFieldWidth.setText(String.valueOf(valueWidth));
	}

	public void setTextFieldWidth(JTextField textFieldWidth) {
		this.textFieldWidth = textFieldWidth;
	}

	public void setTextFieldWidth(String textFieldWidth) {
		this.textFieldWidth.setText(textFieldWidth);
	}
}
