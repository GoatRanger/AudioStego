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
 $Id: PurchaseOrder.java,v 1.3 2003/03/11 20:26:19 brydon Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.util.*;

/**
 * This object is represents the purchase order
 * that is persisted after a user places an order.
 */
public class PurchaseOrder {

  protected String poId;
  protected String userId;
  protected String emailId;
  protected String locale;
  protected Date orderDate;
  protected ContactInfo shippingInfo;
  protected ContactInfo billingInfo;
  protected float totalPrice;
  protected CreditCard creditCard;
  protected int headCount;
  protected Date startDate;
  protected Date endDate;
  protected String departureCity;
  protected Activity[] activities;
  protected Lodging lodging;
  protected Transportation departureFlightInfo;
  protected Transportation returnFlightInfo;

  // Constructor
  public PurchaseOrder() {}

  public PurchaseOrder(String poId, String userId, String emailId,
                       String locale, Date orderDate,
                       ContactInfo shippingInfo, ContactInfo billingInfo,
                       float totalPrice, CreditCard creditCard, int headCount,
                       Date startDate, Date endDate, String departureCity,
                       Activity[] activities, Lodging lodging,
                       Transportation departureFlightInfo,
                       Transportation returnFlightInfo) {
    this.poId = poId;
    this.shippingInfo = shippingInfo;
    this.userId = userId;
    this.emailId = emailId;
    this.locale = locale;
    this.orderDate = orderDate;
    this.billingInfo = billingInfo;
    this.totalPrice = totalPrice;
    this.creditCard = creditCard;
    this.headCount = headCount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departureCity = departureCity;
    this.activities = activities;
    this.lodging = lodging;
    this.departureFlightInfo = departureFlightInfo;
    this.returnFlightInfo = returnFlightInfo;
  }

  // getter methods
  public String getPoId() {
    return poId;
  }

  public String getUserId() {
    return userId;
  }

  public String getEmailId() {
    return emailId;
  }

  public String getLocale() {
    return locale;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public ContactInfo getShippingInfo() {
    return shippingInfo;
  }

  public ContactInfo getBillingInfo() {
    return billingInfo;
  }

  public float getTotalPrice() {
    return totalPrice;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public int getHeadCount() {
    return headCount;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getDepartureCity() {
    return departureCity;
  }

  public Activity[] getActivities() {
    return activities;
  }

  public Lodging getLodging() {
    return lodging;
  }

  public Transportation getDepartureFlightInfo() {
    return departureFlightInfo;
  }

  public Transportation getReturnFlightInfo() {
    return returnFlightInfo;
  }

  // setter methods
  public void setPoId(String poId) {
    this.poId = poId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public void setShippingInfo(ContactInfo shippingInfo) {
    this.shippingInfo = shippingInfo;
  }

  public void setBillingInfo(ContactInfo billingInfo) {
    this.billingInfo = billingInfo;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public void setHeadCount(int headCount) {
    this.headCount = headCount;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setDepartureCity(String departureCity) {
    this.departureCity = departureCity;
  }

  public void setActivities(Activity[] activity) {
    this.activities =  activity;
  }

  public void setLodging(Lodging lodging) {
    this.lodging = lodging;
  }

  public void setDepartureFlightInfo(Transportation departureFlightInfo) {
    this.departureFlightInfo = departureFlightInfo;
  }

  public void setReturnFlightInfo(Transportation returnFlightInfo) {
    this.returnFlightInfo = returnFlightInfo;
  }

}
