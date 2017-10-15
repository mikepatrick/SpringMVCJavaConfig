

package com.cds.groovy.config

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import javax.sql.DataSource

import org.h2.jdbcx.JdbcConnectionPool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.groovy.GroovyMarkupConfigurer
import org.springframework.web.servlet.view.groovy.GroovyMarkupViewResolver

import com.cds.groovy.dao.UsersDao
import com.cds.groovy.dao.UsersDaoIF
import com.cds.groovy.domain.global.customer.Customer
import com.cds.groovy.domain.global.customer.GlobalCustomer
import com.cds.groovy.domain.global.customer.GlobalCustomerResponse
import com.cds.groovy.domain.recreg.Address
import com.cds.groovy.domain.recreg.Assignment
import com.cds.groovy.domain.recreg.AssignmentDates
import com.cds.groovy.domain.recreg.Email
import com.cds.groovy.domain.recreg.Product
import com.cds.groovy.domain.recreg.RecRegResponse
import com.cds.groovy.domain.recreg.User
import com.cds.groovy.service.GlobalCustomerLookupService
import com.cds.groovy.service.RecRegLookupService
import com.cds.groovy.web.utils.GetUrl
import com.cds.groovy.web.utils.GetUrlIF
import com.cds.groovy.xstream.GlobalCustomerXstream
import com.cds.groovy.xstream.RecRegXstream
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.StaxDriver




@CompileStatic
@EnableWebMvc
@Configuration
@Log4j2
@PropertySource("classpath:properties/application.properties")
@ComponentScan(basePackages = "com.cds" )
class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Autowired Environment env;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
	}
 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable()
	}
 
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		return new InternalResourceViewResolver([prefix: "/WEB-INF/pages/jsp/", suffix: ".jsp", order: 99])
	}
	
    @Bean
    public GroovyMarkupConfigurer groovyMarkupConfigurer() {
		return new GroovyMarkupConfigurer( [resourceLoaderPath: "/WEB-INF/pages/groovy/"] )
    }
	
	@Bean public GroovyMarkupViewResolver groovyViewResolver() {
		return new GroovyMarkupViewResolver( [suffix: ".groovy", order: 1] )
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new DatabaseSetup().setupDatabases(new JdbcTemplate(dataSource()))
	}
	
	@Bean
	public DataSource dataSource() {
		return JdbcConnectionPool.create(env.getProperty("cds.spring.h2.connectionstring"), 
			                             env.getProperty("cds.spring.h2.username"), 
										 env.getProperty("cds.spring.h2.password") );
	}
	
	@Bean
	public UsersDaoIF userDao() {
		log.trace("selecting UsersDao as UsersDaoIF implementation.")
		return new UsersDao()
	}
	
	@Bean
	public RecRegLookupService recRegService() {
		return new RecRegLookupService();	
	}
	
	@Bean
	@Qualifier("recRegXstream")
	public XStream recRegXstream() {
		XStream recRegXstream = configureForRecRegResponse(new XStream(new StaxDriver()))
		recRegXstream.ignoreUnknownElements();
		return recRegXstream
	}	
	
	@Bean
	public GlobalCustomerLookupService globalCustomerService() {
		return new GlobalCustomerLookupService()
	}
	
	@Bean
	public GlobalCustomerXstream getGlobalCustomerXstream() {
		return new GlobalCustomerXstream()
	}
	
	@Bean
	public RecRegXstream getRecRegXstream() {
		return new RecRegXstream()
	}
	
	@Bean
	@Qualifier("globalCustomerXstream")
	public XStream globalCustomerXstream () {
		XStream xstream = configureForRecRegResponse(new XStream(new StaxDriver()))
		xstream.alias("transaction", GlobalCustomerResponse.class)
		xstream.addImplicitCollection(GlobalCustomer.class, "customers", "customer", Customer.class);
		xstream.ignoreUnknownElements();
		return xstream
	}
	
	@Bean
	public GetUrlIF getUrl() {
		return new GetUrl()
	}
	
	private XStream configureForRecRegResponse(XStream xstream) {
		xstream.alias("address", Address.class)
		xstream.alias("assignment", Assignment.class)
		xstream.alias("assignmentDates", AssignmentDates.class)
		xstream.alias("email", Email.class)
		xstream.alias("product", Product.class)
		xstream.alias("response", RecRegResponse.class)
		xstream.alias("user", User.class)
		
		xstream.aliasField("products", User.class, "productList")
		xstream.aliasField("addresses", User.class, "addressList")
		xstream.aliasField("data", RecRegResponse.class, "userList")
		xstream.aliasField("assignments", Address.class, "assignmentList")
		xstream.aliasField("emails", User.class, "emailList")
		xstream.aliasField("assignments", Email.class, "assignmentList")
		xstream.aliasField("assignmentDates", Assignment.class, "dateList")
		return xstream
	}
}
