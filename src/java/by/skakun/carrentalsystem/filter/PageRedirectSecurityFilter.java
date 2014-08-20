package by.skakun.carrentalsystem.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
   @WebFilter (urlPatterns = { "/jsp/*" },
   initParams = { @WebInitParam (name= "INDEX_PATH", value = "/index.jsp") } )


public class PageRedirectSecurityFilter implements Filter {
    static final Logger LOG = Logger.getLogger(CharacterEncodingFilter.class);
    private String indexPath;
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
  /*  @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Logger log = Logger.getLogger(AuthorizationFilter.class);
        try {
            if (request.getSession().getAttribute("user") != null || request.getRequestURI().equals("/login")) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (ServletException e) {
            log.log(Level.ERROR, "ServletException in AuthorizationFilter", e);
        } catch (IOException e) {
            log.log(Level.ERROR, "IOException in AuthorizationFilter", e);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    } */

   
}