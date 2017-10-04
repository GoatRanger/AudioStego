package eecs.robot.instructor.becknel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DayTrader extends eecs.Gui {
  private static int[] daysInMonthArray = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  private static double[][][] quoteArray = new double[5][12][31];
  private static boolean testing = false;

  public static int getDaysInMonth(int y, int m) {
    if (m != 1) {
      return daysInMonthArray[m - 1];
    }
    if (((y % 4) == 0) && (m == 1)) {
      return (daysInMonthArray[m - 1] + 1);
    }
    return daysInMonthArray[m - 1];
  }

  public static void loadQuoteArray() {
    try {
      String file;
      String record;
      Calendar c = new GregorianCalendar(2004, 0, 1);
      int test = 0;
      FileReader fr = null;
      InputStream inputStream = null;
      InputStreamReader inputStreamReader = null;
      BufferedReader br = null;
      test = chooseButton("Would you like to run the default file?", "Yes", "No");
      if (test == BUTTON1) {
        inputStream = DayTrader.class.getResourceAsStream("csco.txt");
        inputStreamReader = new InputStreamReader(inputStream);
        br = new BufferedReader(inputStreamReader);
        openWriteFile("test.out");
        testing = true;
      }
      else {
        file = getString("Enter the filename of your stock quote file: ");
        fr = new FileReader(file);
        br = new BufferedReader(fr);
      }
      while ((c.get(Calendar.YEAR) < 2009) && ((record = br.readLine()) != null)) {
        quoteArray[c.get(Calendar.YEAR) - 2004][c.get(Calendar.MONTH)][c.get(Calendar.DATE) - 1] = Double.parseDouble(record);
        c.add(Calendar.DATE, 1);
      }
      br.close();
      if (test != BUTTON1)
        fr.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static double getQuote(int y, int m, int d, int offset) {
    //need to fix m to m-1 after the project
    GregorianCalendar c = new GregorianCalendar(y, m, d);
    if (offset > 0)
      return (-1.0);
    c.add(Calendar.DATE, offset);
    return quoteArray[c.get(Calendar.YEAR) - 2004][c.get(Calendar.MONTH)][c.get(Calendar.DATE) - 1];
  }

  public static double getDiffQuote(int y, int m, int d, int offset) {
    if (offset > 0)
      return -1.0;
    return (getQuote(y, m, d, offset) - getQuote(y, m, d, offset - 1));
  }

  public static void printLineHold(int y, int m, int d, double b, double s) {
    NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
    double p = (double) ((int) (s * 1000)) / 1000;
    printLine("Hold: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
        + f.format(getQuote(y, m, d, 0)));
    printLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    if (testing) {
      writeLine("Hold: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
          + f.format(getQuote(y, m, d, 0)));
      writeLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    }
    return;
  }

  public static void printLineSell(int y, int m, int d, double b, double s) {
    NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
    double p = (double) ((int) (s * 1000)) / 1000;
    printLine("Sell: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
        + f.format(getQuote(y, m, d, 0)));
    printLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    if (testing) {
      writeLine("Sell: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
          + f.format(getQuote(y, m, d, 0)));
      writeLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    }
    return;
  }

  public static void printLineBuy(int y, int m, int d, double b, double s) {
    NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
    double p = (double) ((int) (s * 1000)) / 1000;
    printLine("Buy: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
        + f.format(getQuote(y, m, d, 0)));
    printLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    if (testing) {
      writeLine("Buy: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
          + f.format(getQuote(y, m, d, 0)));
      writeLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    }
    return;
  }

  public static void printLineFinal(int y, int m, int d, double b, double s) {
    NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
    double p = (double) ((int) (s * 1000)) / 1000;
    printLine("Congratulations!: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
        + f.format(getQuote(y, m, d, 0)));
    printLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
    if (testing) {
      writeLine("Congratulations!: " + m + "/" + d + "/" + y + " " + f.format(b) + " in the bank, and " + p + " shares of BGS at "
          + f.format(getQuote(y, m, d, 0)));
      writeLine("Your total net worth is " + f.format(b + s * getQuote(y, m, d, 0)));
      closeWriteFile();
      Diff diff = new Diff();
      try {
        diff.doDiff(new FileInputStream("test.out"), DayTrader.class.getResourceAsStream("155solution.txt"));
      }
      catch (FileNotFoundException e) {
        // 
        e.printStackTrace();
      }
    }
    return;
  }
}