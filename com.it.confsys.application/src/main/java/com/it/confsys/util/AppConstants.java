package com.it.confsys.util;

public final class AppConstants {
	
	private AppConstants() {
		
	}

	public static final int LIGHTNING_DURATION = 5;
	
	public static final int MORNING_SESSION_START_TIME = 9;
	
	public static final int MORNING_SESSION_END_TIME = 12;
	
	public static final int AFTERNOON_SESSION_START_TIME = 13;
	
	public static final int AFTERNOON_SESSION_END_TIME = 17;
	
	public static final int HR_SESSION_MINUTES = 60;
	
	public static final int MORNING_SESSION_DURATION = (MORNING_SESSION_END_TIME - MORNING_SESSION_START_TIME) * HR_SESSION_MINUTES;
	
	public static final int AFTERNOON_SESSION_DURATION = (AFTERNOON_SESSION_END_TIME - AFTERNOON_SESSION_START_TIME) * HR_SESSION_MINUTES;
	
	public static final int TOTAL_SESSION_DURATION = MORNING_SESSION_DURATION + AFTERNOON_SESSION_DURATION;
	
	public static final int NETWORKING_SESSION_MAX_START_TIME = 17;
	
	public static final int NETWORKING_SESSION_MIN_START_TIME = 16;
	
	public static final String MORNING_SESSION = "morning";
	
	public static final String AFTERNOON_SESSION = "afternoon";
	
//	public static final String NETWORKING_SESSION = "networking";
	
//	public static final int SESSION_BREAK_TIME = 0;
	
}
