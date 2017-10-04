package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.thirdparty.a.c;

public class b extends PopupWindow {
    private View a;
    private View b;
    private ImageView c = ((ImageView) this.b.findViewById(R.id.al3));
    private ImageView d = ((ImageView) this.b.findViewById(R.id.al2));
    private Context e;

    public b(Context context, View view) {
        this.e = context;
        this.a = view;
        this.b = LayoutInflater.from(context).inflate(R.layout.gs_map_location, null, false);
        setContentView(this.b);
        setWindowLayoutMode(-2, -2);
        setAnimationStyle(R.style.e5);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setFocusable(true);
        setOutsideTouchable(true);
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.al2:
                        e.a("GroundStation_RightControlView_Button_ShowMapLocation_Aircraft");
                        c.a().e(dji.pilot.fpv.control.k.b.AIR);
                        break;
                    case R.id.al3:
                        e.a("GroundStation_RightControlView_Button_ShowMapLocation_Home");
                        c.a().e(dji.pilot.fpv.control.k.b.HOME);
                        break;
                }
                this.a.dismiss();
            }
        };
        this.c.setOnClickListener(anonymousClass1);
        this.d.setOnClickListener(anonymousClass1);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public void a() {
        showAsDropDown(this.a, (this.a.getMeasuredHeight() / 2) - (this.b.getMeasuredWidth() / 2), 0);
        com.dji.frame.c.c.a(this.b);
    }

    public void dismiss() {
        super.dismiss();
        c.a().e(a.a);
    }
}
