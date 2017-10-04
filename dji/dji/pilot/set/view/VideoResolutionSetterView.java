package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.pilot.phonecamera.a.c;
import dji.pilot.set.R;

public class VideoResolutionSetterView extends LinearLayout implements OnClickListener {
    private static final int c = -1;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private static final int l = 8;
    private static final int m = 9;
    private static final int[] n = new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
    private static final int[] o = new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1, -1, 5, 4, 2, 1, -1, 7};
    private static final int[] p = new int[]{1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
    private static final int[] q = new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
    private static final int[] r = new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1, -1, 6, 4, 3, 1, -1, 7};
    private static final int[] s = new int[]{1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0};
    private static String[] t = null;
    private static String[] u = null;
    protected TextView a;
    protected TextView b;
    private int v = 0;
    private int[] w;
    private int[] x;
    private String[] y;
    private int[] z;

    public static class a {
        public int a;
        public int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public VideoResolutionSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t = getPalResolution(context);
        u = getNtscResolution(context);
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_text, null, false));
        this.a = (TextView) findViewById(R.id.set_item_title);
        this.a.setText(R.string.set_video_resolution);
        this.b = (TextView) findViewById(R.id.set_item_value);
        this.b.setText("");
        setOnClickListener(this);
    }

    public static String[] getPalResolution(Context context) {
        return new String[]{"4K", "4096x2160 25fps", "4096x2160 24fps", "3840x2160 25fps", "3840x2160 24fps", "2.7K", "2704x1520 25fps", "2704x1520 24fps", "1080P", "1920x1080 50fps", "1920x1080 48fps", "1920x1080 25fps", "1920x1080 24fps", c.A, "1280x720  50fps", "1280x720  48fps", "1280x720  25fps", "1280x720  24fps", context.getResources().getString(R.string.set_camera_video_resolution_module_title_slow_motion), "1920x1080 120P"};
    }

    public static String[] getNtscResolution(Context context) {
        return new String[]{"4K", "4096x2160 24fps", "3840x2160 30fps", "3840x2160 24fps", "2.7K", "2704x1520 30fps", "2704x1520 24fps", "1080P", "1920x1080 60fps", "1920x1080 48fps", "1920x1080 30fps", "1920x1080 24fps", c.A, "1280x720  60fps", "1280x720  48fps", "1280x720  30fps", "1280x720  24fps", context.getResources().getString(R.string.set_camera_video_resolution_module_title_slow_motion), "1920x1080 120P"};
    }

    public static int[] getPalResolution() {
        return n;
    }

    public static int[] getNtscResolution() {
        return q;
    }

    public static int[] getPalFps() {
        return o;
    }

    public static int[] getNtscFps() {
        return r;
    }

    protected void a() {
        DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
        if (instance.getVideoStandard() == 1) {
            this.w = q;
            this.x = r;
            this.y = u;
            this.z = s;
        } else {
            this.w = n;
            this.x = o;
            this.y = t;
            this.z = p;
        }
        int videoFormat = instance.getVideoFormat();
        int videoFps = instance.getVideoFps();
        this.v = -1;
        int i = 0;
        while (i < this.w.length) {
            if (videoFormat == this.w[i] && videoFps == this.x[i]) {
                this.v = i;
                break;
            }
            i++;
        }
        b();
    }

    protected void setValue(int i) {
        if (i != -1) {
            int i2 = this.w[i];
            int i3 = this.x[i];
            new DataCameraSetVideoFormat().a().a(i2).b(i3).start(null);
            DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
            if (i3 == 7) {
                dataCameraSetVideoRecordMode.a(2, 0, 0);
            } else {
                dataCameraSetVideoRecordMode.a(0, 0, 0);
            }
            dataCameraSetVideoRecordMode.start(null);
            this.v = i;
            b();
        }
    }

    private void b() {
        if (this.v >= 0) {
            for (int i = this.v; i >= 0; i--) {
                if (this.z[i] == 1) {
                    int i2 = this.x[this.v];
                    CharSequence charSequence = this.y[i] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
                    if (DataCameraGetPushShotParams.getInstance().getVideoStandard() == 1) {
                        if (this.v == 18) {
                            charSequence = "1080 120";
                        }
                    } else if (this.v == 19) {
                        charSequence = "1080 120";
                    }
                    if (i2 == 1) {
                        charSequence = charSequence + "24";
                    } else if (i2 == 2) {
                        charSequence = charSequence + "25";
                    } else if (i2 == 3) {
                        charSequence = charSequence + "30";
                    } else if (i2 == 4) {
                        charSequence = charSequence + "48";
                    } else if (i2 == 5) {
                        charSequence = charSequence + "50";
                    } else if (i2 == 6) {
                        charSequence = charSequence + "60";
                    }
                    this.b.setText(charSequence);
                    return;
                }
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    public void onClick(View view) {
        if (!dji.pilot.set.c.a()) {
            VideoResolutionGroupListView.showSelectView(this.v, this.y, this.z, R.string.set_video_resolution, getContext());
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void onEventMainThread(a aVar) {
        setValue(aVar.b);
    }
}
