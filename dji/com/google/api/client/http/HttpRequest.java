package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public final class HttpRequest {
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.18.0-rc (gzip)";
    public static final String VERSION = "1.18.0-rc";
    @Beta
    @Deprecated
    private BackOffPolicy backOffPolicy;
    private int connectTimeout = 20000;
    private HttpContent content;
    private int contentLoggingLimit = 16384;
    private boolean curlLoggingEnabled = true;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private boolean followRedirects = true;
    private HttpHeaders headers = new HttpHeaders();
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private boolean loggingEnabled = true;
    private int numRetries = 10;
    private ObjectParser objectParser;
    private int readTimeout = 20000;
    private String requestMethod;
    private HttpHeaders responseHeaders = new HttpHeaders();
    private HttpResponseInterceptor responseInterceptor;
    @Beta
    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private boolean suppressUserAgentSuffix;
    private boolean throwExceptionOnExecuteError = true;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;

    HttpRequest(HttpTransport httpTransport, String str) {
        this.transport = httpTransport;
        setRequestMethod(str);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String str) {
        boolean z = str == null || HttpMediaType.matchesToken(str);
        Preconditions.checkArgument(z);
        this.requestMethod = str;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl genericUrl) {
        this.url = (GenericUrl) Preconditions.checkNotNull(genericUrl);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent httpContent) {
        this.content = httpContent;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding httpEncoding) {
        this.encoding = httpEncoding;
        return this;
    }

    @Beta
    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Beta
    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int i) {
        Preconditions.checkArgument(i >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = i;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean z) {
        this.curlLoggingEnabled = z;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.connectTimeout = i;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.readTimeout = i;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders httpHeaders) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.executeInterceptor = httpExecuteInterceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = httpUnsuccessfulResponseHandler;
        return this;
    }

    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    @Beta
    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler httpIOExceptionHandler) {
        this.ioExceptionHandler = httpIOExceptionHandler;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        this.responseInterceptor = httpResponseInterceptor;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int i) {
        Preconditions.checkArgument(i >= 0);
        this.numRetries = i;
        return this;
    }

    public HttpRequest setParser(ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean z) {
        this.throwExceptionOnExecuteError = z;
        return this;
    }

    @Beta
    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Beta
    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean z) {
        this.retryOnExecuteIOException = z;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean z) {
        this.suppressUserAgentSuffix = z;
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.api.client.http.HttpResponse execute() throws java.io.IOException {
        /*
        r22 = this;
        r0 = r22;
        r2 = r0.numRetries;
        if (r2 < 0) goto L_0x0233;
    L_0x0006:
        r2 = 1;
    L_0x0007:
        com.google.api.client.util.Preconditions.checkArgument(r2);
        r0 = r22;
        r2 = r0.numRetries;
        r0 = r22;
        r3 = r0.backOffPolicy;
        if (r3 == 0) goto L_0x001b;
    L_0x0014:
        r0 = r22;
        r3 = r0.backOffPolicy;
        r3.reset();
    L_0x001b:
        r4 = 0;
        r0 = r22;
        r3 = r0.requestMethod;
        com.google.api.client.util.Preconditions.checkNotNull(r3);
        r0 = r22;
        r3 = r0.url;
        com.google.api.client.util.Preconditions.checkNotNull(r3);
        r8 = r2;
    L_0x002b:
        if (r4 == 0) goto L_0x0030;
    L_0x002d:
        r4.ignore();
    L_0x0030:
        r7 = 0;
        r6 = 0;
        r0 = r22;
        r2 = r0.executeInterceptor;
        if (r2 == 0) goto L_0x0041;
    L_0x0038:
        r0 = r22;
        r2 = r0.executeInterceptor;
        r0 = r22;
        r2.intercept(r0);
    L_0x0041:
        r0 = r22;
        r2 = r0.url;
        r14 = r2.build();
        r0 = r22;
        r2 = r0.transport;
        r0 = r22;
        r3 = r0.requestMethod;
        r15 = r2.buildRequest(r3, r14);
        r16 = com.google.api.client.http.HttpTransport.LOGGER;
        r0 = r22;
        r2 = r0.loggingEnabled;
        if (r2 == 0) goto L_0x0236;
    L_0x005d:
        r2 = java.util.logging.Level.CONFIG;
        r0 = r16;
        r2 = r0.isLoggable(r2);
        if (r2 == 0) goto L_0x0236;
    L_0x0067:
        r2 = 1;
        r13 = r2;
    L_0x0069:
        r3 = 0;
        r2 = 0;
        if (r13 == 0) goto L_0x00ba;
    L_0x006d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "-------------- REQUEST  --------------";
        r4 = r3.append(r4);
        r5 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r4.append(r5);
        r0 = r22;
        r4 = r0.requestMethod;
        r4 = r3.append(r4);
        r5 = 32;
        r4 = r4.append(r5);
        r4 = r4.append(r14);
        r5 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r4.append(r5);
        r0 = r22;
        r4 = r0.curlLoggingEnabled;
        if (r4 == 0) goto L_0x00ba;
    L_0x009a:
        r2 = new java.lang.StringBuilder;
        r4 = "curl -v --compressed";
        r2.<init>(r4);
        r0 = r22;
        r4 = r0.requestMethod;
        r5 = "GET";
        r4 = r4.equals(r5);
        if (r4 != 0) goto L_0x00ba;
    L_0x00ad:
        r4 = " -X ";
        r4 = r2.append(r4);
        r0 = r22;
        r5 = r0.requestMethod;
        r4.append(r5);
    L_0x00ba:
        r0 = r22;
        r4 = r0.headers;
        r4 = r4.getUserAgent();
        r0 = r22;
        r5 = r0.suppressUserAgentSuffix;
        if (r5 != 0) goto L_0x00d3;
    L_0x00c8:
        if (r4 != 0) goto L_0x023a;
    L_0x00ca:
        r0 = r22;
        r5 = r0.headers;
        r9 = "Google-HTTP-Java-Client/1.18.0-rc (gzip)";
        r5.setUserAgent(r9);
    L_0x00d3:
        r0 = r22;
        r5 = r0.headers;
        r0 = r16;
        com.google.api.client.http.HttpHeaders.serializeHeaders(r5, r3, r2, r0, r15);
        r0 = r22;
        r5 = r0.suppressUserAgentSuffix;
        if (r5 != 0) goto L_0x00e9;
    L_0x00e2:
        r0 = r22;
        r5 = r0.headers;
        r5.setUserAgent(r4);
    L_0x00e9:
        r0 = r22;
        r5 = r0.content;
        if (r5 == 0) goto L_0x00f9;
    L_0x00ef:
        r0 = r22;
        r4 = r0.content;
        r4 = r4.retrySupported();
        if (r4 == 0) goto L_0x025c;
    L_0x00f9:
        r4 = 1;
        r12 = r4;
    L_0x00fb:
        if (r5 == 0) goto L_0x01a9;
    L_0x00fd:
        r0 = r22;
        r4 = r0.content;
        r17 = r4.getType();
        if (r13 == 0) goto L_0x031d;
    L_0x0107:
        r4 = new com.google.api.client.util.LoggingStreamingContent;
        r9 = com.google.api.client.http.HttpTransport.LOGGER;
        r10 = java.util.logging.Level.CONFIG;
        r0 = r22;
        r11 = r0.contentLoggingLimit;
        r4.<init>(r5, r9, r10, r11);
    L_0x0114:
        r0 = r22;
        r5 = r0.encoding;
        if (r5 != 0) goto L_0x0260;
    L_0x011a:
        r5 = 0;
        r0 = r22;
        r9 = r0.content;
        r10 = r9.getLength();
        r20 = r5;
        r5 = r4;
        r4 = r20;
    L_0x0128:
        if (r13 == 0) goto L_0x0194;
    L_0x012a:
        if (r17 == 0) goto L_0x016e;
    L_0x012c:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r18 = "Content-Type: ";
        r0 = r18;
        r9 = r9.append(r0);
        r0 = r17;
        r9 = r9.append(r0);
        r9 = r9.toString();
        r18 = r3.append(r9);
        r19 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r18.append(r19);
        if (r2 == 0) goto L_0x016e;
    L_0x014e:
        r18 = new java.lang.StringBuilder;
        r18.<init>();
        r19 = " -H '";
        r18 = r18.append(r19);
        r0 = r18;
        r9 = r0.append(r9);
        r18 = "'";
        r0 = r18;
        r9 = r9.append(r0);
        r9 = r9.toString();
        r2.append(r9);
    L_0x016e:
        r18 = 0;
        r9 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1));
        if (r9 < 0) goto L_0x0194;
    L_0x0174:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r18 = "Content-Length: ";
        r0 = r18;
        r9 = r9.append(r0);
        r9 = r9.append(r10);
        r9 = r9.toString();
        r9 = r3.append(r9);
        r18 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r0 = r18;
        r9.append(r0);
    L_0x0194:
        if (r2 == 0) goto L_0x019b;
    L_0x0196:
        r9 = " -d '@-'";
        r2.append(r9);
    L_0x019b:
        r0 = r17;
        r15.setContentType(r0);
        r15.setContentEncoding(r4);
        r15.setContentLength(r10);
        r15.setStreamingContent(r5);
    L_0x01a9:
        if (r13 == 0) goto L_0x01db;
    L_0x01ab:
        r3 = r3.toString();
        r0 = r16;
        r0.config(r3);
        if (r2 == 0) goto L_0x01db;
    L_0x01b6:
        r3 = " -- '";
        r2.append(r3);
        r3 = "'";
        r4 = "'\"'\"'";
        r3 = r14.replaceAll(r3, r4);
        r2.append(r3);
        r3 = "'";
        r2.append(r3);
        if (r5 == 0) goto L_0x01d2;
    L_0x01cd:
        r3 = " << $$$";
        r2.append(r3);
    L_0x01d2:
        r2 = r2.toString();
        r0 = r16;
        r0.config(r2);
    L_0x01db:
        if (r12 == 0) goto L_0x0282;
    L_0x01dd:
        if (r8 <= 0) goto L_0x0282;
    L_0x01df:
        r2 = 1;
    L_0x01e0:
        r0 = r22;
        r3 = r0.connectTimeout;
        r0 = r22;
        r4 = r0.readTimeout;
        r15.setTimeout(r3, r4);
        r4 = r15.execute();	 Catch:{ IOException -> 0x0290 }
        r3 = new com.google.api.client.http.HttpResponse;	 Catch:{ all -> 0x0285 }
        r0 = r22;
        r3.<init>(r0, r4);	 Catch:{ all -> 0x0285 }
        r4 = r3;
        r3 = r6;
    L_0x01f8:
        if (r4 == 0) goto L_0x02e4;
    L_0x01fa:
        r5 = r4.isSuccessStatusCode();	 Catch:{ all -> 0x02ec }
        if (r5 != 0) goto L_0x02e4;
    L_0x0200:
        r5 = 0;
        r0 = r22;
        r6 = r0.unsuccessfulResponseHandler;	 Catch:{ all -> 0x02ec }
        if (r6 == 0) goto L_0x0211;
    L_0x0207:
        r0 = r22;
        r5 = r0.unsuccessfulResponseHandler;	 Catch:{ all -> 0x02ec }
        r0 = r22;
        r5 = r5.handleResponse(r0, r4, r2);	 Catch:{ all -> 0x02ec }
    L_0x0211:
        if (r5 != 0) goto L_0x0224;
    L_0x0213:
        r6 = r4.getStatusCode();	 Catch:{ all -> 0x02ec }
        r7 = r4.getHeaders();	 Catch:{ all -> 0x02ec }
        r0 = r22;
        r6 = r0.handleRedirect(r6, r7);	 Catch:{ all -> 0x02ec }
        if (r6 == 0) goto L_0x02b6;
    L_0x0223:
        r5 = 1;
    L_0x0224:
        r2 = r2 & r5;
        if (r2 == 0) goto L_0x022a;
    L_0x0227:
        r4.ignore();	 Catch:{ all -> 0x02ec }
    L_0x022a:
        r5 = r8 + -1;
        if (r4 == 0) goto L_0x022e;
    L_0x022e:
        if (r2 != 0) goto L_0x031a;
    L_0x0230:
        if (r4 != 0) goto L_0x02f3;
    L_0x0232:
        throw r3;
    L_0x0233:
        r2 = 0;
        goto L_0x0007;
    L_0x0236:
        r2 = 0;
        r13 = r2;
        goto L_0x0069;
    L_0x023a:
        r0 = r22;
        r5 = r0.headers;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r4);
        r10 = " ";
        r9 = r9.append(r10);
        r10 = "Google-HTTP-Java-Client/1.18.0-rc (gzip)";
        r9 = r9.append(r10);
        r9 = r9.toString();
        r5.setUserAgent(r9);
        goto L_0x00d3;
    L_0x025c:
        r4 = 0;
        r12 = r4;
        goto L_0x00fb;
    L_0x0260:
        r0 = r22;
        r5 = r0.encoding;
        r9 = r5.getName();
        r10 = new com.google.api.client.http.HttpEncodingStreamingContent;
        r0 = r22;
        r5 = r0.encoding;
        r10.<init>(r4, r5);
        if (r12 == 0) goto L_0x027f;
    L_0x0273:
        r4 = com.google.api.client.util.IOUtils.computeLength(r10);
    L_0x0277:
        r20 = r4;
        r4 = r9;
        r5 = r10;
        r10 = r20;
        goto L_0x0128;
    L_0x027f:
        r4 = -1;
        goto L_0x0277;
    L_0x0282:
        r2 = 0;
        goto L_0x01e0;
    L_0x0285:
        r3 = move-exception;
        r4 = r4.getContent();	 Catch:{ IOException -> 0x0290 }
        if (r4 == 0) goto L_0x028f;
    L_0x028c:
        r4.close();	 Catch:{ IOException -> 0x0290 }
    L_0x028f:
        throw r3;	 Catch:{ IOException -> 0x0290 }
    L_0x0290:
        r3 = move-exception;
        r4 = r7;
        r0 = r22;
        r5 = r0.retryOnExecuteIOException;
        if (r5 != 0) goto L_0x02ab;
    L_0x0298:
        r0 = r22;
        r5 = r0.ioExceptionHandler;
        if (r5 == 0) goto L_0x02aa;
    L_0x029e:
        r0 = r22;
        r5 = r0.ioExceptionHandler;
        r0 = r22;
        r5 = r5.handleIOException(r0, r2);
        if (r5 != 0) goto L_0x02ab;
    L_0x02aa:
        throw r3;
    L_0x02ab:
        r5 = java.util.logging.Level.WARNING;
        r6 = "exception thrown while executing request";
        r0 = r16;
        r0.log(r5, r6, r3);
        goto L_0x01f8;
    L_0x02b6:
        if (r2 == 0) goto L_0x0224;
    L_0x02b8:
        r0 = r22;
        r6 = r0.backOffPolicy;	 Catch:{ all -> 0x02ec }
        if (r6 == 0) goto L_0x0224;
    L_0x02be:
        r0 = r22;
        r6 = r0.backOffPolicy;	 Catch:{ all -> 0x02ec }
        r7 = r4.getStatusCode();	 Catch:{ all -> 0x02ec }
        r6 = r6.isBackOffRequired(r7);	 Catch:{ all -> 0x02ec }
        if (r6 == 0) goto L_0x0224;
    L_0x02cc:
        r0 = r22;
        r6 = r0.backOffPolicy;	 Catch:{ all -> 0x02ec }
        r6 = r6.getNextBackOffMillis();	 Catch:{ all -> 0x02ec }
        r10 = -1;
        r9 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x0224;
    L_0x02da:
        r0 = r22;
        r5 = r0.sleeper;	 Catch:{ InterruptedException -> 0x0318 }
        r5.sleep(r6);	 Catch:{ InterruptedException -> 0x0318 }
    L_0x02e1:
        r5 = 1;
        goto L_0x0224;
    L_0x02e4:
        if (r4 != 0) goto L_0x02ea;
    L_0x02e6:
        r5 = 1;
    L_0x02e7:
        r2 = r2 & r5;
        goto L_0x022a;
    L_0x02ea:
        r5 = 0;
        goto L_0x02e7;
    L_0x02ec:
        r2 = move-exception;
        if (r4 == 0) goto L_0x02f2;
    L_0x02ef:
        r4.disconnect();
    L_0x02f2:
        throw r2;
    L_0x02f3:
        r0 = r22;
        r2 = r0.responseInterceptor;
        if (r2 == 0) goto L_0x0300;
    L_0x02f9:
        r0 = r22;
        r2 = r0.responseInterceptor;
        r2.interceptResponse(r4);
    L_0x0300:
        r0 = r22;
        r2 = r0.throwExceptionOnExecuteError;
        if (r2 == 0) goto L_0x0317;
    L_0x0306:
        r2 = r4.isSuccessStatusCode();
        if (r2 != 0) goto L_0x0317;
    L_0x030c:
        r2 = new com.google.api.client.http.HttpResponseException;	 Catch:{ all -> 0x0312 }
        r2.<init>(r4);	 Catch:{ all -> 0x0312 }
        throw r2;	 Catch:{ all -> 0x0312 }
    L_0x0312:
        r2 = move-exception;
        r4.disconnect();
        throw r2;
    L_0x0317:
        return r4;
    L_0x0318:
        r5 = move-exception;
        goto L_0x02e1;
    L_0x031a:
        r8 = r5;
        goto L_0x002b;
    L_0x031d:
        r4 = r5;
        goto L_0x0114;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.execute():com.google.api.client.http.HttpResponse");
    }

    @Beta
    public Future<HttpResponse> executeAsync(Executor executor) {
        Object futureTask = new FutureTask(new 1(this));
        executor.execute(futureTask);
        return futureTask;
    }

    @Beta
    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newSingleThreadExecutor());
    }

    public boolean handleRedirect(int i, HttpHeaders httpHeaders) {
        String location = httpHeaders.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(i) || location == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(location)));
        if (i == HttpStatusCodes.STATUS_CODE_SEE_OTHER) {
            setRequestMethod(HttpMethods.GET);
            setContent(null);
        }
        this.headers.setAuthorization((String) null);
        this.headers.setIfMatch((String) null);
        this.headers.setIfNoneMatch((String) null);
        this.headers.setIfModifiedSince((String) null);
        this.headers.setIfUnmodifiedSince((String) null);
        this.headers.setIfRange((String) null);
        return true;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }
}
