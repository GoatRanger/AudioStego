package dji.pilot2.multimoment.videolib;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.login.widget.ToolTipPopup;
import dji.g.b.e;
import dji.pilot2.bigfilm.RenderBasicData;
import dji.pilot2.bigfilm.RenderDatas;
import dji.pilot2.bigfilm.c;

public class a {
    public dji.pilot2.videolib.a a;
    private dji.g.b.a b;
    private SparseArray<String> c;
    private Context d;
    private long e;
    private long f;
    private int g;
    private final int h = 2;
    private e[][] i;
    private e[][] j;

    public a(Context context) {
        this.d = context;
    }

    public dji.g.b.a a() {
        if (this.b == null) {
            this.b = new dji.g.b.a();
        }
        return this.b;
    }

    public void a(String[] strArr, int[] iArr) {
        if (strArr != null || strArr.length != 0) {
            e[] eVarArr = new e[1];
            e[] eVarArr2 = new e[1];
            if (this.c == null) {
                this.c = new SparseArray();
            }
            for (int i = 0; i < strArr.length; i++) {
                if (!strArr[i].isEmpty()) {
                    this.c.put(iArr[i], strArr[i]);
                    if (i % 2 == 0) {
                        eVarArr[i / 2] = new e(strArr[i], dji.g.a.a.a.e, 0, ToolTipPopup.a, false, 1.0d);
                        eVarArr[i / 2].g = 0;
                        eVarArr[i / 2].h = 14000;
                    } else {
                        eVarArr2[i / 2] = new e(strArr[i], dji.g.a.a.a.e, 0, ToolTipPopup.a, false, 1.0d);
                        eVarArr2[i / 2].g = ToolTipPopup.a;
                        eVarArr2[i / 2].h = 8000;
                    }
                }
            }
            this.i = new e[][]{eVarArr, eVarArr2};
            this.b.a(this.i, (e[][]) null);
        }
    }

    public void a(dji.pilot2.videolib.a aVar) {
        this.a = aVar;
    }

    public void a(String str, int i) {
        if (this.c != null) {
            this.c.put(i, str);
        }
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.remove(i);
        }
    }

    public int a(String str) {
        if (this.c != null) {
            return this.c.indexOfValue(str);
        }
        return -1;
    }

    public void b() {
        if (this.c == null) {
        }
    }

    public void a(int i, int i2) {
        if (this.c != null && this.c.valueAt(i) != null && this.a != null) {
            Log.i("zhang", "add filter path is:" + ((String) this.c.valueAt(i)));
            if (c.TransitionType_GradualChange == c.find(i2)) {
                this.a.a(new dji.pilot2.videolib.a.a(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public int a(int i, int i2) {
                        return 0;
                    }

                    public int a() {
                        return 0;
                    }

                    public RenderDatas a(int[] iArr, int[] iArr2, int[] iArr3, long j, long j2, boolean z) {
                        Log.i("zhang", "video applt!" + j);
                        Log.i("zhang", "video duration:" + this.a.c.keyAt(0));
                        RenderDatas renderDatas = new RenderDatas();
                        renderDatas.curTransition = "bright";
                        int i = (int) (j / 1000);
                        if (i < this.a.c.keyAt(0) - 1500 || i > this.a.c.keyAt(0) + 1500) {
                            renderDatas.textureIndex = 1;
                        } else if (i <= this.a.c.keyAt(0)) {
                            renderDatas.textureIndex = 0;
                            renderDatas.globalFilters = new RenderBasicData[1];
                            r5 = Math.abs(((float) (this.a.c.keyAt(0) - i)) / 1500.0f);
                            Log.i("zhang", "seg 1:" + r5);
                            renderDatas.globalFilters[0] = new RenderBasicData("LOGO_FILTER", 27, r5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
                        } else {
                            renderDatas.textureIndex = 1;
                            renderDatas.globalFilters = new RenderBasicData[1];
                            r5 = Math.abs(((float) ((this.a.c.keyAt(0) + 1500) - i)) / 1500.0f);
                            Log.i("zhang", "seg 1:" + r5);
                            renderDatas.globalFilters[0] = new RenderBasicData("LOGO_FILTER", 27, 1.0f - r5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
                        }
                        return renderDatas;
                    }
                });
            }
            if (c.TransitionType_SpyChange != c.find(i2)) {
            }
        }
    }

    public void b(int i) {
    }
}
