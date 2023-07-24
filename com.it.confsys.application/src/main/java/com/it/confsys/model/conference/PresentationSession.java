package com.it.confsys.model.conference;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentationSession extends Session {

	private Duration plannedEndTime;
	
	private Duration actualEndTime;
	
	private Map<Duration, String> schedule = new LinkedHashMap<Duration, String>();

	public Duration getPlannedEndTime() {
		return plannedEndTime;
	}

	public void setPlannedEndTime(Duration plannedEndTime) {
		this.plannedEndTime = plannedEndTime;
	}

	public Duration getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Duration actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public Map<Duration, String> getSchedule() {
		return schedule;
	}
	
}
