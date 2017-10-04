/*
 * AudioVisualizer.java
 *
 * Created on June 30, 2003, 3:40 PM
 */

package edu.eecs.sound;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author  DK8685
 */
public class AudioVisualizer extends javax.swing.JFrame {
    
    SoundGraph graph;
    AudioCapture capture;
    final static int MAX_SAMPLES = 10;
    Waveform[] waveform = new Waveform[MAX_SAMPLES];
    
    int numberOfFrames = 0;
    int activeFrame = 0;
    /** Creates new form AudioVisualizer */
    public AudioVisualizer() {
        initComponents();
        waveform[0] = new Waveform();
        jDesktopPane1.add(waveform[0], javax.swing.JLayeredPane.DEFAULT_LAYER);
        capture = new AudioCapture();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newItem = new javax.swing.JMenuItem();
        actionsMenu = new javax.swing.JMenu();
        captureMenuItem = new javax.swing.JMenuItem();
        showMenuItem = new javax.swing.JMenuItem();

        setTitle("Sound Demo");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jDesktopPane1.setMinimumSize(new java.awt.Dimension(200, 200));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");
        newItem.setText("New Window");
        newItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemActionPerformed(evt);
            }
        });

        fileMenu.add(newItem);

        menuBar.add(fileMenu);

        actionsMenu.setText("Actions");
        captureMenuItem.setText("Capture");
        captureMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureMenuItemActionPerformed(evt);
            }
        });

        //actionsMenu.add(captureMenuItem);

        showMenuItem.setText("Show");
        showMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMenuItemActionPerformed(evt);
            }
        });

        compareMenuItem = new JMenuItem();
        //actionsMenu.add(showMenuItem);
				compareMenuItem.setText("Compare");
			  compareMenuItem.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						compareAction();
					}
				});
		
				actionsMenu.add(compareMenuItem);

        menuBar.add(actionsMenu);

        setJMenuBar(menuBar);

        pack();
    }

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {

        if (numberOfFrames >= MAX_SAMPLES) return;
        numberOfFrames++;
        waveform[numberOfFrames] = new Waveform();
        jDesktopPane1.add(waveform[numberOfFrames], javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setSelectedFrame(waveform[numberOfFrames]);
        waveform[numberOfFrames].toFront();
    }

    private void showMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

        javax.swing.JInternalFrame frame = jDesktopPane1.getSelectedFrame();
        if (frame==null) {
            frame = waveform[0];
        }
        ((Waveform)frame).setData(capture.getData());
    }

    private void captureMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

        capture.setVisible(true);
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }
    
    private void compareAction() {
    	JInternalFrame[] frames = jDesktopPane1.getAllFrames();
    	String[] windows = new String[frames.length];
    	for (int i=0; i<windows.length; i++) { 
    		windows[i] = frames[i].getTitle();
    	}
    	int formNum = JOptionPane.showOptionDialog(null, "Select Waveform to validate","WaveForm List",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,windows,windows[0]);
      System.out.println(formNum);
    	compareAll(formNum);
    	//compareWaveforms(((Waveform)frames[form]).getData(), ((Waveform)frames[form2]).getData());
    }
    
    private void compareAll(int base) {
		  JInternalFrame[] frames = jDesktopPane1.getAllFrames();
      Byte[] baseArray = getByteArray(((Waveform)frames[base]).extractWord());
      double baseMin = getMinimum(baseArray);
      double baseMax = getMaximum(baseArray);
      double baseAvg = getAverage(baseArray);
      double baseStdDev = getStdDev(baseArray);
      
      double[] minimum = new double[frames.length];
      double[] maximum = new double[frames.length];
      double[] average = new double[frames.length];
      double[] stdDev = new double[frames.length];
		  String all = "Base Waveform: \r\n" + getWaveformStats(((Waveform)frames[base]).extractWord());
      double error = Double.MAX_VALUE;
      int closest = base;
    	for (int other=0; other < frames.length; other++) {
    		if (other==base) continue;
        Byte[] otherBytes = getByteArray(((Waveform)frames[other]).extractWord());
        minimum[other] = getMinimum(otherBytes);
        maximum[other] = getMaximum(otherBytes);
        average[other] = getAverage(otherBytes);
        stdDev[other] = getStdDev(otherBytes);
        double stdErr = Math.pow(baseMin-minimum[other],2.0) +
        Math.pow(baseMax-maximum[other],2.0) +
        Math.pow(baseAvg-average[other],2.0) +
        Math.pow(baseStdDev-stdDev[other],2.0);
        if (stdErr < error) {
          error = stdErr;
          closest = other;
        }
        System.out.println("\r\n" + frames[other].getTitle());
    		String res = "\r\n " + frames[other].getTitle() + ": \r\n" + getWaveformStats(((Waveform)frames[other]).extractWord());
    	  all += res + "\r\n";
    	}
      if (closest==base) {
        all += "Not enough data to compare.";
      } else {
        all += "The closest, based on these stats is Waveform #" + closest;
      }
			JDialog dlg = new JDialog(this, "Results");
			JTextArea text = new JTextArea(300,300);
			text.setText(all);
      JScrollPane jsp = new JScrollPane(text);
			dlg.setSize(400,400);
			dlg.getContentPane().add(jsp);
			dlg.setVisible(true);
    }
    private Byte[] getByteArray(byte[] data) {
      Byte[] obj1 = new Byte[data.length];
      for (int i=0; i<data.length; i++) {
        obj1[i] = new Byte(data[i]);
      }
      return obj1;      
    }
    private String getWaveformStats(byte[] data) {
			Byte[] obj1 = getByteArray(data);
		  String result = "Min/Max: " + Statistics.getMinimum(obj1) + ", " + Statistics.getMaximum(obj1) + "\r\n";
		  result += "Average/Stdev: " + Statistics.getAverage(obj1) + ", " + Statistics.getStdDev(obj1)+"\r\n";
		  return result;
    }
    
    private double getMinimum(Byte[] data) {
      return Statistics.getMinimum(data);
    }

    private double getMaximum(Byte[] data) {
      return Statistics.getMaximum(data);
    }
    
    private double getAverage(Byte[] data) {
      return Statistics.getAverage(data);
    }
    
    private double getStdDev(Byte[] data) {
      return Statistics.getStdDev(data);
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new AudioVisualizer().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu actionsMenu;
    private javax.swing.JMenuItem captureMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newItem;
    private javax.swing.JMenuItem showMenuItem;
    private javax.swing.JMenuItem compareMenuItem;
    // End of variables declaration//GEN-END:variables
    
}
