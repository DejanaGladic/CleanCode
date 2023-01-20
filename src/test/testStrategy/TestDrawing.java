package test.testStrategy;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import model.DrawingModel;
import shapes.Point;
import shapes.Rectangle;
import strategy.ExportImportDrawing;
import strategy.ExportImportManager;

public class TestDrawing {

	private String path;
	private ExportImportDrawing exportImportDrawing;
	private ExportImportManager exportImportManager;
	private DrawingModel model;
	private ArrayList<Object> listOfObjectsToSave;
	private static FileInputStream fileInputStream;
	private static ObjectInputStream objectInputStream;

	@Before
	public void setUp() {
		model = new DrawingModel();
		exportImportDrawing = new ExportImportDrawing();
		exportImportManager = new ExportImportManager(exportImportDrawing);
		model.add(new Point(10, 15, Color.black));
		model.add(new Rectangle(new Point(10, 15), 15, 20, Color.black, Color.white));
		listOfObjectsToSave = new ArrayList<Object>();
		listOfObjectsToSave.add(model.getShapes());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testExportDrawing() throws IOException, ClassNotFoundException {
		path = "testSaveDrawing.drwg";
		exportImportManager.exportData(listOfObjectsToSave, path);
		readFromFile(path);
		assertEquals(listOfObjectsToSave, (ArrayList<Object>) objectInputStream.readObject());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = FileNotFoundException.class)
	public void testExportDrawingThrowException() throws IOException, ClassNotFoundException {
		path = "";
		exportImportManager.exportData(listOfObjectsToSave, path);
		readFromFile(path);
		assertThat(listOfObjectsToSave, is(not((ArrayList<Object>) objectInputStream.readObject())));
	}

	@Test
	public void testImportDrawing() throws IOException, ClassNotFoundException {
		path = "testSaveDrawing.drwg";
		exportImportManager.exportData(listOfObjectsToSave, path);
		assertEquals(listOfObjectsToSave, exportImportManager.importData(path));
	}

	@Test
	public void testImportDrawingWrongPath() throws IOException, ClassNotFoundException {
		path = "testSaveDrawing.drwg";
		exportImportManager.exportData(listOfObjectsToSave, path);
		path = "pathDoesntExist";
		assertThat(listOfObjectsToSave, is(not(exportImportManager.importData(path))));
	}

	private void readFromFile(String path) throws IOException {
		fileInputStream = new FileInputStream(path);
		objectInputStream = new ObjectInputStream(fileInputStream);
	}

	@AfterClass
	public static void tearDown() throws IOException {
		fileInputStream.close();
		objectInputStream.close();
	}
}
