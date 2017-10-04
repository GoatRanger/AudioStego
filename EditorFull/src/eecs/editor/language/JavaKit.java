package eecs.editor.language;

import de.hunsicker.jalopy.Jalopy;
import de.hunsicker.jalopy.swing.SettingsDialog;
import eecs.editor.CollectErrors;
import eecs.editor.environment.Environment;
import eecs.editor.jedit.JEditTextArea;
import eecs.editor.util.GauntletCompileTask;
import eecs.util.JavaWebStartManager;
import eecs.util.ProcessWrapper;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

class JavaKit extends DefaultLanguageKit {
  volatile GauntletCompileTask compileTask = new GauntletCompileTask();
  private Jalopy jalopy;
  private ProcessWrapper runProcess;
  private String compilerMessages = null;
  private StringBuffer javaTemplate = new StringBuffer("public class *ProgramName extends eecs.Gui\n" + "{\n"
      + "    public static void main(String[] args)\n" + "    {\n\n" + "    }\n" + "}");
  StringBuffer runMessages = null;
  private boolean canReformat = true;
  private boolean isRunning = false;
  private static String defaultJalopySettings =
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
    "   <jalopy>\r\n" +
    "       <general>\r\n" +
    "           <compliance>\r\n" +
    "               <version>14</version>\r\n" +
    "           </compliance>\r\n" +
    "           <style>\r\n" +
    "               <description>IT105 Java Coding Convention</description>\r\n" +
    "               <name>IT105</name>\r\n" +
    "           </style>\r\n" +
    "       </general>\r\n" +
    "       <internal>\r\n" +
    "           <version>6</version>\r\n" +
    "       </internal>\r\n" +
    "       <messages>\r\n" +
    "           <priority>\r\n" +
    "               <general>30000</general>\r\n" +
    "               <parser>30000</parser>\r\n" +
    "               <parserJavadoc>30000</parserJavadoc>\r\n" +
    "               <printer>30000</printer>\r\n" +
    "               <printerJavadoc>30000</printerJavadoc>\r\n" +
    "               <transform>30000</transform>\r\n" +
    "           </priority>\r\n" +
    "           <showErrorStackTrace>true</showErrorStackTrace>\r\n" +
    "       </messages>\r\n" +
    "       <misc>\r\n" +
    "           <threadCount>1</threadCount>\r\n" +
    "       </misc>\r\n" +
    "       <printer>\r\n" +
    "           <alignment>\r\n" +
    "               <methodCallChain>false</methodCallChain>\r\n" +
    "               <parameterMethodDeclaration>false</parameterMethodDeclaration>\r\n" +
    "               <ternaryOperator>true</ternaryOperator>\r\n" +
    "               <variableAssignment>false</variableAssignment>\r\n" +
    "               <variableIdentifier>false</variableIdentifier>\r\n" +
    "           </alignment>\r\n" +
    "           <backup>\r\n" +
    "               <directory>bak</directory>\r\n" +
    "               <level>0</level>\r\n" +
    "           </backup>\r\n" +
    "           <blanklines>\r\n" +
    "               <after>\r\n" +
    "                   <block>1</block>\r\n" +
    "                   <braceLeft>0</braceLeft>\r\n" +
    "                   <class>1</class>\r\n" +
    "                   <declaration>0</declaration>\r\n" +
    "                   <footer>1</footer>\r\n" +
    "                   <header>0</header>\r\n" +
    "                   <interface>1</interface>\r\n" +
    "                   <lastImport>1</lastImport>\r\n" +
    "                   <method>1</method>\r\n" +
    "                   <package>1</package>\r\n" +
    "               </after>\r\n" +
    "               <before>\r\n" +
    "                   <block>1</block>\r\n" +
    "                   <braceRight>0</braceRight>\r\n" +
    "                   <caseBlock>1</caseBlock>\r\n" +
    "                   <comment>\r\n" +
    "                       <javadoc>1</javadoc>\r\n" +
    "                       <multiline>1</multiline>\r\n" +
    "                       <singleline>1</singleline>\r\n" +
    "                   </comment>\r\n" +
    "                   <controlStatement>1</controlStatement>\r\n" +
    "                   <declaration>1</declaration>\r\n" +
    "                   <footer>0</footer>\r\n" +
    "                   <header>0</header>\r\n" +
    "               </before>\r\n" +
    "               <keepUpTo>1</keepUpTo>\r\n" +
    "           </blanklines>\r\n" +
    "           <braces>\r\n" +
    "               <empty>\r\n" +
    "                   <cuddle>false</cuddle>\r\n" +
    "                   <insertStatement>false</insertStatement>\r\n" +
    "               </empty>\r\n" +
    "               <insert>\r\n" +
    "                   <dowhile>true</dowhile>\r\n" +
    "                   <for>true</for>\r\n" +
    "                   <ifelse>true</ifelse>\r\n" +
    "                   <while>true</while>\r\n" +
    "               </insert>\r\n" +
    "               <remove>\r\n" +
    "                   <block>true</block>\r\n" +
    "                   <dowhile>false</dowhile>\r\n" +
    "                   <for>false</for>\r\n" +
    "                   <ifelse>false</ifelse>\r\n" +
    "                   <while>false</while>\r\n" +
    "               </remove>\r\n" +
    "               <treatDifferent>\r\n" +
    "                   <methodClass>false</methodClass>\r\n" +
    "                   <methodClassIfWrapped>false</methodClassIfWrapped>\r\n" +
    "               </treatDifferent>\r\n" +
    "           </braces>\r\n" +
    "           <chunks>\r\n" +
    "               <blanklines>true</blanklines>\r\n" +
    "               <comments>true</comments>\r\n" +
    "           </chunks>\r\n" +
    "           <comments>\r\n" +
    "               <format>\r\n" +
    "                   <multiline>true</multiline>\r\n" +
    "               </format>\r\n" +
    "               <javadoc>\r\n" +
    "                   <check>\r\n" +
    "                       <innerclass>false</innerclass>\r\n" +
    "                       <tags>true</tags>\r\n" +
    "                       <throwsTags>true</throwsTags>\r\n" +
    "                   </check>\r\n" +
    "                   <fieldsShort>true</fieldsShort>\r\n" +
    "                   <generate>\r\n" +
    "                       <class>0</class>\r\n" +
    "                       <constructor>0</constructor>\r\n" +
    "                       <field>0</field>\r\n" +
    "                       <method>0</method>\r\n" +
    "                   </generate>\r\n" +
    "                   <parseComments>true</parseComments>\r\n" +
    "                   <tags>\r\n" +
    "                       <in-line></in-line>\r\n" +
    "                       <standard></standard>\r\n" +
    "                   </tags>\r\n" +
    "                   <templates>\r\n" +
    "                       <method>\r\n" +
    "                           <bottom> */</bottom>\r\n" +
    "                           <exception> * @throws $exceptionType$ DOCUMENT ME!</exception>\r\n" +
    "                           <param> * @param $paramType$ DOCUMENT ME!</param>\r\n" +
    "                           <return> * @return DOCUMENT ME!</return>\r\n" +
    "                           <top>/**| * DOCUMENT ME!</top>\r\n" +
    "                       </method>\r\n" +
    "                   </templates>\r\n" +
    "               </javadoc>\r\n" +
    "               <remove>\r\n" +
    "                   <javadoc>false</javadoc>\r\n" +
    "                   <multiline>false</multiline>\r\n" +
    "                   <singleline>false</singleline>\r\n" +
    "               </remove>\r\n" +
    "               <separator>\r\n" +
    "                   <fillCharacter>-</fillCharacter>\r\n" +
    "                   <insert>false</insert>\r\n" +
    "                   <insertRecursive>false</insertRecursive>\r\n" +
    "                   <text>\r\n" +
    "                       <class>Inner Classes</class>\r\n" +
    "                       <constructor>Constructors</constructor>\r\n" +
    "                       <field>Instance fields</field>\r\n" +
    "                       <initializer>Instance initializers</initializer>\r\n" +
    "                       <interface>Inner Interfaces</interface>\r\n" +
    "                       <method>Methods</method>\r\n" +
    "                       <static>Static fields/initializers</static>\r\n" +
    "                   </text>\r\n" +
    "               </separator>\r\n" +
    "           </comments>\r\n" +
    "           <environment></environment>\r\n" +
    "           <footer>\r\n" +
    "               <keys></keys>\r\n" +
    "               <smartMode>0</smartMode>\r\n" +
    "               <use>false</use>\r\n" +
    "           </footer>\r\n" +
    "           <header>\r\n" +
    "               <keys></keys>\r\n" +
    "               <smartMode>0</smartMode>\r\n" +
    "               <use>false</use>\r\n" +
    "           </header>\r\n" +
    "           <history>\r\n" +
    "               <policy>disabled</policy>\r\n" +
    "           </history>\r\n" +
    "           <imports>\r\n" +
    "               <grouping>\r\n" +
    "                   <defaultDepth>5</defaultDepth>\r\n" +
    "                   <packages>*:0|gnu:2|java:2|javax:2</packages>\r\n" +
    "               </grouping>\r\n" +
    "               <policy>disabled</policy>\r\n" +
    "               <sort>true</sort>\r\n" +
    "           </imports>\r\n" +
    "           <indentation>\r\n" +
    "               <caseFromSwitch>true</caseFromSwitch>\r\n" +
    "               <continuation>\r\n" +
    "                   <block>false</block>\r\n" +
    "                   <operator>false</operator>\r\n" +
    "               </continuation>\r\n" +
    "               <firstColumnComments>false</firstColumnComments>\r\n" +
    "               <label>false</label>\r\n" +
    "               <policy>\r\n" +
    "                   <deep>false</deep>\r\n" +
    "               </policy>\r\n" +
    "               <sizes>\r\n" +
    "                   <braceCuddled>1</braceCuddled>\r\n" +
    "                   <braceLeft>0</braceLeft>\r\n" +
    "                   <braceRight>0</braceRight>\r\n" +
    "                   <braceRightAfter>0</braceRightAfter>\r\n" +
    "                   <continuation>4</continuation>\r\n" +
    "                   <deep>55</deep>\r\n" +
    "                   <extends>-1</extends>\r\n" +
    "                   <general>4</general>\r\n" +
    "                   <implements>-1</implements>\r\n" +
    "                   <leading>0</leading>\r\n" +
    "                   <tabs>4</tabs>\r\n" +
    "                   <throws>-1</throws>\r\n" +
    "                   <trailingComment>1</trailingComment>\r\n" +
    "               </sizes>\r\n" +
    "               <tabs>\r\n" +
    "                   <enable>false</enable>\r\n" +
    "                   <onlyLeading>false</onlyLeading>\r\n" +
    "               </tabs>\r\n" +
    "           </indentation>\r\n" +
    "           <misc>\r\n" +
    "               <arrayBracketsAfterIdent>false</arrayBracketsAfterIdent>\r\n" +
    "               <forceFormatting>true</forceFormatting>\r\n" +
    "               <insertExpressionParentheses>true</insertExpressionParentheses>\r\n" +
    "               <insertLoggingConditional>false</insertLoggingConditional>\r\n" +
    "               <insertTrailingNewline>true</insertTrailingNewline>\r\n" +
    "               <insertUID>false</insertUID>\r\n" +
    "           </misc>\r\n" +
    "           <sorting>\r\n" +
    "               <declaration>\r\n" +
    "                   <class>false</class>\r\n" +
    "                   <constructor>false</constructor>\r\n" +
    "                   <enable>true</enable>\r\n" +
    "                   <interface>false</interface>\r\n" +
    "                   <method>false</method>\r\n" +
    "                   <order>static|field|initializer|constructor|method|interface|class</order>\r\n" +
    "                   <variable>false</variable>\r\n" +
    "               </declaration>\r\n" +
    "               <modifier>\r\n" +
    "                   <enable>false</enable>\r\n" +
    "                   <order>public|protected|private|abstract|static|final|synchronized|transient|volatile|native|strictfp</order>\r\n" +
    "               </modifier>\r\n" +
    "           </sorting>\r\n" +
    "           <whitespace>\r\n" +
    "               <after>\r\n" +
    "                   <comma>true</comma>\r\n" +
    "                   <semicolon>true</semicolon>\r\n" +
    "                   <typeCast>true</typeCast>\r\n" +
    "               </after>\r\n" +
    "               <before>\r\n" +
    "                   <braces>true</braces>\r\n" +
    "                   <brackets>false</brackets>\r\n" +
    "                   <bracketsTypes>false</bracketsTypes>\r\n" +
    "                   <caseColon>true</caseColon>\r\n" +
    "                   <operator>\r\n" +
    "                       <not>false</not>\r\n" +
    "                   </operator>\r\n" +
    "                   <parentheses>\r\n" +
    "                       <methodCall>false</methodCall>\r\n" +
    "                       <methodDeclaration>false</methodDeclaration>\r\n" +
    "                       <statement>true</statement>\r\n" +
    "                   </parentheses>\r\n" +
    "               </before>\r\n" +
    "               <padding>\r\n" +
    "                   <braces>true</braces>\r\n" +
    "                   <brackets>false</brackets>\r\n" +
    "                   <operator>\r\n" +
    "                       <assignment>true</assignment>\r\n" +
    "                       <bitwise>true</bitwise>\r\n" +
    "                       <logical>true</logical>\r\n" +
    "                       <mathematical>true</mathematical>\r\n" +
    "                       <relational>true</relational>\r\n" +
    "                       <shift>true</shift>\r\n" +
    "                   </operator>\r\n" +
    "                   <parenthesis>false</parenthesis>\r\n" +
    "                   <typeCast>false</typeCast>\r\n" +
    "               </padding>\r\n" +
    "           </whitespace>\r\n" +
    "           <wrapping>\r\n" +
    "               <always>\r\n" +
    "                   <after>\r\n" +
    "                       <arrayElement>0</arrayElement>\r\n" +
    "                       <braceRight>true</braceRight>\r\n" +
    "                       <extendsTypes>false</extendsTypes>\r\n" +
    "                       <implementsTypes>false</implementsTypes>\r\n" +
    "                       <label>true</label>\r\n" +
    "                       <methodCallChained>false</methodCallChained>\r\n" +
    "                       <ternaryOperator>\r\n" +
    "                           <first>false</first>\r\n" +
    "                           <second>false</second>\r\n" +
    "                       </ternaryOperator>\r\n" +
    "                       <throwsTypes>false</throwsTypes>\r\n" +
    "                   </after>\r\n" +
    "                   <before>\r\n" +
    "                       <braceLeft>true</braceLeft>\r\n" +
    "                       <extends>false</extends>\r\n" +
    "                       <implements>false</implements>\r\n" +
    "                       <throws>false</throws>\r\n" +
    "                   </before>\r\n" +
    "                   <parameter>\r\n" +
    "                       <methodCall>false</methodCall>\r\n" +
    "                       <methodCallNested>false</methodCallNested>\r\n" +
    "                       <methodDeclaration>false</methodDeclaration>\r\n" +
    "                   </parameter>\r\n" +
    "               </always>\r\n" +
    "               <general>\r\n" +
    "                   <beforeOperator>false</beforeOperator>\r\n" +
    "                   <enable>true</enable>\r\n" +
    "                   <lineLength>160</lineLength>\r\n" +
    "               </general>\r\n" +
    "               <ondemand>\r\n" +
    "                   <after>\r\n" +
    "                       <assignment>false</assignment>\r\n" +
    "                       <leftParenthesis>false</leftParenthesis>\r\n" +
    "                       <parameter>false</parameter>\r\n" +
    "                       <types>\r\n" +
    "                           <extends>false</extends>\r\n" +
    "                           <implements>false</implements>\r\n" +
    "                           <throws>false</throws>\r\n" +
    "                       </types>\r\n" +
    "                   </after>\r\n" +
    "                   <before>\r\n" +
    "                       <rightParenthesis>false</rightParenthesis>\r\n" +
    "                   </before>\r\n" +
    "                   <groupingParentheses>false</groupingParentheses>\r\n" +
    "               </ondemand>\r\n" +
    "           </wrapping>\r\n" +
    "       </printer>\r\n" +
    "   </jalopy>\r\n\r\n";

