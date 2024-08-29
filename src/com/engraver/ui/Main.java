package com.engraver.ui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {
   public static void main(String[] args) {
      JLabel label = new JLabel("레이저 조각 기\\u000d\\u000aUnicode!");
      label.setToolTipText("This is Russian");
      JOptionPane.showMessageDialog((Component)null, label);
   }
}
