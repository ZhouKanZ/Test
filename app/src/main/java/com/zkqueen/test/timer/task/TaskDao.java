package com.zkqueen.test.timer.task;

import java.util.List;

/**
 * @author: yifeng
 * @date: 2021/4/16
 * @version:
 * @desc:
 */
public interface TaskDao {

    void add(ITask task);

    void delete(ITask task);

    ITask query(String id);

    void update(ITask task);

    List<ITask> getAll();

}
