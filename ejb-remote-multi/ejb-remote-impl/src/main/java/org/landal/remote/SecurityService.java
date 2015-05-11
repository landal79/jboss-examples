package org.landal.remote;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.jboss.ejb3.annotation.Depends;
import org.jboss.security.plugins.JaasSecurityManagerServiceMBean;

@Stateless
@LocalBean
public class SecurityService {

	@Resource
	private SessionContext sessionContext;

	@Depends("jboss.security:service=JaasSecurityManager")
	private JaasSecurityManagerServiceMBean jaasSecurityManagerService;

	public void logout()
	{
		jaasSecurityManagerService.flushAuthenticationCache("EjbSecurityDomain", sessionContext.getCallerPrincipal());
	}

	/**
	 * This method performs logout with MBean programmatic lookup
	 */
	public void logoutWithProgrammaticLookup()
	{
		try
		{
			ObjectName jaasMgr = new ObjectName("jboss.security:service=JaasSecurityManager");
			Object[] params = { "EjbSecurityDomain", sessionContext.getCallerPrincipal() };
			String[] signature = { "java.lang.String", "java.security.Principal" };
			MBeanServer server = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
			server.invoke(jaasMgr, "flushAuthenticationCache", params, signature);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
