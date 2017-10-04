/* @@copyright $Id: OrderTrackingHTMLAction.java,v 1.4 2003/03/10 23:06:29 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web.actions;

import javax.servlet.http.*;
import java.util.Collection;
import javax.naming.*;
import javax.xml.rpc.*;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

//adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;
import com.sun.j2ee.blueprints.adventure.web.exceptions.*;
// Catalog imports
import com.sun.j2ee.blueprints.catalog.*;


/**
 * Handles responsibilities related to getting HTTP request 
 * info and making the calls to process the action 
 */
public class OrderTrackingHTMLAction extends HTMLActionSupport{
   
    /**
     * Handles the http request and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
        
        String orderId = null;
        OrderDetails result = null;      
        orderId = request.getParameter("orderId");
        // put the orderId in the request to display in the screen
        request.setAttribute("orderTrackingId", orderId);
        try {
            result = this.getOrderDetails(orderId);
            if (result.getPO() == null) {
                throw new OrderNotFoundException("Order Not Found: " + orderId);
            }
            // places result bean data in the response.
            request.setAttribute("orderDetails", result);
        } catch(Exception ex) {
           throw new OrderNotFoundException("Handler error calling ordertracking endpoint " + ex);
        }
        return null;
    }

    /**
     * Accesses OrderTracking Web Service endpoint using Jax-rpc
     */
    private OrderDetails getOrderDetails(String orderId) throws Exception {
             
        Context ic = new InitialContext();
        OpcOrderTrackingService opcOrderTrackingSvc =
            (OpcOrderTrackingService) ic.lookup("java:comp/env/service/OpcOrderTrackingService");
        OrderTrackingIntf port = opcOrderTrackingSvc.getOrderTrackingIntfPort();
        ((Stub)port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY,
            "http://localhost:8000/webservice/OtEndpointEJB");
        return port.getOrderDetails(orderId);    
   }
}
