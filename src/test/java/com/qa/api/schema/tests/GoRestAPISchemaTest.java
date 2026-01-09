package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.Configmanager;
import com.qa.api.pojo.user;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.Stringutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestAPISchemaTest extends BaseTest{
	
	@BeforeClass
	public void getaccesstoken() {
		
		Configmanager.set("bearerToken", "a7919c17af4c89473841655d6b0183483a08127a9e470c7e16481062f2b880c4");
	}
		
	@Test
	public void getalluserschematest() {
		
		Response response = restclients.get(BASE_URL_GOREST, END_POINT_GOREST, ContentType.ANY, AuthType.BEARE_TOKEN, null, null);
		
		Assert.assertTrue(SchemaValidator.validationofschema(response, "getalluserschena.json"));
		
	}
	
	@Test
	public void createauserschematest() {
		
		user users = new user(null,"Rock","male",Stringutils.getrandomemailid(),"active");
		
		
		Response response = restclients.post(BASE_URL_GOREST, END_POINT_GOREST, users, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);
		Assert.assertTrue(SchemaValidator.validationofschema(response, "createuserschema.json"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
