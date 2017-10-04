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
 $Id: ServiceLocator.java,v 1.6 2003/02/28 02:50:18 inder Exp $ */

package com.sun.j2ee.blueprints.servicelocator.ejb;

import java.net.*;
import javax.ejb.*;
import javax.jms.*;
import javax.naming.*;
import javax.rmi.*;
import javax.sql.*;

import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;

/**
 *  This class is an implementation of the Service Locator pattern. It is
 *  used to looukup resources such as EJBHomes, JMS Destinations, etc.
 */
public class ServiceLocator {
    
    private transient InitialContext ic;
    
    public ServiceLocator() throws ServiceLocatorException  {
        try {
            ic = new InitialContext();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * will get the ejb Local home factory.
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the Local EJB Home corresponding to the homeName
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws ServiceLocatorException {
        try {
            return (EJBLocalHome) ic.lookup(jndiHomeName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * will get the ejb Remote home factory.
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class className) throws ServiceLocatorException {
        try {
            Object objref = ic.lookup(jndiHomeName);
            return (EJBHome) PortableRemoteObject.narrow(objref, className);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the factory for the factory to get queue connections from
     */
    public  QueueConnectionFactory getQueueConnectionFactory(String qConnFactoryName)
    throws ServiceLocatorException {
        try {
            return (QueueConnectionFactory) ic.lookup(qConnFactoryName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the Queue Destination to send messages to
     */
    public  Queue getQueue(String queueName) throws ServiceLocatorException {
        try {
            return (Queue)ic.lookup(queueName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * This method helps in obtaining the topic factory
     * @return the factory for the factory to get topic connections from
     */
    public  TopicConnectionFactory getTopicConnectionFactory(String topicConnFactoryName) throws ServiceLocatorException {
        try {
            return (TopicConnectionFactory) ic.lookup(topicConnFactoryName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * This method obtains the topc itself for a caller
     * @return the Topic Destination to send messages to
     */
    public  Topic getTopic(String topicName) throws ServiceLocatorException {
        try {
            return (Topic)ic.lookup(topicName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
        try {
            return (DataSource)ic.lookup(dataSourceName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the URL value corresponding
     * to the env entry name.
     */
    public URL getUrl(String envName) throws ServiceLocatorException {
        try {
            return (URL)ic.lookup(envName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the boolean value corresponding
     * to the env entry such as SEND_CONFIRMATION_MAIL property.
     */
    public boolean getBoolean(String envName) throws ServiceLocatorException {
        try {
            return ((Boolean)ic.lookup(envName)).booleanValue();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the String value corresponding
     * to the env entry name.
     */
    public String getString(String envName) throws ServiceLocatorException {
        try {
            return (String)ic.lookup(envName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
}
