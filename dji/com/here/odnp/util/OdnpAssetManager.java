package com.here.odnp.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.ConditionVariable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OdnpAssetManager {
    private static final String TAG = "odnp.util.OdnpAssetManager";

    public static class Asset {
        private final String mName;
        private final boolean mOverwrite;
        private final boolean mRequired;

        public Asset(String str, boolean z, boolean z2) {
            this.mName = str;
            this.mRequired = z;
            this.mOverwrite = z2;
        }

        public String getName() {
            return this.mName;
        }

        public boolean getRequired() {
            return this.mRequired;
        }

        public boolean getOverwrite() {
            return this.mOverwrite;
        }
    }

    public interface AsyncCopyListener {
        void asyncCopyFinished(boolean z);
    }

    public static class CopyTask extends AsyncTask<Asset[], Void, Boolean> {
        private final Context mContext;
        private final AsyncCopyListener mListener;
        private final ConditionVariable mLock = new ConditionVariable();

        CopyTask(Context context, AsyncCopyListener asyncCopyListener) {
            this.mContext = context;
            this.mListener = asyncCopyListener;
        }

        protected Boolean doInBackground(Asset[]... assetArr) {
            Thread.currentThread().setName("CopyTask:AMGR");
            boolean z = true;
            try {
                Boolean valueOf;
                for (Asset copyAsset : assetArr[0]) {
                    z &= OdnpAssetManager.copyAsset(this.mContext, copyAsset);
                    if (isCancelled()) {
                        valueOf = Boolean.valueOf(false);
                        break;
                    }
                }
                valueOf = Boolean.valueOf(z);
                this.mLock.open();
                return valueOf;
            } finally {
                this.mLock.open();
            }
        }

        protected void onPostExecute(Boolean bool) {
            this.mListener.asyncCopyFinished(bool.booleanValue());
        }

        protected void onCancelled(Boolean bool) {
            this.mListener.asyncCopyFinished(false);
        }

        public void cancel() {
            super.cancel(false);
        }

        public CopyTask start(Asset[] assetArr) {
            executeOnExecutor(THREAD_POOL_EXECUTOR, new Asset[][]{assetArr});
            return this;
        }

        public void waitForCompletion() {
            this.mLock.block();
        }

        public boolean waitForCompletion(long j) {
            return this.mLock.block(j);
        }
    }

    public static CopyTask asyncCopyAssets(Context context, Asset[] assetArr, AsyncCopyListener asyncCopyListener) {
        return new CopyTask(context, asyncCopyListener).start(assetArr);
    }

    public static boolean copyAssets(Context context, Asset[] assetArr) {
        boolean z = true;
        for (Asset copyAsset : assetArr) {
            z &= copyAsset(context, copyAsset);
        }
        return z;
    }

    public static boolean copyAsset(Context context, Asset asset) {
        OutputStream outputStream;
        Throwable th;
        int close;
        File file = new File(OdnpFileManager.getPrivateDir(context), asset.getName());
        if (file.exists() && !asset.getOverwrite()) {
            return true;
        }
        try {
            InputStream bufferedInputStream = new BufferedInputStream(context.getAssets().open(asset.getName()));
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                OdnpIOUtils.close(bufferedInputStream);
                return false;
            } else if (parentFile.exists() || parentFile.mkdirs()) {
                OutputStream outputStream2 = null;
                try {
                    OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    try {
                        OdnpIOUtils.copy(bufferedInputStream, bufferedOutputStream);
                        return (1 & OdnpIOUtils.close(bufferedInputStream)) & OdnpIOUtils.close(bufferedOutputStream);
                    } catch (FileNotFoundException e) {
                        outputStream = bufferedOutputStream;
                        return OdnpIOUtils.close(outputStream) & (0 & OdnpIOUtils.close(bufferedInputStream));
                    } catch (IOException e2) {
                        outputStream2 = bufferedOutputStream;
                        return (OdnpIOUtils.close(bufferedInputStream) & 0) & OdnpIOUtils.close(outputStream2);
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream2 = bufferedOutputStream;
                        close = (1 & OdnpIOUtils.close(bufferedInputStream)) & OdnpIOUtils.close(outputStream2);
                        throw th;
                    }
                } catch (FileNotFoundException e3) {
                    outputStream = null;
                    return OdnpIOUtils.close(outputStream) & (0 & OdnpIOUtils.close(bufferedInputStream));
                } catch (IOException e4) {
                    return (OdnpIOUtils.close(bufferedInputStream) & 0) & OdnpIOUtils.close(outputStream2);
                } catch (Throwable th3) {
                    th = th3;
                    close = (1 & OdnpIOUtils.close(bufferedInputStream)) & OdnpIOUtils.close(outputStream2);
                    throw th;
                }
            } else {
                OdnpIOUtils.close(bufferedInputStream);
                return false;
            }
        } catch (IOException e5) {
            if (asset.getRequired()) {
                return false;
            }
            return true;
        }
    }
}
