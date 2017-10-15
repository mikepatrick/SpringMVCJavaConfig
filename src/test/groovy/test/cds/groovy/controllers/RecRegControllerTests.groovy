package test.cds.groovy.controllers

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.junit.Test
import org.junit.runner.RunWith
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
class RecRegControllerTests extends BaseMvcTest {
	
	@Test
	public void testHasEmails() {
		MvcResult result = 
		mockMvc.perform(get("/recreg/hasEmail/WNE/001"))
			   .andExpect(status().isOk())
			   .andReturn()
			   
		String returnedJson = result.getResponse().getContentAsString()
		log.info(returnedJson)
	}
	
	@Test
	public void testGetEmails() {
		MvcResult result =
		mockMvc.perform(get("/recreg/getEmails/WNE/001"))
			   .andExpect(status().isOk())
			   .andReturn()
			   
		String returnedJson = result.getResponse().getContentAsString()
		log.info(returnedJson)
	}
	
	@Test
	public void testGetAddresses() {
		MvcResult result =
		mockMvc.perform(get("/recreg/getAddresses/WNE/001"))
			   .andExpect(status().isOk())
			   .andReturn()
			   
		String jsonResponse = result.getResponse().getContentAsString()
		log.info(jsonResponse)
	}
	
	@Test
	public void testGetProducts() {
		MvcResult result =
		mockMvc.perform(get("/recreg/getProducts/WNE/001"))
			   .andExpect(status().isOk())
			   .andReturn()
			   
		String jsonResponse = result.getResponse().getContentAsString()
		log.info(jsonResponse)
	}
	
}
