package com.esflink.starter.context;

import com.esflink.starter.annotation.FlinkSink;
import com.esflink.starter.config.EasyFlinkOrdered;
import com.esflink.starter.data.FlinkDataChangeSink;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouhongyin
 * @since 2023/5/23 14:19
 */
public class FlinkSinkHolder {
    public static final String BEAN_NAME = "flinkSinkHolder";

    public static final Map<String, List<FlinkDataChangeSink>> SINK_MAP = new ConcurrentHashMap<>();

    public void registerSink(FlinkDataChangeSink sink, FlinkSink flinkSink) {
        String value = flinkSink.value();
        List<FlinkDataChangeSink> sinkList = SINK_MAP.getOrDefault(value, new ArrayList<>());
        sinkList.add(sink);
        SINK_MAP.put(value, sinkList);
    }

}