package com.mll.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by liuhui on 2016/5/5.
 */
@Component
public class EventBusBeanPostProcessor implements BeanPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(EventBusBeanPostProcessor.class);

    @Autowired
    private EventBus eventBus;
    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getMethods()) {
            if(method.isAnnotationPresent(Subscribe.class)){
                logger.info("EventBusSupport register bean: {}", bean.getClass().getName());
                this.eventBus.register(bean);
                this.asyncEventBus.register(bean);
                break;
            }
        }
        return bean;
    }
}
