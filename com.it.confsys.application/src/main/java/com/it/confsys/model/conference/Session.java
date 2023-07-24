package com.it.confsys.model.conference;

import java.time.Duration;

public class Session {

	private String name;
	
	private Duration plannedStartTime;
	
	private Duration actualStartTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Duration getPlannedStartTime() {
		return plannedStartTime;
	}

	public void setPlannedStartTime(Duration plannedStartTime) {
		this.plannedStartTime = plannedStartTime;
	}

	public Duration getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Duration actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	
}
