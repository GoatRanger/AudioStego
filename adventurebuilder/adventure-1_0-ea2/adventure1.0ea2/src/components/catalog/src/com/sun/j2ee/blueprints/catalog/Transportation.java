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
 $Id: Transportation.java,v 1.2 2003/03/12 22:44:58 gmurray Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.io.Serializable;

/**
 * This class represents a Transportation in the Adventure Builder.
 * It has attributes like name, description etc.
 */
public class Transportation implements Serializable {
    
    protected String transportationId;
    protected String name;
    protected String description;
    protected String imageURI;
    protected String origin;
    protected String destination;
    protected String carrier;
    protected String departureTime;
    protected String arrivalTime;
    protected double price;
    protected String travelClass;
    
    public Transportation() { }
    
    public Transportation(String transportationId,
    String name,
    String description,
    String imageURI,
    double price,
    String origin,
    String destination,
    String carrier,
    String departureTime,
    String arrivalTime,
    String travelClass) {
        
        this.transportationId = transportationId;
        this.name = name;
        this.description = description;
        this.imageURI = imageURI;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelClass = travelClass;
    }
    
    public String getTransportationId() {
        return transportationId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getImageURI() {
        return imageURI;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getOrigin() {
        return origin;
    }
    public String getDestination() {
        return destination;
    }
    public String getCarrier() {
        return carrier;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public String getTravelClass() {
        return travelClass;
    }
    
    
    public String toString() {
        return "Transportation[id=" + transportationId +
        ", name=" + name +
        ", price=" + price +
        ", description=" + description +
        ", imageURI=" + imageURI +
        ", origin=" + origin +
        ", destination=" + destination +
        ", carrier=" + carrier +
        ", departure time=" + departureTime +
        ", arrival time =" + arrivalTime +
        ", travel class =" + travelClass +
        "]";
    }
}
