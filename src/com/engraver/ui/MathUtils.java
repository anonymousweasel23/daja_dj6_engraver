package com.engraver.ui;

import java.awt.Point;

public class MathUtils {
   public static double getAngle(Point p1, Point p2) {
      double xDiff = (double)(p2.x - p1.x);
      double yDiff = (double)(p2.y - p1.y);
      return 180.0 - (Math.toDegrees(Math.atan2(yDiff, xDiff)) - 90.0);
   }

   public static Point movePoint(Point p, double angle, int distance) {
      p = new Point(p);
      p.x = (int)((double)p.x + (double)distance * Math.sin(angle));
      p.y = (int)((double)p.y + (double)distance * Math.cos(angle));
      return p;
   }
}
