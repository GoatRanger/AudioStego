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
 $Id: CreateSignOnHTMLAction.java,v 1.10 2003/03/07 02:14:25 inder Exp $ */

package com.sun.j2ee.blueprints.adventure.web.actions;

// j2ee imports
import javax.servlet.http.*;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;
import com.sun.j2ee.blueprints.signon.SignOnFacade;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

// adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;
import com.sun.j2ee.blueprints.adventure.web.exceptions.*;
/**
 * Handles responsibilities related to getting HTTP request 
 * info and making the calls to the signon component 
 * to access the database.
 */
public class CreateSignOnHTMLAction extends HTMLActionSupport {

    public static final String SIGNON_CREATE = "createSignOn";
    public static final String PASSWD_CHECK = "checkPassword";

    /**
     * Handles the http request to create an signon, and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
            
        Boolean result = new Boolean(false);

        // Delegate the work to access the signon component.
        result = new Boolean(createSignOn(request));
        if (result.booleanValue()) {
            String userId = request.getParameter("j_username");
            // put the userID in the session for future reference
            request.getSession().setAttribute(SignOnFilter.USER_NAME, userId);
        }

        // places result bean data in the session using the key that the
        // signonfilter will understand.
        request.getSession().setAttribute(SignOnFilter.SIGNED_ON_USER, result);
        // there is no need for an event because all processing was done here
        return null;
    }
    
        /**
     * Creates a new signon in the database
     */
    private boolean createSignOn(HttpServletRequest request)
                              throws SignOnException {
        try {
            String userId = request.getParameter("j_username");
            String password = request.getParameter("j_password");
            HttpSession session = request.getSession();
            AdventureComponentManager acm =
              (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            SignOnFacade facade = acm.getSignOnFacade(session);
            facade.createSignOn(userId, password);
            return true;
        // you may chose to catch each of the individual signon exceptions and pass on a
        // a more detailed message to the user here
        } catch (Exception e) {
            throw new SignOnException("SignOnHTMLAction:: Exception creating new signon: ", e);
        }
    }
}
