package com.mll.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.concurrent.Executors;

/**
 * Created by liuhui on 2016/5/5.
 */
@Configuration
public class EventBusAutoConfiguration {

    @Bean
    public EventBusSupport eventBusSupport(){
     return new EventBusSupportImpl(eventBus(), asyncEventBus());
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        return eventBus;
    }

    @Bean
    public AsyncEventBus asyncEventBus() {
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(200));
        return asyncEventBus;
    }

    static class EventBusSupportImpl implements EventBusSupport{
        private EventBus eventBus;
        private AsyncEventBus asyncEventBus;

        public EventBusSupportImpl(EventBus eventBus, AsyncEventBus asyncEventBus) {
            Assert.notNull(eventBus, "EventBus should not be null");
            Assert.notNull(asyncEventBus, "AsyncEventBus should not be null");
            this.eventBus = eventBus;
            this.asyncEventBus = asyncEventBus;
        }

        @Override
        public void post(final Object event) {
            this.eventBus.post(event);
        }

        @Override
        public void postAsync(final Object event) {
            this.asyncEventBus.post(event);
        }
    }
}
