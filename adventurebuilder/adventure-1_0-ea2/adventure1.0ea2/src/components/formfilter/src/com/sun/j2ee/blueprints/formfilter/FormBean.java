package com.sun.j2ee.blueprints.formfilter;

import java.util.HashMap;
import java.io.Serializable;

// j2ee imports
import javax.servlet.http.HttpSession;

public class FormBean implements Serializable{
    
    private HashMap parameterMap;
    private String id;
    
    public FormBean(String id, HashMap parameterMap) {
        this.id = id;
        this.parameterMap = parameterMap;
    }
    
    public FormBean(HttpSession session, String id) {
        HashMap parameterMap = ((FormBean)session.getAttribute(id)).getParameters();
        this.parameterMap = parameterMap;
        this.id = id;
    }
    
    public HashMap getParameters() {
        return parameterMap;
    }
    
    public Collection getParameterNames() {
        return parameterMap.keySet();
    }
    
    public String getParameter(String parameterName) {
        return (String)parameterMap.get(parameterName);
    }
    
    public void serialize(HttpSession session, String id) {
        this.id = id;
        session.setAttribute(id, this);
    }
    
    // base 64 serialize to page necessary?

}
