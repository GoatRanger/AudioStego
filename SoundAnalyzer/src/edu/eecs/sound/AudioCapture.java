/*
 * AudioCapture.java
 *
 * Created on June 27, 2003, 5:04 PM
 */
package edu.eecs.sound;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.sound.sampled.*;

/**
 */

/*File AudioCapture01.java
   This program demonstrates the capture
   and subsequent playback of audio data.
   A GUI appears on the screen containing
   the following buttons:
   Capture
   Stop
   Playback
   Input data from a microphone is
   captured and saved in a
   ByteArrayOutputStream object when the
   user clicks the Capture button.
   Data capture stops when the user clicks
   the Stop button.
   Playback begins when the user clicks
   the Playback button.
   Tested using SDK 1.4.0 under Win2000
 **************************************/
import javax.swing.*;


public class AudioCapture extends JPanel
{
    AudioFormat audioFormat;
    AudioInputStream audioInputStream;
    ByteArrayOutputStream byteArrayOutputStream;
    SourceDataLine sourceDataLine;
    TargetDataLine targetDataLine;
    boolean stopCapture = false;
    boolean bufferFull = false;

    public AudioCapture()
    { //constructor

        final JButton captureBtn = new JButton("Capture");
        final JButton stopBtn = new JButton("Stop");
        final JButton playBtn = new JButton("Playback");
        final JButton playNewBtn = new JButton("Playback");
        final JButton encodeBtn = new JButton("Encode");

        captureBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);
        playNewBtn.setEnabled(false);

        //Register anonymous listeners
        captureBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    captureBtn.setEnabled(false);
                    stopBtn.setEnabled(true);
                    playBtn.setEnabled(false);
                    playNewBtn.setEnabled(false);

