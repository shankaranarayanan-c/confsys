package com.it.confsys.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class acts as a place holder for the list of topics along with the durations
 */
public class ConferenceTopics implements InputData {

	private final Map<Integer, HashSet<String>> topics = new HashMap<Integer, HashSet<String>>();

	/**
	 * Adds the given topic and duration to multi map.
	 * The titles are stored in a collection if the duration are same
	 * @param duration the title duration
	 * @param topic the presentation topic along with duration
	 */
	public void addTopic(int duration, String topic) {
		if(! this.topics.containsKey(duration)){
			this.topics.put(duration, new HashSet<String>());
		}
		this.topics.get(duration).add(topic);
	}

	/**
	 * Returns all the durations which are provided as input by the user
	 * 
	 * @return collections of integers
	 */
	public Set<Integer> getDurations() {
		return topics.keySet();
	}

	/**
	 * Returns all the topics which are associated with the given duration
	 * 
	 * @param duration for the user needs the topics
	 * @return collection of topics along with the duration
	 */
	public HashSet<String> getTopicsForDuration(Integer duration) {
		return topics.get(duration);
	}

}
