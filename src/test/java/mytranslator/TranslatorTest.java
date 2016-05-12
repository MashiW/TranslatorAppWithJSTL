package mytranslator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class TranslatorTest {

    Translation translation = new Translation();
    boolean rep =false;

    /*
    * Using data provider for testcase
    * */
    @DataProvider(name = "text")
    public Object[][] text() {

        return new Object[][]{
                {"child", "en", "fr", "enfant"}, // correct language selection
        };
    }

    @Test(dataProvider = "text")
    public void testTranslation(String fromText, String fromLang, String toLang, String exp) {

        Translation translation = new Translation();
        try {
            String rep = translation.textTranslate(fromText, fromLang, toLang);
            Assert.assertEquals(rep, exp, "Correct translation");
            System.out.println(fromText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void  wrongFromLanguage(){

        try{
            rep = Boolean.parseBoolean(translation.textTranslate("child", "fr", "en"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Wrong FromLanguage selected");
    }

    @Test
    public void  wrongToLanguage(){

        try{
            rep = Boolean.parseBoolean(translation.textTranslate("enfant", "en", "fr"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Wrong ToLanguage selected");
    }

    @Test
    public void  emptyInputText(){

        try{
            rep = Boolean.parseBoolean(translation.textTranslate("", "en", "fr"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Empty input text");
    }
    @Test
    public void  invalidCharacters(){

        try{
            rep = Boolean.parseBoolean(translation.textTranslate("u*6frrr", "en", "fr"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Invalid Input Characters");
    }

  /*
        @Test(expectedExceptions = NullPointerException.class)
        public void testNullPointerException() {
            List list = null;
            int size = list.size();
        }*//*

   *//* @Test
    public void testNullPointerException() {
        try {
            TranslateServlet trans = new TranslateServlet();
            trans.validateTranslt(frmtext);
           if()
            fail("The test should have failed");
        } catch (NullPointerException e) {
            // success, do nothing: the test will pass
        }
    }*/
}

