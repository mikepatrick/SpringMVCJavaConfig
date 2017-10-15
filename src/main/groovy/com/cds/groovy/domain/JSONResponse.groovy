package com.cds.groovy.domain

import groovy.transform.CompileStatic;
import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;

@CompileStatic
@EqualsAndHashCode
@ToString
class JSONResponse {

	boolean success
	String message
	Map<String, Object> payload
}
