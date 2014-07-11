package org.landal.jboss.resteasy.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.landal.jboss.resteasy.model.Student;

@RunWith(Arquillian.class)
@RunAsClient
public class StudentServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		File[] libs = Maven.resolver()
                /*.loadPomFromFile("pom.xml")*/.resolve("org.apache.commons:commons-lang3:3.3.2")
                .withTransitivity().as(File.class);

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, StudentService.class.getPackage(), Student.class.getPackage())
				.addAsWebInfResource("web.xml").addAsLibraries(libs);
	}

	@ArquillianResource
	private URL deploymentUrl;

	@Test
	public void test_get_all() throws Exception {
		URL bookland = new URL(deploymentUrl.toString() + "rest/students");
		String result = IOUtils.toString(bookland.openStream(), "UTF-8");

		assertNotNull(result);
		assertFalse(StringUtils.isEmpty(result));
	}

	@Test
	public void test_new_student() {

		Student st = Student.newInstance("Catain", "Hook", 10);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + "rest/students/new");
		Response response = target.request().post(Entity.entity(st, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			fail("Failed : HTTP error code : " + response.getStatus());
		}

		assertThat(response.readEntity(String.class), is(st.toString()));

		response.close();

	}
}
