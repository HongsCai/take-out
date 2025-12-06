package com.hongs.skycommon.context;

public class BaseContext {
    private static final ThreadLocal<Long> thread = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        thread.set(id);
    }

    public static Long getCurrentId() {
        return thread.get();
    }

    public static void removeCurrentId() {
        thread.remove();
    }
}
