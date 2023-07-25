/**
 * Copyright (c) 2023 by it Inc. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author shankar
 */

package com.it.confsys.parser;

import java.lang.reflect.Constructor;

import com.it.confsys.model.topics.InputData;

/**
 * Abstract class which contains common properties for all file parsers
 */
public abstract class FileParser {
	/**
	 * Path of the given input file
	 */
	protected String filePath;
	
	/**
	 * Parameterized constructor which takes the input file path
	 * @param inputFilePath
	 */
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
