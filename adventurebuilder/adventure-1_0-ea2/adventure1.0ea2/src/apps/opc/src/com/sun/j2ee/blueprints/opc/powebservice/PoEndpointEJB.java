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
 $Id: PoEndpointEJB.java,v 1.8 2003/03/11 20:26:18 brydon Exp $ */

package com.sun.j2ee.blueprints.opc.powebservice;

import java.util.*;
import javax.ejb.*;
import java.rmi.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.uidgen.ejb.*;
import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.*;
import com.sun.j2ee.blueprints.opc.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;

/**
 *  This class is the entry point for purchase orders submitted 
 *  by adventure builder web site application when a user 
 *  submits an order.
 *  For this Early Access release, the whole workflow to fullfill 
 *  an order is not done. 
 */
public class PoEndpointEJB implements SessionBean {

    private SessionContext sc;
    private UniqueIdGeneratorLocal uidgen = null;
    private ProcessManagerLocal processManager = null;
    private PurchaseOrderLocalHome poHome = null;
    private OrderTrackingTimerLocal timer = null;
 
    public PoEndpointEJB(){}
    
    public void ejbCreate() throws RemoteException {   
  try {
      ServiceLocator sl = new ServiceLocator();
          OrderTrackingTimerLocalHome tlHome =
            (OrderTrackingTimerLocalHome) sl.getLocalHome(JNDINames.TIMER_EJB);
          timer = tlHome.create();
          //start the timer that updates the order status
          timer.startTimer();
      UniqueIdGeneratorLocalHome home =
    (UniqueIdGeneratorLocalHome)sl.getLocalHome(JNDINames.UIDGEN_EJB);
      uidgen = home.create();
          ProcessManagerLocalHome pmHome =
    (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
      processManager = pmHome.create();
      poHome = (PurchaseOrderLocalHome) sl.getLocalHome(JNDINames.PO_EJB);
  } catch (CreateException ce) {
          throw new EJBException(ce);
  }
    }


    /**
     * Accept a purchase order, create an order id, and return the order 
     * id so that the caller can have a correlation id for the order
     */
    public String submitPurchaseOrder(WSPurchaseOrder poObject)
                                        throws RemoteException {
        String orderId = null; 
        String seedId = "5432";  //to seed the id generator

        //validate PO, make sure all required info is provided.
        if (poObject == null) {
            throw new EJBException("PoEndpointEJB.submitPurchaseOrder:: the PO is not set!");
        } else if ( poObject.getUserId()       == null || 
                    poObject.getEmailId()      == null || 
                    poObject.getLocale()       == null || 
                    poObject.getOrderDate()    == null ||
                poObject.getShippingInfo() == null ||
            poObject.getBillingInfo()  == null ||
                    poObject.getTotalPrice()   == 0    ||
                    poObject.getCreditCard()   == null ||
                    poObject.getHeadCount()    == 0    ||
                    poObject.getStartDate()    == null ||
                    poObject.getEndDate()      == null ||
                    poObject.getDepartureCity()== null  ) {
            throw new EJBException("PoEndpointEJB.submitPurchaseOrder:: a required PO field is null!");
        }

    PurchaseOrder po = new PurchaseOrder(poObject.getPoId(),
            poObject.getUserId(),
        poObject.getEmailId(),
        poObject.getLocale(),
        poObject.getOrderDate(),
        poObject.getShippingInfo(),
                poObject.getBillingInfo(),
        poObject.getTotalPrice(),
        poObject.getCreditCard(),
        poObject.getHeadCount(),
        poObject.getStartDate(),
        poObject.getEndDate(),
        poObject.getDepartureCity(),
        poObject.getActivities(),
        poObject.getLodging(),
        poObject.getDepartureFlightInfo(),
        poObject.getReturnFlightInfo());
        try {
            orderId = uidgen.getUniqueId(seedId);  
            String status = OrderStatusNames.PENDING;
        po.setPoId(orderId);
            PurchaseOrderLocal polocal = poHome.create(po);
            processManager.createManager(orderId, status);     
        } catch (CreateException ce) {            
            throw new EJBException(ce);
        }      
        return orderId;
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
