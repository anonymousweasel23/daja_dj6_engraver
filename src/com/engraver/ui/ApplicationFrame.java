package com.engraver.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.kabeja.dxf.DXFDocument;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.pnuema.java.barcode.Barcode;

import io.nayuki.qrcodegen.QrCode;
import net.sf.image4j.codec.bmp.BMPEncoder;

public class ApplicationFrame extends JFrame implements KeyListener {
	JMenuBar jmb;
	JPopupMenu popMenu = new JPopupMenu();
	JMenu menu1;
	JMenuItem item1;
	public byte[] banben = new byte[]{0, 0, 0, 0};
	public static String ce_shi = "";
	public static String ban_ben = "v2.2.0";
	boolean an_xia = false;
	int anxia_x = 0;
	int anxia_y = 0;
	int anxia_x_1 = 0;
	int anxia_y_1 = 0;
	int an = 0;
	int an_niu = 0;
	public static Com com2 = null;
	boolean com_dakai = false;
	private Object suo_fhm = new Object();
	private boolean kuang = false;
	boolean fan_hui_ma = false;
	boolean shi_zi = false;
	ApplicationFrame win = null;
	public static int chaoshi = 0;
	public static boolean kai_shi2 = false;
	public static ResourceBundle bundle = null;
	boolean fu_zhi = false;
	List ty_fu_zhi = new ArrayList();
	boolean tuo_dong = false;
	boolean mo_ni = false;
	boolean jing_du = false;
	int ji_xing = 0;
	int dang_qian_kuan = 0;
	int dang_qian_gao = 0;
	int shi_jian = 0;
	boolean jdt_xian_shi = false;
	public static int tubiao = 1;
	boolean jin_yong_suo_fang = false;
	String dang_qian_mu_lu = "";
	String str_da_kai = "";
	String str_wen_zi = "";
	String str_yuan = "";
	String str_fang = "";
	String str_xin = "";
	String str_xing = "";
	String str_bao_cun = "";
	String str_shi_zi = "";
	String str_yu_lan = "";
	String str_kai_shi = "";
	String str_ting_zhi = "";
	String str_lian_jie = "";
	String str_ruo_guang = "";
	String str_gong_lv = "";
	String str_shen_du = "";
	String str_gong_lv_sl = "";
	String str_shen_du_sl = "";
	String str_ci_shu = "";
	String str_jing_du = "";
	String str_gao = "";
	String str_zhong = "";
	String str_di = "";
	public static String str_zi_ti = "";
	public static String str_zi_xing = "";
	public static String str_chi_cun = "";
	public static String str_chang_gui = "";
	public static String str_xie_ti = "";
	public static String str_cu_ti = "";
	public static String str_cu_xie = "";
	public static String str_shu = "";
	public static String str_shi_liang = "";
	public static String str_geng_xin = "";
	public static String str_shi_zhi = "";
	public static String str_gu_jian = "";
	public static String str_xing_hao = "";
	public static String str_kai_shi_geng_xin = "";
	public static String str_xia_zai_shi_bai = "";
	public static String str_liu_shui_hao = "";
	public static String str_qi_shi = "";
	public static String str_jie_shu = "";
	public static String str_qian_zhui = "";
	public static String str_hou_zhui = "";
	List bao = new ArrayList();
	Boolean fa_wan = false;
	private JLabel bq_bao_cun;
	private JLabel bq_bao_cun1;
	private JLabel bq_da_kai;
	private JLabel bq_er_wei_ma;
	private JLabel bq_lian_jie;
	private JLabel bq_lian_jie1;
	private JLabel bq_tiao_ma;
	private JLabel bq_tu_xing;
	private JLabel bq_wen_zi;
	private JToggleButton but_hei_bai;
	private JToggleButton but_hui_du;
	private JToggleButton but_lun_kuo;
	private JToggleButton but_su_miao;
	private GraphicsPanel hua_ban1;
	private JButton jButton1;
	private JButton jButton12;
	private JButton jButton13;
	private JButton jButton14;
	private JButton jButton15;
	private JButton jButton16;
	private JButton jButton17;
	private JButton jButton18;
	private JButton jButton19;
	private JButton jButton2;
	private JButton jButton20;
	private JButton jButton21;
	private JButton jButton22;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton buttonOpenTextDialog;
	private JButton jButton8;
	private JCheckBox jCheckBox1;
	private JCheckBox jCheckBox2;
	private JComboBox jComboBox1;
	private JDialog jDialog1;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel16;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JSlider jSlider1;
	private JSlider jSlider2;
	private JSlider jSlider6;
	private JSlider jSlider7;
	private JTextField textFieldX;
	private JTextField jTextFieldY;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JProgressBar jdt;

	private JDialog serialSelectorDialog;

	//Wtf make it static
	public static PropertiesHelper properties = new PropertiesHelper();

	public ApplicationFrame() {
		this.initComponents();
	}

