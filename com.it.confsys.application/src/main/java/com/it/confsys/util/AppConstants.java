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
package com.it.confsys.util;

/**
 * hold the common constants across application
 */
public final class AppConstants {
	
	private AppConstants() {
		
	}

	/**
	 * Duration of the lightning topic within presentation session in minutes
	 */
	public static final int LIGHTNING_DURATION = 5;
	
	/**
	 * Planned start time of the morning presentation session in hrs
	 */
	public static final int MORNING_SESSION_START_TIME = 9;
	
	/**
	 * Planned end time of the morning presentation session in hrs
	 */
	public static final int MORNING_SESSION_END_TIME = 12;
	
	/**
	 * Planned start time of the afternoon presentation session in 24 hrs time
	 */
	public static final int AFTERNOON_SESSION_START_TIME = 13;
	
	/**
	 * Planned end time of the afternoon presentation session in 24 hrs time
	 */
	public static final int AFTERNOON_SESSION_END_TIME = 17;
	
	/**
	 * Total minutes in an one hour presentation session
	 */
	public static final int HR_SESSION_MINUTES = 60;
	
	/**
	 * Total duration of the morning presentation session in minutes
	 */
	public static final int MORNING_SESSION_DURATION = (MORNING_SESSION_END_TIME - MORNING_SESSION_START_TIME) * HR_SESSION_MINUTES;
	
	/**
	 * Total duration of the afternoon presentation session in minutes
	 */
	public static final int AFTERNOON_SESSION_DURATION = (AFTERNOON_SESSION_END_TIME - AFTERNOON_SESSION_START_TIME) * HR_SESSION_MINUTES;
	
	/**
	 * Total presentation session duration including morning and afternoon sessions
	 */
	public static final int TOTAL_SESSION_DURATION = MORNING_SESSION_DURATION + AFTERNOON_SESSION_DURATION;
	
	/**
	 * Max time until which the networking session can be started
	 */
	public static final int NETWORKING_SESSION_MAX_START_TIME = 17;
	
	/**
	 * Min time to start the networking session
	 */
	public static final int NETWORKING_SESSION_MIN_START_TIME = 16;
	
	/**
	 * Name of the morning session
	 */
	public static final String MORNING_SESSION = "morning";
	
	/**
	 * Name of the afternoon session
	 */
	public static final String AFTERNOON_SESSION = "afternoon";
	
}
