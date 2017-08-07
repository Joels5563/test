package cn.ipx.msgListener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-03-23 15:35
 **/
public class IpxMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        System.out.println("Receive: " + message.getMsgID());
        try {
            //do something..
            return Action.CommitMessage;
        }catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
