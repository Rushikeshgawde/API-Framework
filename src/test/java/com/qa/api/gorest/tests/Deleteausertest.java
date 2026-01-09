package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.user;
import com.qa.api.utils.Stringutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Deleteausertest extends BaseTest{

	@Test
	public void deleteusertest() {
		
		user users = new user(null,"Tom","male",Stringutils.getrandomemailid(),"active");

		Response responsepostcall = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		int userid = responsepostcall.jsonPath().getInt("id");
		System.out.println("The new user id: "+userid);
		Assert.assertEquals(responsepostcall.statusCode(), 201);
		
		
		Response responsegetcall = restclients.get(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		String username = responsegetcall.jsonPath().getString("name");
		System.out.println("The user name is: "+username);
		Assert.assertEquals(responsegetcall.statusCode(), 200);
		
		
		Response responsedeletecall = restclients.delete(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid,ContentType.JSON, AuthType.BEARE_TOKEN);
		Assert.assertEquals(responsedeletecall.statusCode(), 204);
		
		Response responsegetcall1 = restclients.get(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(responsegetcall1.statusCode(), 404);
		Assert.assertEquals(responsegetcall1.jsonPath().getString("message"), "Resource not found");
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
