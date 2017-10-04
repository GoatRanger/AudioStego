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
 $Id: XMLDocumentBuilder.java,v 1.4 2002/11/21 21:41:50 inder Exp $ */

package com.sun.j2ee.blueprints.adventure.util;

// DOM
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;

import java.io.OutputStream;

public class XMLDocumentBuilder {
    
    private Document document = null;
    private Element root = null;
    
    /**
     * Creates a new instance of XMLDocumentBuilder
     * based on a Source.
     */
    public XMLDocumentBuilder(Source source) {
        Node node = deSeralize(source);
        DocumentBuilderFactory docBuilderFactory = null;
        DocumentBuilder docBuilder = null;
        try {
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            if (docBuilderFactory != null) docBuilder =
              docBuilderFactory.newDocumentBuilder();
        } catch ( javax.xml.parsers.ParserConfigurationException pce) {
            System.out.println(pce);
        }
        document = docBuilder.newDocument();
        // import the source into this document
        document.importNode(node,true);
    }
    
    /** 
     *  Creates a new instance of XMLDocumentBuilder
     *  with a root node with the given text.
     */
    public XMLDocumentBuilder(String rootNodeText) {
        DocumentBuilderFactory docBuilderFactory = null;
        DocumentBuilder docBuilder = null;
        try {
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            if (docBuilderFactory != null) docBuilder =
              docBuilderFactory.newDocumentBuilder();
        } catch ( javax.xml.parsers.ParserConfigurationException pce) {
                 System.out.println(pce);
        }
        document = docBuilder.newDocument();
        root = document.createElement(rootNodeText);
        document.appendChild(root);
    }

    /**
     *  Add a new value to the document with the specified
     *  body text value.
     */
    public Element createElement(String name, String value) {
        if (value != null) {
            Element element = (Element) document.createElement(name);
            element.appendChild(document.createTextNode(value));
            return element;
        }
        throw new IllegalArgumentException("XMLDocumentBuilder.createElement: value of " +
                                           name +
                                           " element can't be null.");
    }

    /**
     *  Create an Element with the given name.
     */
    public Element createElement(String name) {
  Element child = (Element) document.createElement(name);
  root.appendChild(child);
  return child;
    }

    /**
     *  Create a child Element with the given name and value and append it
     *  to the parent Element passed in as an argument.
     */
    public Element appendChild(Element parent, String nodeName, String value) {
        Element child = document.createElement(nodeName);
  child.appendChild(document.createTextNode(value != null ? value : ""));
  parent.appendChild(child);
        return child;
    }
    
    /**
     *  Return the Document.
     */
    public Document getDocument() {
        return document;
    }
    
    /**
     * Return the Source Object representing this Document
     */
    public Source getSource() {
        return new DOMSource(document);
    }
    
    /**
     *  Serialize the Document out to System.out
     *  encoded in UTF-8. This is mainly used for
     *  debuging
     */
    public void seralize() {
        seralize(System.out, "UTF-8");
    }
  
    /**
     *  Serialize the Document out to the desired output
     *  stream encoded in UTF-8.
     */
    public void serialize(OutputStream out) {
        seralize(out, "UTF-8");
    }

    /**
     *  Serialize the Document out to the desired output stream
     *  encoded in the specified encoding.
     */
    public void seralize(OutputStream out, String encoding) {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(out);
      try {
          Transformer transformer = transformerFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.transform(source, result);
      } catch (Exception exception) {
      exception.printStackTrace(System.err);
      }
    }
    
    /**
     * Convienience method for de-seralizing a DOM tree from
     * a source.
     */
    public static Node deSeralize(Source source) {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      DOMResult result = new DOMResult();
      try {
          Transformer transformer = transformerFactory.newTransformer();
          transformer.transform(source, result);
          return result.getNode();
      } catch (Exception exception) {
      exception.printStackTrace(System.err);
      }
      return null; 
    }
}

