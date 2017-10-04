package dji.pilot.gallery.entryActivity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import dji.pilot2.videolib.VideoLibWrapper;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

public class b {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final int f = 6;
    private static final int g = 7;
    private static final int h = 8;
    private c i;
    private b j;
    private d k;
    private ConcurrentHashMap<String, f> l;

    private enum a {
        TYPE_VIDEO,
        TYPE_IMAGESMALL,
        TYPE_IMAGELARGE
    }

    public class b extends Handler {
        final /* synthetic */ b a;
        private WeakReference<b> b;

        public b(b bVar, Looper looper, b bVar2) {
            this.a = bVar;
            super(looper);
            this.b = new WeakReference(bVar2);
        }

        public void handleMessage(Message message) {
            if (((b) this.b.get()) != null) {
                f fVar;
                String a;
                Bitmap a2;
                ImageView imageView;
                Bitmap bitmap;
                Options options;
                switch (message.what) {
                    case 1:
                        fVar = (f) message.obj;
                        a = this.a.a(a.TYPE_VIDEO, fVar.a);
                        a2 = dji.pilot2.media.e.getInstance().a(a);
                        if (a2 != null) {
                            imageView = (ImageView) fVar.b.get();
                            bitmap = a2;
                        } else if (new File(fVar.a).exists()) {
                            options = new Options();
                            options.inSampleSize = 1;
                            options.inPreferredConfig = Config.RGB_565;
                            bitmap = BitmapFactory.decodeFile(fVar.a, options);
                            if (bitmap != null) {
                                dji.pilot2.media.e.getInstance().a(a, bitmap);
                            }
                        } else {
                            return;
                        }
                        if (bitmap != null) {
                            fVar.c = bitmap;
                            this.a.k.obtainMessage(6, fVar).sendToTarget();
                            return;
                        }
                        return;
                    case 2:
                        fVar = (f) message.obj;
                        String a3 = b.a(fVar.a, fVar.d, fVar.e);
                        a2 = dji.pilot2.media.e.getInstance().a(a3);
                        if (a2 != null) {
                            imageView = (ImageView) fVar.b.get();
                            fVar.c = a2;
                        } else {
                            Integer valueOf;
                            MediaMetadataRetriever mediaMetadataRetriever;
                            String str = fVar.a;
                            int i = fVar.d;
                            MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                            i = i * 1000 == 0 ? 2000 : i * 1000;
                            Integer valueOf2 = Integer.valueOf(0);
                            try {
                                mediaMetadataRetriever2.setDataSource(str);
                                try {
                                    valueOf = Integer.valueOf(mediaMetadataRetriever2.extractMetadata(9));
                                } catch (NumberFormatException e) {
                                    valueOf = Integer.valueOf(0);
                                }
                                mediaMetadataRetriever = mediaMetadataRetriever2;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                valueOf = valueOf2;
                                mediaMetadataRetriever = null;
                            }
                            if (mediaMetadataRetriever != null) {
                                if (i > valueOf.intValue()) {
                                    i = valueOf.intValue();
                                }
                                a2 = mediaMetadataRetriever.getFrameAtTime((long) (i * 1000));
                            } else {
                                a2 = null;
                            }
                            if (a2 == null) {
                                a2 = VideoLibWrapper.getFrameAtTime(str, (long) i, 0);
                            }
                            if (a2 == null || !fVar.e) {
                                bitmap = a2;
                            } else {
                                bitmap = Bitmap.createScaledBitmap(a2, 320, (a2.getHeight() * 320) / a2.getWidth(), false);
                                if (!(bitmap.equals(a2) || a2 == null || a2.isRecycled())) {
                                    a2.recycle();
                                }
                            }
                            fVar.c = bitmap;
                            if (bitmap != null) {
                                dji.pilot2.media.e.getInstance().a(a3, bitmap);
                            }
                        }
                        this.a.k.obtainMessage(5, fVar).sendToTarget();
                        return;
                    case 3:
                        fVar = (f) message.obj;
                        a = this.a.a(a.TYPE_IMAGESMALL, fVar.a);
                        a2 = dji.pilot2.media.e.getInstance().a(a);
                        if (a2 != null) {
                            imageView = (ImageView) fVar.b.get();
                            bitmap = a2;
                        } else if (new File(fVar.a).exists()) {
                            options = new Options();
                            options.inSampleSize = 10;
                            options.inPreferredConfig = Config.RGB_565;
                            bitmap = BitmapFactory.decodeFile(fVar.a, options);
                            if (bitmap != null) {
                                dji.pilot2.media.e.getInstance().a(a, bitmap);
                            }
                        } else {
                            return;
                        }
                        if (bitmap != null) {
                            fVar.c = bitmap;
                            this.a.k.obtainMessage(7, fVar).sendToTarget();
                            return;
                        }
                        return;
                    case 4:
                        fVar = (f) message.obj;
                        a = this.a.a(a.TYPE_IMAGELARGE, fVar.a);
                        a2 = dji.pilot2.media.e.getInstance().a(a);
                        if (a2 != null) {
                            imageView = (ImageView) fVar.b.get();
                            bitmap = a2;
                        } else if (new File(fVar.a).exists()) {
                            options = new Options();
                            options.inSampleSize = 2;
                            options.inPreferredConfig = Config.RGB_565;
                            bitmap = BitmapFactory.decodeFile(fVar.a, options);
                            if (bitmap != null) {
                                dji.pilot2.media.e.getInstance().a(a, bitmap);
                            }
                        } else {
                            return;
                        }
                        if (bitmap != null) {
                            fVar.c = bitmap;
                            this.a.k.obtainMessage(8, fVar).sendToTarget();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class c extends HandlerThread {
        final /* synthetic */ b a;

        public c(b bVar) {
            this.a = bVar;
            super("DJIPlaybackDecoderBitmapThread");
        }
    }

    public class d extends Handler {
        final /* synthetic */ b a;
        private WeakReference<b> b;

        public d(b bVar, Looper looper, b bVar2) {
            this.a = bVar;
            super(looper);
            this.b = new WeakReference(bVar2);
        }

        public void handleMessage(Message message) {
            if (((b) this.b.get()) != null) {
                Object obj;
                f fVar = (f) message.obj;
                String str = fVar.a;
                String a;
                switch (message.what) {
                    case 5:
                        a = b.a(str, fVar.d, fVar.e);
                        break;
                    case 6:
                        a = this.a.a(a.TYPE_VIDEO, str);
                        break;
                    case 7:
                        a = this.a.a(a.TYPE_IMAGESMALL, str);
                        break;
                    case 8:
                        a = this.a.a(a.TYPE_IMAGELARGE, str);
                        break;
                    default:
                        obj = str;
                        break;
                }
                if (fVar != null && fVar.b.get() != null) {
                    ImageView imageView = (ImageView) fVar.b.get();
                    if (fVar.c != null && imageView != null) {
                        f fVar2 = (f) this.a.l.get(obj);
                        if (this.a.l.containsKey(obj) && imageView.getTag().equals(obj)) {
                            imageView.setImageBitmap(fVar.c);
                        }
                    }
                }
            }
        }
    }

    private static class e {
        public static b a = new b();

        private e() {
        }
    }

    public static class f {
        public String a;
        public WeakReference<ImageView> b;
        public Bitmap c;
        public int d;
        public boolean e;
    }

    public static b getInstance() {
        return e.a;
    }

    private b() {
        this.i = new c(this);
        this.i.start();
        this.k = new d(this, Looper.getMainLooper(), this);
        this.j = new b(this, this.i.getLooper(), this);
        this.l = new ConcurrentHashMap();
    }

    private String a(a aVar, String str) {
        if (aVar == a.TYPE_VIDEO) {
            return str + "_video";
        }
        if (aVar == a.TYPE_IMAGESMALL) {
            return str + "_small";
        }
        if (aVar == a.TYPE_IMAGELARGE) {
            return str + "_large";
        }
        return "";
    }

    public void a(String str, ImageView imageView) {
        String a = a(a.TYPE_VIDEO, str);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.j.obtainMessage(1, fVar).sendToTarget();
        this.l.put(a, fVar);
    }

    public void b(String str, ImageView imageView) {
        String a = a(a.TYPE_IMAGESMALL, str);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.j.obtainMessage(3, fVar).sendToTarget();
        this.l.put(a, fVar);
    }

    public void c(String str, ImageView imageView) {
        String a = a(a.TYPE_IMAGELARGE, str);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.j.obtainMessage(4, fVar).sendToTarget();
        this.l.put(a, fVar);
    }

    public void a(String str, ImageView imageView, int i) {
        String a = a(str, i, true);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        fVar.d = i;
        fVar.e = true;
        ((ImageView) fVar.b.get()).setTag(a);
        this.j.obtainMessage(2, fVar).sendToTarget();
        this.l.put(a, fVar);
        imageView.setBackgroundColor(Color.parseColor("#000000"));
    }

    public void a(String str, ImageView imageView, int i, boolean z) {
        String a = a(str, i, z);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        fVar.d = i;
        fVar.e = z;
        ((ImageView) fVar.b.get()).setTag(a);
        this.j.obtainMessage(2, fVar).sendToTarget();
        this.l.put(a, fVar);
        imageView.setBackgroundColor(Color.parseColor("#000000"));
    }

    public void d(String str, ImageView imageView) {
        if (new File(str).exists()) {
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inSampleSize = 4;
            imageView.setImageBitmap(BitmapFactory.decodeFile(str, options));
        }
    }

    public static String a(String str, int i, boolean z) {
        String str2 = "";
        if (z) {
            str2 = "_scale";
        }
        if (i == 0) {
            return str + str2;
        }
        return str + String.valueOf(i) + str2;
    }
}
