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
package com.it.confsys.scheduler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.it.confsys.model.conference.Conference;
import com.it.confsys.model.conference.PresentationSession;
import com.it.confsys.model.conference.Track;
import com.it.confsys.model.topics.ConferenceTopics;
import com.it.confsys.util.AppConstants;
/**
 * This class schedules the conference bases on the given list of valid topics.
 * This class creates conference which contains tracks. Each track contains
 * session. The session holds the topics for presentation.
 */
public class ConferenceScheduler {
	
	private final static Logger LOGGER = Logger.getLogger(ConferenceScheduler.class.getName());
	
	private StringBuilder result = new StringBuilder();
	
	private ConferenceTopics topics;

	/**
	 * parameterized constructor which takes the conference topics
	 * @param conferenceTopics {@link ConferenceTopics}
	 */
	public ConferenceScheduler(ConferenceTopics conferenceTopics) {
		this.topics = conferenceTopics;
	}

	/**
	 * with the given list of topics and durations design the tracks for the conference.
	 * Influenced by Depth first. Each sessions are filled with topics before the next
	 * session is planned.
	 * 
	 * @return {@link Conference}
	 */
	public Conference createTracks() {
		// Calculate total tracks needed
		int totalDuration = 0;
		int totalTracks;
		for(Integer duration: topics.getDurations()) {
			totalDuration += duration * topics.getTopicsForDuration(duration).size();
		}
		
		if(totalDuration > AppConstants.TOTAL_SESSION_DURATION) {
			if(totalDuration % AppConstants.TOTAL_SESSION_DURATION == 0) {
				totalTracks = totalDuration / AppConstants.TOTAL_SESSION_DURATION;
			}else {
				totalTracks = (totalDuration / AppConstants.TOTAL_SESSION_DURATION) + 1;
			}
		}else {
			totalTracks = 1;
		}
		
		// create tracks and sessions
		Conference conference = new Conference();
		List<PresentationSession> totalPresentationSessions = new ArrayList<PresentationSession>();


		for(int i = 0; i < totalTracks ; i++) {
			Track track = new Track();
			
			PresentationSession morning = new PresentationSession();
			morning.setName(AppConstants.MORNING_SESSION);
			morning.setPlannedStartTime(Duration.ofHours(AppConstants.MORNING_SESSION_START_TIME));
			morning.setPlannedEndTime(Duration.ofHours(AppConstants.MORNING_SESSION_END_TIME));
			morning.setActualEndTime(Duration.ofHours(AppConstants.MORNING_SESSION_START_TIME));
			totalPresentationSessions.add(morning);
			
			PresentationSession afternoon = new PresentationSession();
			afternoon.setName(AppConstants.AFTERNOON_SESSION);
			afternoon.setPlannedStartTime(Duration.ofHours(AppConstants.AFTERNOON_SESSION_START_TIME));
			afternoon.setPlannedEndTime(Duration.ofHours(AppConstants.AFTERNOON_SESSION_END_TIME));
			afternoon.setActualEndTime(Duration.ofHours(AppConstants.AFTERNOON_SESSION_START_TIME));
			totalPresentationSessions.add(afternoon);
			
			track.getSessions().put(AppConstants.MORNING_SESSION, morning);
			track.getSessions().put(AppConstants.AFTERNOON_SESSION, afternoon);
			
			conference.getTracks().put(track.getId(), track);
		}
		
		//populate topics to sessions
		List<String> assignedTopics = new ArrayList<String>();
		Integer[] durationArray = (Integer[]) topics.getDurations().toArray(new Integer[topics.getDurations().size()]);
		Arrays.sort(durationArray);
		
		for(int i = durationArray.length - 1 ; i >= 0 ; i--) {
			HashSet<String> topicsForDuration = topics.getTopicsForDuration(durationArray[i]);
			for(String topic: topicsForDuration) {
				if(!assignedTopics.contains(topic)) {
					if(addTopicToSession(durationArray[i], topic, totalPresentationSessions)) {
						assignedTopics.add(topic);
					}else{
						LOGGER.log(Level.SEVERE, "Unable to fit the topic into the sessions planned. Topic: "+ topic+" , Duration: ", durationArray[i]);
					}
				}
			}
		}
		
		//Add lunch breaks and calculate networking session start time
		Duration actualNetworkingTime =  Duration.ofHours(AppConstants.NETWORKING_SESSION_MIN_START_TIME);
		for(PresentationSession presentationSession : totalPresentationSessions ) {
			if(presentationSession.getName().equals(AppConstants.MORNING_SESSION)){
				presentationSession.getSchedule().put(presentationSession.getPlannedEndTime(), "Lunch");
			}else {
				Duration afternoonSessionEndTime = presentationSession.getActualEndTime();
				int durationDifference = afternoonSessionEndTime.compareTo(actualNetworkingTime);
				if(durationDifference > 0) {
					actualNetworkingTime = afternoonSessionEndTime;
				}
			}
		}
		
		// sync networking session start time across sessions
		for(PresentationSession session: totalPresentationSessions) {
			if(session.getName().equals(AppConstants.AFTERNOON_SESSION)) {
				session.getSchedule().put(actualNetworkingTime, "Networking Time");
			}
		}
		
		
		
		//Populate conference details
		addToResult("*** CONFERENCE SCHEDULE ***");
		Map<Integer, Track> tracks = conference.getTracks();
		for(Integer trackId: conference.getTracks().keySet()) {
			addToResult("### Track: "+ trackId+ " ###");
			Track track = tracks.get(trackId);
			displaySessionTopics((PresentationSession) track.getSessions().get(AppConstants.MORNING_SESSION));
			displaySessionTopics((PresentationSession) track.getSessions().get(AppConstants.AFTERNOON_SESSION));
		}
		
		System.out.println(result);
		
		return conference;
	}

	private void addToResult(String data) {
		result.append(System.lineSeparator());
		result.append(data);
	}

	private void displaySessionTopics(PresentationSession session) {
		for(Duration startTime : session.getSchedule().keySet()) {
			addToResult(String.format("%02d", startTime.toHours())+":"+String.format("%02d", startTime.toMinutesPart())+ "  "+session.getSchedule().get(startTime));
		}
	}

	private boolean addTopicToSession(Integer duration, String topic, List<PresentationSession> presentationSessions) {
		for(PresentationSession presentationSession: presentationSessions) {
			if(slotAvailableinSession(duration, presentationSession)) {
				presentationSession.getSchedule().put(presentationSession.getActualEndTime(), topic);
				Duration updatedActualEndTime = presentationSession.getActualEndTime().plusMinutes(duration);
				presentationSession.setActualEndTime(updatedActualEndTime);
				return true;
			}
		}
		return false;
	}

	private boolean slotAvailableinSession(Integer duration, PresentationSession presentationSession) {
		Duration availableDuration = presentationSession.getPlannedEndTime().minus(presentationSession.getActualEndTime());
		if(availableDuration.toMinutes() > 0L && availableDuration.toMinutes() >= duration) {
			return true;
		}
		return false;
	}

}
