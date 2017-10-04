package dji.pilot.playback.litchi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public final class DJIHeadTabView extends DJILinearLayout {
    public static final int a = 0;
    public static final int b = 1;
    public static final String c = "tab_local";
    public static final String d = "tab_remote";
    public static int e = -1;
    public static final String[] f = new String[]{d, c};
    private static final int[] g = new int[]{R.id.bi3, R.id.bi4};
    private static final int h = -1;
    private static final int i = 2;
    private final b[] j;
    private a k;
    private OnClickListener l;
    private int m;

    public interface a {
        void a(int i, String str, int i2);
    }

    private static final class b {
        public View a;
        public DJIRelativeLayout b;
        public DJITextView c;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public DJIHeadTabView(Context context) {
        this(context, null);
    }

    public DJIHeadTabView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIHeadTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new b[2];
        this.k = null;
        this.l = null;
        this.m = -1;
        a();
    }

    private void a() {
        this.l = new OnClickListener(this) {
            final /* synthetic */ DJIHeadTabView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.bi3 == id) {
                    this.a.a(0, DJIHeadTabView.d, 0);
                    DJIHeadTabView.e = 0;
                } else if (R.id.bi4 == id) {
                    this.a.a(1, DJIHeadTabView.c, 0);
                    DJIHeadTabView.e = 1;
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            for (int i = 0; i < 2; i++) {
                b bVar = new b();
                bVar.a = findViewById(g[i]);
                bVar.c = (DJITextView) bVar.a.findViewById(R.id.bhy);
                bVar.b = (DJIRelativeLayout) bVar.a.findViewById(R.id.bhx);
                this.j[i] = bVar;
                bVar.a.setOnClickListener(this.l);
            }
            this.j[0].c.setText(getResources().getString(R.string.playback_tab_tv_remote));
            this.j[0].b.setBackground(getResources().getDrawable(R.drawable.selector_rc_btn_left));
            this.j[1].c.setText(getResources().getString(R.string.playback_tab_tv_local));
            this.j[1].b.setBackground(getResources().getDrawable(R.drawable.selector_rc_btn_right));
        }
    }

    public void setOnTabSelectedListener(a aVar) {
        this.k = aVar;
    }

    public void setCurrentTab(int i) {
        if (-1 < i && i < 2) {
            a(i, f[i], 0);
            e = i;
        }
    }

    public int getCurrentTab() {
        return e;
    }

    public void showTabNew(int i, int i2) {
        if (i2 == 0) {
            this.j[i].c.go();
        } else {
            this.j[i].c.show();
        }
    }

    private void a(int i, String str, int i2) {
        if (i != this.m) {
            a(this.m, i);
            this.m = i;
            if (this.k != null) {
                this.k.a(i, str, i2);
            }
        }
    }

    private void a(int i, int i2) {
        if (-1 != i) {
            this.j[i].a.setSelected(false);
        }
        this.j[i2].a.setSelected(true);
    }
}
