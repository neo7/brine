package com.brine.sol.logic;

import com.brine.sol.FetchConfigValues;

public class ModificationLogic {

	public String modifyContent(String content) {
		//Using regex pattern matching to search and replace from the Files.
		FetchConfigValues fetchConfigValues = new FetchConfigValues();
		String REGEXP_MATCH = fetchConfigValues.getPropertyValue("REGEXP_MATCH");
		String REGEXP_REPLACE = fetchConfigValues.getPropertyValue("REGEXP_REPLACE");
		
		String modifiedString = content.replaceAll(REGEXP_MATCH, REGEXP_REPLACE);
		return modifiedString;
	}
	
}
