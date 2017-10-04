package com.google.api.client.http;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GenericUrl extends GenericData {
    private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:", false);
    private String fragment;
    private String host;
    private List<String> pathParts;
    private int port;
    private String scheme;
    private String userInfo;

    public GenericUrl() {
        this.port = -1;
    }

    public GenericUrl(String str) {
        this(parseURL(str));
    }

    public GenericUrl(URI uri) {
        this(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getRawPath(), uri.getRawFragment(), uri.getRawQuery(), uri.getRawUserInfo());
    }

    public GenericUrl(URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    private GenericUrl(String str, String str2, int i, String str3, String str4, String str5, String str6) {
        String str7 = null;
        this.port = -1;
        this.scheme = str.toLowerCase();
        this.host = str2;
        this.port = i;
        this.pathParts = toPathParts(str3);
        this.fragment = str4 != null ? CharEscapers.decodeUri(str4) : null;
        if (str5 != null) {
            UrlEncodedParser.parse(str5, (Object) this);
        }
        if (str6 != null) {
            str7 = CharEscapers.decodeUri(str6);
        }
        this.userInfo = str7;
    }

    public int hashCode() {
        return build().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof GenericUrl)) {
            return false;
        }
        return build().equals(((GenericUrl) obj).toString());
    }

    public String toString() {
        return build();
    }

    public GenericUrl clone() {
        GenericUrl genericUrl = (GenericUrl) super.clone();
        if (this.pathParts != null) {
            genericUrl.pathParts = new ArrayList(this.pathParts);
        }
        return genericUrl;
    }

    public GenericUrl set(String str, Object obj) {
        return (GenericUrl) super.set(str, obj);
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final void setScheme(String str) {
        this.scheme = (String) Preconditions.checkNotNull(str);
    }

    public String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        this.host = (String) Preconditions.checkNotNull(str);
    }

    public final String getUserInfo() {
        return this.userInfo;
    }

    public final void setUserInfo(String str) {
        this.userInfo = str;
    }

    public int getPort() {
        return this.port;
    }

    public final void setPort(int i) {
        Preconditions.checkArgument(i >= -1, "expected port >= -1");
        this.port = i;
    }

    public List<String> getPathParts() {
        return this.pathParts;
    }

    public void setPathParts(List<String> list) {
        this.pathParts = list;
    }

    public String getFragment() {
        return this.fragment;
    }

    public final void setFragment(String str) {
        this.fragment = str;
    }

    public final String build() {
        return buildAuthority() + buildRelativeUrl();
    }

    public final String buildAuthority() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String) Preconditions.checkNotNull(this.scheme));
        stringBuilder.append("://");
        if (this.userInfo != null) {
            stringBuilder.append(CharEscapers.escapeUriUserInfo(this.userInfo)).append('@');
        }
        stringBuilder.append((String) Preconditions.checkNotNull(this.host));
        int i = this.port;
        if (i != -1) {
            stringBuilder.append(':').append(i);
        }
        return stringBuilder.toString();
    }

    public final String buildRelativeUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.pathParts != null) {
            appendRawPathFromParts(stringBuilder);
        }
        addQueryParams(entrySet(), stringBuilder);
        String str = this.fragment;
        if (str != null) {
            stringBuilder.append('#').append(URI_FRAGMENT_ESCAPER.escape(str));
        }
        return stringBuilder.toString();
    }

    public final URI toURI() {
        return toURI(build());
    }

    public final URL toURL() {
        return parseURL(build());
    }

    public final URL toURL(String str) {
        try {
            return new URL(toURL(), str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Object getFirst(String str) {
        Object obj = get(str);
        if (!(obj instanceof Collection)) {
            return obj;
        }
        Iterator it = ((Collection) obj).iterator();
        return it.hasNext() ? it.next() : null;
    }

    public Collection<Object> getAll(String str) {
        Object obj = get(str);
        if (obj == null) {
            return Collections.emptySet();
        }
        if (obj instanceof Collection) {
            return Collections.unmodifiableCollection((Collection) obj);
        }
        return Collections.singleton(obj);
    }

    public String getRawPath() {
        if (this.pathParts == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        appendRawPathFromParts(stringBuilder);
        return stringBuilder.toString();
    }

    public void setRawPath(String str) {
        this.pathParts = toPathParts(str);
    }

    public void appendRawPath(String str) {
        if (str != null && str.length() != 0) {
            List toPathParts = toPathParts(str);
            if (this.pathParts == null || this.pathParts.isEmpty()) {
                this.pathParts = toPathParts;
                return;
            }
            int size = this.pathParts.size();
            this.pathParts.set(size - 1, ((String) this.pathParts.get(size - 1)) + ((String) toPathParts.get(0)));
            this.pathParts.addAll(toPathParts.subList(1, toPathParts.size()));
        }
    }

    public static List<String> toPathParts(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            String substring;
            int indexOf = str.indexOf(47, i);
            obj = indexOf != -1 ? 1 : null;
            if (obj != null) {
                substring = str.substring(i, indexOf);
            } else {
                substring = str.substring(i);
            }
            arrayList.add(CharEscapers.decodeUri(substring));
            i = indexOf + 1;
        }
        return arrayList;
    }

    private void appendRawPathFromParts(StringBuilder stringBuilder) {
        int size = this.pathParts.size();
        for (int i = 0; i < size; i++) {
            String str = (String) this.pathParts.get(i);
            if (i != 0) {
                stringBuilder.append('/');
            }
            if (str.length() != 0) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
            }
        }
    }

    static void addQueryParams(Set<Entry<String, Object>> set, StringBuilder stringBuilder) {
        boolean z = true;
        for (Entry entry : set) {
            boolean z2;
            Object value = entry.getValue();
            if (value != null) {
                String escapeUriQuery = CharEscapers.escapeUriQuery((String) entry.getKey());
                if (value instanceof Collection) {
                    for (Object value2 : (Collection) value2) {
                        z = appendParam(z, stringBuilder, escapeUriQuery, value2);
                    }
                    z2 = z;
                } else {
                    z2 = appendParam(z, stringBuilder, escapeUriQuery, value2);
                }
            } else {
                z2 = z;
            }
            z = z2;
        }
    }

    private static boolean appendParam(boolean z, StringBuilder stringBuilder, String str, Object obj) {
        if (z) {
            z = false;
            stringBuilder.append('?');
        } else {
            stringBuilder.append('&');
        }
        stringBuilder.append(str);
        String escapeUriQuery = CharEscapers.escapeUriQuery(obj.toString());
        if (escapeUriQuery.length() != 0) {
            stringBuilder.append('=').append(escapeUriQuery);
        }
        return z;
    }

    private static URI toURI(String str) {
        try {
            return new URI(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URL parseURL(String str) {
        try {
            return new URL(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }
}
