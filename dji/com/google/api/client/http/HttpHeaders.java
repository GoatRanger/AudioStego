package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Base64;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.GenericData.Flags;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import com.here.odnp.debug.DebugFile;
import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpHeaders extends GenericData {
    @Key("Accept")
    private List<String> accept;
    @Key("Accept-Encoding")
    private List<String> acceptEncoding = new ArrayList(Collections.singleton(AsyncHttpClient.ENCODING_GZIP));
    @Key("Age")
    private List<Long> age;
    @Key("WWW-Authenticate")
    private List<String> authenticate;
    @Key("Authorization")
    private List<String> authorization;
    @Key("Cache-Control")
    private List<String> cacheControl;
    @Key("Content-Encoding")
    private List<String> contentEncoding;
    @Key("Content-Length")
    private List<Long> contentLength;
    @Key("Content-MD5")
    private List<String> contentMD5;
    @Key("Content-Range")
    private List<String> contentRange;
    @Key("Content-Type")
    private List<String> contentType;
    @Key("Cookie")
    private List<String> cookie;
    @Key("Date")
    private List<String> date;
    @Key("ETag")
    private List<String> etag;
    @Key("Expires")
    private List<String> expires;
    @Key("If-Match")
    private List<String> ifMatch;
    @Key("If-Modified-Since")
    private List<String> ifModifiedSince;
    @Key("If-None-Match")
    private List<String> ifNoneMatch;
    @Key("If-Range")
    private List<String> ifRange;
    @Key("If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;
    @Key("Last-Modified")
    private List<String> lastModified;
    @Key("Location")
    private List<String> location;
    @Key("MIME-Version")
    private List<String> mimeVersion;
    @Key("Range")
    private List<String> range;
    @Key("Retry-After")
    private List<String> retryAfter;
    @Key("User-Agent")
    private List<String> userAgent;

    public HttpHeaders() {
        super(EnumSet.of(Flags.IGNORE_CASE));
    }

    public HttpHeaders clone() {
        return (HttpHeaders) super.clone();
    }

    public HttpHeaders set(String str, Object obj) {
        return (HttpHeaders) super.set(str, obj);
    }

    public final String getAccept() {
        return (String) getFirstHeaderValue(this.accept);
    }

    public HttpHeaders setAccept(String str) {
        this.accept = getAsList(str);
        return this;
    }

    public final String getAcceptEncoding() {
        return (String) getFirstHeaderValue(this.acceptEncoding);
    }

    public HttpHeaders setAcceptEncoding(String str) {
        this.acceptEncoding = getAsList(str);
        return this;
    }

    public final String getAuthorization() {
        return (String) getFirstHeaderValue(this.authorization);
    }

    public final List<String> getAuthorizationAsList() {
        return this.authorization;
    }

    public HttpHeaders setAuthorization(String str) {
        return setAuthorization(getAsList(str));
    }

    public HttpHeaders setAuthorization(List<String> list) {
        this.authorization = list;
        return this;
    }

    public final String getCacheControl() {
        return (String) getFirstHeaderValue(this.cacheControl);
    }

    public HttpHeaders setCacheControl(String str) {
        this.cacheControl = getAsList(str);
        return this;
    }

    public final String getContentEncoding() {
        return (String) getFirstHeaderValue(this.contentEncoding);
    }

    public HttpHeaders setContentEncoding(String str) {
        this.contentEncoding = getAsList(str);
        return this;
    }

    public final Long getContentLength() {
        return (Long) getFirstHeaderValue(this.contentLength);
    }

    public HttpHeaders setContentLength(Long l) {
        this.contentLength = getAsList(l);
        return this;
    }

    public final String getContentMD5() {
        return (String) getFirstHeaderValue(this.contentMD5);
    }

    public HttpHeaders setContentMD5(String str) {
        this.contentMD5 = getAsList(str);
        return this;
    }

    public final String getContentRange() {
        return (String) getFirstHeaderValue(this.contentRange);
    }

    public HttpHeaders setContentRange(String str) {
        this.contentRange = getAsList(str);
        return this;
    }

    public final String getContentType() {
        return (String) getFirstHeaderValue(this.contentType);
    }

    public HttpHeaders setContentType(String str) {
        this.contentType = getAsList(str);
        return this;
    }

    public final String getCookie() {
        return (String) getFirstHeaderValue(this.cookie);
    }

    public HttpHeaders setCookie(String str) {
        this.cookie = getAsList(str);
        return this;
    }

    public final String getDate() {
        return (String) getFirstHeaderValue(this.date);
    }

    public HttpHeaders setDate(String str) {
        this.date = getAsList(str);
        return this;
    }

    public final String getETag() {
        return (String) getFirstHeaderValue(this.etag);
    }

    public HttpHeaders setETag(String str) {
        this.etag = getAsList(str);
        return this;
    }

    public final String getExpires() {
        return (String) getFirstHeaderValue(this.expires);
    }

    public HttpHeaders setExpires(String str) {
        this.expires = getAsList(str);
        return this;
    }

    public final String getIfModifiedSince() {
        return (String) getFirstHeaderValue(this.ifModifiedSince);
    }

    public HttpHeaders setIfModifiedSince(String str) {
        this.ifModifiedSince = getAsList(str);
        return this;
    }

    public final String getIfMatch() {
        return (String) getFirstHeaderValue(this.ifMatch);
    }

    public HttpHeaders setIfMatch(String str) {
        this.ifMatch = getAsList(str);
        return this;
    }

    public final String getIfNoneMatch() {
        return (String) getFirstHeaderValue(this.ifNoneMatch);
    }

    public HttpHeaders setIfNoneMatch(String str) {
        this.ifNoneMatch = getAsList(str);
        return this;
    }

    public final String getIfUnmodifiedSince() {
        return (String) getFirstHeaderValue(this.ifUnmodifiedSince);
    }

    public HttpHeaders setIfUnmodifiedSince(String str) {
        this.ifUnmodifiedSince = getAsList(str);
        return this;
    }

    public final String getIfRange() {
        return (String) getFirstHeaderValue(this.ifRange);
    }

    public HttpHeaders setIfRange(String str) {
        this.ifRange = getAsList(str);
        return this;
    }

    public final String getLastModified() {
        return (String) getFirstHeaderValue(this.lastModified);
    }

    public HttpHeaders setLastModified(String str) {
        this.lastModified = getAsList(str);
        return this;
    }

    public final String getLocation() {
        return (String) getFirstHeaderValue(this.location);
    }

    public HttpHeaders setLocation(String str) {
        this.location = getAsList(str);
        return this;
    }

    public final String getMimeVersion() {
        return (String) getFirstHeaderValue(this.mimeVersion);
    }

    public HttpHeaders setMimeVersion(String str) {
        this.mimeVersion = getAsList(str);
        return this;
    }

    public final String getRange() {
        return (String) getFirstHeaderValue(this.range);
    }

    public HttpHeaders setRange(String str) {
        this.range = getAsList(str);
        return this;
    }

    public final String getRetryAfter() {
        return (String) getFirstHeaderValue(this.retryAfter);
    }

    public HttpHeaders setRetryAfter(String str) {
        this.retryAfter = getAsList(str);
        return this;
    }

    public final String getUserAgent() {
        return (String) getFirstHeaderValue(this.userAgent);
    }

    public HttpHeaders setUserAgent(String str) {
        this.userAgent = getAsList(str);
        return this;
    }

    public final String getAuthenticate() {
        return (String) getFirstHeaderValue(this.authenticate);
    }

    public final List<String> getAuthenticateAsList() {
        return this.authenticate;
    }

    public HttpHeaders setAuthenticate(String str) {
        this.authenticate = getAsList(str);
        return this;
    }

    public final Long getAge() {
        return (Long) getFirstHeaderValue(this.age);
    }

    public HttpHeaders setAge(Long l) {
        this.age = getAsList(l);
        return this;
    }

    public HttpHeaders setBasicAuthentication(String str, String str2) {
        return setAuthorization("Basic " + Base64.encodeBase64String(StringUtils.getBytesUtf8(((String) Preconditions.checkNotNull(str)) + ":" + ((String) Preconditions.checkNotNull(str2)))));
    }

    private static void addHeader(Logger logger, StringBuilder stringBuilder, StringBuilder stringBuilder2, LowLevelHttpRequest lowLevelHttpRequest, String str, Object obj, Writer writer) throws IOException {
        if (obj != null && !Data.isNull(obj)) {
            String str2;
            String toStringValue = toStringValue(obj);
            if (("Authorization".equalsIgnoreCase(str) || "Cookie".equalsIgnoreCase(str)) && (logger == null || !logger.isLoggable(Level.ALL))) {
                str2 = "<Not Logged>";
            } else {
                str2 = toStringValue;
            }
            if (stringBuilder != null) {
                stringBuilder.append(str).append(": ");
                stringBuilder.append(str2);
                stringBuilder.append(StringUtils.LINE_SEPARATOR);
            }
            if (stringBuilder2 != null) {
                stringBuilder2.append(" -H '").append(str).append(": ").append(str2).append("'");
            }
            if (lowLevelHttpRequest != null) {
                lowLevelHttpRequest.addHeader(str, toStringValue);
            }
            if (writer != null) {
                writer.write(str);
                writer.write(": ");
                writer.write(toStringValue);
                writer.write(DebugFile.EOL);
            }
        }
    }

    private static String toStringValue(Object obj) {
        return obj instanceof Enum ? FieldInfo.of((Enum) obj).getName() : obj.toString();
    }

    static void serializeHeaders(HttpHeaders httpHeaders, StringBuilder stringBuilder, StringBuilder stringBuilder2, Logger logger, LowLevelHttpRequest lowLevelHttpRequest) throws IOException {
        serializeHeaders(httpHeaders, stringBuilder, stringBuilder2, logger, lowLevelHttpRequest, null);
    }

    public static void serializeHeadersForMultipartRequests(HttpHeaders httpHeaders, StringBuilder stringBuilder, Logger logger, Writer writer) throws IOException {
        serializeHeaders(httpHeaders, stringBuilder, null, logger, null, writer);
    }

    static void serializeHeaders(HttpHeaders httpHeaders, StringBuilder stringBuilder, StringBuilder stringBuilder2, Logger logger, LowLevelHttpRequest lowLevelHttpRequest, Writer writer) throws IOException {
        HashSet hashSet = new HashSet();
        for (Entry entry : httpHeaders.entrySet()) {
            String str = (String) entry.getKey();
            Preconditions.checkArgument(hashSet.add(str), "multiple headers of the same name (headers are case insensitive): %s", new Object[]{str});
            Object value = entry.getValue();
            if (value != null) {
                String name;
                FieldInfo fieldInfo = httpHeaders.getClassInfo().getFieldInfo(str);
                if (fieldInfo != null) {
                    name = fieldInfo.getName();
                } else {
                    name = str;
                }
                Class cls = value.getClass();
                if ((value instanceof Iterable) || cls.isArray()) {
                    for (Object value2 : Types.iterableOf(value2)) {
                        addHeader(logger, stringBuilder, stringBuilder2, lowLevelHttpRequest, name, value2, writer);
                    }
                } else {
                    addHeader(logger, stringBuilder, stringBuilder2, lowLevelHttpRequest, name, value2, writer);
                }
            }
        }
        if (writer != null) {
            writer.flush();
        }
    }

    public final void fromHttpResponse(LowLevelHttpResponse lowLevelHttpResponse, StringBuilder stringBuilder) throws IOException {
        clear();
        ParseHeaderState parseHeaderState = new ParseHeaderState(this, stringBuilder);
        int headerCount = lowLevelHttpResponse.getHeaderCount();
        for (int i = 0; i < headerCount; i++) {
            parseHeader(lowLevelHttpResponse.getHeaderName(i), lowLevelHttpResponse.getHeaderValue(i), parseHeaderState);
        }
        parseHeaderState.finish();
    }

    private <T> T getFirstHeaderValue(List<T> list) {
        return list == null ? null : list.get(0);
    }

    private <T> List<T> getAsList(T t) {
        if (t == null) {
            return null;
        }
        List<T> arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }

    public String getFirstHeaderStringValue(String str) {
        Object obj = get(str.toLowerCase());
        if (obj == null) {
            return null;
        }
        Class cls = obj.getClass();
        if ((obj instanceof Iterable) || cls.isArray()) {
            Iterator it = Types.iterableOf(obj).iterator();
            if (it.hasNext()) {
                return toStringValue(it.next());
            }
        }
        return toStringValue(obj);
    }

    public List<String> getHeaderStringValues(String str) {
        Object obj = get(str.toLowerCase());
        if (obj == null) {
            return Collections.emptyList();
        }
        Class cls = obj.getClass();
        if (!(obj instanceof Iterable) && !cls.isArray()) {
            return Collections.singletonList(toStringValue(obj));
        }
        List arrayList = new ArrayList();
        for (Object toStringValue : Types.iterableOf(obj)) {
            arrayList.add(toStringValue(toStringValue));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final void fromHttpHeaders(HttpHeaders httpHeaders) {
        try {
            ParseHeaderState parseHeaderState = new ParseHeaderState(this, null);
            serializeHeaders(httpHeaders, null, null, null, new HeaderParsingFakeLevelHttpRequest(this, parseHeaderState));
            parseHeaderState.finish();
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    void parseHeader(String str, String str2, ParseHeaderState parseHeaderState) {
        List list = parseHeaderState.context;
        ClassInfo classInfo = parseHeaderState.classInfo;
        ArrayValueMap arrayValueMap = parseHeaderState.arrayValueMap;
        StringBuilder stringBuilder = parseHeaderState.logger;
        if (stringBuilder != null) {
            stringBuilder.append(str + ": " + str2).append(StringUtils.LINE_SEPARATOR);
        }
        FieldInfo fieldInfo = classInfo.getFieldInfo(str);
        if (fieldInfo != null) {
            Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(list, fieldInfo.getGenericType());
            if (Types.isArray(resolveWildcardTypeOrTypeVariable)) {
                Object rawArrayComponentType = Types.getRawArrayComponentType(list, Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable));
                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, list, str2));
                return;
            } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(list, resolveWildcardTypeOrTypeVariable), Iterable.class)) {
                Collection collection = (Collection) fieldInfo.getValue(this);
                if (collection == null) {
                    collection = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                    fieldInfo.setValue(this, collection);
                }
                collection.add(parseValue(resolveWildcardTypeOrTypeVariable == Object.class ? null : Types.getIterableParameter(resolveWildcardTypeOrTypeVariable), list, str2));
                return;
            } else {
                fieldInfo.setValue(this, parseValue(resolveWildcardTypeOrTypeVariable, list, str2));
                return;
            }
        }
        ArrayList arrayList = (ArrayList) get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            set(str, (Object) arrayList);
        }
        arrayList.add(str2);
    }

    private static Object parseValue(Type type, List<Type> list, String str) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, type), str);
    }
}
