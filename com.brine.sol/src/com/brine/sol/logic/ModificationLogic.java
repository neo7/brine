package com.brine.sol.logic;

public class ModificationLogic {

	public String modifyContent(String content) {
		//Using regex pattern matching to search and replace from the Files.
		String modifiedString = content.replaceAll("styleClass=\"([a-z,A-Z-])+\"", "styleClass=\"\"");
		return modifiedString;
	}
	
}
