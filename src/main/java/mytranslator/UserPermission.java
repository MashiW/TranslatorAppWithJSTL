package mytranslator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPermission extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UserPermission.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to UserPermission servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String usernm = request.getParameter("usernm");

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        String sqlgroup = "SELECT name FROM `permission`\n" +
                "WHERE id IN\n" +
                "(SELECT per_id FROM permission_group\n" +
                " WHERE grp_id IN\n" +
                " (SELECT grp_id FROM user_group\n" +
                "  WHERE username=\'" + usernm + "\'))";

        try {

            con = Database.cpds.getConnection();
            st = con.prepareStatement(sqlgroup);
            rs = st.executeQuery();

            ArrayList<String> permissionlist = new ArrayList<String>();

            while (rs.next()) {
                permissionlist.add(rs.getString("name"));
            }
            session.setAttribute("permissions", permissionlist);

        } catch (SQLException ex) {
            LOGGER.error("Errr");

        } finally {
            try {
                LOGGER.trace("Closing UserPermission connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing UserPermission connection..", e);
            }
            try {
                LOGGER.trace("Closing UserPermission resultset..");
                rs.close();
            } catch (SQLException ex) {
                LOGGER.fatal("Error closing UserPermission resultset !", ex);
            }
        }

    }
}
