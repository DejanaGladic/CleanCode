package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExportImportLog implements ExportImport {

	@Override
	public void exportData(ArrayList<Object> listOfLogsToExport, String path) {
		File createdFile = new File(path);
		FileOutputStream fileOutputStream;
		BufferedWriter bufferedWriter;
		String commandFromLogString;
		try {
			fileOutputStream = new FileOutputStream(createdFile);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
			for (Object commandFromLog : listOfLogsToExport) {
				commandFromLogString = (String) commandFromLog;
				bufferedWriter.write(commandFromLogString);
				if (commandFromLogString.contains("Redo")) {
					bufferedWriter.newLine();
				}
				if (commandFromLogString.contains("Undo")) {
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	@Override
	public ArrayList<Object> importData(String path) {
		FileReader logFile;
		Scanner scanner;
		ArrayList<Object> listOfImportedLogs = new ArrayList<Object>();
		String lineOfFile;
		try {
			logFile = new FileReader(path);
			scanner = new Scanner(logFile);
			while (scanner.hasNextLine()) {
				lineOfFile = scanner.nextLine();
				listOfImportedLogs.add(lineOfFile);
			}
			scanner.close();
			return listOfImportedLogs;
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		return null;
	}
}
