package com.alibaba.sdk.android;

import com.alibaba.sdk.android.kernel.message.KernelMessageConstants;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.message.MessageUtils;
import com.tencent.android.tpush.common.Constants;

public class ResultCode {
    @Deprecated
    public static final ResultCode ALIPAY_AUTH_FAIL_CODE = new ResultCode(10020, "ALIPAY_AUTH_CODE");
    @Deprecated
    public static final ResultCode ALREADY_LOGOUT = new ResultCode(10011, "AREADY_LOGOUT");
    @Deprecated
    public static final ResultCode BRIDGE_EXCEPTION = new ResultCode(Constants.CODE_LOGIC_ILLEGAL_ARGUMENT, "BRIDGE_EXCEPTION");
    @Deprecated
    public static final ResultCode GET_ORDER_URL_EXCEPTION = new ResultCode(10008, "GET_ORDER_URL_EXCEPTION");
    @Deprecated
    public static final ResultCode HTTP_REQUEST_EXCEPTION = new ResultCode(Constants.CODE_ACCESSKET_OR_ACCESSID_ERROR);
    @Deprecated
    public static final ResultCode ILLEGAL_PARAM = new ResultCode(10013, "ILLEGAL_PARAM");
    @Deprecated
    public static final ResultCode INIT_EXCEPTION = new ResultCode(10002, "INIT_EXCEPTION");
    @Deprecated
    public static final ResultCode NEED_AUTHORIZE = new ResultCode(10017, "NEED_AUTHORIZE");
    @Deprecated
    public static final ResultCode NETWORK_NOT_AVAILABLE = new ResultCode(10014, "NETWORK_NOT_AVAILABLE");
    @Deprecated
    public static final ResultCode NO_SUCH_METHOD = new ResultCode(10000, "NO_SUCH_METHOD");
    @Deprecated
    public static final ResultCode PLUGIN_START_FAIL = new ResultCode(KernelMessageConstants.PLUGIN_INIT_FAILED, "PLUGIN_START_FAIL");
    @Deprecated
    public static final ResultCode QUERY_ORDER_RESULT_EXCEPTION = new ResultCode(10009, "QUERY_ORDER_RESULT_EXCEPTION");
    @Deprecated
    public static final ResultCode REFRESH_SID_EXCEPTION = new ResultCode(101);
    @Deprecated
    public static final ResultCode REQUEST_INIT_SERVER_EXCEPTION = new ResultCode(Constants.CODE_SO_ERROR, "INIT_SID_EXCEPTION");
    @Deprecated
    public static final ResultCode RSA_DECRYPT_EXCEPTION = new ResultCode(10005);
    @Deprecated
    public static final ResultCode SDK_NOT_INITED_EXCEPTION = new ResultCode(KernelMessageConstants.SDK_NOT_INIT, "SDK_NOT_INITED_EXCEPTION");
    @Deprecated
    public static final ResultCode SECURITY_GUARD_INIT_EXCEPTION = new ResultCode(10016, "SECURITY_GUARD_INIT_EXCEPTION");
    public static final ResultCode SUCCESS = new ResultCode(100, "SUCCESS");
    @Deprecated
    public static final ResultCode SYSTEM_EXCEPTION = new ResultCode(KernelMessageConstants.GENERIC_SYSTEM_ERROR, "SYSTEM_EXCEPTION");
    @Deprecated
    public static final ResultCode TAOKE_TRACE_FAIL = new ResultCode(10018, "TAOKE_TRACE_FAIL");
    @Deprecated
    public static final ResultCode TB_BIND_FAIL = new ResultCode(10021, "TB_BIND_FAIL");
    @Deprecated
    public static final ResultCode UMID_INIT_FAIL = new ResultCode(10019, "UMID_INIT_FAIL");
    @Deprecated
    public static final ResultCode UNSUPPORTED_ITEM_TYPE = new ResultCode(Constants.CODE_SERVICE_DISABLED, "UNSUPPORTED_ITEM_TYPE");
    @Deprecated
    public static final ResultCode USER_CANCEL = new ResultCode(Constants.CODE_PERMISSIONS_ERROR, "USER_CANCEL");
    @Deprecated
    public static final ResultCode USER_LOGOUT = new ResultCode(10015, "USER_LOGOUT");
    public int code;
    public String message;

    public ResultCode(int i) {
        this(i, null);
    }

    public ResultCode(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public boolean isSuccess() {
        return this.code == 100;
    }

    public static ResultCode create(Message message) {
        return new ResultCode(message.code, message.message);
    }

    public static ResultCode create(int i, Object... objArr) {
        return new ResultCode(i, MessageUtils.getMessageContent(i, objArr));
    }

    public int hashCode() {
        return this.code + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.code != ((ResultCode) obj).code) {
            return false;
        }
        return true;
    }
}
