package org.landal.jboss.resteasy.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Student {

	private static Integer idCounter = 0;

	public static Student newInstance(String firstName, String lastName, Integer age){
		return new Student(firstName, lastName, age, idCounter++);
	}

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;

	// Must have no-argument constructor
	public Student() {

	}

	public Student(String fname, String lname, Integer age, Integer id) {
		this.firstName = fname;
		this.lastName = lname;
		this.age = age;
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lname) {
		this.lastName = lname;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}


}
