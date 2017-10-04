/*
 * CrystalClassifier.java
 *
 * Created on June 20, 2000, 10:35 PM
 */
package imageproc;

/**
 * Class to manage the image database.  This class allows viewing, editing the
 * database, as well as some image manipulation of filtering and edge detection.
 *
 * @author: Karl Gossett
 * @version: 2.1
 *
 * @see java.media.jai.JAI
 */

import javax.media.jai.JAI;
import javax.swing.*;

import java.awt.image.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

import imageproc.database.*;
import imageproc.utilities.*;
import imageproc.utilities.SwingWorker;

import java.util.logging.*;

public class CrystalClassifier extends javax.swing.JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5842611413283506387L;

	String defaultExtension = ".jpg";
    
    ImageDialog image;
    File imageDirectory;
    FileWriterTask fileTask;
    ClassifyWriterTask classifyTask;
    Database crystalDB;
    
    Triage triage;
    
    private javax.swing.Timer timer;
    public static Logger myLog = Logger.getLogger("imageproc");
    
    /** Creates new form CrystalClassifier */
    public CrystalClassifier () {
    	FileHandler fh;
		try {
			fh = new FileHandler("imageproclog.txt");
			myLog.addHandler(fh);
	        myLog.setLevel(Level.INFO);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	initComponents ();
        pack ();
        crystalDB = new Database();
        imageDirectory = getDirectory();
        image = new ImageDialog (this, false);
        image.setSize (640,580);
        image.setLocation(200,200);
        image.setVisible (true);
        //ScreenLogger.register ();
    }
    
    public File getDirectory () {
        File tempFile = null;
        JFileChooser fc = new JFileChooser ();
        fc.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
        fc.setApproveButtonText ("Accept");
        fc.setDialogTitle ("Select Directory of Images");
        int returnVal = fc.showOpenDialog (this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            tempFile = fc.getSelectedFile ();
            myLog.info ("Image Directory -> " + tempFile.toString ());
        }
        return tempFile;
    }
        
    /** method loadImage loads an image from a file, based on the current filename
     * from the database.
     *
     * Can modify (should work with most file formats)
     */
    private void loadImage () {
        BufferedImage bi = null; // = new BufferedImage (640, 480, BufferedImage.TYPE_BYTE_BINARY);
        boolean validImage = false;
        
        String dirID = fileID.getText ();
        int len = dirID.length ();
        for (int i = 0;i < (7-len);i++) { dirID = "0"+dirID; }
        if (imageDirectory == null) {
            imageDirectory = getDirectory ();
        }
        String fileName = imageDirectory.toString () + "\\t" + dirID + "\\"
        + cellID.getText () + "-" + imageDate.getText ();
        setBackground (Color.white);
        try {
            bi = (BufferedImage) JAI.create ("fileload",fileName
            + defaultExtension).getAsBufferedImage ();
            validImage = true;
        }
        catch (Exception e) {
            if (defaultExtension.equalsIgnoreCase (".jpg")) {
                defaultExtension = ".bmp";
            }
            else {
                defaultExtension = ".jpg";
            }
        }
        if (! validImage) {
            try {
                bi = (BufferedImage) JAI.create ("fileload",fileName
                + defaultExtension).getAsBufferedImage ();
                validImage = true;
            }
            catch (Exception e) {
                myLog.warning ("Could not load image: "+ e.toString ());
            }
        }
        if (validImage) {
            image.setImage (bi);
        }
        myLog.config ("Image " + fileName + " loaded into processor.");
    }
    
    /**
     * This method updates all fields on the display, and reads the current
     * image from disk.  This is normally used in response to the user clicking
     * on a database navigation button.
     */
    protected void updateFields () {
        // Get the current record from the database
        CrystalData dt = (CrystalData) crystalDB.getCurrent();

        // Set the fields on the GUI from the record
        proteinName.setText (dt.getProtein());
        concentrationAmt.setText (String.valueOf(dt.getConcentration()));
        sampleScreen.setText (String.valueOf(dt.getScreen()));
        trayNumber.setText (dt.getTray());
        cellID.setText (dt.getWell());
        // Reformat the entry date so it matches the file name format
        StringTokenizer st 
            = new StringTokenizer(dt.getEntryDate(),"/ \t\n\r\f");
        String m = st.nextToken();
        String d = st.nextToken();
        String y = st.nextToken().substring(2);
        // If only 1 digit, add a leading 0 to put in mmdd format
        if (m.length() == 1) m = "0" + m;
        if (d.length() == 1) d = "0" + d;
        imageDate.setText (m+d+y);
        majorClassification.setText (String.valueOf(dt.getMajorClass()));
        minorClassification.setText (String.valueOf(dt.getMinorClass()));
        comment.setText (dt.getComments());
        fileID.setText (String.valueOf(dt.getImageDirNumber()));

        // Set the tray ID as a combination of fields
        String s = proteinName.getText () + " ("
        + concentrationAmt.getText () + ") " + sampleScreen.getText ()
        + ", " + trayNumber.getText ();
        trayID.setText (s);

        // The GUI is set, so load the image from disk
        loadImage ();
        image.setVisible (true);
    } // End updateFields
    
    /** Gets the integer identifier for the image Major Classification */
    public int getMajorClass () {
        return ( (int)Integer.valueOf (majorClassification.getText ()).intValue ()-1);
    }
    
    /* Forte for Java 2.0CE generated code */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        menu = new javax.swing.JMenuBar ();
        fileMenu = new javax.swing.JMenu ();
        openDBItem = new javax.swing.JMenuItem ();
        importItem = new javax.swing.JMenuItem ();
        saveDB = new javax.swing.JMenuItem ();
        exportItem = new javax.swing.JMenuItem ();
        separator = new javax.swing.JSeparator ();
        regionWriteItem = new javax.swing.JMenuItem ();
        classWriteItem = new javax.swing.JMenuItem ();
        jSeparator1 = new javax.swing.JSeparator ();
        exitItem = new javax.swing.JMenuItem ();
        imageMenu = new javax.swing.JMenu ();
        thresholdItem = new javax.swing.JMenuItem ();
        edgeImageItem = new javax.swing.JMenuItem ();
        testSeparator = new javax.swing.JSeparator ();
        testItem = new javax.swing.JMenuItem ();
        helpMenu = new javax.swing.JMenu ();
        aboutItem = new javax.swing.JMenuItem ();
        dataNavPanel = new javax.swing.JPanel ();
        navButtons = new javax.swing.JPanel ();
        firstRecord = new javax.swing.JButton ();
        prevRecord = new javax.swing.JButton ();
        nextRecord = new javax.swing.JButton ();
        lastRecord = new javax.swing.JButton ();
        closeButton = new javax.swing.JButton ();
        writerPanel = new javax.swing.JPanel ();
        progressText = new javax.swing.JLabel ();
        progress = new javax.swing.JProgressBar ();
        parametersPanel = new javax.swing.JPanel ();
        dataSizeLabel = new javax.swing.JLabel ();
        dataSize = new javax.swing.JTextField ();
        toolBar = new javax.swing.JToolBar ();
        propertiesButton = new javax.swing.JButton ();
        writerButton = new javax.swing.JButton ();
        refreshButton = new javax.swing.JButton ();
        infoButton = new javax.swing.JButton ();
        helpButton = new javax.swing.JButton ();
        infoPanel = new javax.swing.JPanel ();
        trayLabel = new javax.swing.JLabel ();
        trayID = new javax.swing.JTextField ();
        cellLabel = new javax.swing.JLabel ();
        cellID = new javax.swing.JTextField ();
        imageDateLabel = new javax.swing.JLabel ();
        imageDate = new javax.swing.JTextField ();
        classificationLabel = new javax.swing.JLabel ();
        majorClassification = new javax.swing.JTextField ();
        minorClassLabel = new javax.swing.JLabel ();
        minorClassification = new javax.swing.JTextField ();
        commentLabel = new javax.swing.JLabel ();
        comment = new javax.swing.JTextField ();
        fileID = new javax.swing.JTextField ();
        proteinName = new javax.swing.JTextField ();
        sampleScreen = new javax.swing.JTextField ();
        trayNumber = new javax.swing.JTextField ();
        concentrationAmt = new javax.swing.JTextField ();
        menu.setPreferredSize (new java.awt.Dimension (80, 20));
        menu.setMinimumSize (new java.awt.Dimension (39, 10));
        
        fileMenu.setText ("File");
          
          openDBItem.setText ("Open Database...");
            openDBItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    openDBItemActionPerformed (evt);
                }
            }
            );
            fileMenu.add (openDBItem);
            
          importItem.setText ("Import Database...");
            importItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    importItemActionPerformed (evt);
                }
            }
            );
            fileMenu.add (importItem);
            
          saveDB.setText ("Save Database...");
            saveDB.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    saveDBActionPerformed (evt);
                }
            }
            );
            fileMenu.add (saveDB);
            
          exportItem.setText ("Export Database...");
            exportItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    exportItemActionPerformed (evt);
                }
            }
            );
            fileMenu.add (exportItem);
            
          fileMenu.add (separator);
            
          regionWriteItem.setText ("Write Region Statistics");
            regionWriteItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    regionWriteItemActionPerformed (evt);
                }
            }
            );
            fileMenu.add (regionWriteItem);
            
          classWriteItem.setText ("Write Classifications");
            classWriteItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    classWriteItemActionPerformed (evt);
                }
            }
            );
            fileMenu.add (classWriteItem);
            
          fileMenu.add (jSeparator1);
            
          exitItem.setText ("Exit");
            exitItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    closeButtonAction (evt);
                }
            }
            );
            fileMenu.add (exitItem);
            menu.add (fileMenu);
          
        imageMenu.setText ("Image");
          
          thresholdItem.setText ("Threshold");
            thresholdItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    thresholdItemActionPerformed (evt);
                }
            }
            );
            imageMenu.add (thresholdItem);
            
          edgeImageItem.setText ("Detect Edges");
            edgeImageItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    edgeImageItemActionPerformed (evt);
                }
            }
            );
            imageMenu.add (edgeImageItem);
            
          imageMenu.add (testSeparator);
            
          testItem.setText ("Process All");
            testItem.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    testItemActionPerformed (evt);
                }
            }
            );
            imageMenu.add (testItem);
            menu.add (imageMenu);
          
        helpMenu.setText ("Help");
          
          aboutItem.setText ("About...");
            helpMenu.add (aboutItem);
            menu.add (helpMenu);
          setTitle ("Image Preprocessor");
        addWindowListener (new java.awt.event.WindowAdapter () {
            public void windowClosing (java.awt.event.WindowEvent evt) {
                exitForm (evt);
            }
        }
        );
        
        dataNavPanel.setPreferredSize (new java.awt.Dimension (200, 100));
        dataNavPanel.setBorder (new javax.swing.border.TitledBorder ("Data Access"));
        dataNavPanel.setMinimumSize (new java.awt.Dimension (200, 65));
        
        navButtons.setPreferredSize (new java.awt.Dimension (130, 30));
          navButtons.setMinimumSize (new java.awt.Dimension (100, 30));
          
          firstRecord.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/Rewind24.gif")));
            firstRecord.setPreferredSize (new java.awt.Dimension (24, 24));
            firstRecord.setMaximumSize (new java.awt.Dimension (24, 24));
            firstRecord.setMinimumSize (new java.awt.Dimension (24, 24));
            firstRecord.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    firstRecordActionPerformed (evt);
                }
            }
            );
            navButtons.add (firstRecord);
            
            
          prevRecord.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/StepBack24.gif")));
            prevRecord.setPreferredSize (new java.awt.Dimension (24, 24));
            prevRecord.setMaximumSize (new java.awt.Dimension (24, 24));
            prevRecord.setMinimumSize (new java.awt.Dimension (24, 24));
            prevRecord.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    prevRecordAction (evt);
                }
            }
            );
            navButtons.add (prevRecord);
            
            
          nextRecord.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/StepForward24.gif")));
            nextRecord.setPreferredSize (new java.awt.Dimension (24, 24));
            nextRecord.setToolTipText ("Next Record");
            nextRecord.setMaximumSize (new java.awt.Dimension (24, 24));
            nextRecord.setMinimumSize (new java.awt.Dimension (24, 24));
            nextRecord.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    nextRecordAction (evt);
                }
            }
            );
            navButtons.add (nextRecord);
            
            
          lastRecord.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/FastForward24.gif")));
            lastRecord.setPreferredSize (new java.awt.Dimension (24, 24));
            lastRecord.setMaximumSize (new java.awt.Dimension (24, 24));
            lastRecord.setMinimumSize (new java.awt.Dimension (24, 24));
            lastRecord.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    lastRecordActionPerformed (evt);
                }
            }
            );
            navButtons.add (lastRecord);
            
            dataNavPanel.add (navButtons);
          
          
        closeButton.setPreferredSize (new java.awt.Dimension (80, 25));
          closeButton.setHorizontalTextPosition (javax.swing.SwingConstants.CENTER);
          closeButton.setText ("Close");
          closeButton.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  closeButtonAction (evt);
              }
          }
          );
          dataNavPanel.add (closeButton);
          
          
        getContentPane ().add (dataNavPanel, java.awt.BorderLayout.WEST);
        
        
        writerPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints1;
        writerPanel.setPreferredSize (new java.awt.Dimension (150, 100));
        writerPanel.setBorder (new javax.swing.border.TitledBorder ("Output File"));
        writerPanel.setMinimumSize (new java.awt.Dimension (150, 100));
        
        progressText.setText ("Progress:");
          gridBagConstraints1 = new java.awt.GridBagConstraints ();
          gridBagConstraints1.gridx = 0;
          gridBagConstraints1.gridy = 0;
          gridBagConstraints1.insets = new java.awt.Insets (10, 0, 0, 0);
          gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
          writerPanel.add (progressText, gridBagConstraints1);
          
          
        progress.setPreferredSize (new java.awt.Dimension (200, 20));
          progress.setString ("0 of 0");
          progress.setMinimumSize (new java.awt.Dimension (100, 20));
          progress.setToolTipText ("Output Data File Progress");
          progress.setStringPainted (true);
          gridBagConstraints1 = new java.awt.GridBagConstraints ();
          gridBagConstraints1.gridx = 0;
          gridBagConstraints1.gridy = 1;
          gridBagConstraints1.insets = new java.awt.Insets (1, 0, 0, 0);
          gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
          writerPanel.add (progress, gridBagConstraints1);
          
          
        getContentPane ().add (writerPanel, java.awt.BorderLayout.EAST);
        
        
        parametersPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints2;
        parametersPanel.setPreferredSize (new java.awt.Dimension (450, 100));
        parametersPanel.setBorder (new javax.swing.border.TitledBorder ("Parameters"));
        parametersPanel.setMinimumSize (new java.awt.Dimension (450, 100));
        
        dataSizeLabel.setText ("Samples");
          gridBagConstraints2 = new java.awt.GridBagConstraints ();
          gridBagConstraints2.gridx = 3;
          gridBagConstraints2.gridy = 0;
          gridBagConstraints2.insets = new java.awt.Insets (0, 11, 0, 0);
          gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
          parametersPanel.add (dataSizeLabel, gridBagConstraints2);
          
          
        dataSize.setPreferredSize (new java.awt.Dimension (40, 20));
          dataSize.setToolTipText ("Number of Images to Process to Data File");
          dataSize.setMaximumSize (new java.awt.Dimension (40, 20));
          dataSize.setText ("0");
          dataSize.setMinimumSize (new java.awt.Dimension (40, 20));
          dataSize.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  dataSizeActionPerformed (evt);
              }
          }
          );
          gridBagConstraints2 = new java.awt.GridBagConstraints ();
          gridBagConstraints2.insets = new java.awt.Insets (0, 5, 0, 0);
          gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
          parametersPanel.add (dataSize, gridBagConstraints2);
          
          
        getContentPane ().add (parametersPanel, java.awt.BorderLayout.CENTER);
        
        
        toolBar.setPreferredSize (new java.awt.Dimension (400, 30));
        toolBar.setMinimumSize (new java.awt.Dimension (40, 30));
        toolBar.setMaximumSize (new java.awt.Dimension (600, 30));
        
        propertiesButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/Properties24.gif")));
          propertiesButton.setPreferredSize (new java.awt.Dimension (24, 24));
          propertiesButton.setMaximumSize (new java.awt.Dimension (24, 24));
          propertiesButton.setMinimumSize (new java.awt.Dimension (24, 24));
          toolBar.add (propertiesButton);
          
          
        writerButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/Export24.gif")));
          writerButton.setPreferredSize (new java.awt.Dimension (24, 24));
          writerButton.setMaximumSize (new java.awt.Dimension (24, 24));
          writerButton.setMinimumSize (new java.awt.Dimension (24, 24));
          writerButton.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  processButtonAction (evt);
              }
          }
          );
          toolBar.add (writerButton);
          
          
        refreshButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/Refresh24.gif")));
          refreshButton.setPreferredSize (new java.awt.Dimension (24, 24));
          refreshButton.setMaximumSize (new java.awt.Dimension (24, 24));
          refreshButton.setMinimumSize (new java.awt.Dimension (24, 24));
          refreshButton.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  resetItemInfoActionPerformed (evt);
              }
          }
          );
          toolBar.add (refreshButton);
          
          
        infoButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/About24.gif")));
          infoButton.setPreferredSize (new java.awt.Dimension (24, 24));
          infoButton.setMaximumSize (new java.awt.Dimension (24, 24));
          infoButton.setMinimumSize (new java.awt.Dimension (24, 24));
          toolBar.add (infoButton);
          
          
        helpButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/imageproc/utilities/images/Help24.gif")));
          helpButton.setPreferredSize (new java.awt.Dimension (24, 24));
          helpButton.setMaximumSize (new java.awt.Dimension (24, 24));
          helpButton.setMinimumSize (new java.awt.Dimension (24, 24));
          toolBar.add (helpButton);
          
          
        getContentPane ().add (toolBar, java.awt.BorderLayout.NORTH);
        
        
        infoPanel.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints3;
        infoPanel.setPreferredSize (new java.awt.Dimension (400, 110));
        infoPanel.setBorder (new javax.swing.border.TitledBorder ("Sample Information"));
        infoPanel.setMinimumSize (new java.awt.Dimension (250, 100));
        infoPanel.setMaximumSize (new java.awt.Dimension (400, 130));
        
        trayLabel.setText ("Tray ID");
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (trayLabel, gridBagConstraints3);
          
          
        trayID.setPreferredSize (new java.awt.Dimension (360, 20));
          trayID.setEditable (false);
          trayID.setMinimumSize (new java.awt.Dimension (200, 20));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 1;
          gridBagConstraints3.gridy = 0;
          gridBagConstraints3.gridwidth = 4;
          gridBagConstraints3.insets = new java.awt.Insets (0, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (trayID, gridBagConstraints3);
          
          
        cellLabel.setPreferredSize (new java.awt.Dimension (35, 16));
          cellLabel.setText ("Well");
          cellLabel.setHorizontalAlignment (javax.swing.SwingConstants.RIGHT);
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 5;
          gridBagConstraints3.gridy = 0;
          gridBagConstraints3.insets = new java.awt.Insets (0, 10, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
          infoPanel.add (cellLabel, gridBagConstraints3);
          
          
        cellID.setPreferredSize (new java.awt.Dimension (60, 20));
          cellID.setEditable (false);
          cellID.setMinimumSize (new java.awt.Dimension (40, 20));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 0;
          gridBagConstraints3.insets = new java.awt.Insets (0, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (cellID, gridBagConstraints3);
          
          
        imageDateLabel.setText ("Image Date");
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 7;
          gridBagConstraints3.gridy = 0;
          gridBagConstraints3.insets = new java.awt.Insets (0, 10, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (imageDateLabel, gridBagConstraints3);
          
          
        imageDate.setPreferredSize (new java.awt.Dimension (80, 20));
          imageDate.setEditable (false);
          imageDate.setMaximumSize (new java.awt.Dimension (70, 20));
          imageDate.setMinimumSize (new java.awt.Dimension (50, 20));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 8;
          gridBagConstraints3.gridy = 0;
          gridBagConstraints3.insets = new java.awt.Insets (0, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (imageDate, gridBagConstraints3);
          
          
        classificationLabel.setText ("Classification");
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 0;
          gridBagConstraints3.gridy = 1;
          gridBagConstraints3.insets = new java.awt.Insets (5, 0, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (classificationLabel, gridBagConstraints3);
          
          
        majorClassification.setPreferredSize (new java.awt.Dimension (40, 20));
          majorClassification.setMinimumSize (new java.awt.Dimension (40, 20));
          majorClassification.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  majorClassificationActionPerformed (evt);
              }
          }
          );
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 1;
          gridBagConstraints3.gridy = 1;
          gridBagConstraints3.gridwidth = 2;
          gridBagConstraints3.insets = new java.awt.Insets (5, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (majorClassification, gridBagConstraints3);
          
          
        minorClassLabel.setText ("Subclass");
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 3;
          gridBagConstraints3.gridy = 1;
          gridBagConstraints3.insets = new java.awt.Insets (5, 10, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (minorClassLabel, gridBagConstraints3);
          
          
        minorClassification.setPreferredSize (new java.awt.Dimension (40, 20));
          minorClassification.setMinimumSize (new java.awt.Dimension (40, 20));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 4;
          gridBagConstraints3.gridy = 1;
          gridBagConstraints3.insets = new java.awt.Insets (5, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (minorClassification, gridBagConstraints3);
          
          
        commentLabel.setText ("Comment");
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 0;
          gridBagConstraints3.gridy = 2;
          gridBagConstraints3.insets = new java.awt.Insets (5, 0, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (commentLabel, gridBagConstraints3);
          
          
        comment.setPreferredSize (new java.awt.Dimension (248, 20));
          comment.setMinimumSize (new java.awt.Dimension (100, 20));
          comment.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                  commentActionPerformed (evt);
              }
          }
          );
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 1;
          gridBagConstraints3.gridy = 2;
          gridBagConstraints3.gridwidth = 0;
          gridBagConstraints3.insets = new java.awt.Insets (5, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
          infoPanel.add (comment, gridBagConstraints3);
          
          
        fileID.setPreferredSize (new java.awt.Dimension (0, 0));
          fileID.setEditable (false);
          fileID.setName ("jFileID");
          fileID.setMinimumSize (new java.awt.Dimension (0, 0));
          fileID.setAutoscrolls (false);
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 1;
          infoPanel.add (fileID, gridBagConstraints3);
          
          
        proteinName.setPreferredSize (new java.awt.Dimension (0, 0));
          proteinName.setEditable (false);
          proteinName.setMinimumSize (new java.awt.Dimension (0, 0));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 1;
          infoPanel.add (proteinName, gridBagConstraints3);
          
          
        sampleScreen.setPreferredSize (new java.awt.Dimension (0, 0));
          sampleScreen.setEditable (false);
          sampleScreen.setMinimumSize (new java.awt.Dimension (0, 0));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 1;
          infoPanel.add (sampleScreen, gridBagConstraints3);
          
          
        trayNumber.setPreferredSize (new java.awt.Dimension (0, 0));
          trayNumber.setEditable (false);
          trayNumber.setMinimumSize (new java.awt.Dimension (0, 0));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 1;
          infoPanel.add (trayNumber, gridBagConstraints3);
          
          
        concentrationAmt.setPreferredSize (new java.awt.Dimension (0, 0));
          concentrationAmt.setEditable (false);
          concentrationAmt.setMinimumSize (new java.awt.Dimension (0, 0));
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridx = 6;
          gridBagConstraints3.gridy = 1;
          infoPanel.add (concentrationAmt, gridBagConstraints3);
          
          
        getContentPane ().add (infoPanel, java.awt.BorderLayout.SOUTH);
        
        setJMenuBar (menu);
        
    }//GEN-END:initComponents

    /** Exports the current database to a formatted text file */
  private void exportItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItemActionPerformed
// Add your handling code here:
      crystalDB.exportFile();
  }//GEN-LAST:event_exportItemActionPerformed

  /** Sets the comments when the user presses return in the text box */
  private void commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentActionPerformed
// Add your handling code here:
      crystalDB.setComment(comment.getText());
  }//GEN-LAST:event_commentActionPerformed

  /** Sets the Major classification when user presses return in text box */
  private void majorClassificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_majorClassificationActionPerformed
// Add your handling code here:
      crystalDB.setMajorClass(Integer.valueOf(majorClassification.getText()).intValue());
  }//GEN-LAST:event_majorClassificationActionPerformed

  /** Saves the current database to a serialized object file */
  private void saveDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDBActionPerformed
// Add your handling code here:
      crystalDB.saveFile();
  }//GEN-LAST:event_saveDBActionPerformed

  /** Imports a database from a formatted text file */
  private void importItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItemActionPerformed
