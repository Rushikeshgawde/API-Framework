package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Getausertest extends BaseTest{

	
	@Test
	
	public void getallusertest() {
		
       Response response = restclients.get(BASE_URL_GOREST, END_POINT_GOREST, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
       Assert.assertEquals(response.statusCode(), 200);
       Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
    @Test
	
	public void getalluserwithqueryparamtest() {
		
	   Map<String,String>queryparam = new HashMap<String,String>();
	   queryparam.put("name", "Tom");
	   queryparam.put("status", "active");

       Response response = restclients.get(BASE_URL_GOREST, END_POINT_GOREST, ContentType.JSON, AuthType.BEARE_TOKEN, queryparam, null);
       Assert.assertEquals(response.statusCode(), 200);
       Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
    @Test
	
	public void getasingleuserwithqueryparamtest() {
		
	  String userid = "8115331";

       Response response = restclients.get(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
       Assert.assertEquals(response.statusCode(), 200);
       Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	
	
	
	
	
	
	
}
