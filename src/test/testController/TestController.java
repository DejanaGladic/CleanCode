package test.testController;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import commands.CommadSelectShape;
import commands.CommandAddShape;
import commands.CommandUndoRedo;
import controller.DrawingController;
import dialogs.DialogOfCircle;
import dialogs.DialogOfDonut;
import dialogs.DialogOfHexagon;
import dialogs.DialogOfLine;
import dialogs.DialogOfPoint;
import dialogs.DialogOfRectangle;
import dialogs.DialogOfShape;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Circle;
import shapes.Donut;
import shapes.HexagonAdapter;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class TestController {

	DrawingModel model;
	DrawingFrame frame;
	DrawingController controller;
	DrawingController mockController;
	MouseEvent clickOnPanel;
	Point clickedPoint;
	Rectangle testedShape;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		frame.getView().setModel(model);
		frame.setController(controller);

		clickOnPanel = mock(MouseEvent.class);
		when(clickOnPanel.getX()).thenReturn(150);
		when(clickOnPanel.getY()).thenReturn(250);
		clickedPoint = new Point(clickOnPanel.getX(), clickOnPanel.getY(), Color.black);
		controller.setClickedPoint(clickedPoint);

		testedShape = new Rectangle(clickedPoint, 40, 50, Color.white, Color.black);
	}

	@Test
	public void testSelectOrDeselectShapeExpectedSelect() {
		testedShape.setSelected(false);
		model.add(testedShape);

		controller.selectOrDeselectShape();

		assertTrue(testedShape.isSelected());
	}

	@Test
	public void testSelectOrDeselectShapeExpectedDeselect() {
		testedShape.setSelected(true);
		model.add(testedShape);

		controller.selectOrDeselectShape();

		assertFalse(testedShape.isSelected());
	}

	@Test
	public void testDrawPointIfAccepted() {
		DialogOfPoint dialogOfPoint = new DialogOfPoint();
		setPoint(dialogOfPoint);
		dialogOfPoint.setValuesAreAccepted(true);

		controller.drawPointIfAccepted(dialogOfPoint);

		Shape drawnShape = new Point(150, 250, Color.red);
		assertTrue(model.doesModelContainShape(drawnShape));
	}

	@Test
	public void testDrawLineIfAccepted() {
		DialogOfLine dialogOfLine = new DialogOfLine();
		setPoint(dialogOfLine);
		dialogOfLine.setXCoordinateValueEndPoint(350);
		dialogOfLine.setYCoordinateValueEndPoint(450);
		dialogOfLine.setValuesAreAccepted(true);

		controller.drawLineIfAccepted(dialogOfLine);

		Point startPoint = new Point(150, 250);
		Point endPoint = new Point(350, 450);
		Shape drawnShape = new Line(startPoint, endPoint, Color.red);
		assertTrue(model.doesModelContainShape(drawnShape));
	}

	@Test
	public void testDrawRectangleIfAccepted() {
		DialogOfRectangle dialogOfRectangle = new DialogOfRectangle();
		setPoint(dialogOfRectangle);
		dialogOfRectangle.setValueHeight(40);
		dialogOfRectangle.setValueWidth(50);
		dialogOfRectangle.setValuesAreAccepted(true);

		controller.drawRectangleIfAccepted(dialogOfRectangle);

		assertTrue(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testDrawCircleIfAccepted() {
		DialogOfCircle dialogOfCircle = new DialogOfCircle();
		setPoint(dialogOfCircle);
		dialogOfCircle.setValueRadius(15);
		dialogOfCircle.setValuesAreAccepted(true);

		controller.drawCircleIfAccepted(dialogOfCircle);

		Point center = new Point(150, 250);
		Shape drawnShape = new Circle(center, 15, Color.red, Color.white);
		assertTrue(model.doesModelContainShape(drawnShape));
	}

	@Test
	public void testIfDrawDonutIfNotAccepted() {
		DialogOfDonut dialogOfDonut = new DialogOfDonut();
		setPoint(dialogOfDonut);
		dialogOfDonut.setValueRadius(15);
		dialogOfDonut.setValueInnerRadius(10);
		dialogOfDonut.setValuesAreAccepted(false);

		controller.drawDonutIfAccepted(dialogOfDonut);

		Point center = new Point(150, 250);
		Shape drawnShape = new Donut(center, 15, 10, Color.red, Color.white);
		assertFalse(model.doesModelContainShape(drawnShape));
	}

	@Test
	public void testIfDrawHexagonIfNotAccepted() {
		DialogOfHexagon dialogOfHexagon = new DialogOfHexagon();
		setPoint(dialogOfHexagon);
		dialogOfHexagon.setValueHexRadius(15);
		dialogOfHexagon.setValuesAreAccepted(false);

		controller.drawHexagonIfAccepted(dialogOfHexagon);

		Point center = new Point(150, 250);
		Shape drawnShape = new HexagonAdapter(center, 15, Color.red, Color.white);
		assertFalse(model.doesModelContainShape(drawnShape));
	}

	@Test
	public void testCommandAddShape() {
		controller.commandAddShape(testedShape);

		assertTrue(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testEditDrawnRectangleIfAccepted() {
		DialogOfRectangle dialogOfRectangle = new DialogOfRectangle();
		setPoint(dialogOfRectangle);
		dialogOfRectangle.setValueHeight(35);
		dialogOfRectangle.setValueWidth(25);
		dialogOfRectangle.setValuesAreAccepted(true);

		assertEquals(40, testedShape.getHeight());
		controller.editDrawnRectangleIfAccepted(dialogOfRectangle, testedShape);
		assertEquals(35, testedShape.getHeight());
	}

	@Test
	public void testIfEditDrawnCircleIfNotAccepted() {
		Point center = new Point(150, 250);
		Circle drawnShapeToEdit = new Circle(center, 15, Color.red, Color.white, true);

		DialogOfCircle dialogOfCircle = new DialogOfCircle();
		setPoint(dialogOfCircle);
		dialogOfCircle.setValueRadius(35);
		dialogOfCircle.setValuesAreAccepted(false);

		assertEquals(15, drawnShapeToEdit.getRadius());
		controller.editDrawnCircleIfAccepted(dialogOfCircle, drawnShapeToEdit);
		assertEquals(15, drawnShapeToEdit.getRadius());
	}

	@Test
	public void testCommandRemoveOneShape() {
		CommadSelectShape commadSelectShape = new CommadSelectShape(model, testedShape);
		commadSelectShape.executeCommand();

		controller.commandRemoveShape(model.getSelected());

		assertFalse(model.doesModelContainShape(testedShape));
		assertEquals(0, model.getSelected().size());
	}

	@Test
	public void testUndoCommand() {
		CommandUndoRedo commandUndoRedo = new CommandUndoRedo();
		controller.setUndoRedoCommand(commandUndoRedo);
		CommandAddShape commandAddShape = new CommandAddShape(model, testedShape);
		commandAddShape.executeCommand();
		commandUndoRedo.getCommands().add(commandAddShape);

		controller.undoCommand();

		assertTrue(controller.getLogCommandList().size() == 1);
		ArrayList<String> expectedValue = new ArrayList<String>();
		expectedValue.add(
				"Undo command: Added: Rectangle: UpperLeftPoint= (150,250), Height=40, Width=50, OuterColor=-16777216, InnerColor=-1");
		assertEquals(expectedValue, controller.getLogCommandList());
		assertFalse(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testRedoCommand() {
		CommandUndoRedo commandUndoRedo = new CommandUndoRedo();
		controller.setUndoRedoCommand(commandUndoRedo);
		CommandAddShape commandAddShape = new CommandAddShape(model, testedShape);
		commandAddShape.executeCommand();
		commandUndoRedo.getCommands().add(commandAddShape);

		controller.undoCommand();
		controller.redoCommand();

		assertTrue(controller.getLogCommandList().size() == 2);
		ArrayList<String> expectedValue = new ArrayList<String>();
		expectedValue.add(
				"Redo command: Added: Rectangle: UpperLeftPoint= (150,250), Height=40, Width=50, OuterColor=-16777216, InnerColor=-1");
		assertEquals(expectedValue.get(0), controller.getLogCommandList().get(1));
		assertTrue(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testToBackCommand() {
		Rectangle rectangle_two = new Rectangle(clickedPoint, 50, 60, Color.white, Color.black);
		model.add(testedShape);
		model.add(rectangle_two);
		model.addSelected(rectangle_two);

		assertEquals(1, model.getIndexOfShape(rectangle_two));
		controller.toBackCommand();
		assertEquals(0, model.getIndexOfShape(rectangle_two));
	}

	@Test
	public void testToFrontCommand() {
		Rectangle rectangle_two = new Rectangle(clickedPoint, 50, 60, Color.white, Color.black);
		model.add(testedShape);
		model.add(rectangle_two);
		model.addSelected(testedShape);

		assertEquals(0, model.getIndexOfShape(testedShape));
		controller.toFrontCommand();
		assertEquals(1, model.getIndexOfShape(testedShape));
	}

	@Test
	public void testBringToBackCommand() {
		Rectangle rectangle_two = new Rectangle(clickedPoint, 50, 60, Color.white, Color.black);
		Rectangle rectangle_three = new Rectangle(clickedPoint, 60, 70, Color.white, Color.black);
		model.add(testedShape);
		model.add(rectangle_two);
		model.add(rectangle_three);
		model.addSelected(rectangle_three);

		assertEquals(2, model.getIndexOfShape(rectangle_three));
		controller.bringToBackCommand();
		assertEquals(0, model.getIndexOfShape(rectangle_three));
	}

	@Test
	public void testBringToFrontCommand() {
		Rectangle rectangle_two = new Rectangle(clickedPoint, 50, 60, Color.white, Color.black);
		Rectangle rectangle_three = new Rectangle(clickedPoint, 60, 70, Color.white, Color.black);
		model.add(testedShape);
		model.add(rectangle_two);
		model.add(rectangle_three);
		model.addSelected(testedShape);

		assertEquals(0, model.getIndexOfShape(testedShape));
		controller.bringToFrontCommand();
		assertEquals(2, model.getIndexOfShape(testedShape));
	}

	@Test
	public void testAddCommandToTheLogList() {
		CommandAddShape commandAddShape = new CommandAddShape(model, testedShape);

		controller.addCommandToTheLogList(commandAddShape);

		String expectedValue = commandAddShape.commandToString() + "\n";
		assertTrue(expectedValue.equals(controller.getLogCommandList().get(0)));
	}

	@Test
	public void testAddShapeFromString() {
		String shapeString = " Rectangle";
		String logCommand = "Added: " + testedShape.toString();

		controller.addShapeFromString(shapeString, logCommand);

		assertTrue(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testSelectShapeFromString() {
		model.add(testedShape);
		String shapeString = " Rectangle";
		String logCommand = "Selected: " + testedShape.toString();

		controller.selectShapeFromString(shapeString, logCommand);

		assertTrue(model.doesModelContainSelectedShape(testedShape));
	}

	@Test
	public void testCommandSelectShape() {
		model.add(testedShape);

		controller.commandSelectShape(testedShape);

		assertTrue(model.doesModelContainShape(testedShape));
	}

	@Test
	public void testDeselectShapeFromString() {
		model.add(testedShape);
		model.addSelected(testedShape);
		String shapeString = " Rectangle";
		String logCommand = "Deselected: " + testedShape.toString();

		controller.deselectShapeFromString(shapeString, logCommand);

		assertFalse(model.doesModelContainSelectedShape(testedShape));
	}

	@Test
	public void testCommandDeselectShape() {
		model.add(testedShape);
		model.addSelected(testedShape);

		controller.commandDeselectShape(testedShape);

		assertFalse(model.doesModelContainSelectedShape(testedShape));
	}

	@Test
	public void testUpdateShapeFromString() {
		model.add(testedShape);
		String shapeString = "Rectangle:";
		Shape oldStateShape = testedShape;
		String newStateShape = "Rectangle: UpperLeftPoint= (150,250), Height=50, Width=60, OuterColor=-16777216, InnerColor=-1";

		controller.updateShapeFromString(shapeString, newStateShape, oldStateShape);

		assertFalse(testedShape.getHeight() == 40);
		assertFalse(testedShape.getWidth() == 50);
		assertEquals(50, testedShape.getHeight());
		assertEquals(60, testedShape.getWidth());
	}

	@Test
	public void testCreatePointFromString() {
		String pointToCreate = "Added: Point: (150, 250), Color: (-16777216)";
		Point expectedPoint = clickedPoint;
		expectedPoint.setBorderColor(Color.black);
		expectedPoint.setSelected(true);

		assertEquals(expectedPoint, controller.createPointFromString(pointToCreate, true));
	}

	@Test
	public void testCreateLineFromString() {
		String lineToCreate = "Added: Line: (150, 250) (300, 400), Color: (-16777216)";
		Line expectedLine = new Line(new Point(150, 250), new Point(300, 400));
		expectedLine.setBorderColor(Color.black);
		expectedLine.setSelected(true);

		assertEquals(expectedLine, controller.createLineFromString(lineToCreate, true));
	}

	@Test
	public void testCreateRectangleFromString() {
		String rectangleToCreate = "Added: Rectangle: UpperLeftPoint= (150,250), Height=40, Width=50, OuterColor=-16777216, InnerColor=-1";
		Rectangle expectedRectangle = testedShape;
		expectedRectangle.setSelected(true);

		assertEquals(expectedRectangle, controller.createRectangleFromString(rectangleToCreate, true));
	}

	@Test
	public void testCreateCircleFromString() {
		String circleToCreate = "Added: Circle: Center= (150,250), Radius=40, OuterColor=-16777216, InnerColor=-1";
		Circle expectedCircle = new Circle(clickedPoint, 40, Color.white, Color.black);
		expectedCircle.setSelected(true);

		assertEquals(expectedCircle, controller.createCircleFromString(circleToCreate, true));
	}

	@Test
	public void testCreateDonutFromString() {
		String donutToCreate = "Added: Donut: Center= (150,250), Radius=40, OuterColor=-16777216, InnerColor=-1, InnerRadius=30";
		Donut expectedDonut = new Donut(clickedPoint, 40, 30, Color.white, Color.black);
		expectedDonut.setSelected(true);

		assertEquals(expectedDonut, controller.createDonutFromString(donutToCreate, true));
	}

	@Test
	public void testCreateHexagonFromString() {
		String hexagonToCreate = "Added: Hexagon: x=150, y=250, radius=40, borderColor=-16777216, innerColor=-1";
		HexagonAdapter expectedHexagon = new HexagonAdapter(clickedPoint, 40, Color.white, Color.black);
		expectedHexagon.setSelected(true);

		assertEquals(expectedHexagon, controller.createHexagonFromString(hexagonToCreate, true));
	}

	@Test
	public void testSelectedShape() {
		testedShape.setSelected(true);
		model.add(testedShape);

		assertEquals(testedShape, controller.selectedShape());
	}

	@Test
	public void testNonSelectedShape() {
		testedShape.setSelected(false);
		model.add(testedShape);

		assertEquals(null, controller.selectedShape());
	}

	private void setPoint(DialogOfShape dialogOfShape) {
		dialogOfShape.setXCoordinateValue(clickedPoint.getXCoordinate());
		dialogOfShape.setYCoordinateValue(clickedPoint.getYCoordinate());
		dialogOfShape.setPickedBorderColor(Color.red);
	}

}
