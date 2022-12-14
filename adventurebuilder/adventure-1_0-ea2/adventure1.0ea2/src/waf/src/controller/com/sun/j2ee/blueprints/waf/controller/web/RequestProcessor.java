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
 $Id: RequestProcessor.java,v 1.5 2003/03/06 04:19:25 gmurray Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.*;

// J2ee Imports
import javax.servlet.*;

// WAF imports
import com.sun.j2ee.blueprints.waf.util.JNDINames;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.controller.*;
// tracer import
import com.sun.j2ee.blueprints.util.tracer.Debug;

/** 
 * This is the web tier controller for the sample application.
 *
 * This class is responsible for processing web requests 
 * that could originate from any number of front controllers
 *
 * This class is responsible for ensuring that the dependencies
 * of the client accessing the controller prior to the being
 * passed off to the WebController.
 * 
 */
public class RequestProcessor implements java.io.Serializable {

    private ServletContext context;
    private HashMap urlMappings;
    private HashMap eventMappings;
    private HashMap actionMap;

    public RequestProcessor() {
        actionMap = new HashMap();
    }


    public void init(ServletContext context) {
        this.context = context;
        urlMappings = (HashMap)context.getAttribute(WebKeys.URL_MAPPINGS);
        eventMappings = (HashMap)context.getAttribute(WebKeys.EVENT_MAPPINGS);
    }
    
    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the HTMLAction that is needed to
     * process a request, and the HTMLAction that is needed
     * to insure that the propper screen is displayed.
    */
    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }

    /**
     * The EventMapping object contains information that will match
     * a event class name to an EJBActionClass.
     *
    */
    private EventMapping getEventMapping(Event eventClass) {
        // get the fully qualified name of the event class
        String eventClassName = eventClass.getClass().getName();
        if ((eventMappings != null) && eventMappings.containsKey(eventClassName)) {
            return (EventMapping)eventMappings.get(eventClassName);
        } else {
            return null;
        }
    }

    /**
    * This method is the core of the RequestProcessor. It receives all requests
    *  and generates the necessary events.
    */
    public void processRequest(URLMapping urlMapping, ServletRequest request) 
         throws ActionException, EventException, ServletException {
        Event ev = null;
        Action action = getAction(urlMapping);
        if (action != null) {
            action.setServletContext(context);
            action.doStart(request);
            ev = action.perform(request);
            EventResponse eventResponse = null;
            if (ev != null) {
               // set the command class name on the event
                EventMapping eventMapping = getEventMapping(ev);
                if (eventMapping != null) {
                    ev.setCommandClassName(eventMapping.getCommandClassName());
                }
               WebController wc = (WebController)context.getAttribute(WebKeys.WEB_CONTROLLER);
         eventResponse  = wc.handleEvent(ev, request);
           }
           action.doEnd(request, eventResponse);
        }
    }
    
    /**
     * This method load the necessary Action class necessary to
     * process a the request for the specified URL. Action instances
     * are cached so that they may be re-used.
     */
    private Action getAction(URLMapping urlMapping) {
        Action handler = null;
        if (urlMapping != null) {      
            if (urlMapping.isAction()) {
                String actionClassString = urlMapping.getWebAction();
                if ((actionClassString != null) &&
                    actionMap.containsKey(actionClassString)) {
                        handler = (Action)actionMap.get(actionClassString);
                } else {
                    try {
                        handler = (Action)getClass().getClassLoader().loadClass(actionClassString).newInstance();
                        actionMap.put(actionClassString, handler);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Debug.print("RequestProcessor caught loading action: " + ex);          
                    }
                }
            }
        }
        return handler;
    }
}