  JavaKit() {
    super();
    // When creating the Jalopy formatter, sometimes throws an exception.
    // Catch it here so it doesn't show on the screen.
    try {
      jalopy = new Jalopy();
    }
    catch (ClassCastException e) {
      canReformat = true;
    }
    catch (RuntimeException e) {
      canReformat = false;
      // Thrown by Jalopy when can't load the property settings
    }
    setFormattingConvention();
    setTemplate(javaTemplate);
    language = LanguageKit.JAVA;
    description = "Java Program";
    fileSuffix = ".java";
  }

  protected void setFormattingConvention() {
    String userPath = Environment.getUsersConfigPath();
    String jalopySettings = "jalopy.xml";
    File file = new File(userPath + jalopySettings);
    if (!file.exists()) {
      try {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(userPath + jalopySettings)));
        out.write(defaultJalopySettings);
        out.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      Jalopy.setConvention(userPath + jalopySettings);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean hasFormatPreferences() {
    return canReformat;
  }

  public boolean hasFormatter() {
    return canReformat;
  }

  public void setFormatPrefs(Window window) {
    if (window == null) {
      window = new JFrame();
    }
    SettingsDialog dlg = SettingsDialog.create(window);
    dlg.setSize(500, 500);
    dlg.setVisible(true);
  }

  public void reformat(JEditTextArea textpane) {
    File temp = null;
    try {
      temp = File.createTempFile("fmt", "java");
      temp.deleteOnExit();
    }
    catch (IOException e) {
      return;
    }
    jalopy.setInput(new StringReader(textpane.getText()), temp.getPath());

    StringBuffer stringBuffer = new StringBuffer();
    jalopy.setOutput(stringBuffer);
    jalopy.setForce(true);
    if (jalopy.format()) {
      textpane.setText(stringBuffer.toString());
    }
  }

  // Returns the result code of the last compile. If the program was fully compiled, but results are not available (due to thread timing),
  // this method will block until the results are available.
  public int getCompileResult() {
    if (getCompilerPercentComplete() < 100) {
      return COMPILE_INCOMPLETE;
    }
    // Compiling is finished,
    while (compileResult == COMPILE_INCOMPLETE) {
      try {
        Thread.sleep(50);
      }
      catch (InterruptedException ie) {
        // Not sure what we should do with it...maybe nothing?
      }
    }
    return compileResult;
  }

  public boolean isCompilerDone() {
    if (getCompilerPercentComplete() < 100) {
      return false;
    }
    while (compileResult == COMPILE_INCOMPLETE) {
      try {
        Thread.sleep(50);
      }
      catch (InterruptedException ie) {
        //
      }
    }
    return true;
  }

  public String getCompilerMessages() {
    StringBuffer buffer = new StringBuffer("<html><body><h4>---BEGIN CHECKS FOR COMMON MISTAKES---</h4>");
    boolean gauntletOk = false;
    Toolkit.getDefaultToolkit().beep();
    if (compileTask == null) {
      buffer.append("<em>Couldn't Compile.</em>");
    }
    else {
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
        eecs.editor.gauntlet.CheckerError ce = (eecs.editor.gauntlet.CheckerError) i.next();
        buffer.append("<p><b>Mistake ");
        buffer.append((n++));
        buffer.append(":</b><br>");
        buffer.append(ce.getMessage());
        buffer.append("</p>");
      }
      if (gauntletOk) {
        buffer.append("<p><i>  None of the common mistakes were found! "
            + "Now just check below to make sure the compiler didn't find any 'uncommon' mistakes.</i></p>");
      }
      buffer.append("<b><h4>--- END COMMON MISTAKE CHECKS---</h4></b>");
      String[] msgs;
      synchronized (compileTask) {
        msgs = compileTask.getCompilerMessages();
      }
      if ((msgs != null) && (msgs.length > 0)) {
        CollectErrors.addErrorsToTotal(msgs);
        buffer.insert(buffer.indexOf("<body>") + 6, "<h1><font color=\"red\">You have errors.  Messages Follow.</font></h1>\r\r\n");
        buffer.append("<p><font color=\"red\"> --- COMPILING: ERRORS DETECTED ---</font><br><code>");
        for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
          buffer.append(textToHTML(msgs[msgNum]) + "<br>");
        }
        buffer.append("</code></p>");
      }
      else {
        buffer.append("<h3>--- BEGIN COMPILING ---</h3><p><i>No Syntax Errors Detected</i></p>");
      }
    }
    buffer.append("<h3>--- Done Compiling ---</h3></body></html>");
    compilerMessages = buffer.toString();
    return compilerMessages;
  }

  public int getCompilerPercentComplete() {
    if (compileTask == null) {
      return 100;
    }
    synchronized (compileTask) {
      return (int) ((double) compileTask.getCurrent() / (double) compileTask.getLengthOfTask() * 100);
    }
  }

  public String getRunResults() {
    while (isRunning && (runProcess != null)) {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
        //
      }
    }
    if (runMessages == null) {
      runMessages = new StringBuffer();
    }
    return runMessages.toString();
  }

  private String textToHTML(String text) {
    String result;
    result = text.replaceAll("&", "&amp;");
    result = result.replaceAll("<", "&lt;");
    result = result.replaceAll(">", "&gt;");
    result = result.replaceAll("\"", "&quot;");
    result = result.replaceAll("'", "&#39;");
    result = result.replaceAll("\r\n", "<br>");
    result = result.replaceAll("\\s", "&nbsp;");
    return result;
  }

  public boolean isRunnable() {
    return true;
  }

  public void compile(File file) {
    compileResult = COMPILE_INCOMPLETE;
    synchronized (compileTask) {
      compileTask.setFile(file);
      compileTask.go();
    }
    new Thread(new Runnable() {
      public void run() {
        while (!compileTask.done()) {
          try {
            Thread.sleep(50);
          }
          catch (InterruptedException ie) {
            //
          }
        }
        String[] msg;
        synchronized (compileTask) {
          msg = compileTask.getCompilerMessages();
        }
        if ((msg == null) || (msg.length == 0)) {
          compileResult = COMPILE_SUCCESS;
        }
        else {
          compileResult = COMPILE_FAILURE;
        }
      }
    }, "java_compile").start();
  }

  public boolean hasCompiler() {
    return true;
  }

  public ProcessWrapper run(File file) {
System.out.println("RUN");    
    runMessages = new StringBuffer();
    String fileName = file.getName();
    final String programName = fileName.substring(0, fileName.lastIndexOf("."));
    runMessages.append("<html><head><title>Running \"" + programName + "\"</title></head><body>");
    runMessages.append("<h3>--- Running \"" + programName + "\" - Output: ---</h3>");
    String command ="java -Djava.ext.dirs=\"\" -classpath .;" + "\"" + Environment.getJagoPath() + "\"" +
        ";" + "\"" + Environment.getEclipseLibDirectory() + "\"" +
        ";" + "\"" + Environment.getWrapperPath() + "\"" +
        ";" + "\"" + Environment.getInstructorPath() + "\"" +
        ";" + "\"" + Environment.getMediaPath() + "\" " + programName;
    
System.out.println("Run java:jws="+(JavaWebStartManager.hasBeenStarted()?"true":"false"));    
System.out.println("  " + command.toString());

    isRunning = true;
    runProcess = new ProcessWrapper(command, file.getParentFile()) {
      public void onOutput(char c) {
        runMessages.append(String.valueOf(c));
      }

      public void onStop() {
        runFinished();
        runMessages.append("<h3>--- End " + programName + "---</h3>");
      }
    };
    return runProcess;
  }

  protected void runFinished() {
    isRunning = false;
    runProcess = null;
  }
}