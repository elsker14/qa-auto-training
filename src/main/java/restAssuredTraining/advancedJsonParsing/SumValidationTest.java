package restAssuredTraining.advancedJsonParsing;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import restAssuredTraining.helpers.Payload;

public class SumValidationTest {

    @Test
    public void validateSumOfCourses() {
        JsonPath jsonPath = new JsonPath(Payload.coursePrice());
        int nrOfCourses = jsonPath.getInt("courses.size()");
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");

        int sumOfCourses = 0;
        for (int i = 0; i < nrOfCourses; i++) {
            sumOfCourses += jsonPath.getInt("courses[" + i + "].price") * jsonPath.getInt("courses[" + i + "].copies");
        }
        Assert.assertEquals(purchaseAmount, sumOfCourses, "Summed courses " + sumOfCourses + " does not match the purchase Amount " + purchaseAmount);
        System.out.println("Summed courses: " + sumOfCourses + " and the purchase Amount: " + purchaseAmount);
    }
}
