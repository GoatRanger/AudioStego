package com.nokia.maps;

import com.alipay.sdk.h.a;
import com.nokia.maps.annotation.Hybrid;
import com.nokia.maps.annotation.HybridNative;
import dji.pilot.fpv.d.c$i;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.publics.b.a$j;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

@Hybrid
public class MapPackageSelection extends BaseNativeObject {
    private static final String a = MapPackageSelection.class.getSimpleName();
    private static final HashSet<Integer> b = new HashSet();
    private static HashMap<String, String> c;
    private HashSet<Short> d;

    private native void destroyNative();

    private native short getDataGroupCount();

    private native String[] getDataGroupNames();

    private native short getDatagroupIdFromIndex(int i);

    private native short getLanguageCode(short s);

    private native short getLanguageCount();

    private native long[] getPackageInstallSizes(int i);

    private native boolean isPackageDataGroupInstalled(int i, int i2);

    private native boolean selectPackageDataGroup(int i, int i2);

    private native boolean selectPackageDataGroupById(int i, short s);

    private native void setLanguage(short s);

    private native boolean unselectPackageDataGroup(int i, int i2);

    private native boolean unselectPackageDataGroupById(int i, short s);

    public native int getPackageChildrenCount(int i);

    public native int[] getPackageChildrenIndices(int i);

    public native int getPackageCount();

    native short getPackageDataGroupSelectionStatus(int i, int i2);

    public native int getPackageIdFromIndex(int i);

    public native String[] getPackageNames();

    native boolean hasChildPackageInstalled(int i);

    static {
        b.add(Integer.valueOf(0));
        b.add(Integer.valueOf(1));
        b.add(Integer.valueOf(12));
        b.add(Integer.valueOf(17));
        b.add(Integer.valueOf(18));
        b.add(Integer.valueOf(20));
        b.add(Integer.valueOf(21));
        b.add(Integer.valueOf(22));
        b.add(Integer.valueOf(49));
        b.add(Integer.valueOf(50));
        b.add(Integer.valueOf(51));
        b.add(Integer.valueOf(52));
        b.add(Integer.valueOf(53));
        c = new HashMap();
        c = new HashMap();
        c.put("en", "001");
        c.put(a$j.f, "002");
        c.put("de", "003");
        c.put("es", "004");
        c.put("it", "005");
        c.put(a.h, "006");
        c.put("da", "007");
        c.put(c$i.c, "008");
        c.put("fi", "009");
        c.put("pt", "013");
        c.put("tr", "014");
        c.put("is", "015");
        c.put("ru", "016");
        c.put("hu", "017");
        c.put("nl", "018");
        c.put("cs", "025");
        c.put("sk", "026");
        c.put("pl", "027");
        c.put("sl", "028");
        c.put("zh", "031");
        c.put("ja", "032");
        c.put("th", "033");
        c.put("af", "034");
        c.put("al", "035");
        c.put("am", "036");
        c.put("ar", "037");
        c.put("hy", "038");
        c.put("tl", "039");
        c.put("be", "040");
        c.put("bn", "041");
        c.put("bg", "042");
        c.put(d.y, "043");
        c.put("ca", "044");
        c.put("hr", "045");
        c.put("et", "049");
        c.put("fa", "050");
        c.put("gd", "052");
        c.put("ka", "053");
        c.put("el", "054");
        c.put("gu", "056");
        c.put("he", "057");
        c.put("hi", "058");
        c.put("id", "059");
        c.put("ga", "060");
        c.put("kn", "062");
        c.put("kk", "063");
        c.put("ko", "065");
        c.put("lo", "066");
        c.put("lv", "067");
        c.put("lt", "068");
        c.put("mk", "069");
        c.put("ms", "070");
        c.put("ml", "071");
        c.put("mr", "072");
        c.put("mo", "073");
        c.put("mn", "074");
        c.put("nn", "075");
        c.put("pa", "077");
        c.put("ro", "078");
        c.put("sr", "079");
        c.put("si", "080");
        c.put("so", "081");
        c.put("sw", "084");
        c.put("ta", "087");
        c.put("te", "088");
        c.put("bo", "089");
        c.put("ti", "090");
        c.put("tk", "092");
        c.put("uk", "093");
        c.put("ur", "094");
        c.put("vi", "096");
        c.put("cy", "097");
        c.put("zu", "098");
        c.put("st", "101");
        c.put("eu", "102");
        c.put("ga", "103");
        c.put("ms", "326");
        c.put("en_GB", "001");
        c.put("en_US", "010");
        c.put("fr_CH", "011");
        c.put("fr_BE", "021");
        c.put("de_CH", "012");
        c.put("zh_TW", "029");
        c.put("zh_HK", "030");
        c.put("zh_CN", "031");
        c.put("en_CA", "046");
        c.put("en_ZA", "048");
        c.put("fr_CA", "051");
        c.put("el_CY", "055");
        c.put("it_CH", "061");
        c.put("pt_BR", "076");
        c.put("es_MX", "083");
        c.put("es_419", "083");
        c.put("sv_FI", "085");
        c.put("tr_CY", "091");
        c.put("en_TW", "157");
        c.put("en_HK", "158");
        c.put("en_CN", "159");
        c.put("en_JP", "160");
        c.put("en_TH", "161");
    }

