package com.ikolosov.concurrency.parser;

import com.ikolosov.concurrency.parser.processing.ILineProcessor;
import com.ikolosov.concurrency.parser.processing.ILineProcessor.SortBy;
import com.ikolosov.concurrency.parser.processing.ILineProcessor.ThreadPool;
import com.ikolosov.concurrency.parser.processing.LineProcessor;
import com.ikolosov.concurrency.parser.utils.FileHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author ikolosov
 */
public class AtWork {

	public static void main(String[] args) {
		try {
			// [] absolute paths
			String readFrom = new File(AtWork.class.getResource("/unsorted.csv").toURI()).getAbsolutePath();
			String writeTo = new File(readFrom).getParentFile().getAbsolutePath().concat(File.separator + "sorted.csv");
			// [] read lines from file
			List<String> lines = FileHelper.readLines(readFrom);
			System.out.println("source file:\n" + readFrom + "\n\nlines:");
			lines.forEach(System.out::println);
			// [] perform lines sort
			ILineProcessor lineProcessor = new LineProcessor(ThreadPool.FOUR_THREADS);
			Instant startPoint = Instant.now();
			Collection<String> sortedLines = lineProcessor.sort(lines, SortBy.FIRST_NAME);
			Instant endPoint = Instant.now();
			System.out.println("\nsorted lines:");
			sortedLines.forEach(System.out::println);
			System.out.println("\nsort duration: " + Duration.between(startPoint, endPoint).toMillis() + "ms");
			// [] write sorted content back to file
			boolean recorded = FileHelper.writeLines(writeTo, sortedLines);
			System.out.println("\nsorted lines were " + (recorded ? "" : "not ") + "recorded into file:\n" + writeTo);
		} catch (URISyntaxException |
				IOException | RuntimeException |
				InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
