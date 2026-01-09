package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.Configmanager;
import com.qa.api.pojo.user;
import com.qa.api.utils.ObjectMapperutils;
import com.qa.api.utils.Stringutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateauserwithDesrialization extends BaseTest{

	@BeforeClass
	
	public void getaccesstoken() {
		Configmanager.set("bearerToken", "a7919c17af4c89473841655d6b0183483a08127a9e470c7e16481062f2b880c4");
	}
	
	@Test
	public void getauserTest() {
		//1. Create a user
		user newuser = new user(null,"Tom","male",Stringutils.getrandomemailid(),"active");
        Response postresponse =    restclients.post(BASE_URL_GOREST, END_POINT_GOREST, newuser, ContentType.JSON, AuthType.BEARE_TOKEN, null, null);		
        Integer newuserid =  postresponse.jsonPath().getInt("id");
        
        Assert.assertEquals(postresponse.jsonPath().getString("name"), "Tom");
	            	
		//2.Get the same user
        
       Response getresponse =  restclients.get(BASE_URL_GOREST, END_POINT_GOREST+"/"+newuserid, ContentType.ANY, AuthType.BEARE_TOKEN, null, null);
       Assert.assertEquals(getresponse.jsonPath().getInt("id"),newuserid);
       
       //3.Used objectMapper for Deseralization
       user userresponse = ObjectMapperutils.desrealization(getresponse, user.class);
       Assert.assertEquals(userresponse.getName(), newuser.getName());
       Assert.assertEquals(userresponse.getStatus(), newuser.getStatus());
       Assert.assertEquals(userresponse.getGender(), newuser.getGender());
       Assert.assertEquals(userresponse.getEmail(), newuser.getEmail());
       Assert.assertEquals(userresponse.getId(), newuserid);

       
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

