package com.it.confsys.model.conference;

import java.util.Map;
import java.util.TreeMap;

public class Conference {

	private Map<Integer, Track> tracks = new TreeMap<Integer, Track>();

	public Map<Integer, Track> getTracks() {
		return tracks;
	}

}
