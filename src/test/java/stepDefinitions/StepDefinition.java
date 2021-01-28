package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static org.junit.Assert.*;

public class StepDefinition {
	@Given("GetTitle Api headers and query param")
	public void get_title_api_headers_and_query_param() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		given().log().all().queryParam("title", "rem alias distinctio quo quis")
		.header("Content-Type", "application/json").when().get("posts").then().log().all().assertThat().statusCode(200);
	    
	}

//	@When("user calls GetTitle Api with {string} http request")
//	public void user_calls_get_title_api_with_http_request(String string) {
//		
//	    throw new io.cucumber.java.PendingException();
//	}
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
