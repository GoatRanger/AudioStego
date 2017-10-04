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
 $Id: FrontController.java,v 1.3 2002/11/13 23:58:07 brydon Exp $ */

package com.sun.j2ee.blueprints.customerapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * The single entry point for all web requests.
 */
public class FrontController extends HttpServlet {

    /** The mappings of URLs to actual JSP pages. */
    protected Map nameSpace = new HashMap();

    /** The handler that deals with accessing customer data. */
    protected AccountHandler accountHandler = null;


    // ------------------------------
    // Overridden HttpServlet methods
    // ------------------------------

    public void init() {
        initPathMapping();
        accountHandler = new AccountHandler();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        process(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        process(req, resp);
    }


    // --------------
    // Helper methods
    // --------------

    /**
     * Processes the given HTTP request and provides an appropriate
     * response.
     */
    protected void process(HttpServletRequest req,
                           HttpServletResponse resp)
        throws IOException, ServletException {

        resp.setContentType("text/html");
        String responseURL = null;
         String fullURL = req.getRequestURI();
        // get the screen name
        String selectedURL = null;            
        int lastPathSeparator = fullURL.lastIndexOf("/") + 1;
        if (lastPathSeparator != -1) {
            selectedURL = fullURL.substring(lastPathSeparator, fullURL.length());
        }

        // If the given request should be handled by the account handler,
        // let it handle the request. It handles create account events 
        // and read account events
        // Otherwise, send the appropriate page to the client.
        if ( selectedURL.equals("createAccount.do") || 
             selectedURL.equals("readAccount.do") ) {
            try {
                accountHandler.handle(req, resp);
                responseURL = getResponseURL(selectedURL);
            }
            catch (AccountHandlerException ahe) {
                req.setAttribute("error_message", ahe.getMessage());
                responseURL = getResponseURL("error.do");
            }
        } else {
            responseURL = getResponseURL(selectedURL);
        }

        getServletConfig().getServletContext()
            .getRequestDispatcher(responseURL).forward(req, resp);
    }

    /**
     * Returns the page to which the given URL is mapped.
     */
    protected String getResponseURL(String u) {
        return (String) nameSpace.get(u);
    }

    /**
     * Initialize table which maps URLs to JSP pages.
     * Maps which page to show next for a request
     */
    protected void initPathMapping() {
        nameSpace.put("index.do", "/index.jsp");
        nameSpace.put("enteraccountdata.do", "/enteraccountdata.jsp");
        nameSpace.put("readaccountdata.do", "/readaccountdata.jsp");
        nameSpace.put("error.do", "/error.jsp");

        // When a create account request form is sent via POST, 
        //the /createAccount URL is requested.
        nameSpace.put("createAccount.do", "/showaccount.jsp");

        // When a read account request form is sent via POST, 
        //the /readAccount URL is requested.
        nameSpace.put("readAccount.do", "/showaccount.jsp");
    }
}
