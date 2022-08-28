package restAssuredTraining.apiTraining.libraryApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import restAssuredTraining.helpers.ReUsableMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {

    @Test()
    public void addBook() throws IOException {
        String bodyPath = new String(Files.readAllBytes(Paths.get("src/main/resources/payload/addBook.json")));

        RestAssured.baseURI = "http://216.10.245.166";
        String postAddBookResponse =
                given()
                        .header("Content-Type", "application/json")
                        .body(bodyPath)
                        .log().all()
                        .when()
                        .post("/Library/Addbook.php")
                        .then()
                        .log().all()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .asString();

        JsonPath jsonPathAddBook = ReUsableMethods.rawToJson(postAddBookResponse);
        System.out.println("Add book TEST -> bookId: " + jsonPathAddBook.getString("ID"));
        Assert.assertEquals(jsonPathAddBook.getString("Msg"), "successfully added", "\"Adding a book was not successful\"");
    }

}
