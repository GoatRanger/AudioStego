package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.controller.DataMgr.b;
import dji.pilot.dji_groundstation.controller.d;

public class CourseLockResetTextView extends TextView {
    private static final String a = "CourseLockResetTextView";

    public CourseLockResetTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        getPaint().setFlags(8);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CourseLockResetTextView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (d.getInstance().b().a() == c.p.a()) {
                    b.getInstance().a(true);
                    DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.HOMEPOINT_LOC);
                    DataFlycFunctionControl.getInstance().start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_course_lock_reset_success));
                        }

                        public void onFailure(a aVar) {
                            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_course_lock_reset_failed));
                        }
                    });
                }
            }
        });
        if (d.getInstance().b().a() == c.p.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
