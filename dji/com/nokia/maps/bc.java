package com.nokia.maps;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Image.Type;
import com.here.android.mpa.common.Size;
import dji.midware.util.a.b;
import dji.pilot.usercenter.protocol.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class bc {
    private static final Object a = new Object();
    private static Map<Integer, Integer> b = null;

    static Image a(Bitmap bitmap) {
        if (bitmap == null) {
            return new Image();
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] b = b(bitmap);
        Image image = new Image();
        ImageImpl.a(image).a(b, width, height);
        return image;
    }

    static Image a(View view) {
        if (view == null) {
            return new Image();
        }
        Bitmap b = b(view);
        if (b == null) {
            return new Image();
        }
        Image a = a(b);
        b.recycle();
        return a;
    }

    static int[] b(Bitmap bitmap) {
        int[] iArr = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            try {
                iArr = new int[(width * height)];
                bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            } catch (OutOfMemoryError e) {
            }
        }
        return iArr;
    }

    static Bitmap b(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Size c = c(view);
        if (c.isNull()) {
            return null;
        }
        view.buildDrawingCache(true);
        Bitmap createBitmap = Bitmap.createBitmap(c.width, c.height, Config.ARGB_8888);
        view.setDrawingCacheEnabled(false);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    static Size c(View view) {
        if (view == null) {
            return null;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        view.measure(makeMeasureSpec, makeMeasureSpec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        return new Size(view.getWidth(), view.getHeight());
    }

    static int[] a(byte[] bArr, int i, int i2) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length), i, i2, false);
        int[] b = b(createScaledBitmap);
        createScaledBitmap.recycle();
        return b;
    }

    static byte[] a(int i) {
        IOException e;
        Throwable th;
        byte[] bArr = null;
        InputStream openRawResource;
        try {
            openRawResource = MapsEngine.e().getResources().openRawResource(i);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[10000];
                for (int read = openRawResource.read(bArr2); read != -1; read = openRawResource.read(bArr2)) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (openRawResource != null) {
                        try {
                            openRawResource.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (openRawResource != null) {
                        try {
                            openRawResource.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222 = e4;
            openRawResource = null;
            e222.printStackTrace();
            if (openRawResource != null) {
                openRawResource.close();
            }
            return bArr;
        } catch (Throwable th3) {
            openRawResource = null;
            th = th3;
            if (openRawResource != null) {
                openRawResource.close();
            }
            throw th;
        }
        return bArr;
    }

    public static Image a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Image image = new Image();
        image.setImageData(bArr);
        if (!image.isValid()) {
            return null;
        }
        if (ImageImpl.a(image).getType() != Type.SVG) {
            return image;
        }
        int width = (int) (((float) image.getWidth()) * b.c);
        int height = (int) (((float) image.getHeight()) * b.c);
        if (width == Integer.MAX_VALUE || height == Integer.MAX_VALUE) {
            return null;
        }
        image.setBitmap(image.getBitmap(width, height));
        return a(ImageImpl.a(image).getImageRawData(), width, height, 5, 3, i);
    }

    static Image a(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        Image image = new Image();
        int i6 = i + (i3 * 2);
        int i7 = i2 + (i4 * 2);
        int[] iArr2 = new int[(i6 * i7)];
        int i8 = 0;
        int i9 = i + i3;
        int i10 = i2 + i4;
        int i11 = 0;
        while (i11 < i7) {
            int i12 = i11 * i6;
            Object obj = (i11 < i4 || i11 >= i10) ? null : 1;
            int i13 = i8;
            i8 = 0;
            while (i8 < i6) {
                int i14 = i12 + i8;
                iArr2[i14] = i5;
                if (obj != null && i8 >= i3 && i8 < i9) {
                    iArr2[i14] = iArr2[i14] | iArr[i13];
                    i13++;
                }
                i8++;
            }
            i11++;
            i8 = i13;
        }
        ImageImpl.a(image).a(iArr2, (i3 * 2) + i, (i4 * 2) + i2);
        return image;
    }

    static int a(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        int lastIndexOf = str.lastIndexOf(d.t);
        if (lastIndexOf != -1) {
            return ((str.charAt(lastIndexOf + 1) - 48) * 10) + (str.charAt(lastIndexOf + 2) - 48);
        }
        return -1;
    }

    public static int b(String str) {
        a();
        int a = a(str);
        if (a == -1) {
            return 0;
        }
        Integer num = (Integer) b.get(Integer.valueOf(a));
        if (num == null) {
            return Color.argb(255, 255, 0, 0);
        }
        return num.intValue();
    }

    @SuppressLint({"UseSparseArrays"})
    private static void a() {
        synchronized (a) {
            if (b != null) {
                return;
            }
            b = new HashMap();
            b.put(Integer.valueOf(1), Integer.valueOf(Color.argb(255, 255, 90, 0)));
            b.put(Integer.valueOf(2), Integer.valueOf(Color.argb(255, 255, 90, 0)));
            b.put(Integer.valueOf(3), Integer.valueOf(Color.argb(255, 42, 150, 6)));
            b.put(Integer.valueOf(6), Integer.valueOf(Color.argb(255, 255, 90, 0)));
            b.put(Integer.valueOf(7), Integer.valueOf(Color.argb(255, 0, 0, 255)));
            b.put(Integer.valueOf(9), Integer.valueOf(Color.argb(255, 255, 178, 0)));
            b.put(Integer.valueOf(34), Integer.valueOf(Color.argb(255, 255, 0, 0)));
        }
    }
}
