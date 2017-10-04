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
 $Id: ContactInfo.java,v 1.5 2003/03/12 22:45:59 gmurray Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

public class ContactInfo {

  protected String familyName;
  protected String givenName;
  protected String streetName1;
  protected String streetName2;
  protected String city;
  protected String state;
  protected String postalCode;
  protected String country;

  protected String email;
  protected String phone;

  public ContactInfo() {}

  public ContactInfo(String familyName, String givenName, String streetName1,
                     String streetName2, String city,
                     String state, String postalCode, String country,
                     String email, String phone) {
    this.familyName = familyName;
    this.givenName = givenName;
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
    this.email = email;
    this.phone = phone;
  }

  // getter methods

  public String getFamilyName() {
    return familyName;
  }

  public String getGivenName() {
    return givenName;
  }

  public String getStreetName1() {
    return streetName1;
  }

  public String getStreetName2() {
    return streetName2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  // setter methods

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public void setStreetName1(String streetName) {
    this.streetName1 = streetName;
  }

  public void setStreetName2(String streetName) {
    this.streetName2 = streetName;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
