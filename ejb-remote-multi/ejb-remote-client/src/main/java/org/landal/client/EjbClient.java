package org.landal.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.landal.model.Book;
import org.landal.remote.BookService;

public class EjbClient {

	public static void main(String[] args)
	{
		try
		{
			Properties props = new Properties();
			//props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.security.jndi.JndiLoginInitialContextFactory");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			props.put(Context.SECURITY_PRINCIPAL, "username");
			props.put(Context.SECURITY_CREDENTIALS, "password");
			
			
			Context context = new InitialContext(props);

			BookService bookService = (BookService) context.lookup("ejb/BookService");

			bookService.create(new Book("title"));
			
			Book book = bookService.find("isbn");
			
			System.out.println(book);
			
			book = bookService.find("isbn");
			
			context.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
