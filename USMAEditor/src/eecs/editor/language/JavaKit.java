/* Copyright (C) 2003  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/*
 * Created on Aug 24, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs.editor.language;

import de.hunsicker.jalopy.Jalopy;
import de.hunsicker.jalopy.swing.SettingsDialog;

import eecs.editor.Messages;
import eecs.editor.util.GauntletCompileTask;
import eecs.util.ProcessWrapper;

import java.awt.Toolkit;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.*;

import jedit.JEditTextArea;

/**
 * This class implements a Java Language Kit that supports compiling, running,
 * reformatting.
 * 
 * @see eecs.editor.language.DefaultLanguageKit
 * @author Karl A. Gossett
 */
class JavaKit extends DefaultLanguageKit {

	private Class cadetCode;
	private Object cadetInstance;
	private Method startMethod;
	private Method stopMethod;
	/** DOCUMENT ME! */
	private volatile GauntletCompileTask compileTask = new GauntletCompileTask();
	private volatile Logger logger = Logger.getLogger(Messages
			.getString("Editor.logger.name")); //$NON-NLS-1$
	/** DOCUMENT ME! */
	private Jalopy jalopy;

	/** DOCUMENT ME! */
	private ProcessWrapper runProcess;

	/** DOCUMENT ME! */
	private String compilerMessages = null;

	/** DOCUMENT ME! */
	private StringBuffer javaTemplate = new StringBuffer("//\n" + "// Name: \n"
			+ "// Section: \n" + "// Date: \n" + "// Description: \n" + "\n"
			+ "public class \\ClassName\\ extends eecs.\\Gui\\RobotGui\\\n"
			+ "{\n" + "    public static void main(String[] args)\n"
			+ "    {\n" + "         // Code for the main program\n" + "\n"
			+ "    } //end main\n" + "} //end <ClassName>\n");

	/** DOCUMENT ME! */
	private StringBuffer runMessages = null;

	/** DOCUMENT ME! */
	private boolean canReformat = true;

	/** DOCUMENT ME! */
	private boolean isRunning = false;

	/** DOCUMENT ME! */
	private int compileStatus;

	/**
	 * Creates a new JavaKit object.
	 */
	JavaKit() {
		super();

		//	When creating the Jalopy formatter, sometimes throws an exception.
		// Catch it here so it doesn't show on the screen.
		try {
			jalopy = new Jalopy();
		} catch (ClassCastException e) {
			canReformat = true;

			// Do nothing. Not sure why this exception is throw. Not consistent.
		} catch (RuntimeException e) {
			canReformat = false;

			// Thrown by Jalopy when can't load the property settings
		}

		setTemplate(javaTemplate);
		language = LanguageKit.JAVA;
		description = "Java Program";
		fileSuffix = ".java";
	}

