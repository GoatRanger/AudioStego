package dji.pilot.set.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;
import dji.pilot.phonecamera.a.c;
import dji.pilot.set.R;
import dji.pilot.storage.a;
import dji.pilot.usercenter.protocol.d;

public class DJILPCameraSetGroupView extends ScrollView {
    DJILPCameraStorageSetView a;
    BroadcastReceiver b = new BroadcastReceiver(this) {
        final /* synthetic */ DJILPCameraSetGroupView a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(this.a.c, "onReceive: ");
            if (intent.getAction().equals("android.intent.action.MEDIA_MOUNTED") && VERSION.SDK_INT >= 21) {
                Log.d(this.a.c, "onReceive: ACTION_MEDIA_MOUNTED");
                this.a.a.setVisibility(0);
            } else if (intent.getAction().equals("android.intent.action.MEDIA_EJECT") || intent.getAction().equals("android.intent.action.MEDIA_UNMOUNTABLE") || intent.getAction().equals("android.intent.action.MEDIA_UNMOUNTED") || intent.getAction().equals("android.intent.action.MEDIA_REMOVED")) {
                this.a.a.setVisibility(8);
                c.a().a(false);
            }
        }
    };
    private String c = DJILPCameraSetGroupView.class.getSimpleName();

    public DJILPCameraSetGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.a = (DJILPCameraStorageSetView) findViewById(R.id.lp_camera_storage_set);
            if (!a.a(getContext())) {
                this.a.setVisibility(8);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
            intentFilter.addDataScheme(d.A);
            getContext().registerReceiver(this.b, intentFilter);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(this.b);
    }
}
