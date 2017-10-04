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
 $Id: TransportSearchHTMLAction.java,v 1.5 2003/03/12 22:44:29 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web.actions;

import java.io.*;
import java.util.*;

// j2ee imports
import javax.servlet.http.*;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.*;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

//adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;

// Catalog imports
import com.sun.j2ee.blueprints.catalog.*;
import com.sun.j2ee.blueprints.catalog.dao.*;

/**
 * Implementation of HTMLAction that processes a
 * user change in language.
 */

public final class TransportSearchHTMLAction extends HTMLActionSupport {   

    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
            
            HttpSession session = request.getSession();
             // look up the adventure transportation
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            String origin = request.getParameter("origin");
            // if we are doing a search for a different flight from the cart page
            if (origin == null) {
                origin = cart.getOrigin();
            } else {
                cart.setOrigin(origin);
            }
             
            ArrayList transpDepartureBeans = null;
            ArrayList transpReturnBeans = null;
            
            String noTransport = request.getParameter("no_transport");
            String showTransport = request.getParameter("show_flights");
            Locale locale = new Locale("en","us");
            String destination = cart.getDestination();
            //access catalog component and retrieve  data from the database
            transpDepartureBeans = searchTransportation(origin, destination, locale);
            transpReturnBeans = searchTransportation(destination, origin, locale);
            
            // places result bean data in the request
            request.setAttribute("departure_result", transpDepartureBeans );
            request.setAttribute("return_result", transpReturnBeans );
            request.setAttribute("search_target","transportation");
        return null; 
    }
    
        /**
     * Access catalog component and retrieve transportation data from the database
     */
    public ArrayList searchTransportation(String origin, String destination ,Locale locale) throws HTMLActionException {
        ArrayList transportation = null;
        ArrayList transportationBean = new ArrayList();
        
        //call catalog component
        try {
            CatalogFacade catalogFacade = new CatalogFacade();            
            transportation = catalogFacade.getTransportations(origin, destination, locale);
        
        // Catch catalog exceptions and re-throw them as
        // mini-app application defined exceptions.
        } catch (Exception e) {
            throw new HTMLActionException("Transportation Search Exception:: Catalog Exception accessing catalog component: " + e);
        }
        for(int i=0;i<transportation.size() ;++i){
            transportationBean.add(new TransportationBean(((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getTransportationId(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getName(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDescription(),
                                                            ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getImageURI(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getPrice(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getOrigin(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDestination(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getCarrier(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDepartureTime(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getArrivalTime(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getTravelClass()));
        }
        return transportationBean ;
    }
}
    
