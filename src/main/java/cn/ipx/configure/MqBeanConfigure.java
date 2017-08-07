package cn.ipx.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-03-23 14:53
 **/
@Configuration
@ImportResource(locations = {"classpath:producer.xml",
        "classpath:consumer.xml"})
public class MqBeanConfigure {
}
