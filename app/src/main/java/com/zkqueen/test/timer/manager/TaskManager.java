package com.zkqueen.test.timer.manager;

import com.zkqueen.test.timer.task.ITask;
import com.zkqueen.test.timer.task.TaskDao;
import com.zkqueen.test.timer.task.TaskMMKVDao;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public class TaskManager implements TaskDao {

    private TaskDao taskDao = new TaskMMKVDao();

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void add(ITask task) {
        taskDao.add(task);
    }

    @Override
    public void delete(ITask task) {
        taskDao.delete(task);
    }

    @Override
    public ITask query(String id) {
        return null;
    }

    @Override
    public void update(ITask task) {
        taskDao.update(task);
    }

    @Override
    public List<ITask> getAll() {
        return taskDao.getAll();
    }

    public List<ITask> onTick(long currentTimeMillis){
        List<ITask> tickedTasks = new ArrayList<>();
        List<ITask> all = getAll();
        for (int i = 0; i < all.size(); i++) {
            ITask iTask = all.get(i);
            iTask.updateCurrentTime(currentTimeMillis);
            taskDao.update(iTask);
            if (iTask.getTriggersTime().contains(currentTimeMillis)){
                tickedTasks.add(iTask);
            }
        }
        return tickedTasks;
    }
}
