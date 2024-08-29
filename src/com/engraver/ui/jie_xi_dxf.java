package com.engraver.ui;

import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kabeja.dxf.DXFArc;
import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFEllipse;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFLWPolyline;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.DXFMLine;
import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.dxf.helpers.DXFSplineConverter;
import org.kabeja.dxf.helpers.MLineConverter;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

public class jie_xi_dxf {
   static void polyline(DXFPolyline polyline) {
      Tu_yuan ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
      ty.lu_jing = new GeneralPath();
      GeneralPath lj = new GeneralPath();
      Iterator vertex = polyline.getVertexIterator();
      boolean yi = false;

      while(vertex.hasNext()) {
         DXFVertex vertex_ = (DXFVertex)vertex.next();
         new Point();
         Point d;
         if (!yi) {
            d = vertex_.getPoint();
            d.setX(d.getX() / Engraver.state.scaleFactor);
            d.setY(d.getY() / Engraver.state.scaleFactor);
            lj.moveTo(d.getX(), d.getY());
            yi = true;
         } else {
            d = vertex_.getPoint();
            d.setX(d.getX() / Engraver.state.scaleFactor);
            d.setY(d.getY() / Engraver.state.scaleFactor);
            lj.lineTo(d.getX(), d.getY());
         }
      }

      if (polyline.isClosed()) {
         lj.closePath();
      }

      ty.lu_jing = lj;
      ty.xuan_zhong = true;
      GraphicsPanel.ty_shuzu.add(ty);
   }

   static void lwpolyline(DXFLWPolyline polyline) {
      Tu_yuan ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
      ty.lu_jing = new GeneralPath();
      GeneralPath lj = new GeneralPath();
      System.out.println(polyline.getContstantWidth());
      System.out.println(polyline.getElevation());
      System.out.println("========================");
      Iterator vertex = polyline.getVertexIterator();
      boolean yi = false;

      while(vertex.hasNext()) {
         DXFVertex vertex_ = (DXFVertex)vertex.next();
         new Point();
         Point d;
         if (!yi) {
            d = vertex_.getPoint();
            d.setX(d.getX() / Engraver.state.scaleFactor);
            d.setY(d.getY() / Engraver.state.scaleFactor);
            lj.moveTo(d.getX(), d.getY());
            yi = true;
         } else {
            d = vertex_.getPoint();
            d.setX(d.getX() / Engraver.state.scaleFactor);
            d.setY(d.getY() / Engraver.state.scaleFactor);
            lj.lineTo(d.getX(), d.getY());
         }
      }

      if (polyline.isClosed()) {
         lj.closePath();
      }

      ty.lu_jing = lj;
      ty.xuan_zhong = true;
      GraphicsPanel.ty_shuzu.add(ty);
   }

