import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

/**
 * Schedule a task that executes once every second.
 */

public class SkippingBeep {
    Toolkit toolkit;
    Timer timer;

    public SkippingBeep() {
	toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(),
	               0,        //initial delay
	               1*1000);  //subsequent rate
    }

    class RemindTask extends TimerTask {
	int numWarningBeeps = 3;

        public void run() {
	    if (numWarningBeeps-- > 0) {
	        long time = System.currentTimeMillis();
	        if (time - scheduledExecutionTime() > 5) {
	            return;
	        }

		//If it's not too late, beep.
	        toolkit.beep();
		System.out.println("Beep!");
	    } else {
	        toolkit.beep(); 
                System.out.println("Time's up!");
	        //timer.cancel(); //Not necessary because we call System.exit
	        System.exit(0);   //Stops the AWT thread (and everything else)
	    }
        }
    }

    public static void main(String args[]) {
	System.out.println("About to schedule task.");
        new SkippingBeep();
	System.out.println("Task scheduled.");
    }
}
