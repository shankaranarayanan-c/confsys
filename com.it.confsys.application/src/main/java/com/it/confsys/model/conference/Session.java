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

/**
 * The Base class session holds the common session properties
 */
public class Session {

	private String name;
	
	private Duration plannedStartTime;
	
	private Duration actualStartTime;

	/**
	 * returns the name of the session. This may be morning or afternoon
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the session. This may be morning or afternoon.
	 * @param name {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This returns the planned start time of the presentation session. 
	 * @return {@link Duration}
	 */
	public Duration getPlannedStartTime() {
		return plannedStartTime;
	}

	/**
	 * This method sets the value of the planned start time of the presentation session
	 * @param plannedStartTime {@link Duration}
	 */
	public void setPlannedStartTime(Duration plannedStartTime) {
		this.plannedStartTime = plannedStartTime;
	}

	/**
	 * This method returns the actual start time of the session. This would be same as the planned
	 * start time as of now.
	 * @return {@link Duration}
	 */
	public Duration getActualStartTime() {
		return actualStartTime;
	}

	/**
	 * This method sets the actual start time of the session. 
	 * @param actualStartTime {@link Duration}
	 */
	public void setActualStartTime(Duration actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	
}
