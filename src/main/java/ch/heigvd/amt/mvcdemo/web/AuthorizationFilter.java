package ch.heigvd.amt.mvcdemo.web;

import ch.heigvd.amt.mvcdemo.services.AuthorizationManagerServiceLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Patrick-PC on 05.10.2016.
 */
@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    @EJB
    private AuthorizationManagerServiceLocal authorizationManagerService;
    private String errorPage;
    public void destroy() {
    }

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/bootstrap", "/dist", "/plugins", "/login", "/logout", "/register")));
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        HttpSession session = ((HttpServletRequest)request).getSession();
        String currentLogin = (String)session.getAttribute("login");
        String currentPassword = (String)session.getAttribute("password");
        //Get relevant URI.
        String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length()).replaceAll("[/]+$", "");

        boolean loggedIn = ((session != null) && ((session.getAttribute("login") != null) && (session.getAttribute("password") != null)));

        boolean allowedPath = false;
        for(String str : ALLOWED_PATHS){
            if(path.startsWith(str)){
                allowedPath = true;
                break;
            }
        }
        chain.doFilter(request, response);
       /* if (allowedPath) {
            chain.doFilter(request, response);
        }else if (currentLogin == null && currentPassword == null){
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
        }else{
            chain.doFilter(request, response);
        }*/
        /*else {

            //Invoke AuthorizationManagerServiceLocal method to see if user can
            //access resource.
            boolean authorized = false;
            try {
                authorized = authorizationManagerService.isUserAuthorized(currentLogin, currentPassword, URI);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (authorized) {
                request.getRequestDispatcher(URI).forward(request,response);
            }
            else {
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
            }
        }*/
    }

    private boolean isProtected(String url){
        System.out.println("URL : " + url);
        if(url.startsWith("/bootstrap")){
            return false;
        }
        else if(url.startsWith("/dist")){
            return false;
        } else if(url.startsWith("/plugins")){
            return false;
        }
        return  true;
    }

    /**Filter should be configured with an system error page.*/
    public void init (FilterConfig FilterConfig) throws ServletException {
        if (FilterConfig != null) {
            errorPage = FilterConfig.getInitParameter("login");
        }
    }

}
