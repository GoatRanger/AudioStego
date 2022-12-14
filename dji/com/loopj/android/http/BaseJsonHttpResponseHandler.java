package com.loopj.android.http;

import android.util.Log;
import com.google.api.client.http.HttpStatusCodes;
import org.apache.http.Header;

public abstract class BaseJsonHttpResponseHandler<JSON_TYPE> extends TextHttpResponseHandler {
    private static final String LOG_TAG = "BaseJsonHttpResponseHandler";

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, String str, JSON_TYPE json_type);

    public abstract void onSuccess(int i, Header[] headerArr, String str, JSON_TYPE json_type);

    protected abstract JSON_TYPE parseResponse(String str, boolean z) throws Throwable;

    public BaseJsonHttpResponseHandler() {
        this("UTF-8");
    }

    public BaseJsonHttpResponseHandler(String str) {
        super(str);
    }

    public final void onSuccess(final int i, final Header[] headerArr, final String str) {
        if (i != HttpStatusCodes.STATUS_CODE_NO_CONTENT) {
            Runnable anonymousClass1 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = BaseJsonHttpResponseHandler.this.parseResponse(str, false);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onSuccess(i, headerArr, str, parseResponse);
                            }
                        });
                    } catch (final Throwable th) {
                        Log.d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", th);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(i, headerArr, th, str, null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode()) {
                anonymousClass1.run();
                return;
            } else {
                new Thread(anonymousClass1).start();
                return;
            }
        }
        onSuccess(i, headerArr, null, null);
    }

    public final void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        if (str != null) {
            final String str2 = str;
            final int i2 = i;
            final Header[] headerArr2 = headerArr;
            final Throwable th2 = th;
            Runnable anonymousClass2 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = BaseJsonHttpResponseHandler.this.parseResponse(str2, true);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, str2, parseResponse);
                            }
                        });
                    } catch (Throwable th) {
                        Log.d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", th);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, str2, null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode()) {
                anonymousClass2.run();
                return;
            } else {
                new Thread(anonymousClass2).start();
                return;
            }
        }
        onFailure(i, headerArr, th, null, null);
    }
}
