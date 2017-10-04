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
 $Id: adventure.jsp,v 1.3 2003/03/07 03:20:00 gmurray Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql-rt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="packageContents"> 
 select p.name, p.description,p.location, p.price , l.name as lodgingname, l.description as lodgingdescription, l.price as lodgingprice, p.imageuri, l.imageuri as lodgingimageuri from package p, lodging l where p.lodgingid = l.lodgingid and p.packageid = ? and p.locale = ?
 <sql:param>${param.PACKAGE_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<sql:query var="activityDetails"> 
 select activity.name, activity.description, activity.price, activity.imageuri from activity, activitylist, package where package.packageid = activitylist.packageid and activity.activityid = activitylist.activityid and package.packageid = ? and package.locale = ?
 <sql:param>${param.PACKAGE_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<table border="0">

  <tr>
     <td>
     <c:url var="imageURL" value="images/${packageContents.rows[0].imageuri}"/>
     <image src="${imageURL}"/>
   </td>
    <td>
    <font size="+2">${packageContents.rows[0].name}</font><br/><br/>
    <b> Location :</b>
     ${packageContents.rows[0].location} <br/><br/>
     ${packageContents.rows[0].description} <br/><br/>
    <b>Total Price : </b>
    <b><fmt:formatNumber value="${packageContents.rows[0].price}" type="currency" /></b>
     <br/><br/>
   </td>
  </tr> 
</table>
<table border="0">
  <tr>   
   <td colspan="3" >
    <font size="+1">Lodging Details </font>
   </td>
  </tr>
  <tr>
   <td>&nbsp;&nbsp;&nbsp;</td>
   <td>
     <c:url var="imageURL" value="images/${packageContents.rows[0].lodgingimageuri}"/>
     <image width="50" height="50" src="${imageURL}"/>
    </td>
    <td>
        Name : ${packageContents.rows[0].lodgingname}<br/>
       ${packageContents.rows[0].lodgingdescription}<br/>  
        Price : <fmt:formatNumber value="${packageContents.rows[0].lodgingprice}" type="currency" />  &nbsp; ${packageContent[5]}<br/> 
   </td>   
  </tr>

  <tr>   
   <td colspan="3" >
    <font size="+1">Included Activities</font>
   </td>
  </tr>
        <c:forEach var="activityDetail" items="${activityDetails.rows}">
          <tr>
           <td>&nbsp;&nbsp;&nbsp;</td>
           <td>
            <c:url var="imageURL" value="images/${activityDetail.imageuri}"/>
            <image width="50" height="50" src="${imageURL}"/>
           </td>
         <td>
          <b><c:out value="${activityDetail.name}"/></b><br/> 
          ${activityDetail.description}<br/> 
           Price : <fmt:formatNumber value="${activityDetail.price}" type="currency" />
          </td>
         </tr>
        </c:forEach>
  <tr/>
 <tr><td colsapn="3">&nbsp;</td></tr>
  <tr>
    <td colspan="3" align="right">
     <form mthod="post" action="cart.do">
     <input type="hidden" name="target_action" value="purchase_package" />
     <input type="hidden" name="package_id" value="${param.PACKAGE_ID}" />
     <input type="submit" value="Purchase Package"/>
    </form>
   </td>
  </tr>
 </table>