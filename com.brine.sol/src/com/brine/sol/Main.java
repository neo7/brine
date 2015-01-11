/**
 * 
 */
package com.brine.sol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author saurabhtiwari
 *
 */
public class Main {

	private static String workingDirectoryLocation = "/Users/saurabhtiwari/workspaces/office_workspace/TaskFlows";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> fileLocationList = new ArrayList<String>();
		// fileLocationList.add(managed_bean_location);
		FetchConfigValues fetchConfigValues = new FetchConfigValues();
		String work_dir_location = fetchConfigValues.getPropertyValue("WORK_DIR_LOCATION");
		String file_type = fetchConfigValues.getPropertyValue("FILE_TYPE");

		File workingDirectory = new File(work_dir_location);
		// specify the type of file
		String[] extensions = new String[] { file_type };

		List<File> files = (List<File>) FileUtils.listFiles(workingDirectory,
				extensions, true);

		for (File file : files) {
			
				try {
					fileLocationList.add(file.getCanonicalPath());
				} catch (IOException e) {
					System.out.println("ERROR IN GETTING CANONICAL FILE PATH");
					e.printStackTrace();
				}
			
		}

		try {
			modifyFileAttributes(fileLocationList);
		} catch (FileNotFoundException e) {
			System.out.println("Could not change the File attributes");
			e.printStackTrace();
		}

	}

	private static void modifyFileAttributes(List<String> fileLocationList) throws FileNotFoundException {
		FileAttribModifier jsffAttribModifier = new FileAttribModifier();
		try {
			jsffAttribModifier.modifyFileAttributes(fileLocationList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