	private void initComponents() {

		//serialSelectorDialog = new JDialog(this,true);

		this.jDialog1 = new JDialog();
		this.jButton2 = new JButton();
		this.jButton1 = new JButton();
		this.buttonOpenTextDialog = new JButton();
		this.hua_ban1 = new GraphicsPanel();
		this.jPanel1 = new JPanel();
		this.jLabel1 = new JLabel();
		this.textFieldX = new JTextField();
		this.jLabel2 = new JLabel();
		this.jTextFieldY = new JTextField();
		this.jLabel3 = new JLabel();
		this.jLabel4 = new JLabel();
		this.jButton18 = new JButton();
		this.jPanel2 = new JPanel();
		this.jSlider1 = new JSlider();
		this.jSlider2 = new JSlider();
		this.jLabel5 = new JLabel();
		this.jLabel7 = new JLabel();
		this.jComboBox1 = new JComboBox();
		this.jLabel14 = new JLabel();
		this.jSlider6 = new JSlider();
		this.jLabel13 = new JLabel();
		this.jLabel16 = new JLabel();
		this.jSlider7 = new JSlider();
		this.but_hei_bai = new JToggleButton();
		this.but_hui_du = new JToggleButton();
		this.but_lun_kuo = new JToggleButton();
		this.but_su_miao = new JToggleButton();
		this.jButton5 = new JButton();
		this.jButton6 = new JButton();
		this.jButton8 = new JButton();
		this.jLabel10 = new JLabel();
		this.jButton14 = new JButton();
		this.jButton21 = new JButton();
		this.jButton15 = new JButton();
		this.jButton17 = new JButton();
		this.jCheckBox2 = new JCheckBox();
		this.jButton4 = new JButton();
		this.jLabel11 = new JLabel();
		this.jCheckBox1 = new JCheckBox();
		this.jTextField3 = new JTextField();
		this.jTextField4 = new JTextField();
		this.jLabel8 = new JLabel();
		this.jLabel9 = new JLabel();
		this.jdt = new JProgressBar();
		this.jButton12 = new JButton();
		this.jLabel6 = new JLabel();
		this.bq_wen_zi = new JLabel();
		this.bq_bao_cun = new JLabel();
		this.bq_lian_jie = new JLabel();
		this.bq_da_kai = new JLabel();
		this.jButton13 = new JButton();
		this.bq_er_wei_ma = new JLabel();
		this.jButton16 = new JButton();
		this.bq_tiao_ma = new JLabel();
		this.jButton20 = new JButton();
		this.bq_tu_xing = new JLabel();
		this.jButton22 = new JButton();
		this.bq_bao_cun1 = new JLabel();
		this.jButton19 = new JButton();
		this.bq_lian_jie1 = new JLabel();
		GroupLayout jDialog1Layout = new GroupLayout(this.jDialog1.getContentPane());
		this.jDialog1.getContentPane().setLayout(jDialog1Layout);
		jDialog1Layout.setHorizontalGroup(jDialog1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 400, 32767));
		jDialog1Layout.setVerticalGroup(jDialog1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 300, 32767));
		this.jButton2.setText("jButton2");
		this.setDefaultCloseOperation(3);
		this.setTitle("激光雕刻机");
		this.setBackground(new Color(204, 204, 204));
		this.setLocation(new Point(400, 200));
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				ApplicationFrame.this.formComponentResized(evt);
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				ApplicationFrame.this.formWindowOpened(evt);
			}
		});
		this.jButton1.setIcon(new ImageIcon(this.getClass().getResource("/tu/tu_pian.png")));
		this.jButton1.setToolTipText("打开图片");
		this.jButton1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jButton1MouseClicked(evt);
			}
		});
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton1ActionPerformed(evt);
			}
		});
		this.buttonOpenTextDialog.setIcon(new ImageIcon(this.getClass().getResource("/tu/wen_zi.png")));
		this.buttonOpenTextDialog.setToolTipText("输入文字");
		this.buttonOpenTextDialog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jButton7MouseClicked(evt);
			}
		});
		this.buttonOpenTextDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton7ActionPerformed(evt);
			}
		});
		this.hua_ban1.setBackground(new Color(255, 255, 255));
		this.hua_ban1.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255)));
		this.hua_ban1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				ApplicationFrame.this.hua_ban1MouseDragged(evt);
			}
		});
		this.hua_ban1.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent evt) {
				ApplicationFrame.this.hua_ban1MouseWheelMoved(evt);
			}
		});
		this.hua_ban1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				ApplicationFrame.this.hua_ban1MousePressed(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				ApplicationFrame.this.hua_ban1MouseReleased(evt);
			}
		});
		this.hua_ban1.setLayout(new AbsoluteLayout());
		this.jPanel1.setBackground(new Color(255, 255, 255));
		this.jPanel1.setPreferredSize(new Dimension(3, 3));
		this.jPanel1.setLayout(new AbsoluteLayout());
		//this.jLabel1.setFont(new Font("宋体", 0, 14));
		this.jLabel1.setText(" X:");
		this.jPanel1.add(this.jLabel1, new AbsoluteConstraints(10, 10, 24, 23));
		this.textFieldX.setText("0");
		this.textFieldX.setMinimumSize(new Dimension(6, 20));
		this.textFieldX.setPreferredSize(new Dimension(12, 20));
		this.jPanel1.add(this.textFieldX, new AbsoluteConstraints(38, 10, 30, 25));
		//this.jLabel2.setFont(new Font("宋体", 0, 14));
		this.jLabel2.setText("Y:");
		this.jPanel1.add(this.jLabel2, new AbsoluteConstraints(75, 10, -1, 23));
		this.jTextFieldY.setText("0");
		this.jTextFieldY.setMinimumSize(new Dimension(6, 20));
		this.jTextFieldY.setPreferredSize(new Dimension(12, 20));
		this.jPanel1.add(this.jTextFieldY, new AbsoluteConstraints(93, 10, 30, 25));
		this.jLabel3.setFont(new Font("宋体", 0, 14));
		this.jLabel3.setText("W:");
		this.jPanel1.add(this.jLabel3, new AbsoluteConstraints(130, 10, -1, 23));
		this.jLabel4.setFont(new Font("宋体", 0, 14));
		this.jLabel4.setText("H:");
		this.jPanel1.add(this.jLabel4, new AbsoluteConstraints(185, 10, -1, 20));
		this.hua_ban1.add(this.jPanel1, new AbsoluteConstraints(30, 20, 10, 50));
		this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb_1.png")));
		this.jButton18.setToolTipText("连接设备");
		this.jButton18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton18ActionPerformed(evt);
			}
		});
		this.jPanel2.setBackground(new Color(204, 204, 204));
		this.jPanel2.setLayout(new AbsoluteLayout());
		this.jSlider1.setValue(100);
		this.jSlider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				ApplicationFrame.this.jSlider1StateChanged(evt);
			}
		});
		this.jSlider1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				ApplicationFrame.this.jSlider1MouseReleased(evt);
			}
		});
		this.jPanel2.add(this.jSlider1, new AbsoluteConstraints(10, 30, 210, -1));
		this.jSlider2.setValue(90);
		this.jSlider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				ApplicationFrame.this.jSlider2StateChanged(evt);
			}
		});
		this.jSlider2.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				ApplicationFrame.this.jSlider2MouseReleased(evt);
			}
		});
		this.jPanel2.add(this.jSlider2, new AbsoluteConstraints(10, 80, 210, -1));
		this.jLabel5.setText("功率：100%");
		this.jPanel2.add(this.jLabel5, new AbsoluteConstraints(10, 10, 200, -1));
		this.jLabel7.setText("次数：");
		this.jPanel2.add(this.jLabel7, new AbsoluteConstraints(10, 232, 100, -1));
		this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "127"}));
		this.jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jComboBox1ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jComboBox1, new AbsoluteConstraints(110, 230, 110, -1));
		this.jLabel14.setText("对比度：50%");
		this.jPanel2.add(this.jLabel14, new AbsoluteConstraints(12, 110, 200, -1));
		this.jSlider6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				ApplicationFrame.this.jSlider6StateChanged(evt);
			}
		});
		this.jPanel2.add(this.jSlider6, new AbsoluteConstraints(10, 130, 210, -1));
		this.jLabel13.setText("速度：90%");
		this.jPanel2.add(this.jLabel13, new AbsoluteConstraints(12, 60, 190, -1));
		this.jLabel13.getAccessibleContext().setAccessibleName("速度：%10");
		this.jLabel16.setText("填充密度：5");
		this.jPanel2.add(this.jLabel16, new AbsoluteConstraints(12, 170, 130, -1));
		this.jSlider7.setMaximum(10);
		this.jSlider7.setMinimum(1);
		this.jSlider7.setToolTipText("");
		this.jSlider7.setValue(5);
		this.jSlider7.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				ApplicationFrame.this.jSlider7StateChanged(evt);
			}
		});
		this.jPanel2.add(this.jSlider7, new AbsoluteConstraints(10, 190, 210, -1));
		this.but_hei_bai.setText("黑白");
		this.but_hei_bai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.but_hei_baiActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.but_hei_bai, new AbsoluteConstraints(10, 370, 100, -1));
		this.but_hui_du.setText("灰度");
		this.but_hui_du.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.but_hui_duActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.but_hui_du, new AbsoluteConstraints(120, 370, 100, -1));
		this.but_lun_kuo.setText("轮廓");
		this.but_lun_kuo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.but_lun_kuoActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.but_lun_kuo, new AbsoluteConstraints(10, 400, 100, -1));
		this.but_su_miao.setText("素描");
		this.but_su_miao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.but_su_miaoActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.but_su_miao, new AbsoluteConstraints(120, 400, 100, -1));
		this.jButton5.setText("X镜像");
		this.jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton5ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton5, new AbsoluteConstraints(10, 430, 100, -1));
		this.jButton6.setText("Y镜像");
		this.jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton6ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton6, new AbsoluteConstraints(120, 430, 100, -1));
		this.jButton8.setText("到中心");
		this.jButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton8ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton8, new AbsoluteConstraints(120, 460, 100, -1));
		this.jLabel10.setText("宽度：");
		this.jPanel2.add(this.jLabel10, new AbsoluteConstraints(10, 270, 100, -1));
		this.jButton14.setIcon(new ImageIcon(this.getClass().getResource("/tu/ding_wei_1.png")));
		this.jButton14.setText("预览位置");
		this.jButton14.setToolTipText("预览位置");
		this.jButton14.setHorizontalTextPosition(4);
		this.jButton14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton14ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton14, new AbsoluteConstraints(10, 530, 210, 30));
		this.jButton21.setIcon(new ImageIcon(this.getClass().getResource("/tu/mo_ni.png")));
		this.jButton21.setText("模拟雕刻");
		this.jButton21.setToolTipText("模拟雕刻");
		this.jButton21.setHorizontalTextPosition(4);
		this.jButton21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton21ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton21, new AbsoluteConstraints(10, 500, 210, 30));
		this.jButton15.setIcon(new ImageIcon(this.getClass().getResource("/tu/bo_fang.png")));
		this.jButton15.setText("开始");
		this.jButton15.setToolTipText("开始");
		this.jButton15.setHorizontalTextPosition(4);
		this.jButton15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton15ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton15, new AbsoluteConstraints(10, 560, 100, 30));
		this.jButton17.setIcon(new ImageIcon(this.getClass().getResource("/tu/ting_zhi.png")));
		this.jButton17.setText("停止");
		this.jButton17.setToolTipText("停止");
		this.jButton17.setHorizontalTextPosition(4);
		this.jButton17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.btnStopActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton17, new AbsoluteConstraints(110, 560, 110, 30));
		this.jCheckBox2.setLabel("填充");
		this.jCheckBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jCheckBox2ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jCheckBox2, new AbsoluteConstraints(140, 165, -1, -1));
		this.jButton4.setText("反色");
		this.jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton4ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jButton4, new AbsoluteConstraints(10, 460, 100, -1));
		this.jLabel11.setText("高度：");
		this.jPanel2.add(this.jLabel11, new AbsoluteConstraints(10, 300, 100, -1));
		this.jCheckBox1.setText("等比缩放");
		this.jCheckBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jCheckBox1ActionPerformed(evt);
			}
		});
		this.jPanel2.add(this.jCheckBox1, new AbsoluteConstraints(7, 320, -1, -1));
		this.jTextField3.setText("0");
		this.jTextField3.setMinimumSize(new Dimension(6, 20));
		this.jTextField3.setPreferredSize(new Dimension(12, 20));
		this.jPanel2.add(this.jTextField3, new AbsoluteConstraints(120, 270, 80, 20));
		this.jTextField4.setText("0");
		this.jTextField4.setMinimumSize(new Dimension(6, 20));
		this.jTextField4.setPreferredSize(new Dimension(12, 20));
		this.jPanel2.add(this.jTextField4, new AbsoluteConstraints(120, 300, 80, 20));
		this.jLabel8.setIcon(new ImageIcon(this.getClass().getResource("/tu/shang_xia.png")));
		this.jLabel8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jLabel8MouseClicked(evt);
			}
		});
		this.jPanel2.add(this.jLabel8, new AbsoluteConstraints(200, 270, 20, 20));
		this.jLabel9.setIcon(new ImageIcon(this.getClass().getResource("/tu/shang_xia.png")));
		this.jLabel9.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jLabel9MouseClicked(evt);
			}
		});
		this.jPanel2.add(this.jLabel9, new AbsoluteConstraints(200, 300, 20, 20));
		this.jdt.setRequestFocusEnabled(false);
		this.jdt.setStringPainted(true);
		this.jButton12.setIcon(new ImageIcon(this.getClass().getResource("/tu/bao_cun.png")));
		this.jButton12.setToolTipText("五角星");
		this.jButton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton12ActionPerformed(evt);
			}
		});
		this.jLabel6.setFont(new Font("宋体", 0, 14));
		this.jLabel6.setHorizontalAlignment(4);
		this.jLabel6.setText("0.0");
		this.bq_wen_zi.setHorizontalAlignment(0);
		this.bq_wen_zi.setText("导入图片");
		this.bq_bao_cun.setHorizontalAlignment(0);
		this.bq_bao_cun.setText("导入图片");
		this.bq_lian_jie.setHorizontalAlignment(0);
		this.bq_lian_jie.setText("导入图片");
		this.bq_da_kai.setHorizontalAlignment(0);
		this.bq_da_kai.setText("导入图片");
		this.jButton13.setIcon(new ImageIcon(this.getClass().getResource("/tu/er_wei_ma.png")));
		this.jButton13.setToolTipText("输入文字");
		this.jButton13.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jButton13MouseClicked(evt);
			}
		});
		this.jButton13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton13ActionPerformed(evt);
			}
		});
		this.bq_er_wei_ma.setHorizontalAlignment(0);
		this.bq_er_wei_ma.setText("导入图片");
		this.jButton16.setIcon(new ImageIcon(this.getClass().getResource("/tu/tiao_ma.png")));
		this.jButton16.setToolTipText("输入文字");
		this.jButton16.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jButton16MouseClicked(evt);
			}
		});
		this.jButton16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton16ActionPerformed(evt);
			}
		});
		this.bq_tiao_ma.setHorizontalAlignment(0);
		this.bq_tiao_ma.setText("导入图片");
		this.jButton20.setIcon(new ImageIcon(this.getClass().getResource("/tu/tu_xing.png")));
		this.jButton20.setToolTipText("输入文字");
		this.jButton20.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ApplicationFrame.this.jButton20MouseClicked(evt);
			}
		});
		this.jButton20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton20ActionPerformed(evt);
			}
		});
		this.bq_tu_xing.setHorizontalAlignment(0);
		this.bq_tu_xing.setText("导入图片");
		this.jButton22.setIcon(new ImageIcon(this.getClass().getResource("/tu/she_zhi.png")));
		this.jButton22.setToolTipText("五角星");
		this.jButton22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton22ActionPerformed(evt);
			}
		});
		this.bq_bao_cun1.setHorizontalAlignment(0);
		this.bq_bao_cun1.setText("导入图片");
		this.jButton19.setIcon(new ImageIcon(this.getClass().getResource("/tu/bang_zhu.png")));
		this.jButton19.setToolTipText("连接设备");
		this.jButton19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ApplicationFrame.this.jButton19ActionPerformed(evt);
			}
		});
		this.bq_lian_jie1.setHorizontalAlignment(0);
		this.bq_lian_jie1.setText("导入图片");
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jdt, -1, -1, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton1, -2, 60, -2).addComponent(this.bq_da_kai, -2, 60, -2)).addGap(47, 47, 47).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.bq_bao_cun, -2, 60, -2).addComponent(this.jButton12, -2, 60, -2)).addGap(51, 51, 51).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.buttonOpenTextDialog, -2, 60, -2).addComponent(this.bq_wen_zi, -2, 60, -2)).addGap(51, 51, 51).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jButton13, -2, 60, -2).addComponent(this.bq_er_wei_ma, -2, 60, -2)).addGap(46, 46, 46).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jButton16, -2, 60, -2).addComponent(this.bq_tiao_ma, -2, 60, -2)).addGap(52, 52, 52).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jButton20, -2, 60, -2).addComponent(this.bq_tu_xing, -2, 60, -2)).addGap(54, 54, 54).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.bq_bao_cun1, -2, 60, -2).addComponent(this.jButton22, -2, 60, -2)).addGap(0, 0, 32767)).addComponent(this.hua_ban1, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel2, Alignment.TRAILING, -2, 236, -2).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.bq_lian_jie, -2, 60, -2).addComponent(this.jButton18, -2, 60, -2)).addGap(53, 53, 53).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.bq_lian_jie1, -2, 60, -2).addComponent(this.jButton19, -2, 60, -2))).addComponent(this.jLabel6, Alignment.TRAILING, -2, 110, -2)).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jButton1, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_da_kai)).addGroup(layout.createSequentialGroup().addComponent(this.jButton22, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_bao_cun1))).addGroup(layout.createSequentialGroup().addComponent(this.buttonOpenTextDialog, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_wen_zi))).addGroup(layout.createSequentialGroup().addComponent(this.jButton13, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_er_wei_ma)).addGroup(layout.createSequentialGroup().addComponent(this.jButton16, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_tiao_ma)).addGroup(layout.createSequentialGroup().addComponent(this.jButton20, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_tu_xing)).addGroup(layout.createSequentialGroup().addComponent(this.jButton12, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_bao_cun))).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jButton18, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_lian_jie)).addGroup(layout.createSequentialGroup().addComponent(this.jButton19, -2, 60, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.bq_lian_jie1)))).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.hua_ban1, -1, -1, 32767).addComponent(this.jPanel2, -1, 597, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jdt, -2, -1, -2).addComponent(this.jLabel6))));
		this.pack();
	}

	public void up() {
		this.hua_ban1.repaint();
	}

	void yu_yan() {
		Locale lo = Locale.getDefault();
		if (lo.getLanguage() == Locale.CHINA.getLanguage()) {
			if (lo == Locale.TRADITIONAL_CHINESE) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_CN_T");
			} else {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_zh_CN");
			}
		} else if (lo.getLanguage() == Locale.US.getLanguage()) {
			bundle = ResourceBundle.getBundle("com.engraver.ui.diao_en_US");
		} else if (lo.getLanguage() != Locale.JAPAN.getLanguage() && lo.getLanguage() != Locale.JAPANESE.getLanguage()) {
			if (lo.getLanguage() == Locale.KOREA.getLanguage()) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_han");
				this.jButton19.setVisible(false);
				this.bq_lian_jie1.setVisible(false);
			} else if (lo.getLanguage() == Locale.GERMAN.getLanguage()) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_de");
			} else if (lo.getLanguage().equals("es")) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_xi_ban_ya");
			} else if (lo.getLanguage() == Locale.ITALIAN.getLanguage()) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_yi_da_li");
			} else if (lo.getLanguage() == Locale.FRANCE.getLanguage()) {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_fa_guo");
			} else {
				bundle = ResourceBundle.getBundle("com.engraver.ui.diao_");
			}
		} else {
			bundle = ResourceBundle.getBundle("com.engraver.ui.diao_JP");
		}

		this.str_da_kai = bundle.getString("str_da_kai");
		this.str_wen_zi = bundle.getString("str_wen_zi");
		this.str_yuan = bundle.getString("str_yuan");
		this.str_fang = bundle.getString("str_fang");
		this.str_xin = bundle.getString("str_xin");
		this.str_xing = bundle.getString("str_xing");
		this.str_bao_cun = bundle.getString("str_bao_cun");
		this.str_shi_zi = bundle.getString("str_shi_zi");
		this.str_yu_lan = bundle.getString("str_yu_lan");
		this.str_kai_shi = bundle.getString("str_kai_shi");
		this.str_ting_zhi = bundle.getString("str_ting_zhi");
		this.str_lian_jie = bundle.getString("str_lian_jie");
		this.str_ruo_guang = bundle.getString("str_ruo_guang");
		this.str_gong_lv = bundle.getString("str_gong_lv");
		this.str_shen_du = bundle.getString("str_shen_du");
		this.str_gong_lv_sl = bundle.getString("str_gong_lv_sl");
		this.str_shen_du_sl = bundle.getString("str_shen_du_sl");
		this.str_ci_shu = bundle.getString("str_ci_shu");
		this.str_jing_du = bundle.getString("str_jing_du");
		this.str_gao = bundle.getString("str_gao");
		this.str_zhong = bundle.getString("str_zhong");
		this.str_di = bundle.getString("str_di");
		str_zi_ti = bundle.getString("str_zi_ti");
		str_zi_xing = bundle.getString("str_zi_xing");
		str_chi_cun = bundle.getString("str_chi_cun");
		str_chang_gui = bundle.getString("str_chang_gui");
		str_xie_ti = bundle.getString("str_xie_ti");
		str_cu_ti = bundle.getString("str_cu_ti");
		str_cu_xie = bundle.getString("str_cu_xie");
		str_shu = bundle.getString("str_shu");
		str_shi_liang = bundle.getString("str_shi_liang");
		str_geng_xin = bundle.getString("str_geng_xin");
		str_shi_zhi = bundle.getString("str_shi_zhi");
		str_gu_jian = bundle.getString("str_gu_jian");
		str_xing_hao = bundle.getString("str_xing_hao");
		str_kai_shi_geng_xin = bundle.getString("str_kai_shi_geng_xin");
		str_xia_zai_shi_bai = bundle.getString("str_xia_zai_shi_bai");
		str_liu_shui_hao = bundle.getString("str_liu_shui_hao");
		str_qian_zhui = bundle.getString("str_qian_zhui");
		str_hou_zhui = bundle.getString("str_hou_zhui");
		str_qi_shi = bundle.getString("str_qi_shi");
		str_jie_shu = bundle.getString("str_jie_shu");
		this.jButton1.setToolTipText(this.str_da_kai);
		this.bq_da_kai.setText(this.str_da_kai);
		this.buttonOpenTextDialog.setToolTipText(this.str_wen_zi);
		this.bq_wen_zi.setText(this.str_wen_zi);
		this.jButton13.setToolTipText(bundle.getString("str_er_wei_ma"));
		this.bq_er_wei_ma.setText(bundle.getString("str_er_wei_ma"));
		this.jButton16.setToolTipText(bundle.getString("str_tiao_xing_ma"));
		this.bq_tiao_ma.setText(bundle.getString("str_tiao_xing_ma"));
		this.jButton20.setToolTipText(bundle.getString("str_tu_xing"));
		this.bq_tu_xing.setText(bundle.getString("str_tu_xing"));
		this.jButton12.setToolTipText(this.str_bao_cun);
		this.bq_bao_cun.setText(this.str_bao_cun);
		this.jButton14.setToolTipText(this.str_yu_lan);
		this.jButton15.setToolTipText(this.str_kai_shi);
		this.jButton17.setToolTipText(this.str_ting_zhi);
		this.jButton18.setToolTipText(this.str_lian_jie);
		this.bq_lian_jie.setText(this.str_lian_jie);
		this.jButton22.setToolTipText(bundle.getString("str_shi_zhi"));
		this.bq_bao_cun1.setText(bundle.getString("str_shi_zhi"));
		this.but_hei_bai.setText(bundle.getString("str_hei_bai"));
		this.but_hei_bai.setToolTipText(bundle.getString("str_hei_bai"));
		this.but_hui_du.setText(bundle.getString("str_hui_du"));
		this.but_hui_du.setToolTipText(bundle.getString("str_hui_du"));
		this.but_lun_kuo.setText(bundle.getString("str_lun_kuo"));
		this.but_lun_kuo.setToolTipText(bundle.getString("str_lun_kuo"));
		this.but_su_miao.setText(bundle.getString("str_su_miao"));
		this.but_su_miao.setToolTipText(bundle.getString("str_su_miao"));
		this.jButton4.setText(bundle.getString("str_fan_se"));
		this.jButton4.setToolTipText(bundle.getString("str_fan_se"));
		this.jButton5.setText(bundle.getString("str_x_jing_xiang"));
		this.jButton5.setToolTipText(bundle.getString("str_x_jing_xiang"));
		this.jButton6.setText(bundle.getString("str_y_jing_xiang"));
		this.jButton6.setToolTipText(bundle.getString("str_y_jing_xiang"));
		this.jButton8.setText(bundle.getString("str_dao_zhong_xin"));
		this.jButton8.setToolTipText(bundle.getString("str_dao_zhong_xin"));
		this.jCheckBox2.setText(bundle.getString("str_tian_chong_xing_zhuang"));
		this.jCheckBox2.setToolTipText(bundle.getString("str_tian_chong_xing_zhuang"));
		this.jButton21.setText(bundle.getString("str_mo_ni"));
		this.jButton14.setText(bundle.getString("str_yu_lan"));
		this.jButton15.setText(bundle.getString("str_kai_shi"));
		this.jButton17.setText(bundle.getString("str_ting_zhi"));
		this.jLabel5.setText(this.str_gong_lv + "100%");
		this.jLabel13.setText(this.str_shen_du + "90%");
		this.jLabel7.setText(this.str_ci_shu);
		this.jLabel7.setToolTipText(this.str_ci_shu);
		this.jComboBox1.setToolTipText(this.str_ci_shu);
		this.jLabel14.setText(bundle.getString("str_dui_bi") + "50%");
		this.jLabel16.setText(bundle.getString("str_tian_chong") + "5");
		this.setTitle(bundle.getString("str_ji_guang") + " " + ban_ben);
		this.jLabel10.setText(bundle.getString("str_kuan_du"));
		this.jLabel11.setText(bundle.getString("str_gao_du"));
		this.jCheckBox1.setText(bundle.getString("str_suo_ding"));
		this.hua_ban1.setToolTipText(bundle.getString("str_hua_ban"));
		this.bq_lian_jie1.setText(bundle.getString("str_bang_zhu"));
		this.jButton19.setToolTipText(bundle.getString("str_bang_zhu"));
	}

	void cai_dan() {
		JMenuItem menuItem1 = new JMenuItem(bundle.getString("str_yuan"), new ImageIcon(this.getClass().getResource("/tu/yuan.png")));
		JMenuItem menuItem2 = new JMenuItem(bundle.getString("str_fang"), new ImageIcon(this.getClass().getResource("/tu/fang.png")));
		JMenuItem menuItem3 = new JMenuItem(bundle.getString("str_xin"), new ImageIcon(this.getClass().getResource("/tu/xin.png")));
		JMenuItem menuItem4 = new JMenuItem(bundle.getString("str_xing"), new ImageIcon(this.getClass().getResource("/tu/5xing.png")));
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(2, (BufferedImage)null));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).suo_fang(2.0, 2.0);
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
			}
		});
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(0, (BufferedImage)null));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).suo_fang(2.0, 2.0);
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
			}
		});
		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(3, (BufferedImage)null));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).suo_fang(2.5, 2.5);
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
			}
		});
		menuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(4, (BufferedImage)null));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).suo_fang(3.0, 3.0);
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
			}
		});
		this.popMenu.add(menuItem1);
		this.popMenu.add(menuItem2);
		this.popMenu.add(menuItem3);
		this.popMenu.add(menuItem4);
	}

	private void formWindowOpened(WindowEvent evt) {
		try {
			if (tubiao == 1) {
				Geng_xin.geng_xin();
			}

			FileTransferHandler ft = new FileTransferHandler();
			FileTransferHandler.hb = this.hua_ban1;
			FileTransferHandler.jSlider1 = this.jSlider1;
			FileTransferHandler.jSlider2 = this.jSlider2;
			this.hua_ban1.setTransferHandler(ft);
			this.yu_yan();
			this.cai_dan();
			Tu_yuan ty = new Tu_yuan();
			ty.lei_xing = 0;
			GraphicsPanel.ty_shuzu.add(ty);
			Engraver.state.width = 80;
			Engraver.state.height = 75;
			Engraver.state.scaleFactor = 0.05;
			Engraver.state.memory = 0;
			this.hua_ban1.di_tu();
			this.UI_();
			this.up();
			if (tubiao == 1) {
				this.setIconImage((new ImageIcon(this.getClass().getResource("/tu/Tu_Wainlux.png"))).getImage());
			} else {
				this.setIconImage((new ImageIcon(this.getClass().getResource("/tu/Tu_HANLIN.png"))).getImage());
			}

			this.jButton21.setVisible(false);
			this.hua_ban1.win2 = this;
			this.hua_ban1.jp = this.jPanel1;
			this.hua_ban1.win = this.jPanel2;
			this.hua_ban1.wb1 = this.textFieldX;
			this.hua_ban1.wb2 = this.jTextFieldY;
			this.hua_ban1.wb3 = this.jTextField3;
			this.hua_ban1.wb4 = this.jTextField4;
			this.jdt.setVisible(false);
			this.textFieldX.addKeyListener(this);
			this.jTextFieldY.addKeyListener(this);
			this.jTextField3.addKeyListener(this);
			this.jTextField4.addKeyListener(this);
			this.textFieldX.requestFocus();
			this.getContentPane().setBackground(new Color(240, 240, 240));
			this.jPanel2.setBackground(new Color(240, 240, 240));
			GraphicsPanel.suo = true;
			this.jCheckBox1.setSelected(GraphicsPanel.suo);
			this.win = this;
			this.qu_yu();
		} catch (Exception var7) {
			Exception ex = var7;
			JOptionPane.showMessageDialog((Component)null, ex);
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
		}

		Runnable runnable2 = new Runnable() {
			public void run() {
				while(true) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (ApplicationFrame.this.ji_xing != Engraver.state.memory || ApplicationFrame.this.dang_qian_kuan != Engraver.state.width || ApplicationFrame.this.dang_qian_gao != Engraver.state.height) {
								ApplicationFrame.this.ji_xing = Engraver.state.memory;
								ApplicationFrame.this.dang_qian_kuan = Engraver.state.width;
								ApplicationFrame.this.dang_qian_gao = Engraver.state.height;
								ApplicationFrame.this.hua_ban1.di_tu();
								ApplicationFrame.this.hua_ban1.di_tu();
								ApplicationFrame.this.hua_ban1.di_tu();
								ApplicationFrame.this.UI_();
								ApplicationFrame.this.qu_yu();
							}

							if (Engraver.jin_du > 0) {
								ApplicationFrame.this.jdt.setValue(Engraver.jin_du);
								ApplicationFrame.this.jdt.setVisible(true);
							} else {
								ApplicationFrame.this.jdt.setValue(0);
								ApplicationFrame.this.jdt.setVisible(false);
							}

							if (!Engraver.qu_diao_ke()) {
								if (!ApplicationFrame.this.jdt_xian_shi) {
									Engraver.jin_du = 0;
								}

								if (Engraver.dk_shang_ci) {
									Engraver.dk_shang_ci = false;

									for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
										if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lsh != null) {
											Tu_yuan ty = (Tu_yuan)GraphicsPanel.ty_shuzu.get(i);
											Tu_yuan ty2 = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lsh.sheng_cheng();
											ty.wei_tu_yuan = ty2.wei_tu_yuan;
											ty.wei_tu = ty2.wei_tu;
											ty.lu_jing = ty2.lu_jing;
											GraphicsPanel.ty_shuzu.remove(i);
											GraphicsPanel.ty_shuzu.add(i, ty);
											ApplicationFrame.this.up();
											if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lsh.dang_qian > ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lsh.jie_shu) {
												((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lsh = null;
											}
										}
									}
								}
							} else {
								Engraver.jin_du = Engraver.state.completed;
								Engraver.dk_shang_ci = true;
							}

							if (Engraver.serialPort.messageReceived()) {
								ApplicationFrame.this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb.png")));
							} else {
								ApplicationFrame.this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb_1.png")));
							}

						}
					});

					try {
						Thread.sleep(200L);
					} catch (InterruptedException var2) {
						InterruptedException ex = var2;
						Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
					}
				}
			}
		};
		Thread lj = new Thread(runnable2);
		lj.start();
		Runnable runnable3 = new Runnable() {
			public void run() {
				while(true) {
					if (Engraver.qu_diao_ke()) {
						if (!Engraver.qu_zan_ting()) {
							++ApplicationFrame.this.shi_jian;
						}

						int fen = ApplicationFrame.this.shi_jian / 60;
						int miao = ApplicationFrame.this.shi_jian % 60;
						ApplicationFrame.this.jLabel6.setText(fen + "." + miao);
					}

					try {
						Thread.sleep(1000L);
					} catch (InterruptedException var3) {
						Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var3);
					}
				}
			}
		};
		Thread lj2 = new Thread(runnable3);
		lj2.start();
		this.jComboBox1.removeAllItems();

		for(int i = 0; i < 63; ++i) {
			this.jComboBox1.addItem(String.valueOf(i + 1));
		}

	}

	void ni() {
	}

	private void jButton1MouseClicked(MouseEvent evt) {
	}

	private void jButton7MouseClicked(MouseEvent evt) {
	}

	int qu_anniu(int x, int y) {
		Rectangle rect = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);
		if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15) {
			return 3;
		} else if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y - 15 && y < rect.y + 15) {
			return 2;
		} else if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y - 15 && y < rect.y + 15) {
			return 1;
		} else if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15) {
			return 4;
		} else if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
			return 5;
		} else if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y - 20 && y < rect.y - 20 + 65) {
			System.out.println(6);
			return 6;
		} else if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 45 && y < rect.y + 45 + 65) {
			System.out.println(7);
			return 7;
		} else if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 110 && y < rect.y + 110 + 65) {
			System.out.println(8);
			return 8;
		} else if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 175 && y < rect.y + 175 + 65) {
			System.out.println(9);
			return 9;
		} else if (x > rect.x + rect.width - 50 && x < rect.x + rect.width + 50 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
			return 10;
		} else if (x > rect.x + rect.width - 85 && x < rect.x + rect.width + 85 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
			return 11;
		} else if (x > rect.x + rect.width - 120 && x < rect.x + rect.width + 120 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
			return 12;
		} else {
			return x > rect.x + rect.width - 155 && x < rect.x + rect.width + 155 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50 ? 13 : 0;
		}
	}

	void mo_shi(int m) {
		switch (m) {
		case 1:
			this.but_hei_bai.setSelected(true);
			this.but_hui_du.setSelected(false);
			this.but_lun_kuo.setSelected(false);
			this.but_su_miao.setSelected(false);
			break;
		case 2:
			this.but_hei_bai.setSelected(false);
			this.but_hui_du.setSelected(true);
			this.but_lun_kuo.setSelected(false);
			this.but_su_miao.setSelected(false);
			break;
		case 3:
			this.but_hei_bai.setSelected(false);
			this.but_hui_du.setSelected(false);
			this.but_lun_kuo.setSelected(true);
			this.but_su_miao.setSelected(false);
			break;
		case 4:
			this.but_hei_bai.setSelected(false);
			this.but_hui_du.setSelected(false);
			this.but_lun_kuo.setSelected(false);
			this.but_su_miao.setSelected(true);
		}

		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_fs = m;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chu_li();
				this.up();
				Che_xiao.tian_jia();
				return;
			}
		}

	}

	void jing_xiang_xy(boolean xx) {
		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
				if (xx) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_jxx = !((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_jxx;
				} else {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_jxy = !((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_jxy;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chu_li();
				this.up();
				Che_xiao.tian_jia();
				return;
			}
		}

	}

	void fan_se() {
		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_fan = !((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_fan;
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chu_li();
				this.up();
				Che_xiao.tian_jia();
				return;
			}
		}

	}

	void tian_chong() {
		Rectangle rect_q = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);
		Tu_yuan ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
		ty.lu_jing = new GeneralPath();
		int ge_shu = 0;
		int wei_zhi = 0;
		boolean tc = false;

		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 0 && !((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wen_zi) {
				GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
				lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
				ty.lu_jing.append(lu_jing2, false);
				++ge_shu;
				wei_zhi = i;
				tc = true;
			}
		}

		if (ge_shu == 1) {
			ty.tian_chong = !((Tu_yuan)GraphicsPanel.ty_shuzu.get(wei_zhi)).tian_chong;
		} else {
			ty.tian_chong = true;
		}

		this.jCheckBox2.setSelected(ty.tian_chong);
		List sz = new ArrayList();

		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			if (!((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong || ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1 || ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wen_zi) {
				sz.add((Tu_yuan)GraphicsPanel.ty_shuzu.get(i));
			}
		}

		GraphicsPanel.ty_shuzu = sz;
		ty.xuan_zhong = true;
		Rectangle r = Tu_yuan.qu_jv_xing(ty);
		AffineTransform sf1 = AffineTransform.getTranslateInstance((double)(-r.x), (double)(-r.y));
		AffineTransform fb = new AffineTransform(sf1);
		fb.concatenate(ty.Tx);
		ty.Tx = fb;
		AffineTransform sf3 = AffineTransform.getScaleInstance((double)rect_q.width / (double)r.width, (double)rect_q.height / (double)r.height);
		AffineTransform fb3 = new AffineTransform(sf3);
		fb3.concatenate(ty.Tx);
		ty.Tx = fb3;
		AffineTransform sf2 = AffineTransform.getTranslateInstance((double)rect_q.x, (double)rect_q.y);
		AffineTransform fb2 = new AffineTransform(sf2);
		fb2.concatenate(ty.Tx);
		ty.Tx = fb2;
		if (tc) {
			GraphicsPanel.ty_shuzu.add(ty);
		}

		Che_xiao.tian_jia();
		this.up();
	}

	void shu_an_xia2(MouseEvent evt) {
		this.an_xia = true;
		this.anxia_x = evt.getX();
		this.anxia_y = evt.getY();
		this.anxia_x_1 = this.anxia_x;
		this.anxia_y_1 = this.anxia_y;
		this.an_niu = evt.getButton();
		if (this.an_niu == 1) {
			this.an = this.qu_anniu(this.anxia_x, this.anxia_y);
			int i;
			if (this.an == 1) {
				List sz = new ArrayList();

				for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					if (!((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
						sz.add((Tu_yuan)GraphicsPanel.ty_shuzu.get(i));
					}
				}

				GraphicsPanel.ty_shuzu = sz;
				this.up();
				Che_xiao.tian_jia();
				return;
			}

			if (this.an != 4 && this.an != 5 && this.an != 6 && this.an != 7 && this.an != 8 && this.an != 9) {
				if (this.an == 2) {
					return;
				}

				if (this.an == 3) {
					return;
				}

				if (this.an != 10 && this.an != 11 && this.an != 12 && this.an == 13) {
				}
			}

			Rectangle rect_q = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);
			if (this.anxia_x > rect_q.x && this.anxia_x < rect_q.x + rect_q.width && this.anxia_y > rect_q.y && this.anxia_y < rect_q.y + rect_q.height) {
				this.kuang = false;
				this.up();
			} else {
				for(i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					if (!((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
						GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
						lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
						Rectangle rect = lu_jing2.getBounds();
						if (this.anxia_x > rect.x && this.anxia_x < rect.x + rect.width && this.anxia_y > rect.y && this.anxia_y < rect.y + rect.height) {
							for(int ii = 0; ii < GraphicsPanel.ty_shuzu.size(); ++ii) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(ii)).xuan_zhong = false;
							}

							((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = true;
							Tu_yuan ty = (Tu_yuan)GraphicsPanel.ty_shuzu.get(i);
							GraphicsPanel.ty_shuzu.remove(i);
							GraphicsPanel.ty_shuzu.add(1, ty);
							this.jSlider6.setValue(ty.yu_zhi);
							this.up();
							this.kuang = false;
							return;
						}
					}

					for(int ii = 0; ii < GraphicsPanel.ty_shuzu.size(); ++ii) {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(ii)).xuan_zhong = false;
					}

					this.kuang = true;
					this.up();
				}
			}
		} else if (this.an_niu == 3) {
		}

	}

	private void hua_ban1MousePressed(MouseEvent evt) {
		if (!this.jin_yong_suo_fang) {
			this.shu_an_xia2(evt);
			this.textFieldX.requestFocus(true);
		}
	}

	private void hua_ban1MouseReleased(MouseEvent evt) {
		this.an_xia = false;
		Tu_yuan.tuo = false;
		if (Engraver.qu_yu_lan()) {
			Runnable runnable2 = new Runnable() {
				public void run() {
					Tu_yuan.qu_jvxing(GraphicsPanel.ty_shuzu);
					GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
					lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
					Rectangle rect = lu_jing2.getBounds();
					Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
					AffineTransform sf = AffineTransform.getTranslateInstance((double)(0 - rect.x), (double)(0 - rect.y));
					zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
					sf = AffineTransform.getScaleInstance(1.0 / GraphicsPanel.quan_beishu, 1.0 / GraphicsPanel.quan_beishu);
					zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
					if (zui_zhong_wjx2.width >= 2 || zui_zhong_wjx2.height >= 2) {
						Engraver.serialPort.ming_ling_yu_lan(zui_zhong_wjx2.x + zui_zhong_wjx2.width / 2, zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2, zui_zhong_wjx2.width, zui_zhong_wjx2.height);
						ApplicationFrame.this.up();
					}
				}
			};
			Thread thread2 = new Thread(runnable2);
			thread2.start();
		}

		if (this.tuo_dong) {
			this.tuo_dong = false;
			Che_xiao.tian_jia();
		}

		this.hua_ban1.xuan_zhong_lei_xing();
		if (this.hua_ban1.LeiXing_sl && this.hua_ban1.LeiXing_tp) {
			this.but_hei_bai.setEnabled(true);
			this.but_hui_du.setEnabled(true);
			this.but_lun_kuo.setEnabled(true);
			this.but_su_miao.setEnabled(true);
			this.jCheckBox1.setEnabled(true);
			this.jButton4.setEnabled(true);
			this.jButton5.setEnabled(true);
			this.jButton6.setEnabled(true);
			this.jButton8.setEnabled(true);
			this.jCheckBox2.setEnabled(true);
			this.jSlider7.setEnabled(true);
		} else if (this.hua_ban1.LeiXing_sl && !this.hua_ban1.LeiXing_tp) {
			this.but_hei_bai.setEnabled(false);
			this.but_hui_du.setEnabled(false);
			this.but_lun_kuo.setEnabled(false);
			this.but_su_miao.setEnabled(false);
			this.jCheckBox1.setEnabled(true);
			this.jButton4.setEnabled(false);
			this.jButton5.setEnabled(true);
			this.jButton6.setEnabled(true);
			this.jButton8.setEnabled(true);
			this.jCheckBox2.setEnabled(true);
			this.jSlider7.setEnabled(true);
		} else if (!this.hua_ban1.LeiXing_sl && this.hua_ban1.LeiXing_tp) {
			this.but_hei_bai.setEnabled(true);
			this.but_hui_du.setEnabled(true);
			this.but_lun_kuo.setEnabled(true);
			this.but_su_miao.setEnabled(true);
			this.jCheckBox1.setEnabled(true);
			this.jButton4.setEnabled(true);
			this.jButton5.setEnabled(true);
			this.jButton6.setEnabled(true);
			this.jButton8.setEnabled(true);
			this.jCheckBox2.setEnabled(false);
			this.jSlider7.setEnabled(false);
		} else if (!this.hua_ban1.LeiXing_sl && !this.hua_ban1.LeiXing_tp) {
			this.but_hei_bai.setEnabled(false);
			this.but_hui_du.setEnabled(false);
			this.but_lun_kuo.setEnabled(false);
			this.but_su_miao.setEnabled(false);
			this.jCheckBox1.setEnabled(false);
			this.jButton4.setEnabled(false);
			this.jButton5.setEnabled(true);
			this.jButton6.setEnabled(true);
			this.jButton8.setEnabled(false);
			this.jCheckBox2.setEnabled(false);
			this.jSlider7.setEnabled(false);
		}

		this.up();
	}

	void shu_yi_dong2(MouseEvent evt) {
		if (this.an_xia) {
			int dx = evt.getX();
			int dy = evt.getY();
			int x;
			if (this.an_niu == 1) {
				Rectangle rect = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);
				int i;
				if (this.an == 0) {
					if (this.kuang) {
						if (dx < this.anxia_x_1) {
							x = dx;
							i = this.anxia_x_1 - dx;
						} else {
							x = this.anxia_x_1;
							i = dx - this.anxia_x_1;
						}

						int y;
						int g;
						if (dy < this.anxia_y_1) {
							y = dy;
							g = this.anxia_y_1 - dy;
						} else {
							y = this.anxia_y_1;
							g = dy - this.anxia_y_1;
						}

						Tu_yuan.tuo = true;
						Tu_yuan.shu_biao = new Rectangle(x, y, i, g);
						Tu_yuan.kuang_xuan(GraphicsPanel.ty_shuzu, Tu_yuan.shu_biao);
					} else {
						for(x = 0; x < GraphicsPanel.ty_shuzu.size(); ++x) {
							if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(x)).xuan_zhong) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(x)).ping_yi(dx - this.anxia_x, dy - this.anxia_y);
							}
						}
					}

					this.anxia_x = dx;
					this.anxia_y = dy;
					this.up();
				} else if (this.an == 2) {
					float zhong_xin_x = 0.0F;
					float zhong_xin_y = 0.0F;
					float jiao1 = 0.0F;
					float jiao2 = 0.0F;
					zhong_xin_x = (float)(rect.x + rect.width / 2);
					zhong_xin_y = (float)(rect.y + rect.height / 2);
					if ((float)this.anxia_x > zhong_xin_x && (float)this.anxia_y < zhong_xin_y) {
						jiao1 = 360.0F - (float)Math.toDegrees(Math.atan((double)((zhong_xin_y - (float)this.anxia_y) / ((float)this.anxia_x - zhong_xin_x))));
					} else if ((float)this.anxia_x < zhong_xin_x && (float)this.anxia_y < zhong_xin_y) {
						jiao1 = 270.0F - (float)Math.toDegrees(Math.atan((double)((zhong_xin_x - (float)this.anxia_x) / (zhong_xin_y - (float)this.anxia_y))));
					} else if ((float)this.anxia_x < zhong_xin_x && (float)this.anxia_y > zhong_xin_y) {
						jiao1 = 90.0F + (float)Math.toDegrees(Math.atan((double)((zhong_xin_x - (float)this.anxia_x) / ((float)this.anxia_y - zhong_xin_y))));
					} else if ((float)this.anxia_x > zhong_xin_x && (float)this.anxia_y > zhong_xin_y) {
						jiao1 = (float)Math.toDegrees(Math.atan((double)(((float)this.anxia_y - zhong_xin_y) / ((float)this.anxia_x - zhong_xin_x))));
					}

					if ((float)dx > zhong_xin_x && (float)dy < zhong_xin_y) {
						jiao2 = 360.0F - (float)Math.toDegrees(Math.atan((double)((zhong_xin_y - (float)dy) / ((float)dx - zhong_xin_x))));

						for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
							if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhuan((double)(jiao2 - jiao1), (double)zhong_xin_x, (double)zhong_xin_y);
							}
						}

						this.anxia_x = dx;
						this.anxia_y = dy;
					} else if ((float)dx < zhong_xin_x && (float)dy < zhong_xin_y) {
						jiao2 = 270.0F - (float)Math.toDegrees(Math.atan((double)((zhong_xin_x - (float)dx) / (zhong_xin_y - (float)dy))));

						for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
							if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhuan((double)(jiao2 - jiao1), (double)zhong_xin_x, (double)zhong_xin_y);
							}
						}

						this.anxia_x = dx;
						this.anxia_y = dy;
					} else if ((float)dx < zhong_xin_x && (float)dy > zhong_xin_y) {
						jiao2 = 90.0F + (float)Math.toDegrees(Math.atan((double)((zhong_xin_x - (float)dx) / ((float)dy - zhong_xin_y))));

						for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
							if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhuan((double)(jiao2 - jiao1), (double)zhong_xin_x, (double)zhong_xin_y);
							}
						}

						this.anxia_x = dx;
						this.anxia_y = dy;
					} else if ((float)dx > zhong_xin_x && (float)dy > zhong_xin_y) {
						jiao2 = (float)Math.toDegrees(Math.atan((double)(((float)dy - zhong_xin_y) / ((float)dx - zhong_xin_x))));

						for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
							if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
								((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhuan((double)(jiao2 - jiao1), (double)zhong_xin_x, (double)zhong_xin_y);
							}
						}

						this.anxia_x = dx;
						this.anxia_y = dy;
					}

					this.up();
				}

				if (this.an == 3) {
					double sf;
					if (GraphicsPanel.suo) {
						sf = (double)(this.anxia_x - rect.x) / (double)rect.width;
						if (sf > 0.0) {
							for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
								if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(sf, sf);
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
								}
							}
						}

						this.anxia_x = dx;
					} else {
						sf = (double)(this.anxia_x - rect.x) / (double)rect.width;
						double sf_y = (double)(this.anxia_y - rect.y) / (double)rect.height;
						if (sf > 0.0 && sf_y > 0.0) {
							for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
								if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(sf, sf_y);
									((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
								}
							}
						}

						this.anxia_x = dx;
						this.anxia_y = dy;
					}

					this.up();
				}

				this.tuo_dong = true;
			} else if (this.an_niu == 3) {
				AffineTransform py = AffineTransform.getTranslateInstance((double)(dx - this.anxia_x), (double)(dy - this.anxia_y));
				GraphicsPanel.quan_x = (int)((double)(GraphicsPanel.quan_x + (dx - this.anxia_x)) / GraphicsPanel.quan_beishu);
				GraphicsPanel.quan_y = (int)((double)(GraphicsPanel.quan_y + (dy - this.anxia_y)) / GraphicsPanel.quan_beishu);

				for(x = 0; x < GraphicsPanel.ty_shuzu.size(); ++x) {
					AffineTransform fb = new AffineTransform(py);
					fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(x)).Tx);
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(x)).Tx = fb;
				}

				this.anxia_x = dx;
				this.anxia_y = dy;
				this.up();
			}
		}

	}

	private void hua_ban1MouseDragged(MouseEvent evt) {
		this.shu_yi_dong2(evt);
	}

	public static BufferedImage convertToBufferedImage(Image image) {
		BufferedImage newImage = new BufferedImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), 2);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image, 0, 0, (ImageObserver)null);
		g.dispose();
		return newImage;
	}

	private void jButton1ActionPerformed(ActionEvent evt) {

		final JFileChooser fc = new JFileChooser(this.dang_qian_mu_lu);

		//Open props
		final String lastOpened = properties.getProperty(PropertiesHelper.LAST_OPENED_DIR);
		if (lastOpened != null) {
			final Path pathLast = Paths.get(lastOpened);
			if (Files.exists(pathLast) && Files.isDirectory(pathLast)) {
				fc.setCurrentDirectory(pathLast.toFile());
			} else
				System.out.println("Invalid path: " + lastOpened);
		}


		ImagePreviewPanel preview = new ImagePreviewPanel();
		fc.setAccessory(preview);
		fc.addPropertyChangeListener(preview);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.bmp,.jpg,.png,.jpeg,.gif,.xj,.plt,.hpgl,.dxf)", new String[]{"bmp", "jpg", "png", "jpeg", "gif", "xj", "plt", "hpgl", "dxf"});
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(this);
		if (fc.getSelectedFile() != null && returnVal == 0) {



			File gcodeFile = fc.getSelectedFile();
			String gcodeFilePath = fc.getSelectedFile().getPath();

			//Save to props
			final Path chosenDir = fc.getSelectedFile().toPath().getParent();
			if (Files.isDirectory(chosenDir)) 
				properties.setProperty(PropertiesHelper.LAST_OPENED_DIR, chosenDir.toAbsolutePath().toString());

			this.dang_qian_mu_lu = gcodeFilePath;
			String fileName = gcodeFile.getName();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			suffix = suffix.toUpperCase();
			BufferedImage bmp;
			if (!suffix.equals("BMP") && !suffix.equals("JPG") && !suffix.equals("PNG") && !suffix.equals("JPEG") && !suffix.equals("GIF")) {
				if (suffix.equals("XJ")) {
					try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gcodeFilePath));

						try {
							GraphicsPanel.ty_shuzu = (List)ois.readObject();
						} catch (Throwable var14) {
							try {
								ois.close();
							} catch (Throwable var13) {
								var14.addSuppressed(var13);
							}

							throw var14;
						}

						ois.close();
					} catch (Exception var15) {
						var15.printStackTrace();
					}

					for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
						if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
							((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu = new BufferedImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_g, 2);
							((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan = new BufferedImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_g, 2);
							((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.setRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_g, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w);
							((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.setRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_g, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w);
						}
					}

					if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).gong_lv != 0 && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).shen_du != 0) {
						this.jSlider1.setValue(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).gong_lv);
						this.jSlider2.setValue(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).shen_du);
					}

					GraphicsPanel.quan_beishu = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).quan_beishu_;
					Che_xiao.tian_jia();
					this.up();
				} else {
					jie_xi_PLT plt;
					if (suffix.equals("PLT")) {
						plt = new jie_xi_PLT();
						plt.jie_xi_PLT(gcodeFile);
						bmp = null;
						Che_xiao.tian_jia();
						this.up();
					} else if (suffix.equals("HPGL")) {
						plt = new jie_xi_PLT();
						plt.jie_xi_PLT(gcodeFile);
						bmp = null;
						Che_xiao.tian_jia();
						this.up();
					} else if (suffix.equals("DXF")) {
						jie_xi_dxf.jie_xi(gcodeFile);
						Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
						Che_xiao.tian_jia();
						this.up();
					}
				}
			} else {
				try {
					bmp = ImageIO.read(gcodeFile);
					if (bmp.getWidth() > 5000 || bmp.getHeight() > 5000) {
						double bi;
						if (bmp.getWidth() > bmp.getHeight()) {
							bi = 5000.0 / (double)bmp.getWidth();
						} else {
							bi = 5000.0 / (double)bmp.getHeight();
						}

						bmp = Tu_pian.zoomImage(bmp, bi);
					}

					GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));

					for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
					}

					((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
					Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
					Che_xiao.tian_jia();
					this.up();
				} catch (IOException var16) {
				}
			}
		}

	}

	private void hua_ban1MouseWheelMoved(MouseWheelEvent evt) {
		if (!this.jin_yong_suo_fang) {
			int dx = evt.getX();
			int dy = evt.getY();
			AffineTransform sf2;
			AffineTransform sf;
			if (evt.getPreciseWheelRotation() < 0.0) {
				AffineTransform sf1 = AffineTransform.getTranslateInstance((double)(-dx), (double)(-dy));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					sf2 = new AffineTransform(sf1);
					sf2.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = sf2;
				}

				GraphicsPanel.quan_beishu *= 1.1;
				sf = AffineTransform.getScaleInstance(1.1, 1.1);

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					sf = new AffineTransform(sf);
					sf.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = sf;
				}

				sf2 = AffineTransform.getTranslateInstance((double)dx, (double)dy);

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					sf2 = new AffineTransform(sf2);
					sf2.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = sf2;
				}
			} else {
				GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
				lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
				Rectangle rect = lu_jing2.getBounds();
				if (rect.width > 200) {
					sf2 = AffineTransform.getTranslateInstance((double)(-dx), (double)(-dy));

					for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
						sf2 = new AffineTransform(sf2);
						sf2.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = sf2;
					}

					GraphicsPanel.quan_beishu *= 0.9;
					sf = AffineTransform.getScaleInstance(0.9, 0.9);

					for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
						AffineTransform fb = new AffineTransform(sf);
						fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
					}

					sf2 = AffineTransform.getTranslateInstance((double)dx, (double)dy);

					for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
						AffineTransform fb = new AffineTransform(sf2);
						fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
					}
				}
			}

			this.up();
		}
	}

	private void btnStopActionPerformed(ActionEvent evt) {

		Runnable runnable2 = new Runnable() {
			public void run() {
				if (Engraver.serialPort.messageReceived()) {
					Engraver.serialPort.sendStopCommand();
				}

				Engraver.ting_zhi = true;
				ApplicationFrame.this.jdt_xian_shi = false;
			}
		};
		Thread thread2 = new Thread(runnable2);
		thread2.start();

		Runnable runnable = new Runnable() {
			public void run() {
				Engraver.serialPort.sendStopPreviewCommand();
				ApplicationFrame.this.up();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private void jButton18ActionPerformed(ActionEvent evt) {

		if (Engraver.serialPort.messageReceived()) {
			Engraver.serialPort.closePort();

		} else {

			final String defaultPort = properties.getProperty(PropertiesHelper.DEFAULT_PORT);

			if (defaultPort == null) {
				showSerialPortSelectorDialog();

			} else {
				//Make sure we can reach the device
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				boolean success = Engraver.serialPort.testPort(defaultPort);
				setCursor(Cursor.getDefaultCursor());
				if (!success) {
					JOptionPane.showMessageDialog(this, "Could not connect to engraver on port " + defaultPort, "Error!", JOptionPane.ERROR_MESSAGE);
					showSerialPortSelectorDialog();
				} else
					Engraver.serialPort.openPort(defaultPort);

			}
		}
	}

	private void showSerialPortSelectorDialog() {
		if (serialSelectorDialog == null)
			serialSelectorDialog = new SerialPortSelectorDialog(this, true);
		serialSelectorDialog.setVisible(true);
	}

	//	public void jie_xi_dxf2() {
	//		Parser dxfParser = ParserBuilder.createDefaultParser();
	//		try {
	//			dxfParser.parse(new FileInputStream("C:\\Users\\Administrator\\Desktop\\dxf2.dxf"), "UTF-8");
	//			DXFDocument var6 = dxfParser.getDocument();
	//		} catch (FileNotFoundException var3) {
	//			FileNotFoundException ex = var3;
	//			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
	//		} catch (ParseException var4) {
	//			ParseException ex = var4;
	//			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
	//		}
	//
	//	}

	URI StringtoURI(String ss) {
		File file = new File(ss);
		URI uri = null;
		uri = file.toURI();
		return uri;
	}

	private void jButton15ActionPerformed(ActionEvent evt) {
		if (Engraver.serialPort.messageReceived()) {
			Engraver.ting_zhi = false;
			this.jdt_xian_shi = true;
			Runnable runnable2;
			Thread thread2;
			if (!Engraver.qu_diao_ke()) {
				if (Engraver.qu_yu_lan()) {
					Engraver.serialPort.sendStopPreviewCommand();
				}

				runnable2 = new Runnable() {
					public void run() {
						try {
							ApplicationFrame.this.jButton15.setEnabled(false);
							ApplicationFrame.this.jin_yong_suo_fang = true;
							ApplicationFrame.this.tuo_ji();
							ApplicationFrame.this.jButton15.setEnabled(true);
							ApplicationFrame.this.jin_yong_suo_fang = false;
							Engraver.jin_du = 0;
						} catch (Exception var2) {
							Exception ex = var2;
							JOptionPane.showMessageDialog((Component)null, ex);
							Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
						}

					}
				};
				thread2 = new Thread(runnable2);
				thread2.start();
				this.shi_jian = 0;
				chaoshi = 0;
			} else if (Engraver.qu_zan_ting()) {
				runnable2 = new Runnable() {
					public void run() {
						if (Engraver.serialPort.messageReceived()) {
							Engraver.serialPort.ming_ling_ji_xu();
						}

					}
				};
				thread2 = new Thread(runnable2);
				thread2.start();
			} else {
				runnable2 = new Runnable() {
					public void run() {
						if (Engraver.serialPort.messageReceived()) {
							Engraver.serialPort.ming_ling_zan_ting();
						}

					}
				};
				thread2 = new Thread(runnable2);
				thread2.start();
			}

		}
	}

	private void jButton14ActionPerformed(ActionEvent evt) {
		if (!Engraver.qu_diao_ke()) {
			if (Engraver.serialPort.messageReceived()) {
				Runnable runnable2;
				Thread thread2;
				if (Engraver.qu_yu_lan()) {
					runnable2 = new Runnable() {
						public void run() {
							Engraver.serialPort.sendStopPreviewCommand();
							ApplicationFrame.this.up();
						}
					};
					thread2 = new Thread(runnable2);
					thread2.start();
				} else {
					runnable2 = new Runnable() {
						public void run() {
							Tu_yuan.qu_jvxing(GraphicsPanel.ty_shuzu);
							GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
							lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
							Rectangle rect = lu_jing2.getBounds();
							Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
							AffineTransform sf = AffineTransform.getTranslateInstance((double)(0 - rect.x), (double)(0 - rect.y));
							zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
							sf = AffineTransform.getScaleInstance(1.0 / GraphicsPanel.quan_beishu, 1.0 / GraphicsPanel.quan_beishu);
							zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
							if (zui_zhong_wjx2.width >= 2 || zui_zhong_wjx2.height >= 2) {
								Engraver.serialPort.ming_ling_yu_lan(zui_zhong_wjx2.x + zui_zhong_wjx2.width / 2, zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2, zui_zhong_wjx2.width, zui_zhong_wjx2.height);
								ApplicationFrame.this.up();
							}
						}
					};
					thread2 = new Thread(runnable2);
					thread2.start();
				}
			}

		}
	}

	private void jButton7ActionPerformed(ActionEvent evt) {
		TextEntryDialog dialog = new TextEntryDialog(this.hua_ban1, true);
		dialog.setDefaultCloseOperation(2);
		dialog.setVisible(true);
	}

	private void formComponentResized(ComponentEvent evt) {
	}

	private void jSlider1StateChanged(ChangeEvent evt) {
		this.jLabel5.setText(this.str_gong_lv + this.jSlider1.getValue() + "%");
		Engraver.gong_lv_wt = this.jSlider1.getValue();
	}

	void she_zhi() {
		Runnable runnable2 = new Runnable() {
			public void run() {
				Engraver.shen_du_wt = 100 - ApplicationFrame.this.jSlider2.getValue();
				Engraver.gong_lv_wt = ApplicationFrame.this.jSlider1.getValue();
				if (Engraver.serialPort.messageReceived()) {
					Engraver.serialPort.ming_ling_gl_sd(Engraver.gong_lv_wt, Engraver.shen_du_wt);
				}

			}
		};
		Thread thread2 = new Thread(runnable2);
		thread2.start();
	}

	private void jSlider1MouseReleased(MouseEvent evt) {
		if (Engraver.qu_diao_ke()) {
			this.she_zhi();
		}
	}

	private void jSlider2StateChanged(ChangeEvent evt) {
		this.jLabel13.setText(this.str_shen_du + this.jSlider2.getValue() + "%");
		Engraver.shen_du_wt = 100 - this.jSlider2.getValue();
	}

	private void jSlider2MouseReleased(MouseEvent evt) {
		if (Engraver.qu_diao_ke()) {
			this.she_zhi();
		}
	}

	void she_zhi_can_shu() {
		Runnable runnable2 = new Runnable() {
			public void run() {
				int rg = Tu_yuan.dk_ruo_guang * 2;
				int jd = Tu_yuan.dk_jing_du;
				if (ApplicationFrame.this.com_dakai) {
					ApplicationFrame.com2.sendBytes(new byte[]{40, 0, 11, (byte)rg, (byte)jd, 0, 0, 0, 0, 0, 0}, 2);
				}

			}
		};
		Thread thread2 = new Thread(runnable2);
		thread2.start();
	}

	private void jButton12ActionPerformed(ActionEvent evt) {

		JFileChooser chooser = new JFileChooser();

		final String lastSaved = properties.getProperty(PropertiesHelper.LAST_SAVED_DIR);
		if (lastSaved != null) {
			final Path pathLast = Paths.get(lastSaved);
			if (Files.exists(pathLast) && Files.isDirectory(pathLast)) {
				chooser.setCurrentDirectory(pathLast.toFile());
			} else
				System.out.println("Invalid path: " + lastSaved);
		}

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.xj)", new String[]{"xj"});
		chooser.setFileFilter(filter);
		int option = chooser.showSaveDialog(this);
		if (option == 0) {
			for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getWidth();
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_g = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getHeight();
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getWidth();
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_g = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getHeight();
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_ = new int[((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getWidth() * ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getHeight()];
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getWidth(), ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getHeight(), ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.getWidth());
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan_ = new int[((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getWidth() * ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getHeight()];
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getWidth(), ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getHeight(), ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.getWidth());
				}
			}

			((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).gong_lv = this.jSlider1.getValue();
			((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).shen_du = this.jSlider2.getValue();
			((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).quan_beishu_ = GraphicsPanel.quan_beishu;
			Tu_yuan.hui_fu();
			BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(GraphicsPanel.ty_shuzu);
			Tu_yuan.hui_fu_xian_chang();
			String ss = chooser.getSelectedFile().getPath();


			final Path chosenDir = chooser.getSelectedFile().toPath().getParent();
			if (Files.isDirectory(chosenDir)) 
				properties.setProperty(PropertiesHelper.LAST_SAVED_DIR, chosenDir.toAbsolutePath().toString());


			ss = ss.toLowerCase();
			if (!ss.substring(ss.length() - 3).equals(".xj")) {
				ss = ss + ".xj";
			}

			File f;
			if (tu_diaoke2 != null) {
				f = new File(ss + ".bmp");

				try {
					BMPEncoder.write(tu_diaoke2, f);
				} catch (IOException var14) {
					IOException ex = var14;
					Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, (String)null, ex);
				}
			}

			f = new File(ss);

			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

				try {
					oos.writeObject(GraphicsPanel.ty_shuzu);
				} catch (Throwable var12) {
					try {
						oos.close();
					} catch (Throwable var11) {
						var12.addSuppressed(var11);
					}

					throw var12;
				}

				oos.close();
			} catch (Exception var13) {
				var13.printStackTrace();
			}
		}

	}

	private void jSlider6StateChanged(ChangeEvent evt) {
		this.jLabel14.setText(bundle.getString("str_dui_bi") + this.jSlider6.getValue() + "%");
		if (GraphicsPanel.ty_shuzu.size() >= 2) {
			for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).yu_zhi = this.jSlider6.getValue();
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chu_li();
				}
			}

			this.up();
		}
	}

	private void jSlider7StateChanged(ChangeEvent evt) {
		this.jLabel16.setText(bundle.getString("str_tian_chong") + this.jSlider7.getValue());
		Tu_yuan.tian_chong_md = this.jSlider7.getValue();
		this.up();
	}

	private void jButton13MouseClicked(MouseEvent evt) {
	}

	private void jButton13ActionPerformed(ActionEvent evt) {
		final JFrame win = new JFrame("QR");
		Container con = win.getContentPane();
		con.setLayout((LayoutManager)null);
		JLabel label1 = new JLabel(bundle.getString("str_qing_shu_ru"));
		JButton button = new JButton("OK");
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 36, 311, 113);
		label1.setBounds(10, 10, 274, 16);
		button.setBounds(248, 156, 74, 38);
		con.add(label1);
		con.add(textArea);
		con.add(button);
		win.setBackground(Color.LIGHT_GRAY);
		win.setIconImage((new ImageIcon(this.getClass().getResource("/tu/she_zhi.png"))).getImage());
		win.setVisible(true);
		win.setSize(345, 238);
		win.setLocationRelativeTo((Component)null);
		win.setDefaultCloseOperation(2);
		win.setResizable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = textArea.getText();
				win.setTitle(text);
				QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;
				QrCode qr = QrCode.encodeText(text, errCorLvl);
				BufferedImage img = qr.toImage(35, 4);
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(1, img));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
				win.dispose();
			}
		});
	}

	private void jButton16MouseClicked(MouseEvent evt) {
	}

	private void jButton16ActionPerformed(ActionEvent evt) {
		final JFrame win = new JFrame("Barcode");
		Container con = win.getContentPane();
		con.setLayout((LayoutManager)null);
		JLabel label1 = new JLabel(bundle.getString("str_qing_shu_ru2"));
		JButton button = new JButton("OK");
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 36, 311, 113);
		label1.setBounds(10, 10, 274, 16);
		button.setBounds(248, 156, 74, 38);
		con.add(label1);
		con.add(textArea);
		con.add(button);
		win.setBackground(Color.LIGHT_GRAY);
		win.setIconImage((new ImageIcon(this.getClass().getResource("/tu/she_zhi.png"))).getImage());
		win.setVisible(true);
		win.setSize(345, 238);
		win.setLocationRelativeTo((Component)null);
		win.setDefaultCloseOperation(2);
		win.setResizable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = textArea.getText();
				BufferedImage img = (BufferedImage)Barcode.DoEncode(Barcode.TYPE.CODE128, text);
				GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(1, img));

				for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
				}

				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).suo_fang(5.0, 3.0);
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
				Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
				Che_xiao.tian_jia();
				ApplicationFrame.this.up();
				win.dispose();
			}
		});
	}

	private void jButton20MouseClicked(MouseEvent evt) {
	}

	private void jButton20ActionPerformed(ActionEvent evt) {
		this.popMenu.show(this.getComponent(this.getComponentCount() - 1), this.jButton20.getX(), this.jButton20.getY() + this.jButton20.getHeight());
	}

	private void jButton21ActionPerformed(ActionEvent evt) {
		Runnable runnable2;
		Thread thread2;
		if (!this.mo_ni) {
			this.mo_ni = true;
			runnable2 = new Runnable() {
				public void run() {
					Tu_yuan.hui_fu();
					BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(GraphicsPanel.ty_shuzu);
					List dian = Tu_yuan.qu_dian(GraphicsPanel.ty_shuzu);
					Tu_yuan.hui_fu_xian_chang();
					if (tu_diaoke2 == null || dian != null) {
						byte[] bao;
						int jishu;
						int j;
						if (tu_diaoke2 == null && dian != null) {
							dian = ApplicationFrame.this.dian_ya_suo(dian);
							bao = new byte[dian.size() * 4 + 6];
							jishu = 4;

							for(j = 0; j < dian.size(); ++j) {
								bao[jishu++] = (byte)(((Dian)dian.get(j)).x >> 8);
								bao[jishu++] = (byte)((Dian)dian.get(j)).x;
								bao[jishu++] = (byte)(((Dian)dian.get(j)).y >> 8);
								bao[jishu++] = (byte)((Dian)dian.get(j)).y;
							}

							bao[0] = -85;
							bao[1] = 17;
							bao[2] = (byte)(dian.size() * 4 + 6 >> 8);
							bao[3] = (byte)(dian.size() * 4 + 6);
							Engraver.serialPort.ming_ling_fa_shu_ju(bao);
						} else if (tu_diaoke2 != null && dian != null) {
							dian = ApplicationFrame.this.dian_ya_suo(dian);
							bao = new byte[dian.size() * 4 + 6];
							jishu = 4;

							for(j = 0; j < dian.size(); ++j) {
								bao[jishu++] = (byte)(((Dian)dian.get(j)).x >> 8);
								bao[jishu++] = (byte)((Dian)dian.get(j)).x;
								bao[jishu++] = (byte)(((Dian)dian.get(j)).y >> 8);
								bao[jishu++] = (byte)((Dian)dian.get(j)).y;
							}

							bao[0] = -85;
							bao[1] = 17;
							bao[2] = (byte)(dian.size() * 4 + 6 >> 8);
							bao[3] = (byte)(dian.size() * 4 + 6);
							Engraver.serialPort.ming_ling_fa_shu_ju(bao);
						}
					}

				}
			};
			thread2 = new Thread(runnable2);
			thread2.start();
		} else {
			runnable2 = new Runnable() {
				public void run() {
					Engraver.serialPort.sendStopPreviewCommand();
					ApplicationFrame.this.mo_ni = false;
					ApplicationFrame.this.up();
				}
			};
			thread2 = new Thread(runnable2);
			thread2.start();
		}

	}

	private void but_hei_baiActionPerformed(ActionEvent evt) {
		this.mo_shi(1);
	}

	private void but_hui_duActionPerformed(ActionEvent evt) {
		this.mo_shi(2);
	}

	private void but_lun_kuoActionPerformed(ActionEvent evt) {
		this.mo_shi(3);
	}

	private void but_su_miaoActionPerformed(ActionEvent evt) {
		this.mo_shi(4);
	}

	private void jButton8ActionPerformed(ActionEvent evt) {
		Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
		this.up();
		Che_xiao.tian_jia();
	}

	private void jButton5ActionPerformed(ActionEvent evt) {
		this.jing_xiang_xy(true);
	}

	private void jButton6ActionPerformed(ActionEvent evt) {
		this.jing_xiang_xy(false);
	}

	private void jButton22ActionPerformed(ActionEvent evt) {
		final JFrame win = new JFrame("Set");
		Container con = win.getContentPane();
		con.setLayout((LayoutManager)null);
		//con.setLayout(new FlowLayout());
		final JLabel label1 = new JLabel(bundle.getString("str_ruo_guang") + Engraver.state.standby / 2 + "%");
		JLabel label2 = new JLabel(bundle.getString("str_jing_du"));
		final JCheckBox gz = new JCheckBox(bundle.getString("str_gun_zhou"));
		gz.setSelected(Engraver.qu_gun_zhou());
		final JCheckBox laser1064 = new JCheckBox(bundle.getString("str_laser1064"));
		laser1064.setSelected(Engraver.qu_1064());
		JButton btnCommSettings = new JButton("Serial");
		btnCommSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSerialPortSelectorDialog();
			}
		});
		JButton but = new JButton("OK");
		final JSlider slider = new JSlider(0, 100, Engraver.state.standby / 2);
		String[] listData = new String[]{bundle.getString("str_di"), bundle.getString("str_zhong"), bundle.getString("str_gao")};
		final JComboBox comboBox = new JComboBox(listData);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					Tu_yuan.dk_jing_du = slider.getValue();
					Runnable runnable = new Runnable() {
						public void run() {
							int rg = slider.getValue() * 2;
							int jd = comboBox.getSelectedIndex();
							if (Engraver.serialPort.messageReceived()) {
								Engraver.serialPort.ming_ling_she_zhi(rg, jd);
							}

						}
					};
					Thread thread = new Thread(runnable);
					thread.start();
				}

			}
		});
		comboBox.setSelectedIndex(Engraver.qu_jing_du());
		label1.setBounds(10, 10, 274, 16);
		slider.setBounds(10, 35, 300, 20);
		label2.setBounds(10, 60, 274, 16);
		comboBox.setBounds(10, 85, 300, 20);
		gz.setBounds(10, 110, 300, 20);
		laser1064.setBounds(10, 135, 300, 20);
		btnCommSettings.setBounds(230, 170, 70, 35);
		but.setBounds(230, 210, 70, 35);

		if ((Engraver.state.memory & 1) > 0) {
			con.add(label1);
			con.add(slider);
		}

		if ((Engraver.state.memory & 2) > 0) {
			con.add(label2);
			con.add(comboBox);
		}

		if ((Engraver.state.memory & 16) > 0) {
			con.add(gz);
		}

		if ((Engraver.state.memory & 32) > 0) {
			con.add(laser1064);
		}

		con.add(btnCommSettings);
		con.add(but);
		win.setBackground(Color.LIGHT_GRAY);
		win.setIconImage((new ImageIcon(this.getClass().getResource("/tu/she_zhi.png"))).getImage());
		win.setVisible(true);
		win.setSize(345, 250);
		win.setLocationRelativeTo((Component)null);
		win.setDefaultCloseOperation(2);
		win.setResizable(false);
		gz.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (gz.isSelected()) {
					Engraver.serialPort.ming_ling_gun_zhou();
				} else {
					Engraver.serialPort.ming_ling_guan_gun_zhou();
				}

			}
		});
		laser1064.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (laser1064.isSelected()) {
					Engraver.serialPort.ming_ling_1064();
					Engraver.diao_ke_ci_shu |= 128;
				} else {
					Engraver.serialPort.ming_ling_guan_1064();
					Engraver.diao_ke_ci_shu &= 127;
				}

			}
		});
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label1.setText(ApplicationFrame.bundle.getString("str_ruo_guang") + slider.getValue() + "%");
			}
		});
		slider.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				Tu_yuan.dk_ruo_guang = slider.getValue();
				Runnable runnable = new Runnable() {
					public void run() {
						int rg = slider.getValue() * 2;
						int jd = comboBox.getSelectedIndex();
						if (Engraver.serialPort.messageReceived()) {
							Engraver.serialPort.ming_ling_she_zhi(rg, jd);
						}

					}
				};
				Thread thread = new Thread(runnable);
				thread.start();
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Runnable runnable = new Runnable() {
					public void run() {
						int rg = slider.getValue() * 2;
						int jd = comboBox.getSelectedIndex();
						if (Engraver.serialPort.messageReceived()) {
							Engraver.serialPort.ming_ling_she_zhi(rg, jd);
						}

					}
				};
				Thread thread = new Thread(runnable);
				thread.start();
				win.dispose();
			}
		});
	}

	private void jButton4ActionPerformed(ActionEvent evt) {
		this.fan_se();
	}

	private void jButton19ActionPerformed(ActionEvent evt) {
		try {
			if (tubiao == 1) {
				Geng_xin.browse2("http://www.dkjxz.com");
			} else {
				Geng_xin.browse2("https://ftpservice.idv.tw/HANLIN/HANLIN-HLS4W-BTplus.html");
			}
		} catch (Exception var3) {
			Exception ex = var3;
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
		}

	}

	private void jCheckBox1ActionPerformed(ActionEvent evt) {
		GraphicsPanel.suo = this.jCheckBox1.isSelected();
		this.up();
	}

	private void jCheckBox2ActionPerformed(ActionEvent evt) {
		this.tian_chong();
	}

	private void jLabel9MouseClicked(MouseEvent evt) {
		if (evt.getY() > this.jLabel9.getHeight() / 2) {
			this.jTextField4.setText(String.valueOf(Integer.parseInt(this.jTextField4.getText()) - 1));
		} else {
			this.jTextField4.setText(String.valueOf(Integer.parseInt(this.jTextField4.getText()) + 1));
		}

		this.set();
	}

	private void jLabel8MouseClicked(MouseEvent evt) {
		if (evt.getY() > this.jLabel8.getHeight() / 2) {
			this.jTextField3.setText(String.valueOf(Integer.parseInt(this.jTextField3.getText()) - 1));
		} else {
			this.jTextField3.setText(String.valueOf(Integer.parseInt(this.jTextField3.getText()) + 1));
		}

		this.set();
	}

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		Engraver.diao_ke_ci_shu = this.jComboBox1.getSelectedIndex() + 1;
		if (Engraver.qu_1064()) {
			Engraver.diao_ke_ci_shu |= 128;
		} else {
			Engraver.diao_ke_ci_shu &= 127;
		}

	}

	List dian_ya_suo(List d) {
		List e = new ArrayList();
		new Dian(0, 0);

		for(int i = 0; i < d.size(); ++i) {
			Dian f = new Dian(((Dian)d.get(i)).x + Tu_yuan.zui_zhong_wjx.x, ((Dian)d.get(i)).y + Tu_yuan.zui_zhong_wjx.y);
			e.add(f);
		}

		//List d = e;
		if (e.size() < 1975) {
			return e;
		} else {
			double bi = (double)e.size() / 1975.0;
			List fh = new ArrayList();

			for(int i = 0; i < 1975; ++i) {
				fh.add((Dian)d.get((int)((double)i * bi)));
			}

			return fh;
		}
	}

	void qu_yu() {
		GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
		lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
		Rectangle rect = lu_jing2.getBounds();
		System.out.println(rect);
		AffineTransform fb;
		if (rect.width > this.hua_ban1.getWidth() || rect.height > this.hua_ban1.getHeight()) {
			double b;
			if (rect.width - this.hua_ban1.getWidth() > rect.height - this.hua_ban1.getHeight()) {
				b = (double)this.hua_ban1.getWidth() / (double)rect.width;
			} else {
				b = (double)this.hua_ban1.getHeight() / (double)rect.height;
			}

			GraphicsPanel.quan_beishu *= b;
			AffineTransform sf = AffineTransform.getScaleInstance(b, b);

			for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				fb = new AffineTransform(sf);
				fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
			}
		}

		lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
		lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
		rect = lu_jing2.getBounds();
		int x1 = rect.x + rect.width / 2;
		int y1 = rect.y + rect.height / 2;
		int x2 = this.hua_ban1.getWidth() / 2;
		int y2 = this.hua_ban1.getHeight() / 2;
		fb = AffineTransform.getTranslateInstance((double)(x2 - x1), (double)(y2 - y1));

		for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
			fb = new AffineTransform(fb);
			fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
			((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
		}

		this.up();
	}

	boolean kai_shi_tuo_ji(int shan_qu, int ban_ben, int kuan_wt, int gao_wt, int weizhi_wt, int gonglv_wt, int shendu_wt, int kuan_sl, int gao_sl, int weizhi_sl, int gonglv_sl, int shendu_sl, int dianshu_sl, int z, int s, int ci_shu, int z_sl, int s_sl) {
		byte[] sz = new byte[]{35, 0, 38, (byte)(shan_qu >> 8), (byte)shan_qu, (byte)ban_ben, (byte)(kuan_wt >> 8), (byte)kuan_wt, (byte)(gao_wt >> 8), (byte)gao_wt, (byte)(weizhi_wt >> 8), (byte)weizhi_wt, (byte)(gonglv_wt >> 8), (byte)gonglv_wt, (byte)(shendu_wt >> 8), (byte)shendu_wt, (byte)(kuan_sl >> 8), (byte)kuan_sl, (byte)(gao_sl >> 8), (byte)gao_sl, (byte)(weizhi_sl >> 24), (byte)(weizhi_sl >> 16), (byte)(weizhi_sl >> 8), (byte)weizhi_sl, (byte)(gonglv_sl >> 8), (byte)gonglv_sl, (byte)(shendu_sl >> 8), (byte)shendu_sl, (byte)(dianshu_sl >> 24), (byte)(dianshu_sl >> 16), (byte)(dianshu_sl >> 8), (byte)dianshu_sl, (byte)(z >> 8), (byte)z, (byte)(s >> 8), (byte)s, (byte)ci_shu, 0};
		return this.com_dakai ? com2.fa_song_fe(sz, 2) : false;
	}

	boolean xie_ru(byte[] m, int chao_shi) {
		return this.com_dakai ? com2.sendBytes(m, chao_shi) : false;
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

	int jiao_yan2(byte[] m) {
		int jiao = 0;

		for(int i = 0; i < m.length; ++i) {
			jiao += m[i];
		}

		if (jiao > 255) {
			jiao = ~jiao;
			++jiao;
		}

		jiao &= 255;
		return jiao;
	}

	public void diao_ke(BufferedImage tu, int tu_x, int tu_y, List shi_liang, int shi_liang_x, int shi_liang_y, int shi_liang_kuan, int shi_liang_gao) {
		int len_tu = 0;
		int kuan_b = 0;
		int len_shi_liang = 0;
		int k = 0;
		int g = 0;
		this.bao.clear();
		this.fa_wan = false;
		if (tu != null) {
			k = tu.getWidth();
			g = tu.getHeight();
			if (tu.getWidth() % 8 > 0) {
				kuan_b = tu.getWidth() / 8 + 1;
			} else {
				kuan_b = tu.getWidth() / 8;
			}

			len_tu = kuan_b * tu.getHeight();
		}

		int dian_shu;
		if (shi_liang == null) {
			dian_shu = 0;
		} else {
			dian_shu = shi_liang.size();
		}

		len_shi_liang = dian_shu * 4;
		int shan_qu = (34 + len_tu + len_shi_liang) / 4094 + 1;
		int wei_zhi_sl = 34 + len_tu;
		Engraver.serialPort.ming_ling_qing_flash(shan_qu, 1, tu_x, tu_y, k, g, 34, Engraver.gong_lv_wt, Engraver.shen_du_wt, shi_liang_x, shi_liang_y, shi_liang_kuan, shi_liang_gao, wei_zhi_sl, Engraver.gong_lv_wt, Engraver.shen_du_wt, dian_shu, Engraver.diao_ke_ci_shu);
		this.jin_yong_suo_fang = false;
		if (tu != null) {
			int[] pixels = new int[k * g];
			tu.getRGB(0, 0, k, g, pixels, 0, k);
			byte[] yi = new byte[]{-128, 64, 32, 16, 8, 4, 2, 1};

			for(int i = 0; i < g; ++i) {
				if (Engraver.ting_zhi) {
					return;
				}

				for(int j = 0; j < kuan_b; ++j) {
					if (Engraver.ting_zhi) {
						return;
					}

					byte bl = 0;

					for(int ba = 0; ba < 8; ++ba) {
						if (j * 8 + ba < k) {
							int clr = pixels[i * k + j * 8 + ba];
							clr = (clr & 16711680) >> 16;
						if (clr < 10) {
							bl |= yi[ba];
						}
						}
					}

					this.bao.add(bl);
				}
			}
		}

		for(int i = 0; i < dian_shu; ++i) {
			if (Engraver.ting_zhi) {
				return;
			}

			Dian d = (Dian)shi_liang.get(i);
			this.bao.add((byte)(d.x >> 8));
			this.bao.add((byte)d.x);
			this.bao.add((byte)(d.y >> 8));
			this.bao.add((byte)d.y);
		}

		int bao_hao = 0;
		int xu_hao = 0;
		int i = 0;
		long shi_jian = 0L;
		long shi_jian2 = 0L;
		this.fa_song(0);

		InterruptedException ex;
		try {
			Thread.sleep(1L);
		} catch (InterruptedException var28) {
			ex = var28;
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
		}

		do {
			if (Engraver.ting_zhi) {
				return;
			}

			if (Engraver.state.qu_zhuang_tai() == 2) {
				if (xu_hao != Engraver.state.serialNumber) {
					this.fa_song(Engraver.state.serialNumber);
					System.out.println(Engraver.state.serialNumber + " ===");
					xu_hao = Engraver.state.serialNumber;
					i = 0;
				} else if (i++ > 250) {
					this.fa_song(Engraver.state.serialNumber);
					i = 0;
					System.out.println("超时重发！->" + Engraver.state.serialNumber);
				}

				try {
					Thread.sleep(10L);
				} catch (InterruptedException var27) {
					ex = var27;
					Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
				}
			} else if (Engraver.state.qu_zhuang_tai() == 3) {
				try {
					Thread.sleep(1L);
				} catch (InterruptedException var26) {
					ex = var26;
					Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
				}
			}

			Engraver.jin_du = (int)((double)Engraver.state.serialNumber * 4000.0 / (double)this.bao.size() * 100.0);
		} while(!this.fa_wan);

		this.jdt_xian_shi = false;
		Engraver.serialPort.ming_ling_diao_ke();
		shi_jian = shi_jian2 - shi_jian;
		shi_jian = shi_jian / 10000L / 1000L;
	}

	void fa_song2(int bao_xu_hao) {
		List bao_m = new ArrayList();
		if (bao_xu_hao * 2000 + 2000 < this.bao.size()) {
			((List)bao_m).addAll(this.bao.subList(bao_xu_hao * 2000, bao_xu_hao * 2000 + 2000));
			((List)bao_m).add(0, -85);
			((List)bao_m).add(1, (byte)101);
			((List)bao_m).add(2, (byte)15);
			((List)bao_m).add(3, -88);
			((List)bao_m).add(4, (byte)(bao_xu_hao >> 8));
			((List)bao_m).add(5, (byte)bao_xu_hao);
			((List)bao_m).add((byte)0);
			((List)bao_m).add((byte)0);
		} else {
			int len = this.bao.size() - bao_xu_hao * 2000;
			if (len <= 0) {
				this.fa_wan = true;
				return;
			}

			bao_m = this.bao.subList(bao_xu_hao * 2000, bao_xu_hao * 2000 + len);
			len += 8;
			((List)bao_m).add(0, -85);
			((List)bao_m).add(1, (byte)101);
			((List)bao_m).add(2, (byte)(len >> 8));
			((List)bao_m).add(3, (byte)len);
			((List)bao_m).add(4, (byte)(bao_xu_hao >> 8));
			((List)bao_m).add(5, (byte)bao_xu_hao);
			((List)bao_m).add((byte)0);
			((List)bao_m).add((byte)0);
		}

		byte[] sz = new byte[((List)bao_m).size()];

		for(int i = 0; i < ((List)bao_m).size(); ++i) {
			sz[i] = (Byte)((List)bao_m).get(i);
		}

		Engraver.serialPort.ming_ling_fa_shu_ju(sz);
	}

	void fa_song(int bao_xu_hao) {
		List bao_m = new ArrayList();
		if (bao_xu_hao * 4000 + 4000 < this.bao.size()) {
			((List)bao_m).addAll(this.bao.subList(bao_xu_hao * 4000, bao_xu_hao * 4000 + 4000));
			((List)bao_m).add(0, -85);
			((List)bao_m).add(1, (byte)101);
			((List)bao_m).add(2, (byte)15);
			((List)bao_m).add(3, -88);
			((List)bao_m).add(4, (byte)(bao_xu_hao >> 8));
			((List)bao_m).add(5, (byte)bao_xu_hao);
			((List)bao_m).add((byte)0);
			((List)bao_m).add((byte)0);
		} else {
			int len = this.bao.size() - bao_xu_hao * 4000;
			if (len <= 0) {
				this.fa_wan = true;
				return;
			}

			bao_m = this.bao.subList(bao_xu_hao * 4000, bao_xu_hao * 4000 + len);
			len += 8;
			((List)bao_m).add(0, -85);
			((List)bao_m).add(1, (byte)101);
			((List)bao_m).add(2, (byte)(len >> 8));
			((List)bao_m).add(3, (byte)len);
			((List)bao_m).add(4, (byte)(bao_xu_hao >> 8));
			((List)bao_m).add(5, (byte)bao_xu_hao);
			((List)bao_m).add((byte)0);
			((List)bao_m).add((byte)0);
		}

		byte[] sz = new byte[((List)bao_m).size()];

		for(int i = 0; i < ((List)bao_m).size(); ++i) {
			//sz[i] = (Byte)((List)bao_m).get(i);
			sz[i] = ((Number)((List)bao_m).get(i)).byteValue();
		}

		Engraver.serialPort.ming_ling_fa_shu_ju(sz);
	}

	void tuo_ji() {
		int i = 0;
		int j = 0;
		int jishu = 0;
		int k = 0;
		int g = 0;
		int k_sl = 0;
		int g_sl = 0;
		int wz_sl = 1;
		int k_len = 0;
		boolean wt_ = false;
		boolean sl_ = false;
		boolean cuo = true;
		byte bl = 1;
		byte[] bao = null;
		int len = 1;
		Tu_yuan.hui_fu();
		BufferedImage tu_diaoke = Tu_yuan.qu_tu(GraphicsPanel.ty_shuzu);
		List dian = Tu_yuan.qu_dian(GraphicsPanel.ty_shuzu);
		Tu_yuan.hui_fu_xian_chang();
		if (tu_diaoke != null || dian != null) {
			this.diao_ke(tu_diaoke, Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2, dian, Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2, Tu_yuan.zui_zhong_wjx.width, Tu_yuan.zui_zhong_wjx.height);
		}
	}

	void tuo_ji2() {
		int i = 1;
		int j = 1;
		int jishu = 1;
		int k = 0;
		int g = 0;
		int k_sl = 0;
		int g_sl = 0;
		int wz_sl = 1;
		int k_len = 0;
		boolean wt_ = false;
		boolean sl_ = false;
		boolean cuo = true;
		byte bl = 1;
		byte[] bao = null;
		int len = 0;
		Tu_yuan.hui_fu();
		BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(GraphicsPanel.ty_shuzu);
		List dian = Tu_yuan.qu_dian(GraphicsPanel.ty_shuzu);
		Tu_yuan.hui_fu_xian_chang();
		if (tu_diaoke2 == null && dian == null) {
			this.jButton15.setEnabled(true);
		} else {
			jishu = 0;
			if (tu_diaoke2 != null) {
				g = tu_diaoke2.getHeight();
				k = tu_diaoke2.getWidth();
				if (tu_diaoke2.getWidth() % 8 > 0) {
					bao = new byte[tu_diaoke2.getWidth() / 8 + 1];
					k_len = tu_diaoke2.getWidth() / 8 + 1;
				} else {
					bao = new byte[tu_diaoke2.getWidth() / 8];
					k_len = tu_diaoke2.getWidth() / 8;
				}

				wz_sl = 33 + g * bao.length;
				wt_ = true;
				len = bao.length;
			} else {
				wz_sl = 33;
			}

			if (dian != null) {
				k_sl = Tu_yuan.zui_zhong_wjx.width;
				g_sl = Tu_yuan.zui_zhong_wjx.height;
				sl_ = true;
			} else {
				dian = new ArrayList();
			}

			int z = Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2;
			int s = Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2;
			int z_sl = Tu_yuan.shi_liang_wjx.x + Tu_yuan.shi_liang_wjx.width / 2;
			int s_sl = Tu_yuan.shi_liang_wjx.y + Tu_yuan.shi_liang_wjx.height / 2;
			bao = new byte[wz_sl - 33 + ((List)dian).size() * 4];
			this.kai_shi_tuo_ji((33 + len * g + ((List)dian).size() * 4) / 4094 + 1, 1, k, g, 33, this.jSlider1.getValue() * 10, 100 - this.jSlider2.getValue(), k_sl, g_sl, wz_sl, this.jSlider1.getValue() * 10, 100 - this.jSlider2.getValue(), ((List)dian).size(), z, s, this.jComboBox1.getSelectedIndex() + 1, z_sl, s_sl);

			InterruptedException ex;
			try {
				Thread.sleep((long)(70 * ((33 + len * g + ((List)dian).size() * 4) / 4094 + 1)));
			} catch (InterruptedException var32) {
				ex = var32;
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
			}

			this.xie_ru(new byte[]{10, 0, 4, 0}, 1);

			try {
				Thread.sleep(500L);
			} catch (InterruptedException var31) {
				ex = var31;
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
			}

			this.xie_ru(new byte[]{10, 0, 4, 0}, 1);

			try {
				Thread.sleep(500L);
			} catch (InterruptedException var30) {
				ex = var30;
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
			}

			byte[] bao2;
			boolean chong_fa;
			if (wt_) {
				int[] pixels = new int[k * g];
				tu_diaoke2.getRGB(0, 0, k, g, pixels, 0, k);
				bao2 = new byte[]{-128, 64, 32, 16, 8, 4, 2, 1};

				for(i = 0; i < g; ++i) {
					for(j = 0; j < k_len; ++j) {
						chong_fa = false;
						bl = 0;

						for(int ba = 0; ba < 8; ++ba) {
							if (i * k + j * 8 + ba < pixels.length && j * 8 + ba <= k) {
								int clr = pixels[i * k + j * 8 + ba];
								clr = (clr & 16711680) >> 16;
							if (clr < 10) {
								bl |= bao2[ba];
							}
							}
						}

						bao[jishu] = bl;
						++jishu;
					}
				}
			}

			if (sl_) {
				for(j = 0; j < ((List)dian).size(); ++j) {
					bao[jishu++] = (byte)((Dian)((List)dian).get(j)).x;
					bao[jishu++] = (byte)(((Dian)((List)dian).get(j)).x >> 8);
					bao[jishu++] = (byte)((Dian)((List)dian).get(j)).y;
					bao[jishu++] = (byte)(((Dian)((List)dian).get(j)).y >> 8);
				}
			}

			int bao_chicuo = 1900;
			bao2 = new byte[bao_chicuo + 4];

			for(i = 0; i < bao.length / bao_chicuo; ++i) {
				for(j = 0; j < bao_chicuo; ++j) {
					bao2[j + 3] = bao[i * bao_chicuo + j];
				}

				chong_fa = false;

				do {
					bao2[0] = 34;
					bao2[1] = (byte)(bao2.length >> 8);
					bao2[2] = (byte)bao2.length;
					bao2[bao2.length - 1] = this.jiao_yan(bao2);
					chong_fa = !this.xie_ru(bao2, 2);
				} while(chong_fa);

				this.jdt.setVisible(true);
				this.jdt.setValue((int)((float)i / (float)(bao.length / bao_chicuo) * 100.0F));
			}

			bao2 = new byte[1904];
			int ba = bao.length % bao_chicuo;
			if (ba > 0) {
				for(i = 0; i < ba; ++i) {
					bao2[i + 3] = bao[bao.length / bao_chicuo * bao_chicuo + i];
				}

				for(i = 0; i < 1900 - bao.length % bao_chicuo; ++i) {
					bao2[bao.length % bao_chicuo + 3 + i] = -1;
				}

				chong_fa = false;

				do {
					bao2[0] = 34;
					bao2[1] = (byte)(bao2.length >> 8);
					bao2[2] = (byte)bao2.length;
					bao2[bao2.length - 1] = this.jiao_yan(bao2);
					chong_fa = !this.xie_ru(bao2, 2);
				} while(chong_fa);
			}

			try {
				Thread.sleep(200L);
			} catch (InterruptedException var29) {
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var29);
			}

			this.xie_ru(new byte[]{10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 1);

			try {
				Thread.sleep(200L);
			} catch (InterruptedException var28) {
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var28);
			}

			this.xie_ru(new byte[]{36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 1);

			try {
				Thread.sleep(500L);
			} catch (InterruptedException var27) {
				Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var27);
			}

			kai_shi2 = true;
			if (com2 != null) {
				com2.jie_shou_ji_shu = 0;
				com2.jie_shou_lei_xing = 3;
				com2.jie_shou_huan_cun.clear();
			}

			this.jdt.setVisible(false);
			this.jButton15.setEnabled(true);
		}
	}

	void UI_() {
		if ((Engraver.state.memory & 51) == 0) {
			this.jButton22.setVisible(false);
			this.bq_bao_cun1.setVisible(false);
		} else {
			this.jButton22.setVisible(true);
			this.bq_bao_cun1.setVisible(true);
		}

		if ((Engraver.state.memory & 4) > 0) {
			this.jButton21.setVisible(true);
		} else {
			this.jButton21.setVisible(false);
		}

		if (Engraver.state.model == 1) {
			this.jButton20.setVisible(false);
			this.bq_tu_xing.setVisible(false);
		} else {
			this.jButton20.setVisible(true);
			this.bq_tu_xing.setVisible(true);
		}

	}

	boolean du_banben() {
		final Object suo_ = new Object();
		synchronized(this.suo_fhm) {
			this.fan_hui_ma = false;
		}

		Runnable runnable2 = new Runnable() {
			public void run() {
				synchronized(suo_) {
					for(int i = 200; i > 0; --i) {
						synchronized(ApplicationFrame.this.suo_fhm) {
							if (ApplicationFrame.this.fan_hui_ma) {
								if (ApplicationFrame.com2.fan_hui_shu.length != 3) {
									ApplicationFrame.this.fan_hui_ma = false;
								}
								break;
							}
						}

						try {
							Thread.sleep(10L);
						} catch (InterruptedException var6) {
							InterruptedException ex = var6;
							Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
						}
					}

				}
			}
		};
		Thread thread2 = new Thread(runnable2);
		thread2.start();

		try {
			Thread.sleep(100L);
		} catch (InterruptedException var7) {
			InterruptedException ex = var7;
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
		}

		synchronized(suo_) {
			return this.fan_hui_ma;
		}
	}

	public static void main(String[] args) {
		boolean nimbusFound = false;
		try {
			UIManager.LookAndFeelInfo[] var13 = UIManager.getInstalledLookAndFeels();
			int var2 = var13.length;

			for(int var3 = 0; var3 < var2; ++var3) {
				UIManager.LookAndFeelInfo info = var13[var3];
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					nimbusFound = true;
					break;
				}
			}
		} catch (ClassNotFoundException var6) {
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var6);
		} catch (InstantiationException var7) {
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var7);
		} catch (IllegalAccessException var8) {
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, var8);
		} catch (UnsupportedLookAndFeelException var9) {
			UnsupportedLookAndFeelException ex = var9;
			Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
		}

		//This was just odd. Breaks layout on linux
		if (!nimbusFound) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception var5) {
				var5.printStackTrace();
			}
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				(new ApplicationFrame()).setVisible(true);
			}
		});
	}

	void set() {
		double x;
		int i;
		if (this.textFieldX.getText() != "") {
			x = (double)Integer.valueOf(this.textFieldX.getText());
			x -= (double)this.hua_ban1.x;
			x /= Engraver.state.scaleFactor;
			x *= GraphicsPanel.quan_beishu;

			for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi((int)x, 0);
				}
			}

			this.up();
		}

		if (this.jTextFieldY.getText() != "") {
			x = (double)Integer.valueOf(this.jTextFieldY.getText());
			x -= (double)this.hua_ban1.y;
			x /= Engraver.state.scaleFactor;
			x *= GraphicsPanel.quan_beishu;

			for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(0, (int)x);
				}
			}

			this.up();
		}

		Rectangle rect;
		if (this.jTextField3.getText() != "") {
			x = (double)Integer.valueOf(this.jTextField3.getText());
			x /= (double)this.hua_ban1.ww;
			rect = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);

			for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
					if (GraphicsPanel.suo) {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(x, x);
					} else {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(x, 1.0);
					}

					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
				}
			}

			this.up();
		}

		if (this.jTextField4.getText() != "") {
			x = (double)Integer.valueOf(this.jTextField4.getText());
			x /= (double)this.hua_ban1.hh;
			rect = Tu_yuan.qu_jv_xing(GraphicsPanel.ty_shuzu);

			for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
					if (GraphicsPanel.suo) {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(x, x);
					} else {
						((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(1.0, x);
					}

					((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
				}
			}

			this.up();
		}

	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		int i;
		if (e.isControlDown() && e.getKeyCode() == 67) {
			this.ty_fu_zhi.clear();

			for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
					this.ty_fu_zhi.add(Tu_yuan.fu_zhi((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)));
				}
			}

			this.fu_zhi = true;
		} else if (e.isControlDown() && e.getKeyCode() == 86) {
			if (this.fu_zhi) {
				for(i = 0; i < this.ty_fu_zhi.size(); ++i) {
					GraphicsPanel.ty_shuzu.add(Tu_yuan.fu_zhi((Tu_yuan)this.ty_fu_zhi.get(i)));
					((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).chu_li();
				}

				this.up();
				Che_xiao.tian_jia();
			}
		} else if (e.isControlDown() && e.getKeyCode() == 65) {
			for(i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = true;
			}

			this.up();
		} else if (e.isControlDown() && e.getKeyCode() == 90) {
			Che_xiao.che_xiao();
			this.up();
		} else if (e.isControlDown() && e.getKeyCode() == 88) {
			for(i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
				((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = true;
			}

			Che_xiao.chong_zuo();
			this.up();
		}

		if (c == 10) {
			this.set();
		} else if (c == 127) {
			List sz = new ArrayList();

			for(int j = 0; j < GraphicsPanel.ty_shuzu.size(); ++j) {
				if (!((Tu_yuan)GraphicsPanel.ty_shuzu.get(j)).xuan_zhong) {
					sz.add((Tu_yuan)GraphicsPanel.ty_shuzu.get(j));
				}
			}

			GraphicsPanel.ty_shuzu = sz;
			this.up();
			Che_xiao.tian_jia();
		}

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
