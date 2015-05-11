package org.landal.remote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.landal.model.Book;

@Stateless
@Remote(BookService.class)
@Interceptors({SecurityInterceptor.class})
public class BaseBookService implements BookService {

	@Override
	public void create(Book book)
	{
		if (book == null)
		{
			throw new NullPointerException();
		}

		System.out.println(book.getTitle());
	}

	@Override
	public Book find(String isbn)
	{
		return new Book("title");
	}

}
