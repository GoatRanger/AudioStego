package dji.pilot2.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.app.NotificationManagerCompat;
import com.alipay.sdk.j.i;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.template.SingleTemplateJson.Filter;
import dji.pilot2.template.SingleTemplateJson.TextInfo;
import dji.pilot2.utils.n;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class c extends d {
    public String a;
    public List<Integer> b = new ArrayList();
    public List<Integer> c = new ArrayList();
    public List<Integer> d = new ArrayList();
    public int e;
    public List<Integer> f = new ArrayList();
    public List<Integer> g = new ArrayList();
    public List<Integer> h = new ArrayList();
    public int i;
    public List<b> j = new ArrayList();
    public String k;
    public String l;
    public String m;
    public String n;
    public int o;
    public Bitmap p;
    public int q = 24;
    public long r;
    public SingleTemplateJson s;
    public List<DealedFilterConf> t;
    private Bitmap u;
    private int v;

    public c(Context context, InputStream inputStream) throws IOException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            str = str + readLine;
        }
        bufferedReader.close();
        this.s = (SingleTemplateJson) h.b(str, SingleTemplateJson.class);
        if (this.s == null) {
            throw new IOException("Cannot parse configuration file");
        }
        this.a = this.s.name;
        this.k = this.s.desc;
        this.v = Integer.parseInt(this.s.id);
        str = n.p(context) + this.s.music_url;
        this.l = str;
        this.m = str;
        this.n = str;
        if (this.n == null || new File(this.n).exists()) {
            this.q = 24;
            this.r = this.s.update_ts;
            String[] split = this.s.ranges.split(i.b);
            this.i = split.length;
            for (String split2 : split) {
                String[] split3 = split2.split("-");
                int parseFrameToTime = parseFrameToTime(split3[1], this.q);
                int parseFrameToTime2 = parseFrameToTime(split3[0], this.q);
                this.f.add(Integer.valueOf(parseFrameToTime2));
                this.g.add(Integer.valueOf(parseFrameToTime));
                this.h.add(Integer.valueOf(parseFrameToTime - parseFrameToTime2));
            }
            str = n.q(context) + this.s.photo_url;
            if (str == null || new File(str).exists()) {
                this.p = getAdjustThumbnail(str, Config.RGB_565);
                if (this.p != null && this.p.getWidth() > 320) {
                    Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.p, 320, (this.p.getHeight() * 320) / this.p.getWidth(), false);
                    if (!(this.p == null || this.p.isRecycled())) {
                        this.p.recycle();
                    }
                    this.p = createScaledBitmap;
                }
                this.o = ((Integer) this.g.get(this.g.size() - 1)).intValue();
                a();
                return;
            }
            throw new FileNotFoundException("SingleTemplate thumbnail file not found");
        }
        throw new FileNotFoundException("SingleTemplate music file not found");
    }

    public int a(int i, int i2, List<Integer> list, List<Integer> list2) {
        int i3 = i2 - i;
        if (list == null || list2 == null) {
            return 1;
        }
        int i4;
        List arrayList = new ArrayList();
        int size = this.f.size() - 2;
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            int intValue = ((Integer) this.g.get(i5)).intValue();
            int intValue2 = ((Integer) this.g.get(i5 + 1)).intValue();
            if (intValue2 >= i3) {
                break;
            }
            b bVar = new b();
            bVar.a = intValue;
            Random random = new Random();
            if (random.nextInt(99) % 100 > 40) {
                intValue = ((Integer) this.h.get(i5 + 1)).intValue();
                bVar.b = ((Integer) this.g.get(i5)).intValue() - ((random.nextInt(1) + 1) * 1000);
                bVar.c = intValue;
                arrayList.add(bVar);
                i4 = intValue2;
            } else {
                intValue = ((Integer) this.g.get(i5)).intValue();
                i4 = (random.nextInt(1) + 1) * 1000;
                i6 = (intValue + ((Integer) this.h.get(i5 + 1)).intValue()) + i4 >= this.o ? NotificationManagerCompat.IMPORTANCE_UNSPECIFIED : i4;
                if (i3 - ((Integer) this.g.get(i5 + 1)).intValue() < i6) {
                    i6 = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                }
                bVar.b = intValue + i6;
                bVar.c = ((Integer) this.h.get(i5 + 1)).intValue();
                arrayList.add(bVar);
                i4 = intValue2;
            }
            i5++;
            i6 = i4;
        }
        if (size < 0) {
            if (i3 < ((Integer) this.g.get(i5)).intValue()) {
                i4 = i3;
            } else {
                i4 = ((Integer) this.g.get(i5)).intValue();
            }
            list.add(Integer.valueOf(i));
            list2.add(Integer.valueOf(i4 + i));
            return 0;
        } else if (size == 0) {
            if (i3 > ((Integer) this.g.get(i5)).intValue() && i3 > ((Integer) this.g.get(i5 + 1)).intValue()) {
                i3 = ((Integer) this.g.get(i5 + 1)).intValue();
            }
            list.add(Integer.valueOf(0));
            list2.add(Integer.valueOf(i3));
            return 0;
        } else {
            b bVar2 = new b();
            bVar2.a = i6;
            if (i5 == size) {
                if (i3 > ((Integer) this.g.get(i5 + 1)).intValue()) {
                    i3 = ((Integer) this.g.get(i5 + 1)).intValue();
                }
                bVar2.b = ((Integer) this.g.get(i5)).intValue();
                bVar2.c = i3 - bVar2.b;
            } else {
                bVar2.b = ((Integer) this.g.get(i5)).intValue();
                bVar2.c = i3 - bVar2.b;
            }
            arrayList.add(bVar2);
            for (intValue2 = 0; intValue2 < arrayList.size(); intValue2++) {
                b bVar3 = (b) arrayList.get(intValue2);
                if (intValue2 == 0) {
                    list.add(Integer.valueOf(i));
                    list2.add(Integer.valueOf(((Integer) this.g.get(0)).intValue() + i));
                }
                list.add(Integer.valueOf(bVar3.b + i));
                list2.add(Integer.valueOf((bVar3.c + bVar3.b) + i));
            }
            return 0;
        }
    }

    public void a() {
        this.e = 1;
        this.b.add(Integer.valueOf(0));
        this.c.add(Integer.valueOf(this.o));
        this.d.add(Integer.valueOf(this.o));
    }

    public TextInfo a(String str) {
        List list = this.s.TextArray;
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(((TextInfo) list.get(i)).text)) {
                return (TextInfo) list.get(i);
            }
        }
        return null;
    }

    protected int b(String str) {
        if (str.contains("NO")) {
            return 0;
        }
        if (str.contains("EaseInQuad")) {
            return 1;
        }
        if (str.contains("EaseOutQuad")) {
            return 2;
        }
        if (str.contains("EaseInOutQuad")) {
            return 3;
        }
        if (str.contains("EaseInCubic")) {
            return 4;
        }
        if (str.contains("EaseOutCubic")) {
            return 5;
        }
        if (str.contains("EaseInOutCubic")) {
            return 6;
        }
        if (str.contains("EaseInQuart")) {
            return 7;
        }
        if (str.contains("EaseOutQuart")) {
            return 8;
        }
        if (str.contains("EaseInOutQuart")) {
            return 9;
        }
        if (str.contains("EaseInQuint")) {
            return 10;
        }
        if (str.contains("EaseOutQuint")) {
            return 11;
        }
        if (str.contains("EaseInOutQuint")) {
            return 12;
        }
        if (str.contains("EaseInSine")) {
            return 13;
        }
        if (str.contains("EaseOutSine")) {
            return 14;
        }
        if (str.contains("EaseInOutSine")) {
            return 15;
        }
        if (str.contains("EaseInExpo")) {
            return 16;
        }
        if (str.contains("EaseOutExpo")) {
            return 17;
        }
        if (str.contains("EaseInOutExpo")) {
            return 18;
        }
        if (str.contains("EaseInCirc")) {
            return 19;
        }
        if (str.contains("EaseOutCirc")) {
            return 20;
        }
        if (str.contains("EaseInOutCirc")) {
            return 21;
        }
        if (str.contains("EaseInElastic")) {
            return 22;
        }
        if (str.contains("EaseOutElastic")) {
            return 23;
        }
        if (str.contains("EaseInOutElastic")) {
            return 24;
        }
        if (str.contains("EaseInBack")) {
            return 25;
        }
        if (str.contains("EaseOutBack")) {
            return 26;
        }
        if (str.contains("EaseInOutBack")) {
            return 27;
        }
        if (str.contains("EaseInBounce")) {
            return 28;
        }
        if (str.contains("EaseOutBounce")) {
            return 29;
        }
        if (str.contains("EaseInOutBounce")) {
            return 30;
        }
        return 2;
    }

    public List<DealedFilterConf> b() {
        if (this.t != null) {
            return this.t;
        }
        int i;
        DealedFilterConf dealedFilterConf;
        List arrayList = new ArrayList();
        if (this.s.Filters != null) {
            for (i = 0; i < this.s.Filters.size(); i++) {
                dealedFilterConf = new DealedFilterConf();
                Filter filter = (Filter) this.s.Filters.get(i);
                dealedFilterConf.begin = filter.begin;
                dealedFilterConf.duration = filter.duration;
                dealedFilterConf.end = filter.end;
                dealedFilterConf.start = filter.start;
                if ("GPUImageBrightnessFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 0;
                }
                if ("GPUImageTransformFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 1;
                }
                if ("GPUImageGaussianBlurFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 2;
                }
                if ("GPUImageMotionBlurFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 3;
                }
                if ("GPUImageSaturationFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 5;
                }
                if ("GPUImageContrastFilter".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 6;
                }
                if ("GImageAlpha".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 8;
                }
                if ("GBlackBorder".equals(filter.filteranme)) {
                    dealedFilterConf.filterEffect = 9;
                    DJILogHelper.getInstance().LOGD("bob", "GBlackBorder");
                }
                dealedFilterConf.textString = "";
                dealedFilterConf.animate = b(filter.animate);
                arrayList.add(dealedFilterConf);
            }
        }
        if (this.s.TextArray != null) {
            for (i = 0; i < this.s.TextArray.size(); i++) {
                TextInfo textInfo = (TextInfo) this.s.TextArray.get(i);
                dealedFilterConf = new DealedFilterConf();
                dealedFilterConf.start = textInfo.start;
                dealedFilterConf.duration = textInfo.duration;
                dealedFilterConf.begin = 0.0d;
                dealedFilterConf.end = 0.0d;
                dealedFilterConf.filterEffect = 4;
                dealedFilterConf.animate = 0;
                dealedFilterConf.textString = textInfo.text;
                arrayList.add(dealedFilterConf);
            }
        }
        this.t = arrayList;
        return this.t;
    }

    public long getTotalDurations() {
        return (long) this.o;
    }

    public String getTemplateName() {
        return this.a;
    }

    public int getDurationAtOrder(int i) {
        if (i < this.i) {
            return ((Integer) this.g.get(i)).intValue() - ((Integer) this.f.get(i)).intValue();
        }
        return 0;
    }

    public int size() {
        return this.i;
    }

    public List<Integer> getSegDurations() {
        return this.h;
    }

    public List<Integer> getStartTime() {
        return this.b;
    }

    public List<Integer> getEndTime() {
        return this.c;
    }

    public String getDescription() {
        return this.k;
    }

    public String getPreviewFileName() {
        return this.l;
    }

    public String getPreviewMusicName() {
        return this.m;
    }

    public Bitmap getThumbnailBitmap() {
        return this.p;
    }

    public String getConcatMusicName() {
        return this.n;
    }

    public long c() {
        return this.r;
    }

    public void a(long j) {
        this.r = j;
    }

    public int d() {
        return this.v;
    }

    public void a(int i) {
        this.v = i;
    }

    public String getTag() {
        if (this.s != null) {
            return this.s.tag;
        }
        return null;
    }
}
