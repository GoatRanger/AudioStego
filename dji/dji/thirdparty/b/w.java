package dji.thirdparty.b;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class w {
    private static final String a = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final String b = "\"([^\"]*)\"";
    private static final Pattern c = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private static final Pattern d = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    private final String e;
    private final String f;
    private final String g;
    private final String h;

    private w(String str, String str2, String str3, String str4) {
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = str4;
    }

    public static w a(String str) {
        Matcher matcher = c.matcher(str);
        if (!matcher.lookingAt()) {
            return null;
        }
        String toLowerCase = matcher.group(1).toLowerCase(Locale.US);
        String toLowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        Matcher matcher2 = d.matcher(str);
        String str2 = null;
        for (int end = matcher.end(); end < str.length(); end = matcher2.end()) {
            matcher2.region(end, str.length());
            if (!matcher2.lookingAt()) {
                return null;
            }
            String group = matcher2.group(1);
            if (group != null && group.equalsIgnoreCase("charset")) {
                if (matcher2.group(2) != null) {
                    group = matcher2.group(2);
                } else {
                    group = matcher2.group(3);
                }
                if (str2 == null || group.equalsIgnoreCase(str2)) {
                    str2 = group;
                } else {
                    throw new IllegalArgumentException("Multiple different charsets: " + str);
                }
            }
        }
        return new w(str, toLowerCase, toLowerCase2, str2);
    }

    public String a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public Charset c() {
        return this.h != null ? Charset.forName(this.h) : null;
    }

    public Charset a(Charset charset) {
        return this.h != null ? Charset.forName(this.h) : charset;
    }

    public String toString() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return (obj instanceof w) && ((w) obj).e.equals(this.e);
    }

    public int hashCode() {
        return this.e.hashCode();
    }
}
