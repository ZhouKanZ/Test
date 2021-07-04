package com.zkqueen.test.timer.task



/**
 *  @author: yifeng
 *  @date: 2021/4/14
 *  @version:
 *  @desc:
 */
interface ITask {

     fun getTriggersTime():MutableList<Long>

     fun updateCurrentTime(currentTime:Long)

     // 任务的基本属性 DairyCycleTask
     // id taskName taskState
     // 任务的触发方式
     //


}