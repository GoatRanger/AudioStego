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
 $Id: WebController.java,v 1.4 2002/12/05 22:23:59 gmurray Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.Collection;

// J2EE Imports
import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;
import com.sun.j2ee.blueprints.waf.controller.EventException;



/**
 * This class is essentially where the business logic for the web tier
 * is processed. Implementations of this class may implement the processing
 * as a factory method to process com.sun.j2ee.blueprints.waf.controller.Command
 * objects directly or they may be processed in the EJB tier in other implmentations
 * of htis class that work as more of a proxy class.
 */
public interface WebController extends java.io.Serializable {


    /**
     * constructor for an HTTP client.
     * @param the  ServletContext object of the application
     */
    public void init(ServletContext context);

  
    /**
     * feeds the specified event to the state machine of the business logic. 
     *
     * @param ev is the current com.sun.j2ee.blueprints.waf.controller.Event
     * @param request is the current javax.servlet.ServletRequest 
     * @return an com.sun.j2ee.blueprints.waf.event.EventResponse resulting in the
     *         processing of this event. 
     * @exception com.sun.j2ee.blueprints.waf.event.EventException <description>
     * 
     */
    public  EventResponse handleEvent(Event ev, ServletRequest request) throws EventException; 

}

