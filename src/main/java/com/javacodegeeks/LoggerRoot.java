package com.javacodegeeks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LoggerRoot {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	public static void main(String... args) {
		MDC.put("app", "JCG");
		IntStream.rangeClosed(1, 10).forEach(counter -> {
			logger.info("Counter:" + counter);
		});

		long count = logger.isInfoEnabled() ? 10000 : 100000000;
		Instant start = Instant.now();
		generalLog(count);
		Duration generalLogDuration = Duration.between(start, Instant.now());
		start = Instant.now();
		conditionalLog(count);
		Duration conditionalLogDuration = Duration.between(start, Instant.now());
		start = Instant.now();
		parameterizedLog(count);
		Duration parameterizedLogDuration = Duration.between(start, Instant.now());
		logger.error("General Log->{}", generalLogDuration);
		logger.error("Conditional Log->{}", conditionalLogDuration);
		logger.error("parameterized Log->{}", parameterizedLogDuration);
	}

	public static void generalLog(long count) {
		LongStream.rangeClosed(1, count).forEach(counter -> {
			logger.info("Counter:" + counter);
		});
	}

	public static void conditionalLog(long count) {
		LongStream.rangeClosed(1, count).forEach(counter -> {
			if (logger.isInfoEnabled()) {
				logger.info("Counter:" + counter);
			}
		});
	}

	public static void parameterizedLog(long count) {
		LongStream.rangeClosed(1, count).forEach(counter -> {
			logger.info("Counter:{}", counter);
		});
	}
}