   public static void jie_xi(File f) {
      try {
         Parser dxfParser = ParserBuilder.createDefaultParser();
         dxfParser.parse(new FileInputStream(f.getAbsolutePath()), "UTF-8");
         DXFDocument doc = dxfParser.getDocument();
         Iterator ceng_list = doc.getDXFLayerIterator();

         while(ceng_list.hasNext()) {
            DXFLayer ceng = (DXFLayer)ceng_list.next();
            Iterator shi_ti_list = ceng.getDXFEntityTypeIterator();

            while(shi_ti_list.hasNext()) {
               String shi_ti2 = (String)shi_ti_list.next();
               System.out.println(shi_ti2);
               List st = ceng.getDXFEntities(shi_ti2);

               for(int i = 0; i < st.size(); ++i) {
                  Tu_yuan ty;
                  ArrayList dian;
                  Point dian1;
                  if (((DXFEntity)st.get(i)).getType() == "CIRCLE") {
                     ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
                     ty.lu_jing = new GeneralPath();
                     DXFCircle cir = (DXFCircle)st.get(i);
                     dian = new ArrayList();

                     for(double j = 0.0; j < 360.0; j += 0.1) {
                        dian1 = cir.getPointAt(j);
                        dian1.setX(dian1.getX() / Engraver.state.scaleFactor);
                        dian1.setY(dian1.getY() / Engraver.state.scaleFactor);
                        dian.add(dian1);
                     }

                     ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
                     Iterator var32 = dian.iterator();

                     while(var32.hasNext()) {
                        dian1 = (Point)var32.next();
                        ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
                     }

                     ty.xuan_zhong = true;
                     GraphicsPanel.ty_shuzu.add(ty);
                  } else if (((DXFEntity)st.get(i)).getType() == "LINE") {
                     ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
                     ty.lu_jing = new GeneralPath();
                     DXFLine l = (DXFLine)st.get(i);
                     Point d = l.getStartPoint();
                     d.setX(d.getX() / Engraver.state.scaleFactor);
                     d.setY(d.getY() / Engraver.state.scaleFactor);
                     ty.lu_jing.moveTo(d.getX(), d.getY());
                     d = l.getEndPoint();
                     d.setX(d.getX() / Engraver.state.scaleFactor);
                     d.setY(d.getY() / Engraver.state.scaleFactor);
                     ty.lu_jing.lineTo(d.getX(), d.getY());
                     ty.xuan_zhong = true;
                     GraphicsPanel.ty_shuzu.add(ty);
                  } else if ("LWPOLYLINE".equals(((DXFEntity)st.get(i)).getType())) {
                     DXFLWPolyline poly = (DXFLWPolyline)st.get(i);
                     lwpolyline(poly);
                  } else if ("POLYLINE".equals(((DXFEntity)st.get(i)).getType())) {
                     DXFPolyline poly = (DXFPolyline)st.get(i);
                     polyline(poly);
                  } else {
                     Point d;
                     double j;
                     Iterator var33;
                     if (((DXFEntity)st.get(i)).getType() == "ELLIPSE") {
                        ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
                        ty.lu_jing = new GeneralPath();
                        DXFEllipse sp = (DXFEllipse)st.get(i);
                        dian = new ArrayList();
                        new Point();
                        if (sp.getStartParameter() < sp.getEndParameter()) {
                           for(j = sp.getStartParameter(); j < sp.getEndParameter(); j += 0.001) {
                              d = sp.getPointAt(j);
                              d.setX(d.getX() / Engraver.state.scaleFactor);
                              d.setY(d.getY() / Engraver.state.scaleFactor);
                              dian.add(d);
                           }
                        } else {
                           for(j = sp.getEndParameter(); j < sp.getStartParameter(); j += 0.1) {
                              d = sp.getPointAt(j);
                              d.setX(d.getX() / Engraver.state.scaleFactor);
                              d.setY(d.getY() / Engraver.state.scaleFactor);
                              dian.add(d);
                           }
                        }

                        ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
                        var33 = dian.iterator();

                        while(var33.hasNext()) {
                           dian1 = (Point)var33.next();
                           ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
                        }

                        ty.xuan_zhong = true;
                        GraphicsPanel.ty_shuzu.add(ty);
                     } else if (((DXFEntity)st.get(i)).getType() == "SPLINE") {
                        new GeneralPath();
                        DXFSpline sp = (DXFSpline)st.get(i);
                        polyline(DXFSplineConverter.toDXFPolyline(sp));
                     } else if (((DXFEntity)st.get(i)).getType() == "MLINE") {
                        new GeneralPath();
                        DXFMLine sp = (DXFMLine)st.get(i);
                        DXFPolyline[] po = MLineConverter.toDXFPolyline(sp);
                        DXFPolyline[] var29 = po;
                        int var34 = po.length;

                        for(int var30 = 0; var30 < var34; ++var30) {
                           DXFPolyline jk1 = var29[var30];
                           polyline(jk1);
                        }
                     } else if (((DXFEntity)st.get(i)).getType() == "ARC") {
                        ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
                        ty.lu_jing = new GeneralPath();
                        DXFArc sp = (DXFArc)st.get(i);
                        dian = new ArrayList();
                        new Point();
                        if (!sp.isCounterClockwise()) {
                           if (sp.getStartAngle() < sp.getEndAngle()) {
                              for(j = sp.getStartAngle(); j < sp.getEndAngle(); j += 0.1) {
                                 d = sp.getPointAt(j);
                                 d.setX(d.getX() / Engraver.state.scaleFactor);
                                 d.setY(d.getY() / Engraver.state.scaleFactor);
                                 dian.add(d);
                              }
                           } else {
                              for(j = sp.getStartAngle(); j < 360.0; j += 0.1) {
                                 d = sp.getPointAt(j);
                                 d.setX(d.getX() / Engraver.state.scaleFactor);
                                 d.setY(d.getY() / Engraver.state.scaleFactor);
                                 dian.add(d);
                              }

                              for(j = 0.0; j < sp.getEndAngle(); j += 0.1) {
                                 d = sp.getPointAt(j);
                                 d.setX(d.getX() / Engraver.state.scaleFactor);
                                 d.setY(d.getY() / Engraver.state.scaleFactor);
                                 dian.add(d);
                              }
                           }
                        } else if (sp.getStartAngle() > sp.getEndAngle()) {
                           for(j = sp.getStartAngle(); j > sp.getEndAngle(); j -= 0.1) {
                              d = sp.getPointAt(j);
                              d.setX(d.getX() / Engraver.state.scaleFactor);
                              d.setY(d.getY() / Engraver.state.scaleFactor);
                              dian.add(d);
                           }
                        } else {
                           for(j = sp.getStartAngle(); j > 0.0; j -= 0.1) {
                              d = sp.getPointAt(j);
                              d.setX(d.getX() / Engraver.state.scaleFactor);
                              d.setY(d.getY() / Engraver.state.scaleFactor);
                              dian.add(d);
                           }

                           for(j = 360.0; j > sp.getEndAngle(); j -= 0.1) {
                              d = sp.getPointAt(j);
                              d.setX(d.getX() / Engraver.state.scaleFactor);
                              d.setY(d.getY() / Engraver.state.scaleFactor);
                              dian.add(d);
                           }
                        }

                        ty.lu_jing.moveTo(((Point)dian.get(0)).getX(), ((Point)dian.get(0)).getY());
                        var33 = dian.iterator();

                        while(var33.hasNext()) {
                           dian1 = (Point)var33.next();
                           ty.lu_jing.lineTo(dian1.getX(), dian1.getY());
                        }

                        ty.xuan_zhong = true;
                        GraphicsPanel.ty_shuzu.add(ty);
                     }
                  }
               }
            }
         }
      } catch (FileNotFoundException var16) {
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var16);
      } catch (ParseException var17) {
         ParseException ex = var17;
         Logger.getLogger(jie_xi_dxf.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      Tu_yuan.jing_xiang_dxf();
   }
}
