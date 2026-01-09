package com.qa.api.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.exceptions.APIexceptions;

import io.restassured.response.Response;

public class ObjectMapperutils {

	private static ObjectMapper objectmapper = new ObjectMapper();
	
	public static <T>T desrealization(Response response,Class<T>targetclass) {
		
		try {
		return objectmapper.readValue(response.getBody().asString(),targetclass);
		}
		catch(Exception e) {
			throw new APIexceptions("Desrialization is failed......"+targetclass.getName());
		}
	}
	
	
}
