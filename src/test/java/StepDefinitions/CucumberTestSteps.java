package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CucumberTestSteps {

    @Given("the user school name")
    public void the_user_school_name() {
        System.out.println("Techtorial");
    }

    @When("the user print the name")
    public void the_user_print_the_name() {
        System.out.println("Volodymyr");
    }

    @Then("the user print the last name")
    public void the_user_print_the_last_name() {
        System.out.println("Bendiukevych");
    }

    @Then("the user print the city")
    public void the_user_print_the_city() {
        System.out.println("Des Plains");
    }

    @Then("the user print the state")
    public void the_user_print_the_state() {
        System.out.println("Illinois");
    }

    @Then("the user validate the product titles")
    public void the_user_validate_the_product_titles(List<String> titles) {

        for(String title : titles){
            System.out.println("Titles is " +title);
        }
    }

    @Then("the user login to the page")
    public void the_user_login_to_the_page(Map<String,String> data) {

        Set<String> keys =data.keySet();// --> return type of keySet is Set

        // we loop our keys to get the all keys from map
        for (String key : keys){
            // we used the get method which is from the map tho get  the value
            System.out.println(data.get(key));
        }
    }


    @Then("the user validate order details list of list")
    public void the_user_validate_order_details_list_of_list(DataTable dataTable) {
        //asList method will return the List of List of String
       List<List<String>> productDetails = dataTable.asLists();

       for (int i=0; i<productDetails.size();i++){

           for (int k=0;k<productDetails.get(i).size();k++){
               System.out.print(productDetails.get(i).get(k)+"-");
           }
           System.out.println();
       }

    }

    @Then("the user validate order details list of map")
    public void the_user_validate_order_details_list_of_map(List<Map<String,String>> data) {

        for (Map<String,String> dt: data){
           Set<String> keys = dt.keySet();
            System.out.println(keys);
           for (String key : keys){
               System.out.println(dt.get(key));
           }
        }

    }



}
