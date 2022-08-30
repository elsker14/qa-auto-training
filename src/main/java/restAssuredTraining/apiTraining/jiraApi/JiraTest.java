package restAssuredTraining.apiTraining.jiraApi;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class JiraTest {

    public void jiraTest() {

        // Log in and get cookie session id
        // To pass session id to other request just store it in the SessionFilter class object
        // And pass it to all other requests with .filter, similar to la auth request
        SessionFilter sessionFilter = new SessionFilter();
        String response =
                given()
                        .log().all()
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "    \"username\": \"iancujianu98\",\n" +
                                "    \"password\": \"1233\"\n" +
                                "}")
                        .filter(sessionFilter)
                        .when()
                        .post("rest/auth/1/session")
                        .then()
                        .log().all()
                        .extract()
                        .response()
                        .asString();

        System.out.println("--------------------------------------------------------------------------------------");

        // Add comment
        RestAssured.baseURI = "http://localhost:8080/";
        given()
                .log().all()
                .filter(sessionFilter)
                .header("Content-Type", "application/json")
                .pathParam("projectId", "10029")
                .when()
                .body("{\n" +
                        "    \"body\": \"Comment dat prin RestAssured 1\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .post("rest/api/2/issue/{projectId}/comment")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
        ;
    }
}
