package mytranslator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    /**
     * declaring new arraylist for getting permission list
     */
    ArrayList<String> permissionlist = new ArrayList<String>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to UserPermission servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        permissionlist = getPermissionList(request.getParameter("userName"));

        JsonArray jsonArrayPermission;
        jsonArrayPermission = new JsonArray();

        try {
            for (int i = 0; i <= permissionlist.size(); i++) {
                jsonArrayPermission.add(permissionlist.get(i));
            }
        } catch (Exception e) {
            LOGGER.error("Error in getting value of permissionlist");
        }
        out.println(jsonArrayPermission);
    }

    /**
     * method for getting permission list for user = userName
     */
    public ArrayList<String> getPermissionList(String userName) throws ServletException {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st;

        ArrayList<String> arrayList = new ArrayList<String>();

        String sqlgroup = "SELECT name FROM `permission`\n" +
                "WHERE id IN\n" +
                "(SELECT per_id FROM permission_group\n" +
                " WHERE grp_id IN\n" +
                " (SELECT grp_id FROM user_group\n" +
                "  WHERE username=\'" + userName + "\'))";

        try {
            con = Database.cpds.getConnection();
            st = con.prepareStatement(sqlgroup);
            rs = st.executeQuery();

            while (rs.next()) {
                arrayList.add(rs.getString("name"));
            }

        } catch (SQLException ex) {
            LOGGER.error("Error..");

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
        return arrayList;
    }
}
