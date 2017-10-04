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
 $Id: readaccountdata.jsp,v 1.4 2002/11/21 22:45:20 inder Exp $ --%>
<html>
<head>
</head>

<body>

<html>
<head>
<title>Java[tm] BluePrints for the Enterprise: Customer Mini-Application > Read an existing account</title>
</head>
<body>

<%@ include file="header.jsp" %>

<h2>Read Existing Account</h2>

<p>Please use the form below to retrieve an existing customer account.</p>

<form name="readaccount" action="readAccount.do" method="GET">

<table border="0" bgcolor="#EEEEEE" cellpadding="5" cellspacing="0">

  <input type="hidden" name="target_action" value="readAccount"/>

  <tr>
  <td><b>UserId:</b></td> <td><input type="text" size="20" name="acct_read_userId"></td>
  </tr>

  <tr>
  <td align="right" colspan="2">
  <input type="reset" value="Clear Form">
  <input type="submit" value="Read Account">
  </td>
  </tr>
</table>
</form>

</body>
</html>
