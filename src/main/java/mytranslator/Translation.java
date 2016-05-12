package mytranslator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Translation {

    private static final Logger LOGGER = LogManager.getLogger(Translation.class);

    String transtext = null;

    /*
    * getLangs = method for getting the language list
    * */
    PropertyReader propobj = new PropertyReader();

    @Deprecated
    public ArrayList<String> getLangs() throws ServletException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(propobj.getproperty("yandex.langs.list","system.properties"));

        HttpResponse response = null;
        InputStream input = null;// Get the response

        //creating DOM object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            response = httpClient.execute(request);

            LOGGER.trace("Getting language list..");
            input = response.getEntity().getContent();

            builder = dbf.newDocumentBuilder();
            doc = builder.parse(input);

        } catch (IOException e) {
            LOGGER.error("Error while getting language list !", e);
            throw new ServletException(e);
        } catch (ParserConfigurationException e) {
            LOGGER.error("Error while getting language list !", e);
            throw new ServletException(e);
        } catch (SAXException e) {
            LOGGER.error("Error while getting language list !", e);
            throw new ServletException(e);
        }

        //Element root = doc.getDocumentElement();
        NodeList nameNodesList = doc.getElementsByTagName("Item"); //tagname =Item

        //get result to an array list
        ArrayList<String> listValues = new ArrayList<String>();

        //get the Item tag's attribute value
        for (int i = 0; i < nameNodesList.getLength(); i++) {
            listValues.add(nameNodesList.item(i).getAttributes().getNamedItem("value").getNodeValue());
        }
        return listValues;
    }

    public String textTranslate(String fromText, String fromLang, String toLang) throws IOException, SAXException, ParserConfigurationException {

        PropertyReader propobj2 = new PropertyReader();

        String yndxUrl = propobj2.getproperty("yandex.url","system.properties");
        String apiKey = propobj2.getproperty("yandex.api.key","system.properties");

        String url = yndxUrl + apiKey + "&text=" + fromText + "&lang=" + fromLang + "-" + toLang;
        HttpClient client = new DefaultHttpClient();

        HttpGet rq = new HttpGet(url);
        HttpResponse resp = null;

        //get response
        InputStream input2 = null;

        //creating DOM
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = null;

        Document doc2 = null;

        try {
            resp = client.execute(rq);
            input2 = resp.getEntity().getContent();
            builder2 = dbf2.newDocumentBuilder();

            doc2 = builder2.parse(input2);
            NodeList textNodelist = doc2.getElementsByTagName("text");
            transtext = String.valueOf(textNodelist.item(0).getTextContent());
        } catch (IOException e) {
            throw new IOException();
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
        return transtext;
    }
}

