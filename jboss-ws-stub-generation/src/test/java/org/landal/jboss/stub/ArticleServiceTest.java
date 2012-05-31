package org.landal.jboss.stub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class ArticleServiceTest {

	private static final Log logger = LogFactory
			.getLog(ArticleServiceTest.class);

	@Test
	public void testGet_WithDefaultURL() {

		ArticleService articleService = new ArticleService();
		ArticleServicePT articleServicePTPort = articleService
				.getArticleServicePTPort();

		List<ArticleType> articleTypes = articleServicePTPort.getAll();
		assertNotNull(articleTypes);
		assertFalse(articleTypes.isEmpty());

		logger.info("# of articles " + articleTypes.size());

	}

	@Test
	public void testGet_WithCustomURL() throws MalformedURLException {
		
		ArticleService articleService = new ArticleService(new URL("http://www.predic8.com:8080/material/ArticleService?wsdl"));
		ArticleServicePT articleServicePTPort = articleService.getArticleServicePTPort();

		List<ArticleType> articleTypes = articleServicePTPort.getAll();
		assertNotNull(articleTypes);
		assertFalse(articleTypes.isEmpty());
		
		logger.info("# of articles " + articleTypes.size());

	}

}
