package API.RahulPractice;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

public class SumValidation {

    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size");

        int amount = 0;
        for (int i=0; i<count;i++){
            int sumOfCopies =js.getInt("courses.get("+i+").copies");
            int sumOfPrices = js.getInt("courses.get("+i+").price");
            amount += sumOfCopies * sumOfPrices;
        }

        Assert.assertEquals(js.getInt("dashboard.purchaseAmount"),amount);
        System.out.println(amount);
    }
}