    private MapPackageSelection() {
        this(0);
    }

    @HybridNative
    private MapPackageSelection(int i) {
        this.d = new HashSet();
        this.nativeptr = i;
        a();
        b();
    }

    protected void finalize() {
        destroyNative();
    }

    public long a(int i) {
        long[] packageInstallSizes = getPackageInstallSizes(i);
        Iterator it = this.d.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = packageInstallSizes[((Short) it.next()).shortValue()] + j;
        }
        return j;
    }

    public boolean b(int i) {
        boolean z = true;
        if (i < 0) {
            bj.b(a, "Invalid package id passed: %d. No selection will occur.", new Object[]{Integer.valueOf(i)});
            return false;
        }
        short dataGroupCount = getDataGroupCount();
        int h = h(i);
        if (h < 0) {
            bj.b(a, "Invalid package id passed: %d. No selection will occur.", new Object[]{Integer.valueOf(i)});
            return false;
        }
        for (short s = (short) 0; s < dataGroupCount; s = (short) (s + 1)) {
            if (!this.d.contains(Short.valueOf(s))) {
                unselectPackageDataGroup(h, s);
            } else if (!selectPackageDataGroup(h, s)) {
                z = false;
            }
        }
        return z;
    }

    public boolean c(int i) {
        if (i < 0) {
            bj.b(a, "Invalid package id passed: %d. Unselection will not occur.", new Object[]{Integer.valueOf(i)});
            return false;
        }
        Iterator it = this.d.iterator();
        boolean z = true;
        while (it.hasNext()) {
            boolean z2;
            if (unselectPackageDataGroupById(i, getDatagroupIdFromIndex(((Short) it.next()).shortValue()))) {
                z2 = z;
            } else {
                z2 = false;
            }
            z = z2;
        }
        return z;
    }

    boolean d(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (!a(i, ((Short) it.next()).shortValue())) {
                return false;
            }
        }
        return true;
    }

    boolean e(int i) {
        Iterator it = this.d.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int i3;
            if (isPackageDataGroupInstalled(i, ((Short) it.next()).shortValue())) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i2 = i3;
        }
        if (i2 <= 0 || i2 >= this.d.size()) {
            return false;
        }
        return true;
    }

    public boolean f(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (!isPackageDataGroupInstalled(i, ((Short) it.next()).shortValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean a(int i, int i2) {
        return (getPackageDataGroupSelectionStatus(i, i2) & 1) > 0;
    }

    private boolean b(int i, int i2) {
        return (getPackageDataGroupSelectionStatus(i, i2) & 2) > 0;
    }

    boolean g(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (!b(i, ((Short) it.next()).shortValue())) {
                return false;
            }
        }
        return true;
    }

    private void b() {
        short dataGroupCount = getDataGroupCount();
        for (short s = (short) 0; s < dataGroupCount; s = (short) (s + 1)) {
            if (b.contains(Integer.valueOf(getDatagroupIdFromIndex(s)))) {
                this.d.add(Short.valueOf(s));
            }
        }
    }

    void a() {
        a(c());
    }

    private String c() {
        Locale locale = Locale.getDefault();
        String locale2 = locale.toString();
        String language = locale.getLanguage();
        String str = (String) c.get(locale2);
        if (str != null) {
            return str;
        }
        str = (String) c.get(language);
        if (str == null) {
            return "010";
        }
        return str;
    }

    void a(String str) {
        short languageCount = getLanguageCount();
        Map hashMap = new HashMap();
        for (short s = (short) 0; s < languageCount; s = (short) (s + 1)) {
            hashMap.put(Short.valueOf(getLanguageCode(s)), Short.valueOf(s));
        }
        if (hashMap.containsKey(Short.valueOf(str))) {
            setLanguage(((Short) hashMap.get(Short.valueOf(str))).shortValue());
        } else {
            setLanguage(((Short) hashMap.get(Short.valueOf("010"))).shortValue());
        }
    }

    private int h(int i) {
        int packageCount = getPackageCount();
        for (int i2 = 0; i2 < packageCount; i2++) {
            if (i == getPackageIdFromIndex(i2)) {
                return i2;
            }
        }
        return -1;
    }
}
