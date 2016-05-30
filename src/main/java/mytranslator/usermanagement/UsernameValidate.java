package mytranslator.usermanagement;

import mytranslator.databasemanagement.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsernameValidate extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UsernameValidate.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("Invoked the UsernameValidate Servlet...");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("uname");
        String sql = "select username from user where username=\'" + uname + "\';";

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);

            if (rs.next()) {
                out.println("<img src='images/error.png'> Error: Username <b>" + uname + "</b> is alreday taken ");
            } else {
                out.println("<img src='images/yes.png'> Username available");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in username validate method..", ex);
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}