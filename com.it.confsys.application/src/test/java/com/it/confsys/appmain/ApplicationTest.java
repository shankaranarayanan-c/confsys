package com.it.confsys.appmain;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.it.confsys.handlers.LoggerHandler;

public class ApplicationTest {
	
	private LoggerHandler loggerHandler;
	
	@Before
	public void setup() {
		loggerHandler = new LoggerHandler();
		Logger.getLogger(ApplicationTest.class.getName()).addHandler(loggerHandler);
	}
	
	@Test
	public void testMainPostive() {
		String[] args = {"pathToInputFile"};
 		Application.main(args);
 		
 		assertEquals(0, loggerHandler.getLogRecords().size());
	}
	
	@Test
	public void testMainNegative() {
		String[] args = {};
		Exception exception = null;
		try {
 		Application.main(args);
		}catch (Exception e) {
			exception = e;
		}
 		
		if(exception != null) {
			assertEquals(exception.getMessage(), "Input file with list of topics and durations as argument expected");
		}
	}

}
