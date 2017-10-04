package dji.thirdparty.afinal.b.b;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import dji.thirdparty.afinal.b.a.d;

public class b implements a {
    public void a(View view, Bitmap bitmap, d dVar) {
        switch (dVar.d()) {
            case 0:
                a(view, bitmap, dVar.c());
                return;
            case 1:
                b(view, bitmap);
                return;
            default:
                return;
        }
    }

    public void a(View view, Bitmap bitmap) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bitmap);
        } else {
            view.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
    }

    private void b(View view, Bitmap bitmap) {
        Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(17170445), new BitmapDrawable(view.getResources(), bitmap)});
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(transitionDrawable);
        } else {
            view.setBackgroundDrawable(transitionDrawable);
        }
        transitionDrawable.startTransition(300);
    }

    private void a(View view, Bitmap bitmap, Animation animation) {
        animation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bitmap);
        } else {
            view.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
        view.startAnimation(animation);
    }
}
