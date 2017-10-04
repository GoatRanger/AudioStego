package dji.pilot2.mine.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import com.dji.frame.c.c;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.model.MediaInfoBean;
import dji.pilot2.mine.d.a;
import dji.pilot2.mine.d.b;

public class CleanCacheActivity extends DJIActivityNoFullScreen implements c$m {
    public static final String G = Environment.getExternalStorageDirectory().getPath();
    public static final String H = ("/DJI/" + DJIApplication.f + "/CACHE_IMAGE");
    public static final String I = ("/DJI/" + DJIApplication.f + "/DJI Pano");
    public static final String J = ("/DJI/" + DJIApplication.f + "/DJI Album");
    public static final String K = ("/DJI/" + DJIApplication.f + "/DJI_RECORD");
    public static final String L = ("/DJI/" + DJIApplication.f + "/VideoEditor/segmentLibrary");
    public static final String M = ("/DJI/" + DJIApplication.f + "/VideoEditor/segmentLibrary_hd");
    public static final String N = ("/DJI/" + DJIApplication.f + "/Upgrade/DLPackage");
    public static final String O = ("/DJI/" + DJIApplication.f + "/academy");
    private TextView P;
    private TextView Q;
    private TextView R;
    private TextView S;
    private TextView T;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_clean_cache);
        this.P = (TextView) findViewById(R.id.c79);
        this.Q = (TextView) findViewById(R.id.c7g);
        this.R = (TextView) findViewById(R.id.c7a);
        this.S = (TextView) findViewById(R.id.c7c);
        this.T = (TextView) findViewById(R.id.c7e);
        b bVar = new b(new String[]{G + J}, this.R);
        b bVar2 = new b(new String[]{G + K}, this.P);
        b bVar3 = new b(new String[]{G + O}, this.S);
        b bVar4 = new b(new String[]{G + N}, this.T);
        b bVar5 = new b(new String[]{G + L, G + M}, this.Q);
        bVar.execute(new Void[]{(Void) null});
        bVar2.execute(new Void[]{(Void) null});
        bVar3.execute(new Void[]{(Void) null});
        bVar4.execute(new Void[]{(Void) null});
        bVar5.execute(new Void[]{(Void) null});
    }

    public void onClickHandler(View view) {
        Builder bVar;
        switch (view.getId()) {
            case R.id.c77:
                finish();
                return;
            case R.id.c78:
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_confirm_clean_video_cache);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        e.b(c$m.dC_);
                        a aVar = new a(new String[]{CleanCacheActivity.G + CleanCacheActivity.K}, this.a.P, dji.pilot2.library.a.VideoAllDelete);
                        aVar.a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                c.c(this.a.a).a(MediaInfoBean.class, "type LIKE '%video%'");
                            }
                        });
                        aVar.execute(new Void[]{(Void) null});
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.c7_:
                e.b(c$m.dD_);
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_confirm_clean_photo_cache);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        a aVar = new a(new String[]{CleanCacheActivity.G + CleanCacheActivity.H, CleanCacheActivity.G + CleanCacheActivity.J, CleanCacheActivity.G + CleanCacheActivity.I}, this.a.R, dji.pilot2.library.a.PhotoAllDelete);
                        aVar.a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                c.c(this.a.a).a(MediaInfoBean.class, "type LIKE '%image%'");
                            }
                        });
                        aVar.execute(new Void[]{(Void) null});
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.c7b:
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_confirm_clean_academy_cache);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new a(new String[]{CleanCacheActivity.G + CleanCacheActivity.O}, this.a.S, null).execute(new Void[]{(Void) null});
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.c7d:
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_confirm_clean_upgrade_cache);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new a(new String[]{CleanCacheActivity.G + CleanCacheActivity.N}, this.a.T, null).execute(new Void[]{(Void) null});
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.c7f:
                bVar = new Builder(this);
                bVar.setMessage(R.string.mine_settings_confirm_clean_moment_cache);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new a(new String[]{CleanCacheActivity.G + CleanCacheActivity.L, CleanCacheActivity.G + CleanCacheActivity.M}, this.a.Q, dji.pilot2.library.a.VideoAllDelete).execute(new Void[]{(Void) null});
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ CleanCacheActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.create().show();
                return;
            default:
                return;
        }
    }
}
