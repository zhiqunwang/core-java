package org.elvis.wang.java.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zhiqun.wang on 2019/9/12 11:35
 */
public class DisruptorTest {

    /**
     * 消息事件类
     */
    public static class MessageEvent{
        /**
         * 原始消息
         */
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 消息事件工厂
     */
    public static class MessageEventFactory implements EventFactory<MessageEvent> {
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * 消息转换类 负责将消息转换为事件
     */
    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String>{

        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * 消费者线程工厂
     */
    public static class MessageThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }







}
