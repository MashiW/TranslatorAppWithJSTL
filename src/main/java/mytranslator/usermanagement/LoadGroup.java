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

public class LoadGroup extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LoadGroup.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to LoadGroup servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // String[] selectedGrp = request.getParameterValues("slctdgrp");

        String sql = "select name from tbl_group";

        JsonObject jsonObj;
        JsonArray jsonArray = new JsonArray();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                jsonObj = new JsonObject();
                jsonObj.addProperty("groupNm", rs.getString("name"));
                jsonArray.add(jsonObj);
            }
            out.println(jsonArray);
            //LOGGER.error(jsonArray);

        } catch (Exception e) {
            LOGGER.error("Error while loading group list.." + e);
        } finally {
            try {
                LOGGER.trace("Closing grouplist connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing gouplist connection.." + e);
                e.printStackTrace();
            }
        }
    }
}
