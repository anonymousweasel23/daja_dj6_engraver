package com.engraver.ui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class she_zhi extends JFrame {
   private JButton jButton1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JSlider jSlider1;
   private JSlider jSlider2;

   public she_zhi() {
      this.initComponents();
   }

   private void initComponents() {
      this.jLabel1 = new JLabel();
      this.jLabel2 = new JLabel();
      this.jSlider1 = new JSlider();
      this.jSlider2 = new JSlider();
      this.jButton1 = new JButton();
      this.setDefaultCloseOperation(3);
      this.jLabel1.setText("弱光功率：");
      this.jLabel2.setText("雕刻次数：");
      this.jLabel2.setToolTipText("");
      this.jButton1.setText("确定");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jButton1, -2, 98, -2).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSlider2, -2, -1, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSlider1, -2, -1, -2)))).addContainerGap(30, 32767)));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(32, 32, 32).addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.jSlider1, -2, -1, -2).addComponent(this.jLabel1)).addGap(39, 39, 39).addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.jSlider2, -2, -1, -2).addComponent(this.jLabel2)).addGap(18, 18, 18).addComponent(this.jButton1, -2, 38, -2).addContainerGap(27, 32767)));
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
         Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         UnsupportedLookAndFeelException ex = var8;
         Logger.getLogger(she_zhi.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new she_zhi()).setVisible(true);
         }
      });
   }
}
