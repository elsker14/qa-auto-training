package restAssuredTraining.googleMapsApiReplica;

/*
    The 3 principles of API testing: given, when, there
    // given - all input details
    // when  - submit the API: resource and http request type
    // there - validate the response
*/

import io.restassured.RestAssured;
import restAssuredTraining.helpers.Payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstRequest {
    public static void main(String[] args) {
        // Validate if Add Place API is working as expected

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given() // EVERYTHING WE PUT IN HERE IS FROM REQUEST: header, body, etc
                .log().all() // log request and all info regarding it
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when()
                .post("maps/api/place/add/json")
                .then() // EVERYTHING WE PUT IN HERE IS FROM RESPONSE: header, body, etc
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.41 (Ubuntu)");
    }
}
