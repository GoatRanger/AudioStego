package eecs;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.EmptyStackException;

public class WorkAround extends Thread {
  private static final long timeToLive = 8 * 1000;
  private boolean shutdown = false;
  private WorkAround.DesktopIntegrationKiller dik = null;

  public WorkAround() {
    setDaemon(true);
    String javawsVersion = System.getProperty("javawebstart.version", "");
    if (javawsVersion.startsWith("javaws-1.4.2")) {
      //      System.out.println("WA4845341: Detected Java WebStart 1.4.2");
      try {
        EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();
        dik = new WorkAround.DesktopIntegrationKiller();
        eq.push(dik);
      }
      catch (Exception e) {
        //        System.err.println("WA4845341: Could not get System's EventQueue.");
      }
      start();
    }
  }

  public void run() {
    try {
      sleep(timeToLive);
    }
    catch (InterruptedException ie) { //
    }
    shutdown();
  }

  public void shutdown() {
    if (shutdown == false && dik != null) {
      shutdown = true;
      dik.pop();
    }
  }

  class DesktopIntegrationKiller extends EventQueue {
    public DesktopIntegrationKiller() {
      //      System.out.println("WA4845341: Started.");
    }

    protected void dispatchEvent(AWTEvent e) {
      if (e instanceof WindowEvent) {
        WindowEvent we = (WindowEvent) e;
        String name = we.getWindow().getAccessibleContext().getAccessibleName();
        if (name != null) {
          // The name does not seems to be localized
          if (name.endsWith("Desktop Integration")) {
            we.getWindow().setVisible(false);
            we.getWindow().dispose();
            //            System.out.println("WA4845341: Integration Window killed.");
            WorkAround.this.shutdown();
            return;
          }
        }
      }
      super.dispatchEvent(e);
    }

    public void pop() {
      try {
        super.pop();
        //        System.out.println("WA4845341: Stopped.");
      }
      catch (EmptyStackException ese) { //
      }
    }
  }
}