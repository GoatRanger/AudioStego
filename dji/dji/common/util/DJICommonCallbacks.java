package dji.common.util;

import dji.common.error.DJIError;

public class DJICommonCallbacks {

    public interface DJICompletionCallback {
        void onResult(DJIError dJIError);
    }

    public interface DJICompletionCallbackWith<T> {
        void onFailure(DJIError dJIError);

        void onSuccess(T t);
    }

    public interface DJICompletionCallbackWithThreeParam<X, Y, Z> {
        void onFailure(DJIError dJIError);

        void onSuccess(X x, Y y, Z z);
    }

    public interface DJICompletionCallbackWithTwoParam<X, Y> {
        void onFailure(DJIError dJIError);

        void onSuccess(X x, Y y);
    }
}
