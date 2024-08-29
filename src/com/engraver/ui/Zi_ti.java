package com.engraver.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Zi_ti extends JFrame {
   public static Font ziti = null;
   static int box1 = 0;
   static int box2 = 0;
   static int daxiao = 14;
   private JSlider da_xiao;
   private JButton jButton1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JScrollPane jScrollPane1;
   private JTextArea wen_zi;
   private JComboBox zi_ti_Box;
   private JComboBox zi_xing_Box;

   public Zi_ti() {
      this.initComponents();
   }

   private void initComponents() {
      this.jLabel1 = new JLabel();
      this.jLabel2 = new JLabel();
      this.jLabel3 = new JLabel();
      this.zi_ti_Box = new JComboBox();
      this.zi_xing_Box = new JComboBox();
      this.da_xiao = new JSlider();
      this.jScrollPane1 = new JScrollPane();
      this.wen_zi = new JTextArea();
      this.jButton1 = new JButton();
      this.setDefaultCloseOperation(3);
      this.setTitle("字体设置");
      this.setLocation(new Point(300, 300));
      this.addWindowListener(new WindowAdapter() {
         public void windowOpened(WindowEvent evt) {
            Zi_ti.this.formWindowOpened(evt);
         }
      });
      this.jLabel1.setText("字体：");
      this.jLabel2.setText("字型：");
      this.jLabel3.setText("大小：");
      this.zi_ti_Box.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
      this.zi_ti_Box.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            Zi_ti.this.zi_ti_BoxItemStateChanged(evt);
         }
      });
      this.zi_xing_Box.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
      this.zi_xing_Box.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            Zi_ti.this.zi_xing_BoxItemStateChanged(evt);
         }
      });
      this.da_xiao.setMaximum(200);
      this.da_xiao.setValue(14);
      this.da_xiao.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent evt) {
            Zi_ti.this.da_xiaoStateChanged(evt);
         }
      });
      this.wen_zi.setColumns(20);
      this.wen_zi.setRows(5);
      this.wen_zi.setText("ABCD");
      this.jScrollPane1.setViewportView(this.wen_zi);
      this.jButton1.setText("OK");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Zi_ti.this.jButton1ActionPerformed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jLabel1)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.zi_ti_Box, -2, 165, -2))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.zi_xing_Box, -2, 149, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.da_xiao, -2, -1, -2).addComponent(this.jLabel3)).addGap(0, 142, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton1, -2, 92, -2))).addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.zi_ti_Box, -2, -1, -2).addComponent(this.da_xiao, -2, 0, 32767))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel3)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.zi_xing_Box, -2, -1, -2))).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 387, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 34, -2).addContainerGap()));
      this.pack();
   }

   private void formWindowOpened(WindowEvent evt) {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] fontNames = ge.getAvailableFontFamilyNames();
      this.zi_ti_Box.setModel(new DefaultComboBoxModel(fontNames));
      String[] fontStyles = new String[]{"常规", "斜体", "粗体", "粗斜体"};
      this.zi_xing_Box.setModel(new DefaultComboBoxModel(fontStyles));
      if (ziti == null) {
         ziti = new Font((String)this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, 14);
         this.wen_zi.setFont(ziti);
      } else {
         this.zi_ti_Box.setSelectedIndex(box1);
         this.zi_xing_Box.setSelectedIndex(box2);
         this.da_xiao.setValue(daxiao);
      }

   }

   public static int getFontStyleByCnName(String fontStyle) {
      if (fontStyle.equals("常规")) {
         return 0;
      } else if (fontStyle.equals("斜体")) {
         return 2;
      } else if (fontStyle.equals("粗体")) {
         return 1;
      } else {
         return fontStyle.equals("粗斜体") ? 3 : -1;
      }
   }

   private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
      String fontName = (String)evt.getItem();
      System.out.println(fontName);
      ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
      this.wen_zi.setFont(ziti);
      box1 = this.zi_ti_Box.getSelectedIndex();
   }

   private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
      String fontStyle = (String)evt.getItem();
      System.out.println(fontStyle);
      ziti = new Font(ziti.getName(), getFontStyleByCnName(fontStyle), ziti.getSize());
      this.wen_zi.setFont(ziti);
      box2 = this.zi_xing_Box.getSelectedIndex();
   }

   private void da_xiaoStateChanged(ChangeEvent evt) {
      ziti = new Font(ziti.getName(), ziti.getStyle(), this.da_xiao.getValue());
      this.wen_zi.setFont(ziti);
      daxiao = this.da_xiao.getValue();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, false);
      this.setVisible(false);
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
         Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         UnsupportedLookAndFeelException ex = var8;
         Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new Zi_ti()).setVisible(true);
         }
      });
   }
}
