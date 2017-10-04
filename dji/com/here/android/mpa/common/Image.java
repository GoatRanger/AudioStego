package com.here.android.mpa.common;

import android.graphics.Bitmap;
import com.nokia.maps.ImageImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.io.IOException;

@Online
public final class Image {
    private ImageImpl a;

    @Online
    public enum Type {
        UNKNOWN,
        BITMAP,
        JPEG,
        PNG,
        SVG
    }

    public Image() {
        this(new ImageImpl());
    }

    private Image(ImageImpl imageImpl) {
        this.a = imageImpl;
    }

    public boolean setBitmap(Bitmap bitmap) {
        return this.a.a(bitmap);
    }

    public void setImageData(byte[] bArr) {
        this.a.a(bArr);
    }

    public void setImageResource(int i) throws IOException {
        this.a.a(i);
    }

    public void setCategory(IconCategory iconCategory) {
        this.a.a(iconCategory);
    }

    public void setImageFile(String str) throws IOException {
        this.a.a(str);
    }

    public void setImageAsset(String str) throws IOException {
        this.a.b(str);
    }

    public void setLocalUrl(String str) {
        this.a.setLocalUrl(str);
    }

    public boolean isValid() {
        return this.a.isValid();
    }

    public long getHeight() {
        return this.a.getHeight();
    }

    public long getWidth() {
        return this.a.getWidth();
    }

    public Bitmap getBitmap(int i, int i2) {
        return this.a.getBitmap(i, i2);
    }

    public Bitmap getBitmap() {
        return this.a.getBitmap();
    }

    public Type getType() {
        return this.a.getType();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Image.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    static {
        ImageImpl.a(new k<Image, ImageImpl>() {
            public ImageImpl a(Image image) {
                return image.a;
            }
        }, new am<Image, ImageImpl>() {
            public Image a(ImageImpl imageImpl) {
                if (imageImpl != null) {
                    return new Image(imageImpl);
                }
                return null;
            }
        });
    }
}
