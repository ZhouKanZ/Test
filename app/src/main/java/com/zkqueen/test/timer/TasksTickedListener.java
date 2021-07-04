package com.zkqueen.test.timer;

import com.zkqueen.test.timer.task.ITask;

import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public interface TasksTickedListener {
    void onTasksTicked(List<ITask> tasks);
}
