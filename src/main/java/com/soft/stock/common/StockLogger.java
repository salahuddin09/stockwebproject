
package com.soft.stock.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockLogger {
	private Logger logger;

	public StockLogger(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public static final StockLogger getLogger(Class<?> clazz) {
		return new StockLogger(clazz);
	}

	public void info(String msg) {
		this.logger.info(msg);
	}

	public void info(String format, Object... args) {
		this.logger.info(format, args);
	}

	public void debug(String msg) {
		this.logger.debug(msg);
	}

	public void debug(String format, Object... args) {
		this.logger.info(format, args);
	}

	public void warn(String msg) {
		this.logger.info(msg);
	}

	public void warn(String format, Object... args) {
		this.logger.info(format, args);
	}

	public void error(String msg) {
		this.logger.error(msg);
	}

	public void error(String format, Object... args) {
		this.logger.error(format, args);
	}

	public void error(Throwable t) {
		this.logger.error(t.getMessage(), t);
	}

	public void error(String msg, Throwable t) {
		this.logger.error(msg, t);
	}
}
