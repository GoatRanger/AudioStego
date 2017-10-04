package dji.pilot2.upgrade;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

public class a {
    private boolean a;
    private Runnable b;
    private Options c;
    private Handler d;
    private int e;
    private boolean f;
    private int g;
    private ArrayList<a> h;
    private byte[] i;
    private Context j;
    private ImageView k;

    private static class a {
        byte[] a;
        int b;
        Drawable c;

        private a() {
            this.a = null;
            this.b = 100;
            this.c = null;
        }
    }

    public a(ImageView imageView) {
        this.g = 0;
        this.h = new ArrayList();
        this.i = new byte[1024];
        this.c = new Options();
        this.c.inPreferredConfig = Config.RGB_565;
        this.d = new Handler();
        this.k = imageView;
        this.j = this.k.getContext();
    }

    public a(ImageView imageView, Options options) {
        this.g = 0;
        this.h = new ArrayList();
        this.i = new byte[1024];
        this.c = options;
        this.d = new Handler();
        this.k = imageView;
        this.j = this.k.getContext();
    }

    public void a(int i, Runnable runnable) {
        d();
        this.b = runnable;
        a(i);
        this.a = true;
        b(this.e);
    }

    public void a() {
        this.a = true;
        b(this.e);
    }

    public boolean b() {
        this.a = false;
        return false;
    }

    public void c() {
        d();
        if (this.h != null) {
            this.h.clear();
            this.h = null;
        }
        this.i = null;
    }

    private void d() {
        if (this.h != null) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (!(aVar == null || aVar.c == null || ((BitmapDrawable) aVar.c).getBitmap() == null)) {
                    ((BitmapDrawable) aVar.c).getBitmap().recycle();
                    aVar.c = null;
                    aVar.a = null;
                }
            }
        }
        this.a = false;
        this.e = 0;
    }

    private void a(int i) {
        this.h.clear();
        XmlResourceParser xml = this.j.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    if (xml.getName().equals("item")) {
                        a aVar = new a();
                        for (eventType = 0; eventType < xml.getAttributeCount(); eventType++) {
                            if (xml.getAttributeName(eventType).equals("drawable")) {
                                aVar.a = a(this.j, Integer.parseInt(xml.getAttributeValue(eventType).substring(1)));
                            } else if (xml.getAttributeName(eventType).equals("duration")) {
                                aVar.b = xml.getAttributeIntValue(eventType, 1000);
                            }
                        }
                        this.h.add(aVar);
                    } else if (xml.getName().equals("animation-list")) {
                        for (eventType = 0; eventType < xml.getAttributeCount(); eventType++) {
                            if (xml.getAttributeName(eventType).equals("oneshot")) {
                                this.f = xml.getAttributeBooleanValue(eventType, true);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        this.g = this.h.size();
    }

    private byte[] a(Context context, int i) {
        byte[] bArr;
        InputStream openRawResource = context.getResources().openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = openRawResource.read(this.i);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(this.i, 0, read);
            } catch (Exception e) {
                Exception exception = e;
                bArr = null;
                Exception exception2 = exception;
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        try {
            openRawResource.close();
            byteArrayOutputStream.close();
        } catch (Exception e2) {
            exception2 = e2;
            exception2.printStackTrace();
            return bArr;
        }
        return bArr;
    }

    private void b(int i) {
        a aVar;
        int i2;
        this.e = i;
        final a aVar2 = (a) this.h.get(i);
        if (i == 0) {
            aVar2.c = new BitmapDrawable(this.j.getResources(), BitmapFactory.decodeByteArray(aVar2.a, 0, aVar2.a.length, this.c));
        } else {
            aVar = (a) this.h.get(i - 1);
            if (!(aVar == null || aVar.c == null)) {
                ((BitmapDrawable) aVar.c).getBitmap().recycle();
                aVar.c = null;
            }
        }
        this.k.setImageDrawable(aVar2.c);
        if (this.f) {
            i2 = i + 1;
        } else {
            i2 = (i + 1) % this.g;
        }
        if (i2 < this.g) {
            aVar = (a) this.h.get(i2);
            aVar.c = new BitmapDrawable(this.j.getResources(), BitmapFactory.decodeByteArray(aVar.a, 0, aVar.a.length));
            this.d.postDelayed(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    if (this.c.k.getDrawable() == aVar2.c && this.c.a) {
                        this.c.b(i2);
                    }
                }
            }, (long) aVar2.b);
        } else if (this.b != null) {
            this.b.run();
        }
    }
}
