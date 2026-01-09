package com.qa.api.amadeus.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.Configmanager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FlightserchAPI extends BaseTest{
	private String accesstoken;

	@BeforeMethod
	
	public void getaccesstoken() {
		Response response = restclients.post(BASE_URL_AMADEUS_ACCESSTOKEN, END_POINT_AMADEUS_ACCESSTOKEN,
				         Configmanager.get("Amadeus_client_id"), Configmanager.get("Amadeus_client_secret"), 
				         Configmanager.get("Amadeus_grant_type"), ContentType.URLENC);
		
	      assertEquals(response.statusCode(), 200);
	      accesstoken = response.jsonPath().getString("access_token");
		
		System.out.println("The access toekn ==>"+accesstoken);
		Configmanager.set("bearerToken", accesstoken);
		
	}
	
	@Test
	public void getflightdetatiltest() {
		
	Response response = 	restclients.get(BASE_URL_AMADEUS, END_POINT_AMADEUS, ContentType.ANY, AuthType.BEARE_TOKEN, amadeuparams, null);
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
