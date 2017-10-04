/*
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package flowchart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ChartGen {

    Pattern ifBracePattern = Pattern.compile("^\\s*?if");
    Pattern ifPattern = Pattern.compile("^\\s*?if\\s*?");
    int seqNum = 0;
    public ChartGen() {
        File f = new File("C:\\Projects\\Flowcharter\\Chart.java");
        if (f.exists()) System.out.println("exists");
        System.out.println(f.getAbsolutePath());
        ArrayList text = null;
        try {
            FileReader reader = new FileReader(f);
            BufferedReader br = new BufferedReader(reader);
            String line;
            text = new ArrayList();
            while ((line = br.readLine()) != null) {
            	text.add(line);
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (text != null) {
            String[] code = new String[text.size()];
            for (int i=0; i<text.size(); i++) {
                code[i] = text.get(i).toString();
            }
            generateSequence(code,50,100);
        }
        
    }
    
    public void generateSequence(String[] code, int startX, int startY) {
        int x = startX;
        int y = startY;
        if (code == null) return;
        for (int i=0; i<code.length; i++) {
            if (code[i] == null || code[i].length() == 0) continue;
            Matcher matchIfBrace = ifBracePattern.matcher(code[i]);
            Matcher matchIf = ifPattern.matcher(code[i]);
            System.out.println(code[i]);
            if (matchIfBrace != null && matchIfBrace.find()) {
                int j=i+1;
                while (j<code.length && code[j].indexOf("}")==-1) j++;
                String[] clause = new String[j-i];
                System.arraycopy(clause,0,code,i,j-i);
                writeLine("diamond seq" + (seqNum++)+" " + x + " " + y + " 100 50 BLACK");
                generateIfSelection(clause,x+100,y);
                //generateSequence(clause,x,y+100);
                i=j;
            } else {
            
            writeLine("rect seq"+(seqNum++)+" " + x + " " + y + " 100 50 BLACK");
            x += 40;
            }
        }
    }
    
    public void generateIfSelection(String[] code, int startX, int startY) {
      
    }
    
    public void writeLine(String msg) {
        System.out.println(msg);
    }
    public static void main(String[] args) {
        double result=1;
        int i=0;
        while (i < Integer.MAX_VALUE) {
            i=i+10;
            result += Math.random()/i;
            System.out.println(result);
        }
        new ChartGen();
    }
}
