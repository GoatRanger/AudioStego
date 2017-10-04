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
 $Id: JNDINames.java,v 1.3 2003/03/11 20:26:17 brydon Exp $ */

package com.sun.j2ee.blueprints.opc;

/**
 * JNDI names of various entities used by the OPC
 */
public class JNDINames {

    // JNDI name of Process Manager  EJB
    public static final String PM_EJB = "java:comp/env/ejb/local/processmanager/ProcessManager";
  
   // JNDI name of PO EJB
    public static final String PO_EJB = "java:comp/env/ejb/local/purchaseorder/PurchaseOrder";

   // JNDI name of Unique Id Generator  EJB
    public static final String UIDGEN_EJB = "java:comp/env/ejb/local/uidgen/UniqueIdGenerator";

   // JNDI name of Timer  EJB
    public static final String TIMER_EJB = "java:comp/env/ejb/local/opc/OrderTrackingTimer";

   //environment entry representing the Order Tracking Timer interval
   public static final String TIMER_INTERVAL = "java:comp/env/param/opc/OrderTrackingTimerInterval";

}

