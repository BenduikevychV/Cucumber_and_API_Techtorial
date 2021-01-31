package API.RahulPractice;

import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());

        int sizeCourses = js.getInt("courses.size()");

        System.out.println(sizeCourses);
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        int firstElCours = js.getInt("courses.get(0).price");
        System.out.println(firstElCours);

        String firstElCourse1 = js.getString("courses.get(0).title");
        System.out.println(firstElCourse1);

        for (int i=0; i<sizeCourses; i++){
            String title = js.getString("courses.get("+i+").title");
            int price = js.getInt("courses.get("+i+").price");
            System.out.println("Title is: "+title+". Price is: "+price);
        }


        for (int i=0; i<sizeCourses; i++){
            String courseTitle = js.getString("courses.get("+i+").title");
            if (courseTitle.equals("RPA")){
                int copiesRPA = js.getInt("courses.get("+i+").copies");
                System.out.println(copiesRPA);
                break;
            }
        }


    }
}
