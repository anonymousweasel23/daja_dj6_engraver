package com.engraver.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Gu_jian extends JFrame {
   int jin_du = 0;
   private JButton jButton1;
   private JComboBox jComboBox1;
   private JLabel jLabel1;
   private JProgressBar jProgressBar1;

   public Gu_jian() {
      this.initComponents();
   }

   private void initComponents() {
      this.jButton1 = new JButton();
      this.jComboBox1 = new JComboBox();
      this.jLabel1 = new JLabel();
      this.jProgressBar1 = new JProgressBar();
      this.setDefaultCloseOperation(3);
      this.setTitle("UP");
      this.addWindowListener(new WindowAdapter() {
         public void windowOpened(WindowEvent evt) {
            Gu_jian.this.formWindowOpened(evt);
         }
      });
      this.jButton1.setText("更新");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Gu_jian.this.jButton1ActionPerformed(evt);
         }
      });
      this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"K6", "JL1", "JL1_S", "JL2", "JL3", "JL3_S", "JL4", "JL4_S", "L1", "L1_S", "L3", "Z2", "Z3", "Z4", ""}));
      this.jLabel1.setText("设备型号");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(66, 66, 66).addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED, 72, 32767).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jButton1, -1, -1, 32767).addComponent(this.jComboBox1, -2, 164, -2)).addGap(50, 50, 50)).addComponent(this.jProgressBar1, -1, -1, 32767));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(42, 42, 42).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jComboBox1, -2, -1, -2).addComponent(this.jLabel1)).addGap(33, 33, 33).addComponent(this.jButton1, -2, 37, -2).addPreferredGap(ComponentPlacement.RELATED, 11, 32767).addComponent(this.jProgressBar1, -2, -1, -2)));
      this.pack();
   }

   private void formWindowOpened(WindowEvent evt) {
      this.jLabel1.setText(ApplicationFrame.str_xing_hao);
      this.jButton1.setText(ApplicationFrame.str_kai_shi_geng_xin);
      this.setBounds(500, 300, this.getWidth(), this.getHeight());
      this.setIconImage((new ImageIcon(this.getClass().getResource("/tu/tu_biao.png"))).getImage());
   }

   public boolean downloadNet(String di_zhi) throws MalformedURLException {
      int bytesum = 0;
      int byteread = 0;
      URL url = new URL(di_zhi);

      try {
         URLConnection conn = url.openConnection();
         InputStream inStream = conn.getInputStream();
         String filepath = System.getProperty("user.dir");
         FileOutputStream fs = new FileOutputStream(filepath + "/bin.bin");
         byte[] buffer = new byte[1204];

         //int byteread;
         while((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread;
            System.out.println(bytesum);
            fs.write(buffer, 0, byteread);
         }

         inStream.close();
         fs.close();
         return true;
      } catch (FileNotFoundException var11) {
         FileNotFoundException e = var11;
         e.printStackTrace();
      } catch (IOException var12) {
         IOException e = var12;
         e.printStackTrace();
      }

      return false;
   }

   byte jiao_yan(byte[] bao) {
      int sum = 0;

      for(int i = 0; i < bao.length - 1; ++i) {
         int a = 255 & bao[i];
         sum += a;
      }

      if (sum > 255) {
         sum = ~sum;
         ++sum;
      }

      sum &= 255;
      return (byte)sum;
   }

   void fu_wei() {
      byte[] bao = new byte[]{-2, 0, 5, 0, 0};
      bao[4] = this.jiao_yan(bao);
      ApplicationFrame.com2.sendBytes(bao, 1);

      try {
         Thread.sleep(600L);
      } catch (InterruptedException var3) {
         InterruptedException ex = var3;
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public byte[] getContent(String filePath) throws IOException {
      File file = new File(filePath);
      long fileSize = file.length();
      if (fileSize > 2147483647L) {
         System.out.println("file too big...");
         return null;
      } else {
         FileInputStream fi = new FileInputStream(file);
         byte[] buffer = new byte[(int)fileSize];
         int offset = 0;

         int numRead;
         for(numRead = 0; offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0; offset += numRead) {
         }

         if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
         } else {
            fi.close();
            return buffer;
         }
      }
   }

   int jiao_yan(List m) {
      int jiao = 0;

      for(int i = 0; i < m.size(); ++i) {
         jiao += (Byte)m.get(i);
      }

      if (jiao > 255) {
         jiao = ~jiao;
         ++jiao;
      }

      jiao &= 255;
      return jiao;
   }

   private static byte[] listTobyte1(List list) {
      if (list != null && list.size() >= 0) {
         byte[] bytes = new byte[list.size()];
         int i = 0;

         for(Iterator iterator = list.iterator(); iterator.hasNext(); ++i) {
            bytes[i] = (Byte)iterator.next();
         }

         return bytes;
      } else {
         return null;
      }
   }

   void sheng() {
      byte[] byData = null;
      ApplicationFrame.com2.sendBytes(new byte[]{2, 0, 5, 0, 115}, 1);

      try {
         Thread.sleep(6000L);
      } catch (InterruptedException var9) {
         InterruptedException ex = var9;
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      String filepath = System.getProperty("user.dir");

      try {
         byData = this.getContent(filepath + "/bin.bin");
      } catch (IOException var8) {
         IOException ex = var8;
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      if (!byData.equals((Object)null)) {
         List bao = new ArrayList();
         int l = 0;
         //int l;
         if (byData.length % 1024 > 0) {
            l = byData.length / 1024 + 1;
         } else {
            l = byData.length / 1024;
         }

         for(int j = 0; j < l; ++j) {
            for(int i = 0; i < 1024; ++i) {
               if (j * 1024 + i < byData.length) {
                  bao.add(byData[j * 1024 + i]);
               } else {
                  bao.add(-1);
               }
            }

            bao.add(0, (byte)4);
            bao.add(0, (byte)4);
            bao.add(0, (byte)3);
            bao.add((byte)this.jiao_yan((List)bao));

            do {
               try {
                  Thread.sleep(10L);
               } catch (InterruptedException var7) {
                  Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, var7);
               }
            } while(!ApplicationFrame.com2.sendBytes(listTobyte1(bao), 1));

            bao.clear();
            Dimension d = this.jProgressBar1.getSize();
            this.jin_du = (int)((double)j / (double)l * 100.0);
            SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                  Gu_jian.this.jProgressBar1.setValue(Gu_jian.this.jin_du);
               }
            });
         }

         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               Gu_jian.this.jProgressBar1.setValue(0);
            }
         });
      }
   }

   void geng_xin(final String di_zhi) {
      Runnable runnable2 = new Runnable() {
         public void run() {
            try {
               if (Gu_jian.this.downloadNet(di_zhi)) {
                  Gu_jian.this.fu_wei();
                  Gu_jian.this.sheng();
                  ApplicationFrame.com2.sendBytes(new byte[]{4, 0, 4, 0}, 1);
                  Gu_jian.this.jButton1.setEnabled(true);
                  Gu_jian.this.jButton1.setText(ApplicationFrame.bundle.getString("str_kai_shi_geng_xin"));
               } else {
                  JOptionPane.showMessageDialog((Component)null, ApplicationFrame.str_xia_zai_shi_bai);
                  Gu_jian.this.jButton1.setEnabled(true);
                  Gu_jian.this.jButton1.setText(ApplicationFrame.bundle.getString("str_kai_shi_geng_xin"));
               }
            } catch (MalformedURLException var2) {
               MalformedURLException ex = var2;
               Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      this.jButton1.setEnabled(false);
      this.jButton1.setText(ApplicationFrame.bundle.getString("str_qing_shao_hou"));
      switch (this.jComboBox1.getSelectedIndex()) {
         case 0:
            this.geng_xin("http://jiakuo25.0594.bftii.com/K6.bin");
            break;
         case 1:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL1.bin");
            break;
         case 2:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL1_S.bin");
            break;
         case 3:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL2.bin");
            break;
         case 4:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL3.bin");
            break;
         case 5:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL3_S.bin");
            break;
         case 6:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL4.bin");
            break;
         case 7:
            this.geng_xin("http://jiakuo25.0594.bftii.com/JL4_S.bin");
            break;
         case 8:
            this.geng_xin("http://jiakuo25.0594.bftii.com/L1.bin");
            break;
         case 9:
            this.geng_xin("http://jiakuo25.0594.bftii.com/L1_S.bin");
            break;
         case 10:
            this.geng_xin("http://jiakuo25.0594.bftii.com/L3.bin");
            break;
         case 11:
            this.geng_xin("http://jiakuo25.0594.bftii.com/Z2.bin");
            break;
         case 12:
            this.geng_xin("http://jiakuo25.0594.bftii.com/Z3.bin");
            break;
         case 13:
            this.geng_xin("http://jiakuo25.0594.bftii.com/Z4.bin");
      }

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
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         UnsupportedLookAndFeelException ex = var8;
         Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new Gu_jian()).setVisible(true);
         }
      });
   }
}
