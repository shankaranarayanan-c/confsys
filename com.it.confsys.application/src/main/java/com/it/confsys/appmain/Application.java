/**
 * 
 */
package com.it.confsys.appmain;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.it.confsys.parser.FileParser;
import com.it.confsys.parser.TopicsTextFileParser;

/**
 * Entry point for the confsys application which has the main method
 */
public class Application {
	
	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			String msg = "Input file with list of topics and durations as argument expected";
			LOGGER.log(Level.SEVERE, msg);
			throw new RuntimeException(msg);
		}
		
		
		FileParser fileParser = new TopicsTextFileParser(args[0]);
		fileParser.parse();
		
	}

}
