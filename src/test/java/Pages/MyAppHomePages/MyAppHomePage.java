package Pages.MyAppHomePages;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyAppHomePage {

    WebDriver driver = Driver.getDriver();

    public MyAppHomePage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//table//tr/th")
    public List<WebElement> columns;

    @FindBy(xpath = "//table//tr[1]/td")
    public List<WebElement> row1;

    @FindBy(xpath = "//table//tr[2]/td")
    public List<WebElement> row2;

    @FindBy(xpath = "//table//tr[3]/td")
    public List<WebElement> row3;

    @FindBy(xpath = "//table//tr[4]/td")
    public List<WebElement> row4;


}
