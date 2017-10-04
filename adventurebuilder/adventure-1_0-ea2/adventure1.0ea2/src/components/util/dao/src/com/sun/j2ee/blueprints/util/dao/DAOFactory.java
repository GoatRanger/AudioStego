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
 $Id: DAOFactory.java,v 1.1 2002/11/20 02:00:14 inder Exp $ */

package com.sun.j2ee.blueprints.util.dao;

import javax.naming.NamingException;
import javax.naming.InitialContext;

public class DAOFactory {

    /**
     * This method instantiates the DAO class that is used in this
     * applications deployment environment to access the data. 
     * @param daoEnvEntry The env-entry in the deployment descriptor
     * that contains the name of the class corresponding to
     * which DAO to instantiate.
     *
     * @throws DAOSystemException if it has system error looking up 
     *         the DAO class name in the env-entry.
     */
    public static Object getDAO(String daoEnvEntry) throws DAOSystemException {
        try {
            InitialContext ic = new InitialContext();
            String className = (String) ic.lookup(daoEnvEntry);
            return Class.forName(className).newInstance();
        } catch (NamingException ne) {
            throw new DAOSystemException("DAOFactory.getDAO(" + daoEnvEntry +"):  NamingException while getting DAO type : \n" + ne.getMessage());
        } catch (Exception se) {
            throw new DAOSystemException("DAOFactory.getDAO(" + daoEnvEntry +"):  Exception while getting DAO type : \n" + se.getMessage());
        }
    }
}
