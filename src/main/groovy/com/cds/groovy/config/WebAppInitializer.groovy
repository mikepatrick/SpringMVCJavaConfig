package com.cds.groovy.config

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import javax.servlet.ServletContext
import javax.servlet.ServletRegistration

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

@CompileStatic
@Log4j2
class WebAppInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext container) {
		log.trace("WebAppInitializer.onStartup()")
		
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext()
 
		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext))
 
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext()
		dispatcherServlet.register(ApplicationConfig.class)
			 
		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet))
		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
		 
	}
}
