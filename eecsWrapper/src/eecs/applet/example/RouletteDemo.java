/*
 * RouletteDemo.java
 *
 * Created on August 23, 2002, 2:17 PM
 */
package eecs.applet.example;

/**
 * DOCUMENT ME!
 *
 * @author Karl A. Gossett
 */
public class RouletteDemo extends eecs.RouletteGui
{
    /**
     * DOCUMENT ME!
     */
    public void run()
    {
        printLine("Roulette Simulation Demo");
        printLine("------------------------");
        showMessage("Press OK to start the demo");
        printLine(
            "All of the standard input/output functions work in this" +
            " gui.  Additionally, the following commands are used:");
        printCode("   pauseFor(int);  // Pauses for the specified ms");
        printCode("   spin();  // Spins the wheel");
        pauseFor(1000);
        spin();
        printCode(
            "   int space = getBallSpace();  // which space the ball stopped on");
        printLine("      (in this case, \"space\" equals " +
            getBallSpace());

        printCode("   int space = getSpaceColor();");
        print("      (in this case, \"space\" equals ");

        if (getSpaceColor() == BLACK_SPACE)
        {
            printLine("BLACK_SPACE");
        }
        else if (getSpaceColor() == RED_SPACE)
        {
            printLine("RED_SPACE");
        }
        else if (getSpaceColor() == GREEN_SPACE)
        {
            printLine("GREEN_SPACE");
        }

        printLine();
        pauseFor(500);
        printLine(
            "Additionally, the following commands place markers:");
        printCode("   addChipNumber(int)");
        addChipNumber(1);
        addChipNumber(11);
        addChipNumber(36);
        pauseFor(2500);
        printCode("   addChipColor(String)");
        addChipColor("black");
        addChipColor("red");
        pauseFor(2500);
        printCode("   addChipOddEven(String)");
        addChipOddEven("odd");
        addChipOddEven("even");
        pauseFor(2500);
        printCode("   addChipHighLow(String)");
        addChipHighLow("high");
        addChipHighLow("low");
        pauseFor(2500);
        printCode("   addChipSection(String)");
        addChipSection("1st");
        addChipSection("2nd");
        addChipSection("3rd");
        pauseFor(2500);
        printCode("   addChipColumn(String)");
        addChipColumn("left");
        addChipColumn("middle");
        addChipColumn("right");
        pauseFor(2500);
        printLine();
        showMessage("This is the end of the roulette demo");
        spin();
        printLine("Finally, to clear all chips:");
        printCode("    clearTable();");
        clearTable();
        printLine("Note that the wheel is still spinning...");
        printLine(
            "The only commands that wait until it stops are the two " +
            "to get the location & color where the ball landed.");
    }
}
