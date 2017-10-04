package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.Collections;

@Beta
public class IdTokenVerifier {
    public static final long DEFAULT_TIME_SKEW_SECONDS = 300;
    private final long acceptableTimeSkewSeconds;
    private final Collection<String> audience;
    private final Clock clock;
    private final String issuer;

    @Beta
    public static class Builder {
        long acceptableTimeSkewSeconds = 300;
        Collection<String> audience;
        Clock clock = Clock.SYSTEM;
        String issuer;

        public IdTokenVerifier build() {
            return new IdTokenVerifier(this);
        }

        public final Clock getClock() {
            return this.clock;
        }

        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public final String getIssuer() {
            return this.issuer;
        }

        public Builder setIssuer(String str) {
            this.issuer = str;
            return this;
        }

        public final Collection<String> getAudience() {
            return this.audience;
        }

        public Builder setAudience(Collection<String> collection) {
            this.audience = collection;
            return this;
        }

        public final long getAcceptableTimeSkewSeconds() {
            return this.acceptableTimeSkewSeconds;
        }

        public Builder setAcceptableTimeSkewSeconds(long j) {
            Preconditions.checkArgument(j >= 0);
            this.acceptableTimeSkewSeconds = j;
            return this;
        }
    }

    public IdTokenVerifier() {
        this(new Builder());
    }

    protected IdTokenVerifier(Builder builder) {
        this.clock = builder.clock;
        this.acceptableTimeSkewSeconds = builder.acceptableTimeSkewSeconds;
        this.issuer = builder.issuer;
        this.audience = builder.audience == null ? null : Collections.unmodifiableCollection(builder.audience);
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final long getAcceptableTimeSkewSeconds() {
        return this.acceptableTimeSkewSeconds;
    }

    public final String getIssuer() {
        return this.issuer;
    }

    public final Collection<String> getAudience() {
        return this.audience;
    }

    public boolean verify(IdToken idToken) {
        return (this.issuer == null || idToken.verifyIssuer(this.issuer)) && ((this.audience == null || idToken.verifyAudience(this.audience)) && idToken.verifyTime(this.clock.currentTimeMillis(), this.acceptableTimeSkewSeconds));
    }
}
