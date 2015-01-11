/**
 * 
 */
package com.brine.sol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.brine.sol.logic.ModificationLogic;

/**
 * @author saurabhtiwari
 *
 */
public class FileAttribModifier {
	private List<String> chageSets = new ArrayList<String>();
	
	public void modifyFileAttributes(List<String> fileLocationList) throws IOException {
		for (String fileLocation : fileLocationList){
			String fileWritingLocation = determineFileWritingLocation(fileLocation);
			String fileName = fileLocation.substring(fileLocation.lastIndexOf("/")+1);
			FileInputStream fileInputStream = new FileInputStream(
					fileLocation);
			File file = new File(fileLocation);
			String content = null;
			try {
				content = FileUtils.readFileToString(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ModificationLogic modificationLogic = new ModificationLogic();
			String modifiedContent = modificationLogic.modifyContent(content);
			
			System.out.println(modifiedContent);
			createFile(fileWritingLocation, modifiedContent, fileName);
			
		}
		createChangeSetReport();
		
	}
	
	private String determineFileWritingLocation(String fileLocation) {
		String fileWritingLocation = fileLocation;
		int lastIndexOfSlash = fileWritingLocation.lastIndexOf("/");
		fileWritingLocation = fileWritingLocation
				.substring(0, lastIndexOfSlash) + "/";
		return fileWritingLocation;
	}

	private void createFile(String fileWritingLocation,
			String content, String fileName) throws IOException {
		File file = new File(fileWritingLocation
				+ fileName);
		if (!file.exists() && !file.getParentFile().mkdirs()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.write(content);

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			bw.close();
			chageSets.add(file.getAbsolutePath());
		}
	}
	
	private void createChangeSetReport() {
		FetchConfigValues fetchConfigValues = new FetchConfigValues();
		String REPORT_GEN_LOCATION = fetchConfigValues.getPropertyValue("REPORT_GEN_LOCATION");
		
		File changeSetReportFile = new File(REPORT_GEN_LOCATION);
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileOutputStream(changeSetReportFile));
			for (String changeSet : chageSets){
				printWriter.println(changeSet);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if (printWriter != null){
				printWriter.close();
			}
		}
	}
	
}
