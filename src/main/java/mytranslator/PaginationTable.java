package mytranslator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PaginationTable extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(PaginationTable.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.trace("Invoking to Pagination servlet ..");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


    }
}
