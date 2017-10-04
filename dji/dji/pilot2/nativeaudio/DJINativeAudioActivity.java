package dji.pilot2.nativeaudio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.nativeaudio.a.a;
import dji.publics.DJIUI.DJITextView;

public class DJINativeAudioActivity extends DJIBaseAudioActivity implements OnClickListener {
    private LinearLayout A;
    private LinearLayout B;
    private DJITextView d;
    private DJITextView t;
    private ViewPager u;
    private a v;
    private View w;
    private View x;
    private ImageView y;
    private ImageView z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        a(false);
        super.onCreate(bundle);
        setContentView(R.layout.v2_native_audio_layout);
        a();
    }

    protected void a() {
        super.a();
        this.c.setOnClickListener(this);
        this.d = (DJITextView) findViewById(R.id.cuk);
        this.t = (DJITextView) findViewById(R.id.cug);
        this.y = (ImageView) findViewById(R.id.cuf);
        this.z = (ImageView) findViewById(R.id.cuj);
        this.A = (LinearLayout) findViewById(R.id.cue);
        this.B = (LinearLayout) findViewById(R.id.cui);
        this.t.setEnabled(true);
        this.t.setSelected(true);
        this.y.setSelected(true);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.u = (ViewPager) findViewById(R.id.cum);
        this.v = new a(getFragmentManager());
        this.u.setAdapter(this.v);
        this.v.notifyDataSetChanged();
        this.w = findViewById(R.id.cuh);
        this.x = findViewById(R.id.cul);
        this.x.setVisibility(8);
        this.u.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ DJINativeAudioActivity a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                if (i % 2 == 0) {
                    this.a.d.setSelected(false);
                    this.a.t.setSelected(true);
                    this.a.w.setVisibility(0);
                    this.a.x.setVisibility(8);
                    this.a.y.setSelected(true);
                    this.a.z.setSelected(false);
                    return;
                }
                this.a.d.setSelected(true);
                this.a.z.setSelected(true);
                this.a.t.setSelected(false);
                this.a.w.setVisibility(8);
                this.a.x.setVisibility(0);
                this.a.y.setSelected(false);
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    protected void a(boolean z) {
        super.a(z);
    }

    public void a(TextView textView) {
        textView.setText(getResources().getString(R.string.v2_audio_more));
    }

    public void a(TextView textView, boolean z) {
        if (z) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cue:
                this.u.setCurrentItem(0);
                this.t.setSelected(true);
                this.d.setSelected(false);
                this.y.setSelected(true);
                this.z.setSelected(false);
                this.w.setVisibility(0);
                this.x.setVisibility(8);
                return;
            case R.id.cui:
                this.u.setCurrentItem(1);
                this.d.setSelected(true);
                this.t.setSelected(false);
                this.y.setSelected(false);
                this.z.setSelected(true);
                this.w.setVisibility(8);
                this.x.setVisibility(0);
                return;
            case R.id.cv8:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.i("zhang", "native:" + i);
        if (i == 100) {
            finish();
        }
    }
}
