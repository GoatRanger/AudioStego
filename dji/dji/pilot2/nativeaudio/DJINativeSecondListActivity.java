package dji.pilot2.nativeaudio;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.main.activity.DJIHowToConnectActivity;
import dji.pilot2.nativeaudio.a.b;
import dji.pilot2.nativeaudio.b.a;
import dji.pilot2.nativeaudio.model.c;
import java.util.ArrayList;

public class DJINativeSecondListActivity extends DJIBaseAudioActivity implements OnClickListener {
    public static String d = "type";
    public static String t = DJIHowToConnectActivity.r;
    private Context A;
    private float B = 0.0f;
    private float C = 0.0f;
    private String u;
    private ListView v;
    private b w;
    private a x;
    private ArrayList<dji.pilot2.nativeaudio.model.a> y;
    private int z = 0;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        a(false);
        super.onCreate(bundle);
        a(getIntent());
        setContentView(R.layout.v2_native_audio_seclayout);
        a();
    }

    private void a(Intent intent) {
        if (intent != null) {
            this.u = intent.getStringExtra(d);
            this.z = intent.getIntExtra(t, 0);
        }
    }

    protected void a() {
        super.a();
        this.A = this;
        this.v = (ListView) findViewById(R.id.cun);
        this.v.setSelector(R.drawable.v2_audioitem_selector);
        this.w = new b(this);
        this.w.a(b.a.AdapterNormal);
        this.x = new a(this);
        this.x.a();
        this.c.setOnClickListener(this);
        this.x.a(new a.a(this) {
            final /* synthetic */ DJINativeSecondListActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.z == 0) {
                    this.a.y = this.a.x.c();
                    this.a.w.b(true);
                    this.a.w.a(true);
                } else if (this.a.z == 1) {
                    this.a.y = this.a.x.e();
                    this.a.w.a(true);
                    this.a.w.b(false);
                } else if (this.a.z == 2) {
                    this.a.y = this.a.x.d();
                    this.a.w.a(false);
                    this.a.w.b(false);
                }
                Log.i("zhang", "size:" + this.a.y.size());
                for (int i = 0; i < this.a.y.size(); i++) {
                    c cVar = new c();
                    if (this.a.z == 1) {
                        cVar.c = this.a.x.a(((dji.pilot2.nativeaudio.model.a) this.a.y.get(i)).c());
                    }
                    if (this.a.z == 0) {
                        cVar.a = R.drawable.v2_audio_dir;
                    }
                    cVar.b = ((dji.pilot2.nativeaudio.model.a) this.a.y.get(i)).d();
                    this.a.w.a(cVar);
                }
                this.a.v.setAdapter(this.a.w);
                this.a.w.notifyDataSetChanged();
            }
        });
        this.v.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJINativeSecondListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.y != null && this.a.y.size() > 0) {
                    dji.pilot2.nativeaudio.model.a aVar = (dji.pilot2.nativeaudio.model.a) this.a.y.get(i);
                    Intent intent = new Intent(this.a.A, DJIAudioPlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key_list", aVar.b());
                    intent.putExtra("list", bundle);
                    this.a.startActivityForResult(intent, 100);
                }
            }
        });
        this.v.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJINativeSecondListActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("zhang", "onTouch list");
                switch (motionEvent.getAction()) {
                    case 0:
                        this.a.B = motionEvent.getX();
                        break;
                    case 1:
                        Log.i("zhang", "onTouch list:" + this.a.C);
                        if (this.a.C > 200.0f) {
                            this.a.finish();
                            return true;
                        }
                        break;
                    case 2:
                        this.a.C = motionEvent.getX() - this.a.B;
                        Log.i("zhang", "move:" + motionEvent.getX());
                        break;
                }
                return false;
            }
        });
    }

    protected void a(boolean z) {
        super.a(z);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    public void a(TextView textView) {
        textView.setText(this.u);
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
            case R.id.cv8:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.i("zhang", "sec:" + i2);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
        }
    }
}
