package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.user;
import com.qa.api.utils.Stringutils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Updateausertest extends BaseTest{

	
	@Test
	public void createausertest() {
		
		user users = new user(null,"Bob","male",Stringutils.getrandomemailid(),"active");
		
		Response responsepostcall = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		
		Assert.assertEquals(responsepostcall.statusCode(), 201);
		JsonPath js = responsepostcall.jsonPath();
		int userid = js.getInt("id");
		System.out.println("The new user id: "+userid);
		
		
        Response responsegetcall = restclients.get(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertEquals(responsegetcall.statusCode(), 200);
		String username = responsegetcall.jsonPath().getString("name");
		System.out.println("The user name is: "+username);
		
		
		user users2 = new user(null,"Bob marley","male",Stringutils.getrandomemailid(),"active");

		Response responseputcall = restclients.put(BASE_URL_GOREST, END_POINT_GOREST+"/"+userid, users2, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		String updatedname = responseputcall.jsonPath().getString("name");
		System.out.println("The user updated name is: "+ updatedname);
	}
		
		
	}
	
	
	
	

