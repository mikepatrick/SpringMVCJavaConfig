package com.cds.groovy.domain

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.util.logging.Log4j

import java.sql.ResultSet
import java.sql.SQLException

import org.springframework.jdbc.core.RowMapper

@CompileStatic
@EqualsAndHashCode
@ToString(includeNames=true)
class GroovyUser {

	Integer id
	String name
	String email
	
	public static RowMapper<GroovyUser> MAPPER = [
		
		mapRow: { ResultSet rs, int index ->
			      GroovyUser userBean = new GroovyUser()
				  userBean.id = rs.getInt("user_id")
				  userBean.name = rs.getString("user_name")
				  userBean.email = rs.getString("user_email")
				  return userBean
		}
	] as RowMapper<GroovyUser>
}