	/**
	 * Returns the result code of the last compile. If the program was fully
	 * compiled, but results are not available (due to thread timing), this
	 * method will block until the results are available.
	 * 
	 * @see #compile()
	 * @see eecs.editor.language.DefaultLanguageKit#getCompileResult()
	 */
	public int getCompileResult() {
		if (getCompilerPercentComplete() < 100) {
			return COMPILE_INCOMPLETE;
		}

		// Compiling is finished,
		while (compileResult == COMPILE_INCOMPLETE) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				// Not sure what we should do with it...maybe nothing?
			}
		}

		return compileResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eecs.editor.language.DefaultLanguageKit#getCompilerMessages()
	 */
	public String getCompilerMessages() {
		if (getCompilerPercentComplete() < 100) {
			return null;
		}

		while (compileResult == COMPILE_INCOMPLETE) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
			}
		}

		StringBuffer buffer = new StringBuffer(
				"<html><body><h4>---BEGIN CHECKS FOR COMMON MISTAKES---</h4>");
		boolean gauntletOk = false;
		Toolkit.getDefaultToolkit().beep();

		if (compileTask == null) {
			buffer.append("<em>Couldn't Compile.</em>");
		} else {
			List errors;
			synchronized (compileTask) {
				errors = compileTask.getErrors();
			}

			if (errors.size() == 0) {
				gauntletOk = true;
			}

			Iterator i = errors.iterator();
			int n = 1;

			while (i.hasNext()) {
				edu.usma.eecs.it.gauntlet.CheckerError ce = (edu.usma.eecs.it.gauntlet.CheckerError) i
						.next();
				buffer.append("<p><b>Mistake ");
				buffer.append((n++));
				buffer.append(":</b><br>");
				buffer.append(ce.getMessage());
				buffer.append("</p>");
			}

			if (gauntletOk) {
				buffer
						.append("<p><i>  None of the common mistakes were found! "
								+ "Now just check below to make sure the compiler didn't find any 'uncommon' mistakes.</i></p>");
			}

			buffer.append("<b><h4>--- END COMMON MISTAKE CHECKS---</h4></b>");

			String[] msgs;
			synchronized (compileTask) {
				msgs = compileTask.getCompilerMessages();
			}

			if ((msgs != null) && (msgs.length > 0)) {
				// Errors, so insert a big, red header to let the user know!
				buffer
						.insert(buffer.indexOf("<body>") + 6,
								"<h1><font color=\"red\">You have errors.  Messages Follow.</font></h1>\r\n");
				buffer
						.append("<p><font color=\"red\"> --- COMPILING: ERRORS DETECTED ---</font><br><code>");
				for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
					buffer.append(textToHTML(msgs[msgNum]) + "<br>");
				}
				buffer.append("</code></p>");
			} else {
				buffer
						.append("<h3>--- BEGIN COMPILING ---</h3><p><i>No Syntax Errors Detected</i></p>");
			}
		}

		buffer.append("<h3>--- Done Compiling ---</h3></body></html>");

		compilerMessages = buffer.toString();

		return compilerMessages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eecs.editor.language.DefaultLanguageKit#getCompilerPercentComplete()
	 */
	public int getCompilerPercentComplete() {
		if (compileTask == null) {
			return 100;
		} else {
			synchronized (compileTask) {
				return (int) ((double) compileTask.getCurrent()
						/ (double) compileTask.getLengthOfTask() * 100);
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param window
	 *            DOCUMENT ME!
	 */
	public void setFormatPrefs(java.awt.Window window) {
		if (window == null) {
			window = new JFrame();
		}
		SettingsDialog dlg = SettingsDialog.create(window);
		dlg.setSize(500, 500);
		dlg.setVisible(true);
		//dlg.show();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getRunResults() {
		while (isRunning /* && (runProcess != null) */) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}

		if (runMessages == null) {
			runMessages = new StringBuffer();
		}

		return runMessages.toString();
	}

	/**
	 * Converts special characters in a string to their HTML entities.
	 * 
	 * @param text
	 *            The String to convert.
	 * @return The String with entities.
	 */
	private String textToHTML(String text) {
		String result;
		result = text.replaceAll("&", "&amp;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("'", "&#39;");
		result = result.replaceAll("\n", "<br>");
		result = result.replaceAll("\\s", "&nbsp;");
		return result;
	}

	/**
	 * Returns <code>true</code>, since Java is an executable language.
	 * 
	 * @see eecs.editor.language.DefaultLanguageKit#isRunnable()
	 */
	public boolean isRunnable() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eecs.editor.language.DefaultLanguageKit#compile(java.io.File)
	 */
	public void compile(File file) {
		compileResult = COMPILE_INCOMPLETE;
		compileStatus = 0;
		//compileTask = new GauntletCompileTask();
		synchronized (compileTask) {
			compileTask.setFile(file);
			compileTask.go();
		}
		new Thread(new Runnable() {
			public void run() {
				while (!compileTask.done()) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException ie) {
					}
				}
				String[] msg;
				synchronized (compileTask) {
					msg = compileTask.getCompilerMessages();
				}
				if ((msg == null) || (msg.length == 0)) {
					compileResult = COMPILE_SUCCESS;
				} else {
					compileResult = COMPILE_FAILURE;
				}
			}
		}, "java_compile").start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eecs.editor.language.LanguageKit#hasCompiler()
	 */
	public boolean hasCompiler() {
		return true;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean hasFormatPreferences() {
		// No reason to allow preferences if formatter is broken
		return canReformat;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean hasFormatter() {
		return canReformat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eecs.editor.language.LanguageKit#reformatAction(java.io.File)
	 */
	public void reformat(JEditTextArea textpane) {
		formatMessages = new StringBuffer();
		formatResult = REFORMAT_ERRORS;

		StringBuffer newBuffer = new StringBuffer();
		String buffer = textpane.getText();

		// Since we're operating from a StringReader, create a temp
		// file to use for Jalopy setInput (why does it need a path?)
		File temp;

		try {
			temp = File.createTempFile("fmt", "java");
			temp.deleteOnExit();
		} catch (IOException e) {
			return;
		}

		jalopy.setInput(new StringReader(buffer), temp.getPath());
		jalopy.setOutput(newBuffer);

		if (jalopy.format()) {
			textpane.setText(newBuffer.toString());
		}

		if (jalopy.getState() == Jalopy.State.OK) {
			formatMessages.append("Reformat completed successfully");
			formatResult = REFORMAT_SUCCESS;
		} else if (jalopy.getState() == Jalopy.State.WARN) {
			formatMessages.append("Formatted with warnings");
			formatResult = REFORMAT_WARNINGS;
		} else if (jalopy.getState() == Jalopy.State.ERROR) {
			formatMessages
					.append("<h2>Your code has syntax errors.</h2> You must compile "
							+ "it and fix the errors before it can be reformatted.");
			formatResult = REFORMAT_ERRORS;
		}
	}

	/*
	 * TODO: Fix the run method so that it will work correctly with reflection
	 * and main methods. The problem with this is that when running with
	 * reflection, the class is executed inside the same JVM as the editor (just
	 * a separate thread), so calling System.exit or the equivalent JFrame close
	 * action will exit the Editor as well as the user's program (so every
	 * Jago/Wrapper class needs to be written to be runnable as a class, not
	 * just as a standalone app). Having nearly everything in the Wrapper static
	 * also causes problems because once the class is loaded, those static
	 * members remain BETWEEN RUNS, not just for the duration of the program's
	 * execution.
	 *  /* Returns a ProcessWrapper, prepared to execute the specified file.
	 * 
	 * @see eecs.editor.language.DefaultLanguageKit#run(java.io.File)
	 */
	public ProcessWrapper run(File file) {
		runMessages = new StringBuffer();

		String fileName = file.getName();
		final String programName = fileName.substring(0, fileName
				.lastIndexOf("."));
		runMessages.append("<html><head><title>Running \"" + programName
				+ "\"</title></head><body>");
		runMessages.append("<h3>--- Running \"" + programName
				+ "\" - Output: ---</h3>");
        /*TODO: Update the IT105 Wrapper to support a startProgram technique
         * to run code (instead of a main method).  All of the commented 
         * code here is to support moving to a coding style that just 
         * has a "startProgram" method (to eliminate the main)--it uses
         * reflection to find the startProgram and supporting methods to
         * make it work.  The issues with using it:
         *   1. Most of the wrapper is static, so multiple instances created
         * here share state; thus subsequent runs of a program might reuse
         * old state.
         *   2. The "close window" operation (using the Close, or Xing the
         * window) causes a System.exit type event; since the technique here
         * creates the cadet's code as a class INSIDE the editor, that exit
         * event applies to the editor, not just the cadet code; results in a 
         * complete close of the editor.  The eecs.Gui was rewritten to support
         * this technique (and it works correctly--see the initializer of eecs.Gui)
         * but support in the SimBox/RobotGui and all subclasses was too time consuming
         * to get straight.
         */
//    boolean hasStartMethod = false;
//		File workingDir = file.getParentFile();
//		String workDir = System.getProperty("user.dir");
//		if (workDir.indexOf(workingDir.getName()) < 0) {
//			System.setProperty("user.dir", workingDir + ";" + workDir);
//		}
//
//		String classPath = System.getProperty("java.class.path");
//
//		if ((classPath.indexOf(workingDir.getName()) < 0)
//      || (classPath.indexOf(".;") < 0)) {
//			System.setProperty("java.class.path", ".;" + workingDir.getName()
//					+ ";" + classPath);
//		}
//
//		cadetCode = null;
//		cadetInstance = null;
//
//		try {
//			Thread t = Thread.currentThread();
//			MyClassLoader cl = new MyClassLoader(workingDir.getAbsolutePath());
//			cadetCode = cl.findClass(programName);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			runMessages.append("Error running " + programName
//					+ ". Couldn't find your program!");
//		} catch (NoClassDefFoundError ndf) {
//			runMessages.append("Error running " + programName
//					+ ". Couldn't find your program!");
//		}
//		final StringBuffer tempMessages = new StringBuffer();
//		try {
//			cadetInstance = cadetCode.newInstance();
//			startMethod = cadetCode.getDeclaredMethod("startProgram",
//					new Class[]{});
//			hasStartMethod = true;
//			Method[] methodList = cadetCode.getMethods();
//			Method stop = null;
//			Method isNotApp = null;
//			// Can't directly access a superclass method, so need to find it.
//			for (int i = 0; i < methodList.length; i++) {
//				if (methodList[i].getName().equals("waitForStop")
//						&& methodList[i].getParameterTypes().length == 0) {
//					stop = methodList[i];
//				}
//				if (methodList[i].getName().equals("setNotApplication")
//						&& methodList[i].getParameterTypes().length == 0) {
//					isNotApp = methodList[i];
//				}
//			}
//			stopMethod = stop;
//			final Method notApp = isNotApp;
//			notApp.setAccessible(true);
//			stopMethod.setAccessible(true);
//			startMethod.setAccessible(true);
//			new Thread() {
//				public void run() {
//					try {
//						isRunning = true;
//						notApp.invoke(cadetInstance, null);
//						startMethod.invoke(cadetInstance, null);
//						stopMethod.invoke(cadetInstance, null);
//						runMessages.append("<h3>--- End " + programName
//								+ "---</h3>");
//					} catch (Exception e) {
//						if (logger.isLoggable(Level.WARNING)) {
//							logger
//									.log(
//											Level.WARNING,
//											"An error occurred trying to run the start/stop methods.",
//											e);
//						}
//						tempMessages
//								.append("Your start method wouldn't run. See your instructor.");
//					} finally {
//						runFinished();
//					}
//				}
//			}.start();
//		} catch (SecurityException e1) {
//			if (logger.isLoggable(Level.WARNING)) {
//				logger.log(Level.WARNING,
//						"An error occurred trying to run the cadet code.", e1);
//			}
//			tempMessages
//					.append("I was unable to access your start method; check with your instructor for assistance.");
//			e1.printStackTrace();
//		} catch (NoSuchMethodException e1) {
//			tempMessages
//					.append("Your program must contain a method called \"void start()\"");
//		} catch (IllegalArgumentException e) {
//			tempMessages.append("Your start method was declared incorrectly.");
//		} catch (IllegalAccessException e) {
//			tempMessages
//					.append("Your start method isn't accessible. Make sure your CLASS is declared as \"public\".");
//		} catch (InstantiationException e) {
//			if (logger.isLoggable(Level.WARNING)) {
//				logger.log(Level.WARNING,
//						"An error occurred trying to run the cadet code.", e);
//			}
//			e.printStackTrace();
//			tempMessages
//					.append("Couldn't create your program!  Talk to your instructor for assistance.");
//		} catch (Exception e) {
//			if (logger.isLoggable(Level.WARNING)) {
//				logger.log(Level.WARNING,
//						"An error occurred trying to run the cadet code.", e);
//			}
//			e.printStackTrace();
//			tempMessages
//					.append("An error occurred in your program.  The exact message (for your instructor) is:\n"
//							+ e.getMessage());
//
//		}
//		if (!hasStartMethod) {
			runProcess = null;
			String command = "java " + programName;
			isRunning = true;
			runProcess = new ProcessWrapper(command, file.getParentFile()) {
				public void onOutput(char c) {
					runMessages.append(String.valueOf(c));
				}

				public void onStop() {
					runFinished();
					runMessages.append("<h3>--- End " + programName
							+ "---</h3>");
				}
			};
//		} else {
//			runMessages.append(tempMessages);
//		}

		return runProcess;
	}

	/**
	 * DOCUMENT ME!
	 */
	protected void runFinished() {
		isRunning = false;
		runProcess = null;
		cadetInstance = null;
	}

	class MyClassLoader extends ClassLoader {
		private String mDirectory;

		public MyClassLoader(String pDirectory) {
			super();
			mDirectory = pDirectory;
		}

		protected Class findClass(String pClassName)
				throws ClassNotFoundException {
			try {
				//System.out.println( "Current dir: " + new File( mDirectory
				// ).getAbsolutePath() );
				File lClassFile = new File(mDirectory, pClassName + ".class");
				InputStream lInput = new BufferedInputStream(
						new FileInputStream(lClassFile));
				ByteArrayOutputStream lOutput = new ByteArrayOutputStream();
				int i = 0;
				while ((i = lInput.read()) >= 0) {
					lOutput.write(i);
				}
				byte[] lBytes = lOutput.toByteArray();
				return defineClass(pClassName, lBytes, 0, lBytes.length);
			} catch (Exception e) {
				throw new ClassNotFoundException("Class: " + pClassName
						+ " could not be found");
			}
		}

		public Class loadClass(String pClassName, boolean pResolve)
				throws ClassNotFoundException {
			System.out.println("loadClass(), resolve: " + pResolve);
			Class lClass = findLoadedClass(pClassName);
			if (lClass == null) {
				try {
					ClassLoader lParent = getParent();
					if (lParent == null) {
						lClass = getSystemClassLoader().loadClass(pClassName);
					} else {
						lClass = lParent.loadClass(pClassName);
					}
				} catch (ClassNotFoundException cnfe) {
					lClass = findClass(pClassName);
				}
			}
			try {
				if (pResolve) {
					System.out.println("resolve class: " + lClass);
					resolveClass(lClass);
				}
			} catch (Error e) {
				e.printStackTrace();
				throw e;
			}
			return lClass;
		}

	}
}