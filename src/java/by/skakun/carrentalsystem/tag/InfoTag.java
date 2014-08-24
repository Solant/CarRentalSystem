package by.skakun.carrentalsystem.tag;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author apple
 */
public class InfoTag extends TagSupport implements Serializable {

    private String username;
    private String type;
    private String userMsg;
    private String typeMsg;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public void setTypeMsg(String typeMsg) {
        this.typeMsg = typeMsg;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(" <div class=\"infotag\">\n"
                    + "<b>" + username + 
                    "</b><br/>" + "<b>" 
            );
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write(type + "</b>\n</div>");
        } catch (IOException ex) {
           throw new JspTagException(ex.getMessage());
        }
        return EVAL_PAGE;
    }

}
