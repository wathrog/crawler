package net.crawler.agent;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

import net.crawler.exceptions.CrawlingException;
import net.crawler.parser.UrlExtractor;

public class CrawlingTask implements Callable<List<URL>> {
	
	private URL startUrl;
	private UrlExtractor extractor;
	
	public CrawlingTask(URL startUrl, UrlExtractor extractor) {
		this.startUrl = startUrl;
		this.extractor = extractor;
	}

	@Override
	public List<URL> call() throws Exception {
		if (startUrl.getProtocol() != "http") {
			throw new CrawlingException("Unsupported protocol");
		}
		HttpURLConnection conn = (HttpURLConnection)startUrl.openConnection();
		try {
			conn.setReadTimeout(10000);
			conn.connect();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new CrawlingException("Wrong return code, expected: '" + HttpURLConnection.HTTP_OK + "' got: '" + conn.getResponseCode() + "'");
			}
			
			if (conn.getContentType() != "text/html") {
				throw new CrawlingException("Unsupported content type");
			}
			
			String content = (String)conn.getContent();
			return extractor.getUrls(startUrl, content);
			
		} finally {
			conn.disconnect();
		}
		
	}
	
	public URL getStartUrl() {
		return startUrl;
	}


}
