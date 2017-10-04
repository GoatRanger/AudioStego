package eecs.instructor.iteration;

import eecs.robot.jago.element.Message;

/*
 * Created on Oct 13, 2003 To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */

/**
 * @author MAJ Michael J. Cobb To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PlayerObj {
  int curCardPositionX;

  int curCardPositionY;

  int titleXLocation;

  int titleYLocation;

  double curCardTotal;

  int bank;

  Message title;

  public void setCurCardValue(double value) {
    this.curCardTotal = 0;
  }

  public void anteUp(int ante) {
    this.bank -= ante;
  }

  public void addToBank(int pot) {
    this.bank += pot;
  }

  public void advanceCurCardPositionX() {
    this.curCardPositionX += 15;
  }

  public void setCurCardPositionX(int xCoord) {
    this.curCardPositionX = xCoord;
  }

  public void setCurCardPositionY(int yCoord) {
    this.curCardPositionY = yCoord;
  }

  public void addtoCardTotal(double cardTotal) {
    this.curCardTotal += cardTotal;
  }

  public void setTitleLocation(int xCoord, int yCoord) {
    this.titleXLocation = xCoord;
    this.titleYLocation = yCoord;
  }

  public void setTitle(Message title) {
    this.title = title;
  }

  public Message getTitle() {
    return title;
  }

  public void setBank(int amount) {
    this.bank = amount;
  }

  public int getCurCardPositionX() {
    return curCardPositionX;
  }

  public int getCurCardPositionY() {
    return curCardPositionY;
  }

  public double getCurCardTotal() {
    return curCardTotal;
  }

  public int getBank() {
    return bank;
  }

  public int getTitleXLocation() {
    return titleXLocation;
  }

  public int getTitleYLocation() {
    return titleYLocation;
  }

}