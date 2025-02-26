package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefination extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
         resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
         if(method.equalsIgnoreCase("POST")){
             response =res.when().post(resourceAPI.getResource());
         } else if (method.equalsIgnoreCase("GET")) {
             response =res.when().get(resourceAPI.getResource());
         }

//                then().spec(resspec).extract().response();
    }

    @Then("The API call is successful with status code {int}")
    public void thes_api_call_is_successful_with_status_code(Integer int1) {
        assertEquals(200, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        assertEquals(Expectedvalue,getJsonPath(response,keyValue));
    }

    @Then("Verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using_get_place_api(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response,"name");
        assertEquals(actualName,expectedName);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
     res = given().spec(requestSpecification()).body(deletePlacePayLoad(place_id));
    }

}