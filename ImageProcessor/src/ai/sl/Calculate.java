////////////////////////////////////////////////////////////////////////////
//
// Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2002.
// Unpublished work.  All Rights Reserved.
//
// The software contained on this media is the property of the
// DSTC Pty Ltd.  Use of this software is strictly in accordance
// with the license agreement in the accompanying LICENSE.DOC
// file. If your distribution of this software does not contain
// a LICENSE.DOC file then you have no rights to use this
// software in any manner and should contact DSTC at the address
// below to determine an appropriate licensing arrangement.
//
//      DSTC Pty Ltd
//      Level 7, GP South
//      University of Queensland
//      St Lucia, 4072
//      Australia
//      Tel: +61 7 3365 4310
//      Fax: +61 7 3365 4311
//      Email: enquiries@dstc.edu.au
//
// This software is being provided "AS IS" without warranty of
// any kind.  In no event shall DSTC Pty Ltd be liable for
// damage of any kind arising out of or in connection with
// the use or performance of this software.
//
////////////////////////////////////////////////////////////////////////////
package ai.sl;


/**
 * This class allows the evaluation of an expression stored in an XML file to be
 * evaluated from the command line. Simply Type: java Calculate file.xml. 
 * <p>
 * Subjective logic expressions are specified with the following xml tags:
 * <p>
 * Addition:<br>
 *    &lt add&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/add&gt<br>
 * <p>
 * Multiplication:<br>
 *    &lt multiply&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/multiply&gt<br>
 * <p>
 * Co-multiplication:<br>
 *    &lt co-multiply&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/co-multiply&gt<br>
 * <p>
 * Complement:<br>
 *    &lt complement&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *    &lt/complement&gt<br>
 * <p>
 * Discounting:<br>
 *    &lt discount&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/discount&gt<br>
 * <p>
 * Consensus:<br>
 *    &lt consensus&gt<br>
 *      &lt opinion b="0.01" d="0.98" u="0.01" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.98" d="0.01" u="0.01" a="0.5"&gt&lt/opinion&gt<br>
 *    &lt/consensus&gt<br>
 * <p>
 * Consensus of two dogmatic opinions:<br>
 *    &lt consensus relative_dogmatism = "0.1"&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *    &lt/consensus&gt<br>
 * <p>
 * Conditional inference:<br>
 *    &lt conditional-inference&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.01" d="0.00" u="0.99" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/conditional-inference&gt<br>
 * </p>
 * <p>Copyright (c) DSTC Pty Ltd 2002</p>
 * @author Robert Peime
 * @author Audun Josang
 * @version 2.0 - 15/10/2002
 */
public class Calculate {

        private static void main(String args[]) {

//        if (args.length != 1) {
                // Usage
//                System.err.println("Usage: java Calculate NameOfXMLFile");
//                System.exit(1);
//        }

//        Opinion op1 = OpinionProcessor.process(args[0]);
//        System.out.println(op1.toString());
          

          if (args.length == 0)
          {
             System.err.println("Usage: java Calculate XMLFileName1 XMLFileName2 ... XMLFileNameN");
             System.exit(1);
          }
          else
          {
             int count = 0;
             while (count != args.length)
             {
               Opinion op = OpinionProcessor.process(args[count]);
               System.out.println("The output of processing "+args[count]+" is: ");
               System.out.println(op.toString());
               count++;
             }
          }


        }
}
