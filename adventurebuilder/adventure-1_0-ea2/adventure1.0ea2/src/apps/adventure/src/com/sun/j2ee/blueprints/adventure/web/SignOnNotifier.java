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
 $Id: SignOnNotifier.java,v 1.2 2003/03/10 23:06:29 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web;

import java.beans.Beans;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;

// signon component
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// customer component imports
import com.sun.j2ee.blueprints.customer.*;

// adventure imports
import com.sun.j2ee.blueprints.adventure.web.actions.*;

/** 
 * This class will bind with the current session and notify the Adventure Builder
 * Back end when a SignOn has occured.
 *
 * This allows for a loose coupling of the SignOn component and the 
 * Adventure Builder Application.  Ensure the neccessary setup is  
 * done when a user signs in.
 */
public class SignOnNotifier  
   implements java.io.Serializable, HttpSessionAttributeListener {


    public SignOnNotifier() { }

    // do nothing
    public void attributeRemoved(HttpSessionBindingEvent se) {}
    
    /**
     *
     * Process an attribute added
     *
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
        processEvent(se);
    }

    /**
     * Process the update
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
        processEvent(se);
    }
    
    private void processEvent(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        String name = se.getName();
        AdventureComponentManager acm =
            (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        // Delegate the work to access the customer component.
        CustomerFacade facade = acm.getCustomerFacade(session);
        /* check if the value matches the signon attribute
         * if a macth fire off an event to the ejb tier that the user
         * has signed on and load the account for the user
         */
        if (name.equals(SignOnFilter.SIGNED_ON_USER)) {
            boolean aSignOn  = ((Boolean)se.getValue()).booleanValue();
            if (aSignOn) {
                CustomerHTMLAction action = new CustomerHTMLAction();
                CustomerBean bean = null;
                try {
                    bean = action.readAccount(session, facade);
                } catch (Exception cex) {
                    cex.printStackTrace();
                    // log message
                }
                session.setAttribute(AdventureKeys.CUSTOMER_BEAN, bean);
            }
        }
    }

}


