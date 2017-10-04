package dji.pilot2.nativeexplore.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.c.a.b;
import dji.pilot2.nativeexplore.c.c;
import dji.pilot2.share.activity.DJIAddTagsActivity;
import dji.pilot2.share.activity.DJIShareLaterActivity;
import dji.pilot2.share.d.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.IOException;

public class PublishArtworkActivity extends DJIActivityNoFullScreen implements OnClickListener, b {
    public static final String a = "PICPATH";
    private int A;
    private int B;
    private OnClickListener C;
    private String D;
    private a E;
    private DJIStateImageView b;
    private DJITextView c;
    private DJIImageView d;
    private DJIEditText t;
    private DJIEditText u;
    private DJILinearLayout v;
    private DJIStateTextView w;
    private DJIStateTextView x;
    private String y;
    private String z = "";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_explore_publishartwork);
        this.D = getIntent().getStringExtra(a);
        this.y = this.D;
        File file = new File(this.D);
        if (this.D == null || !file.exists()) {
            DJILogHelper.getInstance().LOGI("bob", "PublishArtworkActivity mPath is null");
            finish();
            return;
        }
        g();
    }

    private TextView b(String str) {
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.v2_share_add_tag_white_bg);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (str.equalsIgnoreCase(getResources().getString(R.string.v2_add_tags_no_tags_info))) {
            DJILogHelper.getInstance().LOGI("bob", "generateTagTextView add tag");
            Drawable drawable = getResources().getDrawable(R.drawable.v2_explore_publishartwork_add);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(this.A);
        }
        int i = this.A;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        textView.setText(str);
        textView.setTextColor(Color.parseColor("#9B9B9B"));
        textView.setTextSize(2, 12.0f);
        textView.setPadding(this.B, this.A, this.B, this.A);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setMinWidth(dji.publics.e.a.b(this, 50.0f));
        textView.setOnClickListener(this.C);
        return textView;
    }

    private void c(String str) {
        this.v.removeAllViews();
        this.v.addView(b(getString(R.string.v2_add_tags_no_tags_info)));
        if (str != null && !str.equals("")) {
            for (String str2 : str.split(",")) {
                if (!(str2 == null || str2.equals(""))) {
                    this.v.addView(b(str2));
                }
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 2:
                if (!f.getInstance().c()) {
                    finish();
                    return;
                }
                return;
            case 3:
                if (intent != null) {
                    this.z = intent.getStringExtra("tags");
                    c(this.z);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void g() {
        this.E = new a(this);
        this.E.b();
        this.A = dji.publics.e.a.b(this, 2.0f);
        this.B = dji.publics.e.a.b(this, 10.0f);
        h();
        this.d = (DJIImageView) findViewById(R.id.cmk);
        this.v = (DJILinearLayout) findViewById(R.id.cmn);
        this.w = (DJIStateTextView) findViewById(R.id.cmo);
        this.x = (DJIStateTextView) findViewById(R.id.cmp);
        this.t = (DJIEditText) findViewById(R.id.cml);
        this.u = (DJIEditText) findViewById(R.id.cmm);
        this.t.setHint(this.E.a().a());
        this.u.setHint(this.E.a().b());
        if (this.D != null) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.D, options);
            if (options.outWidth > 1000) {
                options.inSampleSize = options.outWidth / 1000;
                if (options.inSampleSize > 8) {
                    options.inSampleSize = 8;
                } else if (options.inSampleSize < 1) {
                    options.inSampleSize = 1;
                }
            } else {
                options.inSampleSize = 1;
            }
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            try {
                Bitmap decodeFile = BitmapFactory.decodeFile(this.D, options);
                if (decodeFile != null) {
                    Bitmap a = dji.pilot2.utils.a.a(decodeFile, new ExifInterface(this.D));
                    if (decodeFile != a) {
                        decodeFile.recycle();
                    } else {
                        a = decodeFile;
                    }
                    this.y = a(a);
                    this.d.setImageBitmap(a);
                }
            } catch (OutOfMemoryError e) {
                Toast.makeText(this, getString(R.string.v2_out_of_memory_info), 1).show();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        this.C = new OnClickListener(this) {
            final /* synthetic */ PublishArtworkActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent(this.a, DJIAddTagsActivity.class);
                if (this.a.z != null) {
                    intent.putExtra("tags", this.a.z);
                }
                this.a.startActivityForResult(intent, 3);
            }
        };
        c("");
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PublishArtworkActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!f.getInstance().c()) {
                    this.a.startActivityForResult(new Intent(this.a, DJIAccountSignActivity.class), 2);
                } else if (PublishArtworkActivity.b(this.a)) {
                    String obj = this.a.t.getText().toString();
                    String obj2 = this.a.u.getText().toString();
                    DJILogHelper.getInstance().LOGI("bob", "post title=" + obj + " desc=" + obj2);
                    if (obj == null || obj.equals("")) {
                        obj = this.a.t.getHint().toString();
                    }
                    if (obj2 == null || obj2.equals("")) {
                        obj2 = this.a.u.getHint().toString();
                    }
                    DJILogHelper.getInstance().LOGI("bob", "after post title=" + obj + " desc=" + obj2);
                    if (obj != null && !obj.equals("") && obj2 != null && !obj2.equals("")) {
                        c.a(this.a, this.a.D, obj, obj2, this.a.z, new c.a(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                            }

                            public void a(String str, String str2, String str3, String str4) {
                                Intent intent = new Intent(this.a.a, DJIShareLaterActivity.class);
                                intent.putExtra(DJIShareLaterActivity.V, 1);
                                intent.putExtra(DJIShareLaterActivity.W, str);
                                intent.putExtra(DJIShareLaterActivity.X, str2);
                                intent.putExtra(DJIShareLaterActivity.Y, str3);
                                intent.putExtra(DJIShareLaterActivity.Z, str4);
                                intent.putExtra(DJIShareLaterActivity.aa, this.a.a.y);
                                this.a.a.startActivity(intent);
                                this.a.a.finish();
                            }

                            public void b() {
                            }

                            public void c() {
                            }
                        });
                    }
                } else {
                    Toast.makeText(this.a, R.string.account_network_error, 0).show();
                }
            }
        });
    }

    private static boolean b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    private void h() {
        this.b = (DJIStateImageView) findViewById(R.id.c42);
        this.c = (DJITextView) findViewById(R.id.c43);
        this.c.setText(R.string.v2_explore_guide_post_text);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PublishArtworkActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(android.graphics.Bitmap r6) {
        /*
        r5 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = dji.pilot2.b.a;
        r1 = r1.a();
        r1 = r1.getExternalCacheDir();
        r0 = r0.append(r1);
        r1 = "/";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = "";
        r3 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r2.<init>();	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r2 = r5.D;	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r2 = com.dji.frame.c.a.a(r2);	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r2 = ".jpg";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x005e, all -> 0x0071 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0080, all -> 0x0071 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0080, all -> 0x0071 }
        r1 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x0083 }
        r3 = 80;
        r6.compress(r1, r3, r2);	 Catch:{ Exception -> 0x0083 }
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r2.close();	 Catch:{ IOException -> 0x0059 }
    L_0x004e:
        r1 = "";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0058;
    L_0x0056:
        r0 = r5.D;
    L_0x0058:
        return r0;
    L_0x0059:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004e;
    L_0x005e:
        r0 = move-exception;
        r2 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0063:
        r1.printStackTrace();	 Catch:{ all -> 0x007d }
        if (r2 == 0) goto L_0x004e;
    L_0x0068:
        r2.close();	 Catch:{ IOException -> 0x006c }
        goto L_0x004e;
    L_0x006c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004e;
    L_0x0071:
        r0 = move-exception;
    L_0x0072:
        if (r3 == 0) goto L_0x0077;
    L_0x0074:
        r3.close();	 Catch:{ IOException -> 0x0078 }
    L_0x0077:
        throw r0;
    L_0x0078:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0077;
    L_0x007d:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0072;
    L_0x0080:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0063;
    L_0x0083:
        r1 = move-exception;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.nativeexplore.activity.PublishArtworkActivity.a(android.graphics.Bitmap):java.lang.String");
    }

    protected void onPause() {
        super.onPause();
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c42:
                finish();
                return;
            default:
                return;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a() {
        DJILogHelper.getInstance().LOGI("bob", "upload start");
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGI("bob", "upload success");
    }

    public void b() {
        DJILogHelper.getInstance().LOGI("bob", "upload onUploadFailed");
    }

    public void f() {
        DJILogHelper.getInstance().LOGI("bob", "upload onCancel");
    }

    public void a(int i) {
        DJILogHelper.getInstance().LOGI("bob", "upload onUploadProgress progress=" + i);
    }
}
