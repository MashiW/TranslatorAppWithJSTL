package mytranslator.usermanagement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

public class SearchUser extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(SearchUser.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to SearchUser servlet..");

        PrintWriter out = response.getWriter();
        String sql;

        String params = request.getParameter("usrnm");
        String s=request.getParameter("initPage");

        int page = Integer.parseInt(s);

        if(params==null){
            sql = "select * from user LIMIT 10 OFFSET "+(page-1)+";";
        }else{
            sql = "select * from user where username LIKE \'%" + params + "%\' LIMIT 10 OFFSET "+(page-1)+";";
        }

        JsonObject jsonObj;
        JsonArray jsonArray = new JsonArray();
        Connection con = null;
        PreparedStatement st;
        ResultSet rs;
        ResultSet rs2;

        try {
            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                jsonObj = new JsonObject();
                jsonObj.addProperty("usrnm", rs.getString(1));
                jsonObj.addProperty("usrfn", rs.getString(3));
                jsonObj.addProperty("usrln", rs.getString(4));
                jsonObj.addProperty("usrdob", rs.getString(5));
                jsonObj.addProperty("usrphone", rs.getString(6));
                jsonObj.addProperty("usrcntry", rs.getString(7));
                jsonObj.addProperty("usremail", rs.getString(9));

                String citysql = "select city from city where id=" + Integer.parseInt(rs.getString(8)) + ";";
                st = con.prepareStatement(citysql);
                rs2 = st.executeQuery();

                while (rs2.next()) {
                    jsonObj.addProperty("usrcity", rs2.getString("city"));
                }
                jsonArray.add(jsonObj);
            }
            out.println(jsonArray);

        } catch (SQLException ex) {
            LOGGER.error("Error in user details sql..", ex);
        } finally {
            try {
                LOGGER.trace("Closing loadCity connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing loadCity connection..");
                e.printStackTrace();
            }
        }

    }
}
