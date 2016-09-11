package com.ksachdeva.opensource.ble.central.utils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

public class DisposableMap {

    private Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();

    public void replaceSubscription(String key, Subscription subscription) {
        Subscription oldSubscription = subscriptions.put(key, subscription);
        if (oldSubscription != null && !oldSubscription.isUnsubscribed()) {
            oldSubscription.unsubscribe();
        }
    }

    public boolean removeSubscription(String key) {
        Subscription subscription = subscriptions.remove(key);
        if (subscription == null) return false;
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        return true;
    }

    public void removeAllSubscriptions() {
        for (Subscription subscription: subscriptions.values()) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
        subscriptions.clear();
    }
}
