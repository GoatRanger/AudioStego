/*
 * Copyright (C) 2002 USMA This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any
 * later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package eecs.robot.jago.element;

/**
 * This class implements the legacy sensor model from the Jago simulation versions prior to version 6.0. It does not
 * provide true realistic operation and is limited in the implementation of light and touch sensors. It will accept
 * other types as parameters, but there is no guarantee that the functionality will be provided under this model. For
 * enhanced sensors, use a <CODE>StandardSensor</CODE> extended with the appropriate functionality.
 */
public final class Sensor extends eecs.robot.jago.element.StandardSensor {

  // Number of ticks to keep a sensor active, even if not triggered
  private static final int TIME_TO_LIVE = 2;

  protected int intensity;

  public Sensor() {
    this(null);
  }

  public Sensor(Monitor host) {
    intensity = -1;
    type = NO_SENSOR;
    realismLevel = IDEALISTIC;
    size = new java.awt.Dimension(3, 2);
  }

  public void setValue(int value) {

    int oldVal = this.value;
    this.value = value;
    activeTimer = TIME_TO_LIVE;

    if (value != oldVal) {

      // Cause this thread to sleep to allow user thread to detect sensor
      try {
        Thread.sleep(25);
      }
      catch (Exception e) {
        //
      }
    }

    changed = true;
  }

  public void draw(java.awt.Graphics2D g2) {

    // Do Nothing
  }

  public String toString() {

    String st;

    switch (type) {

      case 0:
        st = "Touch";

        break;

      case 2:
        st = "Light Sensor";

        break;

      case 1:
        st = "Bumper";

        break;

      case 4:
        st = "Left Bumper";

        break;

      default:
        st = "Unknown";

        break;
    }

    return st + ": " + value;
  }

  protected void setType(int type) {

    if (type > NOT_CONNECTED && type <= LAST_SENSOR_TYPE) {
      this.type = type;
      value = OFF;
    }
  }

  class TimerListener implements java.awt.event.ActionListener {

    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

      if (activeTimer > 0 && type == LIGHT) {
        activeTimer--;

        if (activeTimer == 0) {
          reset();
        }
      }

      changed = false;
    }
  }
}