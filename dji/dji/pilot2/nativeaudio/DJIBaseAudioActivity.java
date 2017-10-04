package dji.pilot2.nativeaudio;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.c.a.a;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public abstract class DJIBaseAudioActivity extends DJIActivityNoFullScreen {
    protected TextView a;
    protected TextView b;
    protected TextView c;
    private ActionBar d;
    private boolean t = true;

    public abstract void a(TextView textView);

    public abstract void a(TextView textView, boolean z);

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.d9);
        a.getInstance().c(25).a(255).b(-16777216).b(this);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = 1;
        window.setAttributes(attributes);
    }

    protected void a() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.v2_nativeaudio_actionbar, null);
        this.d = getActionBar();
        this.d.setDisplayOptions(16);
        this.d.setDisplayShowCustomEnabled(true);
        this.d.setCustomView(inflate);
        a(inflate);
    }

    protected void a(boolean z) {
        this.t = z;
    }

    private void a(View view) {
        if (view != null) {
            this.c = (TextView) view.findViewById(R.id.cv8);
            this.a = (TextView) view.findViewById(R.id.cv9);
            this.b = (TextView) view.findViewById(R.id.cv_);
            a(this.a);
            a(this.b, this.t);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
