package com.ikolosov.concurrency.parser.processing;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author ikolosov
 */
public class LineProcessor implements ILineProcessor {

	private final ExecutorService service;

	public LineProcessor(ThreadPool threadPool) {
		this.service = threadPool.getService();
	}

	@Override
	public Collection<String> sort(List<String> lines, SortBy sortBy)
			throws InterruptedException, ExecutionException, TimeoutException {
		Map<String, Future<String>> taskMap = new LinkedHashMap<>();
		SortedMap<String, String> resultMap = new ConcurrentSkipListMap<>();
		try {
			for (String aLine : lines)
				taskMap.put(
						sortBy.getSortKey(aLine),
						service.submit(new LineTask(aLine)));
			for (Map.Entry<String, Future<String>> mapEntry : taskMap.entrySet())
				resultMap.put(
						mapEntry.getKey(),
						mapEntry.getValue().get(getTaskWaitDuration(), getTaskWaitTimeUnit()));
		} finally {
			service.shutdown();
		}
		return resultMap.values();
	}
}
