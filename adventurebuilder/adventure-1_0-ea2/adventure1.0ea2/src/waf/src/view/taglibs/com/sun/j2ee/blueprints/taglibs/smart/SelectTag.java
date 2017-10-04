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
 $Id: SelectTag.java,v 1.3 2003/03/01 00:21:50 gmurray Exp $ */

package com.sun.j2ee.blueprints.waf.view.taglibs.smart;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.io.*;
import java.util.*;

/**
 * HTML 'select' tag.
 */
public class SelectTag extends BodyTagSupport {

    ArrayList options = null;
    String selectedValue = null;
    int size = 0;
    String name = null;
    boolean isEditable = true;

    public void setSelectedValue(String sv) { selectedValue = sv; }

    public void setSize(int s) { size = s; }
    
    public void setName(String n) { name = n; }

    public void setEditable(boolean e) { isEditable = e; }

    public void putOption(String value, String text) {
        options.add(new OptionItem(value, text));
    }

    public int doStartTag() throws JspTagException {
        options = new ArrayList();
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspTagException { 
        try {
            StringBuffer html = new StringBuffer();
            
            if (isEditable) {
                html.append("<select");
                html.append(size > 0 ? (" size=\"" + size + "\"") : "");
                html.append(" name=\"" + name + "\">");
                Iterator it = options.iterator();
                while (it.hasNext()) {
                    OptionItem item = (OptionItem) it.next();
                    String value = item.getName();
                    String text = item.getValue();
                    html.append("<option value=\"" + value + "\"");
                    html.append(value.equals(selectedValue) 
                                ? " selected>" 
                                : ">");
                    html.append(text);
                    html.append("</option>");
                }
                html.append("</select>");
            } else {
                OptionItem item = getItem(selectedValue);
                if (item != null) {
                    html.append(item.getValue());
                }
            }
            pageContext.getOut().print(html.toString());
            options = null;
            return EVAL_PAGE; 
        } catch (IOException e) {
            throw new JspTagException("SelectTag: " + e.getMessage());
        }
    }
    
    private OptionItem getItem(String key) {
          Iterator it = options.iterator();
          while (it.hasNext()) {
              OptionItem item = (OptionItem) it.next();
              if (item.getName().equals(key)) return item;
          }
          return null;
        }
    /*
     *  Holds an Option for this tag
     */
    private class OptionItem {
        private String name;
        private String value;
        
        public OptionItem (String name, String value) {
            this.name = name;
            this.value = value;            
        }
        
        public String getName() {
            return name;
        }
        
        public String getValue() {
            return value;
        }
    }
}

