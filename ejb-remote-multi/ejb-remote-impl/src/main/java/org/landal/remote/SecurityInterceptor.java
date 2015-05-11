package org.landal.remote;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This interceptor performs the JAAS logout.
 *
 */
public class SecurityInterceptor {
	
	@EJB
	private SecurityService securityService;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		Object result = context.proceed();
		securityService.logout();
//		securityService.logoutWithProgrammaticLookup();
		return result;
	}


}