// Add your handling code here:
      crystalDB.importDB();
      updateFields();
  }//GEN-LAST:event_importItemActionPerformed
    
  /** Writes the classification results to a file */
  private void classWriteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classWriteItemActionPerformed
      // Add your handling code here:
      writeClassificationFile ();
  }//GEN-LAST:event_classWriteItemActionPerformed
  
  /** Write the region statistics for the images */
  private void regionWriteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionWriteItemActionPerformed
      // Add your handling code here:
      writeFile ();
  }//GEN-LAST:event_regionWriteItemActionPerformed
  
  /** Determines the number of images to process */
  private void dataSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSizeActionPerformed
      // Add your handling code here:
      int length = (Integer.valueOf (dataSize.getText ()).intValue ());
      if (length < 0) {
          dataSize.setText ("0");
      }
  }//GEN-LAST:event_dataSizeActionPerformed
  
  /** Detects edges in the current image */
  private void edgeImageItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgeImageItemActionPerformed
      // Add your handling code here:
      image.detectEdges ();
  }//GEN-LAST:event_edgeImageItemActionPerformed
  
  /** Moves to the last record in the current database */
  private void lastRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastRecordActionPerformed
      // Add your handling code here:
      crystalDB.last();
      updateFields();
  }//GEN-LAST:event_lastRecordActionPerformed
  
  /** Moves to the first record in the current database */
  private void firstRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstRecordActionPerformed
      // Add your handling code here:
      crystalDB.first();
      updateFields();
  }//GEN-LAST:event_firstRecordActionPerformed
  
  private void resetItemInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetItemInfoActionPerformed
      // Add your handling code here:
  }//GEN-LAST:event_resetItemInfoActionPerformed
  
  /** Executes all image processing functions on the current image */
  private void testItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testItemActionPerformed
      // Add your handling code here:
      image.processAll (true);
  }//GEN-LAST:event_testItemActionPerformed
  /** Performs thresholding on the current image */
  private void thresholdItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thresholdItemActionPerformed
      // Add your handling code here:
      image.threshold ();
  }//GEN-LAST:event_thresholdItemActionPerformed
 /** Opens a new database of crystal images */
  private void openDBItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDBItemActionPerformed
       // Add your handling code here:
      crystalDB.loadFile();
      updateFields();
  }//GEN-LAST:event_openDBItemActionPerformed
  /** Moves to the next record in the database */
  private void nextRecordAction (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextRecordAction
      // Add your handling code here:
      crystalDB.next ();
      updateFields ();
  }//GEN-LAST:event_nextRecordAction
  /** Moves to the previous record in the database */
  private void prevRecordAction (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevRecordAction
      // Add your handling code here:
      crystalDB.previous ();
      updateFields ();
  }//GEN-LAST:event_prevRecordAction
  
