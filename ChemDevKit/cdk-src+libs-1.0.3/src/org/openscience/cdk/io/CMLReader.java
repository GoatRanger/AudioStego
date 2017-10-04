/* $Revision: 8042 $ $Author: egonw $ $Date: 2007-03-07 16:14:47 +0100 (Wed, 07 Mar 2007) $
 *
 * Copyright (C) 2001-2007  Egon Willighagen <egonw@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package org.openscience.cdk.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.io.cml.CMLErrorHandler;
import org.openscience.cdk.io.cml.CMLHandler;
import org.openscience.cdk.io.cml.CMLResolver;
import org.openscience.cdk.io.cml.ICMLModule;
import org.openscience.cdk.io.formats.CMLFormat;
import org.openscience.cdk.io.formats.IResourceFormat;
import org.openscience.cdk.tools.LoggingTool;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * Reads a molecule in CML 1.x and 2.0 format.
 * CML is an XML based application {@cdk.cite PMR99}, and this Reader
 * applies the method described in {@cdk.cite WIL01}.
 *
 * @author      Egon L. Willighagen
 * @cdk.created 2001-02-01
 * @cdk.module  io
 * @cdk.keyword file format, CML
 * @cdk.bug     1455346
 * @cdk.bug     1544406
 */
public class CMLReader extends DefaultChemObjectReader {

    private XMLReader parser;
    private Reader input;
    private String url;
    
    private Map userConventions = new HashMap();

    private LoggingTool logger;

    /**
     * Define this CMLReader to take the input from a java.io.Reader
     * class. Possible readers are (among others) StringReader and FileReader.
     * FIXME: this can not be used in combination with Aelfred2 yet.
     *
     * @param input Reader type input
     * 
     * @deprecated XML reading should not be done with a Reader, but with an
     *             InputStream instead.
     */
    public CMLReader(Reader input) {
        this.init();
        this.input = input;
    }
    
    /**
     * Reads CML from an java.io.InputStream, for example the FileInputStream.
     *
     * @param input InputStream type input
     */
    public CMLReader(InputStream input) {
        this(new InputStreamReader(input));
    }
    
    public CMLReader() {
        this(new StringReader(""));
    }
    
    public void registerConvention(String convention, ICMLModule conv) {
    	userConventions.put(convention, conv);
    }

    /**
     * Define this CMLReader to take the input from a java.io.Reader
     * class. Possible readers are (among others) StringReader and FileReader.
     *
     * @param url String url which points to the file to be read
     */
    public CMLReader(String url) {
        this.init();
        this.url = url;
    }

    public IResourceFormat getFormat() {
        return CMLFormat.getInstance();
    }

    public void setReader(Reader reader) throws CDKException {
        this.input = reader;
    }

    public void setReader(InputStream input) throws CDKException {
        setReader(new InputStreamReader(input));
    }

    private void init() {
        logger = new LoggingTool(this);

        url = ""; // make sure it is not null

        boolean success = false;
        // If JAXP is prefered (comes with Sun JVM 1.4.0 and higher)
        if (!success) {
            try {
                javax.xml.parsers.SAXParserFactory spf = javax.xml.parsers.SAXParserFactory.newInstance();
                spf.setNamespaceAware(true);
                javax.xml.parsers.SAXParser saxParser = spf.newSAXParser();
                parser = saxParser.getXMLReader();
                logger.info("Using JAXP/SAX XML parser.");
                success = true;
            } catch (Exception e) {
                logger.warn("Could not instantiate JAXP/SAX XML reader: ", e.getMessage());
                logger.debug(e);
            }
        }
        // Aelfred is first alternative.
        if (!success) {
            try {
                parser = (XMLReader)this.getClass().getClassLoader().
                        loadClass("gnu.xml.aelfred2.XmlReader").
                        newInstance();
                logger.info("Using Aelfred2 XML parser.");
                success = true;
            } catch (Exception e) {
                logger.warn("Could not instantiate Aelfred2 XML reader!");
                logger.debug(e);
            }
        }
        // Xerces is second alternative
        if (!success) {
            try {
                parser = (XMLReader)this.getClass().getClassLoader().
                        loadClass("org.apache.xerces.parsers.SAXParser").
                        newInstance();
                logger.info("Using Xerces XML parser.");
                success = true;
            } catch (Exception e) {
                logger.warn("Could not instantiate Xerces XML reader!");
                logger.debug(e);
            }
        }
        if (!success) {
            logger.error("Could not instantiate any XML parser!");
        }
    }

	public boolean accepts(Class classObject) {
		Class[] interfaces = classObject.getInterfaces();
		for (int i=0; i<interfaces.length; i++) {
			if (IChemFile.class.equals(interfaces[i])) return true;
		}
		
		if (IChemFile.class.equals(classObject))
			return true;
		return false;
	}

	/**
     * Read a IChemObject from input
     *
     * @return the content in a ChemFile object
     */
    public IChemObject read(IChemObject object) throws CDKException {
      if (object instanceof IChemFile) {
        return readChemFile((IChemFile)object);
      } else {
        throw new CDKException("Only supported is reading of ChemFile objects.");
      }
    }

    // private functions

    private IChemFile readChemFile(IChemFile file) throws CDKException {
        logger.debug("Started parsing from input...");
        try {
            parser.setFeature("http://xml.org/sax/features/validation", false);
            logger.info("Deactivated validation");
        } catch (SAXException e) {
            logger.warn("Cannot deactivate validation.");
            return null;
        }
        CMLHandler handler = new CMLHandler(file);
        // copy the manually added conventions
        Iterator conventions = userConventions.keySet().iterator();
        while (conventions.hasNext()) {
        	String conv = (String)conventions.next();
        	handler.registerConvention(conv, (ICMLModule)userConventions.get(conv));
        }
        parser.setContentHandler(handler);
        parser.setEntityResolver(new CMLResolver());
        parser.setErrorHandler(new CMLErrorHandler());
        try {
            if (input == null) {
                logger.debug("Parsing from URL: ", url);
                parser.parse(url);
            } else {
                logger.debug("Parsing from Reader");
                parser.parse(new InputSource(input));
            }
        } catch (IOException e) {
            String error = "Error while reading file: " + e.getMessage();
            logger.error(error);
            logger.debug(e);
            throw new CDKException(error, e);
        } catch (SAXParseException saxe) {
            SAXParseException spe = (SAXParseException)saxe;
            String error = "Found well-formedness error in line " + spe.getLineNumber();
            logger.error(error);
            logger.debug(saxe);
            throw new CDKException(error, saxe);
        } catch (SAXException saxe) {
            String error = "Error while parsing XML: " + saxe.getMessage();
            logger.error(error);
            logger.debug(saxe);
            throw new CDKException(error, saxe);
        }
        return file;
    }

    public void close() throws IOException {
        input.close();
    }

}

