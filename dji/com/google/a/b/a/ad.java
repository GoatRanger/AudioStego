package com.google.a.b.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.a.r;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ad extends u {
    private static final Pattern a = Pattern.compile("[a-zA-Z0-9]{2,}:");
    private static final Pattern b = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public ac a(r rVar) {
        String c = u.c(rVar);
        if (c.startsWith("URL:") || c.startsWith("URI:")) {
            return new ac(c.substring(4).trim(), null);
        }
        c = c.trim();
        return a(c) ? new ac(c, null) : null;
    }

    static boolean a(String str) {
        if (str.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            return false;
        }
        Matcher matcher = a.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        matcher = b.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        return false;
    }
}
