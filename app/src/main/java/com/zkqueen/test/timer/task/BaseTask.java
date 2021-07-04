package com.zkqueen.test.timer.task;

import com.zkqueen.test.timer.TaskState;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/14
 * @version:
 * @desc: 任务
 */
public abstract class BaseTask implements ITask {

    // 任务状态
    private TaskState taskState;

    // 触发时机
    private List<Long> triggerTimes;

    public void onTimeTriggered(long triggerDuration){

    }

    public void setTriggerStrategy(){

    }


}
