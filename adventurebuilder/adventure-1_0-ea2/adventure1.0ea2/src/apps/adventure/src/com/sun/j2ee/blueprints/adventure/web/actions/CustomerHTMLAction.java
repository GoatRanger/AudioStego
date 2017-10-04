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
 $Id: CustomerHTMLAction.java,v 1.11 2003/03/10 23:32:19 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web.actions;

import javax.servlet.http.*;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

// customer component imports
import com.sun.j2ee.blueprints.customer.*;

// adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;
import com.sun.j2ee.blueprints.adventure.web.exceptions.CustomerException;

/**
 * Handles responsibilities related to getting HTTP request
 * info and making the calls to the customer account component
 * to access the database.
 */
public final class CustomerHTMLAction extends HTMLActionSupport {
    
    public static final String ACCOUNT_READ_ACTION = "readAccount";
    public static final String ACCOUNT_CREATE_ACTION = "createAccount";
    
    /**
     * Handles the http request to create an account, and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request)
        throws HTMLActionException {
        
        CustomerBean resultBean = null;
        
        //determine which type of request to process
        String targetAction =request.getParameter("target_action");
        // get a handle on the Adventure Component Manager
        HttpSession session = request.getSession();
        AdventureComponentManager acm =
        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        // Delegate the work to access the customer component.
        CustomerFacade facade = acm.getCustomerFacade(session);
        if ((targetAction != null) &&
               targetAction.equals(ACCOUNT_CREATE_ACTION)) {
            resultBean = createAccount(request, facade);
        } else {
            Boolean signedOn = (Boolean)request.getSession().getAttribute(SignOnFilter.SIGNED_ON_USER);
            //FOR READ REQUESTS
            if ((signedOn != null) && signedOn.booleanValue()) {
                // Delegate the work to access the customer component.
                resultBean = readAccount(session,facade);
            } else {
                throw new CustomerException("CustomerHTMLAction: User is not signed on.");
            }
        }
        // places result bean data in the response.
        session.setAttribute(AdventureKeys.CUSTOMER_BEAN, resultBean);
        return null;
    }
    
    /**
     * Validates the given feedback.
     */
    protected void validate(String userId)
        throws CustomerException {
        if ((userId == null) || userId.trim().length() == 0) {
            throw new CustomerException("Unfortunately, there was a problem: The userId must have data. Your request has not been sent.");
        }
    }
    
    /**
     * Access customer component and retrieve account data in the database
     */
    public CustomerBean readAccount(HttpSession session,
                                                                       CustomerFacade facade)
            throws CustomerException {
        
        String userId = (String)session.getAttribute(SignOnFilter.USER_NAME);
        Account acct = null;
        //call customer component
        try {
            acct = facade.getAccount(userId);
            // Catch customer component exceptions and re-throw them as
            // app application defined exceptions
        }catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("CustomerHTMLAction:: CustomerAppException accessing Customer Component: ", e);
        }
        
        return new CustomerBean(acct);
    }
    
    /**
     * Creates a new customer account
     */
    private CustomerBean createAccount(HttpServletRequest request,
                                                                             CustomerFacade facade)
        throws CustomerException {
        
        String userId = (String)request.getSession().getAttribute(SignOnFilter.USER_NAME);
        String familyName =request.getParameter("acct_familyName");
        String givenName =request.getParameter("acct_givenName");
        String telephone =request.getParameter("acct_telephone");
        String email =request.getParameter("acct_email");
        String street1 =request.getParameter("acct_street1");
        String street2 =request.getParameter("acct_street2");
        String city =request.getParameter("acct_city");
        String state =request.getParameter("acct_state");
        String zipCode =request.getParameter("acct_zipCode");
        String country =request.getParameter("acct_country");
        
        com.sun.j2ee.blueprints.customer.Address address =
        new com.sun.j2ee.blueprints.customer.Address(street1, street2, city, state, zipCode, country);
        com.sun.j2ee.blueprints.customer.ContactInformation info =
        new com.sun.j2ee.blueprints.customer.ContactInformation(familyName, givenName,telephone,
        email, address);
        
        com.sun.j2ee.blueprints.customer.Account acct = new com.sun.j2ee.blueprints.customer.Account(userId, info);
        
        //call customer component
        try {
            facade.createAccount(acct);
            // Catch customer component exceptions and re-throw them as
            // app application defined exceptions.
        } catch (Exception e) {
            throw new CustomerException("CustomerBD:: CustomerAppException Error Creating Customer", e);
        }
        
        //return back same data as input
        return new CustomerBean(acct);
    }
}
