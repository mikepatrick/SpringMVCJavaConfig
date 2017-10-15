package com.cds.groovy.controllers;

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import com.cds.groovy.dao.UsersDaoIF
import com.cds.groovy.domain.JSONResponse
import com.cds.groovy.domain.GroovyUser

@Log4j2
@CompileStatic
@RestController
class CdsRestController {

	@Autowired UsersDaoIF userDao
	
	@RequestMapping(value="/ajax/{userId}/getUser", method=RequestMethod.GET)
	public JSONResponse getUser(@PathVariable Integer userId) {
		//return userDao.findUser(userId)	
		
		
		return new JSONResponse( [ "success": true, 
								   "message": "Here is user ${userId}", 
								   "payload" : [ "user": userDao.findUser(userId) ] as Map<String, Object>] )
	}
	
	@RequestMapping(value="/ajax/postUser", method=RequestMethod.POST)
	public String postUser(@RequestBody GroovyUser user) {
		log.info("Marshalled user from JSON request: ${user} ");
		
		return new JSONResponse( [ "success": true, 
								   "message": "Here is your user", 
								   "payload": [ "user": user ] as Map<String, Object> ] )
		
	}
	
}
