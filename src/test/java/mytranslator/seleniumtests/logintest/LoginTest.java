package mytranslator.seleniumtests.logintest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/1/16.
 */
public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void initDriver() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
    }


    @DataProvider(name = "test")
    public Object[][] users() {

        return new Object[][]{
                {"frank", "Frank123", "Login"}, //valid username and password
                {" ", " ", "Home"}, // both username and password empty
                {" ", "Frank123", "Home"}, // empty username, correct password
                {"frank", " ", "Home"}, // correct username, empty password
                {"x", "Frank123", "Home"}, // wrong username, correct password
                {"frank", "x", "Home"}, // correct username, wrong password
                {"fran", "xx", "Home"} // both username and password wrong
        };
    }


    @Test(dataProvider = "test")
    public void startTest(String na, String pw, String expected) throws Exception {

        driver.findElement(By.id("txtlogin")).sendKeys(na); //username input field
        driver.findElement(By.id("txtPw")).sendKeys(pw); // password input field
        driver.findElement(By.id("btnlogin")).click(); // login button

        String actual = driver.getTitle(); // title of the page

        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void endDriver(){

        driver.quit();
    }
}
