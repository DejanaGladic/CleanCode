package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.CommadDeselectShape;
import commands.CommadSelectShape;
import commands.Command;
import commands.CommandAddShape;
import commands.CommandBringToBack;
import commands.CommandBringToFront;
import commands.CommandRemoveSelectedShapes;
import commands.CommandToBack;
import commands.CommandToFront;
import commands.CommandUndoRedo;
import commands.CommandUpdateShape;
import dialogs.DialogOfCircle;
import dialogs.DialogOfDonut;
import dialogs.DialogOfHexagon;
import dialogs.DialogOfLine;
import dialogs.DialogOfPoint;
import dialogs.DialogOfRectangle;
import dialogs.DialogOfShape;
import dialogs.DialogOfSurfaceShape;
import frame.DrawingFrame;
import frame.FrameLeftToolBar;
import frame.FrameTopToolBar;
import model.DrawingModel;
import observer.EnableButtonFireChange;
import observer.EnableButtonListener;
import shapes.Circle;
import shapes.Donut;
import shapes.HexagonAdapter;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import strategy.ExportImportManager;
import strategy.FileDialog;
import strategy.ExportImportLog;
import strategy.ExportImportDrawing;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private FrameTopToolBar frameTopToolBar;
	private FrameLeftToolBar frameLeftToolBar;
	private EnableButtonListener enableButtonListenerObserver;
	private EnableButtonFireChange enableButtonFireChangeObserver;
	private Point clickedPoint;
	private Shape shapeFromModel;
	private CommandUndoRedo undoRedoCommand;
	private List<String> logCommandList = new ArrayList<String>();
	private Point startPoint;
	boolean acceptedValues;
	private Shape selectedShape;
	private Color insideColor;
	private Color borderColor;
	private ArrayList<Object> importedLogFile;
	private Color defaultInnerColor = Color.WHITE;
	private Color defaultBorderColor = Color.BLACK;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		frameTopToolBar = frame.getTopToolBar();
		frameLeftToolBar = frame.getLeftToolBar();
		enableButtonListenerObserver = new EnableButtonListener(frameTopToolBar, frameLeftToolBar);
		enableButtonFireChangeObserver = new EnableButtonFireChange();
		enableButtonFireChangeObserver.addPropertyChangeListener(enableButtonListenerObserver);
		undoRedoCommand = new CommandUndoRedo();
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		clickedPoint = new Point(mouseEvent.getX(), mouseEvent.getY());

		boolean checkIfButtonSelectIsSelected = frame.getTopToolBar().getToggleButtonSelect().isSelected();
		boolean checkSelectionOfButtonPoint = frame.getTopToolBar().getToggleButtonPoint().isSelected();
		boolean checkSelectionOfButtonLine = frame.getTopToolBar().getToggleButtonLine().isSelected();
		boolean checkSelectionOfButtonRectangle = frame.getTopToolBar().getToggleButtonRectangle().isSelected();
		boolean checkSelectionOfButtonCircle = frame.getTopToolBar().getToggleButtonCircle().isSelected();
		boolean checkSelectionOfButtonDonut = frame.getTopToolBar().getToggleButtonDonut().isSelected();
		boolean checkSelectionOfButtonHexagon = frame.getTopToolBar().getToggleButtonHexagon().isSelected();
		if (checkIfButtonSelectIsSelected) {
			selectOrDeselectShape();
			buttonsUndoAndRedoEnable();
		} else {
			if (checkSelectionOfButtonPoint) {
				drawPointSetDialog();
			} else if (checkSelectionOfButtonLine) {
				drawLineSetDialog();
			} else if (checkSelectionOfButtonRectangle) {
				drawRectangleSetDialog();
			} else if (checkSelectionOfButtonCircle) {
				drawCircleSetDialog();
			} else if (checkSelectionOfButtonDonut) {
				drawDonutSetDialog();
			} else if (checkSelectionOfButtonHexagon) {
				drawHexagonSetDialog();
			}
			buttonsUndoEnabledRedoDisabled();
		}
		checkIfRedoListIsEmpty();
		enableButtons();
		frame.getView().repaint();
	}

	public void selectOrDeselectShape() {
		Iterator<Shape> iteratorThroughShapes = model.getShapes().iterator();
		while (iteratorThroughShapes.hasNext()) {
			shapeFromModel = iteratorThroughShapes.next();
			boolean checkIfShapeContainsPoint = shapeFromModel.doesShapeContainPoint(clickedPoint);
			if (checkIfShapeContainsPoint) {
				boolean checkSelectStateOfShape = shapeFromModel.isSelected();
				if (checkSelectStateOfShape == false) {
					commandSelectShape(shapeFromModel);
					buttonsEditDisableRemoveEnable();
				} else {
					commandDeselectShape(shapeFromModel);
				}
			}
		}
	}

	private void buttonsEditDisableRemoveEnable() {
		frame.getTopToolBar().getToggleButtonEdit().setEnabled(false);
		frame.getTopToolBar().getToggleButtonRemove().setEnabled(true);
	}

	private void drawPointSetDialog() {
		DialogOfPoint dialogPoint = new DialogOfPoint();
		setNotEditableFields(dialogPoint);
		setFieldForXYCoordinateAndBorderColor(dialogPoint, clickedPoint);
		dialogPoint.setVisible(true);

		drawPointIfAccepted(dialogPoint);
	}

	public void drawPointIfAccepted(DialogOfPoint dialogPoint) {
		acceptedValues = dialogPoint.isValuesAreAccepted();
		if (acceptedValues) {
			Point pointToAdd = setPointValues(dialogPoint);
			Color borderColor = dialogPoint.getPickedBorderColor();
			pointToAdd.setBorderColor(borderColor);
			commandAddShape(pointToAdd);
		}
	}

	private void drawLineSetDialog() {
		if (startPoint == null)
			startPoint = new Point(clickedPoint.getXCoordinate(), clickedPoint.getYCoordinate());
		else {
			DialogOfLine dialogOfLine = new DialogOfLine();
			setNotEditableFields(dialogOfLine);
			setFieldForXYCoordinateAndBorderColor(dialogOfLine, startPoint);
			setNotEditableFieldsEndPoint(dialogOfLine);
			setFieldForXYCoordinateEndPoint(dialogOfLine, clickedPoint);
			dialogOfLine.setVisible(true);

			drawLineIfAccepted(dialogOfLine);

			startPoint = null;
		}
	}

	public void drawLineIfAccepted(DialogOfLine dialogOfLine) {
		acceptedValues = dialogOfLine.isValuesAreAccepted();
		if (acceptedValues) {
			Color borderColor = dialogOfLine.getPickedBorderColor();

			Line lineToAdd = new Line(setPointValues(dialogOfLine),
					new Point(dialogOfLine.getXCoordinateValueEndPoint(), dialogOfLine.getYCoordinateValueEndPoint()),
					borderColor);
			commandAddShape(lineToAdd);
		}
	}

	private void setFieldForXYCoordinateEndPoint(DialogOfLine dialogOfLine, Point point) {
		dialogOfLine.getTextFieldXCoordinateEndPoint().setText(Integer.toString(point.getXCoordinate()));
		dialogOfLine.getTextFieldYCoordinateEndPoint().setText(Integer.toString(point.getYCoordinate()));
	}

	private void setNotEditableFieldsEndPoint(DialogOfLine dialogOfLine) {
		dialogOfLine.getTextFieldXCoordinateEndPoint().setEditable(false);
		dialogOfLine.getTextFieldYCoordinateEndPoint().setEditable(false);
	}

	private void drawRectangleSetDialog() {
		DialogOfRectangle dialogOfRectangle = new DialogOfRectangle();
		setNotEditableFields(dialogOfRectangle);
		setFieldForXYCoordinateAndBorderColor(dialogOfRectangle, clickedPoint);
		dialogOfRectangle.setPickedInsideColor(frame.getLeftToolBar().getButtonInsideColor().getBackground());
		dialogOfRectangle.setVisible(true);

		drawRectangleIfAccepted(dialogOfRectangle);
	}

	public void drawRectangleIfAccepted(DialogOfRectangle dialogOfRectangle) {
		acceptedValues = dialogOfRectangle.isValuesAreAccepted();
		if (acceptedValues) {
			Rectangle rectangleToAdd = setRectangleValues(dialogOfRectangle, clickedPoint);
			commandAddShape(rectangleToAdd);
		}
	}

	private void drawCircleSetDialog() {
		DialogOfCircle dialogOfCircle = new DialogOfCircle();
		setNotEditableFields(dialogOfCircle);
		setFieldForXYCoordinateAndBorderColor(dialogOfCircle, clickedPoint);
		dialogOfCircle.setPickedInsideColor(frame.getLeftToolBar().getButtonInsideColor().getBackground());
		dialogOfCircle.setVisible(true);

		drawCircleIfAccepted(dialogOfCircle);
	}

	public void drawCircleIfAccepted(DialogOfCircle dialogOfCircle) {
		acceptedValues = dialogOfCircle.isValuesAreAccepted();
		if (acceptedValues) {
			Circle circleToAdd = setCircleValues(dialogOfCircle, clickedPoint);
			commandAddShape(circleToAdd);
		}
	}

	private void drawDonutSetDialog() {
		DialogOfDonut dialogOfDonut = new DialogOfDonut();
		setNotEditableFields(dialogOfDonut);
		setFieldForXYCoordinateAndBorderColor(dialogOfDonut, clickedPoint);
		dialogOfDonut.setPickedInsideColor(frame.getLeftToolBar().getButtonInsideColor().getBackground());
		dialogOfDonut.setVisible(true);

		drawDonutIfAccepted(dialogOfDonut);
	}

	public void drawDonutIfAccepted(DialogOfDonut dialogOfDonut) {
		acceptedValues = dialogOfDonut.isValuesAreAccepted();
		if (acceptedValues) {
			Donut donutToAdd = setDonutValues(dialogOfDonut, clickedPoint);
			commandAddShape(donutToAdd);
		}
	}

	private void drawHexagonSetDialog() {
		DialogOfHexagon dialogOfHexagon = new DialogOfHexagon();
		setNotEditableFields(dialogOfHexagon);
		setFieldForXYCoordinateAndBorderColor(dialogOfHexagon, clickedPoint);
		dialogOfHexagon.setPickedInsideColor(frame.getLeftToolBar().getButtonInsideColor().getBackground());
		dialogOfHexagon.setVisible(true);

		drawHexagonIfAccepted(dialogOfHexagon);
	}

	public void drawHexagonIfAccepted(DialogOfHexagon dialogOfHexagon) {
		acceptedValues = dialogOfHexagon.isValuesAreAccepted();
		if (acceptedValues) {
			HexagonAdapter hexagonToAdd = setHexagonValues(dialogOfHexagon, clickedPoint);
			commandAddShape(hexagonToAdd);
		}
	}

	private void setNotEditableFields(DialogOfShape dialogOfShape) {
		dialogOfShape.getTextFieldXCoordinate().setEditable(false);
		dialogOfShape.getTextFieldYCoordinate().setEditable(false);
	}

	private void setFieldForXYCoordinateAndBorderColor(DialogOfShape dialogOfShape, Point point) {
		dialogOfShape.getTextFieldXCoordinate().setText(Integer.toString(point.getXCoordinate()));
		dialogOfShape.getTextFieldYCoordinate().setText(Integer.toString(point.getYCoordinate()));
		dialogOfShape.setPickedBorderColor(frame.getLeftToolBar().getButtonBorderColor().getBackground());
	}

	public void paint(Graphics g) {
		frame.getView().paint(g);
		Iterator<Shape> iteratorThroughShape = model.getShapes().iterator();
		while (iteratorThroughShape.hasNext()) {
			Shape castedIteratorToShape = ((Shape) iteratorThroughShape.next());
			castedIteratorToShape.draw(g);
		}
	}

	public void editDrawnShape() {
		selectedShape = model.getSelected().get(model.getSelected().size() - 1);

		if (selectedShape instanceof Point) {
			editDrawnPointSetDialog();
		} else if (selectedShape instanceof Line) {
			editDrawnLineSetDialog();
		} else if (selectedShape instanceof Rectangle) {
			editDrawnRectangleSetDialog();
		} else if (selectedShape instanceof Donut) {
			editDrawnDonutSetDialog();
		} else if (selectedShape instanceof Circle) {
			editDrawnCircleSetDialog();
		} else if (selectedShape instanceof HexagonAdapter) {
			editDrawnHexagonSetDialog();
		} else {
			startPoint = null;
		}
		frame.getView().repaint();
	}

	private void editDrawnPointSetDialog() {
		DialogOfPoint dialogOfPoint = new DialogOfPoint();
		Point selectedPoint = (Point) selectedShape;
		setFieldForXYCoordinateAndBorderColor(dialogOfPoint, selectedPoint);
		dialogOfPoint.setVisible(true);

		editDrawnPointIfAccepted(dialogOfPoint, selectedPoint);
	}

	public void editDrawnPointIfAccepted(DialogOfPoint dialogOfPoint, Point selectedPoint) {
		acceptedValues = dialogOfPoint.isValuesAreAccepted();
		if (acceptedValues) {
			Point pointWithNewState = setPointValues(dialogOfPoint);
			Color borderColor = dialogOfPoint.getPickedBorderColor();
			(pointWithNewState).setBorderColor(borderColor);
			(pointWithNewState).setSelected(true);

			commandEditShape(selectedPoint, pointWithNewState);
		}
	}

	private void editDrawnLineSetDialog() {
		DialogOfLine dialogOfLine = new DialogOfLine();
		Line selectedLine = (Line) selectedShape;
		setFieldForXYCoordinateAndBorderColor(dialogOfLine, selectedLine.getStartPoint());
		setFieldForXYCoordinateEndPoint(dialogOfLine, ((Line) selectedShape).getEndPoint());
		dialogOfLine.setVisible(true);

		editDrawnLineIfAccepted(dialogOfLine, selectedLine);
	}

	public void editDrawnLineIfAccepted(DialogOfLine dialogOfLine, Line selectedLine) {
		acceptedValues = dialogOfLine.isValuesAreAccepted();
		if (acceptedValues) {
			Point startPoint = setPointValues(dialogOfLine);
			Point endPoint = new Point(dialogOfLine.getXCoordinateValueEndPoint(),
					dialogOfLine.getYCoordinateValueEndPoint());
			Color borderColor = dialogOfLine.getPickedBorderColor();
			Line lineWithNewState = new Line(startPoint, endPoint, borderColor);
			lineWithNewState.setSelected(true);

			commandEditShape(selectedLine, lineWithNewState);
		}
	}

	private void editDrawnRectangleSetDialog() {
		DialogOfRectangle dialogOfRectangle = new DialogOfRectangle();
		Rectangle selectedRectangle = ((Rectangle) selectedShape);
		setFieldForXYCoordinateAndBorderColor(dialogOfRectangle, selectedRectangle.getUpperLeftPoint());
		dialogOfRectangle.setTextFieldHeight(Integer.toString(((Rectangle) selectedShape).getHeight()));
		dialogOfRectangle.setTextFieldWidth(Integer.toString(((Rectangle) selectedShape).getWidth()));
		dialogOfRectangle.setPickedInsideColor(((Rectangle) selectedShape).getInsideColor());
		dialogOfRectangle.setVisible(true);

		editDrawnRectangleIfAccepted(dialogOfRectangle, selectedRectangle);
	}

	public void editDrawnRectangleIfAccepted(DialogOfRectangle dialogOfRectangle, Rectangle selectedRectangle) {
		acceptedValues = dialogOfRectangle.isValuesAreAccepted();
		if (acceptedValues) {
			Point upperLeftPoint = setPointValues(dialogOfRectangle);
			Rectangle rectangleWithNewState = setRectangleValues(dialogOfRectangle, upperLeftPoint);
			rectangleWithNewState.setSelected(true);

			commandEditShape(selectedRectangle, rectangleWithNewState);
		}
	}

	private void editDrawnDonutSetDialog() {
		DialogOfDonut dialogOfDonut = new DialogOfDonut();
		Donut selectedDonut = (Donut) selectedShape;
		setFieldForXYCoordinateAndBorderColor(dialogOfDonut, selectedDonut.getCenter());
		dialogOfDonut.setTextFieldRadius(Integer.toString(((Donut) selectedShape).getRadius()));
		dialogOfDonut.setTextFieldInnerRadius(Integer.toString(((Donut) selectedShape).getInnerRadius()));
		dialogOfDonut.setPickedInsideColor((((Donut) selectedShape).getInsideColor()));
		dialogOfDonut.setVisible(true);

		editDrawnDonutIfAccepted(dialogOfDonut, selectedDonut);
	}

	public void editDrawnDonutIfAccepted(DialogOfDonut dialogOfDonut, Donut selectedDonut) {
		acceptedValues = dialogOfDonut.isValuesAreAccepted();
		if (acceptedValues) {
			Point center = setPointValues(dialogOfDonut);
			Donut donutWithNewState = setDonutValues(dialogOfDonut, center);
			donutWithNewState.setSelected(true);

			commandEditShape(selectedDonut, donutWithNewState);
		}
	}

	private void editDrawnCircleSetDialog() {
		DialogOfCircle dialogOfCircle = new DialogOfCircle();
		Circle selectedCircle = (Circle) selectedShape;
		setFieldForXYCoordinateAndBorderColor(dialogOfCircle, selectedCircle.getCenter());
		dialogOfCircle.setTextFieldRadius(Integer.toString(((Circle) selectedShape).getRadius()));
		dialogOfCircle.setPickedInsideColor((((Circle) selectedShape).getInsideColor()));
		dialogOfCircle.setVisible(true);

		editDrawnCircleIfAccepted(dialogOfCircle, selectedCircle);
	}

	public void editDrawnCircleIfAccepted(DialogOfCircle dialogOfCircle, Circle selectedCircle) {
		acceptedValues = dialogOfCircle.isValuesAreAccepted();
		if (acceptedValues) {
			Point center = setPointValues(dialogOfCircle);
			Circle circleWithNewState = setCircleValues(dialogOfCircle, center);
			circleWithNewState.setSelected(true);

			commandEditShape(selectedCircle, circleWithNewState);
		}
	}

	private void editDrawnHexagonSetDialog() {
		DialogOfHexagon dialogOfHexagon = new DialogOfHexagon();
		HexagonAdapter selectedHexagon = (HexagonAdapter) selectedShape;
		Point centerOfHexagon = new Point(selectedHexagon.getXCoordinateOfHexagon(),
				selectedHexagon.getYCoordinateOfHexagon());
		setFieldForXYCoordinateAndBorderColor(dialogOfHexagon, centerOfHexagon);
		dialogOfHexagon.setTextFieldRadius(Integer.toString(((HexagonAdapter) selectedShape).getRadius()));
		dialogOfHexagon.setPickedInsideColor(((HexagonAdapter) selectedShape).getInsideColor());
		dialogOfHexagon.setVisible(true);

		editDrawnHexagoneIfAccepted(dialogOfHexagon, selectedHexagon);
	}

	public void editDrawnHexagoneIfAccepted(DialogOfHexagon dialogOfHexagon, HexagonAdapter selectedHexagon) {
		acceptedValues = dialogOfHexagon.isValuesAreAccepted();
		if (acceptedValues) {
			Point center = setPointValues(dialogOfHexagon);
			HexagonAdapter hexagonWithNewState = setHexagonValues(dialogOfHexagon, center);
			hexagonWithNewState.setSelected(true);

			commandEditShape(selectedHexagon, hexagonWithNewState);
		}
	}

	private Point setPointValues(DialogOfShape dialogOfShape) {
		int xCoorOfPoint = dialogOfShape.getXCoordinateValue();
		int yCoorOfPoint = dialogOfShape.getYCoordinateValue();

		return new Point(xCoorOfPoint, yCoorOfPoint);
	}

	private Rectangle setRectangleValues(DialogOfRectangle dialogOfRectangle, Point upperLeftPoint) {
		int height = dialogOfRectangle.getValueHeight();
		int width = dialogOfRectangle.getValueWidth();
		setColorsInDialog(dialogOfRectangle);

		return new Rectangle(upperLeftPoint, height, width, insideColor, borderColor);
	}

	private Circle setCircleValues(DialogOfCircle dialogOfCircle, Point center) {
		int radius = dialogOfCircle.getValueRadius();
		setColorsInDialog(dialogOfCircle);

		return new Circle(center, radius, insideColor, borderColor);
	}

	private Donut setDonutValues(DialogOfDonut dialogOfDonut, Point center) {
		int innerRadius = dialogOfDonut.getValueInnerRadius();
		int outherRadius = dialogOfDonut.getValueRadius();
		setColorsInDialog(dialogOfDonut);

		return new Donut(center, outherRadius, innerRadius, borderColor, insideColor);
	}

	private HexagonAdapter setHexagonValues(DialogOfHexagon dialogOfHexagon, Point center) {
		int radius = dialogOfHexagon.getValueHexRadius();
		setColorsInDialog(dialogOfHexagon);

		return new HexagonAdapter(center, radius, insideColor, borderColor);
	}

	private void setColorsInDialog(DialogOfSurfaceShape dialogOfSurfaceShape) {
		insideColor = dialogOfSurfaceShape.getPickedInsideColor();
		borderColor = dialogOfSurfaceShape.getPickedBorderColor();
	}

	public void deleteDrawnShape() {
		if (model.getSelected().size() != 0) {
			if (JOptionPane.showConfirmDialog(new JFrame(), "Please confirm deletion.", "Confirm",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				commandRemoveShape(model.getSelected());
				checkIfRedoListIsEmpty();
				buttonsUndoEnabledRedoDisabled();
			}
		}
		frame.getView().repaint();
	}

	public void commandRemoveShape(ArrayList<Shape> selectedShapes) {
		CommandRemoveSelectedShapes commandRemoveSelectedShapes = new CommandRemoveSelectedShapes(model,
				selectedShapes);
		commandRemoveSelectedShapes.executeCommand();
		addCommandToTheLogList(commandRemoveSelectedShapes);
		model.getSelected().clear();
	}

	public void undoCommand() {
		undoRedoCommand.unexecuteCommand();
		String stringOfCommandToUndo = undoRedoCommand.commandToString();
		frame.getDefaultListModel().addElement(stringOfCommandToUndo);
		logCommandList.add(stringOfCommandToUndo);

		changeButtonsUndoRedoState();

		frame.getView().repaint();
	}

	public void redoCommand() {
		undoRedoCommand.executeCommand();
		String stringOfCommandToRedo = undoRedoCommand.commandToString();
		frame.getDefaultListModel().addElement(stringOfCommandToRedo);
		logCommandList.add(stringOfCommandToRedo);

		changeButtonsUndoRedoState();

		frame.getView().repaint();
	}

	private void changeButtonsUndoRedoState() {
		if (undoRedoCommand.getRedoCommands().isEmpty() && !undoRedoCommand.getCommands().isEmpty()) {
			buttonsUndoEnabledRedoDisabled();
		} else if (undoRedoCommand.getCommands().isEmpty() && !undoRedoCommand.getRedoCommands().isEmpty()) {
			buttonsUndoDisabledRedoEnabled();
		} else if (!undoRedoCommand.getCommands().isEmpty() && !undoRedoCommand.getRedoCommands().isEmpty()) {
			buttonsUndoAndRedoEnable();
		}
	}

	private void buttonsUndoDisabledRedoEnabled() {
		frame.getLeftToolBar().getButtonUndo().setEnabled(false);
		frame.getLeftToolBar().getButtonRedo().setEnabled(true);
	}

	private void buttonsUndoEnabledRedoDisabled() {
		frame.getLeftToolBar().getButtonUndo().setEnabled(true);
		frame.getLeftToolBar().getButtonRedo().setEnabled(false);
	}

	private void buttonsUndoAndRedoEnable() {
		frame.getLeftToolBar().getButtonUndo().setEnabled(true);
		frame.getLeftToolBar().getButtonRedo().setEnabled(true);
	}

	public void toBackCommand() {
		Shape shape = model.getSelectedShape();
		CommandToBack cmdToBack = new CommandToBack(model, shape);
		cmdToBack.executeCommand();
		addCommandToTheLogList(cmdToBack);
		checkIfRedoListIsEmpty();
		frame.getView().repaint();
	}

	public void bringToBackCommand() {
		Shape shape = model.getSelectedShape();
		CommandBringToBack cmdBringToBack = new CommandBringToBack(model, shape);
		cmdBringToBack.executeCommand();
		addCommandToTheLogList(cmdBringToBack);
		checkIfRedoListIsEmpty();
		frame.getView().repaint();
	}

	public void toFrontCommand() {
		Shape shape = model.getSelectedShape();
		CommandToFront cmdToFront = new CommandToFront(model, shape);
		cmdToFront.executeCommand();
		addCommandToTheLogList(cmdToFront);
		checkIfRedoListIsEmpty();
		frame.getView().repaint();
	}

	public void bringToFrontCommand() {
		Shape shape = model.getSelectedShape();
		CommandBringToFront cmdBringToFront = new CommandBringToFront(model, shape);
		cmdBringToFront.executeCommand();
		addCommandToTheLogList(cmdBringToFront);
		checkIfRedoListIsEmpty();
		frame.getView().repaint();
	}

	public void addCommandToTheLogList(Command commandToAdd) {
		undoRedoCommand.getCommands().push(commandToAdd);
		String commandToString = commandToAdd.commandToString() + "\n";
		frame.getDefaultListModel().addElement(commandToString);
		logCommandList.add(commandToString);
	}

	public void enableButtons() {
		if (model.getSelected().size() != 0) {
			if (model.getSelected().size() == 1) {
				enableButtonFireChangeObserver.setEditEnabled(true);
				enablingMovingOnZButtons();
			} else {
				enableButtonFireChangeObserver.setEditEnabled(false);
				disablingMovingOnZButtons();
			}
			enableButtonFireChangeObserver.setDeleteEnabled(true);
		} else {
			enableButtonFireChangeObserver.setDeleteEnabled(false);
			enableButtonFireChangeObserver.setEditEnabled(false);
			disablingMovingOnZButtons();
		}
	}

	public void enablingMovingOnZButtons() {
		Shape selectedShape = model.getSelectedShape();
		if (model.getShapes().size() == 1) {
			disablingMovingOnZButtons();
		} else {
			if (selectedShape.equals(model.get(model.getShapes().size() - 1))) {
				enableButtonFireChangeObserver.setBringToFrontEnabled(false);
				enableButtonFireChangeObserver.setToFrontEnabled(false);
				enableButtonFireChangeObserver.setBringToBackEnabled(true);
				enableButtonFireChangeObserver.setToBackEnabled(true);
			} else if (selectedShape.equals(model.get(0))) {
				enableButtonFireChangeObserver.setBringToFrontEnabled(true);
				enableButtonFireChangeObserver.setToFrontEnabled(true);
				enableButtonFireChangeObserver.setBringToBackEnabled(false);
				enableButtonFireChangeObserver.setToBackEnabled(false);
			} else {
				enableButtonFireChangeObserver.setBringToFrontEnabled(true);
				enableButtonFireChangeObserver.setToFrontEnabled(true);
				enableButtonFireChangeObserver.setBringToBackEnabled(true);
				enableButtonFireChangeObserver.setToBackEnabled(true);
			}
		}
	}
	
	private void disablingMovingOnZButtons() {
		enableButtonFireChangeObserver.setBringToBackEnabled(false);
		enableButtonFireChangeObserver.setBringToFrontEnabled(false);
		enableButtonFireChangeObserver.setToBackEnabled(false);
		enableButtonFireChangeObserver.setToFrontEnabled(false);
	}

	public void exportLog() {
		if (logCommandList.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "The log is empty!", "Error", JOptionPane.WARNING_MESSAGE);
		} else {
			ArrayList<Object> listOfCommandToSave = new ArrayList<Object>();
			listOfCommandToSave.addAll(logCommandList);
			ExportImportLog exportLog = new ExportImportLog();
			ExportImportManager exportManager = new ExportImportManager(exportLog);
			FileDialog fileDialog = new FileDialog();
			String extension = "log";
			String path = fileDialog.showSaveFileDialog(extension);
			if (path != null) {
				exportManager.exportData(listOfCommandToSave, path);
			}
		}
	}

	public void exportShapes() {
		if (model.getShapes().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "The drawing is empty!", "Error", JOptionPane.WARNING_MESSAGE);
		} else {
			ArrayList<Object> objectFromDrawToSave = new ArrayList<Object>();
			objectFromDrawToSave.add(model.getShapes());
			ExportImportDrawing exportDrawing = new ExportImportDrawing();
			ExportImportManager exportManager = new ExportImportManager(exportDrawing);
			FileDialog fileDialog = new FileDialog();
			String extension = "drwg";
			String path = fileDialog.showSaveFileDialog(extension);
			if (path != null) {
				exportManager.exportData(objectFromDrawToSave, path);
			}
		}
	}

	public void importLog() {
		ExportImportLog importLog = new ExportImportLog();
		ExportImportManager importManager = new ExportImportManager(importLog);
		FileDialog fileDialog = new FileDialog();
		String path = fileDialog.showOpenFileDialog();
		if (path != null) {
			if (path.substring(path.indexOf(".") + 1, path.length()).equals("log")) {
				importedLogFile = importManager.importData(path);
				frame.getDefaultListModel().addElement("Imported log from log file");
				frame.getLeftToolBar().getButtonExecuteLog().setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Import LOG expected!");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void importDrawing() {
		ExportImportManager importManager = new ExportImportManager(new ExportImportDrawing());
		FileDialog fileDialog = new FileDialog();
		String path = fileDialog.showOpenFileDialog();
		if (path != null) {
			if (path.substring(path.indexOf(".") + 1, path.length()).equals("drwg")) {
				ArrayList<Object> log = importManager.importData(path);
				for (Shape s : (ArrayList<Shape>) log.get(0)) {
					model.add(s);
				}
				frame.getView().repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Import DRAWING expected!");
			}
		}
	}

	public void setLineColor() {
		defaultBorderColor = JColorChooser.showDialog(null, "Choose a color!", defaultInnerColor);
		if (defaultBorderColor != null)
			frame.getLeftToolBar().getButtonBorderColor().setBackground(defaultBorderColor);
		frame.getView().repaint();
	}

	public void setFillColor() {
		defaultInnerColor = JColorChooser.showDialog(null, "Choose a color!", defaultInnerColor);
		if (defaultInnerColor != null)
			frame.getLeftToolBar().getButtonInsideColor().setBackground(defaultInnerColor);
		frame.getView().repaint();
	}

	public void executeLog() {
		if (importedLogFile.size() != 0) {
			String lineToExecute = (String) importedLogFile.get(0);
			importedLogFile.remove(0);

			String getCommand = lineToExecute.split("[)|\\,|\\(]|\\=|\\:.")[0];
			String getShape = lineToExecute.split("[)|\\,|\\(]|\\=|\\:")[1];
			Shape selectedShape = selectedShape();

			if (getCommand.equals("Added")) {
				addShapeFromString(getShape, lineToExecute);
			} else if (getCommand.equals("Selected")) {
				selectShapeFromString(getShape, lineToExecute);
			} else if (getCommand.equals("Deselected")) {
				deselectShapeFromString(getShape, lineToExecute);
			} else if (getCommand.equals("Removed")) {
				commandRemoveShape(model.getSelected());
				checkIfRedoListIsEmpty();
			} else if (getCommand.contains("Updated shape")) {
				String typeOfShape = lineToExecute.split("[)|\\\\,|\\\\ (]|\\\\=|\\\\: ")[3];
				String newStateForShape = lineToExecute.split(" to ")[1];
				updateShapeFromString(typeOfShape, newStateForShape, selectedShape);
			} else if (getCommand.equals("Undo command")) {
				undoCommand();
			} else if (getCommand.equals("Redo command")) {
				redoCommand();
			} else if (getCommand.equals("Bring To Front")) {
				bringToFrontCommand();
			} else if (getCommand.equals("Bring To Back")) {
				bringToBackCommand();
			} else if (getCommand.equals("To Front")) {
				toFrontCommand();
			} else if (getCommand.equals("To Back")) {
				toBackCommand();
			}
		} else {
			buttonsDisable();
		}
		frame.getView().repaint();
	}

	private void buttonsDisable() {
		frame.getLeftToolBar().getButtonExecuteLog().setEnabled(false);
		frame.getLeftToolBar().getButtonRedo().setEnabled(false);
	}

	public void addShapeFromString(String shapeString, String lineToExecute) {
		Shape createdShape = null;
		if (shapeString.equals(" Point")) {
			createdShape = createPointFromString(lineToExecute, false);
		} else if (shapeString.equals(" Line")) {
			createdShape = createLineFromString(lineToExecute, false);
		} else if (shapeString.contains(" Rectangle")) {
			createdShape = createRectangleFromString(lineToExecute, false);
		} else if (shapeString.equals(" Circle")) {
			createdShape = createCircleFromString(lineToExecute, false);
		} else if (shapeString.equals(" Donut")) {
			createdShape = createDonutFromString(lineToExecute, false);
		} else if (shapeString.equals(" Hexagon")) {
			createdShape = createHexagonFromString(lineToExecute, false);
		}
		commandAddShape(createdShape);
		checkIfRedoListIsEmpty();
	}

	public void commandAddShape(Shape shapeToAdd) {
		CommandAddShape commandAddShape = new CommandAddShape(model, shapeToAdd);
		commandAddShape.executeCommand();
		addCommandToTheLogList(commandAddShape);
	}

	public void selectShapeFromString(String shapeString, String line) {
		Shape createdShapeFromString = null;
		if (shapeString.equals(" Point")) {
			createdShapeFromString = createPointFromString(line, false);
		} else if (shapeString.equals(" Line")) {
			createdShapeFromString = createLineFromString(line, false);
		} else if (shapeString.equals(" Rectangle")) {
			createdShapeFromString = createRectangleFromString(line, false);
		} else if (shapeString.equals(" Circle")) {
			createdShapeFromString = createCircleFromString(line, false);
		} else if (shapeString.equals(" Donut")) {
			createdShapeFromString = createDonutFromString(line, false);
		} else if (shapeString.equals(" Hexagon")) {
			createdShapeFromString = createHexagonFromString(line, false);
		}
		commandSelectShape(createdShapeFromString);
		checkIfRedoListIsEmpty();
	}

	public void commandSelectShape(Shape shape) {
		CommadSelectShape selectShape = new CommadSelectShape(model,
				model.getShapes().get(model.getShapes().indexOf(shape)));
		selectShape.executeCommand();
		addCommandToTheLogList(selectShape);
	}

	public void deselectShapeFromString(String shapeString, String line) {
		Shape createdShapeFromString = null;
		for (Shape s : model.getShapes()) {
			String str = " " + s.toString().split("[)|\\,|\\(]|\\=|\\:.")[0];
			if (shapeString.equals(str)) {
				if (shapeString.equals(" Point")) {
					createdShapeFromString = createPointFromString(line, false);
				} else if (shapeString.equals(" Line")) {
					createdShapeFromString = createLineFromString(line, false);
				} else if (shapeString.equals(" Rectangle")) {
					createdShapeFromString = createRectangleFromString(line, false);
				} else if (shapeString.equals(" Circle")) {
					createdShapeFromString = createCircleFromString(line, false);
				} else if (shapeString.equals(" Donut")) {
					createdShapeFromString = createDonutFromString(line, false);
				} else if (shapeString.equals(" Hexagon")) {
					createdShapeFromString = createHexagonFromString(line, false);
				}
				commandDeselectShape(createdShapeFromString);
			}
		}
		checkIfRedoListIsEmpty();
	}

	public void commandDeselectShape(Shape shape) {
		CommadDeselectShape deselectShape = new CommadDeselectShape(model,
				model.getShapes().get(model.getShapes().indexOf(shape)));
		deselectShape.executeCommand();
		addCommandToTheLogList(deselectShape);
	}

	public void updateShapeFromString(String shapeFromCommand, String newShape, Shape selected) {
		Shape newStateForShape = null;
		if (shapeFromCommand.equals("Point:")) {
			newStateForShape = createPointFromString("Updated: " + newShape, true);
		} else if (shapeFromCommand.contains("Line:")) {
			newStateForShape = createLineFromString("Updated: " + newShape, true);
		} else if (shapeFromCommand.equals("Rectangle:")) {
			newStateForShape = createRectangleFromString("Updated: " + newShape, true);
		} else if (shapeFromCommand.equals("Circle:")) {
			newStateForShape = createCircleFromString("Updated: " + newShape, true);
		} else if (shapeFromCommand.equals("Donut:")) {
			newStateForShape = createDonutFromString("Updated: " + newShape, true);
		} else if (shapeFromCommand.equals("Hexagon:")) {
			newStateForShape = createHexagonFromString("Updated: " + newShape, true);
		}
		commandEditShape(selected, newStateForShape);
		checkIfRedoListIsEmpty();
	}

	public void commandEditShape(Shape oldState, Shape newState) {
		CommandUpdateShape commandUpdate = new CommandUpdateShape(oldState, newState);
		commandUpdate.executeCommand();
		addCommandToTheLogList(commandUpdate);
		checkIfRedoListIsEmpty();
	}

	public Point createPointFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getX = string[3];
		String getY = string[5];
		String col = string[10];
		Color colPoint = Color.decode(col);

		Point newPoint = new Point(Integer.parseInt(getX), Integer.parseInt(getY), colPoint, true);
		newPoint.setSelected(isUpdate);
		return newPoint;
	}

	public Line createLineFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getStartPointX = string[3];
		String getStartPointY = string[5];
		String getEndPointX = string[8];
		String getEndPointY = string[10];
		String lineCol = string[15];
		Color colLine = Color.decode(lineCol);

		Line newLine = new Line(new Point(Integer.parseInt(getStartPointX), Integer.parseInt(getStartPointY)),
				new Point(Integer.parseInt(getEndPointX), Integer.parseInt(getEndPointY)), colLine, true);
		newLine.setSelected(isUpdate);
		return newLine;
	}

	public Rectangle createRectangleFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getUpperLeftX = string[5];
		String getUpperLeftY = string[6];
		String getHeight = string[10];
		String getWidth = string[13];
		String fill = string[19];
		Color colFill = Color.decode(fill);
		String lineCol = string[16];
		Color colLine = Color.decode(lineCol);

		Point upperLeftPoint = new Point(Integer.parseInt(getUpperLeftX), Integer.parseInt(getUpperLeftY));
		Rectangle newRecatnagle = new Rectangle(upperLeftPoint, Integer.parseInt(getHeight), Integer.parseInt(getWidth),
				colFill, colLine);
		newRecatnagle.setSelected(isUpdate);
		return newRecatnagle;
	}

	public Circle createCircleFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getCenterX = string[5];
		String getCenterY = string[6];
		String getRadius = string[10];
		String fill = string[16];
		Color colFill = Color.decode(fill);
		String lineCol = string[13];
		Color colLine = Color.decode(lineCol);

		Point center = new Point(Integer.parseInt(getCenterX), Integer.parseInt(getCenterY));
		Circle newCircle = new Circle(center, Integer.parseInt(getRadius), colFill, colLine);
		newCircle.setSelected(isUpdate);
		return newCircle;
	}

	public Donut createDonutFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getCenterX = string[5];
		String getCenterY = string[6];
		String getRadius = string[10];
		String fill = string[16];
		Color colFill = Color.decode(fill);
		String lineCol = string[13];
		Color colLine = Color.decode(lineCol);
		String getInnerRadius = string[19];

		Point center = new Point(Integer.parseInt(getCenterX), Integer.parseInt(getCenterY));
		Donut newDonut = new Donut(center, Integer.parseInt(getRadius), Integer.parseInt(getInnerRadius), colLine,
				colFill);
		newDonut.setSelected(isUpdate);
		return newDonut;
	}

	public HexagonAdapter createHexagonFromString(String input, boolean isUpdate) {
		String[] string = input.split("[)|\\, |\\(]|\\=|\\:] ");
		String getCenterX = string[3];
		String getCenterY = string[6];
		String getRadius = string[9];
		String fill = string[15];
		Color colFill = Color.decode(fill);
		String lineCol = string[12];
		Color colLine = Color.decode(lineCol);

		Point center = new Point(Integer.parseInt(getCenterX), Integer.parseInt(getCenterY));
		HexagonAdapter newHexagon = new HexagonAdapter(center, Integer.parseInt(getRadius), colFill, colLine);
		newHexagon.setSelected(isUpdate);
		return newHexagon;
	}

	public Shape selectedShape() {
		for (Shape s : model.getShapes()) {
			if (s.isSelected()) {
				return s;
			}
		}
		return null;
	}

	public void newDrawing() {
		model.getShapes().clear();
		model.getSelected().clear();
		frame.getLeftToolBar().getButtonUndo().setEnabled(false);
		logCommandList.clear();
		undoRedoCommand.getCommands().clear();
		frame.getDefaultListModel().removeAllElements();
		frame.getView().repaint();
	}

	public void checkIfRedoListIsEmpty() {
		boolean checkIfRedoListIsEmpty = undoRedoCommand.getRedoCommands().empty();
		if (!checkIfRedoListIsEmpty == false) {
			undoRedoCommand.getRedoCommands().clear();
			frame.getLeftToolBar().getButtonRedo().setEnabled(false);
		}
	}

	public List<String> getLogCommandList() {
		return logCommandList;
	}

	public ArrayList<Object> getImportedLog() {
		return importedLogFile;
	}

	public void setClickedPoint(Point clickedPoint) {
		this.clickedPoint = clickedPoint;
	}

	public void setUndoRedoCommand(CommandUndoRedo undoRedoCommand) {
		this.undoRedoCommand = undoRedoCommand;
	}
}
