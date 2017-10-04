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
 $Id: template.jsp,v 1.10 2003/03/01 00:20:55 gmurray Exp $ --%>
<%@ taglib prefix="template" uri="/WEB-INF/template.tld" %>

 <head>
  <title><template:insert parameter="title" /></title>
 </head>

 <body bgcolor="#FFFFFF">
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   <tr valign="top">
    <td colspan="2">
      <table width="100%" height="125" border="0" cellpadding="0" cellspacing="0">
       <tr valign="bottom">
       <td>
       <template:insert parameter="banner" />
       </td>
       </tr>
       <tr tr valign="top">
        <td>
        <jsp:include page="top_menubar.jsp"/>
       </td>
      </tr>
    </table>
    </td>
   </tr>
   <tr valign="top">
     <td  valign="top" width="280"><template:insert parameter="sidebar" /></td>
     <td  valign="top" width="100%" align="left">
      <!-- body start-->
       <template:insert parameter="body" />
      <!-- body done-->
     </td>

   </tr>
  <!-- footer start-->
   <tr>
    <td colspan="2"><template:insert parameter="footer" /></td>
   </tr>
  <!-- footer done-->
  </table>

 </body>
</html>

