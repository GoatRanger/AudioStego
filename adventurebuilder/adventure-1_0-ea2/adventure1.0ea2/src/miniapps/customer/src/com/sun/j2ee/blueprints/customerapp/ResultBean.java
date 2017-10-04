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
 $Id: ResultBean.java,v 1.5 2002/11/17 00:38:49 vijaysr Exp $ */

package com.sun.j2ee.blueprints.customerapp;

import java.io.Serializable;
import com.sun.j2ee.blueprints.customer.*;


/**
 * A JavaBeans component representing an account.
 */
public class ResultBean extends Account implements Serializable {


    public ResultBean(String userId , ContactInformation info) {
      super(userId , info);
    }
     public ResultBean() {
     
    }

   //getter methods are provided in the base class

   //setter methods

   public void setUserId(String userId) {
        this.userId = userId;
    }
   public void setContactInformation(ContactInformation info){
       this.info = info;
  }

    public String toString() {
      String space = " ";
      String ret =  getUserId() + space  + getContactInformation().getAddress().getStreetName1() + space + getContactInformation().getAddress().getStreetName2() + space  + getContactInformation().getAddress().getCity() + space  + getContactInformation().getAddress().getState() + space + getContactInformation().getAddress().getCountry() + space  + getContactInformation().getAddress().getZipCode() + space + getContactInformation().getGivenName() + space + getContactInformation().getFamilyName() + space + getContactInformation().getEMail() + space + getContactInformation().getTelephone() + "\n";
      return ret;
    }

}
