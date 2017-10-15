package test.cds.groovy.config

import groovy.transform.CompileStatic

import javax.sql.DataSource

import org.h2.jdbcx.JdbcConnectionPool
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import test.cds.groovy.test.strings.UrlResultTestStrings

import com.cds.groovy.config.ApplicationConfig
import com.cds.groovy.config.DatabaseSetup
import com.cds.groovy.web.utils.GetUrl
import com.cds.groovy.web.utils.GetUrlIF

@CompileStatic
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.cds" )
class TestApplicationConfig extends ApplicationConfig {

	@Bean
	public GetUrlIF getUrl() {
		return { String url ->
			return UrlResultTestStrings.testStrings.get(url)
		} as GetUrl
	}
	
	@Bean
	@Override
	public DataSource dataSource() {
		return JdbcConnectionPool.create(env.getProperty("cds.spring.h2.test.connectionstring"),
										 env.getProperty("cds.spring.h2.username"),
										 env.getProperty("cds.spring.h2.password") );
	}
	
	@Bean
	@Override
	public JdbcTemplate jdbcTemplate() {
		return dbSetup().setupTestDatabases(new JdbcTemplate(dataSource()))
	}
	
	@Bean
	public DatabaseSetup dbSetup() {
		return new DatabaseSetup()
	}
}
