package dji.pilot2.share.b;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dji.frame.c.c;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.utils.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends dji.pilot2.publics.object.a implements OnClickListener {
    private View b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private LinearLayout f;
    private dji.pilot2.mine.e.a.a g = null;
    private String h = null;
    private dji.pilot2.share.e.a.a i = dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR;
    private a j = a.NONE;
    private dji.pilot2.share.mode.b[] k;

    public enum a {
        NONE,
        EXPLORE_MINE,
        EDIT_UPLOAD,
        GIFT_SHARE
    }

    public class b {
        public dji.pilot2.share.e.a.b a;
        public dji.pilot2.mine.e.a.a b;
        public dji.pilot2.share.e.a.a c;
        public a d;
        final /* synthetic */ b e;

        public b(b bVar, dji.pilot2.share.e.a.b bVar2, dji.pilot2.mine.e.a.a aVar) {
            this.e = bVar;
            this.a = bVar2;
            this.b = aVar;
        }

        public void a(dji.pilot2.share.e.a.a aVar) {
            this.c = aVar;
        }
    }

    public b(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public b(Context context, int i) {
        super(context, i);
    }

    public b(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_new_share_dialog_layout);
        a();
        b();
    }

    private void a() {
        if (this.a.getResources().getConfiguration().locale.getCountry().equals("CN")) {
            this.k = dji.pilot2.share.mode.b.g;
        } else {
            this.k = dji.pilot2.share.mode.b.h;
        }
    }

    private void b() {
        this.b = findViewById(R.id.cwb);
        this.c = (ImageView) findViewById(R.id.cw8);
        this.d = (TextView) findViewById(R.id.cw9);
        this.e = (TextView) findViewById(R.id.cw_);
        this.f = (LinearLayout) findViewById(R.id.cwa);
        if (this.g != null) {
            this.d.setText(this.g.c());
            this.e.setText(this.g.d());
            if (this.g.f() != null && !this.g.f().equals("")) {
                this.c.setImageBitmap(BitmapFactory.decodeFile(this.g.f()));
            } else if (this.g.a() != null) {
                DJILogHelper.getInstance().LOGI("Lyric", "share image url: " + this.g.a());
                ImageLoader.getInstance().loadImage(this.g.a(), new ImageLoadingListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onLoadingStarted(String str, View view) {
                    }

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                    }

                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        String str2;
                        Exception e;
                        OutputStream outputStream;
                        Throwable th;
                        FileOutputStream fileOutputStream = null;
                        this.a.c.setImageBitmap(bitmap);
                        if (bitmap != null) {
                            try {
                                str2 = (dji.pilot2.b.a.a().getExternalCacheDir() + d.t) + com.dji.frame.c.a.a(str) + dji.pilot2.main.a.a.n;
                                try {
                                    OutputStream fileOutputStream2 = new FileOutputStream(str2);
                                    try {
                                        bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2);
                                        this.a.g.f = str2;
                                        if (fileOutputStream2 != null) {
                                            try {
                                                fileOutputStream2.close();
                                            } catch (IOException e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        outputStream = fileOutputStream2;
                                        try {
                                            e.printStackTrace();
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (IOException e22) {
                                                    e22.printStackTrace();
                                                }
                                            }
                                            if (str2 == null) {
                                                new File(str2).deleteOnExit();
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (IOException e4) {
                                                    e4.printStackTrace();
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        outputStream = fileOutputStream2;
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        throw th;
                                    }
                                } catch (Exception e5) {
                                    e = e5;
                                    e.printStackTrace();
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (str2 == null) {
                                        new File(str2).deleteOnExit();
                                    }
                                }
                            } catch (Exception e6) {
                                e = e6;
                                str2 = null;
                                e.printStackTrace();
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (str2 == null) {
                                    new File(str2).deleteOnExit();
                                }
                            }
                            if (str2 == null) {
                                new File(str2).deleteOnExit();
                            }
                        }
                    }

                    public void onLoadingCancelled(String str, View view) {
                    }
                });
            }
        }
        for (int i = 0; i < this.k.length; i++) {
            if (this.k[i].d != dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM) {
                View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_share_dialog_item, this.f, false);
                TextView textView = (TextView) inflate.findViewById(R.id.czj);
                ((ImageView) inflate.findViewById(R.id.cjd)).setImageResource(this.k[i].b);
                textView.setText(this.k[i].a);
                inflate.setTag(this.k[i]);
                inflate.setOnClickListener(this);
                this.f.addView(inflate);
            }
        }
        this.b.setOnClickListener(this);
    }

    public void a(dji.pilot2.mine.e.a.a aVar) {
        this.g = aVar;
    }

    public void a(dji.pilot2.share.e.a.a aVar) {
        this.i = aVar;
    }

    public void a(String str) {
        this.h = str;
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    private void b(final dji.pilot2.share.e.a.a aVar) {
        c.a().a("http://api.t.sina.com.cn/short_url/shorten.json?source=3268350148&url_long=" + this.g.b(), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                String str2;
                String b = this.b.g.b();
                if (str != null) {
                    try {
                        String optString;
                        JSONArray jSONArray = new JSONArray(str);
                        if (jSONArray.length() > 0) {
                            JSONObject jSONObject = (JSONObject) jSONArray.get(0);
                            if (jSONObject.optString("url_long").equals(this.b.g.b())) {
                                optString = jSONObject.optString("url_short");
                                str2 = optString;
                                DJILogHelper.getInstance().LOGI("bob", "shorturl = " + str2);
                                dji.pilot2.share.f.b.a(this.b.a, this.b.g.c(), this.b.g.d() + "\n" + str2, this.b.g.f(), this.b.h, dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO, aVar, this.b.j);
                            }
                        }
                        optString = b;
                        str2 = optString;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    DJILogHelper.getInstance().LOGI("bob", "shorturl = " + str2);
                    dji.pilot2.share.f.b.a(this.b.a, this.b.g.c(), this.b.g.d() + "\n" + str2, this.b.g.f(), this.b.h, dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO, aVar, this.b.j);
                }
                str2 = b;
                DJILogHelper.getInstance().LOGI("bob", "shorturl = " + str2);
                dji.pilot2.share.f.b.a(this.b.a, this.b.g.c(), this.b.g.d() + "\n" + str2, this.b.g.f(), this.b.h, dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO, aVar, this.b.j);
            }

            public void a(Throwable th, int i, String str) {
                dji.pilot2.share.f.b.a(this.b.a, this.b.g.c(), this.b.g.d() + "\n" + this.b.g.b(), this.b.g.f(), this.b.h, dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO, aVar, this.b.j);
            }
        });
    }

    public void onClick(View view) {
        if (view.getTag() == null || !(view.getTag() instanceof dji.pilot2.share.mode.b)) {
            switch (view.getId()) {
                case R.id.cwb:
                    dismiss();
                    return;
                default:
                    return;
            }
        }
        dji.pilot2.share.mode.b bVar = (dji.pilot2.share.mode.b) view.getTag();
        DJILogHelper.getInstance().LOGI("bob", "NewShareDialog onClick ThumbnailPath = " + this.g.f() + " platformResource.type=" + bVar.d.toString());
        if (this.g.f() == null) {
            if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO) {
                b(dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR);
            } else if (bVar.d != dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM) {
                dji.pilot2.share.f.b.a(this.a, this.g.c(), this.g.d() + "\n" + this.g.b(), this.g.f(), this.h, bVar.d, dji.pilot2.share.e.a.a.CONTENT_LINK_ADDR, this.j);
            }
        } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK) {
            b bVar2 = new b(this, bVar.d, this.g);
            bVar2.a(this.i);
            bVar2.d = this.j;
            dji.thirdparty.a.c.a().e(bVar2);
        } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM) {
            if (p.b(this.a, "com.instagram.android")) {
                dji.pilot2.share.f.b.a(this.a, this.g.f(), dji.pilot2.share.e.a.a.CONTENT_IMG, a.EDIT_UPLOAD);
                return;
            }
            Toast.makeText(this.a, R.string.v2_share_instagram_tip, 1).show();
        } else if (bVar.d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO) {
            b(this.i);
        } else {
            dji.pilot2.share.f.b.a(this.a, this.g.c(), this.g.d() + "\n" + this.g.b(), this.g.f(), this.h, bVar.d, this.i, this.j);
        }
    }
}
