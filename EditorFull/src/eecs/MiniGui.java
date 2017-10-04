package eecs;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MiniGui extends MiniGuiBase {
  private static final String DEFAULT_TITLE = "Output";
  private static WorkAround wa = null;
  static {
    wa = new WorkAround();
    getProperties();
    if (!runningTests) {
      frame = new JFrame();
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
    wa.shutdown();
  }

  public static void setOutputTitle(String title) {
    frame.setTitle(title);
  }
}