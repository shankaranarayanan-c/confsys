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
package com.it.confsys.model.conference;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The contains about the morning or afternoon session. This class also contains list of topics
 * planned.
 */
public class PresentationSession extends Session {

	private Duration plannedEndTime;
	
	private Duration actualEndTime;
	
	private Map<Duration, String> schedule = new LinkedHashMap<Duration, String>();

	/**
	 * This returns the planned end time of the presentation session. This would be 
	 * the max time of the session.
	 * @return {@link Duration}
	 */
	public Duration getPlannedEndTime() {
		return plannedEndTime;
	}

	/**
	 * This method sets the value of the planned end time of the presentation session
	 * @param plannedEndTime {@link Duration}
	 */
	public void setPlannedEndTime(Duration plannedEndTime) {
		this.plannedEndTime = plannedEndTime;
	}

	/**
	 * This method returns the actual end time of the session. This is computed based
	 * on the topics planned
	 * @return {@link Duration}
	 */
	public Duration getActualEndTime() {
		return actualEndTime;
	}

	/**
	 * This method sets the actual end time of the session. This is computed based on the
	 * topics planned
	 * @param actualEndTime {@link Duration}
	 */
	public void setActualEndTime(Duration actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	/**
	 * This method returns the complete schedule of the presentation as Map.
	 * Key is the {@link Duration}
	 * Value is the topic
	 * @return {@link Map} 
	 */
	public Map<Duration, String> getSchedule() {
		return schedule;
	}
	
}
