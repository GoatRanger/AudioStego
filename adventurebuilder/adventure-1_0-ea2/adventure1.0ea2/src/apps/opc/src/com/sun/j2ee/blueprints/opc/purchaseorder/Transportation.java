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
 $Id: Transportation.java,v 1.3 2003/03/11 20:26:19 brydon Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.util.*;

public class Transportation {

  protected String transportationId;
  protected String carrier;
  protected String origin;
  protected String destination;
  protected Date departureDate;
  protected String departureTime;
  protected float price;
  protected String travelClass;

  // Constructor
  public Transportation() {}

  public Transportation(String transportationId, String carrier, String origin,
                        String destination, Date departureDate,
                        String departureTime, float price, String travelClass) {
    this.transportationId = transportationId;
    this.carrier = carrier;
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
    this.price = price;
    this.travelClass = travelClass;
    this.departureTime = departureTime;

  }

  // getter methods
  public String getTransportationId() {
    return transportationId;
  }

  public String getCarrier() {
    return carrier;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public Date getDepartureDate() {
    return departureDate;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public float getPrice() {
    return price;
  }

  public String getTravelClass() {
    return travelClass;
  }

  // setter methods
  public void setTransportationId(String transportationId) {
    this.transportationId = transportationId;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public void setDepartureDate(Date departureDate) {
    this.departureDate = departureDate;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setTravelClass(String travelClass) {
    this.travelClass = travelClass;
  }
}
