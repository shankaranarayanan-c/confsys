/**
 * 
 */
package com.it.confsys.parser;

import com.it.confsys.model.InputData;

/**
 * 
 */
public abstract class FileParser {
	
	protected String filePath;
	
	public FileParser(String inputFilePath) {
		this.filePath = inputFilePath;
	}
	
	/**
	 * Parse the input file and returns the valid data found in the files.
	 * 
	 * @return {@link InputData}
	 */
	public abstract InputData parse();

}
