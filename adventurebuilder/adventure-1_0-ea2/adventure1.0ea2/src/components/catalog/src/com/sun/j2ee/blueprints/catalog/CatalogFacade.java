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
 $Id: CatalogFacade.java,v 1.6 2003/02/25 01:53:28 gmurray Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.util.Locale;
import java.util.ArrayList;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAO;
import com.sun.j2ee.blueprints.catalog.dao.JNDINames;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAOException;
import com.sun.j2ee.blueprints.util.dao.DAOFactory;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * This class is used by clients wanting to access the catalog data
 **/
public class CatalogFacade {
    
    private CatalogDAO catalogDao = null;
    
    public CatalogFacade() {
        catalogDao = (CatalogDAO) DAOFactory.getDAO(JNDINames.CATALOG_DAO_CLASS);
    }
    
    public ArrayList getLodgings(String location, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getLodgings(location,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
     public Lodging getLodging(String id, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getLodging(id,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    
    public AdventurePackage getAdventurePackage(String packageId,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getAdventurePackage(packageId,locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public ArrayList getTransportations(String origin, String destination,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getTransportations(origin, destination, locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public Transportation getTransportation(String id,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getTransportation(id,locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public ArrayList getActivities(String location, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getActivities(location,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public Activity getActivity(String id, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getActivity(id,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
}
