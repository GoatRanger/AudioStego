package eecs.instructor.cobb;

import jago.element.SolidElement;


/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author dm0266
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Card extends SolidElement {
  double value;

  public Card(int card, String suit) {
    switch (card) {
    case 0: //King
      setAppearance("images/k" + suit + ".gif");
      this.value = .5;

      break;

    case 1: //Ace
      setAppearance("images/a" + suit + ".gif");
      this.value = 1.0;

      break;

    case 2:
      setAppearance("images/2" + suit + ".gif");
      this.value = 2.0;

      break;

    case 3:
      setAppearance("images/3" + suit + ".gif");
      this.value = 3.0;

      break;

    case 4:
      setAppearance("images/4" + suit + ".gif");
      this.value = 4.0;

      break;

    case 5:
      setAppearance("images/5" + suit + ".gif");
      this.value = 5.0;

      break;

    case 6:
      setAppearance("images/6" + suit + ".gif");
      this.value = 6.0;

      break;

    case 7:
      setAppearance("images/7" + suit + ".gif");
      this.value = 7.0;

      break;

    case 8:
      setAppearance("images/8" + suit + ".gif");
      this.value = 8.0;

      break;

    case 9:
      setAppearance("images/9" + suit + ".gif");
      this.value = 9.0;

      break;

    case 10:
      setAppearance("images/t" + suit + ".gif");
      this.value = 10.0;

      break;

    case 11:
      setAppearance("images/j" + suit + ".gif");
      this.value = .5;

      break;

    case 12:
      setAppearance("images/q" + suit + ".gif");
      this.value = .5;

      break;

    case 333: //down card for dealer
      setAppearance("images/faceDown.GIF");

      break;
    }
  }
}
