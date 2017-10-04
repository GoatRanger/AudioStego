/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: EmptyEnumeration.java,v 1.2 2002/04/08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.util;

import java.util.*;

public class EmptyEnumeration implements Enumeration {
    public boolean hasMoreElements() {
	return false;
    }
    
    public Object nextElement() {
	return null;
    }
}
