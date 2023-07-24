package com.it.confsys.model.conference;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Track {
	
	private int id;
	
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public Track() {
		this.id = atomicInteger.incrementAndGet();
	}
	
	private Map<String, Session> sessions = new HashMap<String, Session>();

	public int getId() {
		return id;
	}

	public Map<String, Session> getSessions() {
		return sessions;
	}
	
}
