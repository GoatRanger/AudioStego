package dji.pilot.publics.e;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import dji.thirdparty.afinal.a.b.c;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;

public class f {
    private static final int a = 1032;

    public static void a(View view, int i) {
        if (i != view.getVisibility()) {
            view.setVisibility(i);
        }
    }

    public static void a(View view) {
        if (view != null) {
            Class cls = view.getClass();
            while (cls != null && !cls.getName().startsWith("android.")) {
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null && declaredFields.length > 0) {
                    for (Field field : declaredFields) {
                        try {
                            if ((field.getModifiers() & a) == 0 && View.class.isAssignableFrom(field.getType())) {
                                field.setAccessible(true);
                                if (field.get(view) == null) {
                                    c cVar = (c) field.getAnnotation(c.class);
                                    if (cVar != null) {
                                        int a = cVar.a();
                                        if (a != 0) {
                                            field.set(view, view.findViewById(a));
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                cls = cls.getSuperclass();
            }
        }
    }

    public static boolean a(View view, Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        boolean z;
        Throwable th;
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheBackgroundColor(1);
        view.setDrawingCacheQuality(0);
        Bitmap drawingCache = view.getDrawingCache(false);
        if (!(bitmap == null || drawingCache == null)) {
            int width = drawingCache.getWidth();
            int height = drawingCache.getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawBitmap(bitmap, (float) ((width - bitmap.getWidth()) / 2), (float) ((height - bitmap.getHeight()) / 2), paint);
            canvas.drawBitmap(drawingCache, 0.0f, 0.0f, paint);
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(new File(str));
            } catch (Exception e) {
                fileOutputStream = null;
                createBitmap.recycle();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                        z = false;
                    } catch (Exception e2) {
                        z = false;
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    view.destroyDrawingCache();
                    view.setDrawingCacheEnabled(false);
                    return z;
                }
                z = false;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                view.destroyDrawingCache();
                view.setDrawingCacheEnabled(false);
                return z;
            } catch (Throwable th2) {
                th = th2;
                createBitmap.recycle();
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
            try {
                boolean compress = createBitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                createBitmap.recycle();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                        z = compress;
                    } catch (Exception e4) {
                        z = compress;
                    }
                } else {
                    z = compress;
                }
            } catch (Exception e5) {
                createBitmap.recycle();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    z = false;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    view.destroyDrawingCache();
                    view.setDrawingCacheEnabled(false);
                    return z;
                }
                z = false;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                view.destroyDrawingCache();
                view.setDrawingCacheEnabled(false);
                return z;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream2 = fileOutputStream;
                th = th4;
                createBitmap.recycle();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            view.destroyDrawingCache();
            view.setDrawingCacheEnabled(false);
            return z;
        }
        z = false;
        if (bitmap != null) {
            bitmap.recycle();
        }
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        return z;
    }

    public static void a(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(new File(str));
            try {
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileOutputStream2 = fileOutputStream;
                th = th3;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public static boolean a(View view, String str) {
        FileOutputStream fileOutputStream;
        boolean compress;
        Throwable th;
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(1048576);
        Bitmap drawingCache = view.getDrawingCache(false);
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(new File(str));
            try {
                compress = drawingCache.compress(CompressFormat.JPEG, 100, fileOutputStream);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 == null) {
                    compress = false;
                } else {
                    try {
                        fileOutputStream2.close();
                        compress = false;
                    } catch (Exception e3) {
                        compress = false;
                    }
                }
                view.destroyDrawingCache();
                view.setDrawingCacheEnabled(false);
                return compress;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (fileOutputStream2 == null) {
                fileOutputStream2.close();
                compress = false;
            } else {
                compress = false;
            }
            view.destroyDrawingCache();
            view.setDrawingCacheEnabled(false);
            return compress;
        } catch (Throwable th3) {
            fileOutputStream = null;
            th = th3;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        return compress;
    }

    private f() {
    }
}
