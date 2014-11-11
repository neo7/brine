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

	/**
	 * Use this location for Testing the Single JSFF file.
	 */
	private static String test_fileLocation = "/Users/saurabhtiwari/workspaces"
			+ "/office_workspace/View/com.ofss.fc.ui.view.collection"
			+ "/src/com/ofss/fc/ui/view/collection/core/backing";

	/**
	 * Use this variable for Changing entries in the whole directory.
	 */
	private static String workingDirectoryLocation = "/Users/saurabhtiwari/workspaces/office_workspace/TaskFlows";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> fileLocationList = new ArrayList<String>();
		// fileLocationList.add(managed_bean_location);

		File workingDirectory = new File(workingDirectoryLocation);
		// specify the type of file
		String[] extensions = new String[] { "jsff" };

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
		JSFFAttribModifier jsffAttribModifier = new JSFFAttribModifier();
		try {
			jsffAttribModifier.modifyFileAttributes(fileLocationList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
