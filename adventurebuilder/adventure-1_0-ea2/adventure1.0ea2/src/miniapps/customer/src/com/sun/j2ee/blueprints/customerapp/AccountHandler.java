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
 $Id: AccountHandler.java,v 1.11 2003/03/07 02:14:28 inder Exp $ */

package com.sun.j2ee.blueprints.customerapp;

import javax.servlet.http.*;
import com.sun.j2ee.blueprints.customer.*;
import com.sun.j2ee.blueprints.customer.dao.*;

/**
 * Handles responsibilities related to getting HTTP request
 * info and making the calls to the customer account component
 * to access the database.
 */
public class AccountHandler {
    
    public static final String ACCOUNT_READ_ACTION = "readAccount";
    public static final String ACCOUNT_CREATE_ACTION = "createAccount";
    
    public AccountHandler() {
    }
    /**
     * Handles the http request to create an account, and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp)
    throws AccountHandlerException {
        ResultBean resultBean = null;
        
        //determine which type of request to process
        String targetAction = req.getParameter("target_action");
        
        if (targetAction.equals(ACCOUNT_READ_ACTION)) {
            
            //request parameter for read  requests
            String userId = req.getParameter("acct_read_userId");
            
            //validate the input data
            validate(userId);
            
            //access customer component and retrieve account data from the database
            resultBean = readAccount(userId);
            
            // place a parameter which keeps track of the
            //requested action in the request
            req.setAttribute("action", "read");
        } else if (targetAction.equals(ACCOUNT_CREATE_ACTION)) {
            
            //request parameters for create  requests
            String userId = req.getParameter("acct_userId");
            String familyName = req.getParameter("acct_familyName");
            String givenName = req.getParameter("acct_givenName");
            String telephone = req.getParameter("acct_telephone");
            String email = req.getParameter("acct_email");
            String street1 = req.getParameter("acct_street1");
            String street2 = req.getParameter("acct_street2");
            String city = req.getParameter("acct_city");
            String state = req.getParameter("acct_state");
            String zipCode = req.getParameter("acct_zipCode");
            String country = req.getParameter("acct_country");
            
            //validate the input data
            validate(userId,familyName,givenName, telephone, email, street1, street2, city, state, zipCode, country);
            
            // Access the customer component and create a new account
            resultBean = createAccount(userId, familyName,givenName, telephone, email, street1, street2, city, state, zipCode, country );
            
            // place a parameter which keeps track of the
            //requested action in the request
            req.setAttribute("action", "create");
        }
        
        // places result bean data in the request
        req.setAttribute("result", resultBean);
    }
    
    /**
     * Create a new customer account in the database
     */
    public ResultBean createAccount(String userId, String familyName,
    String givenName, String telephone,
    String email, String street1,
    String street2, String city,
    String state, String zipCode,
    String country )
    throws AccountHandlerException {
        
        //create the Transfer Object
        Address address =
        new Address(street1, street2, city, state, zipCode, country);
        ContactInformation info =
        new ContactInformation(familyName, givenName,telephone, email, address);
        Account acct = new Account(userId, info);
        
        //call customer component
        try {
            CustomerFacade customerFacade = new CustomerFacade();
            customerFacade.createAccount(acct);

            // Catch customer component exceptions and re-throw them as
            // mini-app application defined exceptions.
        } catch (Exception e) {
            throw new AccountHandlerException(e);
        }
        
        //return back same data as input
        return new ResultBean(userId, info);
    }
    
    /**
     * Access customer component and retrieve account data from the database
     */
    public ResultBean readAccount(String userId) throws AccountHandlerException {
        Account acct = null;
        
        //call customer component
        try {
            CustomerFacade customerFacade = new CustomerFacade();
            acct = customerFacade.getAccount(userId);
        }

        // Catch customer component exceptions and re-throw them as
        // mini-app application defined exceptions.
        catch (Exception e) {
            throw new AccountHandlerException(e);
        }
        
        return new ResultBean(acct.getUserId(), acct.getContactInformation());
    }
    
    /**
     * Validate the given feedback.
     */
    
    protected void validate(String userId) throws AccountHandlerException{
        if ((userId == null) || userId.trim().length() == 0) {
            throw new AccountHandlerException("The field UserId must have data. Your request has not been sent.");
        }
    }
    protected void validate(String userId,String familyName,String givenName, String telephone, String email, String street1, String street2,String  city, String state, String zipCode, String country ) throws AccountHandlerException {
        String emptyFields ="";
        String separator = " , ";
        if ((userId == null) || userId.trim().length() == 0) {
            emptyFields  = emptyFields +"UserId"+separator;
        }
        if ((givenName== null) || givenName.trim().length() == 0) {
            emptyFields  = emptyFields +"First Name"+separator;
        }
        if ((familyName== null) || familyName.trim().length() == 0) {
            emptyFields  = emptyFields +"Last Name"+separator;
        }
        if ((street1== null) || street1.trim().length() == 0) {
            emptyFields  = emptyFields +"Street Address"+separator;
        }
        if ((city== null) || city.trim().length() == 0) {
            emptyFields  = emptyFields +"City"+separator;
        }
        if ((state== null) || state.trim().length() == 0) {
            emptyFields  = emptyFields +"State"+separator;
        }
        if ((zipCode== null) || zipCode.trim().length() == 0) {
            emptyFields  = emptyFields +"Zip Code"+separator;
        }
        if ((country== null) || country.trim().length() == 0) {
            emptyFields  = emptyFields +"Country"+separator;
        }
        if ((telephone== null) || telephone.trim().length() == 0) {
            emptyFields  = emptyFields +"Telephone"+separator;
        }
        if ((email== null) || email.trim().length() == 0) {
            emptyFields  = emptyFields +"Email"+separator;
        }
        if(emptyFields .trim().length() != 0){
            throw new AccountHandlerException("The field(s) "+emptyFields.substring(0,emptyFields.length()-2) +" must have data. Your request has not been sent.");
        }
    }
}
