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
 $Id: OrderStatusNames.java,v 1.3 2002/12/09 22:09:54 brydon Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;

/**
 * This class is the central location to store the names of the 
 * status that an order can be in.
 * The states an order goes through are:
 * pending->approved->shippedPart(shipped part of order)->completed(whole order shipped)  or 
 * pending->denied
 */
public class OrderStatusNames {

    private OrderStatusNames() { } //Prevents instantiation

    /** for orders that have been placed but not yet approved */
    public static final String PENDING = "PENDING";

    /** for orders that have been approved */
    public  static final String APPROVED = "APPROVED";

    /** for orders that have been denied */
    public  static final String DENIED = "DENIED";


    /** for orders that have been partially completed*/
    public  static final String SHIPPED_PART = "SHIPPED_PART";


    /** for orders that have been completed*/
    public  static final String COMPLETED = "COMPLETED";
    
}

