package com.cds.groovy.config

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.springframework.jdbc.core.JdbcTemplate

@CompileStatic
@Log4j2
class DatabaseSetup {

	static String insertStatement = "insert into users (user_name, user_email) values (?,?)"
	static String createStatement = "create table if not exists users (user_id int identity, user_name varchar(50), user_email varchar(250));"
	
	public JdbcTemplate setupDatabases(JdbcTemplate template) {
		template.execute("SET DB_CLOSE_DELAY -1")
		template.execute(createStatement)
		
		int recordCount = template.queryForObject("select count(*) from users", Integer.class)
		
		if (recordCount == 0) {
			log.info("Inserting two records into users table")
			
			int result = template.update(insertStatement, "mike", "mike@abc.net")
			result = template.update(insertStatement, "john", "john@abc.com")
		}
		return template
	}
	
	public JdbcTemplate setupTestDatabases(JdbcTemplate template) {
		template = setupDatabases(template)
		int recordCount = template.queryForObject("select count(*) from users", Integer.class)
		
		if (recordCount == 2) {
			log.trace("Inserting additional test records into users table")
			
			template.update(insertStatement, "jack", "jack@thehill.com")
			template.update(insertStatement, "mookie", "nunnov@yerbiznazz.info")
		}
		return template
	}
}
