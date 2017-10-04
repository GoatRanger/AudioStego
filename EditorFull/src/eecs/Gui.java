package eecs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import eecs.robot.wrapper.ImageLoader;

public class Gui extends GuiBase {
  private static final String DEFAULT_TITLE = "Output";
  static {
    getProperties();
    if (!runningTests) {
      frame = new JFrame();
      frame.setIconImage(ImageLoader.getImage(frame, "/eecs/images/eecs.gif"));
      output = new JTextArea();
      output.setFont(new Font("Monospaced", Font.PLAIN, 12));
      output.setEditable(false);
      JScrollPane pane = new JScrollPane(output);
      frame.setSize(new Dimension(550, 380));
      frame.getContentPane().add(pane);
      frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension screenSize = toolkit.getScreenSize();
      Dimension dialogSize = frame.getSize();
      frame.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
      setOutputTitle(DEFAULT_TITLE);
      frame.setVisible(true);
    }
  }

  public static void setOutputTitle(String title) {
    frame.setTitle(title);
  }
}