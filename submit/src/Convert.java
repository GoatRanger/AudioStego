import java.io.*;
import java.util.*;

public class Convert {

  public static void main (String [] args) throws IOException, FileNotFoundException {
    if (args.length!=1)
      System.out.println("Usage: java MakeFile <filename>");
    else {
      File f = new File(args[0]);
      if (f.exists()) {
	
	BufferedReader bf = new BufferedReader(new FileReader(f));	
	FileWriter fw = new FileWriter(args[0]+".t");
	
	while (bf.ready()) {
	  String this_line = getNextLine(bf);
	  if (!this_line.equals("")) {
	    writeToFile(this_line,fw);
	  } 	  
	} // end while bf ready
	
	fw.close();
	
      } // end if f exists
    }
  } // method: main

  
  public static String getNextLine(BufferedReader bf) {
    String this_line="";
    try {
      this_line = eliminateComment(bf.readLine(),bf);
      this_line = eliminatePrint(this_line,bf);
      this_line = eliminateSpace(this_line);
    } catch (IOException e) {
      System.out.println("Error2");
    }
    return this_line;
  } // method getNextLine
  
  
  private static String eliminateComment(String line, BufferedReader bf) throws IOException {
    
    int comment = contains(line,"//");
    if (comment!=-1) {                                   // has comment
      if (comment==0)
	line = "";
      else 
	line = line.substring(0,comment-1);
    }

    comment = contains(line,"/*");
    if (comment!=-1) {
      String rest_of_line = line.substring(comment+2,line.length());
      //System.out.println(rest_of_line);

      if (comment==0)
	line = "";
      else
	line = line.substring(0,comment-1);

      boolean found = false;
      do {
	comment = contains(rest_of_line,"*/");
	if (comment>-1) {
	  line+=rest_of_line.substring(comment+2,rest_of_line.length());
	  found = true;
	} else
	    rest_of_line = bf.readLine();
      } while (found==false&&bf.ready());
    }
    return line;
  } // method eliminateComment


  private static String eliminatePrint(String line, BufferedReader bf) throws IOException {
      
    int print = contains(line, "System.out.print");
    if (print>-1) {
      String rest_of_line = line.substring(print, line.length());
      line = line.substring(0,print);
      boolean found=false;
      do {
	int semicolon = contains(rest_of_line,';');
	if (semicolon>-1) {
	  line += rest_of_line.substring(semicolon+1,rest_of_line.length());
	  found=true;
	} else {
	  rest_of_line = bf.readLine();
	}
      } while (found==false&&bf.ready());

    }
    return line;
  } // method: eliminatePrint


  private static String eliminateSpace(String line) {
    StringTokenizer st_white = new StringTokenizer(line);
    if (st_white.countTokens()!=0)
      line=st_white.nextToken();
    while (st_white.countTokens()>1) {
      line = line+"\n"+st_white.nextToken();
    }
    if (st_white.countTokens()!=0) {
      String s = st_white.nextToken();
      if (s.equals("\n"))
	line=line+s;
      else
	line=line+"\n"+s;
    }
    return line;
  } // method: eliminateSpace


  private static int contains(String s, char c) {
    int return_value = -1;
    for (int i=0; i<s.length(); i++) {
      if (s.charAt(i)==(c)) {
	return_value = i;
	break;
      }
    }
    return return_value;
  } // method contains



  private static int contains(String s1, String s2) {
    int return_value = -1;
    s1 = s1+" ";
    //System.out.println("-"+s1+"-"+"\t"+"-"+s2+"-");
    if (s1.length()==s2.length()) {
	if (s1.equals(s2))
	    return_value=0;
    } else if (s2.length() < s1.length()) {
	for (int i=0; i<s1.length()-s2.length(); i++) {
	    //    System.out.println("-"+s1.substring(i,i+s2.length())+"-");
	    if (s1.substring(i,i+s2.length()).equals(s2)) {
		return_value = i;
		break;
	    }
	}
    }
    return return_value;
  } // method contains


  private static void writeToFile(String this_line,FileWriter fw) {
    try {
      StringTokenizer st = new StringTokenizer(this_line,"({;})=<>!\".[]",true); 
      
      while (st.hasMoreTokens()) { 
	String s = st.nextToken();
	if (s.startsWith("\n")&&s.length()>1)
	  s=s.substring(1,s.length());
	if (s.endsWith("\n")&&s.length()>1)
	  fw.write(s);
	else if (!s.endsWith("\n")&&s.length()>0)
	  fw.write(s+"\n");
      }
    } catch (IOException e) {
      System.out.println("Error3");
    }
  } // method writeToFile
  
} // class: Convert




