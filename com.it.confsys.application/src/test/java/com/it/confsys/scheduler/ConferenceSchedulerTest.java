package com.it.confsys.scheduler;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.it.confsys.handlers.LoggerHandler;
import com.it.confsys.model.conference.Conference;
import com.it.confsys.model.conference.PresentationSession;
import com.it.confsys.model.conference.Session;
import com.it.confsys.model.conference.Track;
import com.it.confsys.model.topics.ConferenceTopics;
import com.it.confsys.util.AppConstants;

public class ConferenceSchedulerTest {
	private LoggerHandler loggerHandler;
	private String[] topics1 = {"aaa","bbb","ccc"};
	private String[] topics2 = {"ddd","eee","fff"};
	private String[] topics3 = {"ggg","hhh","iii"};
	private String[] topics4 = {"jjj","kkk","lll"};
	private String[] topics_restructured_60 = {"jjj 60","60 jjj"};
	private String[] topics_restructured_lighning = {"jjj lightning", "lightning jjj"};
	
	@Before
	public void setup() {
		loggerHandler = new LoggerHandler();
		Logger.getLogger(ConferenceScheduler.class.getName()).addHandler(loggerHandler);
		
		
	}
	
	@Test
	public void testCreateTracksLessThan1Day() {
		
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics1);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(1, conference.getTracks().size());
	}

	@Test
	public void testCreateTracksEqualTo1Day() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics1);
		mockConferenceTopics(conferenceTopics, 20, topics2);
		mockConferenceTopics(conferenceTopics, 60, topics3);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(1, conference.getTracks().size());
		Map<String, Session> sessions = conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions();
		for(Session session: sessions.values()) {
			if(session.getName().equals(AppConstants.AFTERNOON_SESSION)){
				Duration actualEndTime = ((PresentationSession)session).getActualEndTime();
				Duration compareDuration = Duration.ofHours(AppConstants.AFTERNOON_SESSION_END_TIME).minus(actualEndTime);
				assertEquals(0, compareDuration.toHours());
			}
		}
	}
	
	@Test
	public void testCreateTracksMoreThan1Day() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics1);
		mockConferenceTopics(conferenceTopics, 60, topics2);
		mockConferenceTopics(conferenceTopics, 60, topics3);
		mockConferenceTopics(conferenceTopics, 60, topics4);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(2, conference.getTracks().size());
		Map<String, Session> sessions = conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions();
		for(Session session: sessions.values()) {
			if(session.getName().equals(AppConstants.AFTERNOON_SESSION)){
				Duration actualEndTime = ((PresentationSession)session).getActualEndTime();
				Duration compareDuration = Duration.ofHours(AppConstants.AFTERNOON_SESSION_END_TIME).minus(actualEndTime);
				assertEquals(2, compareDuration.toHours());
			}
		}
	}
	
	@Test
	public void testCreateTracksSyncNetworkingTime() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics1);
		mockConferenceTopics(conferenceTopics, 60, topics2);
		mockConferenceTopics(conferenceTopics, 60, topics3);
		mockConferenceTopics(conferenceTopics, 60, topics4);
		mockConferenceTopics(conferenceTopics, 30, topics1);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(2, conference.getTracks().size());
		List<Session> sessions = new ArrayList<Session>();
		sessions.addAll(conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions().values());
		sessions.addAll(conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-2].getSessions().values());
		Duration[] networkingTime = new Duration[2];
		int count = 0;
		for(Session session: sessions) {
			if(session.getName().equals(AppConstants.AFTERNOON_SESSION)){
				Duration[] schedules = ((PresentationSession)session).getSchedule().keySet().toArray(new Duration[((PresentationSession)session).getSchedule().size()]);
				networkingTime[count] = schedules[schedules.length-1];
				count++;
			}
		}
		Duration compareDuration = networkingTime[0].minus(networkingTime[1]);
		assertEquals(0, compareDuration.toHours());
	}
	
	@Test
	public void testCreateTracksDuplicateEntries() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics1);
		mockConferenceTopics(conferenceTopics, 20, topics2);
		mockConferenceTopics(conferenceTopics, 60, topics3);
		mockConferenceTopics(conferenceTopics, 60, topics3);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(1, conference.getTracks().size());
	}
	
	@Test
	public void testCreateTracksUnevenEntries() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 59, topics1);
		mockConferenceTopics(conferenceTopics, 47, topics2);
		mockConferenceTopics(conferenceTopics, 32, topics3);
		mockConferenceTopics(conferenceTopics, 16, topics4);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(2, conference.getTracks().size());
		List<Session> sessions = new ArrayList<Session>();
		sessions.addAll(conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions().values());
		sessions.addAll(conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-2].getSessions().values());
		List<String> expectedEndTimes = new ArrayList<String>();
		expectedEndTimes.add("PT9H48M");
		expectedEndTimes.add("PT11H57M");
		expectedEndTimes.add("PT13H");
		expectedEndTimes.add("PT16H57M");
		int count = 4;
		for(Session session: sessions) {
			Duration actualEndTime = ((PresentationSession)session).getActualEndTime();
			if(expectedEndTimes.contains(actualEndTime.toString())) {
				count--;
			}
		}
		assertEquals(0, count);
	}
	
	@Test
	public void testCreateTracksReStructuredEntries() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 60, topics_restructured_60);
		mockConferenceTopics(conferenceTopics, 5, topics_restructured_lighning);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(1, conference.getTracks().size());
		int totalTopics=0;
		Map<String, Session> sessions = conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions();
		for(Session session: sessions.values()) {
			totalTopics += ((PresentationSession)session).getSchedule().size();
		}
		//reduce the lunch and networking event
		assertEquals(4, totalTopics-2);
	}
	
	@Test
	public void testCreateTracksDuplicateTopisDiffDurationEntries() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		String[] topics_60 = {"aaa 60 mins","bbb 60 mins ","ccc 60 mins"};
		mockConferenceTopics(conferenceTopics, 60, topics_60);
		String[] topics_45 = {"aaa 45 mins","bbb 45 mins ","ccc 45 mins"};
		mockConferenceTopics(conferenceTopics, 45, topics_45);
		String[] topics_30 = {"aaa 30 mins","bbb 30 mins ","ccc 30 mins"};
		mockConferenceTopics(conferenceTopics, 30, topics_30);
		String[] topics_lightning = {"aaa lightning","bbb lightning","ccc lightning"};
		mockConferenceTopics(conferenceTopics, 5, topics_lightning);
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.createTracks();
		
		assertEquals(1, conference.getTracks().size());
		int totalTopics=0;
		Map<String, Session> sessions = conference.getTracks().values().toArray(new Track[conference.getTracks().size()])[conference.getTracks().size()-1].getSessions();
		for(Session session: sessions.values()) {
			totalTopics += ((PresentationSession)session).getSchedule().size();
		}
		//reduce the lunch and networking event
		assertEquals(12, totalTopics-2);
	}
	
	private void mockConferenceTopics(ConferenceTopics conferenceTopics, int duration, String[] topics) {
		for(int i=0; i< topics.length ;i++) {
			conferenceTopics.addTopic(duration, topics[i]);
		}
	}
}
