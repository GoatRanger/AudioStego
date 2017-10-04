package eecs.instructor.iteration;

import eecs.robot.jago.element.SolidElement;

/*
 * Created on Oct 16, 2003 To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */

/**
 * @author dm0266 To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 *         Generation&gt;Code and Comments
 */
public class Card extends SolidElement {
  double value;

  public Card(int card, String suit) {
    switch (card) {
      case 0: //King
        setAppearance("/eecs/images/cards/k" + suit + ".gif");
        this.value = .5;

        break;

      case 1: //Ace
        setAppearance("/eecs/images/cards/a" + suit + ".gif");
        this.value = 1.0;

        break;

      case 2:
        setAppearance("/eecs/images/cards/2" + suit + ".gif");
        this.value = 2.0;

        break;

      case 3:
        setAppearance("/eecs/images/cards/3" + suit + ".gif");
        this.value = 3.0;

        break;

      case 4:
        setAppearance("/eecs/images/cards/4" + suit + ".gif");
        this.value = 4.0;

        break;

      case 5:
        setAppearance("/eecs/images/cards/5" + suit + ".gif");
        this.value = 5.0;

        break;

      case 6:
        setAppearance("/eecs/images/cards/6" + suit + ".gif");
        this.value = 6.0;

        break;

      case 7:
        setAppearance("/eecs/images/cards/7" + suit + ".gif");
        this.value = 7.0;

        break;

      case 8:
        setAppearance("/eecs/images/cards/8" + suit + ".gif");
        this.value = 8.0;

        break;

      case 9:
        setAppearance("/eecs/images/cards/9" + suit + ".gif");
        this.value = 9.0;

        break;

      case 10:
        setAppearance("/eecs/images/cards/t" + suit + ".gif");
        this.value = 10.0;

        break;

      case 11:
        setAppearance("/eecs/images/cards/j" + suit + ".gif");
        this.value = .5;

        break;

      case 12:
        setAppearance("/eecs/images/cards/q" + suit + ".gif");
        this.value = .5;

        break;

      case 333: //down card for dealer
        setAppearance("/eecs/images/cards/faceDown.GIF");

        break;
    }
  }
}