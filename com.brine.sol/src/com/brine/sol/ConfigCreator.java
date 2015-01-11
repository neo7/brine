package com.brine.sol;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigCreator {
	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			/*
			 * WORK_DIR_LOCATION=/Users/saurabhtiwari/workspaces/
			 * office_workspace/TaskFlows
			 * TEST_FILE_LOCATION=/Users/saurabhtiwari
			 * /workspaces/office_workspace
			 * /View/com.ofss.fc.ui.view.collection/src
			 * /com/ofss/fc/ui/view/collection/core/backing FILE_TYPE=jsff
			 * REPORT_GEN_LOCATION
			 * =/Users/saurabhtiwari/changeset-jsff-report.txt
			 * REGEXP_MATCH=styleClass=\"([a-z,A-Z-])+\"
			 * REGEXP_REPLACE=styleClass=\"\"
			 */
			prop.setProperty("WORK_DIR_LOCATION",
					"/Users/saurabhtiwari/workspaces/office_workspace/TaskFlows");
			prop.setProperty(
					"TEST_FILE_LOCATION",
					"/Users/saurabhtiwari/workspaces/office_workspace/View/com.ofss.fc.ui.view.collection/src/com/ofss/fc/ui/view/collection/core/backing");
			prop.setProperty("FILE_TYPE", "jsff");
			prop.setProperty("REPORT_GEN_LOCATION",
					"/Users/saurabhtiwari/changeset-jsff-report.txt");
			prop.setProperty("REGEXP_MATCH", "styleClass=\"([a-z,A-Z-])+\"");
			prop.setProperty("REGEXP_REPLACE", "styleClass=\"\"");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}