package BasicauthTest;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class basicauthtest extends BaseTest{

	@Test
	public void basicauthenticationtest() {
		
       Response response = restclients.get(BASE_URL_HEROKUAPP, END_POINT_HEROKUAPP_BASIC_AUTH, ContentType.JSON, AuthType.BASIC_AUTH, null, null);		
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.getBody().asString().contains("Congratulations! You must have the proper credentials."));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
