package com.it.confsys.report;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.it.confsys.model.conference.Conference;
import com.it.confsys.model.topics.ConferenceTopics;
import com.it.confsys.scheduler.ConferenceScheduler;

public class ConsoleReporterTest {
	
	private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	private String[] topics1 = {"aaa","bbb","ccc"};
	private String[] topics2 = {"ddd","eee","fff"};
	private String[] topics3 = {"ggg","hhh","iii"};
	private String[] topics4 = {"jjj","kkk","lll"};
	
	@Before
	public void setup() {
		System.setOut(new PrintStream(byteArrayOutputStream));
	}
	
	@Test
	public void testDisplay() {
		ConferenceTopics conferenceTopics = new ConferenceTopics();
		mockConferenceTopics(conferenceTopics, 59, topics1);
		mockConferenceTopics(conferenceTopics, 47, topics2);
		mockConferenceTopics(conferenceTopics, 32, topics3);
		mockConferenceTopics(conferenceTopics, 16, topics4);
		String expectedResult = "\n*** CONFERENCE SCHEDULE ***\n"
				+ "### Track ###\n"
				+ "09:00  aaa\n"
				+ "09:59  ccc\n"
				+ "10:58  bbb\n"
				+ "12:00  Lunch\n"
				+ "13:00  eee\n"
				+ "13:47  ddd\n"
				+ "14:34  fff\n"
				+ "15:21  ggg\n"
				+ "15:53  iii\n"
				+ "16:25  hhh\n"
				+ "16:57  Networking Time\n"
				+ "### Track ###\n"
				+ "09:00  kkk\n"
				+ "09:16  jjj\n"
				+ "09:32  lll\n"
				+ "12:00  Lunch\n"
				+ "16:57  Networking Time\n";
		
		ConferenceScheduler conferenceScheduler = new ConferenceScheduler(conferenceTopics);
		Conference conference = conferenceScheduler.scheduleConference();
		
		ConsoleReporter consoleReporter = new ConsoleReporter();
		consoleReporter.display(conference);
		String actualResult = byteArrayOutputStream.toString().replaceAll("Track: \\d+", "Track");
		assertEquals(expectedResult, actualResult);
	}

	@After
	public void tearDown() {
		System.setOut(System.out);
	}
	
	private void mockConferenceTopics(ConferenceTopics conferenceTopics, int duration, String[] topics) {
		for(int i=0; i< topics.length ;i++) {
			conferenceTopics.addTopic(duration, topics[i]);
		}
	}
}
