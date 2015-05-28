package com.ikolosov.concurrency.parser.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ikolosov
 */
public final class FileHelper {

	public static List<String> readLines(String absolutePath) throws IOException, RuntimeException {
		if (absolutePath != null) {
			Path path = Paths.get(absolutePath);
			return Files.readAllLines(path);
		}
		throw new RuntimeException("FileHelper#readLines: inappropriate inputs: \nabsolutePath = null");
	}

	public static boolean writeLines(String absolutePath, Iterable<String> lines) throws IOException, RuntimeException {
		if (absolutePath != null && lines != null) {
			Path path = Paths.get(absolutePath);
			Files.createDirectories(path.getParent());
			return Files.write(path, lines).toFile().exists();
		}
		throw new RuntimeException("FileHelper#writeLines: inappropriate inputs: " +
				"\nabsolutePath = " + absolutePath +
				"\nlines = " + lines);
	}

	private FileHelper() {
	}
}
