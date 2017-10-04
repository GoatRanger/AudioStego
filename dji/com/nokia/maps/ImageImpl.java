package com.nokia.maps;

import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import com.here.android.mpa.common.IconCategory;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Image.Type;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Online
public class ImageImpl extends BaseNativeObject {
    private static k<Image, ImageImpl> c = null;
    private static am<Image, ImageImpl> d = null;
    public final ar a = new ar();
    private cq b = new cq(ImageImpl.class.getName());

    private native void createImageNative();

    private native void destroyImageNative();

    private native void reset();

    private native void setCategoryNative(int i);

    private native void setImageDataNative(byte[] bArr);

    private native boolean setImageDataRawNative(int[] iArr, int i, int i2);

    public native Bitmap getBitmap();

    public native Bitmap getBitmap(int i, int i2);

    public native long getHeight();

    public native int[] getImageRawData();

    public native int[] getImageTextureData();

    public native Type getType();

    public native long getWidth();

    public native boolean isValid();

    public native void setLocalUrl(String str);

    static {
        ce.a(Image.class);
    }

    public static ImageImpl a(Image image) {
        if (c != null) {
            return (ImageImpl) c.a(image);
        }
        return null;
    }

    static Image a(ImageImpl imageImpl) {
        if (imageImpl != null) {
            return (Image) d.a(imageImpl);
        }
        return null;
    }

    static List<Image> a(ImageImpl[] imageImplArr) {
        if (d == null || imageImplArr == null) {
            return null;
        }
        List<Image> arrayList = new ArrayList();
        for (Object a : imageImplArr) {
            Image image = (Image) d.a(a);
            if (image != null) {
                arrayList.add(image);
            }
        }
        return arrayList;
    }

    public static void a(k<Image, ImageImpl> kVar, am<Image, ImageImpl> amVar) {
        c = kVar;
        d = amVar;
    }

    public ImageImpl() {
        createImageNative();
    }

    @OnlineNative
    private ImageImpl(int i) {
        this.nativeptr = i;
    }

    public boolean a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Object obj = new int[(width * height)];
        bitmap.getPixels(obj, 0, width, 0, 0, width, height);
        boolean imageDataRawNative = setImageDataRawNative(obj, width, height);
        this.a.a(this, obj);
        return imageDataRawNative;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            setImageDataNative(bArr);
            this.a.a(this, bArr);
        }
    }

    public void a(int[] iArr, int i, int i2) {
        if (iArr != null && i > 0 && i2 > 0) {
            setImageDataRawNative(iArr, i, i2);
            this.a.a(this, iArr);
        }
    }

    public void a(IconCategory iconCategory) {
        setCategoryNative(bb.a(iconCategory));
    }

    public void a(int i) throws IOException {
        InputStream openRawResource;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            Object obj;
            openRawResource = MapsEngine.e().getResources().openRawResource(i);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (NotFoundException e) {
                byteArrayOutputStream = null;
                obj = openRawResource;
                try {
                    throw new IOException("Could not find resource with the given ID");
                } catch (Throwable th3) {
                    th = th3;
                    openRawResource = byteArrayOutputStream2;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    th2 = th;
                    if (openRawResource != null) {
                        openRawResource.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw th2;
                }
            } catch (IOException e2) {
                byteArrayOutputStream = null;
                try {
                    throw new IOException("Could not read resource");
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    th2 = th;
                    if (openRawResource != null) {
                        openRawResource.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                if (openRawResource != null) {
                    openRawResource.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th2;
            }
            try {
                byte[] bArr = new byte[10000];
                for (int read = openRawResource.read(bArr); read != -1; read = openRawResource.read(bArr)) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                obj = byteArrayOutputStream.toByteArray();
                setImageDataNative(obj);
                this.a.a(this, obj);
                if (openRawResource != null) {
                    openRawResource.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (NotFoundException e3) {
                obj = openRawResource;
                throw new IOException("Could not find resource with the given ID");
            } catch (IOException e4) {
                throw new IOException("Could not read resource");
            }
        } catch (NotFoundException e5) {
            byteArrayOutputStream = null;
            throw new IOException("Could not find resource with the given ID");
        } catch (IOException e6) {
            byteArrayOutputStream = null;
            openRawResource = null;
            throw new IOException("Could not read resource");
        } catch (Throwable th6) {
            th2 = th6;
            openRawResource = null;
            if (openRawResource != null) {
                openRawResource.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th2;
        }
    }

    public void a(String str) throws IOException {
        FileInputStream openFileInput;
        Throwable th;
        Throwable th2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            Object obj;
            openFileInput = MapsEngine.e().openFileInput(str);
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (FileNotFoundException e) {
                byteArrayOutputStream2 = null;
                obj = openFileInput;
                try {
                    throw new IOException("Could not find file");
                } catch (Throwable th3) {
                    th = th3;
                    openFileInput = byteArrayOutputStream;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th2 = th;
                    if (openFileInput != null) {
                        openFileInput.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
            } catch (IOException e2) {
                byteArrayOutputStream2 = null;
                try {
                    throw new IOException("Could not open/read file");
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th2 = th;
                    if (openFileInput != null) {
                        openFileInput.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
            try {
                byte[] bArr = new byte[10000];
                for (int read = openFileInput.read(bArr); read != -1; read = openFileInput.read(bArr)) {
                    byteArrayOutputStream2.write(bArr, 0, read);
                }
                obj = byteArrayOutputStream2.toByteArray();
                setImageDataNative(obj);
                this.a.a(this, obj);
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
            } catch (FileNotFoundException e3) {
                obj = openFileInput;
                throw new IOException("Could not find file");
            } catch (IOException e4) {
                throw new IOException("Could not open/read file");
            }
        } catch (FileNotFoundException e5) {
            byteArrayOutputStream2 = null;
            throw new IOException("Could not find file");
        } catch (IOException e6) {
            byteArrayOutputStream2 = null;
            openFileInput = null;
            throw new IOException("Could not open/read file");
        } catch (Throwable th6) {
            th2 = th6;
            openFileInput = null;
            if (openFileInput != null) {
                openFileInput.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
    }

    public void b(String str) throws IOException {
        InputStream open;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            Object obj;
            open = MapsEngine.e().getAssets().open(str);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException e) {
                byteArrayOutputStream = null;
                obj = open;
                try {
                    throw new IOException("Could not open/read asset");
                } catch (Throwable th3) {
                    th = th3;
                    open = byteArrayOutputStream2;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    th2 = th;
                    if (open != null) {
                        open.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                if (open != null) {
                    open.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th2;
            }
            try {
                byte[] bArr = new byte[10000];
                for (int read = open.read(bArr); read != -1; read = open.read(bArr)) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                obj = byteArrayOutputStream.toByteArray();
                setImageDataNative(obj);
                this.a.a(this, obj);
                if (open != null) {
                    open.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e2) {
                obj = open;
                throw new IOException("Could not open/read asset");
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream2 = byteArrayOutputStream;
                th2 = th;
                if (open != null) {
                    open.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th2;
            }
        } catch (IOException e3) {
            byteArrayOutputStream = null;
            throw new IOException("Could not open/read asset");
        } catch (Throwable th6) {
            th2 = th6;
            open = null;
            if (open != null) {
                open.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th2;
        }
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyImageNative();
        }
    }
}
