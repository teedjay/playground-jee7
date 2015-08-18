package teedjay.servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

/**
 * Simple JEE7 servlet with principal info injected.
 *
 * curl -u ola http://localhost:8080/jee7-1.0-SNAPSHOT/servlet
 *
 * @author thore
 */
@WebServlet("/servlet")
public class HelloWorldServlet extends HttpServlet {

    @Inject Principal principal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // request.login("ole", "bole"); // manual login

        PrintWriter out = response.getWriter();
        out.printf("Hello there GET user %s\n", userInfo());
        out.println("getRemoteUser = " + request.getRemoteUser());
        out.println("getUserPrincipal = " + request.getUserPrincipal());
        out.println("isUserInRole = " + request.isUserInRole("r1"));
        out.println("getAuthType = " + request.getAuthType());

    }

    private String userInfo() {
        return (principal == null) ? "unknown" : principal.getName();
    }

}
