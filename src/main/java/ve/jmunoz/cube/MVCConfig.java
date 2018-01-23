package ve.jmunoz.cube;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * <h1>Class MVCConfig</h1><br>
 * Initialize default MVC configurer to manage Servlet requests and responses<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
@EnableWebMvc
@Configuration
@ComponentScan("ve.jmunoz")
public class MVCConfig extends WebMvcConfigurerAdapter {

	/**
	 * <h2>Configure default servlet handler - configureDefaultServletHandling</h2> Method that configure
	 * standard default servlet handler.
	 * 
	 * @param ServletContext servletContext: Servlet context configuration
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/").setViewName("home") ;
		registry.addViewController("").setViewName("home") ;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".html");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
}