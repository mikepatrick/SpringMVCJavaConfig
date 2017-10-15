package com.cds.groovy.controllers

import groovy.util.logging.Log4j2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.cds.groovy.domain.JSONResponse
import com.cds.groovy.domain.recreg.Email
import com.cds.groovy.domain.recreg.Product
import com.cds.groovy.domain.recreg.RecRegResponse
import com.cds.groovy.domain.recreg.User
import com.cds.groovy.service.RecRegLookupService

@RestController
@Log4j2
class RecRegConroller {

	@Autowired
	RecRegLookupService service
	
	@RequestMapping("/recreg/passthru")
	public JSONResponse recRegPassThru(@RequestParam String acct, @RequestParam String mag) {
		RecRegResponse response = service.doLookupByAcct("AccountCode", "SERV", acct, mag)
		
		JSONResponse json = new JSONResponse( ["success": true,
											   "message": "RecReg lookup success",
											   "payload": [ "result": response ]
											  ] )
		
		
	}
	
	@RequestMapping("/recreg/hasEmail/{magcode}/{acctnbr}")
	public JSONResponse hasEamilRecords(@PathVariable String magcode, @PathVariable String acctnbr) {
		
		RecRegResponse response = service.doLookupByAcct("AccountCode", "SERV", acctnbr, magcode)
		
		List<User> usersWithEmails = getUsersWithEmails(response)
		
		JSONResponse json = new JSONResponse( ["success": true, 
				    						   "message": "Email record existence check success", 
											   "payload": [ "result": usersWithEmails.size() > 0]
								 			  ] )
		log.debug json
		return json
	}
	
	@RequestMapping("/recreg/getEmails/{magcode}/{acctnbr}")
	public JSONResponse getEmailRecords(@PathVariable String magcode, @PathVariable String acctnbr) {
		RecRegResponse response = service.doLookupByAcct("AccountCode", "SERV", acctnbr, magcode)
		
		List<User> usersWithEmails = getUsersWithEmails(response)
																
		List<Email> emails = usersWithEmails?.collect { it.emailList.each {it} }
		
		JSONResponse json =  new JSONResponse( [
											    "success": true, 
												"message": "Email record inquiry success", 
												"payload": [ "emailRecords": emails]
											   ] )
		log.debug json
		return json
	}

	
	@RequestMapping("/recreg/getAddresses/{magcode}/{acctnbr}")
	public JSONResponse getAddresses(@PathVariable String magcode, @PathVariable String acctnbr) {
		RecRegResponse response = service.doLookupByAcct("AccountCode", "SERV", acctnbr, magcode)
		
		List<User> usersWithAddresses = getUsersWithAddresses(response)
		List<Product> addresses = usersWithAddresses.collect { it.addressList.each {it}  }
		log.info(addresses)
		
		JSONResponse json = new JSONResponse( ["success": true,
											   "message": "Address record inquiry success",
											   "payload": [ "addresses:": addresses]
		   									  ]	)
		log.debug json
		return json
	}
	
	@RequestMapping("/recreg/getProducts/{magcode}/{acctnbr}")
	public JSONResponse getProducts(@PathVariable String magcode, @PathVariable String acctnbr) {
		RecRegResponse response = service.doLookupByAcct("AccountCode", "SERV", acctnbr, magcode)
		
		List<User> usersWithProducts = getUsersWithProducts(response)
		List<Product> products = usersWithProducts.collect { it.productList.each {it}  }
		log.info(products)
		
		JSONResponse json =  new JSONResponse( ["success": true,
										    	"message": "Product record inquiry success",
												"payload": [ "products:": products]
		   									   ] )
		log.debug json
		return json
	}
	
	public List<User> getUsersWithProducts(RecRegResponse response) {
		return response.userList?.findAll { it.productList != null && !it.productList.isEmpty() } 
	}
	
	public List<User> getUsersWithAddresses(RecRegResponse response) {
		return response.userList?.findAll { it.addressList != null && !it.addressList.isEmpty() }
	}
	
	public List<User> getUsersWithEmails(RecRegResponse response) {
		return response.userList?.findAll { it.emailList != null && !it.emailList.isEmpty() }
	}
}
