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
 $Id: SignOnFacade.java,v 1.4 2002/11/22 20:57:47 brydon Exp $ */

package com.sun.j2ee.blueprints.signon;

import com.sun.j2ee.blueprints.util.dao.DAOFactory;
import com.sun.j2ee.blueprints.signon.dao.UserDAO;
import com.sun.j2ee.blueprints.signon.dao.JNDINames;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAOFinderException;
import com.sun.j2ee.blueprints.signon.dao.InvalidPasswordException;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAODupKeyException; 


public class SignOnFacade {

    //These constants are defined by the application to constrain
    //the user input fields. They are used to validate user entries
    //independent of the database
    public static final int MAX_USERID_LENGTH = 25;
    public static final int MAX_PASSWD_LENGTH = 25;

    private UserDAO userDAO = null; 

    public SignOnFacade() {
        userDAO = (UserDAO)DAOFactory.getDAO(JNDINames.SIGNON_DAO_CLASS);
    }

    /**
     * business method used to check if a user is allowed to sign on
     * @return true if user name and password match in database
     */
    public boolean authenticate(String userName, String password) {
        boolean valid = false;
        try {
            valid = userDAO.matchPassword(userName, password);
        } catch (SignOnDAOFinderException sfx) {
            return false;
        } catch (InvalidPasswordException ix) {
            return false;
        }
        return valid;
    }

    /** business method to call to create new users **/
    public void createSignOn(String userName, String password) 
        throws SignOnLongIdException,
               SignOnInvalidCharException,
               SignOnDupKeyException {

       //validate user input against application constraints
       isInputValidLength(userName, password);
       try {
           userDAO.createUser(userName, password);
       } catch (SignOnDAODupKeyException sdke) {
           throw new SignOnDupKeyException("Duplicate User: " + userName);
       }
    }

    /**
     * Performs user input validation for the application. 
     *
     * @throws CreateException if user input not within length limits 
    **/
    private void isInputValidLength(String userName, String password) throws 
                     SignOnLongIdException, SignOnInvalidCharException {
          // check the input data
        if(userName.length() > MAX_USERID_LENGTH) {
            throw new SignOnLongIdException("User ID cant be more than " +
            MAX_USERID_LENGTH + " chars long");
        }
        if(password.length() > MAX_PASSWD_LENGTH) {
            throw new SignOnLongIdException("Password cant be more than " +
            MAX_PASSWD_LENGTH + " chars long");
        }
        if( (userName.indexOf('%') != -1) ||
        (userName.indexOf('*') != -1) ) {
            throw new SignOnInvalidCharException("User Id cannot " +
            "have '%' or '*' characters");
        }
    }
}
