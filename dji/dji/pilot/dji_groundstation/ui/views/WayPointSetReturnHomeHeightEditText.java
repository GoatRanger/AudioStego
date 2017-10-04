package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointSetReturnHomeHeightEditText extends EditText {
    private static final String a = "WayPointSetReturnHomeHeightEditText";
    private boolean b = false;

    public WayPointSetReturnHomeHeightEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setText(Integer.toString(DataOsdGetPushHome.getInstance().getGoHomeHeight()));
        a();
        setFocusableInTouchMode(true);
        setFocusable(true);
        setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ WayPointSetReturnHomeHeightEditText a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.a();
                return false;
            }
        });
    }

    private void a() {
        String obj = getText().toString();
        if (obj.trim().isEmpty()) {
            setText(Integer.toString(DataOsdGetPushHome.getInstance().getGoHomeHeight()));
        }
        try {
            final int parseInt = Integer.parseInt(obj);
            e.getInstance().a(new d(this) {
                final /* synthetic */ WayPointSetReturnHomeHeightEditText b;

                public void onSuccess(Object obj) {
                    if (e.getInstance().b()) {
                        e.getInstance().c(parseInt);
                    }
                }

                public void onFailure(a aVar) {
                }
            });
        } catch (Exception e) {
        }
    }
}
