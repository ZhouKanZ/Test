package com.zkqueen.test;

import android.content.Context;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * @author: yifeng
 * @date: 2021/6/25
 * @version:
 * @desc:
 */
public class CustomBottomSheetDialog extends BottomSheetDialog {

    public CustomBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public CustomBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected CustomBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
