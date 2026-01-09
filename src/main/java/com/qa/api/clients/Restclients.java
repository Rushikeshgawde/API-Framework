package com.qa.api.clients;

import static io.restassured.RestAssured.expect;


import java.util.Base64;
import java.util.Map;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIexceptions;
import com.qa.api.manager.Configmanager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
public class Restclients {

	private ResponseSpecification response200or404 = expect().statusCode(anyOf(equalTo(200),(equalTo(404))));
	private ResponseSpecification response200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification response204 = expect().statusCode(204);

	/**
	 * 
	 * @param baseURL
	 * @param contenttype
	 * @param authtype
	 * @return
	 */
	private RequestSpecification setupRequest(String baseURL,ContentType contenttype,AuthType authtype) {
		
		RequestSpecification request =  RestAssured.given().log().all()
                    .baseUri(baseURL)
                    .contentType(contenttype)
                    .accept(contenttype);
		switch(authtype) {
		case BEARE_TOKEN:
			request.header("Authorization" , "Bearer "+ Configmanager.get("bearerToken"));
			break;
		case BASIC_AUTH:
			request.header("Authorization" , "Basic "+ basicauth());
			break;
		case API_KEY:
			request.header("x-api-key" , Configmanager.get("apikey"));
			break;
		case NO_AUTH:
            System.out.println("No authorization is required......");
            break;
        default:
             System.out.println("This type of auth is not supported....please provide correct auth type.... ");
             throw new APIexceptions("==========INVALID AUTHTYPE===========");
            
		}
		
		return request;
		
	}
	
	private String basicauth() {
		
		String inputs = Configmanager.get("username").trim()+":"+Configmanager.get("password").trim();
		
		return Base64.getEncoder().encodeToString(inputs.getBytes());
		
		
	}
	/**
	 * 
	 * @param request
	 * @param queryparam
	 * @param pathparam
	 */
	private void applyparams(RequestSpecification request,Map<String , String>queryparam, Map<String , String>pathparam) {
		
		if(queryparam!=null) {
			request.queryParams(queryparam);
		}
		if(pathparam!=null) {
			request.pathParams(pathparam);
		}
	}
	
	
	/**
	 * 
	 * @param baseURL
	 * @param endpoint
	 * @param contenttype
	 * @param authtype
	 * @param queryparam
	 * @param pathparam
	 * @return the GET API response......
	 */
	public Response get(String baseURL, String endpoint, ContentType contenttype, AuthType authtype,
			        Map<String , String>queryparam, Map<String , String>pathparam) {
		
		
		RequestSpecification request = setupRequest(baseURL,contenttype,authtype);
		
		applyparams(request,queryparam ,pathparam);
		
		Response response = request.get(endpoint)
		   .then().spec(response200or404).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	/**
	 * This method is use to create a user with post call---accept any type of body exclude the file 
	 * @param <T>
	 * @param baseURL
	 * @param endpoint
	 * @param body
	 * @param contenttype
	 * @param authtype
	 * @param queryparam
	 * @param pathparam
	 * @return
	 */
	public <T>Response post(String baseURL, String endpoint, T body, ContentType contenttype, AuthType authtype, Map<String , String>queryparam, Map<String , String>pathparam) {
		
		RequestSpecification request = setupRequest(baseURL,contenttype,authtype);
		
		applyparams(request,queryparam ,pathparam);
		ChainTestListener.log("Fullurl: "+ baseURL+endpoint);
		Response response =request.body(body).post(endpoint).then().spec(response200or201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	
public Response post(String baseURL, String endpoint, String client_id, String client_secret,String grant_type,ContentType contenttype) {
		
		Response response = RestAssured.given()
		           .baseUri(baseURL)
		           .contentType(contenttype)
		           .formParam("grant_type", grant_type)
		           .formParam("client_secret", client_secret)
		           .formParam("client_id", client_id)
		           
		           .when()
		            .post(baseURL+endpoint);
		            
		           return response;
		            
		           
	}

			
	/**
	 * This method is used to update the user with put call		
	 * @param <T>
	 * @param baseURL
	 * @param endpoint
	 * @param body
	 * @param contenttype
	 * @param authtype
	 * @param queryparam
	 * @param pathparam
	 * @return
	 */
   public <T>Response put(String baseURL, String endpoint, T body, ContentType contenttype, AuthType authtype, Map<String , String>queryparam, Map<String , String>pathparam) {
		
		RequestSpecification request = setupRequest(baseURL,contenttype,authtype);
		
		applyparams(request,queryparam ,pathparam);
		Response response =request.body(body).put(endpoint).then().spec(response200or201).extract().response();
		response.prettyPrint();
		return response;
	}
   
   /**
    * This method is used to update the user with patch call
    * @param <T>
    * @param baseURL
    * @param endpoint
    * @param body
    * @param contenttype
    * @param authtype
    * @param queryparam
    * @param pathparam
    * @return
    */
   public <T>Response patch(String baseURL, String endpoint, T body, ContentType contenttype, AuthType authtype, Map<String , String>queryparam, Map<String , String>pathparam) {
		
		RequestSpecification request = setupRequest(baseURL,contenttype,authtype);
		
		applyparams(request,queryparam ,pathparam);
		Response response =request.body(body).patch(endpoint).then().spec(response200or201).extract().response();
		response.prettyPrint();
		return response;
	}
   
   /**
    * 
    * @param baseURL
    * @param endpoint
    * @param contenttype
    * @param authtype
    * @return
    */
   
   public Response delete(String baseURL, String endpoint,  ContentType contenttype, AuthType authtype) {
		
		RequestSpecification request = setupRequest(baseURL,contenttype,authtype);
		
		Response response =request.delete(endpoint).then().spec(response204).extract().response();
		response.prettyPrint();
		return response;
	}
	
	
	
	
	
}
