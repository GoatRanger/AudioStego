package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible
public class Joiner {
    private final String separator;

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner, String str) {
            this.joiner = joiner;
            this.keyValueSeparator = (String) Preconditions.checkNotNull(str);
        }

        public <A extends Appendable> A appendTo(A a, Map<?, ?> map) throws IOException {
            return appendTo((Appendable) a, map.entrySet());
        }

        public StringBuilder appendTo(StringBuilder stringBuilder, Map<?, ?> map) {
            return appendTo(stringBuilder, map.entrySet());
        }

        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        @Beta
        @Deprecated
        public <A extends Appendable, I extends Iterable<? extends Entry<?, ?>> & Iterator<? extends Entry<?, ?>>> A appendTo(A a, I i) throws IOException {
            return appendTo((Appendable) a, (Iterator) i);
        }

        @Beta
        public <A extends Appendable> A appendTo(A a, Iterable<? extends Entry<?, ?>> iterable) throws IOException {
            return appendTo((Appendable) a, iterable.iterator());
        }

        @Beta
        public <A extends Appendable> A appendTo(A a, Iterator<? extends Entry<?, ?>> it) throws IOException {
            Preconditions.checkNotNull(a);
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                a.append(this.joiner.toString(entry.getKey()));
                a.append(this.keyValueSeparator);
                a.append(this.joiner.toString(entry.getValue()));
                while (it.hasNext()) {
                    a.append(this.joiner.separator);
                    entry = (Entry) it.next();
                    a.append(this.joiner.toString(entry.getKey()));
                    a.append(this.keyValueSeparator);
                    a.append(this.joiner.toString(entry.getValue()));
                }
            }
            return a;
        }

        @Beta
        @Deprecated
        public <I extends Iterable<? extends Entry<?, ?>> & Iterator<? extends Entry<?, ?>>> StringBuilder appendTo(StringBuilder stringBuilder, I i) throws IOException {
            return appendTo(stringBuilder, (Iterator) i);
        }

        @Beta
        public StringBuilder appendTo(StringBuilder stringBuilder, Iterable<? extends Entry<?, ?>> iterable) {
            return appendTo(stringBuilder, iterable.iterator());
        }

        @Beta
        public StringBuilder appendTo(StringBuilder stringBuilder, Iterator<? extends Entry<?, ?>> it) {
            try {
                appendTo((Appendable) stringBuilder, (Iterator) it);
                return stringBuilder;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Beta
        @Deprecated
        public <I extends Iterable<? extends Entry<?, ?>> & Iterator<? extends Entry<?, ?>>> String join(I i) throws IOException {
            return join((Iterator) i);
        }

        @Beta
        public String join(Iterable<? extends Entry<?, ?>> iterable) {
            return join(iterable.iterator());
        }

        @Beta
        public String join(Iterator<? extends Entry<?, ?>> it) {
            return appendTo(new StringBuilder(), (Iterator) it).toString();
        }

        @CheckReturnValue
        public MapJoiner useForNull(String str) {
            return new MapJoiner(this.joiner.useForNull(str), this.keyValueSeparator);
        }
    }

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    public static Joiner on(char c) {
        return new Joiner(String.valueOf(c));
    }

    private Joiner(String str) {
        this.separator = (String) Preconditions.checkNotNull(str);
    }

    private Joiner(Joiner joiner) {
        this.separator = joiner.separator;
    }

    @Beta
    @Deprecated
    public final <A extends Appendable, I extends Iterable<?> & Iterator<?>> A appendTo(A a, I i) throws IOException {
        return appendTo((Appendable) a, (Iterator) i);
    }

    public <A extends Appendable> A appendTo(A a, Iterable<?> iterable) throws IOException {
        return appendTo((Appendable) a, iterable.iterator());
    }

    public <A extends Appendable> A appendTo(A a, Iterator<?> it) throws IOException {
        Preconditions.checkNotNull(a);
        if (it.hasNext()) {
            a.append(toString(it.next()));
            while (it.hasNext()) {
                a.append(this.separator);
                a.append(toString(it.next()));
            }
        }
        return a;
    }

    public final <A extends Appendable> A appendTo(A a, Object[] objArr) throws IOException {
        return appendTo((Appendable) a, Arrays.asList(objArr));
    }

    public final <A extends Appendable> A appendTo(A a, @Nullable Object obj, @Nullable Object obj2, Object... objArr) throws IOException {
        return appendTo((Appendable) a, iterable(obj, obj2, objArr));
    }

    @Beta
    @Deprecated
    public final <I extends Iterable<?> & Iterator<?>> StringBuilder appendTo(StringBuilder stringBuilder, I i) {
        return appendTo(stringBuilder, (Iterator) i);
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Iterable<?> iterable) {
        return appendTo(stringBuilder, iterable.iterator());
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Iterator<?> it) {
        try {
            appendTo((Appendable) stringBuilder, (Iterator) it);
            return stringBuilder;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, Object[] objArr) {
        return appendTo(stringBuilder, Arrays.asList(objArr));
    }

    public final StringBuilder appendTo(StringBuilder stringBuilder, @Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return appendTo(stringBuilder, iterable(obj, obj2, objArr));
    }

    @Beta
    @Deprecated
    public final <I extends Iterable<?> & Iterator<?>> String join(I i) {
        return join((Iterator) i);
    }

    public final String join(Iterable<?> iterable) {
        return join(iterable.iterator());
    }

    public final String join(Iterator<?> it) {
        return appendTo(new StringBuilder(), (Iterator) it).toString();
    }

    public final String join(Object[] objArr) {
        return join(Arrays.asList(objArr));
    }

    public final String join(@Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return join(iterable(obj, obj2, objArr));
    }

    @CheckReturnValue
    public Joiner useForNull(String str) {
        Preconditions.checkNotNull(str);
        return new 1(this, this, str);
    }

    @CheckReturnValue
    public Joiner skipNulls() {
        return new 2(this, this);
    }

    @CheckReturnValue
    public MapJoiner withKeyValueSeparator(String str) {
        return new MapJoiner(str);
    }

    CharSequence toString(Object obj) {
        Preconditions.checkNotNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    private static Iterable<Object> iterable(Object obj, Object obj2, Object[] objArr) {
        Preconditions.checkNotNull(objArr);
        return new 3(objArr, obj, obj2);
    }
}
