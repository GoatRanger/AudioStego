package dji.phone.controview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.here.odnp.config.OdnpConfigStatic;
import dji.phone.c.a;
import dji.phone.d.c;
import dji.phone.d.d;
import dji.phone.h.b;
import dji.pilot.fpv.R;
import java.util.LinkedHashMap;

public class DJILPCameraZoomSeekBar extends LinearLayout {
    private static final String a = DJILPCameraZoomSeekBar.class.getSimpleName();
    private ImageView b;
    private ImageView c;
    private SeekBar d;
    private b e = b.ROTATION_0;
    private OnSeekBarChangeListener f = new OnSeekBarChangeListener(this) {
        final /* synthetic */ DJILPCameraZoomSeekBar a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int i2;
            Log.d(DJILPCameraZoomSeekBar.a, "onProgressChanged: progress = " + i);
            if (i > a.c().i()) {
                i2 = a.c().i();
            } else {
                i2 = i;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            d.getInstance().c(i2, false);
            this.a.removeCallbacks(this.a.g);
            this.a.postDelayed(this.a.g, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d(DJILPCameraZoomSeekBar.a, "onStartTrackingTouch: ");
            if (this.a.isShown()) {
                this.a.removeCallbacks(this.a.g);
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(DJILPCameraZoomSeekBar.a, "onStopTrackingTouch: ");
        }
    };
    private Runnable g = new Runnable(this) {
        final /* synthetic */ DJILPCameraZoomSeekBar a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setVisibility(8);
        }
    };

    public DJILPCameraZoomSeekBar(Context context) {
        super(context);
    }

    public DJILPCameraZoomSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraZoomSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onEventMainThread(b bVar) {
        this.e = bVar;
        Log.d(a, "onEventMainThread: getOritation = " + bVar.a() + " getRotation = " + bVar.b());
        b();
    }

    public void onEventMainThread(LinkedHashMap linkedHashMap) {
        Log.d(a, "onEventMainThread: ");
        if (!isShown()) {
            setVisibility(0);
        }
        if (d.getInstance().c() == c.a.CAMERASTATE_RECORDING) {
            setVisibility(8);
        }
        if (linkedHashMap != null) {
            this.d.setProgress(((Integer) linkedHashMap.get(dji.phone.d.b.d)).intValue());
        }
    }

    public void onEventMainThread(dji.pilot.phonecamera.a.a aVar) {
        if (aVar == dji.pilot.phonecamera.a.a.d) {
            this.d.setMax(a.c().i());
            this.d.setProgress(a.c().h());
        }
    }

    private void b() {
        if (this.e.a(90) || this.e.a(270)) {
            this.b.setImageResource(R.drawable.lp_zoom_reduce_port);
        } else {
            this.b.setImageResource(R.drawable.lp_zoom_reduce_landscape);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = (ImageView) findViewById(R.id.lp_zoom_reduce);
        this.c = (ImageView) findViewById(R.id.lp_zoom_increase);
        this.d = (SeekBar) findViewById(R.id.lp_zoom_seekbar);
        try {
            this.d.setMax(a.c().i());
            this.d.setProgress(a.c().h());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        this.d.setOnSeekBarChangeListener(this.f);
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }
}
