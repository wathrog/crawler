package net.crawler;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CrawlerManager {
	
	private int limit;
	private Set<URL> foundUrls;
	private Queue<URL> parsingQueue;
	
	
	public CrawlerManager(URL initialUrl, int limit) {
		if (limit < 1) throw new IllegalArgumentException("Limit should be > 0");
		this.limit = limit;
		
		foundUrls = new HashSet<>();
		parsingQueue = new LinkedList<>();
		parsingQueue.add(initialUrl);
		
	}
	
	public List<URL> startCrawling() {
		while (!parsingQueue.isEmpty() || foundUrls.size() < limit) {
			//TODO
		}
		return null;
	}
	
	
	
	
	
	

}
