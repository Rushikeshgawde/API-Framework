package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class Jsonpathutils {

	
	
	private static String getresponseasstring(Response response) {
        return response.getBody().asString();  		
	}
	
	public static <T>T read(Response response , String jsonpath) {
		 ReadContext ctx = JsonPath.parse(getresponseasstring(response));	
		return  ctx.read(jsonpath);
	}
	
	public static <T>List<T> readList(Response response , String jsonpath) {
		 ReadContext ctx = JsonPath.parse(getresponseasstring(response));	
		return  ctx.read(jsonpath);
	}
	
	public static <T>List<Map<String,T>> readListpfMap(Response response , String jsonpath) {
		 ReadContext ctx = JsonPath.parse(getresponseasstring(response));	
		return  ctx.read(jsonpath);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
