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
 $Id: cart_activities_tab.jsp,v 1.3 2003/03/10 20:55:41 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />
<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<form type="post" action="cart.do">
 <input type="hidden" name="target_action" value="update_activities" />
<table width="100%">
    <table border="0">
      <c:choose>
       <c:when test="${cart.activityCount > 0}">  
        <c:forEach var="activity" items="${cart.activityIds}">
        <sql:query var="activityDetails"> 
          select name, description, price, imageuri from activity where activityid =  ? and locale =?
        <sql:param>${activity}</sql:param>
        <sql:param>en_US</sql:param>
          </sql:query>
         <tr>
           <td>
            <c:url var="imageURL" value="images/${activityDetails.rows[0].imageuri}"/>
            <image width="50" height="50" src="${imageURL}"/>
           </td>
          <td>
              <font size="+1">${activityDetails.rows[0].name}</font>
              <br/>${activityDetails.rows[0].description}
              <br/><b>Number of people:</b>
              <waf:select size="1"
                      name="headcount_${activity}">
                <waf:selected>${cart.activities[activity]}</waf:selected>
               <waf:option value="0">None - Cancel</waf:option>
                <c:forEach varStatus="status" begin="1" end="${cart.headCount}">
                 <waf:option value="${status.index}">${status.index}</waf:option>
                </c:forEach>
              </waf:select>
              <br/><br/>
           </td>
           <td align="right">
            <b>Price Per Person:</b> <fmt:formatNumber value="${activityDetails.rows[0].price}" type="currency" />
          </td>
         </tr>
        </c:forEach>
   <tr>
   <td>
    </td>
    <td>
      <input type="submit" value="Update Activity Number of People"/>
   </td>
   <td  align="right">
      <a href="activities.screen">Purchase More Activities</a>
   </td>
  </tr>
    <tr>
     <td colspan="3">
     <hr noshade="true"><br/>
     </td>
    </tr>
   <tr>
    <td>
     <font size="+1" color="black">Activity Total:</font>
    </td>
   <td colspan="2" align="right">
     <b><fmt:formatNumber value="${cartBean.activityTotal}" type="currency" /></b>
    </td>
  </tr>
  </c:when>
   <c:otherwise>
    <tr>
     <td>
         There are no activities in your adventure. <br/><br/>
       <a href="activities.screen">Purchase Activities</a>
     </td>
    </tr>
   </c:otherwise>
  </c:choose>
 </table>
</form>