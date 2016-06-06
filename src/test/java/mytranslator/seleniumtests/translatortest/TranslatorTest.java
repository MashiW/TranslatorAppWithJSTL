package mytranslator.seleniumtests.translatortest;

import mytranslator.seleniumtests.logintest.LoginTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/1/16.
 */
public class TranslatorTest {

    private WebDriver driver;

    @BeforeMethod
    public void initDriver() throws Exception {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");

        driver.findElement(By.id("txtlogin")).sendKeys("frank"); //username input field
        driver.findElement(By.id("txtPw")).sendKeys("Frank123"); // password input field
        driver.findElement(By.id("btnlogin")).click(); // login button

    }

    @DataProvider(name = "transTest")
    public Object[][] texts(){

        return new Object[][]{
                {"child", "en", "fr", "enfant"}, // correct language selection
                {"", "en", "fr", ""}, // incorrect
                {"tagfvfd", "en", "fr", "tagfvfd"},
                {"4546342", "en", "fr", "4546342"},
                {"enfant", "fr", "en", "child"},
                {"child", "en", "en", "child"}
        };
    }

    @Test(dataProvider = "transTest")
    public void translateTest(String fromTxt, String fromLang, String toLang, String expected){

        driver.findElement(By.name("txtFromText")).sendKeys(fromTxt); // text to be translated
        driver.findElement(By.name("frmType")).sendKeys(fromLang); // original language from
        driver.findElement(By.name("toType")).sendKeys(toLang); // language to be translated
        driver.findElement(By.id("btnTransTxt")).click(); // translate button click

        String actual = driver.findElement(By.id("toText")).getText(); // text translated

        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }
}
