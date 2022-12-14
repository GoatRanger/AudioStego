<%-- Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 
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
 $Id: enter_order_information.jsp,v 1.1 2003/03/07 23:27:58 gmurray Exp $ --%>

<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>


<font size="+2">Billing Information</font>

<waf:form name="order_ino" method="post" action="checkout.do">
 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td align="right">
    <b>First Name</b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                           name="given_name_a"
                              size="30"
                   maxlength="30"
                       validation="validation">
      <waf:value>${customerBean.givenName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Last Name</b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="family_name_a"
                               size="30"
                    maxlength="30"
                      validation="validation">
     <waf:value>${customerBean.familyName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b>Street Address</b></td>
   <td align="left" colspan="2">
   <waf:input type="text"
                           name="address_1_a"
                              size="55"
                   maxlength="70"
              validation="validation">
     <waf:value>${customerBean.streetName1}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td>&nbsp;</td>
   <td align="left" colspan="2">
    <waf:input type="text"
                       name="address_2_a"
                          size="55"
               maxlength="70">
       <waf:value>${customerBean.streetName2}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>City</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="city_a"
                               size="55"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.city}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>State/Province</b>
   </td>
   <td align="left">
    <waf:select size="1"
                      name="state_or_province_a">
     <waf:selected>${customerBean.state}</waf:selected>
     <waf:option value="California">CA</waf:option>
     <waf:option value="New York">NY</waf:option>
     <waf:option value="Texas">TX</waf:option>
    </waf:select>
   </td>
   <td><b>Zip Code</b>
    <waf:input     type="text"
                            name="postal_code_a" 
                               size="12"
                    maxlength="12"
                      validation="validation">
     <waf:value>${customerBean.zipCode}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Country</b>
   </td>
   <td align="left" colspan="2">
    <waf:select size="1" name="country_a">
    <waf:selected>${customerBean.country}</waf:selected>
     <waf:option value="United States">United States</waf:option>
     <waf:option value="Canada" />
     <waf:option value="Japan" /> 
     <waf:option value="China" /> 
    </waf:select>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Telephone</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="telephone_number_a"
                                size="12"
                     maxlength="70"
                         validation="validation">
      <waf:value>${customerBean.telephone}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>E-Mail</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="email_a"
                               size="12"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.email}</waf:value>
     </waf:input>
   </td>
  </tr>
 </table>

 <font size="+2">Credit Card  Information</font>

 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td> Credit Card Name:
      <select name="credit_card_name">
       <option>Duke Express Card</option>
       <option>Globe Traveler Card</option>
      </select>
   </td>
 </tr>
   <tr>
   <td> Number Number:
    <waf:input type="text"
                           name="credit_card_number"
                              size="20"
                   maxlength="20"
                       validation="validation">
      <waf:value>1111-1234-5432</waf:value>
    </waf:input>
   </td>
 </tr>
  <tr>
   <td>Expiration Month:
     <select name="credit_card_month">
     <c:forEach varStatus="status" begin="1" end="12">
       <option>${status.index}</option>
     </c:forEach>
     </select>
     Expiration Year:
     <select name="credit_card_year">
     <c:forEach varStatus="status" begin="2003" end="2005">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
 </table>


 <font size="+2">Shipping Information</font>

 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td align="right">
    <b>First Name</b>
   </td> 
   <td align="left" colspan="2">
    <waf:input type="text"
                           name="given_name_b"
                              size="30"
                   maxlength="30"
                       validation="validation">
      <waf:value>${customerBean.givenName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Last Name</b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="family_name_b"
                               size="30"
                    maxlength="30"
                      validation="validation">
     <waf:value>${customerBean.familyName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b>Street Address</b></td>
   <td align="left" colspan="2">
   <waf:input type="text"
                           name="address_1_b"
                              size="55"
                   maxlength="70"
              validation="validation">
     <waf:value>${customerBean.streetName1}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td>&nbsp;</td>
   <td align="left" colspan="2">
    <waf:input type="text"
                       name="address_2_b"
                          size="55"
               maxlength="70">
       <waf:value>${customerBean.streetName2}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>City</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="city_b"
                               size="55"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.city}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>State/Province</b>
   </td>
   <td align="left">
    <waf:select size="1"
                      name="state_or_province_b">
     <waf:selected>${customerBean.state}</waf:selected>
     <waf:option value="California">CA</waf:option>
     <waf:option value="New York">NY</waf:option>
     <waf:option value="Texas">TX</waf:option>
    </waf:select>
   </td>
   <td><b>Zip Code</b>
    <waf:input     type="text"
                            name="postal_code_b" 
                               size="12"
                    maxlength="12"
                      validation="validation">
     <waf:value>${customerBean.zipCode}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Country</b>
   </td>
   <td align="left" colspan="2">
    <waf:select size="1" name="country_b">
    <waf:selected>${customerBean.country}</waf:selected>
     <waf:option value="United States">United States</waf:option>
     <waf:option value="Canada" />
     <waf:option value="Japan" /> 
    </waf:select>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>Telephone</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="telephone_number_b"
                                size="12"
                     maxlength="70"
                         validation="validation">
      <waf:value>${customerBean.telephone}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b>E-Mail</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="email_b"
                               size="12"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.email}</waf:value>
     </waf:input>
   </td>
  </tr>
 </table>
 <input type="submit" value="Submit">
</waf:form>

