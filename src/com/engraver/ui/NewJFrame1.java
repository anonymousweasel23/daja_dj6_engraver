package com.engraver.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NewJFrame1 extends JFrame {
   private Box.Filler filler1;
   private JButton jButton1;
   private JButton jButton2;

   public NewJFrame1() {
      this.initComponents();
   }

   private void initComponents() {
      this.filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 32767));
      this.jButton1 = new JButton();
      this.jButton2 = new JButton();
      this.setDefaultCloseOperation(3);
      this.filler1.setBackground(new Color(255, 0, 0));
      this.jButton1.setText("jButton1");
      this.jButton2.setText("jButton1");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(41, 41, 41).addComponent(this.jButton2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.filler1, -1, 389, 32767).addGap(29, 29, 29).addComponent(this.jButton1).addGap(136, 136, 136)));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(119, 119, 119).addComponent(this.filler1, -2, 231, -2)).addGroup(layout.createSequentialGroup().addGap(246, 246, 246).addComponent(this.jButton1)).addGroup(layout.createSequentialGroup().addGap(241, 241, 241).addComponent(this.jButton2))).addContainerGap(194, 32767)));
      this.pack();
   }

   public static void main(String[] args) {
      try {
         UIManager.LookAndFeelInfo[] var12 = UIManager.getInstalledLookAndFeels();
         int var2 = var12.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            UIManager.LookAndFeelInfo info = var12[var3];
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         UnsupportedLookAndFeelException ex = var8;
         Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new NewJFrame1()).setVisible(true);
         }
      });
   }
}
