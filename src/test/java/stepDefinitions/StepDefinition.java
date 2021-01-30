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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import files.ReusableMethods;

public class StepDefinition {
	List<Map<String, Object>> responseUser = null;
	int userId;

	@Given("GetTitle Api headers and {string} query param")

	public void get_title_api_headers_and_query_param(String expectedTitle) {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		String response = given().queryParam("title", expectedTitle)
				.header("Content-Type", "application/json").when().get("posts").then().assertThat().statusCode(200)
				.body("[0].title", equalTo(expectedTitle)).header("Content-Type", "application/json; charset=utf-8")
				.extract().response().asString();

		JsonPath js = ReusableMethods.rawToJson(response);
		String actualtTitle = js.get("[0].title");
		Assert.assertEquals(expectedTitle, actualtTitle);
		userId = js.getInt("[0].userId");
	}

	@When("user calls GetUserId Api with get http request")
	public void user_calls_get_UserId_api_with_http_request() {

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		responseUser = given().queryParam("userId", userId).header("Content-Type", "application/json")
				.when().get("posts").then().assertThat().statusCode(200).body("[0].userId", equalTo(userId))
				.header("Content-Type", "application/json; charset=utf-8").extract().response()
				.as(new TypeRef<List<Map<String, Object>>>() {
				});

		for (Map<String, Object> posts : responseUser) {

			System.out.println(posts.get("title"));

		}
		System.out.println("Total posts from this author are : " + responseUser.size());
	}

	@Then("all the posts returned have the same author")
	public void all_the_posts_returned_have_the_same_author() {
		int expectedUserId = userId;
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		responseUser = given().queryParam("userId", userId).header("Content-Type", "application/json")
				.when().get("posts").then().assertThat().statusCode(200).body("[0].userId", equalTo(userId))
				.header("Content-Type", "application/json; charset=utf-8").extract().response()
				.as(new TypeRef<List<Map<String, Object>>>() {
				});

		for (Map<String, Object> posts : responseUser) {
			Assert.assertEquals(expectedUserId, posts.get("userId"));

		}
	}

}
