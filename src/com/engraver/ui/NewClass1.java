package com.engraver.ui;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class NewClass1 {
   Frame fr = new Frame("MenuDemo");
   MenuBar mb = new MenuBar();
   Menu m1 = new Menu("文件");
   MenuItem open = new MenuItem("打开");
   MenuItem close = new MenuItem("关闭");
   MenuItem exit = new MenuItem("退出");

   NewClass1() {
      this.fr.setSize(350, 200);
      this.m1.add(this.open);
      this.m1.add(this.close);
      this.m1.addSeparator();
      this.m1.add(this.exit);
      this.mb.add(this.m1);
      this.fr.setMenuBar(this.mb);
      this.fr.setVisible(true);
   }

   public static void main(String[] args) {
      new NewClass1();
   }
}
