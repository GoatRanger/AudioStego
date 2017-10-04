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
 $Id: DAOUtils.java,v 1.3 2003/02/26 03:20:35 inder Exp $ */

package com.sun.j2ee.blueprints.util.dao;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

public class DAOUtils {
    
    private DAOUtils() { }
    
    private static DataSource getDataSource(String dsName)
    throws DAOSystemException {
        String dataSourceName = ServiceLocator.getInstance().getString(dsName);
        return (DataSource) ServiceLocator.getInstance().getDataSource(dataSourceName);
    }
    
    public static Connection getDBConnection(String source)
    throws DAOSystemException {
        try {
            return getDataSource(source).getConnection();
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while getting "
            + "DB connection : \n" + se);
        }
    }
    
    public static void closeConnection(Connection dbConnection)
    throws DAOSystemException {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "DB connection : \n" + se);
        }
    }
    
    public static void closeResultSet(ResultSet result)
    throws DAOSystemException {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "Result Set : \n" + se);
        }
    }
    
    public static void closeStatement(PreparedStatement stmt)
    throws DAOSystemException {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "Statement : \n" + se);
        }
    }
}
