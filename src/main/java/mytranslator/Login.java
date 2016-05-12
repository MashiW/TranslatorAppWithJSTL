package mytranslator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hsenid on 3/14/16.
 */
public class Login extends HttpServlet {
    /**
     * User login class
     */

    private static final Logger LOGGER = LogManager.getLogger(Login.class);

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("Invoked the Login Servlet...");

        /**
         *new obj creation of Translation class
         */
        Translation translate = null;

        response.setContentType("text/html");

        /**
         *username = get the name entered by user's input
         * password = get the password entered by user's input
         */
        String username = request.getParameter("txtUn");
        String password = request.getParameter("txtPw");

        if (loginValidate(username, password)) {

            /**
             Setting session by user name
             */
            LOGGER.trace("Creating session..");
            HttpSession session = request.getSession();
            session.setAttribute("sessionname", username);

            /*
            * get the languages to an array list as langlist
            * set attribute of the langlist as 'list'
            **/
            translate = new Translation();
            ArrayList<String> langlist = null;
            langlist = translate.getLangs();
            request.setAttribute("list", langlist);

            /** send result to the translate page
             */
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mainprocess.jsp");
            rd.forward(request, response);

        } else {
            LOGGER.error("Invalid session..");
            request.setAttribute("errorMessage", "Invalid credentials. Try again !");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    public boolean loginValidate(String uname, String pswd) throws ServletException {

        /**
         * sql - query for the data extraction from tbl_user and compare with user's input name and password
         * password is compared after hashing
         */
        String sql = "select username, usr_pass from user where username=binary\"" + uname + "\" and usr_pass=md5(\"" + pswd + "\");";

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement st = null;
        boolean result = false;

        try {
            con = Database.cpds.getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            if (rs.first()) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error in login validate method..", e);
            throw new ServletException(e);
        } finally {
            try {
                LOGGER.trace("Closing connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing connection..");
                e.printStackTrace();
            }
            try {
                LOGGER.trace("Closing Prepared statement..");
                st.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing prepared statement !", e);
            }
            try {
                LOGGER.trace("Closing resultset..");
                rs.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error closing resultset !", e);
            }
        }

        return result;
    }
}
