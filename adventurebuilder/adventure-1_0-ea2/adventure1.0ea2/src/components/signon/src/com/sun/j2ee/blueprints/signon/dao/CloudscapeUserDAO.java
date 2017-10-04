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
 $Id: CloudscapeUserDAO.java,v 1.19 2003/01/30 08:10:46 inder Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

import com.sun.j2ee.blueprints.util.tracer.Debug;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;
import com.sun.j2ee.blueprints.util.dao.DAOUtils;


/**
 * This class encapsulates all the database access. It follows
 * the Data Access Object pattern.
 **/
public class CloudscapeUserDAO implements UserDAO {
    
    private final static String MATCH_PASSWORD_QUERY =
    "SELECT password FROM " + DatabaseNames.SIGNON_TABLE +
    " WHERE username = ?";
    private final static String CREATE_USER_QUERY = "INSERT INTO " +
    DatabaseNames.SIGNON_TABLE + " VALUES(?, ?)";
    
    public CloudscapeUserDAO() {}
    
    
    /**
     * @throws SignOnDAODupKeyException if userName already exists in database
     **/
    public void createUser(String userName, String password)
    throws SignOnDAODupKeyException {
        
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DAOUtils.getDBConnection(JNDINames.SIGNON_DATASOURCE);
            ps = conn.prepareStatement(CREATE_USER_QUERY);
            ps.setString(1, userName.trim());
            ps.setString(2, password.trim());
            int result = ps.executeUpdate();
            if(result != 1) {
                throw new SignOnDAODupKeyException("Unable to create user. " +
                "Duplicate Key : " + userName);
            }
        } catch (SQLException se) {
            throw new DAOSystemException(se);
        } finally {
            DAOUtils.closeStatement(ps);
            DAOUtils.closeConnection(conn);
        }
    }
    
    
    /**
     *  @return true  if userName already exists in database AND the
     *                corresponding password in the database matches
     *                the password parameter
     **/
    public boolean matchPassword(String userName, String password)
    throws SignOnDAOFinderException,
    InvalidPasswordException{
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DAOUtils.getDBConnection(JNDINames.SIGNON_DATASOURCE);
            ps = conn.prepareStatement(MATCH_PASSWORD_QUERY);
            ps.setString(1, userName.trim());
            rs = ps.executeQuery();
            if(rs.next()) {
                if(!rs.getString(1).equals(password)) {
                    throw new InvalidPasswordException("Password does not match");
                }
            } else {
                throw new SignOnDAOFinderException("Unable to find user " +
                userName);
            }
        } catch (SQLException se) {
            throw new DAOSystemException(se);
        } finally {
            DAOUtils.closeResultSet(rs);
            DAOUtils.closeStatement(ps);
            DAOUtils.closeConnection(conn);
        }
        return(true);
    }
}
