package test.testDialogs;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dialogs.DialogOfPoint;

public class TestDialogPoint {

	private DialogOfPoint dialogPoint;
	private DialogOfPoint dialogPointMock;

	@Before
	public void setUp() {
		dialogPoint = new DialogOfPoint();
		dialogPointMock = spy(DialogOfPoint.class);
	}

	@Test
	public void testBtnCancelClicked() {
		dialogPointMock.getCancelButton().doClick();
		verify(dialogPointMock).dispose();
	}

	@Test
	public void testButtonOk() {
		dialogPoint.setXCoordinateValue(10);
		dialogPoint.setYCoordinateValue(15);
		dialogPoint.getOkButton().doClick();
		assertTrue(dialogPoint.isValuesAreAccepted());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmptyFields() throws Exception {
		dialogPoint.setXCoordinateValue(5);
		exception.expect(Exception.class);
		exception.expectMessage("All fields must be filled");
		dialogPoint.checkIfValuesAreFilled();
		assertFalse(dialogPoint.isValuesAreAccepted());
	}

	@Test
	public void testValidXCoordinateField() throws Exception {
		dialogPoint.setXCoordinateValue(0);
		dialogPoint.setYCoordinateValue(15);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogPoint.checkIfValuesAreValid();
		assertFalse(dialogPoint.isValuesAreAccepted());
	}

	@Test
	public void testValidYCoordinateField() throws Exception {
		dialogPoint.setXCoordinateValue(15);
		dialogPoint.setYCoordinateValue(0);
		exception.expect(Exception.class);
		exception.expectMessage("Values for POINT are not valid");
		dialogPoint.checkIfValuesAreValid();
		assertFalse(dialogPoint.isValuesAreAccepted());
	}
}
