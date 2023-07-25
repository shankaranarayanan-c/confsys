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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class holds the track informations of the conference. This contains
 * sessions. At present it hold only 2 sessions morning and afternoon.
 */
public class Track {
	
	private int id;
	
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	/**
	 * user defined default constructor which generates the id for the track automatically 
	 */
	public Track() {
		this.id = atomicInteger.incrementAndGet();
	}
	
	private Map<String, Session> sessions = new HashMap<String, Session>();

	/**
	 * returns the id of the track. The id of session is computed automatically.
	 * @return {@link Integer}
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns the Map of sessions 
	 * key name of the session
	 * value is the session. morning or afternoon.
	 * @return  {@link Map}
	 */
	public Map<String, Session> getSessions() {
		return sessions;
	}
	
}
