package com.mq.aliyun.converter;

import com.aliyun.openservices.ons.api.Message;
import com.mq.idempotent.core.aop.MessageConverter;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author : wh
 * @date : 2021/11/15 14:00
 * @description:
 */
public class AliYunRocketMQMessageConverter implements MessageConverter<Message> {


    @Override
    public String getUniqueKey(Message message, String field, Method method, Object[] args) {
        String messageKey = message.getKey();
        return !StringUtils.isEmpty(messageKey) ? messageKey : message.getMsgID();
    }
}
