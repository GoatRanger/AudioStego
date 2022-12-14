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
 $Id: CloudscapeCatalogDAO.java,v 1.13 2003/03/12 22:44:59 gmurray Exp $ */

package com.sun.j2ee.blueprints.catalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.ArrayList;


import com.sun.j2ee.blueprints.util.tracer.Debug;
import com.sun.j2ee.blueprints.util.dao.*;
import com.sun.j2ee.blueprints.catalog.*;


/**
 * This class implements CatalogDAO for Cloudscape database.
 * This class encapsulates all the JDBC calls .
 * The logic of inserting/fetching/updating/deleting  the data in
 * relational database tables is implemented here.
 */
public class CloudscapeCatalogDAO implements CatalogDAO {
    
    private final static String SELECT_LODGINGS_QUERY_STR = "SELECT "+
    "lodgingid, name, description, price, location" +"  FROM "+ DatabaseNames.LODGING_TABLE +
    " WHERE location = ? AND locale = ?";
    private final static String SELECT_LODGING_QUERY_STR = "SELECT "+
    "lodgingid, name, description, price, location" +"  FROM "+ DatabaseNames.LODGING_TABLE +
    " WHERE lodgingid = ? AND locale = ?";
    private final static String SELECT_TRANSPORTATIONS_QUERY_STR = "SELECT "+
    "transportationid, name, description, imageuri, price, origin, destination,carrier,departuretime, arrivaltime, class"+" FROM " + DatabaseNames.TRANSPORTATION_TABLE +
    " WHERE origin = ? AND destination = ? AND locale = ?";
    private final static String SELECT_TRANSPORTATION_QUERY_STR = "SELECT "+
    "transportationid, name, description, imageuri, price, origin, destination,carrier,departuretime, arrivaltime, class"+" FROM " + DatabaseNames.TRANSPORTATION_TABLE +
    " WHERE transportationid = ? AND locale = ?";
    private final static String SELECT_ACTIVITIES_QUERY_STR = "SELECT "+
    "activityid, name, description, price, location "+" FROM " + DatabaseNames.ACTIVITY_TABLE +
    " WHERE location = ? AND locale = ?";
    private final static String SELECT_ACTIVITY_QUERY_STR = "SELECT "+
    "activityid, name, description, price, location "+" FROM " + DatabaseNames.ACTIVITY_TABLE +
    " WHERE activityid = ? AND locale = ?";
    private final static String SELECT_ADVENTURE_PACKAGE_QUERY_STR = "SELECT "+
    "name, description, price, location, lodgingid  "+" FROM " + DatabaseNames.PACKAGE_TABLE +
    " WHERE packageid = ? AND locale = ?";
    private final static String SELECT_ACTIVITYLIST_QUERY_STR = "SELECT "+
    "activityid"+" FROM " + DatabaseNames.ACTIVITYLIST_TABLE +
    " WHERE packageid = ? AND locale = ?";
    
    private PreparedStatement stmt = null;
    private PreparedStatement stmt2 = null;
    private ResultSet result = null;
    private ResultSet result2 = null;
    private Connection dbConnection = null;
    
    public CloudscapeCatalogDAO() {
    }
    
