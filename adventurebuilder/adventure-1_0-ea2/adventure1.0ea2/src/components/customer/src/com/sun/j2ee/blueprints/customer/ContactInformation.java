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
 $Id: ContactInformation.java,v 1.5 2002/11/22 21:13:39 brydon Exp $ */

package com.sun.j2ee.blueprints.customer;


/**
 * This class represents all the data needed to
 * identify an indvidual and contact that individual.
 * This class is meant to be immutable.
 */
public class ContactInformation implements java.io.Serializable {

    private String telephone;
    private String email;
    private Address address;
    private String familyName;
    private String givenName;

    public ContactInformation() {}

    public ContactInformation(String familyName,
                String givenName,
                String telephone,
                String email,
                Address address){

        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public String getGivenName(){
        return givenName;
    }

    public String getFamilyName(){
        return familyName;
    }

    public String getEMail(){
        return email;
    }

    public Address getAddress(){
        return address;
    }
    public String getTelephone(){
        return telephone;
    }

    public String toString(){
        return "[familyName=" + familyName + ", givenName=" +
            givenName + ", telephone=" + telephone + ", email=" +
            email + ",  address=" + address+ "]";
    }

}
