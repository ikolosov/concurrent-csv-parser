package com.ikolosov.concurrency.parser.processing;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ikolosov
 */
public interface ILineProcessor {

	Collection<String> sort(List<String> lines, SortBy sortBy)
			throws InterruptedException, ExecutionException, TimeoutException;

	default int getTaskWaitDuration() {
		return 10;
	}

	default TimeUnit getTaskWaitTimeUnit() {
		return TimeUnit.SECONDS;
	}

	enum ThreadPool {

		SINGLE_THREAD(Executors.newSingleThreadExecutor()),
		FOUR_THREADS(Executors.newFixedThreadPool(4)),
		EIGHT_THREADS(Executors.newFixedThreadPool(8)),
		CACHED(Executors.newCachedThreadPool()),
		WORK_STEALING(Executors.newWorkStealingPool());

		private final ExecutorService service;

		ThreadPool(ExecutorService service) {
			this.service = service;
		}

		public ExecutorService getService() {
			return service;
		}
	}

	enum SortBy {

		ID(0),
		FIRST_NAME(1),
		SECOND_NAME(2),
		OCCUPATION(3);

		private static final String separator = ",";
		private final int index;

		SortBy(int index) {
			this.index = index;
		}

		public String getSortKey(String line) {
			return line != null ? line.split(separator)[index] : "";
		}
	}
}
