package com.engraver.ui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEntryDialog extends JFrame {
   public static Font ziti = null;
   static int box1 = 0;
   static int box2 = 0;
   static int daxiao = 60;
   GraphicsPanel fu;
   private JSlider da_xiao;
   private JButton jButton1;
   private JCheckBox jCheckBox1;
   private JCheckBox jCheckBox2;
   private JCheckBox jCheckBox3;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JLabel jLabel4;
   private JLabel jLabel5;
   private JLabel jLabel6;
   private JLabel jLabel7;
   private JPanel jPanel1;
   private JScrollPane jScrollPane1;
   private JTextField jTextField1;
   private JTextField jTextField2;
   private JTextField jTextField3;
   private JTextField jTextField4;
   private JTextArea wen_zi;
   private JComboBox zi_ti_Box;
   private JComboBox zi_xing_Box;

   public TextEntryDialog(GraphicsPanel parent, boolean modal) {
      this.setTitle(ApplicationFrame.bundle.getString("str_wen_zi"));
      this.setLocation(new Point(200, 100));
      this.fu = parent;
      this.initComponents();
   }

   private void initComponents() {
      this.jLabel2 = new JLabel();
      this.jLabel3 = new JLabel();
      this.zi_ti_Box = new JComboBox();
      this.zi_xing_Box = new JComboBox();
      this.da_xiao = new JSlider();
      this.jScrollPane1 = new JScrollPane();
      this.wen_zi = new JTextArea();
      this.jButton1 = new JButton();
      this.jLabel1 = new JLabel();
      this.jCheckBox1 = new JCheckBox();
      this.jCheckBox2 = new JCheckBox();
      this.jCheckBox3 = new JCheckBox();
      this.jPanel1 = new JPanel();
      this.jLabel4 = new JLabel();
      this.jTextField1 = new JTextField();
      this.jLabel5 = new JLabel();
      this.jTextField2 = new JTextField();
      this.jLabel6 = new JLabel();
      this.jTextField3 = new JTextField();
      this.jLabel7 = new JLabel();
      this.jTextField4 = new JTextField();
      this.setDefaultCloseOperation(2);
      this.addWindowListener(new WindowAdapter() {
         public void windowOpened(WindowEvent evt) {
            TextEntryDialog.this.formWindowOpened(evt);
         }
      });
      this.jLabel2.setText("字型：");
      this.jLabel3.setText("尺寸:10");
      this.zi_ti_Box.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
      this.zi_ti_Box.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            TextEntryDialog.this.zi_ti_BoxItemStateChanged(evt);
         }
      });
      this.zi_xing_Box.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
      this.zi_xing_Box.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent evt) {
            TextEntryDialog.this.zi_xing_BoxItemStateChanged(evt);
         }
      });
      this.da_xiao.setMaximum(300);
      this.da_xiao.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent evt) {
            TextEntryDialog.this.da_xiaoStateChanged(evt);
         }
      });
      this.wen_zi.setColumns(20);
      this.wen_zi.setRows(5);
      this.wen_zi.setText("ABCD");
      this.jScrollPane1.setViewportView(this.wen_zi);
      this.jButton1.setText("OK");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jButton1ActionPerformed(evt);
         }
      });
      this.jLabel1.setText("字体：");
      this.jCheckBox1.setText("竖向");
      this.jCheckBox2.setText("矢量图5");
      this.jCheckBox3.setText("序列号");
      this.jCheckBox3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jCheckBox3ActionPerformed(evt);
         }
      });
      this.jLabel4.setText("前缀：");
      this.jTextField1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jTextField1ActionPerformed(evt);
         }
      });
      this.jLabel5.setText("起始：");
      this.jTextField2.setText("0");
      this.jTextField2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jTextField2ActionPerformed(evt);
         }
      });
      this.jLabel6.setText("结束：");
      this.jTextField3.setText("1000");
      this.jTextField3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jTextField3ActionPerformed(evt);
         }
      });
      this.jLabel7.setText("后缀：");
      this.jTextField4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            TextEntryDialog.this.jTextField4ActionPerformed(evt);
         }
      });
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.jLabel7)).addGap(86, 86, 86).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTextField1, -2, 126, -2).addComponent(this.jTextField2, -2, 126, -2).addComponent(this.jTextField3, -2, 126, -2).addComponent(this.jTextField4, -2, 126, -2)).addContainerGap()));
      jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jLabel4)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jLabel5)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jLabel6)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextField4, -2, -1, -2).addComponent(this.jLabel7)).addContainerGap(-1, 32767)));
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton1, -2, 92, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jLabel1)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.zi_ti_Box, -2, 165, -2))).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.zi_xing_Box, -2, 149, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.da_xiao, -2, -1, -2).addComponent(this.jLabel3)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jCheckBox2).addComponent(this.jCheckBox1)).addGap(0, 198, 32767))).addContainerGap()).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1).addGroup(layout.createSequentialGroup().addComponent(this.jCheckBox3).addGap(10, 10, 10).addComponent(this.jPanel1, -2, -1, -2).addGap(0, 0, 32767)))));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.zi_ti_Box, -2, -1, -2).addComponent(this.da_xiao, -2, 0, 32767))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jCheckBox2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.zi_xing_Box, -2, -1, -2))).addComponent(this.jCheckBox1)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jCheckBox3).addComponent(this.jPanel1, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 416, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 34, -2).addContainerGap()));
      this.pack();
   }

   private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
      String fontName = (String)evt.getItem();
      System.out.println(fontName);
      ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
      this.wen_zi.setFont(ziti);
      box1 = this.zi_ti_Box.getSelectedIndex();
   }

   public static int getFontStyleByCnName(String fontStyle) {
      if (fontStyle.equals(ApplicationFrame.str_chang_gui)) {
         return 0;
      } else if (fontStyle.equals(ApplicationFrame.str_xie_ti)) {
         return 2;
      } else if (fontStyle.equals(ApplicationFrame.str_cu_ti)) {
         return 1;
      } else {
         return fontStyle.equals(ApplicationFrame.str_cu_xie) ? 3 : -1;
      }
   }

   private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
      String fontStyle = (String)evt.getItem();
      System.out.println(fontStyle);
      ziti = new Font(ziti.getName(), getFontStyleByCnName(fontStyle), ziti.getSize());
      this.wen_zi.setFont(ziti);
      box2 = this.zi_xing_Box.getSelectedIndex();
   }

   private void da_xiaoStateChanged(ChangeEvent evt) {
      this.jLabel3.setText(ApplicationFrame.bundle.getString("str_chi_cun") + this.da_xiao.getValue());
      daxiao = 100 + this.da_xiao.getValue() * 5;
      ziti = new Font(ziti.getName(), ziti.getStyle(), daxiao);
      this.wen_zi.setFont(ziti);
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      if (this.jCheckBox3.isSelected()) {
         liu_shui_hao lsm = new liu_shui_hao(Integer.parseInt(this.jTextField2.getText()), Integer.parseInt(this.jTextField3.getText()), Integer.parseInt(this.jTextField2.getText()), this.jTextField1.getText(), this.jTextField4.getText(), ziti, this.jCheckBox1.isSelected(), this.jCheckBox2.isSelected());
         GraphicsPanel.ty_shuzu.add(lsm.sheng_cheng());
      } else if (this.jCheckBox1.isSelected()) {
         GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi_shu(this.wen_zi.getText(), ziti, 0, this.jCheckBox2.isSelected()));
      } else {
         GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, this.jCheckBox2.isSelected()));
      }

      for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
      }

      ((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
      Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
      Che_xiao.tian_jia();
      this.fu.repaint();
      this.setVisible(false);
   }

   private void formWindowOpened(WindowEvent evt) {
      this.jPanel1.setVisible(false);
      this.wen_zi.setBounds(this.wen_zi.getBounds().x, 75, this.wen_zi.getBounds().width, 49);
      this.jLabel1.setText(ApplicationFrame.str_zi_ti);
      this.jLabel2.setText(ApplicationFrame.str_zi_xing);
      this.jLabel3.setText(ApplicationFrame.str_chi_cun + "60");
      this.jCheckBox1.setText(ApplicationFrame.str_shu);
      this.jCheckBox2.setText(ApplicationFrame.str_shi_liang);
      this.jCheckBox3.setText(ApplicationFrame.str_liu_shui_hao);
      this.jCheckBox3.setVisible(true);
      this.jLabel4.setText(ApplicationFrame.str_qian_zhui);
      this.jLabel5.setText(ApplicationFrame.str_qi_shi);
      this.jLabel6.setText(ApplicationFrame.str_jie_shu);
      this.jLabel7.setText(ApplicationFrame.str_hou_zhui);
      if (ApplicationFrame.tubiao == 1) {
         this.setIconImage((new ImageIcon(this.getClass().getResource("/tu/Tu_Wainlux.png"))).getImage());
      } else {
         this.setIconImage((new ImageIcon(this.getClass().getResource("/tu/Tu_HANLIN.png"))).getImage());
      }

      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] fontNames = ge.getAvailableFontFamilyNames();
      String[] fontNames2 = new String[fontNames.length];

      for(int i = 0; i < fontNames.length; ++i) {
         fontNames2[i] = fontNames[fontNames.length - i - 1];
      }

      this.zi_ti_Box.setModel(new DefaultComboBoxModel(fontNames2));
      String[] fontStyles = new String[]{ApplicationFrame.str_chang_gui, ApplicationFrame.str_xie_ti, ApplicationFrame.str_cu_ti, ApplicationFrame.str_cu_xie};
      this.zi_xing_Box.setModel(new DefaultComboBoxModel(fontStyles));
      if (ziti == null) {
         daxiao = 400;
         ziti = new Font((String)this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, daxiao);
         this.wen_zi.setFont(ziti);
         box1 = this.zi_ti_Box.getSelectedIndex();
         box2 = this.zi_xing_Box.getSelectedIndex();
      } else {
         this.zi_ti_Box.setSelectedIndex(box1);
         this.zi_xing_Box.setSelectedIndex(box2);
         this.da_xiao.setValue((daxiao - 100) / 5);
         ziti = new Font(ziti.getName(), getFontStyleByCnName((String)this.zi_xing_Box.getItemAt(box2)), ziti.getSize());
         this.wen_zi.setFont(ziti);
      }

   }

   private void jCheckBox3ActionPerformed(ActionEvent evt) {
      if (this.jCheckBox3.isSelected()) {
         this.wen_zi.setBounds(this.wen_zi.getBounds().x, 155, this.wen_zi.getBounds().width, 45);
      } else {
         this.wen_zi.setBounds(this.wen_zi.getBounds().x, 75, this.wen_zi.getBounds().width, 45);
      }

      this.jPanel1.setVisible(this.jCheckBox3.isSelected());
      this.liu_shui_hao();
   }

   void liu_shui_hao() {
      this.wen_zi.setText(this.jTextField1.getText() + this.jTextField2.getText() + this.jTextField4.getText());
   }

   private void jTextField1ActionPerformed(ActionEvent evt) {
      this.liu_shui_hao();
   }

   private void jTextField2ActionPerformed(ActionEvent evt) {
      this.liu_shui_hao();
   }

   private void jTextField3ActionPerformed(ActionEvent evt) {
      this.liu_shui_hao();
   }

   private void jTextField4ActionPerformed(ActionEvent evt) {
      this.liu_shui_hao();
   }
}
