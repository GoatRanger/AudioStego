import java.io.*;
import java.util.*;

class SummaryDetect {
  
  public static void main (String[] args) throws IOException {
    if (args.length!=2) 
      System.out.println("\tUsage: java SW <filename> <filename>");
    else {
      File f1 = new File(args[0]);
      File f2 = new File(args[1]);
      
      try {
	if (f1.exists()&&f2.exists()) {
	  BufferedReader bf1 = new BufferedReader(new FileReader(f1));
	  BufferedReader bf2 = new BufferedReader(new FileReader(f2));	
	  
	  Vector sequence1 = new Vector(0,1);
	  Vector sequence2 = new Vector(0,1);

	  sequence1.addElement("");
	  while (bf1.ready()) 
	    sequence1.addElement((char)bf1.read()+"");
	  

	  sequence2.addElement("");
	  while (bf2.ready())        
	    sequence2.addElement((char)bf2.read()+"");

	  MatrixNode[][] matrix =
	    new MatrixNode[sequence1.size()][sequence2.size()];
	  // intitialize
	  for (int row=0; row<sequence1.size(); row++) {
	    for (int column=0; column<sequence2.size(); column++) {
	      matrix[row][column] = new MatrixNode();
	    }
	  }


	  int max = -1;  
	  int d = 8;     // gap penalty
	  int prev = -1; 
	  int left = -1;
	  int diag = -1;
	  int right = -1;
	  for (int row=1; row<sequence1.size(); row++) {
	      for (int column=1; column<sequence2.size(); column++) {
		  max = -1;
		  left = (matrix[row-1][column]).getValue()-d;
		  diag = (matrix[row-1][column-1]).getValue()+
		      getSubstVal((String)sequence1.elementAt(row),
				  (String)sequence2.elementAt(column));
		  right = (matrix[row][column-1]).getValue()-d;
		  
		  if (left>diag) {		  
		      max = left;
		      prev = 1;
		  } else {
		      max = diag;
		      prev = 2;
		  }
		  if (right>max) {
		      max = right;
		      prev = 3;
		  }
		  if (0>max) {
		      max = 0;
		      prev = 0;
		  }
		  (matrix[row][column]).setValue(max);
		  (matrix[row][column]).setPrevious(prev);
	      }
	  } // end for
	  
	  
	  max = -1;
	  int row_max = -1;
	  int column_max = -1;
	  int current = -1;
	  for (int row=0; row<sequence1.size(); row++) {
	      for (int column=0; column<sequence2.size(); column++) {
		  current = (matrix[row][column]).getValue();
		  if (current > max) {
		      max = current;
		      row_max = row;
		      column_max = column;
		  }
	      }
	  }
	  
	  Vector a = new Vector(0,1);
	  Vector b = new Vector(0,1);
	  int cur_row = row_max;
	  int cur_col = column_max;
	  prev = -1;
	  a.addElement(sequence1.elementAt(cur_row));
	  b.addElement(sequence2.elementAt(cur_col));
	  while (prev!=0) {
	      prev = (matrix[cur_row][cur_col]).getPrevious();	    
	      
	      if (prev==1){
		  a.addElement("-");
		  b.addElement(sequence2.elementAt(cur_col-1));
		  cur_row--;
	      } else if (prev==2) {
		  a.addElement(sequence1.elementAt(cur_row-1));
		  b.addElement(sequence2.elementAt(cur_col-1));
		  cur_row--;
		  cur_col--;
	      } else if (prev==3) {
		  a.addElement(sequence1.elementAt(cur_row-1));
		  b.addElement("-");
		  cur_col--;
	      } else if (prev==0) {
		  // do nothing, exit loop
	      } else {
		  System.out.println("error: prev = "+prev);
		  System.out.println("row: "+cur_row+" column: "+cur_col);
	      }
	  }
	  
	  
	  System.out.println("Alignment score = "+max+" for "+f1.getName()+
			     " and "+f2.getName()+".");
	  if (max > 40) {
	      System.out.println("*****CHEATING DETECTED*****");
	      printVector(a);
	      System.out.println("\n");
	      printVector(b);
	      System.out.println("\n");
	  }
	}
      } catch (Exception e) {
	  if (e instanceof FileNotFoundException) {
	      String message = "";
	      if (!f1.exists())
		  message+="No such file: "+f1+"\n";
	      if (!f2.exists())
		  message+="No such file: "+f1+"\n";
	      System.out.println(message);
	  } else
	      e.printStackTrace();
      }  // catch
    }  // else
  } // method: main
    
    
  
  private static void printVector(Vector v) {
    for (int i=v.size()-1; i>0; i--) {
      System.out.print(v.elementAt(i)+" ");
    }
  }
  
  
  private static int getSubstVal(String s1, String s2) {
    s1 = s1.toLowerCase();
    s2 = s2.toLowerCase();

    if (s1.equals(s2))
      return 5;
    else return -5;
  } // method: getSubstVal
  
  
} // class: Detect









