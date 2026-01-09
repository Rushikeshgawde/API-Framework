package com.qa.api.products.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.products;
import com.qa.api.utils.ObjectMapperutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetallproductswithPOJODesrialization extends BaseTest{

	
	@Test
	public void getallproductstest() {
		
		Response response =restclients.get(BASE_URL_FAKESTORE, END_POINT_FAKESTORE_PRODCUTS, ContentType.ANY, AuthType.NO_AUTH, null, null);
		
		products[] products = ObjectMapperutils.desrealization(response,products[].class);
		for(products bot : products ) {
			System.out.println("product id : "+ bot.getId());
			System.out.println("product title : "+ bot.getTitle());
			System.out.println("product price : "+ bot.getPrice());
			System.out.println("product description : "+ bot.getDescription());
			System.out.println("product category : "+ bot.getCategory());
			System.out.println("product image : "+ bot.getImage());
			System.out.println("product rate : "+ bot.getRating().getRate());
			System.out.println("product count : "+ bot.getRating().getCount());
			System.out.println("-------------------------------");

		}
			
		}
			
			
		
	}
	
	
	
	
	
	
	
	
	
	
	

