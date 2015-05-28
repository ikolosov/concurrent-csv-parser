package com.ikolosov.concurrency.parser.processing;

/**
 * @author ikolosov
 */
public class LineTask implements ILineTask {

	private final String line;

	public LineTask(String line) {
		this.line = line;
	}

	@Override
	public String call() throws InterruptedException {
		/* 	[]
			this dummy sleep emulates some processing efforts;
			in real app whatever other business logic may go here, for instance - database record upload
		*/
		Thread.sleep(100);
		// [] marking out a line as a processed one
		return line + ",[PROCESSED]";
	}
}
