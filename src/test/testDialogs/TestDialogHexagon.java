package test.testDialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfHexagon;

public class TestDialogHexagon {
	private DialogOfHexagon dialogHexagon;

	@Before
	public void setUp() {
		dialogHexagon = new DialogOfHexagon();
	}

	@Test
	public void testButtonOk() {
		dialogHexagon.setXCoordinateValue(10);
		dialogHexagon.setYCoordinateValue(15);
		dialogHexagon.setValueHexRadius(10);
		dialogHexagon.getOkButton().doClick();
		assertTrue(dialogHexagon.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyCenterPointFields() throws Exception {
		dialogHexagon.setValueHexRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogHexagon.checkIfValuesAreFilled();
		assertFalse(dialogHexagon.isValuesAreAccepted());
	}

	@Test
	public void testEmptyRadiusField() throws Exception {
		dialogHexagon.setXCoordinateValue(5);
		dialogHexagon.setYCoordinateValue(15);
		exception.expect(Exception.class);
		exception.expectMessage("Not valid input. Check if all fields have values!");
		dialogHexagon.checkIfValuesAreFilled();
		assertFalse(dialogHexagon.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldsCenter() throws Exception {
		dialogHexagon.setXCoordinateValue(0);
		dialogHexagon.setYCoordinateValue(0);
		dialogHexagon.setValueHexRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogHexagon.checkIfValuesAreValid();
		assertFalse(dialogHexagon.isValuesAreAccepted());
	}

	@Test
	public void testValidRadiusField() throws Exception {
		dialogHexagon.setXCoordinateValue(10);
		dialogHexagon.setYCoordinateValue(15);
		dialogHexagon.setValueHexRadius(-10);
		exception.expect(Exception.class);
		exception.expectMessage("Radius must be positive!");
		dialogHexagon.checkIfValuesAreValid();
		assertFalse(dialogHexagon.isValuesAreAccepted());
	}
}
