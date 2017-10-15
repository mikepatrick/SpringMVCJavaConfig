package test.cds.groovy.controllers

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import groovy.transform.CompileStatic

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MvcResult

import test.cds.groovy.base.BaseMvcTest
import test.cds.groovy.config.TestApplicationConfig

import com.cds.groovy.domain.GroovyUser

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestApplicationConfig.class)
@CompileStatic
class UserControllerTests extends BaseMvcTest {

	@Test
	public void testAddUser() {
		MvcResult result =
		mockMvc.perform(get("/addUser").param("newUserName", "bill")
									   .param("newUserEmail", "bill@sky.net")
									   .sessionAttrs(sessAttrs))
			   .andExpect(status().isOk())
			   .andExpect(view().name("dashboard"))
			   .andExpect(model().attribute("users", hasSize(5)))
			   .andExpect(model().attribute("name", is("testUser")))
			   .andReturn()
			   
		List<GroovyUser> users = (List<GroovyUser>) result.getModelAndView().getModel().get("users")
		GroovyUser user = users.find { it != null && it.name == "bill"}
		
		assert(user.email == "bill@sky.net")
		assert(user.name == "bill")
	}
	
	@Test
	public void testDeleteUser() {
		MvcResult result =
		mockMvc.perform(get("/deleteUser").param("userId", "1").sessionAttrs(sessAttrs))
			   .andExpect(status().isOk())
			   .andExpect(view().name("dashboard"))
			   .andExpect(model().attribute("users", hasSize(4)))
			   .andReturn()
			   
//		List<GroovyUser> userList = (List<GroovyUser>) result.getModelAndView().getModel().get("users")
//		assert(userList.size() == 4)
			   
	}
	
	@Test
	public void testDeleteAllUsers() {
		mockMvc.perform(get("/deleteAllUsers").sessionAttrs(sessAttrs))
			   .andExpect(status().isOk())
			   .andExpect(view().name("dashboard"))
			   .andExpect(model().attribute("users", hasSize(0)))
			   .andExpect(model().attribute("name", "testUser"))
	}
	

}
