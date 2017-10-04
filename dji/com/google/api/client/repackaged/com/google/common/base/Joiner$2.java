package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.base.Joiner.MapJoiner;
import java.io.IOException;
import java.util.Iterator;

class Joiner$2 extends Joiner {
    final /* synthetic */ Joiner this$0;

    Joiner$2(Joiner joiner, Joiner joiner2) {
        this.this$0 = joiner;
        super(joiner2, null);
    }

    public <A extends Appendable> A appendTo(A a, Iterator<?> it) throws IOException {
        Preconditions.checkNotNull(a, "appendable");
        Preconditions.checkNotNull(it, "parts");
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                a.append(this.this$0.toString(next));
                break;
            }
        }
        while (it.hasNext()) {
            next = it.next();
            if (next != null) {
                a.append(Joiner.access$100(this.this$0));
                a.append(this.this$0.toString(next));
            }
        }
        return a;
    }

    public Joiner useForNull(String str) {
        Preconditions.checkNotNull(str);
        throw new UnsupportedOperationException("already specified skipNulls");
    }

    public MapJoiner withKeyValueSeparator(String str) {
        Preconditions.checkNotNull(str);
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
    }
}
