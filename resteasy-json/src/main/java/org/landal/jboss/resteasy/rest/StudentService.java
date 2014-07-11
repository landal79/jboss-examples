package org.landal.jboss.resteasy.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.landal.jboss.resteasy.model.Student;

@Path("/students")
public class StudentService {

	private static List<Student> students;

	static {
		students = new ArrayList<>();
		students.add(Student.newInstance("bob", "marley", 40));
		students.add(Student.newInstance("bob", "gheldof", 40));
		students.add(Student.newInstance("lue", "reed", 40));
	}

	@GET
	@Produces("application/json")
	public List<Student> getAll(){
		return students;
	}

	@GET
	@Path("/print/{name}")
	@Produces("application/json")
	public Student echoName(@PathParam("name") String name) {
		return Student.newInstance(name, "reed", 40);
	}

	@POST
	@Path("/new")
	@Consumes("application/json")
	public Response add( Student student ) {
		String output = student.toString();
		return Response.status(200).entity(output).build();
	}

}
