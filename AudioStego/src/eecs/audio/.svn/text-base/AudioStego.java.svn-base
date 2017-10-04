/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.audio;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AudioStego extends JFrame {
  JPanel inputPanel;
  JPanel outputPanel;
  JPanel stegoPanel;
  JPanel inputButtons;
  JPanel outputButtons;
  JPanel inputInfo;
  JButton openFileButton;
  JButton saveFileButton;
  JButton stegoButton;
  JButton extractButton;
  JButton origPlayButton;
  JButton stegoPlayButton;
  JSlider bitSelectSlider;
  JLabel bytesAvailLabel = new JLabel("Bytes Available: ");
  JTextField bytesAvail;
  JLabel bytesUsedLabel = new JLabel("Hidden File Size: ");
  JTextField bytesUsed;
  JLabel encodingLabel = new JLabel("  Encoding: ");
  JLabel sampleRateLabel = new JLabel("  Sample Rate: ");
  JLabel sampleSizeLabel = new JLabel("  Sample Size: ");
  JLabel channelsLabel = new JLabel("  Channels: ");
  JLabel endianLabel = new JLabel("  Byte Order: ");
  JLabel stegoFileLabel = new JLabel("Hidden Content in File: ");
  JLabel channelsOutLabel = new JLabel("  Channels: ");
  JTextField inputEncoding;
  JTextField inputSampleRate;
  JTextField inputSampleSize;
  JTextField inputChannels;
  JTextField inputEndian;
  JTextField stegoFile;
  JTextField outputEncoding;
  JTextField outputSampleRate;
  JTextField outputSampleSize;
  JTextField outputChannels;
  Waveform inputForm;
  Waveform outputForm;
  AudioManager audio;

  public AudioStego() {
    audio = new AudioManager();
    initComponents();
    initActions();
    pack();
  }

  private void initActions() {
    openFileButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          extractButton.setEnabled(false);
          audio.openFile();

          if (audio.getAudioFormat() == null) {
            return;
          }

          inputPanel.setBorder(BorderFactory.createTitledBorder("Input: " +
              audio.getInputFileName()));

          AudioFormat format = audio.getAudioFormat();

          inputEncoding.setText(format.getEncoding().toString());
          inputSampleRate.setText("" + format.getSampleRate());
          inputSampleSize.setText("" + format.getSampleSizeInBits());
          inputChannels.setText("" + format.getChannels());
          inputEndian.setText("" +
            (format.isBigEndian() ? "big-endian" : "little-endian"));

          if (format != null) {
            if (format.getEncoding() == AudioFormat.Encoding.PCM_UNSIGNED) {
              byte[] data = audio.getData();

              for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] - 128);
              }

              inputForm.setData(data);
            } else {
              inputForm.setData(audio.getData());
            }
          }
          outputForm.setData(new byte[0]);
          bytesAvail.setText("" +
            ((audio.getData().length - 200) / format.getFrameSize() / 8 * bitSelectSlider.getValue()));
          audio.decode();
          stegoFile.setText(audio.getDecodeFileName());
          
          if (!audio.getDecodeFileName().equalsIgnoreCase("no contents")) {
            extractButton.setEnabled(true);
          }
        }
      });

    extractButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          audio.saveStegoFile();
        }
      });

    origPlayButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          audio.playAudio();
        }
      });

    stegoPlayButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          audio.playHiddenAudio();
        }
      });

    saveFileButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          audio.saveFile();
        }
      });

    stegoButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (audio.encodeFile(bitSelectSlider.getValue())) {
            AudioFormat format = audio.getAudioFormat();

            if (format != null) {
              if (format.getEncoding() == AudioFormat.Encoding.PCM_UNSIGNED) {
                byte[] data = audio.getStegoData();

                for (int i = 0; i < data.length; i++) {
                  data[i] = (byte) (data[i] - 128);
                }

                outputForm.setData(data);
              } else {
                outputForm.setData(audio.getStegoData());
              }
            }
            bytesUsed.setText("" + audio.getEncodedSize());
          
          }
        }
      });
  }

  private void initComponents() {
    Container pane = getContentPane();
    pane.setLayout(new BorderLayout());
    inputPanel = new JPanel();
    outputPanel = new JPanel();
    inputPanel.setSize(new Dimension(400, 450));
    inputPanel.setPreferredSize(new Dimension(400, 450));
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
    outputPanel.setSize(new Dimension(400, 450));
    outputPanel.setPreferredSize(new Dimension(400, 450));
    inputPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1),BorderFactory.createTitledBorder("Input")));
    outputPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1),BorderFactory.createTitledBorder("Output")));

    inputButtons = new JPanel();
    openFileButton = new JButton("Open File");
    origPlayButton = new JButton("Play");
    extractButton = new JButton("Save Hidden File");
    extractButton.setEnabled(false);
    inputButtons.add(openFileButton);
    inputButtons.add(origPlayButton);
    inputButtons.add(extractButton);
    inputPanel.add(inputButtons, 0);

    inputInfo = new JPanel();
    inputInfo.setBorder(BorderFactory.createTitledBorder("File Information"));
    inputInfo.setLayout(new GridLayout(0, 4, 5, 2));
    inputEncoding = new JTextField();
    inputEncoding.setEditable(false);
    inputSampleRate = new JTextField();
    inputSampleRate.setEditable(false);
    inputSampleSize = new JTextField();
    inputSampleSize.setEditable(false);
    inputChannels = new JTextField();
    inputChannels.setEditable(false);
    inputEndian = new JTextField();
    inputEndian.setEditable(false);
    stegoFile = new JTextField();
    stegoFile.setEditable(false);
    inputInfo.add(encodingLabel);
    inputInfo.add(inputEncoding);
    inputInfo.add(sampleRateLabel);
    inputInfo.add(inputSampleRate);
    inputInfo.add(sampleSizeLabel);
    inputInfo.add(inputSampleSize);
    inputInfo.add(channelsLabel);
    inputInfo.add(inputChannels);
    inputInfo.add(endianLabel);
    inputInfo.add(inputEndian);
    pane.add(inputInfo,BorderLayout.SOUTH);
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new GridLayout(0,2));
    contentPanel.add(stegoFileLabel);
    contentPanel.add(stegoFile);
    inputPanel.add(contentPanel,1);
    inputForm = new Waveform();
    inputPanel.add(inputForm, 2);

    outputButtons = new JPanel();
    saveFileButton = new JButton("Save File");
    stegoPlayButton = new JButton("Play");
    outputButtons.add(saveFileButton);
    outputButtons.add(stegoPlayButton);

    outputPanel.add(outputButtons, 0);
    outputEncoding = new JTextField();
    outputEncoding.setEditable(false);
    outputSampleRate = new JTextField();
    outputSampleRate.setEditable(false);
    outputSampleSize = new JTextField();
    outputSampleSize.setEditable(false);
    outputChannels = new JTextField();
    outputChannels.setEditable(false);

    outputForm = new Waveform();
    outputPanel.add(outputForm, 1);

    stegoPanel = new JPanel();
    stegoPanel.setSize(new Dimension(200, 500));
    stegoPanel.setPreferredSize(new Dimension(200, 500));
    stegoPanel.setBorder(BorderFactory.createTitledBorder("Hiding"));
    bitSelectSlider = new JSlider(1, 8);
    bitSelectSlider.setSize(180, 65);
    bitSelectSlider.setPreferredSize(new Dimension(180, 65));
    bitSelectSlider.setMajorTickSpacing(1);
    bitSelectSlider.setValue(2);
    bitSelectSlider.setSnapToTicks(true);
    bitSelectSlider.setPaintTicks(true);
    bitSelectSlider.setPaintLabels(true);
    bitSelectSlider.setBorder(BorderFactory.createTitledBorder("Bits Used"));
    bitSelectSlider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          JSlider source = (JSlider) e.getSource();

          if (audio == null) {
            return;
          }

          if (!source.getValueIsAdjusting()) {
            int bits = (int) source.getValue();

            AudioFormat af = audio.getAudioFormat();
            bytesAvail.setText("" +
              ((audio.getData().length - 200) / af.getFrameSize() / 8 * bits));
            bytesAvail.repaint();
          }
        }
      });
    stegoPanel.add(bitSelectSlider);

    stegoButton = new JButton("Hide a File");
    stegoPanel.add(stegoButton);

    bytesAvail = new JTextField("none");
    bytesAvail.setPreferredSize(new Dimension(60, 26));
    stegoPanel.add(bytesAvailLabel);
    stegoPanel.add(bytesAvail);
    
    bytesUsed = new JTextField("none");
    bytesUsed.setPreferredSize(new Dimension(60, 26));
    stegoPanel.add(bytesUsedLabel);
    stegoPanel.add(bytesUsed);

    pane.add(inputPanel, BorderLayout.WEST);
    pane.add(stegoPanel, BorderLayout.CENTER);
    pane.add(outputPanel, BorderLayout.EAST);
    setTitle("AudioStego");
  }

  public static void main(String[] args) {
    new AudioStego().setVisible(true);
  }
}
