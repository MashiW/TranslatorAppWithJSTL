package mytranslator;

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

public class UpdateUser extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UpdateUser.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking UpdateUser servlet..");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("txtunameUpd");
        String fname = request.getParameter("txtfnameUpd");
        String lname = request.getParameter("txtlstnmUpd");
        String dob = request.getParameter("dateUpdt");
        String grp = request.getParameter("slctgrpUpd");
        String country = request.getParameter("slctcountryUpd");
        String city = request.getParameter("slctcityUpd");
        String phone = request.getParameter("txtphoneUpd");
        String email = request.getParameter("txtemailUpd");

        String sql = "update user SET first_name=\'" + fname + "\',last_name=\'" + lname + "\',dob=\'" + dob + "\',phone_no=\'" + phone + "\'," +
                "country=\'" + country + "\',city_id=(select id from city where city=\'" + city + "\') ,email=\'" + email + "\' WHERE username=\'" + uname + "\';";

        Connection con = null;
        PreparedStatement st;

        try {
            con = Database.cpds.getConnection();
            st = con.prepareStatement(sql);
            int rs = st.executeUpdate();

            if (rs == 1) {

                String sqlGrp = "update user_group set grp_id=(select id from tbl_group where name=\'" + grp + "\') WHERE username=\'" + uname + "\' ";

                st = con.prepareStatement(sqlGrp);
                int rs2 = st.executeUpdate();

                if (rs2 == 1) {
                    out.println(rs);
                }
            }

            LOGGER.trace("rs= " + rs);

            if (rs == 1) {
                LOGGER.trace("Updated user " + uname + ";");
            } else {
                LOGGER.error("Error: cannot update user" + uname + ";");
            }

        } catch (Exception e) {
            LOGGER.error("Error in user updation..", e);
        } finally {
            try {
                LOGGER.trace("Closing connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing connection..");
                e.printStackTrace();
            }
            /*try {
                LOGGER.trace("Closing Prepared statement..");
                st.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing prepared statement !", e);
            }*/
        }
    }
}
