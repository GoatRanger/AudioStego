package dji.playback.entryActivity;

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
import dji.log.DJILogHelper;
import dji.pilot2.videolib.VideoLibWrapper;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final int f = 6;
    private static final int g = 7;
    private static final int h = 8;
    private ExecutorService i;
    private c j;
    private b k;
    private d l;
    private ConcurrentHashMap<String, f> m;

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

        public void handleMessage(final Message message) {
            if (((b) this.b.get()) != null) {
                switch (message.what) {
                    case 1:
                        this.a.i.execute(new dji.pilot2.b.b(this) {
                            f a = ((f) message.obj);
                            final /* synthetic */ b c;

                            public void run() {
                                Bitmap bitmap;
                                f fVar = this.a;
                                String a = this.c.a.a(a.TYPE_VIDEO, fVar.a);
                                Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
                                if (a2 != null) {
                                    ImageView imageView = (ImageView) fVar.b.get();
                                    bitmap = a2;
                                } else {
                                    DJILogHelper.getInstance().LOGI("bob", "DecoderHandler handleMessage MSG_DECODER key " + a);
                                    if (new File(fVar.a).exists()) {
                                        Options options = new Options();
                                        options.inSampleSize = 1;
                                        options.inPreferredConfig = Config.RGB_565;
                                        bitmap = BitmapFactory.decodeFile(fVar.a, options);
                                        if (bitmap != null) {
                                            dji.pilot2.media.e.getInstance().a(a, bitmap);
                                        }
                                    } else {
                                        DJILogHelper.getInstance().LOGI("bob", "decoderhandler path donot exist!!");
                                        return;
                                    }
                                }
                                if (bitmap != null) {
                                    fVar.c = bitmap;
                                    this.c.a.l.obtainMessage(6, fVar).sendToTarget();
                                }
                            }
                        });
                        return;
                    case 2:
                        this.a.i.execute(new dji.pilot2.b.b(this) {
                            f a = ((f) message.obj);
                            final /* synthetic */ b c;

                            public void run() {
                                f fVar = this.a;
                                String a = b.a(fVar.a, fVar.d, fVar.e);
                                Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
                                if (a2 != null) {
                                    ImageView imageView = (ImageView) fVar.b.get();
                                    fVar.c = a2;
                                } else {
                                    Bitmap bitmap;
                                    String str = fVar.a;
                                    int i = fVar.d;
                                    DJILogHelper.getInstance().LOGI("bob", "DecoderHandler handleMessage MSG_VIDEODECODER path=" + str + " time=" + i);
                                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                    i = i * 1000 == 0 ? 2000 : i * 1000;
                                    Integer valueOf = Integer.valueOf(0);
                                    try {
                                        mediaMetadataRetriever.setDataSource(str);
                                        try {
                                            valueOf = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9));
                                        } catch (NumberFormatException e) {
                                            valueOf = Integer.valueOf(0);
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                        mediaMetadataRetriever = null;
                                    }
                                    if (i > valueOf.intValue()) {
                                        i = valueOf.intValue();
                                    }
                                    if (mediaMetadataRetriever != null) {
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
                                        dji.pilot2.media.e.getInstance().a(a, bitmap);
                                    }
                                }
                                this.c.a.l.obtainMessage(5, fVar).sendToTarget();
                            }
                        });
                        return;
                    case 3:
                        this.a.i.execute(new dji.pilot2.b.b(this) {
                            f a = ((f) message.obj);
                            final /* synthetic */ b c;

                            public void run() {
                                Bitmap bitmap;
                                f fVar = this.a;
                                String a = this.c.a.a(a.TYPE_IMAGESMALL, fVar.a);
                                Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
                                if (a2 != null) {
                                    ImageView imageView = (ImageView) fVar.b.get();
                                    bitmap = a2;
                                } else {
                                    DJILogHelper.getInstance().LOGI("bob", "DecoderHandler handleMessage MSG_DECODER key " + a);
                                    if (new File(fVar.a).exists()) {
                                        Options options = new Options();
                                        options.inSampleSize = 10;
                                        options.inPreferredConfig = Config.RGB_565;
                                        bitmap = BitmapFactory.decodeFile(fVar.a, options);
                                        if (bitmap != null) {
                                            dji.pilot2.media.e.getInstance().a(a, bitmap);
                                        }
                                    } else {
                                        DJILogHelper.getInstance().LOGI("bob", "decoderhandler path donot exist!!");
                                        return;
                                    }
                                }
                                if (bitmap != null) {
                                    fVar.c = bitmap;
                                    this.c.a.l.obtainMessage(7, fVar).sendToTarget();
                                }
                            }
                        });
                        return;
                    case 4:
                        this.a.i.execute(new dji.pilot2.b.b(this) {
                            f a = ((f) message.obj);
                            final /* synthetic */ b c;

                            public void run() {
                                Bitmap bitmap;
                                f fVar = this.a;
                                String a = this.c.a.a(a.TYPE_IMAGELARGE, fVar.a);
                                Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
                                if (a2 != null) {
                                    ImageView imageView = (ImageView) fVar.b.get();
                                    bitmap = a2;
                                } else {
                                    DJILogHelper.getInstance().LOGI("bob", "DecoderHandler handleMessage MSG_DECODER key " + a);
                                    if (new File(fVar.a).exists()) {
                                        Options options = new Options();
                                        options.inSampleSize = 2;
                                        options.inPreferredConfig = Config.RGB_565;
                                        bitmap = BitmapFactory.decodeFile(fVar.a, options);
                                        if (bitmap != null) {
                                            dji.pilot2.media.e.getInstance().a(a, bitmap);
                                        }
                                    } else {
                                        DJILogHelper.getInstance().LOGI("bob", "decoderhandler path donot exist!!");
                                        return;
                                    }
                                }
                                if (bitmap != null) {
                                    fVar.c = bitmap;
                                    this.c.a.l.obtainMessage(8, fVar).sendToTarget();
                                }
                            }
                        });
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
                        DJILogHelper.getInstance().LOGI("bob", "ShowHandler handleMessage MSG_DECODER");
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
                        f fVar2 = (f) this.a.m.get(obj);
                        if (this.a.m.containsKey(obj) && imageView.getTag().equals(obj)) {
                            DJILogHelper.getInstance().LOGI("bob", "show bitmap set " + fVar.a);
                            imageView.setImageBitmap(fVar.c);
                            return;
                        }
                        DJILogHelper.getInstance().LOGI("bob", "show bitmap err xxxa");
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
        this.j = new c(this);
        this.j.start();
        this.l = new d(this, Looper.getMainLooper(), this);
        this.k = new b(this, this.j.getLooper(), this);
        this.m = new ConcurrentHashMap();
        this.i = Executors.newFixedThreadPool(5);
    }

    public void a() {
        if (this.l != null) {
            this.l.removeCallbacksAndMessages(null);
        }
        if (this.k != null) {
            this.k.removeCallbacksAndMessages(null);
        }
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
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile path = " + str);
        if (a2 != null) {
            DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile got ");
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.k.obtainMessage(1, fVar).sendToTarget();
        this.m.put(a, fVar);
    }

    public void b(String str, ImageView imageView) {
        String a = a(a.TYPE_IMAGESMALL, str);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile path = " + str);
        if (a2 != null) {
            DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile got ");
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.k.obtainMessage(3, fVar).sendToTarget();
        this.m.put(a, fVar);
    }

    public void c(String str, ImageView imageView) {
        String a = a(a.TYPE_IMAGELARGE, str);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile path = " + str);
        if (a2 != null) {
            DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromFile got ");
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        ((ImageView) fVar.b.get()).setTag(a);
        this.k.obtainMessage(4, fVar).sendToTarget();
        this.m.put(a, fVar);
    }

    public void a(String str, ImageView imageView, int i) {
        String a = a(str, i, true);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromVideoFile path = " + str);
        if (a2 != null) {
            DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromVideoFile got ");
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        fVar.d = i;
        fVar.e = true;
        ((ImageView) fVar.b.get()).setTag(a);
        this.k.obtainMessage(2, fVar).sendToTarget();
        this.m.put(a, fVar);
        imageView.setBackgroundColor(Color.parseColor("#000000"));
    }

    public void a(String str, ImageView imageView, int i, boolean z) {
        String a = a(str, i, z);
        Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromVideoFile path = " + str);
        if (a2 != null) {
            DJILogHelper.getInstance().LOGI("bob", "decoderBitmapFromVideoFile got ");
            imageView.setImageBitmap(a2);
            return;
        }
        f fVar = new f();
        fVar.b = new WeakReference(imageView);
        fVar.a = str;
        fVar.d = i;
        fVar.e = z;
        ((ImageView) fVar.b.get()).setTag(a);
        this.k.obtainMessage(2, fVar).sendToTarget();
        this.m.put(a, fVar);
        imageView.setBackgroundColor(Color.parseColor("#000000"));
    }

    public void d(String str, ImageView imageView) {
        if (new File(str).exists()) {
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inSampleSize = 4;
            imageView.setImageBitmap(BitmapFactory.decodeFile(str, options));
            return;
        }
        DJILogHelper.getInstance().LOGI("bob", "decoderBitmapSynchronize path donot exist!!");
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
