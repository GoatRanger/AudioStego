package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

class o extends View {
    private Paint a = new Paint();

    public o(Context context) {
        super(context);
        this.a.setAntiAlias(true);
        this.a.setColor(Color.argb(255, 235, 235, 235));
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.a);
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            return;
        }
        setVisibility(8);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(200);
        clearAnimation();
        startAnimation(alphaAnimation);
    }
}
