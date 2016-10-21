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
 * Created by Patrick-PC on 12.10.2016.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @EJB
    UserManagerServiceLocal userManagerService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        try {
            userManagerService.registerUser(new User(email, login, pwd));
        } catch (DuplicateResourceException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }
}
