package restAssuredTraining.apiTraining.libraryApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import restAssuredTraining.helpers.Payload;
import restAssuredTraining.helpers.ReUsableMethods;

import static io.restassured.RestAssured.given;

public class DynamicJason {

    @Test
    public void addBook() {
        String bookName = "In lumea lui Jijel";
        String isbn = "6969";
        String aisle = "9696";
        String authorName = "Iancu Jianu";

        RestAssured.baseURI = "http://216.10.245.166";
        String postAddBookResponse =
                given()
                        .header("Content-Type", "application/json")
                        .body(Payload.addBook(bookName, isbn, aisle, authorName))
                        .when()
                        .post("Library/Addbook.php")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .asString();

        JsonPath jsonPathAddBook = ReUsableMethods.rawToJson(postAddBookResponse);
        String bookId = jsonPathAddBook.getString("ID");
        System.out.println(bookId);
    }
}
