package com.engraver.ui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BasicSpline {
   private List points = new ArrayList();
   private List xCubics = new ArrayList();
   private List yCubics = new ArrayList();

   public void addPoint(Point point) {
      this.points.add(point);
   }

   private List extractValues(List points, PosField field) {
      List ints = new ArrayList();
      Iterator var4 = points.iterator();

      while(var4.hasNext()) {
         Point p = (Point)var4.next();
         switch (field) {
            case X:
               ints.add(p.x);
               break;
            case Y:
               ints.add(p.y);
         }
      }

      return ints;
   }

   public void calcSpline() {
      this.calcNaturalCubic(this.extractValues(this.points, BasicSpline.PosField.X), this.xCubics);
      this.calcNaturalCubic(this.extractValues(this.points, BasicSpline.PosField.Y), this.yCubics);
   }

   public Point getPoint(float position) {
      position *= (float)this.xCubics.size();
      int cubicNum = (int)Math.min((float)(this.xCubics.size() - 1), position);
      float cubicPos = position - (float)cubicNum;
      return new Point((int)((Cubic)this.xCubics.get(cubicNum)).eval((double)cubicPos), (int)((Cubic)this.yCubics.get(cubicNum)).eval((double)cubicPos));
   }

   public void calcNaturalCubic(List values, Collection cubics) {
      int num = values.size() - 1;
      double[] gamma = new double[num + 1];
      double[] delta = new double[num + 1];
      double[] D = new double[num + 1];
      gamma[0] = 0.5;

      int i;
      for(i = 1; i < num; ++i) {
         gamma[i] = 1.0 / (4.0 - gamma[i - 1]);
      }

      gamma[num] = 1.0 / (2.0 - gamma[num - 1]);
      int p0 = (Integer)values.get(0);
      int p1 = (Integer)values.get(1);
      delta[0] = (double)(3.0F * (float)(p1 - p0)) * gamma[0];

      for(i = 1; i < num; ++i) {
         p0 = (Integer)values.get(i - 1);
         p1 = (Integer)values.get(i + 1);
         delta[i] = ((double)(3.0F * (float)(p1 - p0)) - delta[i - 1]) * gamma[i];
      }

      p0 = (Integer)values.get(num - 1);
      p1 = (Integer)values.get(num);
      delta[num] = ((double)(3.0F * (float)(p1 - p0)) - delta[num - 1]) * gamma[num];
      D[num] = delta[num];

      for(i = num - 1; i >= 0; --i) {
         D[i] = delta[i] - gamma[i] * D[i + 1];
      }

      cubics.clear();

      for(i = 0; i < num; ++i) {
         p0 = (Integer)values.get(i);
         p1 = (Integer)values.get(i + 1);
         cubics.add(new Cubic((double)p0, D[i], (double)(3 * (p1 - p0)) - 2.0 * D[i] - D[i + 1], (double)(2 * (p0 - p1)) + D[i] + D[i + 1]));
      }

   }

   private static enum PosField {
      X,
      Y;
   }

   public class Cubic {
      private double a;
      private double b;
      private double c;
      private double d;

      public Cubic(double p0, double d2, double e, double f) {
         this.d = p0;
         this.c = d2;
         this.b = e;
         this.a = f;
      }

      public double eval(double u) {
         return ((this.a * u + this.b) * u + this.c) * u + this.d;
      }
   }
}
