package com.flurry.sdk;

import android.text.TextUtils;
import com.alipay.sdk.j.i;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class is {
    private final Pattern a = Pattern.compile(".*?(%\\{\\w+\\}).*?");

    public String b(String str) {
        Matcher matcher = this.a.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    protected boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals("%{" + str + i.d);
    }
}
