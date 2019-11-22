package pl.edu.pjwstk.jaz.webapp;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*")
public class Authorization extends HttpFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
                ServletContext context = filterConfig.getServletContext();
            context.log("AuthenticationFilter initialized");
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp,
                             FilterChain chain) throws IOException, ServletException {

                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) resp;
                HttpSession session = request.getSession(false);

                String loginURL = request.getContextPath() + "/login.xhtml";
                String registerURL = request.getContextPath() + "/register.xhtml";
                String welcomeURL = request.getContextPath() + "/welcome.xhtml";

                boolean loggedIn = (session != null) && (session.getAttribute("username") != null);
                boolean loginRequest  = request.getRequestURI().equals(loginURL);
                boolean registerRequest  = request.getRequestURI().equals(registerURL);
                boolean welcomeRequest = request.getRequestURI().equals(welcomeURL);
                boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");

                if(loggedIn || loginRequest || resourceRequest || registerRequest || welcomeRequest){
                    chain.doFilter(request,response);
                } else {
                    response.sendRedirect(welcomeURL);
                }
        }

        @Override
        public void destroy() {

        }
    }
