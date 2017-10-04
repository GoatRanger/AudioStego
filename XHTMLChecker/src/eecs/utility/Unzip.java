package eecs.utility;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/** A utility class for unzipping files.  Modified from the Unzip class at 
 *  {@link http://gethelp.devx.com/techtips/java_pro/10MinuteSolutions/10min0300.asp}
 *  to support the html turn-in structure used in IT105.
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Unzip {

    public static final void copyInputStream(InputStream in, OutputStream out)
        throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);

        in.close();
        out.close();
    }

    public static final void main(String[] args) {
        Enumeration entries;
        ZipFile zipFile;

        if (args.length != 1) {
            System.err.println("Usage: Unzip zipfile");
            return;
        }

        zipExtract("./",args[0]);
    }

    public static void zipExtract(String baseDir, String zipFileName) {
        Enumeration entries;
        ZipFile zipFile;
        System.out.println(zipFileName);
        try {
            zipFile = new ZipFile(zipFileName);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    // Assume directories are stored parents first then children.
                    System.err.println(
                        "Extracting directory: " + baseDir + entry.getName());
                    // This is not robust, just for demonstration purposes.
                     (new File(baseDir + entry.getName())).mkdir();
                    continue;
                }

//                System.err.println("Extracting file: " + baseDir + entry.getName());
                int slashPos = entry.getName().indexOf("/");
                int oldPos = 0;
                if (slashPos != -1) {
                  String subDirName = "";
                  while (slashPos > -1) {
                    
                 	  System.err.println("Extracting Directory: " + baseDir + subDirName + entry.getName().substring(0,entry.getName().indexOf("/")));
                	     (new File(baseDir  + "/" + subDirName + entry.getName().substring(oldPos,slashPos))).mkdir();
                    subDirName += entry.getName().substring(0,entry.getName().indexOf("/")) + "\\";
                     oldPos = slashPos;
                     slashPos = entry.getName().indexOf("/", oldPos+1);
                  }
                }
                if (entry.getName().endsWith(".zip")) {
                  zipExtract(baseDir,entry.getName());
                }
                copyInputStream(
                    zipFile.getInputStream(entry),
                    new BufferedOutputStream(
                        new FileOutputStream(baseDir + entry.getName())));
            }

            zipFile.close();
        } catch (IOException ioe) {
            System.err.println("Unhandled exception:");
            ioe.printStackTrace();
            return;
        }
    }

}
