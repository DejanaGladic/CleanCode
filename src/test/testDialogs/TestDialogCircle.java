package test.testDialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfCircle;

public class TestDialogCircle {
	private DialogOfCircle dialogCircle;

	@Before
	public void setUp() {
		dialogCircle = new DialogOfCircle();
	}

	@Test
	public void testButtonOk() {
		dialogCircle.setXCoordinateValue(10);
		dialogCircle.setYCoordinateValue(15);
		dialogCircle.setValueRadius(10);
		dialogCircle.getOkButton().doClick();
		assertTrue(dialogCircle.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyCenterPointFields() throws Exception {
		dialogCircle.setValueRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogCircle.checkIfValuesAreFilled();
		assertFalse(dialogCircle.isValuesAreAccepted());
	}

	@Test
	public void testEmptyRadiusField() throws Exception {
		dialogCircle.setXCoordinateValue(5);
		dialogCircle.setYCoordinateValue(15);
		exception.expect(Exception.class);
		exception.expectMessage("Not valid input. Check if all fields have values!");
		dialogCircle.checkIfValuesAreFilled();
		assertFalse(dialogCircle.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldsCenter() throws Exception {
		dialogCircle.setXCoordinateValue(0);
		dialogCircle.setYCoordinateValue(0);
		dialogCircle.setValueRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogCircle.checkIfValuesAreValid();
		assertFalse(dialogCircle.isValuesAreAccepted());
	}

	@Test
	public void testValidRadiusField() throws Exception {
		dialogCircle.setXCoordinateValue(10);
		dialogCircle.setYCoordinateValue(15);
		dialogCircle.setValueRadius(-10);
		exception.expect(Exception.class);
		exception.expectMessage("Radius must be positive!");
		dialogCircle.checkIfValuesAreValid();
		assertFalse(dialogCircle.isValuesAreAccepted());
	}
}