    public ArrayList getLodgings(String location, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        ArrayList lodgings = new ArrayList();
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_LODGINGS_QUERY_STR);
            stmt.setString(1, location.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data found for  " + location +" , "+locale.toString());
            do {
                int i = 1;
                String lodgingId = result.getString(i++);
                String name = result.getString(i++);
                String description = result.getString(i++);
                double price = result.getDouble(i++);
                location = result.getString(i++);
                lodgings.add(new Lodging(lodgingId , name, description, price, location));
            }
            while(result.next());
            
            return(lodgings );
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "lodging details; location = " + location +" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    
    
    public Lodging getLodging(String id, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_LODGING_QUERY_STR);
            stmt.setString(1, id.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data found for  " + id +" , "+locale.toString());
            int i = 1;
            String lodgingId = result.getString(i++);
            String name = result.getString(i++);
            String description = result.getString(i++);
            double price = result.getDouble(i++);
            String location = result.getString(i++);
            return new Lodging(lodgingId , name, description, price, location);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "lodging details; id = " + id +" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    public AdventurePackage getAdventurePackage(String packageId, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        ArrayList transportations = new ArrayList();
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_ADVENTURE_PACKAGE_QUERY_STR);
            stmt.setString(1, packageId.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data found for  " + packageId +" , "+locale.toString());
            int i = 1;
            String name = result.getString(i++).trim();
            String description = result.getString(i++).trim();
            double price = result.getDouble(i++);
            String location = result.getString(i++).trim();
            String lodgingId = result.getString(i++).trim();
            ArrayList activities = new ArrayList();
            // Getting the activites
            stmt2 = dbConnection.prepareStatement(SELECT_ACTIVITYLIST_QUERY_STR);
            stmt2.setString(1, packageId);
            stmt2.setString(2, locale.toString().trim());
            result2 = stmt2.executeQuery();
            
            while (result2.next()) {
                String activityId =  result2.getString(1).trim();
                activities.add(activityId);
            }
            AdventurePackage ap = new AdventurePackage(packageId,
            name, description, location, lodgingId, price, activities);
            return(ap);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "AdventurePackage details; origin = " + packageId +", and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeResultSet(result2);
            DAOUtils.closeStatement(stmt2);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    public ArrayList getTransportations(String origin, String destination, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        ArrayList transportations = new ArrayList();
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_TRANSPORTATIONS_QUERY_STR);
            stmt.setString(1, origin.trim());
            stmt.setString(2, destination.trim());
            stmt.setString(3, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data found for  " + origin +" , "+ destination +" , "+locale.toString());
            do {
                int i = 1;
                String transportId = result.getString(i++);
                String name = result.getString(i++);
                String description = result.getString(i++);
                String imageURI = result.getString(i++);
                double price = result.getDouble(i++);
                origin = result.getString(i++);
                destination  = result.getString(i++);
                String carrier  = result.getString(i++);
                String arrivalTime = result.getString(i++);
                String departureTime = result.getString(i++);
                String travelClass = result.getString(i++);
                transportations.add(new Transportation(transportId , name, description, imageURI, price,origin,destination,carrier,arrivalTime,departureTime,travelClass));
            }
            while(result.next());
            
            return(transportations);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "Transportation details; origin = " + origin +" , "+"destination = "+destination+" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    public Transportation getTransportation(String id, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_TRANSPORTATION_QUERY_STR);
            stmt.setString(1, id.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data found for  " + id +" , "+locale.toString());
            int i = 1;
            String transportId = result.getString(i++);
            String name = result.getString(i++);
            String description = result.getString(i++);
            String imageURI = result.getString(i++);
            double price = result.getDouble(i++);
            String origin = result.getString(i++);
            String destination  = result.getString(i++);
            String carrier  = result.getString(i++);
            String arrivalTime = result.getString(i++);
            String departureTime = result.getString(i++);
            String travelClass = result.getString(i++);
            return new Transportation(transportId , name, description, imageURI,
            price, origin, destination, carrier, arrivalTime, departureTime, travelClass);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "Transportation details; id = " + id +" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    
    public ArrayList getActivities(String location, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        ArrayList activities = new ArrayList();
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_ACTIVITIES_QUERY_STR);
            stmt.setString(1, location.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data dound for  " + location +" , "+locale.toString());
            do {
                int i = 1;
                String activityId = result.getString(i++);
                String name = result.getString(i++);
                String description = result.getString(i++);
                double price = result.getDouble(i++);
                location = result.getString(i++);
                activities.add(new Activity(activityId , name, description, price,location));
            }
            while(result.next());
            
            return(activities);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "Activity details; location = " + location +" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
    
    
    public Activity getActivity(String id, Locale locale)
    throws CatalogDAOException, DAOSystemException {
        
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CATALOG_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_ACTIVITY_QUERY_STR);
            stmt.setString(1, id.trim());
            stmt.setString(2, locale.toString().trim());
            result = stmt.executeQuery();
            if ( !result.next() )
                throw new CatalogDAOException(
                "No data dound for  " + id +" , "+locale.toString());
            int i = 1;
            String activityId = result.getString(i++);
            String name = result.getString(i++);
            String description = result.getString(i++);
            double price = result.getDouble(i++);
            String location = result.getString(i++);
            return new Activity(activityId , name, description, price,location);
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
            "Activity details; id = " + id +" and locale = "+locale.toString()+" \n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
}
