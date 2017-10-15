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
class WelcomeControllerTests extends BaseMvcTest {
	
	@Test
	public void testWelcomeEntry() throws Exception {
		
		 mockMvc.perform(get("/welcome"))
		 		.andExpect(status().isOk())
				.andExpect(view().name("welcome"))
				
		mockMvc.perform(get("/welcome").param("viewType", "groovy"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("groovyWelcome"))
	}
	
	@Test
	public void testDashboard() {
		
		MvcResult results = 
		mockMvc.perform(get("/Dashboard").param("login", "testUser"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("dashboard"))
			   .andExpect(model().attributeExists("users"))
			   .andExpect(model().attribute("users", hasSize(4)))
			   .andExpect(model().attribute("name", "testUser"))
			   .andReturn()
			   
		Map<String, Object> model = results.getModelAndView().getModel()
		List<GroovyUser> userList = (List<GroovyUser>) model.get("users")
		GroovyUser user = userList.find { it != null && it.name == "john" }
		
		assert(user.email == "john@abc.com")
	}
	

}
