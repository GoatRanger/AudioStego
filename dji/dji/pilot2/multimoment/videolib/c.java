package dji.pilot2.multimoment.videolib;

import android.content.Context;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.pilot2.bigfilm.BigFilmBean;
import dji.pilot2.bigfilm.a;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.template.d;
import java.util.ArrayList;
import java.util.List;

public class c {
    public static final String a = "bob MomentEditController";
    public static final int b = 298000;
    public static final double c = 4.0d;
    protected Context d;
    protected List<d> e = new ArrayList();
    protected List<d> f = new ArrayList();
    protected List<d> g = new ArrayList();
    protected b h;
    protected int i;
    protected List<? extends d> j;

    public c(String[] strArr, int i, Context context) {
        this.i = i;
        this.d = context;
        if (strArr.length == 1) {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        } else {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int a = d.a(strArr[i2]);
            d dVar = new d(strArr[i2], 0, (long) a, 0, this.h);
            dVar.a(a);
            this.e.add(dVar);
        }
    }

    public c(String[] strArr, int[] iArr, int i, Context context) {
        this.i = i;
        this.d = context;
        if (strArr.length == 1) {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        } else {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int i3 = iArr[i2];
            d dVar = new d(strArr[i2], 0, (long) i3, 0, this.h);
            dVar.a(i3);
            this.e.add(dVar);
        }
    }

