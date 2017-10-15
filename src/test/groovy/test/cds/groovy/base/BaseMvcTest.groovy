package test.cds.groovy.base

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import org.junit.Before
import org.junit.Rule
import org.junit.rules.ErrorCollector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import com.cds.groovy.config.DatabaseSetup

class BaseMvcTest {

	protected MockMvc mockMvc;
	
	protected static HashMap<String, Object> sessAttrs = new HashMap<>(["login": "testUser"])
	
	@Autowired 
	protected WebApplicationContext ctx
	
	@Autowired 
	protected JdbcTemplate template
	
	@Autowired
	protected DatabaseSetup dbSetup
	
	@Rule public ErrorCollector verifier = new ErrorCollector()
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build()
		dbSetup.setupTestDatabases(template)
	}
}
