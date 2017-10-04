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
 $Id: CartBean.java,v 1.1 2003/02/25 01:52:45 gmurray Exp $ */

package com.sun.j2ee.blueprints.adventure.web;

import java.io.Serializable;

/**
 * A JavaBeans component representing an account.
 */
public class CartBean implements Serializable {


    private double adventureTotal = 0;
    private double lodgingTotal = 0;
    private double activityTotal = 0;
    private double transportationTotal = 0;

    private int lodgingDays = 0;
    private int adventureDays = 0;
    
    // activities when we allow number of people will
    // have to compensate (assumption for now is that 
    // one person per activity
    
    public CartBean(double adventureTotal,
                                       double lodgingTotal,
                                       double activityTotal,
                                       double transportationTotal,
                                       int lodgingDays,
                                       int adventureDays) {
                                           
      this.adventureTotal = adventureTotal;
      this.lodgingTotal = lodgingTotal;
      this.activityTotal = activityTotal;
      this.transportationTotal = transportationTotal;
      this.lodgingDays = lodgingDays;
      this.adventureDays = adventureDays;
    }

    public double getGrandTotal() {
        return adventureTotal + lodgingTotal + activityTotal;
    }
    
    public double getTotal() {
        return adventureTotal;
    }
        
    public double getLodgingTotal() {
        return lodgingTotal;
    }
    
    public double getActivityTotal() {
        return activityTotal;
    }
    
    public int getLodgingDays() {
        return lodgingDays;
    }
    
    public double getTransportationTotal() {
        return transportationTotal;
    }
    
    public int getAdventureDays() {
        return adventureDays;
    }
}