    public c(List<String> list, List<Integer> list2, int i, Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        this.i = i;
        this.d = context;
        if (list.size() == 1) {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        } else {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            long currentTimeMillis2 = System.currentTimeMillis();
            int intValue = ((Integer) list2.get(0)).intValue();
            DJILogHelper.getInstance().LOGI("bob", "MomentEditController constructor getFileActureDuration " + (System.currentTimeMillis() - currentTimeMillis2));
            d dVar = new d((String) list.get(i2), 0, (long) intValue, 0, this.h);
            dVar.a(intValue);
            this.e.add(dVar);
        }
        DJILogHelper.getInstance().LOGI("bob", "MomentEditController constructor " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public c(List<String> list, int i, Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        this.i = i;
        this.d = context;
        if (list.size() == 1) {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        } else {
            this.h = b.MultiEdit_Normal;
            this.j = TemplateController.getInstance().getTemplates(context);
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            long currentTimeMillis2 = System.currentTimeMillis();
            int a = d.a((String) list.get(i2));
            DJILogHelper.getInstance().LOGI("bob", "MomentEditController constructor getFileActureDuration " + (System.currentTimeMillis() - currentTimeMillis2));
            d dVar = new d((String) list.get(i2), 0, (long) a, 0, this.h);
            dVar.a(a);
            this.e.add(dVar);
        }
        DJILogHelper.getInstance().LOGI("bob", "MomentEditController constructor " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public int a(b bVar) {
        int i = 0;
        if (this.h != b.SingleEdit) {
            b bVar2 = this.h;
            this.h = bVar;
            if (!(bVar == b.MultiEdit_tmp || bVar == b.MultiEdit_Normal)) {
                this.j = TemplateController.getInstance().getTemplates(this.d);
            }
            List segDurations;
            List arrayList;
            int i2;
            if (bVar == b.MultiEdit_Intelligent) {
                this.g.clear();
                d dVar = (d) this.j.get(this.i);
                segDurations = dVar.getSegDurations();
                arrayList = new ArrayList();
                i2 = 0;
                while (i2 < this.e.size()) {
                    d dVar2 = (d) this.e.get(i2);
                    if (!dVar2.u().booleanValue()) {
                        if (dVar != null && i2 < segDurations.size()) {
                            dVar2.a(bVar, (long) ((Integer) segDurations.get(i2)).intValue());
                        }
                        if (i2 < segDurations.size()) {
                            arrayList.add(dVar2);
                        }
                        if (i2 >= segDurations.size()) {
                            this.f.add(dVar2);
                        }
                    }
                    i2++;
                }
                this.e = arrayList;
            } else if (bVar == b.MultiEdit_Normal) {
                this.g.clear();
                List arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < this.e.size(); i3++) {
                    r0 = (d) this.e.get(i3);
                    if (!r0.u().booleanValue()) {
                        r0.a(b.MultiEdit_Normal, 0);
                        arrayList2.add(r0);
                    }
                }
                while (i < this.f.size()) {
                    r0 = (d) this.f.get(i);
                    if (!r0.u().booleanValue()) {
                        r0.a(b.MultiEdit_Normal, 0);
                        arrayList2.add(r0);
                    }
                    i++;
                }
                this.e = arrayList2;
                this.f.clear();
            } else if (bVar == b.MultiEdit_tmp) {
                d d = d();
                segDurations = d.getSegDurations();
                arrayList = new ArrayList();
                if (this.g.size() == 0 || segDurations.size() < this.e.size()) {
                    this.g.clear();
                    while (i < this.e.size()) {
                        r0 = (d) this.e.get(i);
                        if (!r0.u().booleanValue()) {
                            if (d != null && i < segDurations.size()) {
                                r0.a(bVar, (long) ((Integer) segDurations.get(i)).intValue());
                            }
                            if (i < segDurations.size()) {
                                arrayList.add(r0);
                            }
                            if (i >= segDurations.size()) {
                                this.g.add(r0);
                            }
                        }
                        i++;
                    }
                } else {
                    for (i2 = 0; i2 < this.e.size(); i2++) {
                        r0 = (d) this.e.get(i2);
                        if (!r0.u().booleanValue()) {
                            r0.a(b.MultiEdit_tmp, (long) ((Integer) segDurations.get(i2)).intValue());
                            arrayList.add(r0);
                        }
                    }
                    if (this.g.size() > segDurations.size() - this.e.size()) {
                        i2 = segDurations.size() - this.e.size();
                    } else {
                        i2 = this.g.size();
                    }
                    while (i < i2) {
                        r0 = (d) this.g.get(i);
                        if (!r0.u().booleanValue()) {
                            r0.a(b.MultiEdit_tmp, (long) ((Integer) segDurations.get(this.e.size() + i)).intValue());
                            arrayList.add(r0);
                        }
                        i++;
                    }
                    this.g.clear();
                }
                this.e = arrayList;
            }
        }
        return this.i;
    }

    public int a() {
        return this.e.size();
    }

    public int b() {
        d d = d();
        if (d != null) {
            return d.getSegDurations().size();
        }
        return -1;
    }

    public b c() {
        return this.h;
    }

    public d d() {
        DJIMultiMomentEditActivity dJIMultiMomentEditActivity = (DJIMultiMomentEditActivity) this.d;
        for (int i = 0; i < this.j.size(); i++) {
            if (((dji.pilot2.template.c) this.j.get(i)).d() == dJIMultiMomentEditActivity.o().getPosToID(this.i)) {
                Log.i("zhang", "temp ID:" + ((dji.pilot2.template.c) this.j.get(i)).d());
                return (d) this.j.get(i);
            }
        }
        return null;
    }

    public void a(int i) {
        if (this.h == b.MultiEdit_DP) {
            DJILogHelper.getInstance().LOGI("bob", "changeToDP use error");
            return;
        }
        this.h = b.MultiEdit_DP;
        b(i);
    }

    public void b(int i) {
        if (this.h == b.MultiEdit_DP && this.i != i) {
            d dVar;
            this.i = i;
            this.j = a.getInstance().a(this.d);
            BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
            List segDurations = bigFilmBean.getSegDurations();
            List arrayList = new ArrayList();
            int size = segDurations.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                String confedFilePath = bigFilmBean.getConfedFilePath(i3, this.d);
                double confedSpeed = (double) bigFilmBean.getConfedSpeed(i3);
                if (confedFilePath != null) {
                    dVar = new d(confedFilePath, 0, (long) d.a(confedFilePath), ((Integer) segDurations.get(i3)).longValue(), this.h);
                    dVar.a(Boolean.valueOf(true));
                } else {
                    d dVar2;
                    if (i2 <= this.e.size()) {
                        dVar = (d) this.e.get(i2);
                        int i4 = i2;
                        while (dVar.u().booleanValue()) {
                            i4++;
                            if (i4 > this.e.size()) {
                                if (this.f.size() > 0) {
                                    dVar = (d) this.f.get(0);
                                    this.f.remove(0);
                                }
                                if (this.f.size() <= 0) {
                                    i2 = i4;
                                    break;
                                } else {
                                    i2 = i4 + 1;
                                    dVar2 = dVar;
                                }
                            } else {
                                dVar = (d) this.e.get(i4);
                            }
                        }
                        if (this.f.size() <= 0) {
                            i2 = i4;
                            break;
                        } else {
                            i2 = i4 + 1;
                            dVar2 = dVar;
                        }
                    } else if (this.f.size() <= 0) {
                        break;
                    } else {
                        dVar = (d) this.f.get(0);
                        this.f.remove(0);
                        dVar2 = dVar;
                    }
                    dVar2.a(0, ((Integer) segDurations.get(i3)).longValue(), ((Integer) segDurations.get(i3)).longValue(), this.h);
                    dVar = dVar2;
                }
                if (dVar != null) {
                    dVar.h(confedSpeed);
                    arrayList.add(dVar);
                } else {
                    DJILogHelper.getInstance().LOGI("bob", "err videoSegmentInfo=null mBackSegmentInfos.size() = " + this.f.size());
                }
            }
            if (this.e.size() > i2) {
                while (i2 < this.e.size()) {
                    dVar = (d) this.e.get(i2);
                    if (!dVar.u().booleanValue()) {
                        this.f.add(dVar);
                    }
                    i2++;
                }
            }
            this.e = arrayList;
        }
    }

    public void c(int i) {
        if (i != this.i) {
            this.i = i;
            d dVar;
            if (this.h == b.SingleEdit) {
                this.j = dji.pilot2.multimoment.template.c.getInstance().a(this.d);
                long totalDurations = ((d) this.j.get(i)).getTotalDurations();
                dVar = (d) this.e.get(0);
                dVar.a(0);
                if (((long) dVar.e()) < totalDurations) {
                    dVar.b((long) dVar.e());
                } else {
                    dVar.b(totalDurations);
                }
                dVar.e(totalDurations);
            } else if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
                d dVar2;
                this.j = TemplateController.getInstance().getTemplates(this.d);
                List segDurations = ((d) this.j.get(this.i)).getSegDurations();
                int min = Math.min(segDurations.size(), this.e.size());
                List arrayList = new ArrayList();
                for (int i2 = 0; i2 < min; i2++) {
                    dVar2 = (d) this.e.get(i2);
                    if (!dVar2.u().booleanValue()) {
                        dVar2.a(0, ((Integer) segDurations.get(i2)).longValue(), ((Integer) segDurations.get(i2)).longValue(), this.h);
                        dVar2.a(1.0d);
                        arrayList.add(dVar2);
                    }
                }
                if (arrayList.size() >= segDurations.size()) {
                    for (int i3 = min; i3 < this.e.size(); i3++) {
                        dVar = (d) this.e.get(i3);
                        if (!dVar.u().booleanValue()) {
                            this.f.add(dVar);
                        }
                    }
                }
                if (arrayList.size() < segDurations.size()) {
                    for (min = arrayList.size(); min < segDurations.size() && this.f.size() != 0; min++) {
                        dVar2 = (d) this.f.get(0);
                        dVar2.a(0, ((Integer) segDurations.get(min)).longValue(), ((Integer) segDurations.get(min)).longValue(), this.h);
                        dVar2.a(1.0d);
                        arrayList.add(dVar2);
                        this.f.remove(0);
                    }
                }
                this.e = arrayList;
            } else if (this.h != b.MultiEdit_Normal) {
            }
        }
    }

    public void a(int i, int i2, int i3, double d, double d2) {
        DJILogHelper.getInstance().LOGI(a, "segIndex = " + i + " start = " + i2 + " end = " + i3);
        if (this.e.size() < i) {
            DJILogHelper.getInstance().LOGI(a, "segSegInfo segIndex too big");
            return;
        }
        d dVar = (d) this.e.get(i);
        if (dVar.u().booleanValue()) {
            DJILogHelper.getInstance().LOGI("TAG", "setSegInfo Seg is conf file");
            return;
        }
        dVar.f(d2);
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            dVar.a((long) i2);
            dVar.b((long) i3);
            dVar.a(d);
        } else if (this.h == b.MultiEdit_Normal) {
            dVar.c((long) i2);
            dVar.d((long) i3);
            dVar.b(d);
        } else if (this.h == b.SingleEdit) {
            dVar.a((long) i2);
            dVar.b((long) i3);
            dVar.a(1.0d);
        } else if (this.h == b.MultiEdit_DP) {
            dVar.f((long) i2);
            dVar.g((long) i3);
            dVar.h(d);
        }
    }

