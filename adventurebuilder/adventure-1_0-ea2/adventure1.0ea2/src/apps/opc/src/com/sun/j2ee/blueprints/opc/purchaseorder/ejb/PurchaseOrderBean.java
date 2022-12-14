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
 $Id: PurchaseOrderBean.java,v 1.3 2003/03/08 03:09:08 sk140329 Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

/**
 * Implementation class for the  PurchaseOrderBean .
 * PurchaseOrderBean is a CMP Entity Bean representing
 * a purchase order . It has a  1:1  relationship
 * with  ContactInfoBean , CreditCardBean, LodgingBean,
 * TransportationBean and a 1:many relationship with
 * ActivityBean
 **/

public abstract class PurchaseOrderBean  implements EntityBean {

  private EntityContext entityContext = null;

  public String ejbCreate(PurchaseOrder po) throws CreateException {

    setPoId(po.getPoId());
    setUserId(po.getUserId());
    setEmailId(po.getEmailId());
    setLocale(po.getLocale());
    setOrderDate(po.getOrderDate().getTime());
    setTotalPrice(po.getTotalPrice());
    setHeadCount(po.getHeadCount());
    setStartDate(po.getStartDate().getTime());
    setEndDate(po.getEndDate().getTime());
    setDepartureCity(po.getDepartureCity());

    return null;
  }

  public void ejbPostCreate(PurchaseOrder po) throws CreateException {

      try {
      ServiceLocator sl = new ServiceLocator();
     
      //set shipping and billing info 
      ContactInfoLocalHome cilh = (ContactInfoLocalHome) sl.getLocalHome(JNDINames.CINFO_EJB);
      ContactInfoLocal  cil = (ContactInfoLocal) cilh.create(po.getShippingInfo());
      setShippingInfo(cil);     
      cil = (ContactInfoLocal) cilh.create(po.getBillingInfo());
      setBillingInfo(cil);
  
      //set credit card
      CreditCardLocalHome cclh = (CreditCardLocalHome) sl.getLocalHome(
          JNDINames.CCARD_EJB);
      CreditCardLocal ccl = (CreditCardLocal) cclh.create(po.
          getCreditCard());
      setCreditCard(ccl);

      //set lodging
      if(po.getLodging() != null){
      LodgingLocalHome llh = (LodgingLocalHome) sl.getLocalHome(
          JNDINames.LDG_EJB);
      LodgingLocal ll = (LodgingLocal) llh.create(po.getLodging());
      setLodging(ll);
      }

      //set transportation
  if(po.getDepartureFlightInfo() != null){
      TransportationLocalHome tlh = (TransportationLocalHome) sl.getLocalHome(
          JNDINames.TRPN_EJB);
      TransportationLocal tl = (TransportationLocal) tlh.create(po. getDepartureFlightInfo());
      setDepartureFlightInfo(tl);
  }
  
  if(po.getReturnFlightInfo() != null){ 
  TransportationLocalHome tlh = (TransportationLocalHome) sl.getLocalHome(
          JNDINames.TRPN_EJB);
      TransportationLocal tl =(TransportationLocal) tlh.create(po.getReturnFlightInfo());
      setReturnFlightInfo(tl);
  }

      //set activities
  if(po.getActivities() != null){
      ActivityLocalHome alh = (ActivityLocalHome) sl.getLocalHome(JNDINames.ACTY_EJB);
      Activity[] activities = po.getActivities();      
      for(int i=0; i < activities.length; i++) {
        ActivityLocal al = (ActivityLocal) alh.create(activities[i]);
        addActivity(al);
      }
      }

    }
    catch (ServiceLocatorException se) {
      throw new CreateException(" Exception saving PO:" +
                                se.getMessage());
    }

  }

  //getters and setters for CMP  fields
  public abstract void setPoId(String poId);

  public abstract void setUserId(String userId);

  public abstract void setEmailId(String emailId);

  public abstract void setLocale(String locale);

  public abstract void setOrderDate(long orderDate);

  public abstract void setTotalPrice(float totalPrice);

  public abstract void setHeadCount(int headCount);

  public abstract void setStartDate(long startDate);

  public abstract void setEndDate(long endDate);

  public abstract void setDepartureCity(String departureCity);

  public abstract String getPoId();

  public abstract String getUserId();

  public abstract String getEmailId();

  public abstract String getLocale();

  public abstract long getOrderDate();

  public abstract float getTotalPrice();

  public abstract int getHeadCount();

  public abstract long getStartDate();

  public abstract long getEndDate();

  public abstract String getDepartureCity();

  //getters and setters for CMR fields
  public abstract void setShippingInfo(ContactInfoLocal shippingInfo);

  public abstract void setCreditCard(CreditCardLocal creditCard);

  public abstract void setLodging(LodgingLocal lodging);

  public abstract void setBillingInfo(ContactInfoLocal billingInfo);

  public abstract void setDepartureFlightInfo(TransportationLocal
                                              departureFlightInfo);

  public abstract void setReturnFlightInfo(TransportationLocal returnFlightInfo);

  public abstract void setActivities(Collection activities);

  public abstract ContactInfoLocal getShippingInfo();

  public abstract CreditCardLocal getCreditCard();

  public abstract LodgingLocal getLodging();

  public abstract ContactInfoLocal getBillingInfo();

  public abstract TransportationLocal getDepartureFlightInfo();

  public abstract TransportationLocal getReturnFlightInfo();

  public abstract Collection getActivities();

  public void addActivity(ActivityLocal activity) {
    getActivities().add(activity);
  }

  public PurchaseOrder getPO() {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setPoId(getPoId());
    purchaseOrder.setUserId(getUserId());
    purchaseOrder.setEmailId(getEmailId());
    purchaseOrder.setOrderDate(new Date(getOrderDate()));
    purchaseOrder.setLocale(getLocale());
    purchaseOrder.setTotalPrice(getTotalPrice());
    purchaseOrder.setBillingInfo(getBillingInfo().getDetails());
    purchaseOrder.setShippingInfo(getShippingInfo().getDetails());
    purchaseOrder.setCreditCard(getCreditCard().getDetails());
    Collection activities = getActivities(); 
    if(activities != null){   
    Activity[] acts = new Activity[activities.size()];
    int i = 0;
    for (Iterator iter = activities.iterator(); iter.hasNext(); i++) {
      ActivityLocal activity = (ActivityLocal) iter.next();
      acts[i] = activity.getDetails();
    }
    purchaseOrder.setActivities(acts);
    }
    purchaseOrder.setHeadCount(getHeadCount());
    purchaseOrder.setStartDate(new Date(getStartDate()));
    purchaseOrder.setEndDate(new Date(getEndDate()));
    purchaseOrder.setDepartureCity(getDepartureCity());
    if(getDepartureFlightInfo() != null)
    purchaseOrder.setDepartureFlightInfo(getDepartureFlightInfo().getDetails());
    if(getReturnFlightInfo() != null)
    purchaseOrder.setReturnFlightInfo(getReturnFlightInfo().getDetails());
    if(getLodging() != null)
    purchaseOrder.setLodging(getLodging().getDetails());

    return purchaseOrder;
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
