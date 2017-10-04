package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

@g(a = {@f(a = ImageView.class, b = "android:tint", c = "setImageTintList"), @f(a = ImageView.class, b = "android:tintMode", c = "setImageTintMode")})
public class p {
    @c(a = {"android:src"})
    public static void a(ImageView imageView, String str) {
        if (str == null) {
            imageView.setImageURI(null);
        } else {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    @c(a = {"android:src"})
    public static void a(ImageView imageView, Uri uri) {
        imageView.setImageURI(uri);
    }

    @c(a = {"android:src"})
    public static void a(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
}
