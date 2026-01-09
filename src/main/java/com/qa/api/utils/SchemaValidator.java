package com.qa.api.utils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SchemaValidator {

	
	public static boolean validationofschema(Response response, String schemafilename) {
		
		try {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(schemafilename));
		System.out.println("The schema validation is paassed for ==>"+schemafilename);
		return true;
		}
		catch(Exception e){
			System.out.println("The schema validation is failed for ==>"+schemafilename);
			return false;

		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
