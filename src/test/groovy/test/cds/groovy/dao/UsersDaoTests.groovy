package test.cds.groovy.dao

import groovy.transform.CompileStatic

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration

import test.cds.groovy.config.TestApplicationConfig

import com.cds.groovy.dao.UsersDao
import com.cds.groovy.domain.GroovyUser

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestApplicationConfig.class)
@CompileStatic
class UsersDaoTests {

	@Autowired UsersDao dao;
	
	@Test
	public void testGetUsers() {
		List<GroovyUser> userList = dao.getUsers()
		assert(userList.size() == 4)
		
		List<String> userNames = userList.collect { it.name }
		assert(userNames.contains("john"))
		assert(userNames.contains("mike"))
		assert(userNames.contains("jack"))
		assert(userNames.contains("mookie"))
		
		List<String> userEmails = userList.collect { it.email }
		assert(userEmails.contains("mike@abc.net"))
		assert(userEmails.contains("john@abc.com"))
		assert(userEmails.contains("jack@thehill.com"))
		assert(userEmails.contains("nunnov@yerbiznazz.info"))
	}
}
