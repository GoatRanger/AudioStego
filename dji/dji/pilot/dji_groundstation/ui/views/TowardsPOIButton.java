package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycHotPointResetCamera;
import dji.midware.e.d;
import dji.publics.DJIUI.DJITextView;

public class TowardsPOIButton extends DJITextView {
    private static final String a = "TowardsPOIButton";

    public TowardsPOIButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TowardsPOIButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DataFlycHotPointResetCamera.getInstance().start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(a aVar) {
                    }
                });
            }
        });
    }
}
