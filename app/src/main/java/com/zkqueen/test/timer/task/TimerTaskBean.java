package com.zkqueen.test.timer.task;

import com.zkqueen.test.timer.TaskState;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version: 706 客户端通用计时
 * @desc:
 */
public class TimerTaskBean implements Serializable ,ITask{

    private String id;                  // 任务id
    private TaskState taskState;        // 任务状态
    private Long[] triggerNode;         // 触发节点
    private Long currentRunningTimestamp; // 当前执行时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public Long[] getTriggerNode() {
        return triggerNode;
    }

    public void setTriggerNode(Long[] triggerNode) {
        this.triggerNode = triggerNode;
    }

    public Long getCurrentRunningTimestamp() {
        return currentRunningTimestamp;
    }

    public void setCurrentRunningTimestamp(Long currentRunningTimestamp) {
        this.currentRunningTimestamp = currentRunningTimestamp;
    }

    @NotNull
    @Override
    public List<Long> getTriggersTime() {
        return Arrays.asList(triggerNode);
    }

    @Override
    public void updateCurrentTime(long currentTime) {
        currentRunningTimestamp = currentTime;
    }
}
