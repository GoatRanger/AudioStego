package dji.pilot.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;
import dji.pilot.phonecamera.R;
import dji.pilot.phonecamera.e;
import dji.pilot.phonecamera.e.d;
import dji.pilot.phonecamera.g;
import dji.pilot.phonecamera.h;
import dji.pilot.phonecamera.i;
import dji.pilot.phonecamera.j;
import dji.sdksharedlib.b.b;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SurfaceTextureListener {
    Button a;
    Button b;
    Button c;
    Button d;
    Button e;
    Button f;
    Button g;
    Button h;
    Spinner i;
    String j = "auto";
    String k = "auto";
    String[] l;
    private CameraPreview m;
    private DrawingView n;
    private g o;
    private SurfaceTexture p = null;
    private Context q;
    private boolean r = false;
    private int s = 0;
    private ArrayAdapter<String> t;
    private boolean u = false;
    private d v = new d(this) {
        final /* synthetic */ MainActivity a;

        {
            this.a = r1;
        }

        public void a(int i) {
        }

        public void b(int i) {
            Toast.makeText(this.a, "camera open failing", 1).show();
        }

        public void a(e eVar) {
        }
    };

    class a implements OnItemSelectedListener {
        final /* synthetic */ MainActivity a;

        a(MainActivity mainActivity) {
            this.a = mainActivity;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.o.d(Integer.valueOf(this.a.l[i]).intValue());
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        b(0);
        this.q = this;
        this.n = new DrawingView(this);
        this.m = new CameraPreview(this, this.n);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.camera_preview);
        frameLayout.addView(this.m);
        frameLayout.addView(this.n);
        this.m.setSurfaceTextureListener(this);
        this.a = (Button) findViewById(R.id.switchMode);
        this.b = (Button) findViewById(R.id.photo);
        this.c = (Button) findViewById(R.id.switchCamera);
        this.d = (Button) findViewById(R.id.flash);
        this.e = (Button) findViewById(R.id.HDR);
        this.f = (Button) findViewById(R.id.WB);
        this.g = (Button) findViewById(R.id.scene);
        this.i = (Spinner) findViewById(R.id.exposure);
        this.h = (Button) findViewById(R.id.setExposure);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.u) {
                    this.a.b.setText("PHOTO");
                    this.a.u = false;
                    this.a.a(0);
                    return;
                }
                this.a.u = true;
                this.a.a(1);
                this.a.b.setText("RECORD");
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.b.getText().equals("RECORD")) {
                    ((j) this.a.o).e();
                    this.a.b.setText("RECORDING");
                } else if (this.a.b.getText().equals("RECORDING")) {
                    ((j) this.a.o).f();
                    this.a.b.setText("RECORD");
                } else if (this.a.b.getText().equals("PHOTO") || this.a.b.getText().equals("photo")) {
                    ((i) this.a.o).d();
                }
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o.b(-1);
                this.a.a();
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.d.getText().equals("ON")) {
                    this.a.o.a("off");
                    this.a.d.setText("OFF");
                } else if (this.a.d.getText().equals("OFF")) {
                    this.a.o.a("auto");
                    this.a.d.setText("AUTO");
                } else if (this.a.d.getText().equals("AUTO")) {
                    this.a.o.a("torch");
                    this.a.d.setText("TORCH");
                } else if (this.a.d.getText().equals("TORCH")) {
                    this.a.o.a("on");
                    this.a.d.setText("ON");
                }
            }
        });
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j.equals(h.c)) {
                    this.a.j = "auto";
                    this.a.o.c(this.a.j);
                    return;
                }
                this.a.j = h.c;
                this.a.o.p();
            }
        });
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j.equals("auto")) {
                    this.a.j = "night";
                    this.a.o.c(this.a.j);
                } else if (this.a.j.equals("night")) {
                    this.a.j = "action";
                    this.a.o.c(this.a.j);
                } else if (this.a.j.equals("action")) {
                    this.a.j = "party";
                    this.a.o.c(this.a.j);
                } else if (this.a.j.equals("party")) {
                    this.a.j = "sunset";
                    this.a.o.c(this.a.j);
                } else if (this.a.j.equals("sunset")) {
                    this.a.j = "auto";
                    this.a.o.c(this.a.j);
                }
            }
        });
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.k.equals("auto")) {
                    this.a.k = "cloudy-daylight";
                    this.a.o.b(this.a.k);
                } else if (this.a.k.equals("cloudy-daylight")) {
                    this.a.k = "daylight";
                    this.a.o.b(this.a.k);
                } else if (this.a.k.equals("daylight")) {
                    this.a.k = "fluorescent";
                    this.a.o.b(this.a.k);
                } else if (this.a.k.equals("fluorescent")) {
                    this.a.k = "incandescent";
                    this.a.o.b(this.a.k);
                } else if (this.a.k.equals("incandescent")) {
                    this.a.k = "auto";
                    this.a.o.b(this.a.k);
                }
            }
        });
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                List arrayList = new ArrayList();
                this.a.a("setExpoture");
                for (int minExposureCompensation = this.a.o.m().getMinExposureCompensation(); minExposureCompensation <= this.a.o.m().getMaxExposureCompensation(); minExposureCompensation++) {
                    arrayList.add(String.valueOf(minExposureCompensation));
                }
                this.a.l = new String[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    this.a.l[i] = (String) arrayList.get(i);
                    this.a.a("mExposure[" + i + "] = " + this.a.l[i]);
                }
                this.a.t = new ArrayAdapter(this.a.q, 17367048, this.a.l);
                this.a.t.setDropDownViewResource(17367049);
                this.a.i.setAdapter(this.a.t);
                this.a.i.setOnItemSelectedListener(new a(this.a));
                this.a.i.setVisibility(0);
            }
        });
        a();
    }

    protected void onResume() {
        super.onResume();
        Log.d("czf", "onResume: ");
        if (!this.r) {
            b(this.s);
        }
        this.m.setCameraModule(this.o);
    }

    private void a() {
        if (this.o.a(dji.pilot.phonecamera.c.a.FLASH)) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        if (this.o.a(dji.pilot.phonecamera.c.a.HDR)) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        if (this.o.a(dji.pilot.phonecamera.c.a.EXPOSURE)) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
        if (this.o.a(dji.pilot.phonecamera.c.a.SCENE)) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
        if (this.o.a(dji.pilot.phonecamera.c.a.WB)) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.d("czf", "onSurfaceTextureAvailable: ");
        if (this.p == null) {
            this.p = this.m.getSurfaceTexture();
        }
        this.o.a(this.p);
        this.o.k();
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.o.l();
        this.o.c();
        this.r = false;
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void a(int i) {
        this.o.c();
        b(i);
        this.m.setCameraModule(this.o);
        this.o.k();
        a();
    }

    private void b(int i) {
        switch (i) {
            case 0:
                this.o = new i();
                this.o.a(i, -1, this, this.v);
                this.r = true;
                this.s = 0;
                return;
            case 1:
                this.o = new j();
                this.o.a(i, -1, this, this.v);
                this.r = true;
                this.s = 1;
                return;
            default:
                this.o = new i();
                this.o.a(i, -1, this, this.v);
                this.r = true;
                this.s = 0;
                return;
        }
    }

    private void a(String str) {
        Log.d(b.a, "[MainActivity] " + str);
    }
}
