package mytranslator.seleniumtests.usermgttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/3/16.
 */
public class SearchUserTest {

    private WebDriver driver;

    @BeforeMethod
    public void initDriver() throws Exception {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");

        driver.findElement(By.id("txtlogin")).sendKeys("frank"); //username input field
        driver.findElement(By.id("txtPw")).sendKeys("Frank123"); // password input field
        driver.findElement(By.id("btnlogin")).click(); // login button
    }


    @DataProvider(name = "searchUser")
    public Object[][] addUserInfo() {

        return new Object[][]{
                {"Sam", "OK"},
                {"", "OK"},
                {"Ann","OK"}

        };
    }

    @Test(dataProvider = "searchUser")
    public void translateTest(String usernm, String expected) {

        driver.findElement(By.id("usermgtTab")).click(); // click on user management tab
        driver.findElement(By.id("userSearchLink")).click(); // click on add search link

        driver.findElement(By.name("txtSrchun")).sendKeys(usernm);
        driver.findElement(By.id("btnSrchUser")).click(); // search button click

        String actual = "OK";

        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void closeDriver() {

        driver.quit();
    }
}
