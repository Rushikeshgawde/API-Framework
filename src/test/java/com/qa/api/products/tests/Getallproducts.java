package com.qa.api.products.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Getallproducts extends BaseTest {

	@Test
	public void getallproducttest() {
		
		Response response = restclients.get(BASE_URL_FAKESTORE, END_POINT_FAKESTORE_PRODCUTS, ContentType.JSON, AuthType.NO_AUTH, null, null);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	
	
	
	
}
