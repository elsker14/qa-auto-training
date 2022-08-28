package restAssuredTraining.apiTraining.libraryApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restAssuredTraining.helpers.Payload;
import restAssuredTraining.helpers.ReUsableMethods;

import static io.restassured.RestAssured.given;

public class DynamicJason {

    @Test(dataProvider = "BooksData", priority = 1)
    public void addBook(String bookName, String isbn, String aisle, String authorName) {
        /*
        // old way
        String bookName = "In lumea lui Jijel";
        String isbn = "6868";
        String aisle = "haha";
        String authorName = "Iancu Jianu 1";

        // .body(Payload.addBook(bookName, isbn, aisle, authorName))
        */
        RestAssured.baseURI = "http://216.10.245.166";
        String postAddBookResponse =
                given()
                        .header("Content-Type", "application/json")
                        .body(Payload.addBook(bookName, isbn, aisle, authorName))
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

    @Test(dataProvider = "BooksData", priority = 2)
    public void getBook(String bookName, String isbn, String aisle, String authorName) {
        RestAssured.baseURI = "http://216.10.245.166";
        String getBookResponse =
                given()
                        .header("Content-Type", "application/json")
                        .queryParam("ID", (isbn + aisle))
                        .when()
                        .get("/Library/GetBook.php")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .asString();

        JsonPath jsonPathGetBook = ReUsableMethods.rawToJson(getBookResponse);
        System.out.println("Get book TEST -> Response JSON: " + getBookResponse);
//        Assert.assertEquals(jsonPathGetBook.getString("book_name"), bookName,
//                "Book does not match, expected: " + bookName + ", found: " + jsonPathGetBook.getString("book_name"));
//        Assert.assertEquals(jsonPathGetBook.getString("isbn"), isbn,
//                "Isbn does not match, expected: " + isbn + ", found: " + jsonPathGetBook.getString("isbn"));
//        Assert.assertEquals(jsonPathGetBook.getString("aisle"), aisle,
//                "Aisle does not match, expected: " + aisle + ", found: " + jsonPathGetBook.getString("aisle"));
//        Assert.assertEquals(jsonPathGetBook.getString("author"), authorName,
//                "Book does not match, expected: " + authorName + ", found: " + jsonPathGetBook.getString("author"));
    }

    @Test(dataProvider = "BooksData", priority = 3)
    public void deleteBook(String bookName, String isbn, String aisle, String authorName) {
        RestAssured.baseURI = "http://216.10.245.166";
        String deleteBookResponse =
                given()
                        .header("Content-Type", "application/json")
                        .body(Payload.deleteBook(isbn, aisle))
                        .when()
                        .delete("/Library/DeleteBook.php")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .asString();
        JsonPath jsonPathDeleteBook = ReUsableMethods.rawToJson(deleteBookResponse);
        System.out.println("Delete book TEST -> Status: " + jsonPathDeleteBook.getString("msg"));
        Assert.assertTrue(jsonPathDeleteBook.getString("msg").equalsIgnoreCase("book is successfully deleted"),
                "Delete was unsuccessful");
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][]{
                {"eeee1", "66661", "77771", "ffff1"},
                {"gggg1", "88881", "99991", "hhhh1"}
        };
    }
}
