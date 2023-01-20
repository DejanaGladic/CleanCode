package strategy;

import java.util.ArrayList;

public class ExportImportManager implements ExportImport {

	private ExportImport exportImport;

	public ExportImportManager(ExportImport export) {
		this.exportImport = export;
	}

	@Override
	public void exportData(ArrayList<Object> listOfObjectsToSave, String path) {
		exportImport.exportData(listOfObjectsToSave, path);
	}

	@Override
	public ArrayList<Object> importData(String path) {
		return exportImport.importData(path);
	}
}
