package by.skakun.carrentalsystem.tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Skakun
 */
public class HelloTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(HelloTag.class.getSimpleName());

    private String role;

    
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    

    @Override
    public int doStartTag() {
        try {
            String to = null;
            if ("admin".equalsIgnoreCase(role)) {
                to = "Hello, " + role;
            } else {
                to = "Welcome, " + role;
            }
            pageContext.getOut().write("<hr/>" + to + "<hr/>");
        } catch (IOException ex) {
            LOG.info("IOException while InfoTimeJave.doStartTag()");
        }
        return SKIP_BODY;
    }
   

}
