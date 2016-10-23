package ch.heigvd.amt.mvcdemo.web;

import ch.heigvd.amt.mvcdemo.model.User;
import ch.heigvd.amt.mvcdemo.services.DuplicateResourceException;
import ch.heigvd.amt.mvcdemo.services.ResourceNotFoundException;
import ch.heigvd.amt.mvcdemo.services.UserManagerServiceLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @Description This servlet is used to manage the http request to register a user,
 *              when a request url contains "register",  this servlet will catch the request and
 *              forward a response according to the request method.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    // injection dependency
    @EJB
    UserManagerServiceLocal userManagerService;

    /**
     * @description This fonction will be used by the servlet to manage every http request with post method.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // we get users proprities
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        try {
            // we register the user
            userManagerService.registerUser(new User(email, login, pwd));
        } catch (DuplicateResourceException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        // then will draw the user main page index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * @description This fonction will be used by the servlet to manage every http request with get method.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }
}
