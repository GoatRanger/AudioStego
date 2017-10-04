package org.msgpack.core.buffer;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.phonecamera.h;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.msgpack.core.Preconditions;
import sun.misc.Unsafe;

public class MessageBuffer {
    static final /* synthetic */ boolean $assertionsDisabled;
    static final int ARRAY_BYTE_BASE_OFFSET;
    private static final String BIGENDIAN_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBufferBE";
    private static final String DEFAULT_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBuffer";
    private static final String UNIVERSAL_MESSAGE_BUFFER = "org.msgpack.core.buffer.MessageBufferU";
    static final boolean isUniversalBuffer;
    private static final Constructor<?> mbArrConstructor;
    static final Unsafe unsafe;
    protected final long address;
    protected final Object base;
    protected final int size;

    static {
        boolean z;
        Exception exception;
        Constructor declaredConstructor;
        Throwable th;
        int i;
        Unsafe unsafe = null;
        boolean z2 = true;
        if (MessageBuffer.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
        int i2 = 16;
        String property;
        boolean z3;
        try {
            boolean z4;
            boolean contains;
            Field declaredField;
            Unsafe unsafe2;
            Unsafe unsafe3;
            int i3;
            property = System.getProperty("java.specification.version", "");
            int indexOf = property.indexOf(46);
            if (indexOf != -1) {
                try {
                    int parseInt = Integer.parseInt(property.substring(0, indexOf));
                    z = parseInt > 1 || (parseInt == 1 && Integer.parseInt(property.substring(indexOf + 1)) >= 7);
                } catch (NumberFormatException e) {
                    e.printStackTrace(System.err);
                }
                if (Class.forName("sun.misc.Unsafe") == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                z4 = z3;
                contains = System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
                if (System.getProperty("com.google.appengine.runtime.version") == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (Boolean.parseBoolean(System.getProperty("msgpack.universal-buffer", h.e)) && !contains && !r3 && r0 && r6) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                if (z4) {
                    try {
                        declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                        declaredField.setAccessible(true);
                        unsafe2 = (Unsafe) declaredField.get(null);
                        if (unsafe2 != null) {
                            try {
                                throw new RuntimeException("Unsafe is unavailable");
                            } catch (Exception e2) {
                                unsafe = unsafe2;
                                exception = e2;
                                z3 = z4;
                                try {
                                    exception.printStackTrace(System.err);
                                    unsafe = unsafe;
                                    ARRAY_BYTE_BASE_OFFSET = i2;
                                    isUniversalBuffer = true;
                                    if (isUniversalBuffer) {
                                        property = UNIVERSAL_MESSAGE_BUFFER;
                                    } else {
                                        if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                            z2 = false;
                                        }
                                        property = z2 ? BIGENDIAN_MESSAGE_BUFFER : DEFAULT_MESSAGE_BUFFER;
                                    }
                                    try {
                                        declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                        declaredConstructor.setAccessible(true);
                                        mbArrConstructor = declaredConstructor;
                                    } catch (Throwable e3) {
                                        e3.printStackTrace(System.err);
                                        throw new RuntimeException(e3);
                                    }
                                } catch (Throwable e32) {
                                    z4 = z3;
                                    th = e32;
                                    unsafe = unsafe;
                                    ARRAY_BYTE_BASE_OFFSET = i2;
                                    isUniversalBuffer = z4;
                                    if (isUniversalBuffer) {
                                        if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                            z2 = false;
                                        }
                                        property = z2 ? DEFAULT_MESSAGE_BUFFER : BIGENDIAN_MESSAGE_BUFFER;
                                    } else {
                                        property = UNIVERSAL_MESSAGE_BUFFER;
                                    }
                                    try {
                                        declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                        declaredConstructor.setAccessible(true);
                                        mbArrConstructor = declaredConstructor;
                                        throw th;
                                    } catch (Throwable e322) {
                                        e322.printStackTrace(System.err);
                                        throw new RuntimeException(e322);
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                unsafe = unsafe2;
                                unsafe = unsafe;
                                ARRAY_BYTE_BASE_OFFSET = i2;
                                isUniversalBuffer = z4;
                                if (isUniversalBuffer) {
                                    property = UNIVERSAL_MESSAGE_BUFFER;
                                } else {
                                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                        z2 = false;
                                    }
                                    if (z2) {
                                    }
                                }
                                declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                                declaredConstructor.setAccessible(true);
                                mbArrConstructor = declaredConstructor;
                                throw th;
                            }
                        }
                        indexOf = unsafe2.arrayBaseOffset(byte[].class);
                        try {
                            i2 = unsafe2.arrayIndexScale(byte[].class);
                            if (i2 == 1) {
                                throw new IllegalStateException("Byte array index scale must be 1, but is " + i2);
                            }
                            i = indexOf;
                            unsafe3 = unsafe2;
                            i3 = i;
                        } catch (Exception e4) {
                            unsafe = unsafe2;
                            exception = e4;
                            i2 = indexOf;
                            z3 = z4;
                            exception.printStackTrace(System.err);
                            unsafe = unsafe;
                            ARRAY_BYTE_BASE_OFFSET = i2;
                            isUniversalBuffer = true;
                            if (isUniversalBuffer) {
                                property = UNIVERSAL_MESSAGE_BUFFER;
                            } else {
                                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                    z2 = false;
                                }
                                if (z2) {
                                }
                            }
                            declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                            declaredConstructor.setAccessible(true);
                            mbArrConstructor = declaredConstructor;
                        } catch (Throwable th3) {
                            unsafe = unsafe2;
                            i = indexOf;
                            th = th3;
                            i2 = i;
                            unsafe = unsafe;
                            ARRAY_BYTE_BASE_OFFSET = i2;
                            isUniversalBuffer = z4;
                            if (isUniversalBuffer) {
                                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                    z2 = false;
                                }
                                if (z2) {
                                }
                            } else {
                                property = UNIVERSAL_MESSAGE_BUFFER;
                            }
                            declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                            declaredConstructor.setAccessible(true);
                            mbArrConstructor = declaredConstructor;
                            throw th;
                        }
                    } catch (Exception e5) {
                        exception = e5;
                        z3 = z4;
                        exception.printStackTrace(System.err);
                        unsafe = unsafe;
                        ARRAY_BYTE_BASE_OFFSET = i2;
                        isUniversalBuffer = true;
                        if (isUniversalBuffer) {
                            property = UNIVERSAL_MESSAGE_BUFFER;
                        } else {
                            if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                z2 = false;
                            }
                            if (z2) {
                            }
                        }
                        declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                        declaredConstructor.setAccessible(true);
                        mbArrConstructor = declaredConstructor;
                    } catch (Throwable e3222) {
                        th = e3222;
                        unsafe = unsafe;
                        ARRAY_BYTE_BASE_OFFSET = i2;
                        isUniversalBuffer = z4;
                        if (isUniversalBuffer) {
                            if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                                z2 = false;
                            }
                            if (z2) {
                            }
                        } else {
                            property = UNIVERSAL_MESSAGE_BUFFER;
                        }
                        declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                        declaredConstructor.setAccessible(true);
                        mbArrConstructor = declaredConstructor;
                        throw th;
                    }
                }
                i3 = 16;
                unsafe3 = null;
                unsafe = unsafe3;
                ARRAY_BYTE_BASE_OFFSET = i3;
                isUniversalBuffer = z4;
                if (isUniversalBuffer) {
                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                        z2 = false;
                    }
                    property = z2 ? DEFAULT_MESSAGE_BUFFER : BIGENDIAN_MESSAGE_BUFFER;
                } else {
                    property = UNIVERSAL_MESSAGE_BUFFER;
                }
                declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
            }
            z = false;
            try {
                if (Class.forName("sun.misc.Unsafe") == null) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                z4 = z3;
            } catch (Exception e6) {
                z4 = false;
            } catch (Throwable e32222) {
                th = e32222;
                z4 = false;
                unsafe = unsafe;
                ARRAY_BYTE_BASE_OFFSET = i2;
                isUniversalBuffer = z4;
                if (isUniversalBuffer) {
                    if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                        z2 = false;
                    }
                    if (z2) {
                    }
                } else {
                    property = UNIVERSAL_MESSAGE_BUFFER;
                }
                declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
                throw th;
            }
            contains = System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
            if (System.getProperty("com.google.appengine.runtime.version") == null) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (Boolean.parseBoolean(System.getProperty("msgpack.universal-buffer", h.e))) {
            }
            z4 = true;
            if (z4) {
                i3 = 16;
                unsafe3 = null;
            } else {
                declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                unsafe2 = (Unsafe) declaredField.get(null);
                if (unsafe2 != null) {
                    indexOf = unsafe2.arrayBaseOffset(byte[].class);
                    i2 = unsafe2.arrayIndexScale(byte[].class);
                    if (i2 == 1) {
                        i = indexOf;
                        unsafe3 = unsafe2;
                        i3 = i;
                    } else {
                        throw new IllegalStateException("Byte array index scale must be 1, but is " + i2);
                    }
                }
                throw new RuntimeException("Unsafe is unavailable");
            }
            unsafe = unsafe3;
            ARRAY_BYTE_BASE_OFFSET = i3;
            isUniversalBuffer = z4;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z2 = false;
                }
                if (z2) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            try {
                declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                mbArrConstructor = declaredConstructor;
            } catch (Throwable e322222) {
                e322222.printStackTrace(System.err);
                throw new RuntimeException(e322222);
            }
        } catch (Exception e7) {
            exception = e7;
            z3 = false;
            exception.printStackTrace(System.err);
            unsafe = unsafe;
            ARRAY_BYTE_BASE_OFFSET = i2;
            isUniversalBuffer = true;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z2 = false;
                }
                if (z2) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            mbArrConstructor = declaredConstructor;
        } catch (Throwable e3222222) {
            th = e3222222;
            z4 = false;
            unsafe = unsafe;
            ARRAY_BYTE_BASE_OFFSET = i2;
            isUniversalBuffer = z4;
            if (isUniversalBuffer) {
                if (ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    z2 = false;
                }
                if (z2) {
                }
            } else {
                property = UNIVERSAL_MESSAGE_BUFFER;
            }
            declaredConstructor = Class.forName(property).getDeclaredConstructor(new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            mbArrConstructor = declaredConstructor;
            throw th;
        }
    }

    public static MessageBuffer allocate(int i) {
        return wrap(new byte[i]);
    }

    public static MessageBuffer wrap(byte[] bArr) {
        return newMessageBuffer(bArr, 0, bArr.length);
    }

    public static MessageBuffer wrap(byte[] bArr, int i, int i2) {
        return newMessageBuffer(bArr, i, i2);
    }

    private static MessageBuffer newMessageBuffer(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        try {
            return (MessageBuffer) mbArrConstructor.newInstance(new Object[]{bArr, Integer.valueOf(i), Integer.valueOf(i2)});
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException(th);
        }
    }

    public static void releaseBuffer(MessageBuffer messageBuffer) {
        if (!isUniversalBuffer && !(messageBuffer.base instanceof byte[])) {
            unsafe.freeMemory(messageBuffer.address);
        }
    }

    MessageBuffer(byte[] bArr, int i, int i2) {
        this.base = bArr;
        this.address = (long) (ARRAY_BYTE_BASE_OFFSET + i);
        this.size = i2;
    }

    protected MessageBuffer(Object obj, long j, int i) {
        this.base = obj;
        this.address = j;
        this.size = i;
    }

    public int size() {
        return this.size;
    }

    public MessageBuffer slice(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        Preconditions.checkArgument(i + i2 <= size());
        return new MessageBuffer(this.base, this.address + ((long) i), i2);
    }

    public byte getByte(int i) {
        return unsafe.getByte(this.base, this.address + ((long) i));
    }

    public boolean getBoolean(int i) {
        return unsafe.getBoolean(this.base, this.address + ((long) i));
    }

    public short getShort(int i) {
        return Short.reverseBytes(unsafe.getShort(this.base, this.address + ((long) i)));
    }

    public int getInt(int i) {
        return Integer.reverseBytes(unsafe.getInt(this.base, this.address + ((long) i)));
    }

    public float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    public long getLong(int i) {
        return Long.reverseBytes(unsafe.getLong(this.base, this.address + ((long) i)));
    }

    public double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(this.base, this.address + ((long) i), bArr, (long) (ARRAY_BYTE_BASE_OFFSET + i2), (long) i3);
    }

    public void getBytes(int i, int i2, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < i2) {
            throw new BufferOverflowException();
        }
        byteBuffer.put(sliceAsByteBuffer(i, i2));
    }

    public void putByte(int i, byte b) {
        unsafe.putByte(this.base, this.address + ((long) i), b);
    }

    public void putBoolean(int i, boolean z) {
        unsafe.putBoolean(this.base, this.address + ((long) i), z);
    }

    public void putShort(int i, short s) {
        unsafe.putShort(this.base, this.address + ((long) i), Short.reverseBytes(s));
    }

    public void putInt(int i, int i2) {
        unsafe.putInt(this.base, this.address + ((long) i), Integer.reverseBytes(i2));
    }

    public void putFloat(int i, float f) {
        putInt(i, Float.floatToRawIntBits(f));
    }

    public void putLong(int i, long j) {
        unsafe.putLong(this.base, this.address + ((long) i), Long.reverseBytes(j));
    }

    public void putDouble(int i, double d) {
        putLong(i, Double.doubleToRawLongBits(d));
    }

    public void putBytes(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(bArr, (long) (ARRAY_BYTE_BASE_OFFSET + i2), this.base, this.address + ((long) i), (long) i3);
    }

    public void putByteBuffer(int i, ByteBuffer byteBuffer, int i2) {
        if (!$assertionsDisabled && i2 > byteBuffer.remaining()) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && isUniversalBuffer) {
            throw new AssertionError();
        } else if (byteBuffer.isDirect()) {
            unsafe.copyMemory(null, DirectBufferAccess.getAddress(byteBuffer) + ((long) byteBuffer.position()), this.base, this.address + ((long) i), (long) i2);
            byteBuffer.position(byteBuffer.position() + i2);
        } else if (byteBuffer.hasArray()) {
            unsafe.copyMemory(byteBuffer.array(), (long) (ARRAY_BYTE_BASE_OFFSET + byteBuffer.position()), this.base, this.address + ((long) i), (long) i2);
            byteBuffer.position(byteBuffer.position() + i2);
        } else if (this.base != null) {
            byteBuffer.get((byte[]) this.base, i, i2);
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                unsafe.putByte(this.base, this.address + ((long) i), byteBuffer.get());
            }
        }
    }

    public ByteBuffer sliceAsByteBuffer(int i, int i2) {
        return ByteBuffer.wrap((byte[]) this.base, (int) ((this.address - ((long) ARRAY_BYTE_BASE_OFFSET)) + ((long) i)), i2);
    }

    public ByteBuffer sliceAsByteBuffer() {
        return sliceAsByteBuffer(0, size());
    }

    public byte[] toByteArray() {
        Object obj = new byte[size()];
        unsafe.copyMemory(this.base, this.address, obj, (long) ARRAY_BYTE_BASE_OFFSET, (long) size());
        return obj;
    }

    public byte[] array() {
        return (byte[]) this.base;
    }

    public int arrayOffset() {
        return ((int) this.address) - ARRAY_BYTE_BASE_OFFSET;
    }

    public void copyTo(int i, MessageBuffer messageBuffer, int i2, int i3) {
        unsafe.copyMemory(this.base, this.address + ((long) i), messageBuffer.base, messageBuffer.address + ((long) i2), (long) i3);
    }

    public String toHexString(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = i; i3 < i2; i3++) {
            if (i3 != i) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(getByte(i3))}));
        }
        return stringBuilder.toString();
    }
}
