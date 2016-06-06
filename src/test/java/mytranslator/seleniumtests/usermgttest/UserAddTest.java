package mytranslator.seleniumtests.usermgttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/2/16.
 */
public class UserAddTest {

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

    @DataProvider(name = "addUser")
    public Object[][] addUserInfo() {

        return new Object[][]{
                {
                        "Username", "fname", "lstname", "1995-Aug-14",
                        "PassWd123", "PassWd123", "Administrator",
                        "Sri Lanka", "Colombo", "1236547896", "hhhuj@gmail.com", "OK"}
        };
    }

    @Test(dataProvider = "addUser")
    public void getAddUser(String unm, String fnm, String lnm, String pw, String cfpw, String DOB,
                           String group, String country, String city, String tele, String email, String expected) {

        driver.findElement(By.id("usermgtTab")).click(); // click on user management tab
        driver.findElement(By.id("userAddLink")).click(); // click on add user link

        String dobYear = "/html/body/div[2]/div[3]/table/tbody/tr/td/span[7]";
        String dobMonth = "/html/body/div[2]/div[2]/table/tbody/tr/td/span[7]";
        String dobDate= "/html/body/div[2]/div[1]/table/tbody/tr[4]/td[2]";

        driver.findElement(By.id("txtuname")).sendKeys(unm); // type username
        driver.findElement(By.id("txtfname")).sendKeys(fnm); // type firstname
        driver.findElement(By.id("txtlstnm")).sendKeys(lnm); // type last name

        driver.findElement(By.id("date")).click();
        driver.findElement(By.xpath(dobYear)).click();
        driver.findElement(By.xpath(dobMonth)).click();
        driver.findElement(By.xpath(dobDate)).click();
        driver.findElement(By.id("date")).sendKeys(DOB);

        driver.findElement(By.id("txtpass")).sendKeys(pw); // type [assword
        driver.findElement(By.id("txtconfpass")).sendKeys(cfpw); // rewrite password

        Select group1 = new Select(driver.findElement(By.id("slctgrp"))); // click group list
        group1.selectByVisibleText(String.valueOf(group)); // select a group

        Select country1 = new Select(driver.findElement(By.id("slctcountry"))); // click country list
        country1.selectByVisibleText(String.valueOf(country)); // select a country

        Select city1 = new Select(driver.findElement(By.id("slctcity"))); // click city list
        city1.selectByValue(String.valueOf(city)); // select a city


        driver.findElement(By.id("txtphone")).sendKeys(tele); // type last phone number
        driver.findElement(By.id("txtemail")).sendKeys(email); // type email

        driver.findElement(By.id("btnAddusr")).click(); // click add user button

        //String requiredFiled = driver.findElement(By.className("input-group-error")).getText();
        WebElement inputs = driver.findElement(By.id("finalErr"));
        inputs.getText();
      // requiredFiled.getAttribute("value");
        String actual;

        if(inputs == null ){
            actual="OK";
        }else{
            actual="";
        }

        Assert.assertEquals(actual,expected);
    }

    @AfterMethod
    public void closeDriver(){

        driver.quit();
    }
}
