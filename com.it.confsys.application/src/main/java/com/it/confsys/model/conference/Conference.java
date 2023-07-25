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

import java.util.Map;
import java.util.TreeMap;
/**
 * place holder for the conference details. It contains track information.
 */
public class Conference {

	private Map<Integer, Track> tracks = new TreeMap<Integer, Track>();

	/**
	 * returns a map of tracks. key is the track id and the value is the track
	 * @return {@link Map}
	 */
	public Map<Integer, Track> getTracks() {
		return tracks;
	}

}
