package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataGimbalResetUserParams;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;

public class RecoveryListView extends LinearLayout implements OnClickListener {
    d a;
    private int b = 0;

    public RecoveryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    private void a() {
        findViewById(R.id.set_recovery_camera).setOnClickListener(this);
        findViewById(R.id.set_recovery_gimbal).setOnClickListener(this);
        findViewById(R.id.set_recovery_all).setOnClickListener(this);
        this.a = new d(this) {
            final /* synthetic */ RecoveryListView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Toast.makeText(this.a.a.getContext(), R.string.set_recovery_success, 0).show();
                    }
                });
            }

            public void onFailure(a aVar) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Toast.makeText(this.a.a.getContext(), R.string.set_recovery_fails, 0).show();
                    }
                });
            }
        };
    }

    public void onClick(View view) {
        this.b = view.getId();
        e.a(getContext(), R.string.set_recovery_confirm, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ RecoveryListView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.a.b == R.id.set_recovery_camera) {
                    DataCameraLoadParams.getInstance().setMode(USER.DEFAULT).start(this.a.a);
                } else if (this.a.b == R.id.set_recovery_gimbal) {
                    DataGimbalResetUserParams.getInstance().start(this.a.a);
                } else if (this.a.b == R.id.set_recovery_all) {
                    DataGimbalResetUserParams.getInstance().start(this.a.a);
                    DataCameraLoadParams.getInstance().setMode(USER.DEFAULT).start(this.a.a);
                }
            }
        });
    }
}
