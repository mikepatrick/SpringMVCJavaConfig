package test.cds.groovy.controllers

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MvcResult

import test.cds.groovy.base.BaseMvcTest
import test.cds.groovy.config.TestApplicationConfig

@Log4j2
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestApplicationConfig.class)
@CompileStatic
class RestControllerTests extends BaseMvcTest {
	
	@Test
	public void testGetUser() {
		MvcResult result =
		mockMvc.perform(get("/ajax/1/getUser"))
			   .andExpect(status().isOk())
			   .andReturn();
			   
		String response = result.getResponse().getContentAsString();
		log.info(response)
		
	}
	
	@Test
	public void testPostUser() {
		
		String jsonToPost = """ {"id":1,"name":"mike","email":"mike@abc.net"} """
		
		MvcResult result = 
		mockMvc.perform(post("/ajax/postUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonToPost))
			   .andExpect(status().isOk())
			   .andReturn()
		String response = result.getResponse().getContentAsString();
		log.info(response)
			  
	}
}
