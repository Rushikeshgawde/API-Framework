package com.qa.api.base;

import java.util.Map;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.clients.Restclients;
import com.qa.api.manager.Configmanager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected final static Map<String,String>amadeuparams = Map.of("airline","ib");
	protected  static String BASE_URL_GOREST;
	
	//***************Base URL***************
//	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	
	protected final static String BASE_URL_FAKESTORE = "https://fakestoreapi.com";
	
	protected final static String BASE_URL_HEROKUAPP = "https://the-internet.herokuapp.com";
	
	protected final static String BASE_URL_SPOTIFY_ACCESSTOKEN = "https://accounts.spotify.com";
     
    protected final static String BASE_URL_AMADEUS_ACCESSTOKEN = "https://test.api.amadeus.com";
    
    protected final static String BASE_URL_AMADEUS = "https://test.api.amadeus.com";

	

	//***************END Points***************
		protected final static String END_POINT_GOREST = "/public/v2/users";
		protected final static String END_POINT_FAKESTORE_PRODCUTS = "/products";
		protected final static String END_POINT_HEROKUAPP_BASIC_AUTH = "/basic_auth";
		protected final static String END_POINT_SPOTIFY_ACCESSTOKEN = "/api/token";
		protected final static String END_POINT_AMADEUS_ACCESSTOKEN = "/v1/security/oauth2/token";
		protected final static String END_POINT_AMADEUS = "/v2/reference-data/urls/checkin-links";
		


	
	
	protected Restclients restclients;
	
	
	@BeforeTest
	public void setup() {
		
		restclients = new Restclients();
		
	}
	
	@BeforeSuite
	public void intialsetup() {
		RestAssured.filters(new AllureRestAssured());
		
		BASE_URL_GOREST = Configmanager.get("baseurl.gorest");	
	}
	
}
