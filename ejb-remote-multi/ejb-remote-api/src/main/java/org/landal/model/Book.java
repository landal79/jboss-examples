package org.landal.model;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;

	public Book(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "Book [" + getTitle() + "]";
	}

	public String getTitle()
	{
		return title;
	}

}
