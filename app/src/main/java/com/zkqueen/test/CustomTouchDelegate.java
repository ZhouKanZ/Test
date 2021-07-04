package com.zkqueen.test;

import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * @author: yifeng
 * @date: 2021/6/25
 * @version:
 * @desc:
 */
public abstract class CustomTouchDelegate {

    private Rect clickRect; // 点击区域
    private boolean isHit = false; // 是否命中
    private int CLICK_EVENT_DURATION = 100; // ms
    private float downTouchTime = 0; // down事件 触摸时间

    public CustomTouchDelegate(Rect clickRect) {
        this.clickRect = clickRect;
    }


    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (clickRect.contains(x, y)) {
                    isHit = true;
                    downTouchTime = System.currentTimeMillis();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isHit) {
                    if (clickRect.contains(x, y)
                            && System.currentTimeMillis() - downTouchTime <= CLICK_EVENT_DURATION) {
                        performClick();
                        return true;
                    }
                }
                performOutSideClickAreaEvent();

                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
                if (!clickRect.contains(x, y)) {
                    isHit = false;
                    downTouchTime = 0;
                }
                break;
        }
        return false;
    }

    protected abstract void performOutSideClickAreaEvent();

    /**
     * 点击事件
     */
    protected abstract void performClick();
}
