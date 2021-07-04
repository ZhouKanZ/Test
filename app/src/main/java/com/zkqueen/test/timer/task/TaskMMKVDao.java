package com.zkqueen.test.timer.task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public class TaskMMKVDao implements TaskDao{

    private List<ITask> tasks = new ArrayList<>();

    @Override
    public void add(ITask task) {
        tasks.add(task);
    }

    @Override
    public void delete(ITask task) {
        tasks.remove(task);
    }

    @Override
    public ITask query(String id) {
        return null;
    }

    @Override
    public void update(ITask task) {

    }

    @Override
    public List<ITask> getAll() {
        return tasks;
    }
}
