package org.landal.remote;

import org.landal.model.Book;


public interface BookService {
	
	void create(Book book);
	
	Book find(String isbn);

}
