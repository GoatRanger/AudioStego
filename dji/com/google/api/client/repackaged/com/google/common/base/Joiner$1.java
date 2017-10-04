package com.google.api.client.repackaged.com.google.common.base;

class Joiner$1 extends Joiner {
    final /* synthetic */ Joiner this$0;
    final /* synthetic */ String val$nullText;

    Joiner$1(Joiner joiner, Joiner joiner2, String str) {
        this.this$0 = joiner;
        this.val$nullText = str;
        super(joiner2, null);
    }

    CharSequence toString(Object obj) {
        return obj == null ? this.val$nullText : this.this$0.toString(obj);
    }

    public Joiner useForNull(String str) {
        Preconditions.checkNotNull(str);
        throw new UnsupportedOperationException("already specified useForNull");
    }

    public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
    }
}
