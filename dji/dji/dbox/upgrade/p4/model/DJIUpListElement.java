package dji.dbox.upgrade.p4.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DJIUpListElement {
    public DJIUpCfgModel cfgModel;
    public String flow;
    public boolean isAllow;
    public String product_version;
    public ReleaseNote release_note;

    public static class ReleaseNote {
        public String en;
        public String ja;
        public String zh_cn;
        public String zh_tw;

        public String get() {
            String str = "";
            if (this.ja != null) {
                str = this.ja;
            }
            if (this.zh_cn != null) {
                str = this.zh_cn;
            }
            if (this.zh_tw != null) {
                str = this.zh_tw;
            }
            if (this.en != null) {
                str = this.en;
            }
            try {
                str = URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public boolean isDeprecated() {
        return this.flow != null && this.flow.equals("deprecated");
    }
}
