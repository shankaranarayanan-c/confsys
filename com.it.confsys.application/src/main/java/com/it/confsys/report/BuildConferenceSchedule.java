package com.it.confsys.report;

import java.time.Duration;
import java.util.Map;

import com.it.confsys.model.conference.Conference;
import com.it.confsys.model.conference.PresentationSession;
import com.it.confsys.model.conference.Track;
import com.it.confsys.util.AppConstants;

/**
 * Prepares the conference tracks to be displayed containing the titles and durations
 */
public class BuildConferenceSchedule {
	
	private StringBuilder result = new StringBuilder();

	/**
	 * Iterates through the conference object and prepares the conference schedule
	 * as a String
	 * @param conference {@link Conference}
	 * @return conference tracks with title and durations as strings
	 */
	public String trasnformToString(Conference conference) {
		//Populate conference details
		addToResult("*** CONFERENCE SCHEDULE ***");
		Map<Integer, Track> tracks = conference.getTracks();
		for(Integer trackId: conference.getTracks().keySet()) {
			addToResult("### Track: "+ trackId+ " ###");
			Track track = tracks.get(trackId);
			addSessionTopicsToResult((PresentationSession) track.getSessions().get(AppConstants.MORNING_SESSION));
			addSessionTopicsToResult((PresentationSession) track.getSessions().get(AppConstants.AFTERNOON_SESSION));
		}
		return result.toString();
	}

	private void addToResult(String data) {
		result.append(System.lineSeparator());
		result.append(data);
	}

	private void addSessionTopicsToResult(PresentationSession session) {
		for(Duration startTime : session.getSchedule().keySet()) {
			addToResult(String.format("%02d", startTime.toHours())+":"+String.format("%02d", startTime.toMinutesPart())+ "  "+session.getSchedule().get(startTime));
		}
	}

}
