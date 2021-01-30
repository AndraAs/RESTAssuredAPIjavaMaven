package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import files.ReusableMethods;

public class StepDefinition {
	
	int userId;
	@Given("GetTitle Api headers and query param")
	
	public void get_title_api_headers_and_query_param() {
		String expectedTitle = "rem alias distinctio quo quis";
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		 String response =given().log().all().queryParam("title", expectedTitle)
		.header("Content-Type", "application/json").when().get("posts").then().assertThat().statusCode(200).body("[0].title", equalTo(expectedTitle))
	    .header("Content-Type", "application/json; charset=utf-8").extract().response().asString();
		
		
		JsonPath js =ReusableMethods.rawToJson(response);
		String actualtTitle =js.get("[0].title");
		Assert.assertEquals(expectedTitle, actualtTitle);
		 userId = js.getInt("[0].userId");
	}

	@When("user calls GetUserId Api with get http request")
	public void user_calls_get_UserId_api_with_http_request() {
		List<Map<String,Object>> responseUser = null;
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		  responseUser =given().log().all().queryParam("userId", userId).header("Content-Type", "application/json").when().get("posts").then().assertThat().statusCode(200).body("[0].userId", equalTo(userId))
		    .header("Content-Type", "application/json; charset=utf-8").extract().response().as(new TypeRef<List<Map<String,Object>>>() {});

	    for(Map<String,Object> posts : responseUser)
		{
	    	
			System.out.println(posts.get("title"));
		}
	    System.out.println("Total posts from this author are : "+ responseUser.size());
	}
//
//	@Then("the Api call is succesfull with {string} {string}")
//	public void the_api_call_is_succesfull_with(String string, String string2) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@And("title in response body is {string}")
//	public void title_in_response_body_is(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}


}
