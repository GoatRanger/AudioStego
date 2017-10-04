/* $Revision: 6707 $ $Author: egonw $ $Date: 2006-07-30 16:38:18 -0400 (Sun, 30 Jul 2006) $
 * 
 * Copyright (C) 2006  Egon Willighagen
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
 */
package net.sf.cdk.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class that creates the ${build}/*.javafiles for <i>@cdk.require</i> statements.
 * 
 * @author egonw
 */
public class MakeRequiresfilesFiles {

    private Map cdkRequire;

    private String sourceDir = null;
    private String outputDir = null;

    public MakeRequiresfilesFiles(String sourceDir, String outputDir) {
        cdkRequire = new Hashtable();
        this.sourceDir = sourceDir;
        this.outputDir = outputDir;
    }
	
    public void outputResults() {
        // output information in .javafiles and .classes files
        try {
		Iterator keys = cdkRequire.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String)keys.next();
			
			// create one file for each cdk package = key
			PrintWriter outJava = new PrintWriter(
			new FileWriter(outputDir + "/" + key + ".javafiles")
			);
			PrintWriter outClass = new PrintWriter(
			new FileWriter(outputDir + "/" + key + ".classes")
			);
			List packageClasses = (List)cdkRequire.get(key);
			Iterator classes = packageClasses.iterator();
			while (classes.hasNext()) {
			String packageClass = (String)classes.next();
			outJava.println(toAPIPath(packageClass) + ".java");
			outClass.println(toAPIPath(packageClass) + "*.class");
			}
			outJava.flush(); outJava.close();
			outClass.flush(); outClass.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    public void processJavaSourceFiles(File path) {
    	if (path.isDirectory()) {
    		File[] files = path.listFiles();
    		for (int i=files.length;i>0;i--) {
    			processJavaSourceFiles(files[i-1]);
    		}
    	} else if (path.isFile() && path.getPath().endsWith(".java") &&
    			   !(path.getPath().indexOf("net/sf") != -1 ||
                             path.getPath().indexOf("net\\sf") != -1)) {
    		Iterator requires = getRequires(path);
    		while (requires.hasNext()) {
			addClassToCDKRequire(getSourceName(path), (String)requires.next());
		}
    	}
    }
    
    public Iterator getRequires(File file) {
	List results = new ArrayList();
    	try {
			BufferedReader reader = new BufferedReader(
				new FileReader(file)
			);
			String line = null;
			boolean inComment = false;
			while ((line = reader.readLine()) != null) {
				int index = line.indexOf("/**");
				if (index != -1) {
					inComment = true;
					if (line.substring(index).indexOf("**/") != -1) inComment = false;
				} else {
					if (line.indexOf("*/") != -1) inComment = false;
				}
				
				if (!inComment && (line.indexOf("public class") != -1 ||
						line.indexOf("public interface") != -1 ||
						line.indexOf("abstract class") != -1 ||
						line.indexOf("final class") != -1)) {
					// Nothing specified: return the default 'extra'
					reader.close();
					return results.iterator();
				}
				
				index = line.indexOf("@cdk.require");
				String name = "";
				if (index != -1) {
					index += 12;
					// skip the first chars
					while (Character.isWhitespace(line.charAt(index))) index++;
					while (index < line.length() && 
						   !Character.isWhitespace(line.charAt(index))) {
						name += line.charAt(index);
						index++;
					}
					results.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return results.iterator();
    }
    
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Syntax: MakeRequiresfilesFiles <sourceDir> <outputDir>");
			System.exit(-1);
		}
		
		MakeRequiresfilesFiles processor = new MakeRequiresfilesFiles(args[0], args[1]);
		
		processor.processJavaSourceFiles(new File(args[0]));
		processor.outputResults();
		
	}
	
    private String toAPIPath(String className) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<className.length(); i++) {
            if (className.charAt(i) == '.') {
                sb.append('/');
            } else {
                sb.append(className.charAt(i));
            }
        }
        return sb.toString();
    }

    private String getSourceName(File classFile) {
    	// assume the pattern src/package/className.java
    	// return package/className
    	String tmp = classFile.getPath().substring(sourceDir.length()+1); 
        return tmp.substring(0, tmp.length()-5);
    }

    private String getClassName(File classFile) {
    	// assume the pattern src/package/className.java
    	// return package.className
    	StringBuffer sb = new StringBuffer();
    	String className = classFile.getPath().substring(sourceDir.length()+1);
        for (int i=0; i<className.length()-5; i++) {
            if (className.charAt(i) == '/' || className.charAt(i) == '\\') {
                sb.append('.');
            } else {
                sb.append(className.charAt(i));
            }
        }
        return sb.toString();
    }

    private void addClassToCDKRequire(String packageClass, String cdkPackageName) {
        List packageClasses = (ArrayList)cdkRequire.get(cdkPackageName);
        if (packageClasses == null) {
            packageClasses = new ArrayList();
            cdkRequire.put(cdkPackageName, packageClasses);
        }
        packageClasses.add(packageClass);
    }

}
