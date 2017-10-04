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
 $Id: TransportationBean.java,v 1.2 2003/03/05 23:14:56 vijaysr Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  TransportationBean .
 * TransportationBean is a CMP Entity Bean
 **/

public abstract class TransportationBean   implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(Transportation transportation) throws CreateException {
    setTransportationId(transportation.getTransportationId());
    setCarrier(transportation.getCarrier());
    setOrigin(transportation.getOrigin());
    setDestination(transportation.getDestination());
    setDepartureDate(transportation.getDepartureDate().getTime());
    setDepartureTime(transportation.getDepartureTime());
    setPrice(transportation.getPrice());
    setTravelClass(transportation.getTravelClass());

    return null;
  }

  public void ejbPostCreate(Transportation transportation) throws
      CreateException {

  }

  //getters and setters for CMP fields
  public abstract void setTransportationId(String transportationId);

  public abstract void setCarrier(String carrier);

  public abstract void setOrigin(String origin);

  public abstract void setDestination(String destination);

  public abstract void setDepartureDate(long departureDate);

  public abstract void setDepartureTime(String departureTime);

  public abstract void setPrice(float price);

  public abstract void setTravelClass(String travelClass);

  public abstract String getTransportationId();

  public abstract String getCarrier();

  public abstract String getOrigin();

  public abstract String getDestination();

  public abstract long getDepartureDate();

  public abstract String getDepartureTime();

  public abstract float getPrice();

  public abstract String getTravelClass();

  public Transportation getDetails() {

    Transportation transportation = new Transportation();
    transportation.setTransportationId(getTransportationId());
    transportation.setCarrier(getCarrier());
    transportation.setOrigin(getOrigin());
    transportation.setDestination(getDestination());
    transportation.setDepartureDate(new Date(getDepartureDate()));
    transportation.setDepartureTime(getDepartureTime());
    transportation.setPrice(getPrice());
    transportation.setTravelClass(getTravelClass());

    return transportation;
  }

  public void ejbRemove() throws RemoveException {

  }

  public void ejbLoad() {

  }

  public void ejbStore() {

  }

  public void ejbActivate() {

  }

  public void ejbPassivate() {

  }

  public void unsetEntityContext() {
    this.entityContext = null;
  }

  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }
}
