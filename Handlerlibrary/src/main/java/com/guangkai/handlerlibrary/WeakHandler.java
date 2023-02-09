package com.guangkai.handlerlibrary;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class WeakHandler<T> extends Handler {
    private WeakReference<T> host;

    public WeakHandler(T host) {
        this.host = new WeakReference<T>(host);
    }

    @Override
    public final void handleMessage(Message msg) {
        if (host.get() == null) {
            handleMessageWhenNotServive(msg);
        } else {
            handleMessageWhenServive(msg, host.get());
        }
    }

    //当引用对象存在（未被GC回收）时，调用此方法
    public abstract void handleMessageWhenServive(Message msg, T host);

    //当引用对象不存在（已被GC回收）时，调用此方法，非必须重写
    public void handleMessageWhenNotServive(Message msg) {
    }
}