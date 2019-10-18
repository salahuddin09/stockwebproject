
package com.soft.stock.common;

public class StockException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StockException() {
		super();
	}

	public StockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StockException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockException(String message) {
		super(message);
	}

	public StockException(Throwable cause) {
		super(cause);
	}

}