    public void a(double[] dArr) {
        int size = this.e.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                ((d) this.e.get(i)).f(dArr[i]);
            }
        }
    }

    public void a(int i, double d, double d2, double d3) {
        DJILogHelper.getInstance().LOGI(a, "setSegOtherInfo segIndex = " + i + " bright = " + d + " contrast = " + d2 + "saturation= " + d3);
        if (this.e.size() < i) {
            DJILogHelper.getInstance().LOGI(a, "setSegOtherInfo segIndex too big");
            return;
        }
        d dVar = (d) this.e.get(i);
        dVar.c(d);
        dVar.d(d3);
        dVar.e(d2);
    }

    public void a(double d, double d2, double d3) {
        int size = this.e.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                d dVar = (d) this.e.get(i);
                dVar.c(d);
                dVar.d(d3);
                dVar.e(d2);
            }
        }
    }

    public void a(int i, int i2, double d) {
        DJILogHelper.getInstance().LOGI(a, "setSegFilterInfo segIndex = " + i + " filterNum = " + i2 + " how much = " + d);
        if (this.e.size() < i) {
            DJILogHelper.getInstance().LOGI(a, "setSegOtherInfo segIndex too big");
            return;
        }
        d dVar = (d) this.e.get(i);
        dVar.b(i2);
        dVar.g(d);
    }

    public void a(int i, double d) {
        int size = this.e.size();
        if (size != 0) {
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = (d) this.e.get(i2);
                dVar.b(i);
                dVar.g(d);
            }
        }
    }

    public Boolean a(String str, int i, int i2) {
        if (this.h == b.SingleEdit) {
            return Boolean.valueOf(false);
        }
        if (this.h == b.MultiEdit_Normal) {
            this.e.add(new d(str, (long) i, (long) i2, 0, this.h));
        } else if (this.h == b.MultiEdit_Intelligent) {
            List segDurations = ((d) this.j.get(this.i)).getSegDurations();
            int size = this.e.size();
            if (size >= segDurations.size()) {
                DJILogHelper.getInstance().LOGI(a, "addSegInfo err size =" + size + " mCurSelectTemplateIndex = " + this.i);
                return Boolean.valueOf(false);
            }
            String str2 = str;
            this.e.add(new d(str2, (long) i, (long) i2, (long) ((Integer) segDurations.get(size)).intValue(), this.h));
        } else if (this.h == b.MultiEdit_DP) {
            List segDurations2 = ((d) this.j.get(this.i)).getSegDurations();
            BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
            int size2 = this.e.size();
            while (size2 < segDurations2.size() && bigFilmBean.getIsConfedFile(size2).booleanValue()) {
                d dVar = new d(bigFilmBean.getConfedFilePath(size2, this.d), (long) i, (long) i2, (long) ((Integer) segDurations2.get(size2)).intValue(), this.h);
                dVar.h((double) bigFilmBean.getConfedSpeed(size2));
                this.e.add(dVar);
                size2 = this.e.size();
            }
            if (size2 >= segDurations2.size()) {
                DJILogHelper.getInstance().LOGI(a, "addSegInfo err  MultiEdit_DP size =" + size2 + " mCurSelectTemplateIndex = " + this.i);
                return Boolean.valueOf(false);
            }
            this.e.add(new d(str, (long) i, (long) i2, (long) ((Integer) segDurations2.get(size2)).intValue(), this.h));
        }
        return Boolean.valueOf(true);
    }

    public Boolean a(String str) {
        if (this.h == b.SingleEdit) {
            return Boolean.valueOf(false);
        }
        int a = d.a(str);
        int s;
        int i;
        d dVar;
        if (this.h == b.MultiEdit_Normal) {
            s = b - s();
            if (s <= 0) {
                DJILogHelper.getInstance().LOGI("bobbob MomentEditController", "addSeg end < 0");
                return Boolean.valueOf(false);
            }
            if (s > a) {
                i = a;
            } else {
                i = s;
            }
            dVar = new d(str, (long) 0, (long) i, 0, this.h);
            dVar.a(a);
            this.e.add(dVar);
        } else if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            d d;
            if (this.h == b.MultiEdit_tmp) {
                d = d();
            } else {
                d = (d) this.j.get(this.i);
            }
            List segDurations = d.getSegDurations();
            i = this.e.size();
            if (i >= segDurations.size()) {
                DJILogHelper.getInstance().LOGI(a, "addSegInfo err size =" + i + " mCurSelectTemplateIndex = " + this.i);
                return Boolean.valueOf(false);
            }
            r4 = (long) ((Integer) segDurations.get(i)).intValue();
            dVar = new d(str, 0, r4, r4, this.h);
            dVar.d((long) a);
            dVar.a(a);
            this.e.add(dVar);
        } else if (this.h == b.MultiEdit_DP) {
            List segDurations2 = ((d) this.j.get(this.i)).getSegDurations();
            BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
            s = this.e.size();
            while (s < segDurations2.size() && bigFilmBean.getIsConfedFile(s).booleanValue()) {
                r4 = (long) ((Integer) segDurations2.get(s)).intValue();
                this.e.add(new d(bigFilmBean.getConfedFilePath(s, this.d), 0, r4, r4, this.h));
                s = this.e.size();
            }
            if (s >= segDurations2.size()) {
                DJILogHelper.getInstance().LOGI("bob", "addSegInfo err MultiEdit_DP  size =" + s + " mCurSelectTemplateIndex = " + this.i);
                return Boolean.valueOf(false);
            }
            r4 = (long) ((Integer) segDurations2.get(s)).intValue();
            dVar = new d(str, 0, r4, r4, this.h);
            dVar.d((long) a);
            dVar.a(a);
            this.e.add(dVar);
        }
        return Boolean.valueOf(true);
    }

    public int a(List<String> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (a((String) list.get(i2)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public Boolean d(int i) {
        DJILogHelper.getInstance().LOGI(a, "delSegInfo size =" + this.e.size() + " mod = " + b.SingleEdit.a());
        if (this.e.size() == 1) {
            DJILogHelper.getInstance().LOGI(a, "delSegInfo mSegments.size() == 1");
            this.e.clear();
            return Boolean.valueOf(true);
        }
        if (this.h != b.MultiEdit_DP) {
            this.e.remove(i);
            if (this.h == b.MultiEdit_Intelligent) {
                List segDurations = ((d) this.j.get(this.i)).getSegDurations();
                while (i < this.e.size()) {
                    d dVar = (d) this.e.get(i);
                    long longValue = ((Integer) segDurations.get(i)).longValue();
                    dVar.a(0, longValue, longValue, this.h);
                    dVar.a(1.0d);
                    i++;
                }
            }
        } else if (((d) this.e.get(i)).u().booleanValue()) {
            return Boolean.valueOf(false);
        } else {
            this.e.remove(i);
            this.e = a(this.e, i, this.e.size());
        }
        return Boolean.valueOf(true);
    }

    public void a(int i, int i2) {
        DJILogHelper.getInstance().LOGI(a, "exchangeSeg from=" + i + " to= " + i2);
        int i3 = i - 1;
        int i4 = i2 - 1;
        d dVar = (d) this.e.get(i3);
        d dVar2 = (d) this.e.get(i4);
        if (!dVar.u().booleanValue() && !dVar2.u().booleanValue()) {
            this.e.remove(i3);
            this.e.add(i4, dVar);
            if (this.h == b.MultiEdit_DP) {
                this.e = a(this.e, i3, i4 + 1);
            } else if (this.h == b.MultiEdit_Intelligent) {
                int i5 = i3 > i2 ? -1 : 1;
                List segDurations = ((d) this.j.get(this.i)).getSegDurations();
                for (int i6 = i3; i6 < i4; i6 += i5) {
                    dVar2 = (d) this.e.get(i6);
                    dVar2.a(0, (long) ((Integer) segDurations.get(i4)).intValue(), (long) ((Integer) segDurations.get(i4)).intValue(), this.h);
                    dVar2.a(1.0d);
                }
            }
        }
    }

    protected List<d> a(List<d> list, int i, int i2) {
        List<d> arrayList = new ArrayList();
        List segDurations = ((d) this.j.get(this.i)).getSegDurations();
        BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
        int i3 = 0;
        while (i3 < list.size()) {
            d dVar = (d) list.get(i3);
            if (i3 < i || i3 >= i2) {
                arrayList.add(dVar);
            } else if (!dVar.u().booleanValue()) {
                long intValue;
                int size = arrayList.size();
                while (bigFilmBean.getIsConfedFile(size).booleanValue()) {
                    intValue = (long) ((Integer) segDurations.get(size)).intValue();
                    d dVar2 = new d(bigFilmBean.getConfedFilePath(size, this.d), 0, intValue, intValue, this.h);
                    dVar2.h((double) bigFilmBean.getConfedSpeed(size));
                    arrayList.add(dVar2);
                    size = arrayList.size();
                }
                intValue = ((Integer) segDurations.get(size)).longValue();
                dVar.a(0, intValue, intValue, this.h);
                dVar.h((double) bigFilmBean.getConfedSpeed(size));
                arrayList.add(dVar);
            }
            i3++;
        }
        return arrayList;
    }

    public void b(int i, int i2) {
        DJILogHelper.getInstance().LOGI(a, "exchangeSeg from=" + i + " to= " + i2);
        int i3 = i - 1;
        int i4 = i2 - 1;
        d dVar = (d) this.e.get(i3);
        d dVar2 = (d) this.e.get(i4);
        if (!dVar.u().booleanValue() && !dVar2.u().booleanValue()) {
            List segDurations;
            if (this.h == b.MultiEdit_Intelligent) {
                segDurations = ((d) this.j.get(this.i)).getSegDurations();
                dVar.a(0, (long) ((Integer) segDurations.get(i4)).intValue(), (long) ((Integer) segDurations.get(i4)).intValue(), this.h);
                dVar.a(1.0d);
                dVar2.a(0, (long) ((Integer) segDurations.get(i3)).intValue(), (long) ((Integer) segDurations.get(i3)).intValue(), this.h);
                dVar2.a(1.0d);
            } else if (this.h != b.MultiEdit_DP) {
                segDurations = ((d) this.j.get(this.i)).getSegDurations();
                BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
                dVar.a(0, (long) ((Integer) segDurations.get(i4)).intValue(), (long) ((Integer) segDurations.get(i4)).intValue(), this.h);
                dVar.h((double) bigFilmBean.getConfedSpeed(i4));
                dVar2.a(0, (long) ((Integer) segDurations.get(i3)).intValue(), (long) ((Integer) segDurations.get(i3)).intValue(), this.h);
                dVar2.h((double) bigFilmBean.getConfedSpeed(i3));
            }
            this.e.remove(i3);
            this.e.add(i3, dVar2);
            this.e.remove(i4);
            this.e.add(i4, dVar);
        }
    }

    public int e() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public int[] f() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((d) this.e.get(i)).e();
        }
        return iArr;
    }

    public String[] g() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            d d;
            if (this.h == b.MultiEdit_tmp) {
                d = d();
            } else {
                d = (d) this.j.get(this.i);
            }
            if (size > d.size()) {
                size = d.size();
            }
        }
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((d) this.e.get(i)).d();
        }
        return strArr;
    }

    public List<String> h() {
        int i = 0;
        List<String> arrayList = new ArrayList();
        if (this.h == b.MultiEdit_Normal) {
            while (i < this.e.size()) {
                arrayList.add(((d) this.e.get(i)).d());
                i++;
            }
        } else if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            d d;
            if (this.h == b.MultiEdit_tmp) {
                d = d();
            } else {
                d = (d) this.j.get(this.i);
            }
            int size = this.e.size();
            if (size > d.size()) {
                size = d.size();
            }
            while (i < size) {
                arrayList.add(((d) this.e.get(i)).d());
                i++;
            }
        } else if (this.h == b.SingleEdit) {
            arrayList.add(((d) this.e.get(0)).d());
        }
        return arrayList;
    }

    public void a(List<Integer> list, List<Integer> list2) {
        if (list == null || list2 == null) {
            DJILogHelper.getInstance().LOGI(a, "getVideoPlayerInfos err startTime=null || endTime == null");
        } else if (this.h == b.MultiEdit_Normal) {
            b((List) list, (List) list2);
        } else if (this.h == b.SingleEdit) {
            f(list, list2);
        } else if (this.h == b.MultiEdit_Intelligent) {
            d(list, list2);
        } else if (this.h == b.MultiEdit_tmp) {
            c(list, list2);
        } else if (this.h == b.MultiEdit_DP) {
            e(list, list2);
        }
    }

    public void i() {
        this.j = dji.pilot2.multimoment.template.c.getInstance().a(this.d);
        Log.i("zhang", "change size:" + this.j.size());
    }

    public void j() {
        this.j = TemplateController.getInstance().getTemplates(this.d);
    }

    protected void b(List<Integer> list, List<Integer> list2) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            list.add(Integer.valueOf((int) ((d) this.e.get(i)).f()));
            list2.add(Integer.valueOf((int) ((d) this.e.get(i)).g()));
        }
    }

    protected void c(List<Integer> list, List<Integer> list2) {
        int size;
        int size2 = this.e.size();
        d d = d();
        if (size2 > d.size()) {
            size = d.size();
        } else {
            size = size2;
        }
        for (int i = 0; i < size; i++) {
            list.add(Integer.valueOf((int) ((d) this.e.get(i)).b()));
            list2.add(Integer.valueOf((int) ((d) this.e.get(i)).c()));
        }
    }

    protected void d(List<Integer> list, List<Integer> list2) {
        int size = this.e.size();
        d dVar = (d) this.j.get(this.i);
        if (size > dVar.size()) {
            size = dVar.size();
        }
        for (int i = 0; i < size; i++) {
            Log.i("zhang", "start time:" + ((int) ((d) this.e.get(i)).b()));
            Log.i("zhang", "end time:" + ((int) ((d) this.e.get(i)).c()));
            list.add(Integer.valueOf((int) ((d) this.e.get(i)).b()));
            list2.add(Integer.valueOf((int) ((d) this.e.get(i)).c()));
        }
    }

    protected void e(List<Integer> list, List<Integer> list2) {
        BigFilmBean bigFilmBean = (BigFilmBean) this.j.get(this.i);
        int size = this.e.size();
        if (size > bigFilmBean.size()) {
            size = bigFilmBean.size();
        }
        for (int i = 0; i < size; i++) {
            list.add(Integer.valueOf((int) ((d) this.e.get(i)).r()));
            list2.add(Integer.valueOf((int) ((d) this.e.get(i)).s()));
        }
    }

    protected void f(List<Integer> list, List<Integer> list2) {
        if (this.e.size() != 1) {
            DJILogHelper.getInstance().LOGI(a, "getSingleEditTimes err mSegments.size() = " + this.e.size());
            return;
        }
        dji.pilot2.multimoment.template.c.getInstance().a(this.i, (int) ((d) this.e.get(0)).b(), (int) ((d) this.e.get(0)).c(), this.d, list, list2);
    }

    public double[] k() {
        int i = 0;
        if (this.h == b.SingleEdit) {
            DJILogHelper.getInstance().LOGI(a, "getFast err singleEdit");
            return new double[]{1.0d};
        }
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_DP || this.h == b.MultiEdit_tmp) {
            d d;
            if (this.h == b.MultiEdit_tmp) {
                d = d();
            } else {
                d = (d) this.j.get(this.i);
            }
            if (size > d.size()) {
                size = d.size();
            }
        }
        double[] dArr = new double[size];
        while (i < size) {
            if (this.h == b.MultiEdit_Intelligent || this.h == b.SingleEdit || this.h == b.MultiEdit_tmp) {
                dArr[i] = ((d) this.e.get(i)).h();
                Log.i("zhang", " fast is:" + dArr[i]);
            } else if (this.h == b.MultiEdit_DP) {
                dArr[i] = ((d) this.e.get(i)).t();
            } else {
                dArr[i] = ((d) this.e.get(i)).j();
            }
            i++;
        }
        return dArr;
    }

    public double[] l() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.e.get(i)).k();
        }
        return dArr;
    }

    public double[] m() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.e.get(i)).l();
        }
        return dArr;
    }

    public double[] n() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.e.get(i)).m();
        }
        return dArr;
    }

    public int[] o() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((d) this.e.get(i)).o();
        }
        return iArr;
    }

    public double[] p() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                size = dVar.size();
            }
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.e.get(i)).p();
        }
        return dArr;
    }

    public double[] q() {
        int size;
        double[] dArr;
        int size2 = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size2 > dVar.size()) {
                size = dVar.size();
                if (size != 0) {
                    return null;
                }
                dArr = new double[size];
                for (size2 = 0; size2 < size; size2++) {
                    dArr[size2] = ((d) this.e.get(size2)).n();
                }
                return dArr;
            }
        }
        size = size2;
        if (size != 0) {
            return null;
        }
        dArr = new double[size];
        for (size2 = 0; size2 < size; size2++) {
            dArr[size2] = ((d) this.e.get(size2)).n();
        }
        return dArr;
    }

    public int r() {
        int size = this.e.size();
        if (this.h == b.MultiEdit_Intelligent) {
            d dVar = (d) this.j.get(this.i);
            if (size > dVar.size()) {
                return dVar.size();
            }
        }
        return size;
    }

    public int s() {
        int i = 0;
        for (int i2 = 0; i2 < r(); i2++) {
            i += ((d) this.e.get(i2)).q();
        }
        return i;
    }

    public int f(int i) {
        if (i >= r()) {
            return 0;
        }
        return ((d) this.e.get(i)).q();
    }

    public Boolean t() {
        if (this.h == b.SingleEdit) {
            return Boolean.valueOf(false);
        }
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_DP || this.h == b.MultiEdit_tmp) {
            d d;
            if (this.h == b.MultiEdit_tmp) {
                d = d();
            } else {
                d = (d) this.j.get(this.i);
            }
            if (d.size() > this.e.size()) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        } else if (this.h != b.MultiEdit_Normal) {
            return Boolean.valueOf(false);
        } else {
            int i = 0;
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                i += ((d) this.e.get(i2)).q();
            }
            if (i >= b) {
                return Boolean.valueOf(false);
            }
            return Boolean.valueOf(true);
        }
    }

    public List<Integer> u() {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        if (this.h == b.MultiEdit_Normal) {
            List<Integer> arrayList2 = new ArrayList();
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(Integer.valueOf(((d) this.e.get(i)).q()));
            }
            return arrayList2;
        } else if (this.h == b.MultiEdit_Intelligent) {
            return ((d) this.j.get(this.i)).getSegDurations();
        } else {
            if (this.h == b.MultiEdit_DP) {
                return ((d) this.j.get(this.i)).getSegDurations();
            }
            if (this.h == b.SingleEdit) {
                long min = Math.min((long) ((d) this.e.get(0)).q(), ((d) this.j.get(this.i)).getTotalDurations());
                List<Integer> arrayList3 = new ArrayList();
                arrayList3.add(Integer.valueOf((int) min));
                return arrayList3;
            } else if (this.h == b.MultiEdit_tmp) {
                return d().getSegDurations();
            } else {
                return null;
            }
        }
    }

    public List<Integer> g(int i) {
        return ((dji.pilot2.template.c) dji.pilot2.multimoment.template.c.getInstance().a(this.d).get(i)).h;
    }

    public List<Integer> v() {
        return ((dji.pilot2.template.c) dji.pilot2.multimoment.template.c.getInstance().a(this.d).get(this.i)).h;
    }

    public int h(int i) {
        return ((d) this.e.get(i)).e();
    }

    public int i(int i) {
        if (this.h == b.SingleEdit) {
            return (int) ((d) this.e.get(0)).b();
        }
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            if (i < this.e.size()) {
                return (int) ((d) this.e.get(i)).e;
            }
            return 0;
        } else if (this.h == b.MultiEdit_Normal) {
            if (i < this.e.size()) {
                return (int) ((d) this.e.get(i)).i;
            }
            return 0;
        } else if (this.h != b.MultiEdit_DP || i >= this.e.size()) {
            return 0;
        } else {
            return (int) ((d) this.e.get(i)).r();
        }
    }

    public int j(int i) {
        if (this.h == b.SingleEdit) {
            return (int) ((d) this.e.get(0)).c();
        }
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_tmp) {
            if (i < this.e.size()) {
                return (int) ((d) this.e.get(i)).f;
            }
            return 0;
        } else if (this.h == b.MultiEdit_Normal) {
            if (i < this.e.size()) {
                return (int) ((d) this.e.get(i)).j;
            }
            return 0;
        } else if (this.h != b.MultiEdit_DP || i >= this.e.size()) {
            return 0;
        } else {
            return (int) ((d) this.e.get(i)).s();
        }
    }

    public double k(int i) {
        if (this.h == b.SingleEdit) {
            return 1.0d;
        }
        if (this.h == b.MultiEdit_Intelligent) {
            if (i < this.e.size()) {
                return ((d) this.e.get(i)).l;
            }
            return 1.0d;
        } else if (this.h == b.MultiEdit_Normal) {
            if (i < this.e.size()) {
                return ((d) this.e.get(i)).k;
            }
            return 1.0d;
        } else if (this.h != b.MultiEdit_DP || i >= this.e.size()) {
            return 1.0d;
        } else {
            return (double) ((int) ((d) this.e.get(i)).t());
        }
    }

    public double l(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).q;
        }
        return 0.0d;
    }

    public double m(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).r;
        }
        return 1.0d;
    }

    public double n(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).s;
        }
        return 1.0d;
    }

    public double o(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).m;
        }
        return 0.0d;
    }

    public int p(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).o;
        }
        return 0;
    }

    public double q(int i) {
        if (i < this.e.size()) {
            return ((d) this.e.get(i)).p;
        }
        return 0.0d;
    }

    public String w() {
        if (c() == b.MultiEdit_tmp) {
            return d().getPreviewMusicName();
        }
        if (this.i >= this.j.size()) {
            return null;
        }
        return ((d) this.j.get(this.i)).getPreviewMusicName();
    }

    public List<d> x() {
        return this.e;
    }

    public String y() {
        if (c().equals(b.MultiEdit_tmp) || c().equals(b.MultiEdit_DP)) {
            int e = e();
            if (!(this.j == null || this.j.get(e) == null)) {
                if (c().equals(b.MultiEdit_tmp)) {
                    return d().getTag();
                }
                return ((d) this.j.get(e)).getTag();
            }
        }
        return null;
    }

    public boolean z() {
        if (this.h == b.MultiEdit_Intelligent || this.h == b.MultiEdit_Normal) {
            d A = A();
            DJILogHelper.getInstance().LOGI("bob", "((MultiTemplateBean) bean).getmId() = " + A.getTemplateName());
            if ((A instanceof dji.pilot2.template.a) && ((dji.pilot2.template.a) A).d() <= -100000) {
                DJILogHelper.getInstance().LOGI("bob", "((MultiTemplateBean) bean).getmId() = " + ((dji.pilot2.template.a) A).d());
                return true;
            }
        }
        return false;
    }

    public d A() {
        int e = e();
        if (this.j != null) {
            return (d) this.j.get(e);
        }
        return null;
    }
}
