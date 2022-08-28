package restAssured;

import io.restassured.RestAssured;
import restAssured.files.Payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChainedE2ERequests {
    public static void main(String[] args) {
        // Validate if Add Place API is working as expected
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given() // EVERYTHING WE PUT IN HERE IS FROM REQUEST: header, body, etc
                .log().all()
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

        // Add place -> Update Place with New Address -> Get Place to validate if New Addressis present in response
    }
}
