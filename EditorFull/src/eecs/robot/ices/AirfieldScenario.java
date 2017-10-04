package eecs.robot.ices;

import eecs.robot.jago.ImageLoader;
import java.awt.Image;

public class AirfieldScenario extends eecs.RobotGui {
  static {
    Image bg = ImageLoader.getImage(AirfieldScenario.class,
        "/eecs/images/backgroundBaghdadAirBase.jpg");
    sim.setBackgroundImage(bg);
  }
}