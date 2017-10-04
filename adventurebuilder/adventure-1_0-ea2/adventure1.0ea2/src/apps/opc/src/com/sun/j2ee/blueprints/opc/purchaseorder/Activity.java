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
 $Id: Activity.java,v 1.3 2003/03/11 20:26:19 brydon Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.util.*;

public class Activity {

  protected String activityId;
  protected String name;
  protected float price;
  protected String location;
  protected Date startDate;
  protected Date endDate;
  protected int headCount;

  // Constructor
  public Activity() {}

  public Activity(String activityId, String name, float price, String location,
                  Date startDate, Date endDate, int headCount) {
    this.activityId = activityId;
    this.name = name;
    this.price = price;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
    this.headCount = headCount;
  }

  // getter methods
  public String getActivityId() {
    return activityId;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public String getLocation() {
    return location;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public int getHeadCount() {
    return headCount;
  }

  // setter methods
  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setHeadCount(int headCount) {
    this.headCount = headCount;
  }

}
