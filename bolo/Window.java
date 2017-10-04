package bolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {

  public static void open(JComponent component,
                          int width,
						  int height,
						  String title) {
    JFrame frame = new JFrame(title);
    frame.setBackground(Const.BACKGROUND_COLOR);
    component.setBackground(Const.BACKGROUND_COLOR);
    frame.setSize(width, height);
	frame.setResizable(false);
    frame.setContentPane(component);

    frame.addWindowListener(
	  new WindowAdapter () {
        public void windowClosing(WindowEvent event) {
          System.exit(0);
        }
      }
	);

    frame.setVisible(true);
  }
}