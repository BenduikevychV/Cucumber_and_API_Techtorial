package StepDefinitions;

import Utils.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    // We have before and after annotations.
    // Those are coming from the Cucumber.
    // They will run before and after each scenario
    // We have one Scenario interface to get the details from every scenario

   @Before
   public  void setUp(Scenario scenario){
       System.out.println("This one will run before each scenario");
       System.out.println(scenario.getName());
   }

   @After
    public void tearDown(Scenario scenario){
       System.out.println("This one will run after each scenario");
       // after annotation will execute after scenario even though they are failed, undefined.
       if (scenario.isFailed()){
           System.out.println("--> is failed");
           BrowserUtils.takeScreenShot();
       }
   }
   //I am saying run this Before if the scenario has @tt tag
   @Before("@tt")
    public void conditionalAnnotation(){
       // I want to run this annotation only before @conditional tag
       System.out.println("Conditional annotation");
       // It will run only the scenario which has @conditional tag
   }

}
