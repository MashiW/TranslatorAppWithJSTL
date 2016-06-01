package mytranslator.seleniumtests.logintestsele;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by hsenid on 6/1/16.
 */
public class LoginTest {

    private WebDriver driver;

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
    public void initDriver(String na, String pw, String expected) throws Exception {

        driver = new ChromeDriver();

        driver.get("http://localhost:8080/");
        Thread.sleep(2000);
        driver.findElement(By.id("txtlogin")).sendKeys(na); //username input field
        Thread.sleep(2000);
        driver.findElement(By.id("txtPw")).sendKeys(pw); // password input field
        Thread.sleep(2000);
        driver.findElement(By.id("btnlogin")).click(); // login button
        Thread.sleep(2000);

        String actual=driver.getTitle(); // title of the page
        Thread.sleep(2000);

        Assert.assertEquals(actual, expected);
    }
}
