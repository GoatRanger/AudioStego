/*
 * Waveform.java
 *
 * Created on July 1, 2003, 4:26 PM
 */
package edu.eecs.sound;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 *
 * @author  DK8685
 */
public class Waveform extends javax.swing.JInternalFrame {
  static int static_id = 0;
  SoundGraph graph;
  SoundGraph processGraph;
  AudioCapture capturer;
  int id;
  byte[] dataset;
  byte[] displayedSet;

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem fftMenuItem;
  private javax.swing.JMenuItem findWordMenuItem;
  private JMenuBar jMenuBar1;
  private JMenu optionsMenu;
  private JMenu recordMenu;
  private JMenuItem recordMenuItem;


  public Waveform() {
    this(static_id++);
  }

  /** Creates new form Waveform */
  public Waveform(int reference) {
    initComponents();
    setTitle("Waveform " + reference);
    graph = new SoundGraph();
    getContentPane().add(graph, java.awt.BorderLayout.CENTER);
    processGraph = new SoundGraph("Transformed", 300, 150);
    getContentPane().add(processGraph, java.awt.BorderLayout.SOUTH);
    id = reference;
    setVisible(true);
    this.toFront();
  }

  public void setData(byte[] data) {
    dataset = new byte[data.length];
    displayedSet = new byte[data.length];
    System.arraycopy(data, 0, dataset, 0, data.length);
    System.arraycopy(data, 0, displayedSet, 0, data.length);
    graph.setData(displayedSet);
    repaint();
  }

  public byte[] getData() {
    if (processGraph.data != null) {
      return processGraph.data;
    }

    return displayedSet;
  }

  public int getID() {
    return id;
  }

  public void showWord() {
    if (dataset == null) {
      return;
    }
    displayedSet = extractWord();
		graph.setData(displayedSet);
  }
  public byte[] extractWord() {
    if (dataset==null) return new byte[0];
    int i=0;
    int period = 40;
    int threshold = 50;
    int aboveCount = 0;
    while (Math.abs(dataset[i])-threshold <= 0 && i < dataset.length-1) {
      i++;
    }
    
    int start = i;
    i=dataset.length-1;
    while (Math.abs(dataset[i])-threshold <= 0 && i>start+100) {
        i--;
    }

    int end = i;
    byte[] trimmed = new byte[end - start];
    if (end-start < 10) {
      JOptionPane.showMessageDialog(this, "Insufficient Data Captured (too quiet?)");
    }
    System.out.println("Start: " + start + ", end: " + end);
    System.arraycopy(dataset, start, trimmed, 0, end - start);
    return trimmed;
  }
  /** This method is called from within the constructor to
   * initialize the form.
   */
  private void initComponents() { //GEN-BEGIN:initComponents
    jMenuBar1 = new javax.swing.JMenuBar();
    optionsMenu = new javax.swing.JMenu();
    recordMenu = new JMenu();
    findWordMenuItem = new javax.swing.JMenuItem();
    fftMenuItem = new JMenuItem();
    recordMenuItem = new JMenuItem();
    
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    setDoubleBuffered(true);
    setMinimumSize(new java.awt.Dimension(200, 200));
    setPreferredSize(new java.awt.Dimension(400, 500));

    try {
      setSelected(true);
    } catch (java.beans.PropertyVetoException e1) {
      e1.printStackTrace();
    }

    optionsMenu.setText("Waveform");
    findWordMenuItem.setText("Remove Silence");
    findWordMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          findWordMenuItemActionPerformed(evt);
        }
      });

    optionsMenu.add(findWordMenuItem);

    fftMenuItem.setText("Display FFT");
    fftMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          fftMenuItemActionPerformed(evt);
        }
      });

    optionsMenu.add(fftMenuItem);

    jMenuBar1.add(optionsMenu);
    recordMenu.setText("Recording");
    recordMenuItem.setText("View Recording");
    recordMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          recordMenuItemActionPerformed(evt);
        }
      });

    recordMenu.add(recordMenuItem); 
    jMenuBar1.add(recordMenu);   
    setJMenuBar(jMenuBar1);
    capturer = new AudioCapture();
    getContentPane().add(capturer, java.awt.BorderLayout.NORTH);
    pack();
  }
   //GEN-END:initComponents
  private void recordMenuItemActionPerformed(ActionEvent evt) {
    if (capturer.isBufferFull()) {
      setData(capturer.getData());
    }
  }
  
  private void fftMenuItemActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_fftMenuItemActionPerformed

    // Add your handling code here:
    if (displayedSet == null) {
      return;
    }

    int len = displayedSet.length;
    int size = 1;

    while (size < len) {
      size *= 2;
    }

    float[] data = new float[size];
    java.util.Arrays.fill(data, (float) 0.0);

    for (int i = 0; i < displayedSet.length; i++) {
      data[i] = displayedSet[i];
    }

    FastFourierTransform fft = new FastFourierTransform();
    float[] result = fft.fftMag(data);
    byte[] bResult = new byte[result.length];

    // For now, just display the real pdata[0]t (not sure what else to do here)
    float max = 0;

    for (int i = 0; i < result.length; i++) {
      max = Math.max(max, result[i]);
    }

    System.out.println("Maximum: " + max);

    for (int i = 0; i < result.length; i++) {
      bResult[i] = (byte) ((result[i] * 300) / max);
    }

    processGraph.setData(bResult);
  }

  private void findWordMenuItemActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_findWordMenuItemActionPerformed

    showWord();
  }
}
