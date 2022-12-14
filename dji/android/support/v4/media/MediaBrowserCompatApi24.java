package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import java.util.List;

class MediaBrowserCompatApi24 {

    interface SubscriptionCallback extends SubscriptionCallback {
        void onChildrenLoaded(@NonNull String str, List<Parcel> list, @NonNull Bundle bundle);

        void onError(@NonNull String str, @NonNull Bundle bundle);
    }

    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends SubscriptionCallbackProxy<T> {
        public SubscriptionCallbackProxy(T t) {
            super(t);
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list, @NonNull Bundle bundle) {
            ((SubscriptionCallback) this.mSubscriptionCallback).onChildrenLoaded(str, SubscriptionCallbackProxy.itemListToParcelList(list), bundle);
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
            ((SubscriptionCallback) this.mSubscriptionCallback).onError(str, bundle);
        }
    }

    MediaBrowserCompatApi24() {
    }

    public static Object createSubscriptionCallback(SubscriptionCallback subscriptionCallback) {
        return new SubscriptionCallbackProxy(subscriptionCallback);
    }

    public static void subscribe(Object obj, String str, Bundle bundle, Object obj2) {
        ((MediaBrowser) obj).subscribe(str, bundle, (android.media.browse.MediaBrowser.SubscriptionCallback) obj2);
    }

    public static void unsubscribe(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).unsubscribe(str, (android.media.browse.MediaBrowser.SubscriptionCallback) obj2);
    }
}
