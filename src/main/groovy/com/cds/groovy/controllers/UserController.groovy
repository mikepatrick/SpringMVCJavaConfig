package com.cds.groovy.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.servlet.ModelAndView

import com.cds.groovy.dao.UsersDaoIF

@Controller
@Log4j2
@CompileStatic
class UserController {
	
	@Autowired UsersDaoIF userDao;
	
	@RequestMapping("/addUser")
	public ModelAndView addUser(@RequestParam String newUserName,
								@RequestParam String newUserEmail,
								@SessionAttribute("login") String name) {
								
		int success = userDao.insertUser(newUserName, newUserEmail)
		if (success == 1) {
			return new ModelAndView("dashboard").addObject("users", userDao.getUsers())
												.addObject("name", name);
		} else {
			return new ModelAndView("welcome")
		}
		
	}
								
	@RequestMapping("deleteAllUsers")
	public ModelAndView deleteUsers(@SessionAttribute("login") String name) {
		userDao.deleteUsers()
		return new ModelAndView("dashboard").addObject("users", userDao.getUsers())
											.addObject("name", name)
		
	}
	
	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(@SessionAttribute("login") String name,
								   @RequestParam Integer userId) {
								   
		userDao.deleteUser(userId)
		return new ModelAndView("dashboard").addObject("users", userDao.getUsers())
											.addObject("name", name)
	}
}