/**
 * If the Process button was pressed, then begin processing the image
 * files stored in the database.  All processing begins at the currently
 * displayed image.
 */
  private void processButtonAction (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonAction
      
      writeFile ();
  }//GEN-LAST:event_processButtonAction
  
/**
 * If the data in the Cell_ID edit box was changed, update the stored variable
 * to reflect the update. The cell id will be changed as a result of the
 * using a data navigator on the database, as the field is not user-editable.
 * This method simply calls the updateFields method.
 *
 * @see #updateFields
 */
  @SuppressWarnings("unused")
private void cellIDChanged (java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cellIDChanged
      updateFields ();
  }//GEN-LAST:event_cellIDChanged
  
  private void closeButtonAction (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonAction
      image = null;
      crystalDB = null;
      System.gc();
      this.dispose();
  }//GEN-LAST:event_closeButtonAction
  
/** Exit the Application */
  private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
      image = null;
      crystalDB = null;
      System.gc();
      this.dispose();
  }//GEN-LAST:event_exitForm
  
    /**
     * @param args the command line arguments
     */
  public static void main (String args[]) {
      new CrystalClassifier ().setVisible (true);
  }
  
  private void writeFile () {
      String temp = "crystal.data";
      int QUARTER_SECOND = 250;
      
      myLog.config ("Initializing SwingWorker to write training file");
      
      fileTask = new FileWriterTask (temp);
      timer = new javax.swing.Timer (QUARTER_SECOND, new ActionListener () {
          public void actionPerformed (ActionEvent evt) {
              progress.setValue ((int) (fileTask.getCurrent ()
              / fileTask.getLengthOfTask () * 100));
              progress.setString (fileTask.getCurrent () + " of "
              + fileTask.getLengthOfTask () );
              if (fileTask.done ()) {
                  Toolkit.getDefaultToolkit ().beep ();
                  timer.stop ();
                  progress.setValue (progress.getMinimum ());
              }
          }
      });
      myLog.config ("Starting training file output task");
      
      fileTask.go ();
      timer.start ();
  }
  
    private void writeClassificationFile () {
      String temp = "classify.out";
      int QUARTER_SECOND = 100;
      
      myLog.config ("Initializing SwingWorker to write classification file");
      
      classifyTask = new ClassifyWriterTask (temp);
      timer = new javax.swing.Timer (QUARTER_SECOND, new ActionListener () {
          public void actionPerformed (ActionEvent evt) {
              progress.setValue ((int) (classifyTask.getCurrent ()
              / classifyTask.getLengthOfTask () * 100));
              progress.setString (classifyTask.getCurrent () + " of "
              + classifyTask.getLengthOfTask () );
              if (classifyTask.done ()) {
                  Toolkit.getDefaultToolkit ().beep ();
                  timer.stop ();
                  progress.setValue (progress.getMinimum ());
              }
          }
      });
      myLog.config ("Starting classification");
      
      classifyTask.go ();
      timer.start ();
  }
  
