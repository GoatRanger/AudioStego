package dji.pilot.usercenter.activity;

import android.content.Context;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.SpannableString;
import com.dji.frame.c.l;
import dji.pilot.R;
import dji.pilot.publics.d.a.b;
import dji.pilot.publics.e.d;
import dji.pilot.publics.objects.c;
import dji.pilot.usercenter.f.e;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.util.Date;

public class a extends c {
    private DJITextView a;
    private DJITextView b;
    private DJITextView c;
    private DJITextView d;
    private DJITextView e;
    private DJITextView f;
    private DJITextView g;
    private DJITextView h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;

    public a(Context context) {
        this(context, R.style.c6);
    }

    public a(Context context, int i) {
        super(context, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        b();
    }

    public void a(String str) {
        this.q = str;
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            this.i = exifInterface.getAttribute("DateTime");
            if (d.a(this.i)) {
                File file = new File(str);
                if (dji.pilot.usercenter.f.c.a(file)) {
                    this.i = l.a(new Date(file.lastModified()), e.c);
                }
            }
            this.j = exifInterface.getAttribute(dji.pilot2.utils.a.a.m);
            this.k = exifInterface.getAttributeInt("ImageWidth", 0) + "X" + exifInterface.getAttributeInt("ImageLength", 0);
            this.l = exifInterface.getAttribute("Make");
            this.m = exifInterface.getAttribute("FNumber");
            this.n = exifInterface.getAttribute("ExposureTime");
            this.o = exifInterface.getAttribute("ExposureTime");
            this.p = exifInterface.getAttribute("ISOSpeedRatings");
        } catch (Exception e) {
        }
    }

    private void b() {
        setContentView(R.layout.media_info_view);
        this.a = (DJITextView) findViewById(R.id.be1);
        this.b = (DJITextView) findViewById(R.id.be2);
        this.c = (DJITextView) findViewById(R.id.be3);
        this.d = (DJITextView) findViewById(R.id.be4);
        this.e = (DJITextView) findViewById(R.id.be5);
        this.f = (DJITextView) findViewById(R.id.be6);
        this.g = (DJITextView) findViewById(R.id.be7);
        this.h = (DJITextView) findViewById(R.id.be8);
        this.f.go();
    }

    protected void onStart() {
        super.onStart();
        Context context = this.N;
        this.a.setText(a(context.getString(R.string.media_info_created), b(this.i), 0));
        this.b.setText(a(context.getString(R.string.media_info_model), b(this.j), 0));
        this.c.setText(a(context.getString(R.string.media_info_dimensions), this.k, 0));
        this.d.setText(a(context.getString(R.string.media_info_make), b(this.l), 0));
        this.e.setText(a(context.getString(R.string.media_info_f_number), b(this.m), 0));
        this.f.setText(a(context.getString(R.string.media_info_exposure_program), this.n, 0));
        this.g.setText(a(context.getString(R.string.media_info_exposure_time), b(this.o), 0));
        this.h.setText(a(context.getString(R.string.media_info_iso), b(this.p), 0));
    }

    protected void onStop() {
        super.onStop();
    }

    private SpannableString a(String str, String str2, int i) {
        SpannableString spannableString = new SpannableString(str + str2);
        if (i == 0) {
            spannableString.setSpan(new b(DJITextView.NBOLD, 0, 0, getContext().getResources().getColor(R.color.om)), str.length(), spannableString.length(), 18);
        } else if (i == 1) {
        }
        return spannableString;
    }

    private String b(String str) {
        return str != null ? str : "";
    }

    protected void onCreate(Bundle bundle) {
        a(-2, -2, 0, 53, true, true);
    }
}
