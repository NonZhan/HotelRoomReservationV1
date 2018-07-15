package com.zhan.app1.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IncorrectPassword extends BodyTagSupport {
    
    private String text = "";
    
    IncorrectPassword(String text){
        this.text = text;
    }
   
    private void otherDoStartTagOperations() throws JspException {
           try {
               JspWriter out = pageContext.getOut();
               out.println(text);
           } catch (IOException ex) {
               System.out.println("JustTag otherDoStartTagOperations()" + ex);
           }
    }
    
    @Override
    public int doStartTag() throws JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }
    
    private boolean theBodyShouldBeEvaluated() {
        return true;
    }

}