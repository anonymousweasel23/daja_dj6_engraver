package com.engraver.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaintCanvas extends DoubleBuffer {
   private List points = new ArrayList();
   private BasicSpline spline = new BasicSpline();
   private Point grabbedPoint = null;
   private Point grabClick = null;
   private static final int POINT_RADIUS = 15;
   private static final int BORDER = 3;
   private static final int LINE_RADIUS = 10;

   private Point getNearestPoint(int x, int y, int radius) {
      Iterator var4 = this.points.iterator();

      Point pos;
      double dist;
      do {
         if (!var4.hasNext()) {
            return null;
         }

         pos = (Point)var4.next();
         dist = Math.sqrt(Math.pow((double)(x - pos.x), 2.0) + Math.pow((double)(y - pos.y), 2.0));
      } while(!(dist <= (double)radius));

      return pos;
   }

   public PaintCanvas() {
      this.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent e) {
            switch (e.getButton()) {
               case 1:
                  PaintCanvas.this.grabbedPoint = PaintCanvas.this.getNearestPoint(e.getX(), e.getY(), 18);
                  if (PaintCanvas.this.grabbedPoint != null) {
                     PaintCanvas.this.grabClick = new Point(e.getX() - PaintCanvas.this.grabbedPoint.x, e.getY() - PaintCanvas.this.grabbedPoint.y);
                  } else {
                     PaintCanvas.this.points.add(new Point(e.getX(), e.getY()));
                     PaintCanvas.this.recalcSpline();
                     PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
                  }
                  break;
               case 3:
                  Point toRemove = PaintCanvas.this.getNearestPoint(e.getX(), e.getY(), 18);
                  if (toRemove != null) {
                     PaintCanvas.this.points.remove(toRemove);
                     PaintCanvas.this.recalcSpline();
                     PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
                  }
            }

         }

         public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1 && PaintCanvas.this.grabbedPoint != null) {
               PaintCanvas.this.grabbedPoint = null;
               PaintCanvas.this.recalcSpline();
               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
            }

         }
      });
      this.addMouseMotionListener(new MouseMotionListener() {
         public void mouseMoved(MouseEvent e) {
         }

         public void mouseDragged(MouseEvent e) {
            if (PaintCanvas.this.grabbedPoint != null) {
               PaintCanvas.this.grabbedPoint.x = e.getX() - PaintCanvas.this.grabClick.x;
               PaintCanvas.this.grabbedPoint.y = e.getY() - PaintCanvas.this.grabClick.y;
               PaintCanvas.this.recalcSpline();
               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
            }

         }
      });
      this.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == 'c') {
               PaintCanvas.this.points = new ArrayList();
               PaintCanvas.this.recalcSpline();
               PaintCanvas.this.paint(PaintCanvas.this.getGraphics());
            }

         }
      });
   }

   public void recalcSpline() {
      this.spline = new BasicSpline();
      if (this.points.size() > 2) {
         Iterator var1 = this.points.iterator();

         while(var1.hasNext()) {
            Point p = (Point)var1.next();
            this.spline.addPoint(p);
         }

         this.spline.calcSpline();
      }

   }

   public void paintBuffer(Graphics g) {
      Graphics2D g2D = (Graphics2D)g;
      g2D.setColor(Color.LIGHT_GRAY);
      g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
      Point pointBefore = null;
      Point p;
      if (this.points.size() > 2) {
         for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + 0.001)) {
            p = this.spline.getPoint(f);
            Point pnt = new Point(p.x, p.y);
            this.drawConnectionPoint(pnt, g2D);
            if (pointBefore != null) {
               this.drawConnection(pnt, pointBefore, g2D);
            }

            pointBefore = pnt;
         }
      }

      Iterator var7 = this.points.iterator();

      while(var7.hasNext()) {
         p = (Point)var7.next();
         this.drawPoint(p, g2D);
      }

   }

   private void drawConnection(Point a, Point b, Graphics2D g2D) {
      g2D.setColor(Color.RED);
      g2D.setStroke(new BasicStroke(20.0F));
      g2D.drawLine(a.x, a.y, b.x, b.y);
   }

   private void drawConnectionPoint(Point p, Graphics2D g2D) {
      g2D.setColor(Color.RED);
      g2D.fillOval(p.x - 10, p.y - 10, 20, 20);
   }

   private void drawPoint(Point p, Graphics2D g2D) {
      g2D.setColor(Color.BLACK);
      g2D.fillOval(p.x - 18, p.y - 18, 36, 36);
      g2D.setColor(Color.GRAY);
      g2D.fillOval(p.x - 15, p.y - 15, 30, 30);
   }
}
