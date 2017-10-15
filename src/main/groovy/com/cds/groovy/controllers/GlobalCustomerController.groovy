package com.cds.groovy.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.cds.groovy.domain.JSONResponse
import com.cds.groovy.domain.global.customer.GlobalCustomerResponse
import com.cds.groovy.domain.global.customer.Wallet
import com.cds.groovy.service.GlobalCustomerLookupService

@Log4j2
@RestController
class GlobalCustomerController {

	@Autowired GlobalCustomerLookupService service
	
	@RequestMapping("/gc/wallet/{acct}/{mag}/{zip}")
	public JSONResponse getCustomerWallet(@PathVariable String acct, @PathVariable String mag, @PathVariable String zip) {
		
		GlobalCustomerResponse response = service.doLookupByAcct(acct, mag, zip)
		
		Wallet wallet = response.globalcustomer.wallet
		
		JSONResponse json = new JSONResponse(["success": true,
											  "message": "Wallet lookup successful",
											  "payload": ["wallet" : wallet ]
											 ])
		log.debug json
		return json
	}
}
