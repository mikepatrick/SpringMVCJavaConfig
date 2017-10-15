package com.cds.groovy.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.servlet.ModelAndView

import com.cds.groovy.dao.UsersDaoIF

//@CompileStatic
@Controller
@Log4j2
@CompileStatic
//@SessionAttributes("login")
class WelcomeController {

	@Autowired
	HttpServletRequest request
	
	@Autowired UsersDaoIF userDao;
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(@RequestParam(required=false)  String viewType)
	{
		log.debug("WelcomeController.welcome()")
		
		if ("groovy".equals(viewType)) {
			return new ModelAndView("groovyWelcome")
		}
		
		return new ModelAndView("welcome")
	}
	
	//@ModelAttribute("login")
	@RequestMapping("/Dashboard")
	public ModelAndView dashboard(@RequestParam String login) {
		log.debug("WelcomeController.dashboard()")
		
		request.getSession().setAttribute("login", login)
		return new ModelAndView("dashboard").addObject("users", userDao.getUsers())
											.addObject("name", login)
		
	}
}
