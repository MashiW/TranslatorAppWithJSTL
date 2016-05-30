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
import java.sql.SQLException;

public class DeleteUser extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(DeleteUser.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoked to DeleteUser Servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String usrname = request.getParameter("val");

        String sql = "delete from user where username=\'" + usrname + "\';";

        Connection con = null;
        PreparedStatement st = null;

        try {
            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sql);
            int rs = st.executeUpdate();

            if (rs == 1) {
                out.println(rs);
            }

        } catch (Exception ex) {
            LOGGER.error("Error in user deletion..", ex);
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
        }
    }
}