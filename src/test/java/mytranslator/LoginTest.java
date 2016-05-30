package mytranslator;

import mytranslator.databasemanagement.Database;
import mytranslator.login.Login;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.testng.Assert.fail;

/**
 * Created by hsenid on 3/29/16.
 */
public class LoginTest {

    Login validate = new Login();
    boolean res = false;


    /*@DataProvider(name = "test")
    public Object[][] users() {

        return new Object[][]{
                {"abc", "123", true}, //valid username and password
                {" ", " ", false}, // both username and password empty
                {" ", "123", false}, // empty username, correct password
                {"abc", " ", false}, // correct password, empty username
                {"x", "123", false}, // wrong username, correct password
                {"abc", "x", false}, // correct username, wrong password
                {"ab", "xx", false} // both username and password wrong
        };
        //return new Object[][]{{String na,String pw,true}};
    }*/

    @BeforeMethod
    /**
     *call db connection
     *insert a user to db
     */
    public void setConnection() {

        PropertyReader propobj = new PropertyReader();

        String dburl = propobj.getproperty("db.url", "system.properties"); //  url of the database
        String database = propobj.getproperty("db.database", "system.properties");//database name
        String dbUname = propobj.getproperty("db.db_uname", "system.properties");// user name for database
        String dbPasswd = propobj.getproperty("db.db_pswd", "system.properties");// password for the database

        //Database db = new Database(dburl, database, dbUname, dbPasswd);
        // Database.connectDatabase(dburl, database, dbUname, dbPasswd);
        Connection conn = Database.getConn();
        String sql = "INSERT INTO tbl_user(usrName, usrPass) VALUES('abc',md5('123'));";

        try {
            PreparedStatement sta = conn.prepareStatement(sql);
            sta.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Using data provider as input for the test
    /*@Test(dataProvider = "test")
    */

    /**
     * call login validate method
     * pass parameters
     *//*
    public void userTest(String uname, String pass, boolean exp) {

        Login validate = new Login();
        try {
            boolean act = validate.loginValidate(uname, pass);
            Assert.assertEquals(act, exp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }*/
    @Test
    public void validUserPass() {

        try {
            res = validate.loginValidate("abc", "123");
        } catch (Exception ex) {
            fail();
        }
        Assert.assertEquals(res, true, "Valid username and Valid password");
    }

    @Test
    public void invalidUserPass() {

        try {
            res = validate.loginValidate("aBc", "765");
        } catch (Exception ex2) {
            fail();
        }
        Assert.assertEquals(res, false, "Invalid username and Invalid password");
    }

    @Test
    public void validUnInvalidPw() {

        try {
            res = validate.loginValidate("abc", "111");
        } catch (Exception ex3) {
            fail();
        }
        Assert.assertEquals(res, false, "Valid username but Invalid password");
    }

    @Test
    public void invalidUnValidPw() {

        try {
            res = validate.loginValidate("Abc", "123");
        } catch (Exception ex4) {
            fail();
        }
        Assert.assertEquals(res, false, "Invalid username but Valid password");
    }

    @Test
    public void emptyUnValidPw() {

        try {
            res = validate.loginValidate("", "123");
        } catch (Exception ex) {
            fail();
        }
        Assert.assertEquals(res, false, "Empty username and Valid password");
    }

    @Test
    public void validUnemptyPw() {

        try {
            res = validate.loginValidate("abc", "");
        } catch (Exception ex) {
            fail();
        }
        Assert.assertEquals(res, false, "Valid username but Empty password");
    }

    @Test
    public void emptyUnEmptyPw() {

        try {
            res = validate.loginValidate("", "");
        } catch (Exception ex) {
            fail();
        }
        Assert.assertEquals(res, false, "Empty username and Password");
    }

    @AfterMethod
    /**
     *delete added user
     *connection close
     */
    public void conClose() {
        Connection conn = Database.getConn();

        String sql = "DELETE FROM tbl_user WHERE usrName='abc' AND usrPass=md5('123');";
        if (conn != null) {
            try {
                PreparedStatement sta = conn.prepareStatement(sql);
                sta.executeUpdate(sql);
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
