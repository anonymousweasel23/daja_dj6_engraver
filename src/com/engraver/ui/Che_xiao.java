package com.engraver.ui;

import java.util.ArrayList;
import java.util.List;

public class Che_xiao {
   static List ty_shuzu_cx = new ArrayList();
   static int dang_qian = 0;
   static int bu_shu = 20;

   public static void tian_jia() {
      if (dang_qian < bu_shu) {
         if (ty_shuzu_cx.size() > dang_qian) {
            for(int i = 0; i < ty_shuzu_cx.size() - dang_qian + 1; ++i) {
               ty_shuzu_cx.remove(ty_shuzu_cx.size() - 1);
            }
         }

         ty_shuzu_cx.add(Tu_yuan.fu_zhi(GraphicsPanel.ty_shuzu));
         dang_qian = ty_shuzu_cx.size();
      } else {
         ty_shuzu_cx.remove(0);
         ty_shuzu_cx.add(Tu_yuan.fu_zhi(GraphicsPanel.ty_shuzu));
         dang_qian = ty_shuzu_cx.size();
      }

   }

   public static void che_xiao() {
      if (dang_qian > 1) {
         GraphicsPanel.ty_shuzu = Tu_yuan.fu_zhi((List)ty_shuzu_cx.get(dang_qian - 1 - 1));
         --dang_qian;
      }

   }

   public static void chong_zuo() {
      if (dang_qian < bu_shu) {
         GraphicsPanel.ty_shuzu = Tu_yuan.fu_zhi((List)ty_shuzu_cx.get(dang_qian + 1 - 1));
         ++dang_qian;
      }

   }
}
