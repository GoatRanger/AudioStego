package dji.setting.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c;
import dji.pilot.setting.ui.R;

public class d extends Dialog {
    private View a;
    private int b;
    private int c;
    private int d;
    private int e;

    public d(Context context, View view, View view2, int i, int i2) {
        super(context, R.style.setting_ui_dialog);
        this.a = view;
        this.b = i;
        this.c = i2;
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i5 = displayMetrics.widthPixels;
        if (i4 + i2 < displayMetrics.heightPixels) {
            this.e = i4;
        } else {
            this.e = (i4 - i2) + view2.getHeight();
        }
        this.d = i3;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.a);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = this.b;
        attributes.height = this.c;
        attributes.x = this.d;
        attributes.y = this.e;
        attributes.gravity = 51;
        attributes.dimAmount = 0.0f;
        getWindow().setAttributes(attributes);
    }

    public void show() {
        if (a.a != 1126) {
            getWindow().setFlags(8, 8);
            c.a(getWindow());
            a.a = getWindow().getDecorView().getSystemUiVisibility();
        }
        super.show();
        if (a.a != 1126) {
            getWindow().clearFlags(8);
        }
    }
}
