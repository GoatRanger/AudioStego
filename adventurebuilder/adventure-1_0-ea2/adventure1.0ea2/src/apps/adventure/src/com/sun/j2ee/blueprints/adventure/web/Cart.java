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
 $Id: Cart.java,v 1.6 2003/03/11 23:26:52 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web;

import java.util.*;

/**
 *  This class is used to represent the total package .
 */
public class Cart implements java.io.Serializable {
    
    private HashMap activities;
    private String packageId;
    private String lodgingId;
    private String destination;
    private String departureFlight;
    private String returnFlight;
    private String origin;
    private boolean configurationComplete = false;
    private int headCount;
    private int adventureDays =0;
    private int lodgingDays = 0;
    private int lodgingRoomCount = 0;
    private Calendar departureDate = Calendar.getInstance();
    private Calendar returnDate = Calendar.getInstance();
    
    /** Creates a new instance of AdventurePackage */
    public Cart() {
    }
    
    public void addActivity(String itemId) {
       addActivity(itemId,1);
    }
    
    public void addActivity(String itemId, int qty) {
        if (activities == null) {
            activities = new HashMap();
        }
        if (activities.containsKey(itemId)) {
            activities.remove(itemId);
        }    
        activities.put(itemId, new Integer(qty));    
    }
    
    public HashMap getActivities() {
        return activities;
    }
    
    public int getActivityCount() {
        if (activities == null) return 0;
        return activities.size();
    }
  
    public void setActivityHeadCount(String itemId,
                                    int quantity) {
        if ((activities != null) && activities.containsKey(itemId)) {
            activities.remove(itemId);
            if (quantity > 0) activities.put(itemId, new Integer(quantity));   
        }
    }
    
    public void setLodgingId(String lodgingId) {
        this.lodgingId = lodgingId;
    }
    
    public String getLodgingId() {
        return lodgingId;
    }
    
    public int getLodgingRoomCount() {
        return lodgingRoomCount;
    }
    
    public void setLodgingRoomCount(int lodgingRoomCount) {
        this.lodgingRoomCount = lodgingRoomCount;
    }
    
    public void setDepartureFlight(String departureFlight) {
        this.departureFlight = departureFlight;
    }
    
    public String getDepartureFlight() {
        return departureFlight;
    }
    
    public void setReturnFlight(String returnFlight) {
        this.returnFlight = returnFlight;
    }
    
    public String getReturnFlight() {
        return returnFlight;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getOrigin() {
       return origin;
    }
    
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    
    public String getPackageId() {
        return packageId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public int getHeadCount() {
        return headCount;
    }
    
    public void setAdventureDays(int adventureDays) {
        this.adventureDays = adventureDays;
        // set the lodging days to one less than the adventure days
        this.lodgingDays = adventureDays - 1;
    }
    
    public int getAdventureDays() {
        return adventureDays;
    }
    
    public int getLodgingDays() {
        return lodgingDays;
    }
    
    public void setLodgingDays(int lodgingDays) {
        this.lodgingDays = lodgingDays;
    }
    
    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
    
    public Collection getActivityIds() {
        if (activities == null) return null;
        return activities.keySet();
    }
    
    public Collection getValues() {
        if (activities == null) return null;
        return activities.values();
    }
    
    public Calendar getDepartureDate() {
        return departureDate;
    }
    
    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }
    
    public Calendar getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }
    
    public boolean isConfigurationComplete() {
        return configurationComplete;
    }
    
    public void setConfigurationComplete(boolean configurationComplete) {
        this.configurationComplete = configurationComplete;
    }
    
    public void clear() {
        packageId = null;
        activities = null;
        lodgingId = null;
        origin = null;
        headCount = 0;
        lodgingDays = 0;
        adventureDays = 0;
        returnFlight = null;
        departureFlight = null;
        configurationComplete = false;
    }
}
