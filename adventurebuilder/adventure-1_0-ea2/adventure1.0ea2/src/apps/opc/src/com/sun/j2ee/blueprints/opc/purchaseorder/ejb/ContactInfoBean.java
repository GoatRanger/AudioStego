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
 $Id: ContactInfoBean.java,v 1.2 2003/03/05 23:14:55 vijaysr Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;


/**
 * Implementation class for the  ContactInfoBean .
 * ContactInfoBean is a CMP Entity Bean .
 **/

public abstract class ContactInfoBean  implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(ContactInfo contactInfo) throws CreateException {

    setGivenName(contactInfo.getGivenName());
    setFamilyName(contactInfo.getFamilyName());
    setStreetName1(contactInfo.getStreetName1());
    setStreetName2(contactInfo.getStreetName2());
    setCity(contactInfo.getCity());
    setState(contactInfo.getState());
    setPostalCode(contactInfo.getPostalCode());
    setCountry(contactInfo.getCountry());
    setPhone(contactInfo.getPhone());
    setEmail(contactInfo.getEmail());

    return null;
  }

  public void ejbPostCreate(ContactInfo contactInfo) throws CreateException {

  }

  public ContactInfo getDetails() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setGivenName(getGivenName());
    contactInfo.setFamilyName(getFamilyName());
    contactInfo.setStreetName1(getStreetName1());
    contactInfo.setStreetName2(getStreetName2());
    contactInfo.setCity(getCity());
    contactInfo.setState(getState());
    contactInfo.setPostalCode(getPostalCode());
    contactInfo.setCountry(getCountry());
    contactInfo.setPhone(getPhone());
    contactInfo.setEmail(getEmail());

    return contactInfo;

  }

  //getters and setters for CMP fields
  public abstract void setFamilyName(String familyName);

  public abstract void setGivenName(String givenName);

  public abstract void setStreetName1(String streetName1);

  public abstract void setStreetName2(String streetName2);

  public abstract void setCity(String city);

  public abstract void setState(String state);

  public abstract void setPostalCode(String postalCode);

  public abstract void setCountry(String country);

  public abstract void setEmail(String email);

  public abstract void setPhone(String phone);

  public abstract String getFamilyName();

  public abstract String getGivenName();

  public abstract String getStreetName1();

  public abstract String getStreetName2();

  public abstract String getCity();

  public abstract String getState();

  public abstract String getPostalCode();

  public abstract String getCountry();

  public abstract String getEmail();

  public abstract String getPhone();

  public void ejbLoad() {
  }

  public void ejbRemove() throws RemoveException {
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
