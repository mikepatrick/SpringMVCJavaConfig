package test.cds.groovy.controllers;

import static org.junit.Assert.*
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@Log4j2
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=TestApplicationConfig.class)
@CompileStatic
public class GlobalCustomerControllerTests extends BaseMvcTest {

	@Test
	public void testGetWallet() {
		MvcResult result =
		mockMvc.perform(get("/gc/wallet/001/WNE/50315"))
			   .andExpect(status().isOk())
			   .andReturn();
			   
		String response = result.getResponse().getContentAsString();
		log.info(response)
	}

}
