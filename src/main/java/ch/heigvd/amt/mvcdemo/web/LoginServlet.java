package ch.heigvd.amt.mvcdemo.web;

import ch.heigvd.amt.mvcdemo.model.User;
import ch.heigvd.amt.mvcdemo.services.ResourceNotFoundException;
import ch.heigvd.amt.mvcdemo.services.UserManagerServiceLocal;

import java.io.IOException;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @Description This servlet is used to manage the http request to authenticate a user,
 *              when a request url contains "/login",  this servlet will catch the request and
 *              forward a response according to the request method.
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private UserManagerServiceLocal userManagerService;


    /**
     * @description This fonction will be used by the servlet to manage every http request with get method.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

    }

    /**
     * @description This fonction will be used by the servlet to manage every http request with post method.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        User user = null;
        try {
            // We make sure the user exist
            user = userManagerService.getUser(login, pwd);
            request.getRequestDispatcher("index.jsp").forward(request, response); // if yes then we direct him to the main page index.jsp
            return;
        }catch (ResourceNotFoundException e) { // if not we remain to the same login page
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}