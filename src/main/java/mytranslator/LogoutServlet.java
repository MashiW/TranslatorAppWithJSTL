package mytranslator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hsenid on 3/21/16.
 */
public class LogoutServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.trace("Destroying session !");
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (IOException e) {
            LOGGER.error("Error in logout !", e);
        }
    }

}


