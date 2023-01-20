package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import controller.DrawingController;

public class FrameTopToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private DrawingController controller;
	private ButtonGroup butonGroup;

	private JToggleButton toggleButtonPoint;
	private JToggleButton toggleButtonLine;
	private JToggleButton toggleButtonRectangle;
	private JToggleButton toggleButtonCircle;
	private JToggleButton toggleButtonDonut;
	private JToggleButton toggleButtonHexagon;
	private JToggleButton toggleButtonSelect;
	private JToggleButton toggleButtonEdit;
	private JToggleButton toggleButtonRemove;

	public FrameTopToolBar() {
		createShapeButtons();
		setIcons();
		addShapeButtons();
		createOptionsButtons();
		addOptionsButtons();
		optionsButtonsListener();
		butonGroup = new ButtonGroup();
		addButtonsToTheGroup();
	}

	private void createShapeButtons() {
		toggleButtonPoint = new JToggleButton("Point");
		toggleButtonPoint.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonPoint.setMaximumSize(new Dimension(57, 39));
		toggleButtonPoint.setPreferredSize(new Dimension(60, 47));
		toggleButtonPoint.setSize(new Dimension(24, 25));
		toggleButtonPoint.setForeground(Color.GRAY);
		toggleButtonPoint.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonPoint.setAlignmentX(3.0f);
		toggleButtonPoint.setBackground(new Color(245, 246, 247));

		toggleButtonLine = new JToggleButton("Line");
		toggleButtonLine.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonLine.setPreferredSize(new Dimension(50, 23));
		toggleButtonLine.setMaximumSize(new Dimension(81, 39));
		toggleButtonLine.setForeground(Color.GRAY);
		toggleButtonLine.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonLine.setAlignmentX(2.0f);
		toggleButtonLine.setBackground(new Color(245, 246, 247));

		toggleButtonRectangle = new JToggleButton("Rectangle");
		toggleButtonRectangle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonRectangle.setPreferredSize(new Dimension(200, 23));
		toggleButtonRectangle.setMaximumSize(new Dimension(100, 39));
		toggleButtonRectangle.setForeground(Color.GRAY);
		toggleButtonRectangle.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonRectangle.setAlignmentX(2.0f);
		toggleButtonRectangle.setBackground(new Color(245, 246, 247));

		toggleButtonCircle = new JToggleButton("Circle");
		toggleButtonCircle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonCircle.setMaximumSize(new Dimension(81, 39));
		toggleButtonCircle.setForeground(Color.GRAY);
		toggleButtonCircle.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonCircle.setAlignmentX(2.0f);
		toggleButtonCircle.setBackground(new Color(245, 246, 247));

		toggleButtonDonut = new JToggleButton("Donut");
		toggleButtonDonut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonDonut.setMaximumSize(new Dimension(81, 39));
		toggleButtonDonut.setForeground(Color.GRAY);
		toggleButtonDonut.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonDonut.setAlignmentX(3.0f);
		toggleButtonDonut.setBackground(new Color(245, 246, 247));

		toggleButtonHexagon = new JToggleButton("Hexagon");
		toggleButtonHexagon.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonHexagon.setMaximumSize(new Dimension(100, 39));
		toggleButtonHexagon.setForeground(Color.GRAY);
		toggleButtonHexagon.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonHexagon.setAlignmentX(2.0f);
		toggleButtonHexagon.setBackground(new Color(245, 246, 247));
	}

	private void setIcons() {
		toggleButtonPoint.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/point.png")));
		toggleButtonLine.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/line1.png")));
		toggleButtonRectangle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/rectangle.png")));
		toggleButtonCircle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/circle1.png")));
		toggleButtonDonut.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/donut.png")));
		toggleButtonHexagon.setIcon(new ImageIcon(DrawingFrame.class.getResource("/img/hexagon.png")));
	}

	private void addShapeButtons() {
		add(toggleButtonPoint);
		add(toggleButtonLine);
		add(toggleButtonRectangle);
		add(toggleButtonCircle);
		add(toggleButtonDonut);
		add(toggleButtonHexagon);
	}

	private void createOptionsButtons() {
		toggleButtonSelect = new JToggleButton("Select");
		toggleButtonSelect.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonSelect.setPreferredSize(new Dimension(30, 23));
		toggleButtonSelect.setMaximumSize(new Dimension(63, 39));
		toggleButtonSelect.setBackground(new Color(245, 246, 247));
		toggleButtonSelect.setForeground(Color.GRAY);
		toggleButtonSelect.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));

		toggleButtonEdit = new JToggleButton("Edit");
		toggleButtonEdit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonEdit.setPreferredSize(new Dimension(30, 23));
		toggleButtonEdit.setMaximumSize(new Dimension(51, 39));
		toggleButtonEdit.setBackground(new Color(245, 246, 247));
		toggleButtonEdit.setForeground(Color.GRAY);
		toggleButtonEdit.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonEdit.setEnabled(false);

		toggleButtonRemove = new JToggleButton("Remove");
		toggleButtonRemove.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toggleButtonRemove.setPreferredSize(new Dimension(30, 23));
		toggleButtonRemove.setMaximumSize(new Dimension(71, 39));
		toggleButtonRemove.setBackground(new Color(245, 246, 247));
		toggleButtonRemove.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		toggleButtonRemove.setForeground(Color.GRAY);
		toggleButtonRemove.setEnabled(false);

	}

	private void addOptionsButtons() {
		add(toggleButtonSelect);
		add(toggleButtonEdit);
		add(toggleButtonRemove);
	}

	private void optionsButtonsListener() {
		toggleButtonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.editDrawnShape();
			}
		});

		toggleButtonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteDrawnShape();
			}
		});
	}

	private void addButtonsToTheGroup() {
		butonGroup.add(toggleButtonPoint);
		butonGroup.add(toggleButtonLine);
		butonGroup.add(toggleButtonRectangle);
		butonGroup.add(toggleButtonCircle);
		butonGroup.add(toggleButtonDonut);
		butonGroup.add(toggleButtonHexagon);
		butonGroup.add(toggleButtonSelect);
		butonGroup.add(toggleButtonEdit);
		butonGroup.add(toggleButtonRemove);
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getToggleButtonPoint() {
		return toggleButtonPoint;
	}

	public void setToggleButtonPoint(JToggleButton toggleButtonPoint) {
		this.toggleButtonPoint = toggleButtonPoint;
	}

	public JToggleButton getToggleButtonLine() {
		return toggleButtonLine;
	}

	public void setToggleButtonLine(JToggleButton toggleButtonLine) {
		this.toggleButtonLine = toggleButtonLine;
	}

	public JToggleButton getToggleButtonRectangle() {
		return toggleButtonRectangle;
	}

	public void setToggleButtonRectangle(JToggleButton toggleButtonRectangle) {
		this.toggleButtonRectangle = toggleButtonRectangle;
	}

	public JToggleButton getToggleButtonCircle() {
		return toggleButtonCircle;
	}

	public void setToggleButtonCircle(JToggleButton toggleButtonCircle) {
		this.toggleButtonCircle = toggleButtonCircle;
	}

	public JToggleButton getToggleButtonDonut() {
		return toggleButtonDonut;
	}

	public void setToggleButtonDonut(JToggleButton toggleButtonDonut) {
		this.toggleButtonDonut = toggleButtonDonut;
	}

	public JToggleButton getToggleButtonHexagon() {
		return toggleButtonHexagon;
	}

	public void setToggleButtonHexagon(JToggleButton toggleButtonHexagon) {
		this.toggleButtonHexagon = toggleButtonHexagon;
	}

	public JToggleButton getToggleButtonSelect() {
		return toggleButtonSelect;
	}

	public void setToggleButtonSelect(JToggleButton toggleButtonSelect) {
		this.toggleButtonSelect = toggleButtonSelect;
	}

	public JToggleButton getToggleButtonEdit() {
		return toggleButtonEdit;
	}

	public void setToggleButtonEdit(JToggleButton toggleButtonEdit) {
		this.toggleButtonEdit = toggleButtonEdit;
	}

	public JToggleButton getToggleButtonRemove() {
		return toggleButtonRemove;
	}

	public void setToggleButtonRemove(JToggleButton toggleButtonRemove) {
		this.toggleButtonRemove = toggleButtonRemove;
	}

}
