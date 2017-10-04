package ai.sl;

/**
 * This class provides an example of how an application uses the subjective logic API.
 * Subjective logic expressions are specified with the following xml tags:
 * <p>
 * Multiplication:<br>
 *    &lt mult&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/mult&gt<br>
 * <p>
 * Co-multiplication:<br>
 *    &lt comult&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/comult&gt<br>
 * <p>
 * Negation:<br>
 *    &lt neg&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
  *    &lt/neg&gt<br>
 * <p>
 * Discounting:<br>
 *    &lt disc&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/disc&gt<br>
 * <p>
 * Consensus:<br>
 *    &lt cons&gt<br>
 *      &lt opinion b="0.01" d="0.98" u="0.01" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.98" d="0.01" u="0.01" a="0.5"&gt&lt/opinion&gt<br>
 *    &lt/cons&gt<br>
 * <p>
 * Consensus of two dogmatic opinions:<br>
 *    &lt cons relative_dogmatism = "0.1"&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *    &lt/cons&gt<br>
 * <p>
 * Conditioning:<br>
 *    &lt cond&gt<br>
 *      &lt opinion b="0.01" d="0.99" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.99" d="0.01" u="0.00" a="0.5"&gt&lt/opinion&gt<br>
 *      &lt opinion b="0.01" d="0.00" u="0.99" a="0.2"&gt&lt/opinion&gt<br>
 *    &lt/cond&gt<br>
 * </p>
 * @author Robert Peime
 * @author Audun Josang
 * @version 1.3 - 12/04/2002
 */

public class Example1 {

        private static void main(String args[]) {

        // The next 2 lines show how to generate an opinion given an
        // xml schema of the decomposition of an opinion expression

        Opinion op1 = OpinionProcessor.process("expression1.xml");
        Opinion op2 = OpinionProcessor.process("expression2.xml");


        // The next line shows how to generate an opinion using an opinion
        // operator (in this case the 'Discount' operator).
        Opinion op3 = Opinion.Discount(op1, op2);

        // print the discounted opinion to screen
        System.out.println("Discounted Opinion:  " + op3.toString());

        // negate the op3
        Opinion op4 = Opinion.Complement(op3);

        // print the negated opinion3
        System.out.println("Complement:            " + op4.toString());
        }
}
