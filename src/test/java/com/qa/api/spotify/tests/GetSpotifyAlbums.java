package com.qa.api.spotify.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.Configmanager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetSpotifyAlbums extends BaseTest{
		private String accesstoken;
		@BeforeTest
		public void getaccesstoken() {
			
	Response response =restclients.post(BASE_URL_SPOTIFY_ACCESSTOKEN, END_POINT_SPOTIFY_ACCESSTOKEN, 
			Configmanager.get("client_id").trim(), Configmanager.get("client_secret").trim(), Configmanager.get("grant_type").trim(), ContentType.URLENC);
			
	      accesstoken =  response.jsonPath().getString("access_token");
	      System.out.println("The access token ==>"+accesstoken);
	      Configmanager.set("bearerToken", accesstoken);
			
			
		}
		@Test
		public void getalbumstest() {
			
			Response response = restclients.get("https://api.spotify.com", "/v1/albums"+"/4aawyAB9vmqN3uQ7FjRGTy",ContentType.ANY, AuthType.BEARE_TOKEN, null, null);
			Assert.assertEquals(response.statusCode(), 200);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

