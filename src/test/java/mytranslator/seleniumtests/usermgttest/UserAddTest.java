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
                        "Username", "fname", "lstname", "2000-01-03",
                        "PassWd123", "PassWd123", "Administrator",
                        "Sri Lanka", "Colombo", "1236547896", "hhhuj@gmail.com", "OK"}
        };
    }

    @Test(dataProvider = "addUser")
    public void getAddUser(String unm, String fnm, String lnm, String dob, String pw, String cfpw,
                           String group, String country, String city, String tele, String email, String expected) {

        driver.findElement(By.id("usermgtTab")).click(); // click on user management tab
        driver.findElement(By.id("userAddLink")).click(); // click on add user link

        driver.findElement(By.id("txtuname")).sendKeys(unm); // type username
        driver.findElement(By.id("txtfname")).sendKeys(fnm); // type firstname
        driver.findElement(By.id("txtlstnm")).sendKeys(lnm); // type last name
        driver.findElement(By.id("date")).sendKeys(dob); // select dob
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
        WebElement inputs = driver.findElement(By.cssSelector("form-group.required"));
        inputs.getText();
      // requiredFiled.getAttribute("value");
        String actual;

        if(inputs != null ){
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
