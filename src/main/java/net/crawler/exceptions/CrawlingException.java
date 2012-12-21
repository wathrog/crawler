package net.crawler.exceptions;

public class CrawlingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1172144627436166175L;
	
	public CrawlingException(String message) {
		super(message);
	}
	
	public CrawlingException(String message, Throwable cause) {
		super(message,cause);
	}

}
