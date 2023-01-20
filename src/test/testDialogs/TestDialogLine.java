package test.testDialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfLine;

public class TestDialogLine {

	private DialogOfLine dialogLine;

	@Before
	public void setUp() {
		dialogLine = new DialogOfLine();
	}

	@Test
	public void testButtonOk() {
		dialogLine.setXCoordinateValue(10);
		dialogLine.setYCoordinateValue(15);
		dialogLine.setXCoordinateValueEndPoint(50);
		dialogLine.setYCoordinateValueEndPoint(55);
		dialogLine.getOkButton().doClick();
		assertTrue(dialogLine.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyFields() throws Exception {
		dialogLine.setXCoordinateValue(5);
		dialogLine.setYCoordinateValue(15);
		dialogLine.setXCoordinateValueEndPoint(50);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogLine.checkIfValuesAreFilled();
		assertFalse(dialogLine.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldStartPoint() throws Exception {
		dialogLine.setXCoordinateValue(0);
		dialogLine.setYCoordinateValue(15);
		dialogLine.setXCoordinateValueEndPoint(15);
		dialogLine.setYCoordinateValueEndPoint(55);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogLine.checkIfValuesAreValid();
		assertFalse(dialogLine.isValuesAreAccepted());
	}

	@Test
	public void testValidCoordinateFieldEndPoint() throws Exception {
		dialogLine.setXCoordinateValue(10);
		dialogLine.setYCoordinateValue(15);
		dialogLine.setXCoordinateValueEndPoint(0);
		dialogLine.setYCoordinateValueEndPoint(55);
		exception.expect(Exception.class);
		exception.expectMessage("Values for END POINT are not valid");
		dialogLine.checkIfValuesAreValid();
		assertFalse(dialogLine.isValuesAreAccepted());
	}
}
