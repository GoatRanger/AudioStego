/* Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 - Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
 
 - Redistribution in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in
   the documentation and/or other materials provided with the
   distribution.
 
 Neither the name of Sun Microsystems, Inc. or the names of
 contributors may be used to endorse or promote products derived
 from this software without specific prior written permission.
 
 This software is provided "AS IS," without a warranty of any
 kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 
 You acknowledge that Software is not designed, licensed or intended
 for use in the design, construction, operation or maintenance of
 any nuclear facility.
 $Id: ApplicationComponentManager.java,v 1.6 2003/03/11 22:24:50 gmurray Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.beans.Beans;
import java.util.HashMap;

// J2EE imports
import javax.servlet.*;

// Service Locator imports
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

// tracer import
import com.sun.j2ee.blueprints.util.tracer.Debug;

// WAF imports
import com.sun.j2ee.blueprints.waf.util.JNDINames;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.controller.web.URLMappingsXmlDAO;


/**
 *  Provides all the startup objects needed by the WAF which will
 *  be loaded when the application Servlet Context is started
 */
public class ApplicationComponentManager implements ServletContextListener {
    
    public ApplicationComponentManager() {}
    
    public void contextDestroyed(ServletContextEvent sce){
        // do nothing for destroying now
    }
    
    public void contextInitialized(ServletContextEvent sce){
        try {
            doInit(sce.getServletContext());
            getWebController(sce.getServletContext());
            getRequestProcessor(sce.getServletContext());
            getScreenFlowManager(sce.getServletContext());
        } catch (Throwable ex) {
            System.out.println("WAF ApplicaitonComponentManager Error Initializing:" + ex);
            throw new RuntimeException(ex);
        }
    }
    
    private void doInit(ServletContext context) {
        String requestMappingsURL = null;
        try {
            requestMappingsURL = context.getResource("/WEB-INF/mappings.xml").toString();
        } catch (java.net.MalformedURLException ex) {
            System.err.println("ApplicationComponentManager: initializing ScreenFlowManager malformed URL exception: " + ex);
        }
        HashMap urlMappings = URLMappingsXmlDAO.loadRequestMappings(requestMappingsURL);
        context.setAttribute(WebKeys.URL_MAPPINGS, urlMappings);
        HashMap eventMappings = URLMappingsXmlDAO.loadEventMappings(requestMappingsURL);
        context.setAttribute(WebKeys.EVENT_MAPPINGS, eventMappings);
    }
    
    public static WebController getWebController(ServletContext context) {
        ServiceLocator sl = ServiceLocator.getInstance();
        WebController wc =  (WebController)context.getAttribute(WebKeys.WEB_CONTROLLER);
        if ( wc == null ) {
            try {
                String wcClassName = sl.getString(JNDINames.DEFAULT_WEB_CONTROLLER);
                if ((wcClassName != null) && !wcClassName.toLowerCase().equals("none")) {
                    wc = (WebController) Class.forName(wcClassName).newInstance();
                    wc.init(context);
                }
            } catch (com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException slx) {
               // do nothing here
                // we do not require a Web Controller
            } catch (Exception exc) {
                throw new RuntimeException("Unable to create WebController: " + exc);
            }
        }
        return wc;
    }
    
    public static RequestProcessor getRequestProcessor(ServletContext context) {
        RequestProcessor rp = (RequestProcessor)context.getAttribute(WebKeys.REQUEST_PROCESSOR);
        if ( rp == null ) {
            rp = new RequestProcessor();
            rp.init(context);
            context.setAttribute(WebKeys.REQUEST_PROCESSOR, rp);
        }
        return rp;
    }
    
    public static ScreenFlowManager getScreenFlowManager(ServletContext context) {
        ScreenFlowManager screenManager = (ScreenFlowManager)context.getAttribute(WebKeys.SCREEN_FLOW_MANAGER);
        if (screenManager == null ) {
            screenManager = new ScreenFlowManager();
            screenManager.init(context);
            context.setAttribute(WebKeys.SCREEN_FLOW_MANAGER, screenManager);
        }
        return screenManager;
    }
}
