package com.zkqueen.test.timer.timerutil;

/**
 * @author: yifeng
 * @date: 2021/4/14
 * @version:
 * @desc:
 */
public interface ITimer {

    /**
     *  开启
     */
    void start();

    /**
     *  暂停
     */
    void pause();

    /**
     *  触发
     * @param currentMillis
     */
    void onTick(long currentMillis);

}
