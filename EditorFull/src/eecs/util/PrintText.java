/*
 * Copyright (C) 2003 USMA This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package eecs.util;

/*
 * adapted from the PrintListing example in the documentation for the Java 2D API
 */
import java.awt.*;
import java.awt.print.*;

public class PrintText {
  public static void printText(String title, String text, boolean lineNumbersEnabled) {
    // Get a PrinterJob
    PrinterJob job = PrinterJob.getPrinterJob();
    // Ask user for page format (e.g., portrait/landscape)
    PageFormat pf = job.pageDialog(job.defaultPage());
    // Specify the Printable is an instance of
    // PrintListingPainter; also provide given PageFormat
    job.setPrintable(new PrintListingPainter(title, text, lineNumbersEnabled), pf);
    // Print 1 copy
    job.setCopies(1);
    // Put up the dialog box
    if (job.printDialog()) {
      // Print the job if the user didn't cancel printing
      try {
        job.print();
      }
      catch (Exception e) { /* handle exception */
      }
    }
  }
}

class PrintListingPainter implements Printable {
  private String title;
  private String text;
  private boolean lineNumbersEnabled;
  private Font titleFont = new Font("Monospaced", Font.BOLD, 10);
  private Font codeFont = new Font("Monospaced", Font.PLAIN, 9);
  private Font pageFont = new Font("Monospaced", Font.BOLD, 9);
  private Font numberFont = new Font("Monospaced", Font.PLAIN, 7);
  private int rememberedPageIndex = -1;
  private int rememberedPageOffset = 0;
  private int rememberedLineNumber = 0;
  private int offset = 0;
  private int lineNumber = 1;

  public PrintListingPainter(String title, String text, boolean lineNumbersEnabled) {
    this.title = title;
    this.text = text;
    this.lineNumbersEnabled = lineNumbersEnabled;
  }

  private boolean endOfText() {
    return (text == null) || (offset >= text.length());
  }

  private String nextLine() {
    String s = "";
    if (endOfText()) {
      return null;
    }
    for (int i = offset;; i++) {
      if (i == text.length()) {
        s = text.substring(offset, i);
        offset = i;
        break;
      }
      if (text.charAt(i) == '\r') {
        s = text.substring(offset, i);
        i++;
        if ((i < text.length()) && (text.charAt(i) == '\n')) {
          i++;
        }
        offset = i;
        break;
      }
      if (text.charAt(i) == '\n') {
        s = text.substring(offset, i);
        offset = i + 1;
        break;
      }
    }
    lineNumber++;
    return s;
  }

  public int print(Graphics g, PageFormat pf, int pageIndex) {
    try {
      if (pageIndex == rememberedPageIndex) {
        offset = rememberedPageOffset;
        lineNumber = rememberedLineNumber;
      }
      else {
        rememberedPageIndex = pageIndex;
        rememberedPageOffset = offset;
        rememberedLineNumber = lineNumber;
      }
      if (endOfText()) {
        return Printable.NO_SUCH_PAGE;
      }
      int titleX = (int) pf.getImageableX();
      int numberX = titleX;
      int codeX = lineNumbersEnabled ? (numberX + 24) : numberX;
      int pageX = (int) (pf.getImageableX() + pf.getImageableWidth()) - 66;
      int y = (int) pf.getImageableY() + 6;
      g.setColor(Color.black);
      g.setFont(titleFont);
      g.drawString(title, titleX, y);
      g.setFont(pageFont);
      y += 12;
      g.drawString("Page " + (pageIndex + 1), pageX, y);
      // Generate as many lines as will fit in imageable area
      y += 24;
      while ((y + 12) < (pf.getImageableY() + pf.getImageableHeight())) {
        if (endOfText()) {
          break;
        }
        if (lineNumbersEnabled) {
          g.setFont(numberFont);
          g.drawString("" + lineNumber, numberX, y);
        }
        String line = nextLine();
        g.setFont(codeFont);
        FontMetrics fm = g.getFontMetrics(codeFont);
        double pageWidth = pf.getImageableWidth() - codeX;
        int end;
        int start = 0;
        int numSpaces = 0;
        String spaces = "";
        while (line != null && numSpaces < line.length() && line.charAt(numSpaces) == ' ') {
          numSpaces++;
          spaces += " ";
        }
        while (start < line.length()) {
          end = line.length();
          while (fm.stringWidth(line.substring(start, end)) > pageWidth && end > start) {
            end--;
          }
          if (start > 0 && end < line.length()) {
            end -= 4;
          }
          if (end < line.length()) {
            int tempEnd = end;
            while (end - 1 > start && line.substring(end - 1, end).matches("[a-zA-Z0-9]")) {
              end--;
            }
            if (end == start)
              end = tempEnd;
          }
          int offset = 0;
          if (start > 0)
            offset += fm.stringWidth("    " + spaces);
          g.drawString(line.substring(start, end), codeX + offset, y);
          y += 12;
          start = end;
        }
        // If no text, then loop above didn't do anything, so make sure
        // we just move to the next line.
        if (line.length() == 0) {
          y += 12;
        }
      }
      return Printable.PAGE_EXISTS;
    }
    catch (Exception e) {
      e.printStackTrace();
      return Printable.NO_SUCH_PAGE;
    }
  }
}