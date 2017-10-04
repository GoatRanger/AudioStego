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
 $Id: AdventurePackage.java,v 1.1 2003/02/25 01:54:00 gmurray Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.util.*;

/**
 *  This class is used to represent the total package 
 *  of an advenutre which includes activities
 *  and an AdventureItem.
 */
public class AdventurePackage implements java.io.Serializable {
    
    private ArrayList activities;
    private String packageId;
    private String name;
    private String lodginglId;
    private String location;
    private String description;
    private double price;
    
    /** Creates a new instance of AdventurePackage */
    public AdventurePackage(String packageId,
                                                String name,
                                                String description,
                                                String location,
                                                String lodginglId,
                                                double price,
                                                ArrayList activities) {
        this.packageId = packageId;
        this.name = name;
        this.description = description;
        this.lodginglId = lodginglId;
        this.location = location;
        this.price = price;
        this.activities = activities;        
    }
    
    public ArrayList getActivities() {
        return activities;
    }
      
    public void setHotelItemId(String lodginglId) {
        this.lodginglId = lodginglId;
    }
    
    public String getLodgingId() {
        return lodginglId;
    }
     
    public String getLocation() {
       return location;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getPackageId() {
        return packageId;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "AdventurePackage[packageId=" + packageId + 
                   ", name=" + name +
                   ", lodginglId=" + lodginglId +
                   ", location=" + location + 
                   ", price=" + price +
                   ", activites=" + activities + "]";
    }
}
