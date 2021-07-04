package com.zkqueen.test.timer.manager;

import com.zkqueen.test.timer.TasksTickedListener;
import com.zkqueen.test.timer.task.ITask;
import com.zkqueen.test.timer.timerutil.CountUpTimer;
import com.zkqueen.test.timer.timerutil.ITimer;

import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public class CountUpTaskManager implements ITimer{

    private TaskManager taskManager;
    private ITimer timer;
    private TasksTickedListener listener;

    public CountUpTaskManager() {
        taskManager = new TaskManager();
        //  防止泄露
        timer = new CountUpTimer() {
            @Override
            public void onTick(long currentMillis) {
                CountUpTaskManager.this.onTick(currentMillis);
            }
        };
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void pause() {
        timer.pause();
    }

    @Override
    public void onTick(long currentMillis) {
        List<ITask> iTasks = taskManager.onTick(currentMillis);
        if (!iTasks.isEmpty() && listener != null){
            listener.onTasksTicked(iTasks);
        }
    }

    /**
     *  释放并存储
     */
    public void release(){

    }
}
