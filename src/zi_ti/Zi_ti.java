package zi_ti;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Zi_ti extends JDialog {
   private final JPanel contentPanel = new JPanel();
   private JComboBox fontNameBox = null;
   private JComboBox fontStyleBox = null;
   private JComboBox fontSizeBox = null;
   private JTextArea txtrHereIs = null;
   private String fontName;
   private String fontStyle;
   private String fontSize;
   private int fontSty;
   private int fontSiz;

   public static void main(String[] args) {
      try {
         Zi_ti dialog = new Zi_ti();
         dialog.setDefaultCloseOperation(2);
         dialog.setVisible(true);
      } catch (Exception var2) {
         Exception e = var2;
         e.printStackTrace();
      }

   }

   public Zi_ti() {
      this.setTitle("你好我来了");
      this.setBounds(100, 100, 483, 234);
      this.getContentPane().setLayout(new BorderLayout());
      this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.getContentPane().add(this.contentPanel, "Center");
      this.contentPanel.setLayout((LayoutManager)null);
      JLabel label = new JLabel("字体(F):");
      label.setBounds(0, 10, 54, 15);
      this.contentPanel.add(label);
      label = new JLabel("字形(Y):");
      label.setBounds(182, 10, 54, 15);
      this.contentPanel.add(label);
      label = new JLabel("大小(S):");
      label.setBounds(315, 10, 54, 15);
      this.contentPanel.add(label);
      label = new JLabel("显示效果:");
      label.setBounds(126, 82, 64, 15);
      this.contentPanel.add(label);
      Panel panel = new Panel();
      panel.setBounds(196, 40, 228, 113);
      this.contentPanel.add(panel);
      panel.setLayout((LayoutManager)null);
      this.txtrHereIs = new JTextArea();
      this.txtrHereIs.setBounds(39, 38, 177, 44);
      this.txtrHereIs.setText("这里显示预览\r\nHere is the preview");
      panel.add(this.txtrHereIs);
      this.fontNameBox = new JComboBox();
      this.fontNameBox.setBounds(49, 7, 123, 21);
      this.contentPanel.add(this.fontNameBox);
      this.fontNameBox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent itemevent) {
            Zi_ti.this.fontName = (String)itemevent.getItem();
            System.out.println(Zi_ti.this.fontName);
            Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
            Zi_ti.this.txtrHereIs.setFont(f);
         }
      });
      this.fontStyleBox = new JComboBox();
      this.fontStyleBox.setBounds(232, 7, 73, 21);
      this.contentPanel.add(this.fontStyleBox);
      this.fontStyleBox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent itemevent) {
            Zi_ti.this.fontStyle = (String)itemevent.getItem();
            Zi_ti.this.fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
            Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
            Zi_ti.this.txtrHereIs.setFont(f);
         }
      });
      this.fontSizeBox = new JComboBox();
      this.fontSizeBox.setBounds(379, 7, 78, 21);
      this.contentPanel.add(this.fontSizeBox);
      this.fontSizeBox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent itemevent) {
            Zi_ti.this.fontSize = (String)itemevent.getItem();
            Zi_ti.this.fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
            Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
            Zi_ti.this.txtrHereIs.setFont(f);
         }
      });
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(2));
      this.getContentPane().add(buttonPane, "South");
      JButton cancelButton = new JButton("确定");
      cancelButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent actionevent) {
            int fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
            int fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
            JOptionPane.showMessageDialog(Zi_ti.this, "你选择的字体名称：" + Zi_ti.this.fontName + ",字体样式：" + Zi_ti.this.fontStyle + ",字体大小：" + fontSiz, "提示", -1);
         }
      });
      cancelButton.setActionCommand("OK");
      buttonPane.add(cancelButton);
      this.getRootPane().setDefaultButton(cancelButton);
      cancelButton = new JButton("取消");
      cancelButton.setActionCommand("Cancel");
      buttonPane.add(cancelButton);
      cancelButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent actionevent) {
            Zi_ti.this.dispose();
         }
      });
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] fontNames = ge.getAvailableFontFamilyNames();
      this.fontNameBox.setModel(new DefaultComboBoxModel(fontNames));
      String[] fontStyles = new String[]{"常规", "斜体", "粗体", "粗斜体"};
      this.fontStyleBox.setModel(new DefaultComboBoxModel(fontStyles));
      String[] fontSizes = new String[]{"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
      this.fontSizeBox.setModel(new DefaultComboBoxModel(fontSizes));
      System.out.println("finish.");
      this.fontSizeBox.setSelectedIndex(4);
      this.fontStyle = (String)this.fontStyleBox.getSelectedItem();
      this.fontSize = (String)this.fontSizeBox.getSelectedItem();
      this.fontSty = getFontStyleByCnName(this.fontStyle);
      this.fontSiz = Integer.parseInt(this.fontSize);
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
}
