package mytranslator.usermanagement;

import com.google.gson.JsonArray;
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

public class TypeaheadUsername extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(TypeaheadUsername.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to TypeaheadUSername servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("usrnm");
        String sql = "select * from user where username LIKE \'%" + username + "%\';";


        JsonArray jsonArray = new JsonArray();
        Connection con = null;
        PreparedStatement st;
        ResultSet rs;

        try {

            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                jsonArray.add(rs.getString("username"));
            }
            out.println(jsonArray);

        } catch (Exception e) {
            LOGGER.error("Error in username typeahed loading..");
        } finally {
            try {
                LOGGER.trace("Closing typeaheadUsrname connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing typeaheadUsrname connection..");
                e.printStackTrace();
            }
        }
    }
}