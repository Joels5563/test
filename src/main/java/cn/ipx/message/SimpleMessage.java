package cn.ipx.message;

/**
 * 消息对象
 *
 * @author joels
 * @create 2017-03-23 10:53
 **/
public class SimpleMessage {
    //发送的消息体
    private String body;
    //消息的全局唯一标识，由 MQ 系统自动生成，唯一标识某条消息。
    private String msgId;
    //消息生成时间
    private String bornTime;
    //消息句柄
    private String msgHandle;
    //消息消费次数
    private int reconsumeTimes;
    //标记
    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }
    public int getReconsumeTimes() {
        return reconsumeTimes;
    }
    public void setReconsumeTimes(int reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }
    public void setMsgHandle(String msgHandle) {
        this.msgHandle = msgHandle;
    }
    public String getMsgHandle() {
        return msgHandle;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getBornTime() {
        return bornTime;
    }
    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }
}
