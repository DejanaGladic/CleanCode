package dialogs;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogOfPoint extends DialogOfShape {

	private static final long serialVersionUID = 1L;

	public DialogOfPoint() {
		setTitle("Point");
		getTitleOfDialog().setText("Point");
		setShapeDialogLayout();
	}

	public void setShapeDialogLayout() {
		getGl_contentPanel().setHorizontalGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
				.addGroup(getGl_contentPanel().createSequentialGroup().addContainerGap().addGroup(getGl_contentPanel()
						.createParallelGroup(Alignment.LEADING).addComponent(getButtonBorderColor())
						.addGroup(getGl_contentPanel().createSequentialGroup()
								.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(getLabelXCoordinate()).addComponent(getLabelYCoordinate()))
								.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
								.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
										.addComponent(getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(29, Short.MAX_VALUE))
				.addComponent(getSeparator(), GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
				.addGroup(getGl_contentPanel().createSequentialGroup().addGap(19)
						.addComponent(getTitleOfDialog(), GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(244, Short.MAX_VALUE)));
		getGl_contentPanel().setVerticalGroup(getGl_contentPanel().createParallelGroup(Alignment.TRAILING)
				.addGroup(getGl_contentPanel().createSequentialGroup()
						.addComponent(getTitleOfDialog(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
						.addComponent(getSeparator(), GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addGap(37)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
								.addComponent(getTextFieldXCoordinate(), GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getLabelXCoordinate()))
						.addGap(29)
						.addGroup(getGl_contentPanel().createParallelGroup(Alignment.LEADING)
								.addComponent(getTextFieldYCoordinate(), GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getLabelYCoordinate()))
						.addGap(36).addComponent(getButtonBorderColor()).addGap(41)));
		getContentPanel().setLayout(getGl_contentPanel());
	}
}
