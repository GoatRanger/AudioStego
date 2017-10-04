package com.google.api.client.util.store;

import com.google.api.client.util.Beta;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

@Beta
public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    @Beta
    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory fileDataStoreFactory, File file, String str) throws IOException {
            super(fileDataStoreFactory, str);
            this.dataFile = new File(file, str);
            if (IOUtils.isSymbolicLink(this.dataFile)) {
                throw new IOException("unable to use a symbolic link: " + this.dataFile);
            } else if (this.dataFile.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                save();
            } else {
                this.keyValueMap = (HashMap) IOUtils.deserialize(new FileInputStream(this.dataFile));
            }
        }

        void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }

        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory) super.getDataStoreFactory();
        }
    }

    public FileDataStoreFactory(File file) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        this.dataDirectory = canonicalFile;
        if (IOUtils.isSymbolicLink(canonicalFile)) {
            throw new IOException("unable to use a symbolic link: " + canonicalFile);
        } else if (canonicalFile.exists() || canonicalFile.mkdirs()) {
            setPermissionsToOwnerOnly(canonicalFile);
        } else {
            throw new IOException("unable to create directory: " + canonicalFile);
        }
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }

    protected <V extends Serializable> DataStore<V> createDataStore(String str) throws IOException {
        return new FileDataStore(this, this.dataDirectory, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void setPermissionsToOwnerOnly(java.io.File r7) throws java.io.IOException {
        /*
        r0 = java.io.File.class;
        r1 = "setReadable";
        r2 = 2;
        r2 = new java.lang.Class[r2];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r3 = 0;
        r4 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r2[r3] = r4;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r3 = 1;
        r4 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r2[r3] = r4;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = r0.getMethod(r1, r2);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = java.io.File.class;
        r2 = "setWritable";
        r3 = 2;
        r3 = new java.lang.Class[r3];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 0;
        r5 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r3[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 1;
        r5 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r3[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r2 = r0.getMethod(r2, r3);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = java.io.File.class;
        r3 = "setExecutable";
        r4 = 2;
        r4 = new java.lang.Class[r4];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r5 = 0;
        r6 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4[r5] = r6;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r5 = 1;
        r6 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4[r5] = r6;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r3 = r0.getMethod(r3, r4);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 0;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 1;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r1.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 == 0) goto L_0x009c;
    L_0x005e:
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 0;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 1;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r2.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 == 0) goto L_0x009c;
    L_0x007d:
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 0;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 1;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r3.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 != 0) goto L_0x00b4;
    L_0x009c:
        r0 = LOGGER;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4.<init>();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r5 = "unable to change permissions for everybody: ";
        r4 = r4.append(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = r4.append(r7);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = r4.toString();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0.warning(r4);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
    L_0x00b4:
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 0;
        r5 = 1;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r4 = 1;
        r5 = 1;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r4] = r5;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r1.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 == 0) goto L_0x0111;
    L_0x00d3:
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = 0;
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r1] = r4;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = 1;
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r1] = r4;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r2.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 == 0) goto L_0x0111;
    L_0x00f2:
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = 0;
        r2 = 1;
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r1] = r2;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = 1;
        r2 = 1;
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0[r1] = r2;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r3.invoke(r7, r0);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        if (r0 != 0) goto L_0x0129;
    L_0x0111:
        r0 = LOGGER;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1.<init>();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r2 = "unable to change permissions for owner: ";
        r1 = r1.append(r2);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = r1.append(r7);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r1 = r1.toString();	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
        r0.warning(r1);	 Catch:{ InvocationTargetException -> 0x012a, NoSuchMethodException -> 0x013a, SecurityException -> 0x015e, IllegalAccessException -> 0x015c, IllegalArgumentException -> 0x015a }
    L_0x0129:
        return;
    L_0x012a:
        r0 = move-exception;
        r0 = r0.getCause();
        r1 = java.io.IOException.class;
        com.google.api.client.util.Throwables.propagateIfPossible(r0, r1);
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
    L_0x013a:
        r0 = move-exception;
        r0 = LOGGER;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unable to set permissions for ";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r2 = ", likely because you are running a version of Java prior to 1.6";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.warning(r1);
        goto L_0x0129;
    L_0x015a:
        r0 = move-exception;
        goto L_0x0129;
    L_0x015c:
        r0 = move-exception;
        goto L_0x0129;
    L_0x015e:
        r0 = move-exception;
        goto L_0x0129;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.store.FileDataStoreFactory.setPermissionsToOwnerOnly(java.io.File):void");
    }
}
