
package eecs.audio;


/**
 *
 * @author  DK8685
 */
public class Waveform extends javax.swing.JPanel {
  static int static_id = 0;
  SoundGraph graph;
  int id;
  byte[] dataset;
  byte[] displayedSet;


  public Waveform() {
    this(static_id++);
  }

  /** Creates new form Waveform */
  public Waveform(int reference) {
    graph = new SoundGraph("Waveform",370,380);
    add(graph, java.awt.BorderLayout.CENTER);
    id = reference;
    setVisible(true);
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

    return displayedSet;
  }

  public int getID() {
    return id;
  }
  
}
