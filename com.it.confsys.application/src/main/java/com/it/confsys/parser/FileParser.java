/**
 * 
 */
package com.it.confsys.parser;

/**
 * 
 */
public abstract class FileParser {
	
	protected String filePath;
	
	public FileParser(String inputFilePath) {
		this.filePath = inputFilePath;
	}
	
	public abstract void parse();

}
