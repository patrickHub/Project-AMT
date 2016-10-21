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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private UserManagerServiceLocal userManagerService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        User user = null;
        user = userManagerService.getUser(login, pwd);
        if(user != null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}