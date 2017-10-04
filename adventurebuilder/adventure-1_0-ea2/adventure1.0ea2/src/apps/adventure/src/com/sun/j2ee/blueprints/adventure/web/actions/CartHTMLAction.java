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
 $Id: CartHTMLAction.java,v 1.8 2003/03/11 23:26:53 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web.actions;


import java.io.IOException;
import java.util.*;

// j2ee imports
import javax.servlet.http.*;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

//adventure imports
import com.sun.j2ee.blueprints.adventure.web.*;

// Catalog imports
import com.sun.j2ee.blueprints.catalog.*;

/**
 * Implementation of HTMLAction that processes a
 * user change in language.
 */

public final class CartHTMLAction extends HTMLActionSupport {   

    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
        
        String actionType= (String)request.getParameter("target_action");
        HttpSession session = request.getSession();
        if (actionType == null) {
            getResultBean(request);
            return null;
        }
        if (actionType.equals("purchase_package")) {
            String packageId = request.getParameter("package_id").trim();
            // look up the adventure package
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart ac = acm.getCart(session);
            // clear the cart
            ac.clear();
            CatalogFacade facade = acm.getCatalogFacade(session);
            try {
                // locale will change after beta
                Locale locale = new Locale("en","us");
                AdventurePackage pack = facade.getAdventurePackage(packageId, locale);
                // put the info into the Adventure Cart
                ac.setPackageId(packageId);
                ac.setLodgingId(pack.getLodgingId());
                ac.setDestination(pack.getLocation());
                 Iterator it = pack.getActivities().iterator();
                 // add the activities and set default people to 1
                 while (it.hasNext()) {
                     String activityId = (String)it.next();
                     ac.addActivity(activityId.trim(), 1);
                 }
            } catch (Exception ex) {
                ex.printStackTrace();
            }   
        } else if (actionType.equals("purchase_activities")) {
            purchaseActivities(request);
            getResultBean(request);
        } else if (actionType.equals("purchase_activity")) {
            String activityId = request.getParameter("activity_id");
            String headCountString = request.getParameter("head_count");
            // don't do anything if a hotel was not specified
            if (activityId == null && headCountString != null) {
                getResultBean(request);
                return null;
            }
            activityId = activityId.trim();
            int headCount = Integer.parseInt(headCountString);
            // look up the cart
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.addActivity(activityId,headCount);
            getResultBean(request);
        } else if (actionType.equals("update_activities")) {
            updateActivityHeadCounts(request);
            getResultBean(request);
        } else if (actionType.equals("set_package_details")) {
            updatePackage(request);
            getResultBean(request);
        } else if (actionType.equals("update_package_details")) {
            updatePackage(request);
            getResultBean(request);
         } else if (actionType.equals("update_lodging_room_count")) {
            updatePackage(request);
            getResultBean(request);
        } else if (actionType.equals("purchase_lodging")) {
            String hotelId = request.getParameter("lodging_id");
            // don't do anything if a hotel was not specified
            if (hotelId == null) {
                getResultBean(request);
                return null;
            }
            hotelId = hotelId.trim();
            // look up the adventure package
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.setLodgingId(hotelId);
            // update the room count
            updatePackage(request);
            getResultBean(request);
       } else if (actionType.equals("no_transportation")) { 
           AdventureComponentManager acm = 
                  (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.setOrigin("None");
            cart.setConfigurationComplete(true);
            getResultBean(request);
        } else if (actionType.equals("purchase_transportation")) { 
            String departureFlight = request.getParameter("departure_flight");
            if (departureFlight != null) departureFlight = departureFlight.trim();
            String returnFlight = request.getParameter("return_flight");
            if (returnFlight != null) returnFlight = returnFlight.trim();
            // look up the adventure package
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.setDepartureFlight(departureFlight);
            cart.setReturnFlight(returnFlight);
            cart.setConfigurationComplete(true);
            getResultBean(request);
        } else if (actionType.equals("cancel_departure_flight")) {
             AdventureComponentManager acm = 
                  (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.setDepartureFlight(null);
        } else if (actionType.equals("cancel_return_flight")) {
             AdventureComponentManager acm = 
                  (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.setReturnFlight(null);
        } else if (actionType.equals("cancel")) {
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            cart.clear();
        } else if (actionType.equals("update")) {
            // not for beta
        }
        return null; 
    }
    
    private void updateActivityHeadCounts(HttpServletRequest request) {
         HttpSession session = request.getSession();
        // look up the cart
        AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        Cart cart = acm.getCart(session);
        HashMap params = (HashMap)request.getParameterMap();
        if (params == null) return;
        Collection keys = params.keySet();
        if (keys == null) return;
        Iterator it = keys.iterator();
        while(it.hasNext() ) {
            String key = (String)it.next();
            if (key.startsWith("headcount_")) {
                String id = key.substring("headcount_".length(), key.length());
                String value = request.getParameter(key);
                int qty = (new Integer(value)).intValue();
                cart.setActivityHeadCount(id,qty);
            }
        }
    }
    
    private void purchaseActivities(HttpServletRequest request) {
         HttpSession session = request.getSession();
        // look up the cart
        AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        Cart cart = acm.getCart(session);
        HashMap params = (HashMap)request.getParameterMap();
        if (params == null) return;
        Collection keys = params.keySet();
        if (keys == null) return;
        Iterator it = keys.iterator();
        while(it.hasNext() ) {
            String key = (String)it.next();
            if (key.startsWith("activity_")) {
                String id = key.substring("activity_".length(), key.length());
                String value = request.getParameter("head_count_" + id);
                id = id.trim();
                int qty = 1;
                if (value != null) qty = (new Integer(value)).intValue();
                System.out.println("Setting " + id + " with head count of " + qty);
                cart.addActivity(id,qty);
            }
        }
    }
    
    private void updatePackage(HttpServletRequest request) {
         HttpSession session = request.getSession();
        // look up the cart
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            String adventureDaysString = request.getParameter("adventure_days");
            if (adventureDaysString != null) {
                int adventureDays = (new  Integer(adventureDaysString)).intValue();
                cart.setAdventureDays(adventureDays);
            }
            String lodgingRoomCountString = request.getParameter("lodging_room_count");
            if (lodgingRoomCountString != null) {
                int lodingRoomCount = (new  Integer(lodgingRoomCountString)).intValue();
                cart.setLodgingRoomCount(lodingRoomCount);
            }
            String headCountString = request.getParameter("head_count");
            if (headCountString != null) {
                int headCount = (new  Integer(headCountString)).intValue();
                cart.setHeadCount(headCount);
            }      
    }
    
    private void getResultBean(HttpServletRequest request) 
            throws HTMLActionException {
        double grandTotal = 0;
        double cartTotal = 0;
        double lodgingTotal = 0;
        double activityTotal = 0;
        double departureTotal = 0;
        double returnTotal = 0;
        double transportationTotal = 0;
        int adventureDays =0;
        int lodgingDays=0;
        int headCount = 0;
        int lodgingRoomCount = 1;
        
        HttpSession session = request.getSession();
        AdventureComponentManager acm = 
            (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        Cart cart = acm.getCart(session);

        CatalogFacade facade = acm.getCatalogFacade(session);
        try {
            // locale will change after beta
            Locale locale = new Locale("en","us");

            //assume the the user uses the same hotel for every night of the adventure
            lodgingDays = cart.getLodgingDays();
            // this needs to be multiplied by the number of rooms 
            lodgingRoomCount = cart.getLodgingRoomCount();
            com.sun.j2ee.blueprints.catalog.Lodging lodging = facade.getLodging(cart.getLodgingId(), locale);
            lodgingTotal = (lodgingDays * lodging.getPrice() * lodgingRoomCount);
            // add in the actvities
            HashMap activities = cart.getActivities();
            Iterator it = null;
            if (activities != null) it = activities.keySet().iterator();
            while ((it != null) && it.hasNext()) {
                String itemId = (String)it.next();
                Integer qty = (Integer)activities.get(itemId);
                com.sun.j2ee.blueprints.catalog.Activity item = facade.getActivity(itemId, locale);
                activityTotal += (qty.intValue() * item.getPrice());
            }
            headCount = cart.getHeadCount();
            if (cart.getDepartureFlight() != null) {
                com.sun.j2ee.blueprints.catalog.Transportation departureFlight =
                    facade.getTransportation(cart.getDepartureFlight(), locale);
                if (departureFlight != null) {
                    departureTotal = (departureFlight.getPrice() * headCount);
                }
            }
            if (cart.getReturnFlight() != null) {
                com.sun.j2ee.blueprints.catalog.Transportation returnFlight =
                    facade.getTransportation(cart.getReturnFlight(), locale);
                if (returnFlight != null) {
                    returnTotal =  (returnFlight.getPrice() * headCount);
                }
            }
            transportationTotal = departureTotal + returnTotal;
        } catch (Exception ex) {
            throw new HTMLActionException("CartHTMLAction error: " + ex);        
        }
        cartTotal = activityTotal + lodgingTotal + transportationTotal;
        CartBean bean = 
            new CartBean(cartTotal,
                                            lodgingTotal,
                                            activityTotal,
                                            transportationTotal,
                                            lodgingDays,
                                            adventureDays);
        request.setAttribute(AdventureKeys.CART_BEAN, bean);
    }
}

