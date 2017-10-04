package dji.pilot.publics.objects;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.SeekBar;
import dji.pilot.R;
import dji.publics.DJIUI.DJITextView;

public class f extends c {
    private SeekBar a;
    private DJITextView b;
    private a c = null;

    public interface a {
        void a(DialogInterface dialogInterface, boolean z, int i);
    }

    public f(Context context) {
        super(context);
        setContentView(R.layout.fpv_upgrade_selt_dlg);
        this.a = (SeekBar) findViewById(R.id.adb);
        this.b = (DJITextView) findViewById(R.id.adc);
        this.b.setOnClickListener(new 1(this));
        this.a.setOnSeekBarChangeListener(new 2(this));
        this.a.setPadding(0, 0, 0, 0);
    }

    protected void m() {
        a(-1, -1, 0, 17, true, true);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(0.7f);
        setCancelable(false);
    }

    public f a(a aVar) {
        this.c = aVar;
        return this;
    }

    protected void b() {
        if (this.a.getProgress() >= 95) {
            this.a.setProgress(100);
            a(true);
            return;
        }
        this.a.setProgress(0);
        a(false);
    }

    private void a(boolean z) {
        if (this.c != null) {
            this.c.a(this, z, 0);
        }
    }
}
