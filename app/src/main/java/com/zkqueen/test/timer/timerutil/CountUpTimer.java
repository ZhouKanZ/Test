package com.zkqueen.test.timer.timerutil;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public abstract class CountUpTimer extends Handler implements ITimer {

    private static final int MSG = 1;
    private long mCountUpInterval = 1000;
    private long currentMillis = 0L;
    private boolean isPause = false;

    @Override
    public void handleMessage(@NonNull Message msg) {
        synchronized (CountUpTimer.this) {
            if (isPause) {
                return;
            }
            currentMillis += mCountUpInterval;
            onTick(currentMillis);
            sendMessageDelayed(obtainMessage(MSG), mCountUpInterval);
        }
    }

    public CountUpTimer() {
        super(Looper.getMainLooper());
    }

    public CountUpTimer(long countUpInterval) {
        super(Looper.getMainLooper());
        mCountUpInterval = countUpInterval;
    }

    @Override
    public synchronized final void start() {
        isPause = false;
        removeMessages(MSG);
        sendMessage(obtainMessage(MSG));
    }

    @Override
    public synchronized final void pause() {
        isPause = true;
        removeMessages(MSG);
    }

}
