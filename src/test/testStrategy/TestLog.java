package test.testStrategy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import controller.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;
import strategy.ExportImportLog;
import strategy.ExportImportManager;

public class TestLog {

	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	private String path;
	private ExportImportManager exportImportManager;
	private ExportImportLog exportImportLog;
	private ArrayList<Object> listOfCommandsToSave;
	private static Scanner scanner;
	private ArrayList<Object> listOfLogsSavedInFile;

	@Before
	public void setUp() {
		frame = new DrawingFrame();
		model = new DrawingModel();
		controller = new DrawingController(model, frame);
		exportImportLog = new ExportImportLog();
		exportImportManager = new ExportImportManager(exportImportLog);
		controller.getLogCommandList().add("Added: Point: (10, 20), Color: (-16777216)");
		listOfCommandsToSave = new ArrayList<Object>();
		listOfCommandsToSave.addAll(controller.getLogCommandList());
	}

	@Test
	public void testExportLog() throws FileNotFoundException {
		path = "testSaveLog.log";
		exportImportManager.exportData(listOfCommandsToSave, path);
		listOfLogsSavedInFile = readFromFile(path);
		assertEquals(listOfCommandsToSave, listOfLogsSavedInFile);
	}

	@Test(expected = FileNotFoundException.class)
	public void testExportLogException() throws FileNotFoundException {
		path = "";
		exportImportManager.exportData(listOfCommandsToSave, path);
		listOfLogsSavedInFile = readFromFile(path);
		assertThat(listOfCommandsToSave, is(not(listOfLogsSavedInFile)));
	}

	@Test
	public void testImportLog() {
		path = "testSaveLog.log";
		exportImportManager.exportData(listOfCommandsToSave, path);
		assertEquals(listOfCommandsToSave, exportImportLog.importData(path));
	}

	@Test
	public void testImportWrongPath() {
		path = "testSaveLog.log";
		exportImportManager.exportData(listOfCommandsToSave, path);
		path = "pathDoesntExist";
		assertThat(listOfCommandsToSave, is(not(exportImportLog.importData(path))));
	}

	public ArrayList<Object> readFromFile(String path) throws FileNotFoundException {
		scanner = new Scanner(new FileReader(path));
		String lineOfFile;
		ArrayList<Object> listOfLogsSavedInFile = new ArrayList<Object>();
		while (scanner.hasNextLine()) {
			lineOfFile = scanner.nextLine();
			listOfLogsSavedInFile.add(lineOfFile);
		}
		return listOfLogsSavedInFile;
	}

	@AfterClass
	public static void tearDown() throws IOException {
		scanner.close();
	}
}
