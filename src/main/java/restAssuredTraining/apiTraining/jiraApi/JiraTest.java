package restAssuredTraining.apiTraining.jiraApi;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

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
        String commentDetails =
                given()
                        .log().all()
                        .filter(sessionFilter)
                        .header("Content-Type", "application/json")
                        .pathParam("projectId", "10029")
                        .when()
                        .body("{\n" +
                                "    \"body\": \"Comment dat prin RestAssured 4\",\n" +
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
                        .extract()
                        .response()
                        .asString();
        JsonPath jpCommentDetails = new JsonPath(commentDetails);
        String commentId = jpCommentDetails.get("id");

        System.out.println("--------------------------------------------------------------------------------------");

        // Add attachment
        given()
                .log().all()
                .filter(sessionFilter)
                .header("X-Atlassian-Token", "no-check")
                .header("Content-Type", "multipart/form-data")
                .pathParam("projectId", "10029")
                .multiPart("file", new File("src/main/resources/payload/jiraAttachment.txt"))
                .when()
                .post("rest/api/2/issue/{projectId}/attachments")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
        ;

        System.out.println("--------------------------------------------------------------------------------------");

        // Get issue
        String issueDetails =
                given()
                        .log().all()
                        .filter(sessionFilter)
                        .pathParam("projectId", "10029")
                        .queryParam("fields", "comment")
                        .when()
                        .get("rest/api/2/issue/{projectId}")
                        .then()
                        .log().all()
                        .extract()
                        .response()
                        .asString();

        JsonPath jpIssueDetails = new JsonPath(issueDetails);
        int commentsSize = jpIssueDetails.getInt("fields.comment.comments.size()");
        System.out.println("Comments ids extracted from get issue request: ");
        for (int i = 0; i < commentsSize; i++) {
            String commentIdIssue = jpIssueDetails.get("fields.comment.comments[" + i + "].id").toString();
            if (commentIdIssue.equals(commentId)) {
                System.out.println(commentIdIssue + " -> OK, found");
            } else {
                System.out.println(commentIdIssue);
            }
        }

        System.out.println();
        System.out.println("Newly created comment id: " + commentId);
    }
}
