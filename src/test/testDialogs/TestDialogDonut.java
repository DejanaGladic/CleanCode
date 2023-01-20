package test.testDialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfDonut;

public class TestDialogDonut {
	private DialogOfDonut dialogDonut;

	@Before
	public void setUp() {
		dialogDonut = new DialogOfDonut();
	}

	@Test
	public void testButtonOk() {
		dialogDonut.setXCoordinateValue(10);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueRadius(20);
		dialogDonut.setValueInnerRadius(10);
		dialogDonut.getOkButton().doClick();
		assertTrue(dialogDonut.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyCenterPointFields() throws Exception {
		dialogDonut.setValueRadius(20);
		dialogDonut.setValueInnerRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogDonut.checkIfValuesAreFilled();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testEmptyRadiusField() throws Exception {
		dialogDonut.setXCoordinateValue(5);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueInnerRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("Not valid input. Check if all fields have values!");
		dialogDonut.checkIfValuesAreFilled();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testEmptyInnerRadiusField() throws Exception {
		dialogDonut.setXCoordinateValue(5);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueRadius(20);
		exception.expect(Exception.class);
		exception.expectMessage("Not valid input. Check if all fields have values!");
		dialogDonut.checkIfValuesAreFilled();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldsCenter() throws Exception {
		dialogDonut.setXCoordinateValue(0);
		dialogDonut.setYCoordinateValue(0);
		dialogDonut.setValueRadius(20);
		dialogDonut.setValueInnerRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogDonut.checkIfValuesAreValid();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testValidRadiusField() throws Exception {
		dialogDonut.setXCoordinateValue(10);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueRadius(-20);
		dialogDonut.setValueInnerRadius(10);
		exception.expect(Exception.class);
		exception.expectMessage("Radius must be positive!");
		dialogDonut.checkIfValuesAreValid();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testValidInnerRadiusField() throws Exception {
		dialogDonut.setXCoordinateValue(10);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueRadius(20);
		dialogDonut.setValueInnerRadius(-10);
		exception.expect(Exception.class);
		exception.expectMessage("Inner radius must be positive!");
		dialogDonut.checkIfValuesAreValid();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}

	@Test
	public void testValidInnerRadiusRadiusFields() throws Exception {
		dialogDonut.setXCoordinateValue(10);
		dialogDonut.setYCoordinateValue(15);
		dialogDonut.setValueRadius(10);
		dialogDonut.setValueInnerRadius(20);
		exception.expect(Exception.class);
		exception.expectMessage("Radius must be greater than inner radius!");
		dialogDonut.checkIfValuesAreValid();
		assertFalse(dialogDonut.isValuesAreAccepted());
	}
}
