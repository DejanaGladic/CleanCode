package strategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ExportImportDrawing implements ExportImport {

	@Override
	public void exportData(ArrayList<Object> listOfObjectsToExport, String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(listOfObjectsToExport);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> importData(String path) {
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			ArrayList<Object> listOfObjectsToImport = (ArrayList<Object>) objectInputStream.readObject();
			fileInputStream.close();
			objectInputStream.close();
			return listOfObjectsToImport;
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		return null;
	}
}