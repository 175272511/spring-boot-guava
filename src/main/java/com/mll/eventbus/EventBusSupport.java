package com.mll.eventbus;

/**
 * Created by liuhui on 2016/5/5.
 */
public interface EventBusSupport {

    void post(Object event);

    void postAsync(Object event);
}
