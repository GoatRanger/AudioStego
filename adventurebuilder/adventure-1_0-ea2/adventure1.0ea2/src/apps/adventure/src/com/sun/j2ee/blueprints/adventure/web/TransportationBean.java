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
 $Id: TransportationBean.java,v 1.2 2003/03/12 22:44:29 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web;

import java.io.Serializable;
import com.sun.j2ee.blueprints.catalog.*;


/**
 * A JavaBeans component representing transportation details.
 */
public class TransportationBean extends Transportation implements Serializable {
    
    
    public TransportationBean(String transportationId,
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
        
        super(transportationId, name, description, imageURI, price, origin,
        destination, carrier, departureTime, arrivalTime, travelClass);
        
    }
    public TransportationBean() {
        
    }
    
    //getter methods are provided in the base class
    
    //setter methods
    
    public void setId(String transportationId) {
        this.transportationId = transportationId;
    }
    
    public String getId() {
        return transportationId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setDestination(String destination) {
        this.destination= destination;
    }
    public void setCarrier(String carrier) {
        this.carrier= carrier;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime= departureTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime= arrivalTime;
    }
    public void setTravelClass(String travelClass) {
        this.travelClass= travelClass;
    }
    
    
    public String toString() {
        return "Transportation[id=" + getTransportationId() +
        ", name=" + getName() +
        ", price=" + getPrice() +
        ", description=" + getDescription() +
        ", origin=" + getOrigin() +
        ", destination=" + getDestination()+
        ", carrier=" + getCarrier() +
        ", departure time=" + getDepartureTime() +
        ", arrival time =" + getArrivalTime() +
        ", travel class =" + getTravelClass() +
        "]";
    }
    
}
