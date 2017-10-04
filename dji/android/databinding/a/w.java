package android.databinding.a;

import android.databinding.c;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

@p(a = {@o(a = RatingBar.class, b = "android:rating")})
public class w {
    @c(a = {"android:rating"})
    public static void a(RatingBar ratingBar, float f) {
        if (ratingBar.getRating() != f) {
            ratingBar.setRating(f);
        }
    }

    @c(a = {"android:onRatingChanged", "android:ratingAttrChanged"}, b = false)
    public static void a(RatingBar ratingBar, final OnRatingBarChangeListener onRatingBarChangeListener, final n nVar) {
        if (nVar == null) {
            ratingBar.setOnRatingBarChangeListener(onRatingBarChangeListener);
        } else {
            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                    if (onRatingBarChangeListener != null) {
                        onRatingBarChangeListener.onRatingChanged(ratingBar, f, z);
                    }
                    nVar.a();
                }
            });
        }
    }
}
