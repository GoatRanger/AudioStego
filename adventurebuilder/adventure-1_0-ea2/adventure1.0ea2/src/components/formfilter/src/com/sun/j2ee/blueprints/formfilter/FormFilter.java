package com.sun.j2ee.blueprints.formfilter;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormFilter implements Filter {

    private FilterConfig config = null;
    private String formBeanName = "formBean";
    
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String formBeanName = config.getInitParameter("form-bean-name");
        if (formBeanName != null) {
            this.formBeanName = formBeanName;
        }
    }
    
    public void destroy() {
        config = null;
        formBeanName = null;
    }

     public  void doFilter(ServletRequest srequest, ServletResponse  sresponse, FilterChain chain)
        throws IOException, ServletException {
            
        HttpServletRequest request = (HttpServletRequest)srequest;
        
        HashMap params = new HashMap();
        Enumeration enum = request.getParameterNames();
        // make a deep copy of the form data
        while (enum.hasMoreElements()) {
            String key = (String)enum.nextElement();
            String value = (String)request.getParameter(key);
            params.put(key,value);
        }
        //create a form bean
        FormBean bean = new FormBean(formBeanName, params);
        request.setAttribute(formBeanName);
        // do form validation here
        
        // move on to the next
       chain.doFilter(srequest,sresponse);  
    }

}
