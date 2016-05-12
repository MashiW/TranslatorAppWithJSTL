package mytranslator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hsenid on 3/16/16.
 */
public class TranslateServlet extends HttpServlet {

    @Deprecated
    protected static final String ENCODING = "UTF-8";
    private static final Logger LOGGER = LogManager.getLogger(TranslateServlet.class);
    Translation getReply = null;

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("Invoked the Translate Servlet...");
        response.setContentType("text/html");

        String fromText = request.getParameter("txtFromText"); // text field's value enter by user
        String fromLang = request.getParameter("frmType"); // from language
        String toLang = request.getParameter("toType"); //  language to be translated

        try {
            getReply = new Translation();
            String textReply = getReply.textTranslate(fromText, fromLang, toLang);

            //set the language list in the page redirect
            ArrayList<String> list = new ArrayList<String>();
            list = getReply.getLangs();
            request.setAttribute("list", list);

            request.setAttribute("selectedFrom", fromLang);
            request.setAttribute("selectedTo", toLang);

            // set the attribute for translated text
            request.setAttribute("textReply", textReply);

            //set the attribute for input text
            request.setAttribute("fromText", fromText);

            //send the result to mainprocess.jsp page
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mainprocess.jsp");
            rd.forward(request, response);
            LOGGER.info("Done Translation !!");

        } catch (Exception ex) {
            LOGGER.error("Error in translating..", ex);
        }

    }
}
