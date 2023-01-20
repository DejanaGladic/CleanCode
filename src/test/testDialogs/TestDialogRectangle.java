package test.testDialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfRectangle;

public class TestDialogRectangle {

	private DialogOfRectangle dialogRectangle;

	@Before
	public void setUp() {
		dialogRectangle = new DialogOfRectangle();
	}

	@Test
	public void testButtonOk() {
		dialogRectangle.setXCoordinateValue(10);
		dialogRectangle.setYCoordinateValue(15);
		dialogRectangle.setValueWidth(10);
		dialogRectangle.setValueHeight(20);
		dialogRectangle.getOkButton().doClick();
		assertTrue(dialogRectangle.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyUpperLeftPointFields() throws Exception {
		dialogRectangle.setValueWidth(10);
		dialogRectangle.setValueHeight(20);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogRectangle.checkIfValuesAreFilled();
		assertFalse(dialogRectangle.isValuesAreAccepted());
	}

	@Test
	public void testEmptyHeightWidthFields() throws Exception {
		dialogRectangle.setXCoordinateValue(5);
		dialogRectangle.setYCoordinateValue(15);
		exception.expect(Exception.class);
		exception.expectMessage("Not valid input. Check if all fields have values!");
		dialogRectangle.checkIfValuesAreFilled();
		assertFalse(dialogRectangle.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldsUpperLeftPoint() throws Exception {
		dialogRectangle.setXCoordinateValue(0);
		dialogRectangle.setYCoordinateValue(0);
		dialogRectangle.setValueWidth(10);
		dialogRectangle.setValueHeight(20);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogRectangle.checkIfValuesAreValid();
		assertFalse(dialogRectangle.isValuesAreAccepted());
	}

	@Test
	public void testValidWidthFields() throws Exception {
		dialogRectangle.setXCoordinateValue(10);
		dialogRectangle.setYCoordinateValue(15);
		dialogRectangle.setValueWidth(-10);
		dialogRectangle.setValueHeight(20);
		exception.expect(Exception.class);
		exception.expectMessage("Width must be positive!");
		dialogRectangle.checkIfValuesAreValid();
		assertFalse(dialogRectangle.isValuesAreAccepted());
	}

	@Test
	public void testValidHeightFields() throws Exception {
		dialogRectangle.setXCoordinateValue(10);
		dialogRectangle.setYCoordinateValue(15);
		dialogRectangle.setValueWidth(10);
		dialogRectangle.setValueHeight(-20);
		exception.expect(Exception.class);
		exception.expectMessage("Heigth must be positive!");
		dialogRectangle.checkIfValuesAreValid();
		assertFalse(dialogRectangle.isValuesAreAccepted());
	}
}
