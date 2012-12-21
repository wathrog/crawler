package net.crawler.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUrlExtractor implements UrlExtractor {
	
	private static final String ANCHOR_PATTERN = "<a href=\"(\\S+)\""; 

	@Override
	public List<URL> getUrls(URL context, String content) {
		List<URL> urls = new ArrayList<>();
		
		Pattern p = Pattern.compile(ANCHOR_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(content);
		
		while (m.find()) {
			String provisionalUrl = m.group(1);
			URL url = createUrl(context, provisionalUrl);
			if (url != null) {
				urls.add(url);
			}
		}
		
		return urls;
	}
	
	private URL createUrl(URL context, String provisionalUrl) {
		URL ret = null;
		try {
			// Try to extract full URL
			ret = new URL(provisionalUrl);
		} catch (MalformedURLException e) {
			try {
				// If fail, try to extract URL with context
				ret = new URL(context, provisionalUrl);
			} catch (MalformedURLException e1) {
				// If all fails we don't have a good url
			}
		}
		
		return ret;
		
	}

}
