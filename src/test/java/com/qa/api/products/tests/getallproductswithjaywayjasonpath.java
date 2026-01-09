package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.Jsonpathutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getallproductswithjaywayjasonpath extends BaseTest{

	@Test
public  void getallproductstets(){
		
	Response response = restclients.get(BASE_URL_FAKESTORE, END_POINT_FAKESTORE_PRODCUTS, ContentType.ANY, AuthType.NO_AUTH, null, null);
	
	List<Number>productids = Jsonpathutils.readList(response, "$[*].id");
		System.out.println("productid"+ productids);
		
//		for(Number bot : productids) {
//			System.out.println(bot);
//		}
		
		List<Map<String , Object>>productidandtitle = Jsonpathutils.readListpfMap(response, "$[*].['id','title']");
		System.out.println("productidandtitle"+ productidandtitle);
		
		for(Map<String , Object> bot :productidandtitle ) {
            Number id = (Integer)bot.get("id");
			 System.out.println("id : "+ id);
			 
	         String title = (String)bot.get("title");
				 System.out.println("title : "+ title);
				 
		     System.out.println("*************************************");
		}
		
		
		Double minprice = Jsonpathutils.read(response, "min($[*].price)");
		System.out.println("minimum price : "+ minprice);
		
		Double sumofratinfcount = Jsonpathutils.read(response, "sum($[*].rating.count)");
		System.out.println("umofratinfcounte : "+ sumofratinfcount);
		
		
		List<Map<String , Object>>priceandimage = Jsonpathutils.readListpfMap(response, "$[*].['price','image']");
		for(Map<String , Object> bot : priceandimage) {
			      Number price = (Number)bot.get("price");
			      System.out.println("Price of product : "+ price);
			      String Image = (String)bot.get("image");
			      System.out.println("Link of product image : "+ Image);
			      System.out.println("**********************************");
			 
		}
		
		
		
		
	}
}
	
	
	

