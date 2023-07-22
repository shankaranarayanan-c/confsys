package com.it.confsys.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.it.confsys.handlers.LoggerHandler;

public class TopicsTextFileParserTest {
	
	private LoggerHandler loggerHandler;
	
	@Before
	public void setup() {
		loggerHandler = new LoggerHandler();
		Logger.getLogger(TopicsTextFileParser.class.getName()).addHandler(loggerHandler);
	}
	
	@Test
	public void testParseValidInputTopics() {
		String filepath = this.getClass().getResource("/confsys_input_valid.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalValidTopics = 2;
		
		assertEquals(totalValidTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			assertEquals(Level.INFO, logRecord.getLevel());
		}
	}
	
	@Test
	public void testParseInValidFile() {
		
		FileParser inputFileParser = new TopicsTextFileParser("Nosuchfile.txt");
		
		try {
			inputFileParser.parse();
		}catch (Exception e) {
			assertEquals(RuntimeException.class.getName(), e.getClass().getName());
		}
		
	}
	
	@Test
	public void testParseInValidDurationDecimal() {
		
		String filepath = this.getClass().getResource("/confsys_input_decimal.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			if(Level.WARNING == logRecord.getLevel()) {
			assertEquals("Ignored topic with decimal duration. Overdoing it in Python 4.5min", logRecord.getMessage());
			}
		}
		
	}
	
	@Test
	public void testParseInValidDurationNegative() {
		
		String filepath = this.getClass().getResource("/confsys_input_negative.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			if(Level.WARNING == logRecord.getLevel()) {
			assertEquals("Ignored topic with negative duration. Overdoing it in Python -45min", logRecord.getMessage());
			}
		}
		
	}
	
	@Test
	public void testParseDurationGreaterThan60() {
		
		String filepath = this.getClass().getResource("/confsys_input_duration_more_than_60.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			if(Level.WARNING == logRecord.getLevel()) {
			assertEquals("Ignored topic with invalid duration. Overdoing it in Python 61min", logRecord.getMessage());
			}
		}
		
	}
	
	@Test
	public void testParseDurationZero() {
		
		String filepath = this.getClass().getResource("/confsys_input_duration_0.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			if(Level.WARNING == logRecord.getLevel()) {
			assertEquals("Ignored topic with invalid duration. Overdoing it in Python 000min", logRecord.getMessage());
			}
		}
		
	}
	
	@Test
	public void testParseDurationLightning() {
		
		String filepath = this.getClass().getResource("/confsys_input_valid_lightning.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			assertEquals(Level.INFO, logRecord.getLevel());
		}
		
	}
	
	@Test
	public void testParseNoDuration() {
		
		String filepath = this.getClass().getResource("/confsys_input_noduration.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		inputFileParser.parse();
		
		int totalTopics = 2;
		
		assertEquals(totalTopics, loggerHandler.getLogRecords().size());
		for(LogRecord logRecord: loggerHandler.getLogRecords()) {
			if(Level.WARNING == logRecord.getLevel()) {
				assertEquals("Ignored topic without valid duration. Overdoing it in Python min", logRecord.getMessage());
			}
		}
		
	}
	
	@Test
	public void testParseNullPath() {
		String filepath = this.getClass().getResource("/confsys_input_valid.txt").getFile();
		File inputFile = new File(filepath);
		
		FileParser inputFileParser = new TopicsTextFileParser(inputFile.getAbsolutePath());
		
		try {
		final RandomAccessFile lockInputFile = new RandomAccessFile(inputFile.getAbsoluteFile(), "rw");
		lockInputFile.getChannel().lock();
		
		
		inputFileParser.parse();
		}catch (Exception e) {
			assertEquals(RuntimeException.class.getName(), e.getClass().getName());
		}
		
	}

}
