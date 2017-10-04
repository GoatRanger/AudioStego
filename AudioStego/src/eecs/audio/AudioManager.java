/*
 * AudioCapture.java
 *
 * Created on June 27, 2003, 5:04 PM
 */
package eecs.audio;

import java.io.*;

import java.util.BitSet;

import javax.sound.sampled.*;

import javax.swing.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class AudioManager extends JPanel {
  /** DOCUMENT ME! */
  AudioFormat audioFormat;

  /** DOCUMENT ME! */
  AudioInputStream audioInputStream;

  /** DOCUMENT ME! */
  ByteArrayOutputStream byteArrayOutputStream;

  /** DOCUMENT ME! */
  SourceDataLine sourceDataLine;

  /** DOCUMENT ME! */
  String decodeFileName = null;

  /** DOCUMENT ME! */
  String inputFileName = null;

  /** DOCUMENT ME! */
  TargetDataLine targetDataLine;

  /** DOCUMENT ME! */
  byte[] stegoData;

  /** DOCUMENT ME! */
  boolean bufferFull = false;

  /** DOCUMENT ME! */
  boolean stopCapture = false;

  /** DOCUMENT ME! */
  long encodedSize = 0;

  /**
   * Creates a new AudioCapture object.
   */
  public AudioManager() {
    // Was going to do some initialization here...
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public AudioFormat getAudioFormat() {
    return new AudioFormat(audioFormat.getEncoding(),
      audioFormat.getSampleRate(), audioFormat.getSampleSizeInBits(),
      audioFormat.getChannels(), audioFormat.getFrameSize(),
      audioFormat.getFrameRate(), audioFormat.isBigEndian());
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean isBufferFull() {
    return bufferFull;
  }

  /**
   * Reads a file into a byte array.
   *
   * @param file
   *
   * @return
   *
   * @throws IOException
   */
  public static byte[] getBytesFromFile(File file) throws IOException {
    InputStream is = new FileInputStream(file);

    // Get the size of the file
    long length = file.length();

    // You cannot create an array using a long type.
    // It needs to be an int type.
    // Before converting to an int type, check
    // to ensure that file length is not larger than Integer.MAX_VALUE.
    if (length > Integer.MAX_VALUE) {
      // File is too large, should handle it,
      // but for now we'll just let the exception take care of it.
    }

    // Create the byte array to hold the data
    byte[] bytes = new byte[(int) length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;

    while (offset < bytes.length
          && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
      offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    // Close the input stream and return bytes
    is.close();

    return bytes;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public byte[] getData() {
    return byteArrayOutputStream.toByteArray();
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getDataLength() {
    return byteArrayOutputStream.size();
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getDecodeFileName() {
    return decodeFileName;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getDecodeMessage() {
    if (getInputFileName().equals("plaintext")) {
      return new String(stegoData);
    } else {
      return "Message Type doesn't support display.";
    }

    //    return decodeMessage;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public long getEncodedSize() {
    return encodedSize;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getInputFileName() {
    return inputFileName;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public byte[] getStegoData() {
    return stegoData;
  }

  //end playNewAudio
  public void decode() {
    byte[] audioData = byteArrayOutputStream.toByteArray();
    int start = 0;
    int frameSize = audioFormat.getFrameSize();

    // The encoding assumes little-endian, but some formats, such
    // as aiff must be saved in big endian, so if the file was converted
    // our information would be lost unless we ensure big-endian always
    // starts on byte 1.
    if (audioFormat.isBigEndian()) {
      start = 1;
    }

    int pos = start;

    // First read the header--7 chars, so 56 bits
    BitSet headerBits = new BitSet(56);

    for (int i = 0; i < 56; i++, pos += frameSize) {
      headerBits.set(i, (audioData[pos] & 0x01) == 1);
    }

    String header = "";
    int bitPos = 0;

    for (int headChar = 0; headChar < 7; headChar++) {
      int ch = 0;

      for (int p = 7; p >= 0; p--) {
        ch += (Math.pow(2, p) * (headerBits.get(bitPos) ? 1 : 0));
        bitPos++;
      }

      header += (char) ch;
    }

    // Advance a full character to skip over the terminator for the header
    pos += frameSize * 8;

    if (header.indexOf("stego") == -1) {
      decodeFileName = "no contents";

      return;
    }

    int bits = Integer.parseInt("" + header.charAt(header.length() - 1));

    if (header.startsWith("fstego")) {
      extract(audioData, bits, pos);
    } else if (header.startsWith("nstego")) {
      decodeFileName = "unknown";
      extract(audioData, bits, pos);
    } else {
      decodeFileName = "Stego Detected--Unknown Fmt";
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param f DOCUMENT ME!
   * @param bits DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean encode(File f, int bits) {
    String mesg = f.getName() + "\\" + f.length() + "\\";

    try {
      byte[] fileBytes = getBytesFromFile(f);
      byte[] messageBytes = new byte[fileBytes.length + mesg.length()];
      byte[] textBytes = mesg.getBytes();
      System.arraycopy(textBytes, 0, messageBytes, 0, textBytes.length);
      System.arraycopy(fileBytes, 0, messageBytes, textBytes.length,
        fileBytes.length);

      return encode("fstego" + bits + "\\", messageBytes, bits);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Couldn't read from file.");

      return false;
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param header DOCUMENT ME!
   * @param msg DOCUMENT ME!
   * @param bits DOCUMENT ME!
   */
  public void encode(String header, String msg, int bits) {
    String messageHeader = header;

    if (header == null) {
      messageHeader = "nstego" + bits + "\\";
    }

    String message = "plaintext" + "\\" + msg.length() + "\\" + msg;
    byte[] messageBytes = message.getBytes();
    encode(messageHeader, messageBytes, bits);
  }

  /**
   * Encodes the byte array into the loaded sound file using the specified
   * bitrate.  To ensure proper decoding, assumes that the header is
   * separated from the message content by a backspace ('\\b') and the
   * content is terminated by two backspaces.  Currently doesn't look for the
   * double backspace.  Need to change it to a length specifier to allow for
   * multiple filetypes (where the char code could be present).
   *
   * @param header DOCUMENT ME!
   * @param data The byte array to encode
   * @param bits Number of bits/byte to use.
   *
   * @return DOCUMENT ME!
   */
  public boolean encode(String header, byte[] data, int bits) {
    int start = 0;
    int frame = audioFormat.getFrameSize();

    if (audioFormat.isBigEndian()) {
      start = 1;
    }

    byte[] audioData = byteArrayOutputStream.toByteArray();
    int space = (audioData.length - 256) / frame / 8 * bits;

    if (data.length > space) {
      JOptionPane.showMessageDialog(this,
        "Not enough space in file.  Choose more bits, or use a different sound file");

      return false;
    } else { // Mask the bit for the header information

      int headIdx = 0;

      for (int head = start; head < header.length() * 8;
            head++, headIdx += frame) {
        audioData[headIdx] = (byte) (audioData[headIdx] & 0xfe);
      }

      BitSet bitset = getBitSet(data);
      BitSet headBits = getBitSet(header.getBytes());
      int pos = start;

      // int currentbit = 0;
      // First write the header, 1 bit encoding
      // Use the minimum so the header has a known encoding
      for (int i = 0; i < (header.length() * 8); i++) {
        audioData[pos] = (byte) (audioData[pos]
          | (headBits.get(i) ? 0x01 : 0x00));
        pos += frame;
      }

      // Now mask the message area
      int mask = 255;
      mask = mask - (int) (Math.pow(2, bits) - 1);

      for (int i = 0, dataPos = headIdx; i < data.length * 8 / bits;
            i++, dataPos += frame) {
        audioData[dataPos] = (byte) (audioData[dataPos] & mask);
      }

      // Now write the rest of the information at the specified bitrate
      int bitEnc = 0;

      while (bitEnc < data.length * 8) {
        int bitcount = 0;
        int dataMask = 0;
        int max = (int) Math.pow(2, bits - 1);

        while (bitcount < bits) {
          dataMask += (max * (bitset.get(bitEnc++) ? 1 : 0));
          max /= 2;
          bitcount++;
        }

        audioData[pos] = (byte) (audioData[pos] | dataMask);
        pos += frame;
      }

      stegoData = audioData;

      return true;
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param bits DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean encodeFile(int bits) {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      // Do nothing special...
    } else {
      return false;
    }

    if (chooser.getSelectedFile().isFile()) {
      encodedSize = chooser.getSelectedFile().length();

      return encode(chooser.getSelectedFile(), bits);
    }

    return false;
  }

  /**
   * DOCUMENT ME!
   *
   * @param bits DOCUMENT ME!
   */
  public void encodeFileTest(int bits) {
    String msg = "result.txt\\18\\Here's the message";
    String header = "fstego" + bits + "\\";
    encode(header, msg.getBytes(), bits);
  }

  //end constructor
  public void openFile() {
    JFileChooser chooser = new JFileChooser();
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("wav");
    filter.addExtension("au");
    filter.addExtension("aiff");
    filter.setDescription("Audio Files");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      // DO nothing special...
    } else {
      return;
    }

    if (chooser.getSelectedFile().isFile()) {
      File inputFileObj = chooser.getSelectedFile();
      inputFileName = inputFileObj.getName();

//      AudioInputStream audioInputStream = null;

      try {
        audioInputStream = AudioSystem.getAudioInputStream(inputFileObj);
        audioFormat = audioInputStream.getFormat();

        int cnt = 0;
        byte[] sndBuffer = new byte[10000];

        byte[] tempBuffer = new byte[10000];
        int iter = 0;
        byteArrayOutputStream = new ByteArrayOutputStream();

        while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
          byteArrayOutputStream.write(tempBuffer, 0, cnt);
        }
      } catch (Exception ae) {
        ae.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error opening input audio.");
      }

      //end catch
    }
  }

  //end captureAudio method
  public void playAudio() {
    try {
      //Get everything set up for
      // playback.
      //Get the previously-saved data
      // into a byte array object.
      byte[] audioData = byteArrayOutputStream.toByteArray();

      //Get an input stream on the
      // byte array containing the data
      InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
      AudioFormat formatCopy = this.audioFormat;

      if (formatCopy == null) {
        formatCopy = createAudioFormat();
      }

      audioInputStream = new AudioInputStream(byteArrayInputStream,
          formatCopy, audioData.length / formatCopy.getFrameSize());

      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
          formatCopy);
      sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
      sourceDataLine.open(formatCopy);
      sourceDataLine.start();

      //Create a thread to play back
      // the data and start it
      // running.  It will run until
      // all the data has been played
      // back.
      Thread playThread = new Thread(new PlayThread());
      playThread.start();
    } catch (NullPointerException e) {
      System.err.println("No File Loaded.");
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }

    //end catch
  }

  /**
   * DOCUMENT ME!
   */
  public void playHiddenAudio() {
    try {
      if (stegoData == null) {
        return;
      }

      //Get an input stream on the
      // byte array containing the data
      InputStream byteArrayInputStream = new ByteArrayInputStream(stegoData);
      AudioFormat audioFormat = this.audioFormat;

      if (audioFormat == null) {
        audioFormat = createAudioFormat();
      }

      audioInputStream = new AudioInputStream(byteArrayInputStream,
          audioFormat, stegoData.length / audioFormat.getFrameSize());

      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
          audioFormat);
      sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      //Create a thread to play back
      // the data and start it
      // running.  It will run until
      // all the data has been played
      // back.
      Thread playThread = new Thread(new PlayThread());
      playThread.start();
    } catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }

    //end catch
  }

  /**
   * DOCUMENT ME!
   */
  public void saveFile() {
    if (stegoData == null) {
      return;
    }

    JFileChooser chooser = new JFileChooser();
    ExampleFileFilter filter = new ExampleFileFilter();
    filter.addExtension("wav");
    filter.setDescription("Wave file");
    chooser.setFileFilter(filter);
    filter = new ExampleFileFilter();
    filter.addExtension("au");
    filter.setDescription("Sun AU Format");
    chooser.addChoosableFileFilter(filter);
    filter = new ExampleFileFilter();
    filter.addExtension("aiff");
    filter.setDescription("AIFF Format");
    chooser.addChoosableFileFilter(filter);

    int returnVal = chooser.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      AudioFileFormat.Type fileFormat = AudioFileFormat.Type.WAVE;
      String name = chooser.getFileFilter().getDescription().toLowerCase();
      String ext = ".wav";

      if (name.indexOf("aiff") >= 0) {
        fileFormat = AudioFileFormat.Type.AIFF;
        ext = ".aiff";
      } else if (name.indexOf("au") >= 0) {
        fileFormat = AudioFileFormat.Type.AU;
        ext = ".au";
      } else if (name.indexOf("snd") >= 0) {
        fileFormat = AudioFileFormat.Type.SND;
        ext = ".snd";
      }

      File savefile = chooser.getSelectedFile();

      if (!savefile.getName().endsWith(ext)) {
        savefile = new File(savefile.getAbsolutePath() + ext);
      }

      InputStream is = new ByteArrayInputStream(stegoData);

      AudioFormat fmt = getAudioFormat();

      AudioInputStream stream = new AudioInputStream(is, fmt, stegoData.length);

      try {
        int bytesWritten = AudioSystem.write(stream, fileFormat, savefile);
      } catch (IOException e) {
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null,
          fileFormat.toString() + " isn't supported.");
      }
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void saveStegoFile() {
    if (stegoData == null || stegoData.length == 0) {
      return;
    }

    JFileChooser chooser = new JFileChooser();
    File saveFile = new File(decodeFileName);
    chooser.setSelectedFile(saveFile);

    int returnVal = chooser.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      saveFile = chooser.getSelectedFile();

      try {
        FileOutputStream fos = new FileOutputStream(saveFile);
        fos.write(stegoData);
        fos.flush();
        fos.close();
      } catch (FileNotFoundException fnfe) {
        fnfe.printStackTrace();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param data DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private BitSet getBitSet(byte[] data) {
    BitSet bitset = new BitSet(data.length * 8);

    for (int i = 0, offset = 0; i < data.length; i++) {
      for (int ch = data[i] & 0xff, j = 128; j >= 1; j = j / 2, offset++) {
        if ((ch - j) >= 0) {
          bitset.set(offset);
          ch -= j;
        }
      }
    }

    return bitset;
  }

  //end getAudioFormat
  private void captureAudio() {
    bufferFull = false;

    try {
      //Get everything set up for
      // capture
      audioFormat = createAudioFormat();

      DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class,
          audioFormat);
      targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
      targetDataLine.open(audioFormat);
      targetDataLine.start();

      //Create a thread to capture the
      // microphone data and start it
      // running.  It will run until
      // the Stop button is clicked.
      Thread captureThread = new Thread(new CaptureThread());
      captureThread.start();
    } catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }

    //end catch
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private AudioFormat createAudioFormat() {
    //		8000,11025,16000,22050,44100
    float sampleRate = 44100.0F;

    //		8,16
    int sampleSizeInBits = 16;

    //1,2        
    int channels = 1;

    //true,false
    boolean signed = true;

    //true,false
    boolean bigEndian = false;

    return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
      bigEndian);
  }

  /**
   * DOCUMENT ME!
   *
   * @param dataBits DOCUMENT ME!
   * @param bitSetSize DOCUMENT ME!
   * @param startBit DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  private String decodeString(BitSet dataBits, int bitSetSize, int startBit) {
    int position = startBit;
    String extractedString = "";
    int ch = 0;

    while ((ch != '\\') && (position < bitSetSize)) {
      ch = 0;

      for (int p = 7; p >= 0; p--) {
        ch += (Math.pow(2, p) * (dataBits.get(position) ? 1 : 0));
        position++;
      }

      if (ch != '\\') {
        extractedString += (char) ch;
      }
    }

    return extractedString;
  }

  /**
   * DOCUMENT ME!
   *
   * @param data DOCUMENT ME!
   * @param bits DOCUMENT ME!
   * @param startPos DOCUMENT ME!
   */
  private void extract(byte[] data, int bits, int startPos) {
    String name = null;
    int frameSize = audioFormat.getFrameSize();

    // TODO: Make a better way to stop reading the bits (use the terminator)
    int bitSetSize = (data.length) / frameSize * bits;
    BitSet dataBits = new BitSet(bitSetSize);
    int bitPos = 0;
extractBits: 
    for (int pos = startPos; pos < data.length; pos += frameSize) {
      for (int p = bits - 1; p >= 0; p--, bitPos++) {
        int mask = (int) Math.pow(2, p);

        if ((data[pos] & mask) > 0) {
          dataBits.set(bitPos);
        }
      }
    }

    String fname = "";
    bitPos = 0;
    decodeFileName = decodeString(dataBits, bitSetSize, bitPos);

    String msgSize = decodeString(dataBits, bitSetSize,
        bitPos + (decodeFileName.length() + 1) * 8);

    // Don't delete until sure the decode above is working...
    //    String msgSize = "";
    //    ch = 0;
    //    while ((ch != '\\') && (bitPos < bitSetSize)) {
    //      ch = 0;
    //
    //      for (int p = 7; p >= 0; p--) {
    //        ch += (Math.pow(2, p) * (dataBits.get(bitPos) ? 1 : 0));
    //        bitPos++;
    //      }
    //
    //      if (ch != '\\') {
    //        msgSize += (char) ch;
    //      }
    //    }
    int size = 0;

    try {
      size = Integer.parseInt(msgSize);
    } catch (NumberFormatException nfe) {
      decodeFileName = "no contents";

      return;
    }

    byte[] fileData = new byte[size];

    for (int read = 0; read < size; read++) {
      int ch = 0;

      for (int p = 7; p >= 0; p--) {
        ch += (Math.pow(2, p) * (dataBits.get(bitPos) ? 1 : 0));
        bitPos++;
      }

      fileData[read] = (byte) ch;
    }

    stegoData = fileData;
  }

  class CaptureThread extends Thread {
    //An arbitrary-size temporary holding
    // buffer. Holds data until it is pushed onto the stream.
    byte[] tempBuffer = new byte[10000];

    public void run() {
      byteArrayOutputStream = new ByteArrayOutputStream();
      stopCapture = false;

      try { //Loop until stopCapture is set
            // by another thread that
            // services the Stop button.

        while (!stopCapture) {
          int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);

          if (cnt > 0) {
            byteArrayOutputStream.write(tempBuffer, 0, cnt);
          }
        }

        byteArrayOutputStream.close();
        bufferFull = true;
      } catch (Exception e) {
        System.out.println(e);
        System.exit(0);
      }

      //end catch
    }

    //end run
  }

  //end inner class CaptureThread
  class PlayThread extends Thread {
    byte[] tempBuffer = new byte[10000];

    public void run() {
      try {
        int cnt;

        //Keep looping until the input
        // read method returns -1 for
        // empty stream.
        while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
          if (cnt > 0) {
            //Write data to the internal
            // buffer of the data line
            // where it will be delivered
            // to the speaker.
            sourceDataLine.write(tempBuffer, 0, cnt);
          }
        }

        sourceDataLine.drain();
        sourceDataLine.close();
      } catch (Exception e) {
        System.out.println(e);
        System.exit(0);
      }
    }
  }

  //end inner class PlayThread
  //===================================//
}
