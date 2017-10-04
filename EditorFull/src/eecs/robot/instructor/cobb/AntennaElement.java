package eecs.robot.instructor.cobb;

import java.awt.Image;
import eecs.robot.jago.element.SolidElement;

public class AntennaElement extends SolidElement {
  Image image;

  public AntennaElement() {
    super();
    setAppearance("/eecs/images/tower.jpg");
  }
}