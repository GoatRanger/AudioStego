package dji.pilot2.multimoment.videolib;

import android.media.MediaMetadataRetriever;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.pilot2.bigfilm.b.b;
import dji.pilot2.videolib.VideoLibWrapper;
import java.io.Serializable;

public class d implements Serializable {
    protected static final String a = "bob VideoSegmentInfo";
    public static final int b = -1;
    public static final int c = -1;
    protected String d;
    protected long e;
    protected long f;
    protected long g = -1;
    protected int h = -1;
    protected long i;
    protected long j;
    protected double k;
    protected double l;
    protected double m;
    protected b n;
    protected int o;
    protected double p;
    protected double q;
    protected double r;
    protected double s;
    protected long t;
    protected long u;
    protected double v;
    protected Boolean w = Boolean.valueOf(false);
    protected transient b x;
    protected transient b y;
    protected transient b z;

    public d(String str, long j, long j2, long j3, b bVar) {
        long currentTimeMillis = System.currentTimeMillis();
        this.d = str;
        this.v = 1.0d;
        this.l = 1.0d;
        this.k = 1.0d;
        this.q = 0.0d;
        this.r = 1.0d;
        this.s = 1.0d;
        this.m = 0.0d;
        this.o = 0;
        this.p = 0.8d;
        this.e = j;
        this.t = j;
        this.i = j;
        this.u = j2;
        this.f = j2;
        this.j = j2;
        if (bVar == b.MultiEdit_Normal) {
            this.g = -1;
        } else {
            this.g = j3;
            if (b.MultiEdit_DP != bVar) {
                if (j3 < this.f - this.e) {
                    this.f = this.e + j3;
                }
                if (bVar == b.MultiEdit_Intelligent && j3 > this.f - this.e) {
                    DJILogHelper.getInstance().LOGI(a, "err mod = intelligent template segduration > acture segdutation");
                }
                if (bVar == b.SingleEdit) {
                    DJILogHelper.getInstance().LOGI("TAG", "mStartTime = " + this.e + " mEndTime = " + this.f);
                }
            } else if (j3 < this.u - this.t) {
                this.u = this.t + j3;
            }
        }
        this.n = bVar;
        DJILogHelper.getInstance().LOGI("bob", "VideoSegmentInfo constructor " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void a(b bVar, long j) {
        if (bVar != this.n || bVar == b.MultiEdit_tmp) {
            switch (bVar) {
                case MultiEdit_Normal:
                    if (this.n == b.MultiEdit_Intelligent || this.n == b.MultiEdit_DP) {
                        e();
                        if (((long) this.h) < this.j) {
                            this.j = (long) this.h;
                        }
                    } else {
                        DJILogHelper.getInstance().LOGI(a, "setMode err1" + this.n + "  " + bVar);
                    }
                    this.w = Boolean.valueOf(false);
                    break;
                case MultiEdit_Intelligent:
                    if (this.n == b.MultiEdit_Normal || this.n == b.MultiEdit_DP) {
                        this.g = j;
                        if (this.f - this.e != this.g) {
                            if (this.l == 1.0d) {
                                this.e = 0;
                                this.f = j;
                            }
                            if (((double) (this.f - this.e)) / this.l != ((double) this.g)) {
                                this.f = this.e + ((long) ((int) (((double) this.g) * this.l)));
                                if (this.f > ((long) this.h)) {
                                    this.f = (long) this.h;
                                    this.e = this.f - ((long) ((int) (((double) this.g) * this.l)));
                                    if (this.e < 0) {
                                        this.e = 0;
                                        this.l = (double) (((long) this.h) / this.g);
                                    }
                                }
                            }
                        }
                    } else {
                        DJILogHelper.getInstance().LOGI(a, "setMode err2" + this.n + "  " + bVar);
                    }
                    this.w = Boolean.valueOf(false);
                    break;
                case SingleEdit:
                    DJILogHelper.getInstance().LOGI(a, "setMode err3" + this.n + "  " + bVar);
                    this.w = Boolean.valueOf(false);
                    break;
                case MultiEdit_DP:
                    if (this.n != b.MultiEdit_Intelligent && this.n != b.MultiEdit_Normal) {
                        DJILogHelper.getInstance().LOGI("bob", "setMode err " + bVar);
                        break;
                    }
                    this.g = j;
                    if (((double) (this.u - this.t)) / this.v != ((double) this.g)) {
                        this.u = this.t + ((long) ((int) (((double) this.g) * this.v)));
                        if (this.u > ((long) this.h)) {
                            this.u = (long) this.h;
                            this.t = this.u - ((long) ((int) (((double) this.g) * this.v)));
                            if (this.t < 0) {
                                this.t = 0;
                                this.v = (double) (((long) this.h) / this.g);
                                break;
                            }
                        }
                    }
                    break;
                case MultiEdit_tmp:
                    Log.i("zhangchen", "load 1：" + j);
                    if (this.l == 1.0d) {
                        this.e = 0;
                        this.f = j;
                        Log.i("zhangchen", "load 1");
                    }
                    Log.i("zhangchen", "playTime :" + (((double) (this.f - this.e)) / this.l));
                    break;
            }
            this.n = bVar;
        }
    }

    public void a(long j, long j2, long j3, b bVar) {
        if (bVar == b.MultiEdit_Normal) {
            this.g = -1;
            this.i = j;
            this.j = j2;
            this.w = Boolean.valueOf(false);
            return;
        }
        this.g = j3;
        if (bVar == b.MultiEdit_DP) {
            this.t = j;
            this.u = j2;
            if (j3 < this.u - this.t) {
                this.u = this.t + j3;
            }
            if (j3 > this.u - this.t) {
                DJILogHelper.getInstance().LOGI(a, "setInfo err mod = MultiEdit_DP template segduration > acture segdutation");
                return;
            }
            return;
        }
        this.e = j;
        this.f = j2;
        if (j3 < this.f - this.e) {
            this.f = this.e + j3;
        }
        if (bVar == b.MultiEdit_Intelligent && j3 > this.f - this.e) {
            DJILogHelper.getInstance().LOGI(a, "setInfo err mod = intelligent template segduration > acture segdutation");
        }
        this.w = Boolean.valueOf(false);
    }

    public long a() {
        return this.f - this.e;
    }

    public long b() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public long c() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        if (this.h == -1) {
            this.h = a(this.d);
        }
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public static int a(String str) {
        String extractMetadata;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(str);
            extractMetadata = mediaMetadataRetriever.extractMetadata(9);
        } catch (IllegalArgumentException e) {
            extractMetadata = e;
            DJILogHelper.getInstance().LOGI("bob", "VideoSegmentInfo getFileActureDuration err");
            extractMetadata.printStackTrace();
            extractMetadata = null;
            if (extractMetadata != null) {
                DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration native");
                if (str != null) {
                    return 0;
                }
                return ((int) VideoLibWrapper.getVideoDuration(str)) - 250;
            }
            DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration java");
            return Integer.parseInt(extractMetadata) - 250;
        } catch (RuntimeException e2) {
            extractMetadata = e2;
            DJILogHelper.getInstance().LOGI("bob", "VideoSegmentInfo getFileActureDuration err");
            extractMetadata.printStackTrace();
            extractMetadata = null;
            if (extractMetadata != null) {
                DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration java");
                return Integer.parseInt(extractMetadata) - 250;
            }
            DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration native");
            if (str != null) {
                return ((int) VideoLibWrapper.getVideoDuration(str)) - 250;
            }
            return 0;
        } finally {
            mediaMetadataRetriever.release();
        }
        if (extractMetadata != null) {
            DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration java");
            return Integer.parseInt(extractMetadata) - 250;
        }
        DJILogHelper.getInstance().LOGI("bob", "getFileActureDuration native");
        if (str != null) {
            return ((int) VideoLibWrapper.getVideoDuration(str)) - 250;
        }
        return 0;
    }

    public long f() {
        return this.i;
    }

    public void c(long j) {
        this.i = j;
    }

    public long g() {
        return this.j;
    }

    public void d(long j) {
        this.j = j;
    }

    public double h() {
        return this.l;
    }

    public void a(double d) {
        this.l = d;
    }

    public long i() {
        return this.g;
    }

    public void e(long j) {
        this.g = j;
    }

    public double j() {
        return this.k;
    }

    public void b(double d) {
        this.k = d;
    }

    public double k() {
        return this.q;
    }

    public void c(double d) {
        this.q = d;
    }

    public double l() {
        return this.r;
    }

    public void d(double d) {
        this.r = d;
    }

    public double m() {
        return this.s;
    }

    public void e(double d) {
        this.s = d;
    }

    public double n() {
        return this.m;
    }

    public void f(double d) {
        this.m = d;
    }

    public int o() {
        return this.o;
    }

    public void b(int i) {
        this.o = i;
    }

    public double p() {
        return this.p;
    }

    public void g(double d) {
        this.p = d;
    }

    public int q() {
        double d;
        double d2;
        if (this.n == b.MultiEdit_Normal) {
            d = this.k;
            d2 = (double) (this.j - this.i);
        } else if (this.n == b.MultiEdit_Intelligent || this.n == b.MultiEdit_tmp) {
            d = this.l;
            d2 = (double) (this.f - this.e);
            Log.i("zhangchen", "duration:" + d2);
        } else if (this.n == b.MultiEdit_DP) {
            d = this.v;
            d2 = (double) (this.u - this.t);
        } else {
            d = 1.0d;
            d2 = 0.0d;
        }
        if (d == 0.0d) {
            return (int) d2;
        }
        return (int) (d2 / d);
    }

    public long r() {
        return this.t;
    }

    public void f(long j) {
        this.t = j;
    }

    public long s() {
        return this.u;
    }

    public void g(long j) {
        this.u = j;
    }

    public double t() {
        return this.v;
    }

    public void h(double d) {
        double d2 = (double) ((this.u - this.t) / this.g);
        if (d2 < d) {
            d = d2;
        }
        this.v = d;
    }

    public Boolean u() {
        if (this.n != b.MultiEdit_DP) {
            return Boolean.valueOf(false);
        }
        return this.w;
    }

    public void a(Boolean bool) {
        this.w = bool;
    }

    public b v() {
        return this.x;
    }

    public void a(b bVar) {
        this.x = bVar;
    }

    public b w() {
        return this.y;
    }

    public void b(b bVar) {
        this.y = bVar;
    }

    public b x() {
        return this.z;
    }

    public void c(b bVar) {
        this.z = bVar;
    }
}
