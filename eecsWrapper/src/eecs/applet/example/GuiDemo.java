package eecs.applet.example;

public class GuiDemo extends eecs.applet.AppletBase
{
    public void run()
    {
        int i; // used for lots of loops

        printLine("Here is some sample output.");
        printLine("Here is some more sample output.");
        showMessage("Clear the screen.");
        clearOutput();

        // MessageBox stuff
        showMessage("Here is a sample message box.");

        // InputBox stuff
        i = getInt("Enter an integer:");
        printLine("You entered " + i + ".");

        double x = getDouble("Enter a real number:");
        printLine("You entered " + x + ".");

        // ResponseBox stuff
        int choice;

        do
        {
            choice =
                chooseButton("Press button or close window.", "Red",
                    "Green");

            if (choice == BUTTON1)
            {
                printLine("You pressed Red.");
            }
            else if (choice == BUTTON2)
            {
                printLine("You pressed Green.");
            }
        }
        while (choice != CANCEL);

        // ListBox stuff
        String[] items = new String[15];

        for (i = 0; i < 15; i++)
            items[i] = "Item " + i;

        do
        {
            choice =
                chooseFromList("Choose an item or close the window.",
                    items);
            printLine("You chose Item " + choice + ".");
        }
        while (choice != CANCEL);

        // Format stuff
        printLine("Demo the format stuff...");

        for (i = 0; i < 5; i++)
        {
            printLine(leftAlign(10, 3, (30 * Math.random()) - 10) +
                centerAlign(10, 4, (30 * Math.random()) - 10) +
                rightAlign(10, 2, (30 * Math.random()) - 10));
        }

        // ReadFile stuff
        printLine("Dump a file");
        openReadFile();

        while (moreToRead())
            printLine(readLine());

        closeReadFile();

        // WriteFile stuff
        showMessage("Write some data to a file...");
        openWriteFile();
        write(1);
        write(true);
        write('X');
        writeLine("hello");
        write(Math.PI);
        writeLine("goodbye");
        closeWriteFile();
    }

    // end method main
}
 // end class GuiDemo
