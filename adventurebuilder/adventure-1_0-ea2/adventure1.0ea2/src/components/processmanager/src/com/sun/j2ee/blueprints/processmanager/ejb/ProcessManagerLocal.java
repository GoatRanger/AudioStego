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
 $Id: ProcessManagerLocal.java,v 1.4 2003/03/07 21:53:45 sk140329 Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;

/**
 * This interface provides methods to view and modify
 * information for a particular order workflow process.
*/
public interface ProcessManagerLocal extends javax.ejb.EJBLocalObject {

    /** 
     * update the status of the order corresponding to orderId     
     */
    public void updateStatus (String orderId, String status) throws FinderException ;

    
    /**
     * start a new workflow for each new order
     */
    public void createManager (String orderId, String status) throws CreateException;
    
    public String getStatus(String orderId) throws FinderException;

    public Collection getOrdersByStatus(String status) throws FinderException;

    public Collection getIncompleteOrders() throws FinderException;

}

