package com.ikolosov.concurrency.parser.processing;

import java.util.concurrent.Callable;

/**
 * @author ikolosov
 */
public interface ILineTask extends Callable<String> {
}
