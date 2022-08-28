package restAssuredTraining.advancedJsonParsing;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import restAssuredTraining.helpers.Payload;

import java.util.List;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.coursePrice());

        // 1. Print No of courses returned by API
        int nrOfCourses = jsonPath.getInt("courses.size()");
        System.out.println(nrOfCourses);

        // 2. Print Purchase Amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        // 3. Print Title of the first course
        String titleFirstCourse = jsonPath.getString("courses[0].title");
        System.out.println(titleFirstCourse);

        // 4. Print All course titles and their respective Prices
        /* my idea */
        List<String> listOfCourse = jsonPath.getList("courses");
        System.out.println(listOfCourse);

        /* course idea */
        for (int i = 0; i < nrOfCourses; i++) {
            System.out.println(jsonPath.getString("courses[" + i + "].title") + " -> " + jsonPath.getInt("courses[" + i + "].price"));
        }

        // 5. Print no of copies sold by RPA Course
        /* my idea */
        for (int i = 0; i < nrOfCourses; i++) {
            if (jsonPath.getString("courses[" + i + "].title").equals("RPA"))
                System.out.println("Nr of copies for RPA: " + jsonPath.getString("courses[" + i + "].copies"));
        }

        // 6. Verify if Sum of all Course prices matches with Purchase Amount
        /* my idea */
        int sumOfCourses = 0;
        for (int i = 0; i < nrOfCourses; i++) {
            sumOfCourses += jsonPath.getInt("courses[" + i + "].price") * jsonPath.getInt("courses[" + i + "].copies");
        }
        Assert.assertEquals(purchaseAmount, sumOfCourses, "Summed courses " + sumOfCourses + " does not match the purchase Amount " + purchaseAmount);
        System.out.println("Summed courses: " + sumOfCourses + " and the purchase Amount: " + purchaseAmount);
    }
}
