package ch.heigvd.amt.mvcdemo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @Description This servlet is used to manage the http request to get the main page index.jsp
 */
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

    /**
     * @description This fonction will be used by the servlet to manage every http request with get method.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        String currentLogin = (String)session.getAttribute("login");
        request.setAttribute("login", currentLogin);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
