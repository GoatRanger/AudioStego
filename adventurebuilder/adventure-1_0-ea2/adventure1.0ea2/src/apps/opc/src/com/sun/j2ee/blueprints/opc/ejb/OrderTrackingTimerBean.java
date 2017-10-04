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
 $Id: OrderTrackingTimerBean.java,v 1.5 2003/03/11 22:22:47 sk140329 Exp $ */


package com.sun.j2ee.blueprints.opc.ejb;

import javax.ejb.*;
import javax.ejb.Timer;
import java.util.*;

import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.processmanager.manager.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.opc.*;

/**
 *  This is the OrderTrackingTimerBean CMP entity EJB
 *  It creates a timer . The callback method, ejbTimeout,
 *  updates the order status
 */

public class OrderTrackingTimerBean implements SessionBean, TimedObject {

    private SessionContext sessionContext = null;
    private ProcessManagerLocal pmLocal = null;
    private ProcessManagerLocalHome pmlHome = null;

    public void ejbCreate() throws CreateException {

        try {
            ServiceLocator sl = new ServiceLocator();
            pmlHome = (ProcessManagerLocalHome) sl.getLocalHome(JNDINames.PM_EJB);
            pmLocal = pmlHome.create();
        }
        catch (CreateException ce) {
          throw new EJBException("Exception creating PM ! " + ce.getMessage());
        }
    }

    public void ejbPostCreate() throws CreateException {

    }

    //create the timer
    public void startTimer() {

        try{
            //info about the timer
            String timerInfo = "A Timer to update PO status";
            ServiceLocator sl = new ServiceLocator();

      //get the timer interval in minutes and convert to milliseconds
      long intervalDuration = (Long.parseLong(sl.getString(JNDINames.TIMER_INTERVAL))) * 60000;

      //timer  starts after this duration in milliseconds
      long initialDuration = 60000;
      TimerService timerService = sessionContext.getTimerService();
      Timer timer = timerService.createTimer(initialDuration, intervalDuration, timerInfo);
        }
        catch(NumberFormatException ne){
          throw new EJBException("Exception creating timer ! " + ne.getMessage());
        }
    }

    /**
     * This is the callback method that updates
     * the order status
     */

    public void ejbTimeout(Timer timer) {

        ArrayList incompleteOrders = new ArrayList();
        try {
            Collection ordersColl = pmLocal.getIncompleteOrders();
            Iterator iter = ordersColl.iterator();

            if (iter.hasNext()) {
              while ( (iter != null) && (iter.hasNext())) {
                ManagerLocal ml = (ManagerLocal) iter.next();
                incompleteOrders.add(ml);
              }
              for (int i = 0; i < incompleteOrders.size(); ++i) {
                ManagerLocal ml = (ManagerLocal) incompleteOrders.get(i);
                String status = ml.getStatus();
                if (status.equalsIgnoreCase("PENDING")) {
                  ml.setStatus("APPROVED");
                }
                if (status.equalsIgnoreCase("APPROVED")) {
                  ml.setStatus("SHIPPED_PART");
                }
                if (status.equalsIgnoreCase("SHIPPED_PART")) {
                 ml.setStatus("COMPLETED");
                }
              }
            }
        }
        catch (FinderException fe) {
          System.err.println("Exception finding incomplete orders! " + fe.getMessage());
        }
    }

    public void ejbActivate() {}

    public void ejbPassivate() {}

    public void setSessionContext(SessionContext c) {
      sessionContext = c;
    }

    public void ejbRemove() {}
}
