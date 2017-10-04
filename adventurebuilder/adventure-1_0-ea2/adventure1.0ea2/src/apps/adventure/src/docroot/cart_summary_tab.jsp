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
 $Id: cart_summary_tab.jsp,v 1.3 2003/03/11 23:26:54 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packageDetails"> 
 select name, description, imageuri from package where packageid =  ? and locale = ?
 <sql:param>${cart.packageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<form method="post" action="cart.do" >
<input type="hidden" name="target_action" value="update_package_details" />
<table>
  <tr>
    <td colspan="2">
    <font size="+2">${packageDetails.rows[0].name}</font>
    </td><td/>
  </tr>
  <tr><td colspan="2"><br/></td>
  <tr>
   <td colspan="2"> ${packageDetails.rows[0].description} </td>
   <td>
     <c:url var="imageURL" value="images/${packageDetails.rows[0].imageuri}"/>
     <image src="${imageURL}" height="50" width="50"/>
   </td>
  </tr> 
  <tr><td colspan="2"><br/></td>
  <tr>
     <td colspan="2"><b> Number of People :</b> </td>
    <td>
      <waf:select size="1"
                           name="head_count">
        <waf:selected>${cart.headCount}</waf:selected>
          <c:forEach varStatus="status" begin="1" end="9">
           <waf:option value="${status.index}">${status.index}</waf:option>
          </c:forEach>
         </waf:select>
     </td>
  </tr>
  <tr>
     <td colspan="2"><b> Number of Days :</b> </td>
    <td>
      <waf:select size="1"
                           name="adventure_days">
        <waf:selected>${cart.adventureDays}</waf:selected>
          <c:forEach varStatus="status" begin="2" end="31">
           <waf:option value="${status.index}">${status.index}</waf:option>
          </c:forEach>
         </waf:select>
     </td>
  </tr>
  <tr>
   <td colspan="2"><b> Location :</b> </td>
   <td>
    ${cart.destination}
   </td>
  </tr>
  <tr><td colspan="2"><br/></td> 
  <tr>   
   <td colspan="2"  >
   <b>Lodging Total:</b>
   </td>
   <td>
       <fmt:formatNumber value="${cartBean.lodgingTotal}" type="currency" />
   </td>   
  </tr>
  <tr/><tr/>  
  <tr>   
   <td colspan="2" >
    <b>Activity Total:</b>
   <td> 
      <fmt:formatNumber value="${cartBean.activityTotal}" type="currency" />
   </td> 
  </tr>
  <tr/>  
  <tr>   
   <td colspan="2" >
    <b>Transportation Total:</b>
   <td> 
      <fmt:formatNumber value="${cartBean.transportationTotal}" type="currency" />
   </td> 
  </tr>
  <tr>
    <td colspan="3" align="right"><input type="submit" value="Update Package Details"/></td>
  </tr>
  <tr>   
   <td colspan="3" >
    <hr noshade="true">
   </td> 
  </tr>
  <tr>
   <td colspan="2">
    <b>Total Price : </b>
   </td>
   <td>
    <b><fmt:formatNumber value="${cartBean.total}" type="currency" /></b>
   </td>
  </tr>
 </table>
</form>