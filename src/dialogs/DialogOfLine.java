package dialogs;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class DialogOfLine extends DialogOfShape {

	private static final long serialVersionUID = 1L;
	private JLabel labelOfStartPoint;
	private JLabel labelOfEndPoint;
	private JLabel labelXCoordinateEndPoint;
	private JTextField textFieldXCoordinateEndPoint;
	private JLabel labelYCoordinateEndPoint;
	private JTextField textFieldYCoordinateEndPoint;

	public DialogOfLine() {
		setBounds(100, 100, 400, 450);
		setTitle("Line");
		getTitleOfDialog().setText("Line");
		setShapeDialogLayout();
	}

	@Override
	public void setShapeDialogLayout() {
		labelOfStartPoint = new JLabel("Start Point");
		labelOfStartPoint.setForeground(new Color(128, 128, 128));
		labelOfStartPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));

		getLabelXCoordinate().setText("Coordinate X of start point:");
		getLabelYCoordinate().setText("Coordinate Y of start point:");

		labelOfEndPoint = new JLabel("End Point");
		labelOfEndPoint.setForeground(new Color(128, 128, 128));
		labelOfEndPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 25));

		labelXCoordinateEndPoint = new JLabel("Coordinate X of end point:");
		labelXCoordinateEndPoint.setForeground(new Color(128, 128, 128));
		labelXCoordinateEndPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldXCoordinateEndPoint = new JTextField();
		textFieldXCoordinateEndPoint.setForeground(new Color(128, 128, 128));
		textFieldXCoordinateEndPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldXCoordinateEndPoint.setColumns(10);
		textFieldXCoordinateEndPoint.addKeyListener(getKeyListener());

		labelYCoordinateEndPoint = new JLabel("Coordinate Y of end point:");
		labelYCoordinateEndPoint.setForeground(new Color(128, 128, 128));
		labelYCoordinateEndPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		textFieldYCoordinateEndPoint = new JTextField();
		textFieldYCoordinateEndPoint.setForeground(new Color(128, 128, 128));
		textFieldYCoordinateEndPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		textFieldYCoordinateEndPoint.setColumns(10);
		textFieldYCoordinateEndPoint.addKeyListener(getKeyListener());

		JSeparator separator_1 = new JSeparator();
		getGl_contentPanel().setHorizontalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addGap(23)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addComponent(labelOfEndPoint, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, getGl_contentPanel().createSequentialGroup()
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
												.addGroup(getGl_contentPanel().createSequentialGroup()
														.addComponent(getLabelXCoordinate(), GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(56))
												.addComponent(getLabelYCoordinate())
												.addGroup(getGl_contentPanel().createSequentialGroup().addGap(1)
														.addGroup(getGl_contentPanel()
																.createParallelGroup(Alignment.LEADING)
																.addComponent(labelYCoordinateEndPoint)
																.addComponent(labelXCoordinateEndPoint)
																.addComponent(getButtonBorderColor()))))
										.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
												.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(
														getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(getGl_contentPanel().createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(getGl_contentPanel()
																.createParallelGroup(Alignment.LEADING)
																.addComponent(textFieldXCoordinateEndPoint,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(textFieldYCoordinateEndPoint,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))))
										.addGap(241))))
				.addGroup(getGl_contentPanel().createSequentialGroup().addGap(23).addComponent(labelOfStartPoint)
						.addContainerGap(455, Short.MAX_VALUE))
				.addGroup(
						getGl_contentPanel().createSequentialGroup()
								.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 366,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(191, Short.MAX_VALUE))
				.addGroup(getGl_contentPanel().createSequentialGroup()
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		getGl_contentPanel().setVerticalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addContainerGap().addComponent(labelOfStartPoint)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelXCoordinate()).addComponent(getTextFieldXCoordinate(),
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.BASELINE)
								.addComponent(getLabelYCoordinate()).addComponent(getTextFieldYCoordinate(),
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addComponent(labelOfEndPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldXCoordinateEndPoint, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textFieldYCoordinateEndPoint, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(getGl_contentPanel().createSequentialGroup()
										.addComponent(labelXCoordinateEndPoint).addGap(18)
										.addComponent(labelYCoordinateEndPoint).addGap(18)
										.addComponent(getButtonBorderColor())))
						.addGap(4)));
		getContentPanel().setLayout(getGl_contentPanel());
	}

	@Override
	public void checkIfValuesAreFilled() throws Exception {
		super.checkIfValuesAreFilled();
		if (textFieldXCoordinateEndPoint.getText().isEmpty() || textFieldYCoordinateEndPoint.getText().isEmpty()) {
			throw new Exception("All fields must be filled");
		}
	}

	@Override
	public void checkIfValuesAreValid() throws Exception {
		super.checkIfValuesAreValid();
		if (getXCoordinateValueEndPoint() == 0 || getYCoordinateValueEndPoint() == 0) {
			throw new Exception("Values for END POINT are not valid");
		}
	}

	public void setXCoordinateValueEndPoint(int valueXCoordinateEndPoint) {
		this.textFieldXCoordinateEndPoint.setText(String.valueOf(valueXCoordinateEndPoint));
	}

	public JTextField getTextFieldXCoordinateEndPoint() {
		return textFieldXCoordinateEndPoint;
	}

	public int getXCoordinateValueEndPoint() {
		return Integer.parseInt(textFieldXCoordinateEndPoint.getText());
	}

	public void setTextFieldXCoordinateEndPoint(JTextField textFieldXCoordinateEndPoint) {
		this.textFieldXCoordinateEndPoint = textFieldXCoordinateEndPoint;
	}

	public void setYCoordinateValueEndPoint(int valueYCoordinateEndPoint) {
		this.textFieldYCoordinateEndPoint.setText(String.valueOf(valueYCoordinateEndPoint));
	}

	public JTextField getTextFieldYCoordinateEndPoint() {
		return textFieldYCoordinateEndPoint;
	}

	public int getYCoordinateValueEndPoint() {
		return Integer.parseInt(textFieldYCoordinateEndPoint.getText());
	}

	public void setTextFieldYCoordinateEndPoint(JTextField textFieldYCoordinateEndPoint) {
		this.textFieldYCoordinateEndPoint = textFieldYCoordinateEndPoint;
	}
}
