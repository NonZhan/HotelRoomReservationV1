

package MyTag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import org.apache.log4j.Logger;


public class IncorrectPassword extends BodyTagSupport
{
    private final static Logger log = Logger.getLogger(IncorrectPassword.class);
    private String text = "";
    
    IncorrectPassword(String text){
        this.text = text;
    }
   
    private void otherDoStartTagOperations() throws JspException
    {
       try
       {
           JspWriter out = pageContext.getOut();
           out.println(text);
       }
       catch (IOException x)
       {
           log.error(x);
       }
    }
    
    @Override
    public int doStartTag() throws JspException
    {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated())
        {
            return EVAL_BODY_BUFFERED;
        } else
        {
            return SKIP_BODY;
        }
    }
    
    private boolean theBodyShouldBeEvaluated()
    {
        return true;
    }
}