package dji.phone.pano;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import dji.device.common.view.progressbar.LonganHorizontalProgressBar;
import dji.phone.d.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DJILPPanoDisplayer extends DJIRelativeLayout {
    private static final String a = "DJILPPanoDisplayer";
    private ImageView b;
    private PhotoViewAttacher c;
    private ViewGroup d;
    private ViewGroup e;
    private LonganHorizontalProgressBar f;
    private a g;

    public interface a {
        void a();
    }

    public DJILPPanoDisplayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e = (ViewGroup) findViewById(R.id.longan_pano_progress_ly);
        this.d = (ViewGroup) findViewById(R.id.longan_pano_display_ly);
        this.f = (LonganHorizontalProgressBar) findViewById(R.id.longan_pano_progressbar);
        this.f.setMax(10000);
        this.b = (ImageView) findViewById(R.id.lp_pano_screen_pano_iv);
        this.c = new PhotoViewAttacher(this.b);
        findViewById(R.id.lp_pano_cancle_iv).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJILPPanoDisplayer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.go();
                this.a.g.a();
            }
        });
        dji.f.a.a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.f.a.b(this);
    }

    public void go() {
        super.go();
        this.g.a();
        d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
    }

    public void setLintener(a aVar) {
        this.g = aVar;
    }

    public void setProgress(int i) {
        this.f.setProgress(i);
    }

    public void viewToResult() {
        this.e.setVisibility(8);
        this.d.setVisibility(0);
    }

    public void viewToStitching() {
        if (!isShown()) {
            show();
        }
        this.e.setVisibility(0);
        this.d.setVisibility(8);
    }

    public void showPanoResult(String str) {
        int i = 1;
        viewToResult();
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth / dji.phone.preview.a.e;
        if (i2 > 0) {
            i = i2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        this.b.setImageBitmap(BitmapFactory.decodeFile(str, options));
        this.c.update();
    }

    private void a() {
        viewToStitching();
        this.f.setProgress(1);
    }

    public void onEventMainThread(dji.device.pano.Stitch.a aVar) {
        if (this.e.isShown()) {
            this.f.setProgress(dji.device.pano.Stitch.a.b);
        }
    }
}
