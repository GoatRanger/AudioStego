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
 $Id: LineItem.java,v 1.3 2002/11/22 21:13:39 brydon Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.io.Serializable;

/** 
 * This class represents a particular item in the Adventure Builder.
 *  Each item belongs to particular type of product
 * and has attributes like id,listprice etc.
*/
public class LineItem implements Serializable {

    private String itemId;
    private String name;
    private String description;
    private double listPrice;
    private double unitCost;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;

    public LineItem() { }

    public LineItem(String itemId,
                    String name,
                    String description,
                    double listPrice,
                    double unitCost,
                    String attr1,
                    String attr2,
                    String attr3,
                    String attr4,
                    String attr5) {

        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
        this.attr5 = attr5;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getListPrice() {
        return listPrice;
    }

    public String getAttr1() {
        return attr1;
    }
    
    public String getAttr2() {
        return attr2;
    }
    
    public String getAttr3() {
        return attr3;
    }
    
    public String getAttr4() {
        return attr4;
    }
    
    public String getAttr5() {
        return attr5;
    }
    
    public String toString() {
        return "LineItem [id=" + itemId +
               ", name=" + name + 
               ", listPrice=" + listPrice +
               ", unitCost=" + unitCost +
               ", description=" + description +
               ", attr1=" + attr1 +
               ", attr2=" + attr2 +
               ", attr3=" + attr3 +
               ", attr4=" + attr4 +
               ", attr5=" + attr5 +
               "]";
    }
}
