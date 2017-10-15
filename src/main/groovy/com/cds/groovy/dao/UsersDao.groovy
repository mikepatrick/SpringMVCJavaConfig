package com.cds.groovy.dao

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

import com.cds.groovy.domain.GroovyUser

@CompileStatic
@Log4j2
class UsersDao implements UsersDaoIF {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<GroovyUser> getUsers() {
		return jdbcTemplate.query("select * from users", GroovyUser.MAPPER)
	}
	
	@Override
	public int insertUser(String name, String email) {
		return jdbcTemplate.update("insert into users (user_name, user_email) values (?,?)", name, email)
	}
	
	@Override
	public int insertUser(GroovyUser user) {
		return insertUser(user.name, user.email)
	}
	@Override
	public void deleteUsers() {
		jdbcTemplate.execute("delete from users")
	}
	
	@Override
	public void deleteUser(Integer userId) {
		jdbcTemplate.update("delete from users where user_id = ?", userId)
	}
	
	@Override
	public GroovyUser findUser(Integer userId) {
		List<GroovyUser> users = jdbcTemplate.query("select user_id AS id, user_name as name, user_email as email from users where user_id = ?", new BeanPropertyRowMapper<GroovyUser>(GroovyUser.class), userId);
		if (users) {
			return users.get(0);
		}
		
		log.debug("""User ${userId} not found.  Returning "generic" user""")
		return new GroovyUser();
	}
}
