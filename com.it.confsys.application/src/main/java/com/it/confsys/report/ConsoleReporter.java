/**
 * 
 */
package com.it.confsys.report;

import com.it.confsys.model.conference.Conference;

/**
 * This class displays the conference schedule
 * details to the console
 */
public class ConsoleReporter {

	/**
	 * Displays the conference details in console
	 * @param conference {@link Conference}
	 */
	public void display(Conference conference) {
		BuildConferenceSchedule buildConferenceSchedule = new BuildConferenceSchedule();
		String conferenceTrackResult = buildConferenceSchedule.trasnformToString(conference);
		System.out.println(conferenceTrackResult);
	}

}
