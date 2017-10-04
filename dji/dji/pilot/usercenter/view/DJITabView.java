package dji.pilot.usercenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public final class DJITabView extends DJILinearLayout {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final String e = "tab_album";
    public static final String f = "tab_flightrecord";
    public static final String g = "tab_shop";
    public static final String h = "tab_myinfo";
    public static final String[] i = new String[]{e, f, g, h};
    private static final int[] j = new int[]{R.id.c3r, R.id.c3s, R.id.c3t, R.id.c3u};
    private static final int k = -1;
    private static final int l = 4;
    private final b[] m = new b[4];
    private a n = null;
    private OnClickListener o = null;
    private int p = -1;

    public interface a {
        void a(int i, String str, int i2);
    }

    private static final class b {
        public View a;
        public DJIImageView b;
        public DJIImageView c;
        public DJITextView d;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public DJITabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.o = new OnClickListener(this) {
            final /* synthetic */ DJITabView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.c3u == id) {
                    e.a("UserCenter_BottomBarView_Button_ShowMyInfoView");
                    this.a.a(3, DJITabView.h, 0);
                } else if (R.id.c3s == id) {
                    e.a("UserCenter_BottomBarView_Button_ShowFlightRecordView");
                    this.a.a(1, DJITabView.f, 0);
                } else if (R.id.c3r == id) {
                    e.a("UserCenter_BottomBarView_Button_ShowAlbumView");
                    this.a.a(0, DJITabView.e, 0);
                } else if (R.id.c3t == id) {
                    this.a.a(2, DJITabView.g, 0);
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            for (int i = 0; i < 4; i++) {
                b bVar = new b();
                bVar.a = findViewById(j[i]);
                bVar.b = (DJIImageView) bVar.a.findViewById(R.id.c3o);
                bVar.d = (DJITextView) bVar.a.findViewById(R.id.c3p);
                bVar.c = (DJIImageView) bVar.a.findViewById(R.id.c3q);
                this.m[i] = bVar;
                bVar.a.setOnClickListener(this.o);
            }
            this.m[0].d.setText(R.string.usercenter_album);
            this.m[0].b.setImageResource(R.drawable.selector_usercenter_album);
            this.m[1].d.setText(R.string.usercenter_flight_record);
            this.m[1].b.setImageResource(R.drawable.selector_usercenter_flightrecord);
            this.m[2].a.setVisibility(8);
            this.m[2].d.setText(R.string.usercenter_dji_shop);
            this.m[2].b.setImageResource(R.drawable.selector_usercenter_shop);
            this.m[3].d.setText(R.string.usercenter_my_info);
            this.m[3].b.setImageResource(R.drawable.selector_usercenter_account);
        }
    }

    public void setOnTabSelectedListener(a aVar) {
        this.n = aVar;
    }

    public void setCurrentTab(int i) {
        if (-1 < i && i < 4) {
            a(i, i[i], 0);
        }
    }

    public void showTabNew(int i, int i2) {
        if (i2 == 0) {
            this.m[i].c.go();
        } else {
            this.m[i].c.show();
        }
    }

    private void a(int i, String str, int i2) {
        if (i != this.p) {
            a(this.p, i);
            this.p = i;
            if (this.n != null) {
                this.n.a(i, str, i2);
            }
        }
    }

    private void a(int i, int i2) {
        if (-1 != i) {
            this.m[i].a.setSelected(false);
        }
        this.m[i2].a.setSelected(true);
    }
}
