package com.engraver.ui;

import java.awt.Font;
import java.io.Serializable;

public class liu_shui_hao implements Serializable {
   public int kai_shi = 0;
   public int jie_shu = 0;
   public int dang_qian = 0;
   public String qian_zhui = "";
   public String hou_zhui = "";
   public Font zi_ti = null;
   public boolean shu_xiang = false;
   public boolean shi_liang = false;

   public liu_shui_hao(int ks, int js, int dq, String qz, String hz, Font zt, boolean sx, boolean sl) {
      this.kai_shi = ks;
      this.jie_shu = js;
      this.dang_qian = dq;
      this.qian_zhui = qz;
      this.hou_zhui = hz;
      this.zi_ti = zt;
      this.shu_xiang = sx;
      this.shi_liang = sl;
   }

   public Tu_yuan sheng_cheng() {
      String dang_qian2 = "";
      Tu_yuan fh = null;
      int chang = String.valueOf(this.jie_shu).length();
      chang -= String.valueOf(this.dang_qian).length();
      if (chang > 0) {
         for(int i = 0; i < chang; ++i) {
            dang_qian2 = dang_qian2 + "0";
         }
      }

      dang_qian2 = dang_qian2 + this.dang_qian;
      if (this.shu_xiang) {
         fh = Tu_yuan.chuang_jian_wen_zi_shu(this.qian_zhui + dang_qian2 + this.hou_zhui, TextEntryDialog.ziti, 0, this.shi_liang);
      } else {
         fh = Tu_yuan.chuang_jian_wen_zi(this.qian_zhui + dang_qian2 + this.hou_zhui, TextEntryDialog.ziti, this.shi_liang);
      }

      ++this.dang_qian;
      fh.lsh = this;
      return fh;
   }
}
