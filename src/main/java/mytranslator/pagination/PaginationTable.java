package mytranslator.pagination;

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

public class PaginationTable extends HttpServlet {

    int pgNumber;

    private static final Logger LOGGER = LogManager.getLogger(PaginationTable.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.trace("Invoking to Pagination servlet ..");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        pgNumber = getPageCount();
        out.println(pgNumber);

    }

    public int getPageCount() throws ServletException{

        Connection con = null;
        ResultSet rs =null;
        PreparedStatement st;
        int pageNum =0;
        String sqlPgCount;

        sqlPgCount = " SELECT COUNT(*) AS usersCount FROM user;";

        try{
            con = Database.getDataSource().getConnection();
            st = con.prepareStatement(sqlPgCount);
            rs= st.executeQuery();

            if (rs.first()){
                pageNum=Integer.parseInt(rs.getString("usersCount"));
            }


        }catch (SQLException ex){
            LOGGER.error("Erorr in pgCountSql " +ex);
        }finally {
            try {
                LOGGER.trace("Closing PageCount connection..");
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing PageCount connection..", e);
            }
            try {
                LOGGER.trace("Closing PageCount resultset..");
                rs.close();
            } catch (SQLException ex) {
                LOGGER.fatal("Error closing PageCount resultset !", ex);
            }
        }

        return pageNum;
    }
}
