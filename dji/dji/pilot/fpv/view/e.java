package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.dji.frame.c.c;
import dji.pilot.R;
import dji.pilot.flyunlimit.view.WarningLevelSelectView;
import dji.pilot.fpv.view.DJIStageView.a;

public class e extends PopupWindow {
    private View a;
    private View b;
    private DJIStageView c = ((DJIStageView) this.b.findViewById(R.id.anp));
    private dji.gs.c.e d;

    public e(Context context, dji.gs.c.e eVar, View view) {
        this.a = view;
        this.b = LayoutInflater.from(context).inflate(R.layout.gs_warn_level_set, null, false);
        setContentView(this.b);
        setWindowLayoutMode(-2, -2);
        setAnimationStyle(R.style.e5);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setFocusable(true);
        setOutsideTouchable(true);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.d = eVar;
        this.c.createStageView(R.layout.warning_type_view, R.string.nfz_fly_zone_warn, false);
        this.c.setOnStageChangeListener(new dji.pilot.fpv.view.DJIStageView.e(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void a(int i, int i2, int i3) {
                a currentStageView = this.a.c.getCurrentStageView();
                if (i == 2 && (currentStageView instanceof WarningLevelSelectView)) {
                    ((WarningLevelSelectView) currentStageView).setPointerManager(this.a.d);
                }
            }
        });
    }

    public void a() {
        showAsDropDown(this.a, (this.a.getMeasuredHeight() / 2) - (this.b.getMeasuredWidth() / 2), 0);
        c.a(this.b);
    }

    public void dismiss() {
        super.dismiss();
        dji.thirdparty.a.c.a().e(c.a.a);
    }
}
