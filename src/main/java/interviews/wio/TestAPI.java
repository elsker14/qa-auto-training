package interviews.wio;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestAPI {
    /*
        POST /api/portfolio/performance

        Request:
        Header jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
        Header userId: 3fa85f64-5717-4562-b3fc-2c963f66afa6
        Header portfolioId: 4fa85f64-5712-1234-b3fc-2c963f66aap1

        Response:
        {
        "performance": {
          "absolute": {
           "value": "123.456",
           "currency": "USD"
          },
          "relative": {
           "value": "0.05"
          }
        }
     */

    @Test(description = "Testing POST API Request")
    public void testAPI() {
        baseURI = "https://www.something.com";
        String postResponse =
                given()
                        .log().all()
                        .header("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
                        .header("userId", "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                        .header("portfolioId", "4fa85f64-5712-1234-b3fc-2c963f66aap1")
                        .when()
                        .post("/api/portfolio/performance")
                        .then()
                        .log().all()
                        .extract()
                        .response()
                        .asString();
        JsonPath jsonPath = new JsonPath(postResponse);

        Assert.assertEquals(jsonPath.getInt("performance.absolute.value"), 123456,
                "The Absolute Value for Performance is incorrect, found: " + jsonPath.getInt("performance.absolute.value") + ", expected: " + 123456);
        Assert.assertTrue(jsonPath.getString("performance.absolute.value").equals("USD"),
                "The Absolute Currency for Performance is incorrect, found: " + jsonPath.getString("performance.absolute.value") + ", expected: USD");
        Assert.assertEquals(jsonPath.getFloat("performance.relative.value"), 0.05,
                "The Absolute Value for Relative is incorrect, found: " + jsonPath.getInt("performance.relative.value") + ", expected: " + 0.05);
    }
}
