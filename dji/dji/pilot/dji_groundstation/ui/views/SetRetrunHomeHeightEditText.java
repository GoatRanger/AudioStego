package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.dji_groundstation.controller.DataMgr.d;

public class SetRetrunHomeHeightEditText extends EditText {
    private static final String a = "SetRetrunHomeHeightEditText";
    private boolean b = false;

    public SetRetrunHomeHeightEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setText(Integer.toString(DataOsdGetPushHome.getInstance().getGoHomeHeight()));
        a();
        setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SetRetrunHomeHeightEditText a;

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
            d.getInstance().a(new dji.midware.e.d(this) {
                final /* synthetic */ SetRetrunHomeHeightEditText b;

                public void onSuccess(Object obj) {
                    if (d.getInstance().b()) {
                        d.getInstance().c(parseInt);
                    }
                }

                public void onFailure(a aVar) {
                }
            });
        } catch (Exception e) {
        }
    }
}
