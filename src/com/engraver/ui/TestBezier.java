package com.engraver.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestBezier {
   private static Point2D[] keyPointP;
   private static Ellipse2D.Double[] keyPointE;
   private static int keyPointNum;
   private static double t = 0.001;
   private static boolean flagShow = true;

   public TestBezier() {
      JFrame f = new JFrame();
      f.setTitle("Bezier Test");
      f.setDefaultCloseOperation(3);
      f.setSize(800, 600);
      f.setLocationRelativeTo((Component)null);
      keyPointNum = 0;
      keyPointP = new Point2D[4];
      keyPointE = new Ellipse2D.Double[4];
      BezierPanel bezierPanel = new BezierPanel();
      bezierPanel.setPreferredSize(new Dimension(800, 600));
      bezierPanel.setBackground(Color.WHITE);
      f.setContentPane(bezierPanel);
      f.setVisible(true);
   }

   public static void main(String[] args) {
      new TestBezier();
   }

   static class BezierPanel extends JPanel {
      private static final long serialVersionUID = 1L;
      private int keyPointID = -1;

      BezierPanel() {
         this.addMouseListener(new MouseAction());
         this.addMouseMotionListener(new MouseMotion());
      }

      protected void paintComponent(Graphics gs) {
         super.paintComponent(gs);
         Graphics2D g = (Graphics2D)gs;
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g.setColor(Color.BLUE);
         if (TestBezier.flagShow) {
            for(int i = 0; i < TestBezier.keyPointNum; ++i) {
               if (i > 0 && i < TestBezier.keyPointNum - 1) {
                  g.fill(TestBezier.keyPointE[i]);
               } else {
                  g.draw(TestBezier.keyPointE[i]);
               }

               if (TestBezier.keyPointNum > 1 && i < TestBezier.keyPointNum - 1) {
                  g.drawLine((int)TestBezier.keyPointP[i].getX(), (int)TestBezier.keyPointP[i].getY(), (int)TestBezier.keyPointP[i + 1].getX(), (int)TestBezier.keyPointP[i + 1].getY());
               }

               if (i == 0) {
                  g.drawString("A", (int)TestBezier.keyPointE[i].x, (int)TestBezier.keyPointE[i].y);
               } else if (i == 1) {
                  g.drawString("B", (int)TestBezier.keyPointE[i].x, (int)TestBezier.keyPointE[i].y);
               } else if (i == 2) {
                  g.drawString("C", (int)TestBezier.keyPointE[i].x, (int)TestBezier.keyPointE[i].y);
               } else if (i == 3) {
                  g.drawString("D", (int)TestBezier.keyPointE[i].x, (int)TestBezier.keyPointE[i].y);
               }
            }
         }

         double y;
         double k;
         double r;
         double x;
         if (TestBezier.keyPointNum == 3) {
            g.setColor(Color.RED);

            for(k = TestBezier.t; k <= 1.0 + TestBezier.t; k += TestBezier.t) {
               r = 1.0 - k;
               x = Math.pow(r, 2.0) * TestBezier.keyPointP[0].getX() + 2.0 * k * r * TestBezier.keyPointP[1].getX() + Math.pow(k, 2.0) * TestBezier.keyPointP[2].getX();
               y = Math.pow(r, 2.0) * TestBezier.keyPointP[0].getY() + 2.0 * k * r * TestBezier.keyPointP[1].getY() + Math.pow(k, 2.0) * TestBezier.keyPointP[2].getY();
               g.drawLine((int)x, (int)y, (int)x, (int)y);
            }
         }

         if (TestBezier.keyPointNum == 4) {
            g.setColor(Color.RED);

            for(k = TestBezier.t; k <= 1.0 + TestBezier.t; k += TestBezier.t) {
               r = 1.0 - k;
               x = Math.pow(r, 3.0) * TestBezier.keyPointP[0].getX() + 3.0 * k * Math.pow(r, 2.0) * TestBezier.keyPointP[1].getX() + 3.0 * Math.pow(k, 2.0) * (1.0 - k) * TestBezier.keyPointP[2].getX() + Math.pow(k, 3.0) * TestBezier.keyPointP[3].getX();
               y = Math.pow(r, 3.0) * TestBezier.keyPointP[0].getY() + 3.0 * k * Math.pow(r, 2.0) * TestBezier.keyPointP[1].getY() + 3.0 * Math.pow(k, 2.0) * (1.0 - k) * TestBezier.keyPointP[2].getY() + Math.pow(k, 3.0) * TestBezier.keyPointP[3].getY();
               g.drawLine((int)x, (int)y, (int)x, (int)y);
            }
         }

      }

      class MouseMotion extends MouseMotionAdapter {
         public void mouseDragged(MouseEvent e) {
            if (BezierPanel.this.keyPointID != -1) {
               double x = (double)e.getX();
               double y = (double)e.getY();
               TestBezier.keyPointP[BezierPanel.this.keyPointID] = new Point2D.Double(x, y);
               TestBezier.keyPointE[BezierPanel.this.keyPointID] = new Ellipse2D.Double(x - 4.0, y - 4.0, 8.0, 8.0);
               BezierPanel.this.repaint();
            }

         }
      }

      class MouseAction extends MouseAdapter {
         public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
               if (TestBezier.keyPointNum < 4) {
                  double x = (double)e.getX();
                  double y = (double)e.getY();
                  TestBezier.keyPointP[TestBezier.keyPointNum] = new Point2D.Double(x, y);
                  TestBezier.keyPointE[TestBezier.keyPointNum] = new Ellipse2D.Double(x - 4.0, y - 4.0, 8.0, 8.0);
                  TestBezier.keyPointNum++;
                  BezierPanel.this.repaint();
               }
            } else if (e.getButton() == 3) {
               TestBezier.flagShow = false;
               BezierPanel.this.repaint();
            }

         }

         public void mousePressed(MouseEvent e) {
            for(int i = 0; i < TestBezier.keyPointNum; ++i) {
               if (TestBezier.keyPointE[i].contains(e.getPoint())) {
                  BezierPanel.this.keyPointID = i;
                  break;
               }

               BezierPanel.this.keyPointID = -1;
            }

         }
      }
   }
}
