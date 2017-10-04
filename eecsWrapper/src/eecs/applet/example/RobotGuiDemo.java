package eecs.applet.example;

public class RobotGuiDemo extends eecs.applet.AppletBase
{
    public void run()
    {
        // test robot commands
        showMessage("Ready to Start");

        int realism =
            chooseButton("Realism Level", "Idealistic", "Realistic");

        if (realism == BUTTON2)
        {
            runRealisticSim();
            printLine("Using REALISTIC Mode");
        }

        createRobot(100, 200, 90);

        setPower(1);
        printLine(
            "Normal messages using printLine are displayed like this.");
        printError(
            "This is the output if the printError method is used. " +
            "(the \"ERROR:\" part is attached automatically to errors)");
        System.out.println(
            "And System.out commands are redirected in italics " +
            "(but are displayed when convenient for the system).");
        printLine("Demo display of calculated value: " +
            (.025 % 360));
        pause(1000);

        for (int power = 0; power <= 10; power++)
        {
            setPower(power);
            printLine("setPower(" + power +
                "), fwd-1000, left/right-90, back-1000");
            goForward(2000);
            pivotRight(90);
            pivotLeft(90);
            goBackward(2000);
        }

        printLine("Resetting power to 7");
        setPower(7);
        printLine("pivotLeft(360)");
        pivotLeft(360);
        goForward(2000);
        pivotRight(50);
        goBackward(2000);
        pivotLeft(90);
        printLine("A 2 Second delay");
        pause(2000);
        print("Destroying the robot...");
        destroyRobot();
        printLine("Destroyed!");

        // Remember, resets default speed setting.
        createRobot(170, 200, 180);
        goForward(4000);

        // test gui stuff
        String str = getString("Enter a name:");
        showMessage("You entered " + str);
        printLine("I'm printing in the output area");

        int n = getInt("Enter an integer:");
        showMessage("You entered " + n);
        printLine("you entered " + n);

        double d = getDouble("Enter a real number:");
        showMessage("You entered " + d);
        printLine("you now entered " + d);

        int choice =
            chooseButton("Here is a sample response box.",
                "Button A", "Button B");
        showMessage("You chose button " + choice);
        choice =
            chooseFromList("Here is a sample list box.",
                new String[] { "Item A", "Item B" });
        showMessage("You chose item " + choice);
        goBackward(3000);
        showMessage("Demonstration Complete");
    }
     // end method main
}
