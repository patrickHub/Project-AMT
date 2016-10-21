package ch.heigvd.amt.mvcdemo.web;

import ch.heigvd.amt.mvcdemo.model.User;
import ch.heigvd.amt.mvcdemo.services.TestDataServiceLocal;
import ch.heigvd.amt.mvcdemo.services.UserManagerServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick-PC on 08.10.2016.
 */
@WebServlet(name = "UserDataServlet")
public class UserDataServlet extends HttpServlet {

      @EJB
      private UserManagerServiceLocal userManagerService;

      @EJB
      private TestDataServiceLocal testDataService;

      private final int nberOfUsers = 20;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        testDataService.generateTestData(nberOfUsers);
        List<User> users = new LinkedList<>();
        users = userManagerService.getUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/usersdata.jsp").forward(request, response);

    }

}
