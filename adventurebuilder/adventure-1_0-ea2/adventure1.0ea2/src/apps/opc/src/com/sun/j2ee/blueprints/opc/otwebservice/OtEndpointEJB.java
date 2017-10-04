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
 $Id: OtEndpointEJB.java,v 1.2 2003/03/10 21:36:52 brydon Exp $ */

package com.sun.j2ee.blueprints.opc.otwebservice;

import javax.ejb.*;
import java.rmi.RemoteException; 
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;
import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.*;

/**
 *  This class is used to get Order Tracking info
 *  by adventure builder web site application after a user 
 *  has submitted an order, and wants to track it.
 */
public class OtEndpointEJB implements SessionBean {

    private SessionContext sc;
    private ProcessManagerLocal processManager = null;
    private PurchaseOrderLocalHome poHome = null;
 
    public OtEndpointEJB(){}
    
    public void ejbCreate() throws RemoteException {   
  try {
      ServiceLocator sl = new ServiceLocator();
      ProcessManagerLocalHome pmHome =
    (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
      processManager = pmHome.create();
      poHome = (PurchaseOrderLocalHome) sl.getLocalHome(JNDINames.PO_EJB);
  } catch (CreateException ce) {
      throw new EJBException(ce);
  }
    }

    /**
     * Accept an order id, and return the details of the current status
     * for the order.
     *
     * @return OrderDetails if orderId exists, else return null to 
     * indicate orderId not found
     */
    public OrderDetails getOrderDetails(String orderId)
                                    throws RemoteException {
       
        OrderDetails details = new OrderDetails();
        try {
            String status = processManager.getStatus(orderId);   
            details.setStatus(status);   
            PurchaseOrderLocal polocal = poHome.findByPrimaryKey(orderId);
        details.setPO(polocal.getPO());
        //System.out.println("PO Id = " + details.getPO().getPoId());
        } catch (FinderException fe) {
      details = null; //indicate user entered non-existent orderId
    }
        return details;
    }
        
    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }
    
    public void ejbRemove() throws RemoteException {}    

    //empty for Stateless EJBs
    public void ejbActivate() {}

    //empty for Stateless EJBs
    public void ejbPassivate() {}

}
