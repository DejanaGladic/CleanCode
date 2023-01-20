package strategy;

import java.util.ArrayList;

public interface ExportImport {
	void exportData(ArrayList<Object> listOfObjectsToSave, String path);

	ArrayList<Object> importData(String path);
}
