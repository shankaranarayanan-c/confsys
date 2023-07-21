/**
 * 
 */
package com.it.confsys.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * 
 */
public class TopicsTextFileParser extends FileParser {
	
	private final Logger LOGGER = Logger.getLogger(TopicsTextFileParser.class.getName());


	public TopicsTextFileParser(String inputFilePath) {
		super(inputFilePath);
	}

	@Override
	public void parse() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(this.filePath);
		}catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Invalid input file. " + this.filePath);
			throw new RuntimeException(e.getMessage());
		}
		
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		try {
			while((line = bufferedReader.readLine()) != null) {
				
				if(Pattern.compile("(.)*(\\d\\.\\d)(.)*").matcher(line).find()) {
					LOGGER.log(Level.WARNING, "Ignored topic with decimal duration. " + line);
					continue;
				}else if(Pattern.compile("(.)*(-)(\\d)(.)*").matcher(line).find()) {
					LOGGER.log(Level.WARNING, "Ignored topic with negative duration. " + line);
					continue;
				}
				
				StringBuilder durationExtract = new StringBuilder(line.length());
				for(char c: line.toCharArray()) {
					if(c > 47 && c < 58) {
						durationExtract.append(c);
					}
				}
				if(durationExtract.length() > 0) {
				int value = Integer.parseInt(durationExtract.toString());
					if(value > 0 && value <= 60) {
						LOGGER.log(Level.INFO, "valid topic with duration: ", line);
					}else {
						LOGGER.log(Level.WARNING, "Ignored topic with invalid duration. " + line);
					}
				}else {
					//Lightning duration
					if(line.toLowerCase().contains("lightning")) {
						LOGGER.log(Level.INFO, "valid topic with duration: ", line);
					}else {
						// invalid topic without duration
						LOGGER.log(Level.WARNING, "Ignored topic without valid duration. " + line);
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unable to read input file. " + filePath);
			throw new RuntimeException(e.getMessage());
		}
	}

}
