package com.alibaba.sdk.android.message;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.util.ResourceUtils;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageUtils {
    private static Map<Integer, Message> a = new HashMap();
    private static ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private static final Message c;
    private static final Message d;
    private static Message e;
    private static Message f;
    private static final Object g = new Object();

    static {
        Message message = new Message();
        c = message;
        message.code = 1;
        c.message = "未在消息文件中找到 id 为 {0} 的消息";
        c.action = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        c.type = "E";
        message = new Message();
        d = message;
        message.code = 2;
        d.message = "检索消息时发生如下错误 {0}";
        d.action = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        d.type = "E";
    }

    public static Message createMessage(int i, Object... objArr) {
        try {
            Message b;
            b.readLock().lock();
            Message message = (Message) a.get(Integer.valueOf(i));
            if (message == null) {
                b.readLock().unlock();
                b.writeLock().lock();
                b = b(i);
                if (b != null) {
                    a.put(Integer.valueOf(i), b);
                }
                b.readLock().lock();
                b.writeLock().unlock();
            } else {
                b = message;
            }
            if (b == null) {
                message = a(i);
                b.readLock().unlock();
                return message;
            } else if (objArr.length == 0) {
                b.readLock().unlock();
                return b;
            } else {
                message = (Message) b.clone();
                message.message = MessageFormat.format(b.message, objArr);
                b.readLock().unlock();
                return message;
            }
        } catch (Throwable e) {
            AliSDKLogger.printStackTraceAndMore(e);
            return a(e.getMessage());
        } catch (Throwable th) {
            b.writeLock().unlock();
        }
    }

    public static String getMessageContent(int i, Object... objArr) {
        try {
            b.readLock().lock();
            Message message = (Message) a.get(Integer.valueOf(i));
            if (message == null) {
                b.readLock().unlock();
                b.writeLock().lock();
                message = b(i);
                if (message != null) {
                    a.put(Integer.valueOf(i), message);
                }
                b.readLock().lock();
                b.writeLock().unlock();
            }
            String str;
            if (message == null) {
                str = a(i).message;
                b.readLock().unlock();
                return str;
            }
            str = MessageFormat.format(message.message, objArr);
            b.readLock().unlock();
            return str;
        } catch (Throwable e) {
            AliSDKLogger.printStackTraceAndMore(e);
            return a(e.getMessage()).message;
        } catch (Throwable th) {
            b.writeLock().unlock();
        }
    }

    private static Message a(String str) {
        if (f == null) {
            synchronized (g) {
                if (f == null) {
                    Message b;
                    b = b(2);
                    f = b;
                    if (b == null) {
                        f = d;
                    }
                }
            }
        }
        try {
            b = (Message) f.clone();
            b.message = MessageFormat.format(b.message, new Object[]{str});
            return b;
        } catch (CloneNotSupportedException e) {
            return f;
        }
    }

    private static Message a(int i) {
        if (e == null) {
            synchronized (g) {
                if (e == null) {
                    Message b;
                    b = b(1);
                    e = b;
                    if (b == null) {
                        e = c;
                    }
                }
            }
        }
        try {
            b = (Message) e.clone();
            b.message = MessageFormat.format(b.message, new Object[]{String.valueOf(i)});
            return b;
        } catch (CloneNotSupportedException e) {
            return e;
        }
    }

    private static Message b(int i) {
        try {
            int identifier = ResourceUtils.getIdentifier(a.a, "string", "alisdk_message_" + i + "_message");
            if (identifier == 0) {
                return null;
            }
            Message message = new Message();
            message.code = i;
            message.message = a.a.getResources().getString(identifier);
            identifier = ResourceUtils.getIdentifier(a.a, "string", "alisdk_message_" + i + "_action");
            if (identifier != 0) {
                message.action = a.a.getResources().getString(identifier);
            } else {
                message.action = "";
            }
            identifier = ResourceUtils.getIdentifier(a.a, "string", "alisdk_message_" + i + "_type");
            if (identifier != 0) {
                message.type = a.a.getResources().getString(identifier);
            } else {
                message.type = "I";
            }
            return message;
        } catch (Exception e) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Fail to get message of the code " + i + ", the error message is " + e.getMessage());
            return null;
        }
    }
}