                    //Capture input data from the
                    // microphone until the Stop
                    // button is clicked.
                    captureAudio();
                }
                 //end actionPerformed
            } //end ActionListener
        ); //end addActionListener()
        /*getContentPane().*/add(captureBtn);

        stopBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    captureBtn.setEnabled(true);
                    stopBtn.setEnabled(false);
                    playBtn.setEnabled(true);
                    playNewBtn.setEnabled(true);

                    //Terminate the capturing of
                    // input data from the
                    // microphone.
                    stopCapture = true;
                }
                 //end actionPerformed
            } //end ActionListener
        ); //end addActionListener()
        /*getContentPane().*/add(stopBtn);

        playBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //Play back all of the data
                    // that was saved during
                    // capture.
                    playAudio();
                }
                 //end actionPerformed
            } //end ActionListener
        ); //end addActionListener()
        /*getContentPane().*/add(playBtn);
		playNewBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Play back all of the data
					// that was saved during
					// capture.
					playNewAudio();
				}
				 //end actionPerformed
			} //end ActionListener
		); //end addActionListener()
		/*getContentPane().*/add(playNewBtn);
		encodeBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Play back all of the data
					// that was saved during
					// capture.
					encode();
				}
				 //end actionPerformed
			} //end ActionListener
		); //end addActionListener()
		/*getContentPane().*/add(encodeBtn);
        /*getContentPane().*/setLayout(new FlowLayout());
        //setTitle("Capture/Playback Demo");
        //setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(250, 70);
        //setVisible(true);
    }
     //end constructor
    public boolean isBufferFull() {
      return bufferFull;
    }
    
    public static void main(String[] args)
    {
        new AudioCapture();
    }
     //end main

    private AudioFormat getAudioFormat()
    {
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


        return new AudioFormat(sampleRate, sampleSizeInBits,
            channels, signed, bigEndian);
    }
     //end getAudioFormat

    private void captureAudio()
    {
        bufferFull = false;
        try
        {
            //Get everything set up for
            // capture
            audioFormat = getAudioFormat();

            DataLine.Info dataLineInfo =
                new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine =
                (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            //Create a thread to capture the
            // microphone data and start it
            // running.  It will run until
            // the Stop button is clicked.
            Thread captureThread = new Thread(new CaptureThread());
            captureThread.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
         //end catch
    }
     //end captureAudio method
    private void playAudio()
    {
        try
        {
            //Get everything set up for
            // playback.
            //Get the previously-saved data
            // into a byte array object.
            byte[] audioData = byteArrayOutputStream.toByteArray();
            //Get an input stream on the
            // byte array containing the data
            InputStream byteArrayInputStream =
                new ByteArrayInputStream(audioData);
            AudioFormat audioFormat = getAudioFormat();
            audioInputStream =
                new AudioInputStream(byteArrayInputStream,
                    audioFormat,
                    audioData.length / audioFormat.getFrameSize());

            DataLine.Info dataLineInfo =
                new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine =
                (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            //Create a thread to play back
            // the data and start it
            // running.  It will run until
            // all the data has been played
            // back.
            Thread playThread = new Thread(new PlayThread());
            playThread.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
         //end catch
    }
     //end playAudio
	private void playNewAudio()
		{
			try
			{
				//Get everything set up for
				// playback.
				//Get the previously-saved data
				// into a byte array object.
				byte[] audioData = byteArrayOutputStream.toByteArray();
				for (int i=0; i<audioData.length; i+=2) {
					audioData[i] = (byte)(audioData[i] & 252);
				}
				System.out.println("Length: " + (audioData.length/2));
				//Get an input stream on the
				// byte array containing the data
				InputStream byteArrayInputStream =
					new ByteArrayInputStream(audioData);
				AudioFormat audioFormat = getAudioFormat();
				audioInputStream =
					new AudioInputStream(byteArrayInputStream,
						audioFormat,
						audioData.length / audioFormat.getFrameSize());

				DataLine.Info dataLineInfo =
					new DataLine.Info(SourceDataLine.class, audioFormat);
				sourceDataLine =
					(SourceDataLine) AudioSystem.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				//Create a thread to play back
				// the data and start it
				// running.  It will run until
				// all the data has been played
				// back.
				Thread playThread = new Thread(new PlayThread());
				playThread.start();
			}
			catch (Exception e)
			{
				System.out.println(e);
				System.exit(0);
			}
			 //end catch
		}
		 //end playNewAudio
	
	public void encode() {
		byte[] audioData = byteArrayOutputStream.toByteArray();
		for (int i=0; i<audioData.length; i+=2) {
			audioData[i] = (byte)(audioData[i] & 252);
		}
		System.out.println("Bytes Available: " + (audioData.length/8));
		String msg = "This is a text file hidden in audio";
		if (msg.length() > audioData.length/4) {
			System.err.println("Cannot hide, not enough space");
		} else {
			int pos = 0;
			for (int i=0; i<msg.length(); i++) {
			  audioData[pos] = (byte)(audioData[pos] | (msg.charAt(i) >> 4));
			  pos += 2;
			  audioData[pos] = (byte)(audioData[pos] | (msg.charAt(i) & 240));
			  pos += 2;
			}
			audioData[pos]=(byte)(audioData[pos] | 15);
			pos += 2;
			audioData[pos]=(byte)(audioData[pos] | 15);
			System.out.println("Writing: " + audioData.length);
			InputStream is = new ByteArrayInputStream(audioData);
			AudioInputStream stream = new AudioInputStream(is, audioFormat, audioData.length);
			try {
				int bytesWritten = AudioSystem.write(stream,
					AudioFileFormat.Type.AIFF,
					new File("output.aiff"));
				System.out.println("Bytes written:" + bytesWritten);
			} catch (IOException e) {}
		}
	}
    public byte[] getData() {
        return byteArrayOutputStream.toByteArray();
    }
    
    class CaptureThread extends Thread
    {
        //An arbitrary-size temporary holding
        // buffer. Holds data until it is pushed onto the stream.
        byte[] tempBuffer = new byte[10000];

        public void run()
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            stopCapture = false;
            try
            { //Loop until stopCapture is set
              // by another thread that
              // services the Stop button.

                while (!stopCapture)
                {
                    //Read data from the internal
                    // buffer of the data line.
                    int cnt =
                        targetDataLine.read(tempBuffer, 0,
                            tempBuffer.length);
                    if (cnt > 0)
                    {
                        //Save data in output stream
                        // object.
                        byteArrayOutputStream.write(tempBuffer, 0, cnt);
                    }
                     //end if
                }
                 //end while

                byteArrayOutputStream.close();
                bufferFull = true;
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.exit(0);
            }
             //end catch
        }
         //end run
    }
     //end inner class CaptureThread

    class PlayThread extends Thread
    {
        byte[] tempBuffer = new byte[10000];

        public void run()
        {
            try
            {
                int cnt;

                //Keep looping until the input
                // read method returns -1 for
                // empty stream.
                while ((cnt =
                          audioInputStream.read(tempBuffer, 0,
                              tempBuffer.length)) != -1)
                {
                    if (cnt > 0)
                    {
                        //Write data to the internal
                        // buffer of the data line
                        // where it will be delivered
                        // to the speaker.
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }
                     //end if
                }
                 //end while

                sourceDataLine.drain();
                sourceDataLine.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.exit(0);
            }
             //end catch
        }
         //end run
    }
     //end inner class PlayThread

    //===================================//
}
 //end outer class AudioCapture.java
