package eecs.robot.finalProject.finalProject051;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import eecs.robot.jago.element.FlatObject;
import eecs.robot.jago.element.VisibleElement;

public class MultiSegmentLine extends VisibleElement implements FlatObject {
  int width;
  Color color;
  GeneralPath path;
  boolean fillPath;
  boolean pendingCollison;

  public MultiSegmentLine(GeneralPath path, Color color, boolean fillPath) {
    super();
    this.width = 1;
    this.path = path;
    this.color = color;
    this.fillPath = fillPath;
  }

  public MultiSegmentLine(GeneralPath path, Color color, boolean fillPath, int width) {
    this(path, color, fillPath);
    this.width = width;
  }

  public void createArea() {
    this.myShape = new Area(path);
  }

  public void draw(Graphics2D g2) {
    Color oldColor;
    Stroke oldStroke;
    oldColor = g2.getColor();
    oldStroke = g2.getStroke();
    g2.setColor(color);
    g2.setStroke(new java.awt.BasicStroke(width, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
    if (fillPath)
      g2.fill(path);
    g2.draw(path);
    g2.setColor(oldColor);
    g2.setStroke(oldStroke);
  }
}