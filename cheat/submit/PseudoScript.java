import java.io.*;
import java.util.*;

public class PseudoScript {

    public static void main (String[] args) throws IOException {
	if (args.length!=1) {
	    System.out.println("Usage: java PseudoScript <filename>");
	} else {     
	    File f = new File(args[0]);
	    Vector dirs = new Vector(0,1);
	    	    
	    try {
		BufferedReader br = new BufferedReader(new FileReader(f));
		while (br.ready())
		    dirs.addElement(br.readLine());
		br.close();

		Runtime rt = Runtime.getRuntime();         
		Process prcs;
		InputStreamReader isr;
		String s;
		FileWriter fw1 = new FileWriter("p1");
		FileWriter fw2 = new FileWriter("p2");
		for (int i=0; i<dirs.size(); i++) {
		    s = "sh icat.sh "+dirs.elementAt(i);
		    prcs = rt.exec(s);
		    isr = new InputStreamReader(prcs.getInputStream());
		    br = new BufferedReader(isr);
		    while (br.ready()) 
			fw1.write(br.readLine());
		    prcs.destroy();
		    for (int j=i+1; j<dirs.size(); j++) {
			s = "sh icat.sh "+dirs.elementAt(j);
			prcs = rt.exec(s);
			isr = new InputStreamReader(prcs.getInputStream());
			br = new BufferedReader(isr);
			while (br.ready()) 
			    fw2.write(br.readLine());
			prcs.destroy();
			    
			prcs = rt.exec("java Detect p1 p2");
			
			isr = new InputStreamReader( prcs.getInputStream() ); 	
			
			br = new BufferedReader( isr );   
			    
			System.out.println(i+"\t"+j);
			while (br.ready()) {
			    System.out.println(br.readLine());
			}
			
			prcs.destroy();
			isr.close();
			br.close();
			
		    }
		}
		
	    } catch (Exception e) {
		if (e instanceof FileNotFoundException)
		    System.err.println("Invalid file");
		else
		    System.err.println(e);
	    }
	}
    } // method: main
    
} // class: PseudoScript
