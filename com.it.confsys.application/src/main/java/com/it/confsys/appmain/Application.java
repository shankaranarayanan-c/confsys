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
package com.it.confsys.appmain;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.it.confsys.model.conference.Conference;
import com.it.confsys.model.topics.ConferenceTopics;
import com.it.confsys.model.topics.InputData;
import com.it.confsys.parser.FileParser;
import com.it.confsys.parser.TopicsTextFileParser;
import com.it.confsys.report.ConsoleReporter;
import com.it.confsys.scheduler.ConferenceScheduler;

/**
 * Entry point for the confsys application which has the main method
 */
public class Application {
	
	private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

	/**
	 * @param args input file location which has the topic details
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			String msg = "Input file with list of topics and durations as argument expected";
			LOGGER.log(Level.SEVERE, msg);
			throw new RuntimeException(msg);
		}
		
		
		FileParser fileParser = new TopicsTextFileParser(args[0]);
		InputData parseData = fileParser.parse();
		
		if(((ConferenceTopics) parseData).getDurations().size() > 0) {
			ConferenceScheduler conferenceScheduler = new ConferenceScheduler((ConferenceTopics) parseData);
			Conference conference = conferenceScheduler.scheduleConference();
			ConsoleReporter reporter = new ConsoleReporter();
			reporter.display(conference);
		}else {
			String msg = "No valid topics found in the given file: "+args[0];
			LOGGER.log(Level.SEVERE, msg);
		}
		
	}

}
