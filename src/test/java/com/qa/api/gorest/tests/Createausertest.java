package com.qa.api.gorest.tests;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.user;
import com.qa.api.utils.Stringutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Createausertest extends BaseTest{

	
	@Test
	public void createausertest() {
		
        String userjson = "{\"name\": \"Tom anderson\",\r\n"	
                   		+ "        \"email\": \"tom58@wilderman-rohan.example\",\r\n"
	                 	+ "        \"gender\": \"male\",\r\n"
	                	+ "        \"status\": \"active\"}";
		
        Response response = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, userjson, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(response.statusCode(), 201);		
		
		
		
		 
	}
	
	@Test
	public void createauserwithpojotest() {
		
		user users = new user(null,"Tom","male","tom46@apiauto.com","active");
        
        Response response = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(response.statusCode(), 201);		
		
		 
	}
	
	@Test
	public void createauserwithrandomemailtest() {
		
		user users = new user(null,"Tom","male", Stringutils.getrandomemailid(),"active");
        
        Response response = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(response.statusCode(), 201);		
		
		 
	}
	@DataProvider
	public Object[][] getuserdata() { 
		return new Object[][] {
		{"rock","male","active"},
		{"jhon","male","active"},
		{"lisa","female","inactive"},
		};
		}
	
	@Test(dataProvider = "getuserdata")
	public void createauserwithbuildertest(String name,String gender,String status) {
		
      user users = user.builder().name(name)
                      .gender(gender)
                       .status(status)
                         .email(Stringutils.getrandomemailid()).build();
        Response response = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(response.statusCode(), 201);		
		ChainTestListener.log("response: "+ response.getBody().asString());		
		ChainTestListener.log("Status code: "+ response.statusCode());		

	}
	
	
	
	
	
	
	
	
	
	
}
