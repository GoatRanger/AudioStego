package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        Version version = null;
        try {
            version = versionFor(getClass());
        } catch (Exception e) {
            System.err.println("ERROR: Failed to load Version information for bundle (via " + getClass().getName() + ").");
        }
        if (version == null) {
            version = Version.unknownVersion();
        }
        this._version = version;
    }

    public Version version() {
        return this._version;
    }

    public static Version versionFor(Class<?> cls) {
        Version version = null;
        InputStream resourceAsStream;
        try {
            resourceAsStream = cls.getResourceAsStream(VERSION_FILE);
            if (resourceAsStream != null) {
                String readLine;
                String trim;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                String readLine2 = bufferedReader.readLine();
                Object obj;
                if (readLine2 != null) {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            readLine = readLine.trim();
                        }
                    } else {
                        trim = readLine;
                        obj = version;
                    }
                } else {
                    obj = version;
                    Object obj2 = version;
                }
                version = parseVersion(readLine2, trim, readLine);
                resourceAsStream.close();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (IOException e3) {
        } catch (Throwable th) {
            resourceAsStream.close();
        }
        return version == null ? Version.unknownVersion() : version;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.core.Version mavenVersionFor(java.lang.ClassLoader r5, java.lang.String r6, java.lang.String r7) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "META-INF/maven/";
        r0 = r0.append(r1);
        r1 = "\\.";
        r2 = "/";
        r1 = r6.replaceAll(r1, r2);
        r0 = r0.append(r1);
        r1 = "/";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = "/pom.properties";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r5.getResourceAsStream(r0);
        if (r1 == 0) goto L_0x0057;
    L_0x0031:
        r0 = new java.util.Properties;	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r0.<init>();	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r0.load(r1);	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r2 = "version";
        r2 = r0.getProperty(r2);	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r3 = "artifactId";
        r3 = r0.getProperty(r3);	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r4 = "groupId";
        r0 = r0.getProperty(r4);	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r0 = parseVersion(r2, r0, r3);	 Catch:{ IOException -> 0x0053, all -> 0x005c }
        r1.close();	 Catch:{ IOException -> 0x0061 }
    L_0x0052:
        return r0;
    L_0x0053:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0063 }
    L_0x0057:
        r0 = com.fasterxml.jackson.core.Version.unknownVersion();
        goto L_0x0052;
    L_0x005c:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0065 }
    L_0x0060:
        throw r0;
    L_0x0061:
        r1 = move-exception;
        goto L_0x0052;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0057;
    L_0x0065:
        r1 = move-exception;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.mavenVersionFor(java.lang.ClassLoader, java.lang.String, java.lang.String):com.fasterxml.jackson.core.Version");
    }

    @Deprecated
    public static Version parseVersion(String str) {
        return parseVersion(str, null, null);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        String str4 = null;
        if (str == null) {
            return null;
        }
        CharSequence trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        int parseVersionPart;
        int parseVersionPart2;
        String[] split = VERSION_SEPARATOR.split(trim);
        int parseVersionPart3 = parseVersionPart(split[0]);
        if (split.length > 1) {
            parseVersionPart = parseVersionPart(split[1]);
        } else {
            parseVersionPart = 0;
        }
        if (split.length > 2) {
            parseVersionPart2 = parseVersionPart(split[2]);
        } else {
            parseVersionPart2 = 0;
        }
        if (split.length > 3) {
            str4 = split[3];
        }
        return new Version(parseVersionPart3, parseVersionPart, parseVersionPart2, str4, str2, str3);
    }

    protected static int parseVersionPart(String str) {
        int i = 0;
        String str2 = str.toString();
        int length = str2.length();
        int i2 = 0;
        while (i < length) {
            char charAt = str2.charAt(i);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i2 = (i2 * 10) + (charAt - 48);
            i++;
        }
        return i2;
    }
}
