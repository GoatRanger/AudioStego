package android.databinding.a;

import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.support.v7.widget.CardView;

@g(a = {@f(a = CardView.class, b = "cardCornerRadius", c = "setRadius"), @f(a = CardView.class, b = "cardMaxElevation", c = "setMaxCardElevation"), @f(a = CardView.class, b = "cardPreventCornerOverlap", c = "setPreventCornerOverlap"), @f(a = CardView.class, b = "cardUseCompatPadding", c = "setUseCompatPadding")})
public class h {
    @c(a = {"contentPadding"})
    public static void a(CardView cardView, int i) {
        cardView.setContentPadding(i, i, i, i);
    }

    @c(a = {"contentPaddingLeft"})
    public static void b(CardView cardView, int i) {
        cardView.setContentPadding(i, cardView.getContentPaddingTop(), cardView.getContentPaddingRight(), cardView.getContentPaddingBottom());
    }

    @c(a = {"contentPaddingTop"})
    public static void c(CardView cardView, int i) {
        cardView.setContentPadding(cardView.getContentPaddingLeft(), i, cardView.getContentPaddingRight(), cardView.getContentPaddingBottom());
    }

    @c(a = {"contentPaddingRight"})
    public static void d(CardView cardView, int i) {
        cardView.setContentPadding(cardView.getContentPaddingLeft(), cardView.getContentPaddingTop(), i, cardView.getContentPaddingBottom());
    }

    @c(a = {"contentPaddingBottom"})
    public static void e(CardView cardView, int i) {
        cardView.setContentPadding(cardView.getContentPaddingLeft(), cardView.getContentPaddingTop(), cardView.getContentPaddingRight(), i);
    }
}
