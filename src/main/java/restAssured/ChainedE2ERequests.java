package restAssured;

import io.restassured.RestAssured;
import org.testng.Assert;
import restAssured.helpers.Payload;
import restAssured.helpers.ReUsableMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChainedE2ERequests {
    public static void main(String[] args) {
        /* Add place -> Update Place with New Address -> Get Place to validate if New Addressis present in response */

        // Add Place POST Request
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postAddPlaceResponse =
                given() // EVERYTHING WE PUT IN HERE IS FROM REQUEST: header, body, etc
                        .queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(Payload.addPlace())
                        .when()
                        .post("maps/api/place/add/json")
                        .then() // EVERYTHING WE PUT IN HERE IS FROM RESPONSE: header, body, etc
                        .assertThat()
                        .statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("server", "Apache/2.4.41 (Ubuntu)")
                        .extract()
                        .response()
                        .asString();
        String placeId = ReUsableMethods.rawToJson(postAddPlaceResponse).getString("place_id");

        // Update Place with New Address using the placeId variable
        String newAddress = "La tactu acasa";
        given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.updatePlace(placeId, newAddress))
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // Get Place to validate if New Addressis present in response
        String getPlaceResponse = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("address", equalTo(newAddress))
                .extract()
                .response()
                .asString();

        String actualAdress = ReUsableMethods.rawToJson(getPlaceResponse).getString("address");
        Assert.assertEquals(actualAdress, newAddress,
                "Actual address " + actualAdress + " is different from introduced address " + newAddress);
    }
}
