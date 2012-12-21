package net.crawler.parser;

import java.net.URL;
import java.util.List;

public interface UrlExtractor {
	
	public List<URL> getUrls(URL context, String content);

}
