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
 $Id: CartFlowHandler.java,v 1.4 2003/03/10 20:55:40 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web.handlers;

import java.io.*;
import java.util.*;


// J2EE imports 
import javax.servlet.http.HttpServletRequest;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.*;

//adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;

/**
 * This class forwards the application to the correct page following a
 * package action.
 *
*/
public class CartFlowHandler implements FlowHandler {

    public void doStart(HttpServletRequest request){
    }
    
    public String processFlow(HttpServletRequest request) 
        throws FlowHandlerException {

        String actionType= (String)request.getParameter("target_action");
        if (actionType == null) return "CART";
        // based on the target action that was processed forward
        // to the necessary screen
        if (actionType.equals("purchase_package")) {
            return "ENTER_PACKAGE_DETAILS";
        } else if (actionType.equals("set_package_details")) {
            return "SELECT_TRANSPORT";
        } else if (actionType.equals("update_package_details")) {
            return "CART";
        } else if (actionType.equals("update_activities")) {
            return "CART-ACTIVITIES";
         } else if (actionType.equals("purchase_activities")) {
            return "CART-ACTIVITIES";
        } else if (actionType.equals("update_lodging_room_count")) {
            return "CART-LODGING";
        } else if (actionType.equals("purchase_lodging")) {
            return "CART-LODGING";
        } else if (actionType.equals("cancel_return_flight")) {
            return "CART_TRANSPORT";
        } else if (actionType.equals("cancel_departure_flight")) {
            return "CART_TRANSPORT";
         } else if (actionType.equals("purchase_transportation")) {
            return "CART_TRANSPORT";
        } else if (actionType.equals("no_transportation")) {
            return "CART";
        } else if (actionType.equals("purchase_activity")) {
            return "CART-ACTIVITIES";        
        } else if (actionType.equals("cancel")) {
            return "CANCEL";        
        } else {
            return "CART"; 
        }
    }
    
    
    public void doEnd(HttpServletRequest request) {
    }

}

