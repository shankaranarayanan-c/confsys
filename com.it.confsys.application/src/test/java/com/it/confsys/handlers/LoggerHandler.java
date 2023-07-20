package com.it.confsys.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoggerHandler extends Handler {
	
	private List<LogRecord> logRecords = new ArrayList<LogRecord>();

	@Override
	public void publish(LogRecord record) {
		logRecords.add(record);
	}

	@Override
	public void flush() {
		
	}

	@Override
	public void close() throws SecurityException {
		
	}

	public List<LogRecord> getLogRecords() {
		return logRecords;
	}

}
