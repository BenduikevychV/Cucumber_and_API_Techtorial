package StepDefinitions;

import Utils.ConfigReader;
import Utils.Driver;
import com.sun.codemodel.JCatchBlock;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenURL {

    WebDriver driver = Driver.getDriver();

    @Test
    public void validatingUrl() {

        driver.get("https://www.youtube.com/");

        List<WebElement> links = driver.findElements(By.xpath("//a"));

        for (int i = 0; i < links.size(); i++) {
            String linkURL = links.get(i).getAttribute("href");
            try {

                if (linkURL != null) {
                    URL object = new  URL(linkURL);
                    HttpURLConnection connection = (HttpURLConnection) object.openConnection();
                    int rCode = connection.getResponseCode();
                    if (rCode==200){
                        System.out.println("Link is valid: "+ linkURL);
                    }else {
                        System.out.println("Link is broken: "+linkURL);
                    }
                }else {
                    System.out.println("Link is broken:" + links.get(i).getText());
                }

            }catch (Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }


    }



}
