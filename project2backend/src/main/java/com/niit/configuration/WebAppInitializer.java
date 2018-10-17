package com.niit.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//similar to web.xml file configuration 
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	public WebAppInitializer() {
		System.out.println("WebAppInitializer loaded and instantiated");
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//create beans for DataSource,SessionFactory,HibernateTransactionManager
		return new Class[]{WebAppConfig.class,DBConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Enable webmvc, handler mapping,scan all the components in the base package "com.niit"
		// TODO Auto-generated method stub
		return null;
	}

	//forward all the incoming requests to DispatcherServlet by specifying the url pattern as '/'
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"}; //url-pattern in web.xml file for the servlet dispatcher
	}

}

