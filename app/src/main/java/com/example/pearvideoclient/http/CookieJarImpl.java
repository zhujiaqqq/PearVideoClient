package com.example.pearvideoclient.http;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author zhujiaqqq
 * @date 2019-07-10
 */
public class CookieJarImpl implements CookieJar {
    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException();
        }
        this.cookieStore = cookieStore;
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        return cookieStore.get(httpUrl);
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        cookieStore.add(httpUrl, list);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }
}
