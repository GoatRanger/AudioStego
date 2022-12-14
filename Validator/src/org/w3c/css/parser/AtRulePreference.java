//
// $Id: AtRulePreference.java,v 1.2 2002/04/08 21:24:12 plehegar Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.parser;

import org.w3c.css.util.InvalidParamException;

public class AtRulePreference extends AtRule {

    static int internal = 0;
    int hash;

    /**
     * Create a new AtRulePreference
     */
    public AtRulePreference() {
	hash = ++internal;
    }

    /**
     * Returns the at rule keyword
     */
    public String keyword() {
	return "preference";
    }

    /**
     * The second must be exactly the same as this one
     */
    public boolean canApply(AtRule atRule) {
	return (atRule instanceof AtRuleFontFace);
    }

    /**
     * Return true if other is an instance of AtRulePreference
     */
    public boolean equals(Object other) {
	return (other instanceof AtRulePreference);
    }
    
    /**
     * The second must only match this one
     */
    public boolean canMatched(AtRule atRule) {
	return (atRule instanceof AtRulePreference);
    }

    /**
     * Returns a string representation of the object
     */
     public String toString() {
	 return "@" + keyword();
     }

    public int hashCode() {
	return hash;
    }

}
