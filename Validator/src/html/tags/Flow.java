/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id: Flow.java,v 1.2 2002/04/08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.tree.*;

public class Flow extends HtmlTree {

    public boolean isBlock() {
	return false;
    }

    public boolean isPreformatted() {
	return false;
    }
}
