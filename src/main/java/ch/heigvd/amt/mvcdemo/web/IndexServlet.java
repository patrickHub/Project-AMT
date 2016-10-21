package ch.heigvd.amt.mvcdemo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Patrick-PC on 19.10.2016.
 */
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        String currentLogin = (String)session.getAttribute("login");
        System.out.println("LOGIN : " + currentLogin);
        currentLogin = "patfs";
        request.setAttribute("login", currentLogin);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
