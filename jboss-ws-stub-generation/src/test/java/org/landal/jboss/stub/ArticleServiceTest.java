package org.landal.jboss.stub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class ArticleServiceTest {
	
	private static final Log logger = LogFactory.getLog(ArticleServiceTest.class);  

	private ArticleServicePT articleServicePTPort;

	@Before
	public void setUp() {
		ArticleService articleService = new ArticleService();
		articleServicePTPort = articleService.getArticleServicePTPort();
	}

	@Test
	public void testGet() {

		List<ArticleType> articleTypes = articleServicePTPort.getAll();
		assertNotNull(articleTypes);
		assertFalse(articleTypes.isEmpty());
		
		logger.info("# of articles " + articleTypes.size());

	}

}
