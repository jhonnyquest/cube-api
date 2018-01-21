package ve.jmunoz.cube;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <h1>Class WebInitializer</h1><br>
 * Web initializer initiate all basic parameters from web services application<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class WebInitializer implements WebApplicationInitializer {
	
	/**
	 * <h2>Initialize web dispatcher - onStartup</h2> Method that initialize the webserver dispatcher when
	 * application starts
	 * 
	 * @param ServletContext servletContext: Servlet context configuration
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");		
	}

	/**
	 * <h2>Get context configuration - getContext</h2> Method that retrieve the application context initialized
	 * 
	 * @return AnnotationConfigWebApplicationContext: Context of the web application.
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("ve.jmunoz.cube");
		return context;
	}
}
