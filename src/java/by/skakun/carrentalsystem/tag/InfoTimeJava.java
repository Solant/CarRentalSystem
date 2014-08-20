
package by.skakun.carrentalsystem.tag;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Skakun
 */

public class InfoTimeJava extends TagSupport{
        private static final Logger LOG = Logger.getLogger(InfoTimeJava.class.getSimpleName());
             String str;

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }


 @Override
 public int doStartTag(){
     
     try {
         GregorianCalendar gc = new GregorianCalendar();
         String time = "<hr/> Time: <b>" + gc.getTime() + "</b> <hr/>";
         String locale = "Locale: <b>" + Locale.getDefault() + "</b> <hr/>";
         JspWriter out = pageContext.getOut();
         out.write(time + locale);
         return SKIP_BODY;
     } catch (IOException ex) {
         LOG.info("IOException while InfoTimeJave.doStartTag()");
     }
     return SKIP_BODY;
 }   
        @Override
 public int doEndTag() {
     return EVAL_PAGE;
 }
    
}