/**
 * Inner class to process all images in the database, and create an output
 * file that contains only the brightness histogram information.
 * Uses a SwingWorker to perform the task to allow a progress bar.
 *
 * @param	filename	The name of the data file to create.
 */
  class FileWriterTask {
      private int lengthOfTask = 0;
      private int current = 0;
      private String statMessage;
      private int numCases = 0;
      private int startCase = 0;
      private String filename;
      private FileOutputStream fos = null;
      private PrintWriter pw = null;
      
      FileWriterTask (String name) {
          filename = name;
          myLog.config ("Determining number of samples to process to file");
          
          startCase = crystalDB.getPosition();
          numCases = crystalDB.size() - startCase;
          
          // Set actual data sampling length based on user input
          int numSamples = (Integer.valueOf (dataSize.getText ()).intValue ());
          if (numSamples > 0) {
              if (numSamples < numCases) {
                  numCases = numSamples;
              }
          }
          myLog.config ("Processing " + numCases + " images");
          
          lengthOfTask = numCases;
          try {
              fos = new FileOutputStream (imageDirectory.toString () + "\\"
              + filename);
              pw = new PrintWriter (fos);
          }
          catch (Exception E) {
              myLog.severe ("Couldn't open output file.");
          }
          image.resetImage ();  // Ensure clean image to process
      }
      
    /**
     * Called from CrystalImagePreprocessor to start the task.
     */
      void go () {
          current = 0;
          final SwingWorker worker = new SwingWorker () {
              public Object construct () {
                  return new WritingTask ();
              }
          };
          myLog.config ("Starting task to write training file");
          
          worker.start ();
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out how much work needs
     * to be done.
     */
      int getLengthOfTask () {
          return lengthOfTask;
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out how much has been done.
     */
      int getCurrent () {
          return current;
      }
      
      void stop () {
          current = lengthOfTask;
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out if the task has completed.
     */
      boolean done () {
          if (current >= lengthOfTask)
              return true;
          else
              return false;
      }
      
      String getMessage () {
          return statMessage;
      }
      
    /**
     * The actual file writing task.  This runs in a SwingWorker thread.
     */
      class WritingTask {
          private int cases = 0;
          
          @SuppressWarnings("unused")
		WritingTask () {
              writeLoop:
                  while (current < lengthOfTask) {
                      myLog.config ("Processing image " + (current +1));
                      
                      image.processAll (false);
                      ImageStatistics stats = image.getStatistics ();
                      double[] edgeMean = stats.getRegionEdgeMeans ();
                      double[] edgeMax = stats.getRegionEdgeMax ();
                      double[] originalMean = stats.getUnprocessedMeans ();
                      double[] originalMin = stats.getUnprocessedRegionMins ();
                      double[] originalStdev = stats.getUnprocessedRegionStdev ();
                      
                      //int classify = getMajorClass ();
                      
                      //int mean = 0;
                      
                      myLog.config ("Writing training file");
                      
                      int k=0;
                      for (int x = 0; x < Constants.REGIONS_HORIZ; x++) {
                          for (int y = 0; y < Constants.REGIONS_VERTICAL; y++) {
                              pw.print (x + "," + y + ",");
                              
                              // Get Statistics from edge detected image
                              pw.print (edgeMean[k] + ",");
                              pw.print (edgeMax[k] + ",");
                              
                              // Get info from unprocessed image
                              pw.print (originalMean[k] + ",");
                              pw.print (originalMin[k] + ",");
                              pw.println (originalStdev[k]);
                              k = k+1;
                          }
                      }
                      cases = cases + 1;
                      
                      myLog.config ("Image data written");
                      
                      crystalDB.next ();
                      updateFields ();
                      
                      myLog.config ("Completed image " + (current+1));
                      
                      try {
                          Thread.sleep (100); //sleep for a second
                          current += 1; //make some progress
                          statMessage = "Completed " + current +
                          " out of " + lengthOfTask + ".";
                      } catch (InterruptedException e) {}
                  } // writing loop
                  
                  try {
                      myLog.config ("Closing file");
                      
                      pw.close ();
                  }
                  catch (Exception e) {
                      myLog.warning ("Couldn't close data file.");
                  }
          }
      }
  }
  
/**
 * Inner class to write classifications for all images.
 * Uses a SwingWorker to perform the task to allow a progress bar.
 *
 * @param filename	The name of the data file to create.
 */
  class ClassifyWriterTask {
      private int lengthOfTask = 0;
      private int current = 0;
      private String statMessage;
      private int numCases = 0;
      private int startCase = 0;
      // Main Classification File
      private String filename;
      private FileOutputStream fos = null;
      private PrintWriter pw = null;
      // Expectations File
      private FileOutputStream exp = null;
      private PrintWriter pwExp = null;

      ClassifyWriterTask (String name) {
          filename = name;
          myLog.config ("Determining number of images...");
          
          startCase = crystalDB.getPosition();
          numCases = crystalDB.size() - startCase;
          
          // Set actual data sampling length based on user input
          int numSamples = (Integer.valueOf (dataSize.getText ()).intValue ());
          if (numSamples > 0) {
              if (numSamples < numCases) {
                  numCases = numSamples;
              }
          }
          myLog.config ("Processing " + numCases + " images...");
          
          lengthOfTask = numCases;
          try {
              fos = new FileOutputStream (imageDirectory.toString () + "\\"
              + filename);
              pw = new PrintWriter (fos);
          }
          catch (Exception e) {
              myLog.severe ("Couldn't open output file.");
          }
          try {
              exp = new FileOutputStream (imageDirectory.toString() + "\\"
              + "imagetriage.txt");
              pwExp = new PrintWriter (exp);
          }
          catch (Exception e) {
              myLog.severe ("Couldn't open triage file.");
          }
          image.resetImage ();  // Ensure clean image to process
      }
      
    /**
     * Called from CrystalImagePreprocessor to start the task.
     */
      void go () {
          current = 0;
          final SwingWorker worker = new SwingWorker () {
              public Object construct () {
                  return new WritingTask ();
              }
          };
          myLog.config ("Starting task to write classifications");
          
          worker.start ();
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out how much work needs
     * to be done.
     */
      int getLengthOfTask () {
          return lengthOfTask;
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out how much has been done.
     */
      int getCurrent () {
          return current;
      }
      
      void stop () {
          current = lengthOfTask;
      }
      
    /**
     * Called from CrystalImagePreprocessor to find out if the task has completed.
     */
      boolean done () {
          if (current >= lengthOfTask)
              return true;
          else
              return false;
      }
      
      String getMessage () {
          return statMessage;
      }
      
    /**
     * The actual file writing task.  This runs in a SwingWorker thread.
     */
      class WritingTask {
          private int cases = 0;
          
          @SuppressWarnings("unused")
		WritingTask () {
              writeLoop:
                  while (current < lengthOfTask) {
                      myLog.info ("Image " + current);
                      
                      image.processAll (false);
                      CrystalData dt = (CrystalData) crystalDB.getCurrent();
                      String s = "\"" + dt.getProtein() + " ("
                                + dt.getConcentration() + "mg/mL) HS" 
                                + dt.getScreen() + ", Tray " + dt.getTray() 
                                + "-" + dt.getWell() + " (" 
                                + dt.getTrayDate() + ")\"";
                      pw.print (s + " (Image " + cases + "): ");
                      if (dt.getMajorClass() != 0) {
                          pw.print (Constants.globalName(dt.getMajorClass()-1));
                      }
                      if (dt.getComments() != null) {
                          pw.print (" (" + dt.getComments() + ")");
                      }
                      pw.println(":");
                      pw.println("--> Classification: " 
                                 + image.getClassification());
                      pw.println(image.getClassifyNarrative());
                      
                      pwExp.println (s + ",\"(Image " + cases + ")\"" 
                           + image.getClassExpectations());
                      cases += 1;
                      
                      myLog.config ("Image data written");
                      
                      crystalDB.next();
                      updateFields ();
                      myLog.config ("Completed image " + (current+1));
                      
                      try {
                          Thread.sleep (100); //sleep for a bit
                          current += 1; //make some progress
                          statMessage = "Completed " + current +
                          " out of " + lengthOfTask + ".";
                      } catch (InterruptedException e) {}
                  } // writing loop
                  
                  try {
                      myLog.config ("Closing file");
                      
                      pw.close ();
                      pwExp.close ();
                  }
                  catch (Exception e) {
                      myLog.severe ("Couldn't close data files.");
                  }
          }
      }
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuBar menu;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenuItem openDBItem;
  private javax.swing.JMenuItem importItem;
  private javax.swing.JMenuItem saveDB;
  private javax.swing.JMenuItem exportItem;
  private javax.swing.JSeparator separator;
  private javax.swing.JMenuItem regionWriteItem;
  private javax.swing.JMenuItem classWriteItem;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JMenuItem exitItem;
  private javax.swing.JMenu imageMenu;
  private javax.swing.JMenuItem thresholdItem;
  private javax.swing.JMenuItem edgeImageItem;
  private javax.swing.JSeparator testSeparator;
  private javax.swing.JMenuItem testItem;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JMenuItem aboutItem;
  private javax.swing.JPanel dataNavPanel;
  private javax.swing.JPanel navButtons;
  private javax.swing.JButton firstRecord;
  private javax.swing.JButton prevRecord;
  private javax.swing.JButton nextRecord;
  private javax.swing.JButton lastRecord;
  private javax.swing.JButton closeButton;
  private javax.swing.JPanel writerPanel;
  private javax.swing.JLabel progressText;
  private javax.swing.JProgressBar progress;
  private javax.swing.JPanel parametersPanel;
  private javax.swing.JLabel dataSizeLabel;
  private javax.swing.JTextField dataSize;
  private javax.swing.JToolBar toolBar;
  private javax.swing.JButton propertiesButton;
  private javax.swing.JButton writerButton;
  private javax.swing.JButton refreshButton;
  private javax.swing.JButton infoButton;
  private javax.swing.JButton helpButton;
  private javax.swing.JPanel infoPanel;
  private javax.swing.JLabel trayLabel;
  private javax.swing.JTextField trayID;
  private javax.swing.JLabel cellLabel;
  private javax.swing.JTextField cellID;
  private javax.swing.JLabel imageDateLabel;
  private javax.swing.JTextField imageDate;
  private javax.swing.JLabel classificationLabel;
  private javax.swing.JTextField majorClassification;
  private javax.swing.JLabel minorClassLabel;
  private javax.swing.JTextField minorClassification;
  private javax.swing.JLabel commentLabel;
  private javax.swing.JTextField comment;
  private javax.swing.JTextField fileID;
  private javax.swing.JTextField proteinName;
  private javax.swing.JTextField sampleScreen;
  private javax.swing.JTextField trayNumber;
  private javax.swing.JTextField concentrationAmt;
  // End of variables declaration//GEN-END:variables
  
}