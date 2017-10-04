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
 $Id: order_tracking_result.jsp,v 1.3 2003/03/12 01:51:37 gmurray Exp $ --%>


<font size="+2">OrderTracking</font>
<hr noshade="true"/>
<br/>
<br/>
The following is the current state of the order number
${orderDetails.PO.poId}

<br/>
<br/>
<b>User Name: <b/>${orderDetails.PO.userId}
<br/><br/>
<font size="+1">Billing Address:</font>
<br/><br/>
<table border="0">
 <tr>
 <td>${orderDetails.PO.billingInfo.streetName1}</td>
 </tr>
 <tr>
 <td>${orderDetails.PO.billingInfo.streetName2}</td>
 </tr>
 <tr>
  <td>
           ${orderDetails.PO.billingInfo.city},
           ${orderDetails.PO.billingInfo.state} &nbsp;&nbsp;
           ${orderDetails.PO.billingInfo.postalCode}
   </td>
 </tr>
</table>
<br/>
<font size="+1">Shipping Address:</font>
<br/><br/>
<table border="0">
 <tr>
 <td>${orderDetails.PO.shippingInfo.streetName1}</td>
 </tr>
 <tr>
 <td>${orderDetails.PO.shippingInfo.streetName2}</td>
 </tr>
 <tr>
  <td>
           ${orderDetails.PO.shippingInfo.city},
           ${orderDetails.PO.shippingInfo.state} &nbsp;&nbsp;
           ${orderDetails.PO.shippingInfo.postalCode}
   </td>
 </tr>
</table>
<br/>
<br/>
<b>Status:</b>${orderDetails.status}
<br/>
<br/>
<a href="track_order.screen">Track another order</a></p>
