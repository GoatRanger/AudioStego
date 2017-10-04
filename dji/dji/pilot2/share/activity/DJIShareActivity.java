package dji.pilot2.share.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.f;
import com.facebook.h;
import com.facebook.o;
import com.facebook.share.model.ShareLinkContent;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.c.k;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.c.a.b;
import dji.pilot2.media.activity.DJIVideoPreveiwActivity;
import dji.pilot2.media.activity.DraftPhotoPreviewActivity;
import dji.pilot2.mine.activity.DraftActivity;
import dji.pilot2.mine.activity.SettingsActivity;
import dji.pilot2.mine.b.c;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.b.e$a;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.videolib.EditRecoverInfo;
import dji.pilot2.share.model.UploadEvent;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class DJIShareActivity extends DJIActivityNoFullScreen implements Callback, OnClickListener, j, k, b {
    public static final String K = "file_path";
    public static final String L = "file_type";
    public static final String M = "share_description";
    public static final String N = "share_title";
    public static final String O = "share_tag";
    public static final String P = "is_from_draft";
    public static final String Q = "default_tip";
    public static final String R = "default_content";
    public static final String S = "edit_info";
    public static final int T = 1;
    public static final int U = 2;
    public static final int V = 4;
    public static final int W = 0;
    public static final int X = 1;
    public static final int Y = 2;
    public static final int Z = 16;
    public static final int aa = 1;
    public static final int ab = 2;
    public static final int ac = 3;
    public static final int ad = 4;
    private boolean aA;
    private OnClickListener aB;
    private dji.pilot2.share.mode.b[] aC = null;
    private int aD = 0;
    private int aE;
    private Handler aF;
    private f aG;
    private com.facebook.share.widget.f aH;
    private dji.pilot2.share.d.a aI;
    private dji.pilot2.mine.e.a.a aJ;
    private boolean aK = false;
    private boolean aL = false;
    private View aM;
    private View aN;
    private String aO;
    private EditRecoverInfo ae;
    private ImageView af;
    private EditText ag;
    private EditText ah;
    private LinearLayout ai;
    private TextView aj;
    private View ak;
    private TextView al;
    private List<View> am;
    private LinearLayout an;
    private ViewGroup ao;
    private ScrollView ap;
    private String aq = "";
    private int ar = 0;
    private String as;
    private String at;
    private String au;
    private String av;
    private String aw;
    private int ax;
    private volatile boolean ay;
    private boolean az;

    public static class a extends AsyncTask<String, Void, Void> {
        int a;
        ImageView b;
        Bitmap c = null;
        Bitmap d = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        public a(int i, ImageView imageView, ImageView imageView2) {
            this.a = i;
            this.b = imageView;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void a(java.lang.String... r6) {
            /*
            r5 = this;
            r0 = 0;
            r0 = r6[r0];
            if (r0 == 0) goto L_0x0023;
        L_0x0005:
            r1 = new java.io.File;
            r1.<init>(r0);
            r1 = r1.exists();
            if (r1 == 0) goto L_0x0023;
        L_0x0010:
            r1 = r5.a;
            r2 = 1;
            if (r1 != r2) goto L_0x0025;
        L_0x0015:
            r1 = new android.graphics.BitmapFactory$Options;
            r1.<init>();
            r2 = 4;
            r1.inSampleSize = r2;
            r0 = android.graphics.BitmapFactory.decodeFile(r0, r1);
            r5.c = r0;
        L_0x0023:
            r0 = 0;
            return r0;
        L_0x0025:
            r1 = r5.a;
            r2 = 2;
            if (r1 != r2) goto L_0x0023;
        L_0x002a:
            r1 = new android.media.MediaMetadataRetriever;
            r1.<init>();
            r1.setDataSource(r0);	 Catch:{ IllegalArgumentException -> 0x0052 }
            r0 = r1.getFrameAtTime();	 Catch:{ IllegalArgumentException -> 0x0052 }
            if (r0 == 0) goto L_0x004e;
        L_0x0038:
            r2 = r0.getWidth();	 Catch:{ IllegalArgumentException -> 0x0052 }
            r3 = r0.getHeight();	 Catch:{ IllegalArgumentException -> 0x0052 }
            r2 = r2 / 4;
            r3 = r3 / 4;
            r4 = 0;
            r2 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r3, r4);	 Catch:{ IllegalArgumentException -> 0x0052 }
            r5.c = r2;	 Catch:{ IllegalArgumentException -> 0x0052 }
            r0.recycle();	 Catch:{ IllegalArgumentException -> 0x0052 }
        L_0x004e:
            r1.release();
            goto L_0x0023;
        L_0x0052:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x005a }
            r1.release();
            goto L_0x0023;
        L_0x005a:
            r0 = move-exception;
            r1.release();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.share.activity.DJIShareActivity.a.a(java.lang.String[]):java.lang.Void");
        }

        protected void a(Void voidR) {
            super.onPostExecute(voidR);
            this.b.setImageBitmap(this.c);
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("rxq1", "DJIShareActivity onCreate");
        setContentView(R.layout.v2_activity_share);
        m();
        h();
        i();
        if (!dji.pilot.usercenter.b.f.getInstance().c()) {
            Intent intent = new Intent(this, DJIAccountSignActivity.class);
            intent.putExtra(DJIAccountSignActivity.a, 1005);
            startActivityForResult(intent, 2);
        }
    }

    protected void onResume() {
        super.onResume();
        Log.i("rxq1", "DJIShareActivity onResume");
        Log.i("rxq1", "DJIShareActivity isSharing: " + this.aL);
        if (this.aL) {
            Intent intent = new Intent(this, DJIShareLaterActivity.class);
            intent.putExtra(DJIShareLaterActivity.V, this.ar);
            intent.putExtra(DJIShareLaterActivity.W, this.aO);
            intent.putExtra(DJIShareLaterActivity.X, this.aq);
            intent.putExtra(DJIShareLaterActivity.Y, this.aJ.c());
            intent.putExtra(DJIShareLaterActivity.Z, this.aJ.d());
            intent.putExtra(DJIShareLaterActivity.aa, this.aJ.f());
            startActivityForResult(intent, 16);
            this.aL = false;
        }
    }

    private void h() {
        this.aD = (int) getResources().getDimension(R.dimen.fj);
        this.aE = (int) getResources().getDimension(R.dimen.gw);
        Intent intent = getIntent();
        this.aq = intent.getStringExtra("file_path");
        this.ar = intent.getIntExtra(L, 0);
        this.as = intent.getStringExtra(M);
        this.at = intent.getStringExtra(N);
        this.au = intent.getStringExtra(O);
        this.av = intent.getStringExtra(Q);
        this.aw = intent.getStringExtra(R);
        this.aA = intent.getBooleanExtra(P, false);
        this.ae = (EditRecoverInfo) intent.getSerializableExtra(S);
        if (this.au == null) {
            this.au = "";
        }
        this.ay = false;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.ax = 4;
        } else {
            this.ax = 8;
        }
        this.am = new ArrayList();
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            this.aC = dji.pilot2.share.mode.b.e;
        } else {
            this.aC = dji.pilot2.share.mode.b.f;
        }
        this.az = false;
        this.aB = new OnClickListener(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent(this.a, DJIAddTagsActivity.class);
                if (this.a.au != null) {
                    intent.putExtra("tags", this.a.au);
                }
                this.a.startActivityForResult(intent, 3);
            }
        };
        this.aF = new Handler();
        this.aI = new dji.pilot2.share.d.a(this);
    }

    private void i() {
        this.aM = findViewById(R.id.cfu);
        this.aN = findViewById(R.id.cfv);
        this.al = (TextView) findViewById(R.id.cft);
        this.af = (ImageView) findViewById(R.id.cfz);
        this.ak = findViewById(R.id.cg0);
        this.ao = (ViewGroup) findViewById(R.id.cfy);
        this.ap = (ScrollView) findViewById(R.id.cfw);
        this.ag = (EditText) findViewById(R.id.cg2);
        this.ah = (EditText) findViewById(R.id.cg3);
        this.ap.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                DJILogHelper.getInstance().LOGE("Jackson", this.a.ap.getHeight() + "");
                DJILogHelper.getInstance().LOGE("Jackson", this.a.ao.getHeight() + "");
                this.a.ao.setMinimumHeight(this.a.ap.getHeight());
                DJILogHelper.getInstance().LOGE("Jackson", this.a.ao.getLayoutParams().height + "");
                this.a.ap.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.aI.b();
        this.ah.setHint(this.aI.a().b());
        this.ag.setHint(this.aI.a().a());
        if (this.av != null) {
            this.ag.setHint(this.av);
        }
        if (this.aw != null) {
            this.ah.setHint(this.aw);
        }
        this.an = (LinearLayout) findViewById(R.id.cg4);
        this.ai = (LinearLayout) findViewById(R.id.cg7);
        this.aj = (TextView) findViewById(R.id.cfx);
        this.ag.addTextChangedListener(new dji.pilot2.share.a(this, 100, this.ag, false));
        this.ah.addTextChangedListener(new dji.pilot2.share.a(this, 200, this.ah, false));
        LinearLayout linearLayout = null;
        for (int i = 0; i < this.aC.length; i++) {
            if (i % this.ax == 0) {
                linearLayout = new LinearLayout(this);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                linearLayout.setWeightSum((float) this.ax);
                layoutParams.topMargin = dji.publics.e.a.b(this, 5.0f);
                layoutParams.bottomMargin = dji.publics.e.a.b(this, 5.0f);
                linearLayout.setOrientation(0);
                this.ai.addView(linearLayout, layoutParams);
            }
            View inflate = LayoutInflater.from(this).inflate(R.layout.v2_share_button, null);
            a(inflate, i);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
            layoutParams2.weight = 1.0f;
            linearLayout.addView(inflate, layoutParams2);
            this.am.add(inflate);
        }
        ((View) this.am.get(0)).findViewById(R.id.czf).setVisibility(0);
        if (!(this.as == null || this.as.equals(""))) {
            this.ah.setText(this.as);
        }
        if (!(this.at == null || this.at.equals(""))) {
            this.ag.setText(this.at);
        }
        if (this.aq != null && new File(this.aq).exists()) {
            new a(this.ar, this.af, null).execute(new String[]{this.aq});
        }
        c(this.au);
        if (this.aA) {
            if (!new File(getFilesDir(), b(this.aq)).exists()) {
                this.al.setText(R.string.back);
            }
            this.al.setVisibility(0);
        } else if (this.ar == 2) {
            this.al.setVisibility(0);
        }
        if (this.ar == 2) {
            this.ak.setVisibility(0);
        }
    }

    private void a(View view, int i) {
        ((ImageView) view.findViewById(R.id.cze)).setImageResource(this.aC[i].b);
        view.setTag(Integer.valueOf(i));
        view.setOnClickListener(this);
    }

    private void c(String str) {
        this.an.removeAllViews();
        this.an.addView(d(getString(R.string.v2_add_tags_no_tags_info)));
        if (str != null || !str.equals("")) {
            for (String str2 : str.split(",")) {
                if (!(str2 == null || str2.equals(""))) {
                    this.an.addView(d(str2));
                }
            }
        }
    }

    private TextView d(String str) {
        int b = dji.publics.e.a.b(this, 2.0f);
        int b2 = dji.publics.e.a.b(this, 10.0f);
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.v2_share_add_tag_white_bg);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (str.equalsIgnoreCase(getResources().getString(R.string.v2_add_tags_no_tags_info))) {
            DJILogHelper.getInstance().LOGI("bob", "generateTagTextView add tag");
            Drawable drawable = getResources().getDrawable(R.drawable.v2_explore_publishartwork_add);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(b);
        }
        int b3 = dji.publics.e.a.b(this, 2.0f);
        layoutParams.rightMargin = b3;
        layoutParams.leftMargin = b3;
        textView.setText(str);
        textView.setTextColor(Color.parseColor("#9B9B9B"));
        textView.setTextSize(2, 12.0f);
        textView.setPadding(b2, b, b2, b);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setMinWidth(dji.publics.e.a.b(this, 50.0f));
        textView.setOnClickListener(this.aB);
        return textView;
    }

    public void onClick(View view) {
        Integer num = (Integer) view.getTag();
        if (num != null) {
            int intValue = num.intValue();
            if (this.aC.length > intValue && this.aC[intValue].d == dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM && !p.b(this, "com.instagram.android")) {
                Toast.makeText(this, R.string.v2_share_instagram_tip, 1).show();
                return;
            }
        }
        for (int i = 0; i < this.am.size(); i++) {
            ((View) this.am.get(i)).findViewById(R.id.czf).setVisibility(4);
        }
        view.findViewById(R.id.czf).setVisibility(0);
    }

    public dji.pilot2.share.e.a.b g() {
        for (int i = 0; i < this.am.size(); i++) {
            if (((View) this.am.get(i)).findViewById(R.id.czf).getVisibility() == 0) {
                return this.aC[i].d;
            }
        }
        return null;
    }

    private void e(String str) {
        try {
            InputStream openFileInput = openFileInput(str);
            ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput);
            EditRecoverInfo editRecoverInfo = (EditRecoverInfo) objectInputStream.readObject();
            objectInputStream.close();
            openFileInput.close();
            Intent intent = new Intent(this, DJIMultiMomentEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DJIMultiMomentEditActivity.X, editRecoverInfo);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.cft:
                setResult(1);
                finish();
                String b = b(this.aq);
                if (new File(getFilesDir(), b).exists()) {
                    e(b);
                    return;
                }
                return;
            case R.id.cfu:
                j();
                return;
            case R.id.cfv:
                finish();
                return;
            case R.id.cfx:
                if (e.getInstance().j() == e$a.INVALLID) {
                    new dji.pilot2.publics.object.b(this).setMessage(R.string.v2_explore_no_network_detail).setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ DJIShareActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                    return;
                } else if (e.getInstance().j() != e$a.CELLULAR || e.getInstance().h()) {
                    String obj = this.ag.getText().toString();
                    String obj2 = this.ah.getText().toString();
                    if (obj == null || obj.equals("")) {
                        obj = this.ag.getHint().toString();
                    }
                    if (obj2 == null || obj2.equals("")) {
                        obj2 = this.ah.getHint().toString();
                    }
                    if (this.au == null) {
                        this.au = "";
                    }
                    if (this.ar == 1) {
                        c.getInstance().a(this.aq, obj, obj2, this.au, "photo");
                    } else if (this.ar == 2) {
                        c.getInstance().a(this.aq, obj, obj2, this.au, "video");
                    }
                    dji.pilot2.nativeexplore.c.c.a(this, this.aq, obj, obj2, this.au, new dji.pilot2.nativeexplore.c.c.a(this) {
                        final /* synthetic */ DJIShareActivity a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.f();
                        }

                        public void a(String str, String str2, String str3, String str4) {
                            this.a.a(str);
                        }

                        public void b() {
                            this.a.b();
                        }

                        public void c() {
                            this.a.a();
                        }
                    }, this.ar);
                    return;
                } else {
                    new dji.pilot2.publics.object.b(this).setMessage(R.string.v2_cellular_upload_alert_message).setPositiveButton(R.string.v2_cellular_upload_alert_yes, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ DJIShareActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.setClass(this.a, SettingsActivity.class);
                            this.a.startActivity(intent);
                        }
                    }).setNegativeButton(R.string.v2_cellular_upload_alert_cancel, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ DJIShareActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                    return;
                }
            case R.id.cfz:
            case R.id.cg0:
                File file;
                if (this.ar == 2 && this.aq != null) {
                    DJILogHelper.getInstance().LOGI("bob", "filePath = " + this.aq);
                    file = new File(this.aq);
                    if (file != null && file.exists()) {
                        DJILogHelper.getInstance().LOGI("bob", "filePath is exist");
                        DJIVideoPreveiwActivity.a(this, this.aq, dji.pilot.publics.objects.a.a);
                        return;
                    }
                    return;
                } else if (this.ar == 1 && this.aq != null) {
                    file = new File(this.aq);
                    if (file != null && file.exists()) {
                        Intent intent = new Intent(this, DraftPhotoPreviewActivity.class);
                        intent.putExtra(DraftPhotoPreviewActivity.a, this.aq);
                        startActivity(intent);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.aG.a(i, i2, intent);
        switch (i) {
            case 2:
                if (this.au == null) {
                    this.au = "";
                }
                if (!dji.pilot.usercenter.b.f.getInstance().c()) {
                    if (this.ar == 1) {
                        c.getInstance().a(this.aq, "", "", this.au, "photo");
                    } else if (this.ar == 2) {
                        c.getInstance().a(this.aq, "", "", this.au, "video");
                    }
                    setResult(2);
                    finish();
                    return;
                }
                return;
            case 3:
                if (intent != null) {
                    this.au = intent.getStringExtra("tags");
                    c(this.au);
                    return;
                }
                return;
            case 16:
                if (i2 == 32) {
                    finish();
                } else if (i2 == 16) {
                    this.aj.setText(getResources().getString(R.string.v2_start_share, new Object[]{Integer.valueOf(0)}));
                }
                this.aL = false;
                return;
            default:
                return;
        }
    }

    private void j() {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        bVar.setMessage(R.string.v2_photo_upload_confirm_title);
        bVar.setPositiveButton(R.string.v2_photo_upload_confirm_right_btn, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = this.a.ag.getText().toString();
                String obj2 = this.a.ah.getText().toString();
                if (obj == null || obj.equals("")) {
                    obj = this.a.ag.getHint().toString();
                }
                if (obj2 == null || obj2.equals("")) {
                    obj2 = this.a.ah.getHint().toString();
                }
                if (this.a.au == null) {
                    this.a.au = "";
                }
                if (this.a.ar == 1) {
                    dji.pilot.fpv.d.e.b(j.y);
                    c.getInstance().a(this.a.aq, obj, obj2, this.a.au, "photo");
                } else if (this.a.ar == 2) {
                    dji.pilot.fpv.d.e.b(k.bQ_);
                    c.getInstance().a(this.a.aq, obj, obj2, this.a.au, "video");
                    if (this.a.ae != null) {
                        this.a.ae.setFromDraft(true);
                        this.a.a(DJIShareActivity.b(this.a.aq), this.a.ae);
                    }
                }
                this.a.setResult(2);
                this.a.finish();
                this.a.startActivity(new Intent(this.a, DraftActivity.class));
            }
        });
        bVar.setNegativeButton(R.string.v2_photo_upload_confirm_left_btn, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    public void onBackPressed() {
    }

    private boolean k() {
        if (this.au != null) {
            if (this.au.contains("2016") && this.au.contains("I will")) {
                return true;
            }
            if (this.au.contains("2016") && this.au.contains("我要")) {
                return true;
            }
        }
        return false;
    }

    private boolean l() {
        if (this.au == null || (!this.au.contains("年味") && !this.au.contains("回家的路") && !this.au.contains("亲人"))) {
            return false;
        }
        return true;
    }

    public void a() {
    }

    public void a(String str) {
        String str2;
        this.aK = true;
        this.aM.setVisibility(4);
        this.aN.setVisibility(0);
        Log.i("rxq1", "work id: " + str);
        this.aO = str;
        if (this.ar == 1) {
            str2 = "photo";
        } else {
            str2 = "video";
        }
        String n = c.getInstance().b(this.aq).n();
        String str3 = "";
        if (k()) {
            str2 = dji.pilot2.utils.k.b(str2, str, "iwill");
        } else if (l()) {
            str2 = dji.pilot2.utils.k.b(str2, str, "springfestival");
        } else {
            str2 = dji.pilot2.utils.k.b(str2, str, null);
        }
        this.aJ = new dji.pilot2.mine.e.a.a();
        this.aJ.c = c.getInstance().b(this.aq).b();
        this.aJ.d = c.getInstance().b(this.aq).c();
        this.aJ.f = n;
        if (dji.pilot.usercenter.b.f.getInstance().c()) {
            this.aJ.b = str2 + "?account_id=" + dji.pilot.usercenter.b.f.getInstance().i();
        } else {
            this.aJ.b = str2;
        }
        DJILogHelper.getInstance().LOGI("bob", "share activity shareinfo referenceurl = " + this.aJ.b);
        b(this.aJ);
        dji.thirdparty.a.c.a().e(new UploadEvent(this.aO, this.aq, dji.pilot2.share.model.UploadEvent.a.UPLOAD_SUCCESS));
        setResult(4);
    }

    public void b() {
        Message message = new Message();
        message.what = 2;
        Bundle bundle = new Bundle();
        bundle.putString(dji.pilot2.share.mode.a.m, this.aq);
        message.setData(bundle);
        DraftActivity.K.sendMessage(message);
        this.az = false;
        this.aF.postDelayed(new Runnable(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.aj.setText(R.string.v2_upload_restart);
            }
        }, 2000);
    }

    public void f() {
    }

    public void a(int i) {
        this.aj.setText(getResources().getString(R.string.v2_upload_progress, new Object[]{Integer.valueOf(i)}));
        if (i == 100) {
            this.az = true;
        }
    }

    private void a(String str, EditRecoverInfo editRecoverInfo) {
        Log.i("rxq1", "share activity saveEditInfo");
        try {
            OutputStream openFileOutput = openFileOutput(str, 0);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
            objectOutputStream.writeObject(this.ae);
            objectOutputStream.close();
            openFileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(d.t);
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf2 == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("rxq1", "DJIShareActivity onDestroy");
        if (this.aK) {
            Message message = new Message();
            message.what = 3;
            Bundle bundle = new Bundle();
            bundle.putString(dji.pilot2.share.mode.a.m, this.aq);
            bundle.putString(dji.pilot2.share.mode.a.q, this.aO);
            message.setData(bundle);
            DraftActivity.K.sendMessage(message);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void m() {
        o.a(getApplicationContext());
        this.aG = com.facebook.f.a.a();
        this.aH = new com.facebook.share.widget.f(this);
        this.aH.a(this.aG, new h<com.facebook.share.c.a>(this) {
            final /* synthetic */ DJIShareActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((com.facebook.share.c.a) obj);
            }

            public void a(com.facebook.share.c.a aVar) {
            }

            public void onCancel() {
                this.a.aj.setText(this.a.getResources().getString(R.string.v2_start_share, new Object[]{Integer.valueOf(0)}));
                this.a.aL = false;
            }

            public void onError(com.facebook.k kVar) {
                this.a.aj.setText(this.a.getResources().getString(R.string.v2_start_share, new Object[]{Integer.valueOf(0)}));
                this.a.aL = false;
            }
        });
    }

    private void a(dji.pilot2.mine.e.a.a aVar) {
        if (com.facebook.share.widget.f.a(ShareLinkContent.class)) {
            this.aH.b(((com.facebook.share.model.ShareLinkContent.a) new com.facebook.share.model.ShareLinkContent.a().b(aVar.c()).a(aVar.d()).a(Uri.parse(aVar.b()))).b());
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 2:
            case 3:
            case 4:
                DJILogHelper.getInstance().LOGI("rxq", "MSG_HANLER_SHARE_CANCEL MSG_HANLER_SHARE_FAIL");
                this.aj.setText(getResources().getString(R.string.v2_start_share, new Object[]{Integer.valueOf(0)}));
                this.aL = false;
                break;
        }
        return false;
    }

    private void b(dji.pilot2.mine.e.a.a aVar) {
        this.aL = true;
        dji.pilot2.share.d.b bVar = new dji.pilot2.share.d.b(this);
        bVar.a(aVar);
        bVar.a(aVar.b());
        if (g() == dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM) {
            if (p.b(this, "com.instagram.android")) {
                dji.pilot2.share.e.a.a aVar2 = dji.pilot2.share.e.a.a.CONTENT_IMG;
                if (this.ar == 1) {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_IMG;
                } else {
                    aVar2 = dji.pilot2.share.e.a.a.CONTENT_VIDEO;
                }
                dji.pilot2.share.f.b.a((Context) this, this.aq, aVar2, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                return;
            }
            Toast.makeText(this, R.string.v2_share_instagram_tip, 1).show();
        } else if (g() == dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK) {
            a(aVar);
        } else if (this.ar == 1) {
            bVar.b(g());
        } else {
            bVar.a(g());
        }
    }
}
