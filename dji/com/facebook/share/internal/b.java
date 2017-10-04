package com.facebook.share.internal;

import com.facebook.internal.ai;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.GameRequestContent.a;

public class b {
    public static void a(GameRequestContent gameRequestContent) {
        int i;
        int i2 = 0;
        ai.a(gameRequestContent.a(), "message");
        if (gameRequestContent.g() != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i3 = (gameRequestContent.f() == a.ASKFOR || gameRequestContent.f() == a.SEND) ? 1 : 0;
        if ((i ^ i3) != 0) {
            throw new IllegalArgumentException("Object id should be provided if and only if action type is send or askfor");
        }
        if (gameRequestContent.c() != null) {
            i2 = 1;
        }
        if (gameRequestContent.i() != null) {
            i2++;
        }
        if (gameRequestContent.h() != null) {
            i2++;
        }
        if (i2 > 1) {
            throw new IllegalArgumentException("Parameters to, filters and suggestions are mutually exclusive");
        }
    }
}
