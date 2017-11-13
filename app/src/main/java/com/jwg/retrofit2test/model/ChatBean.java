/**
 *
 */
package com.jwg.retrofit2test.model;

/**
 * @author juwuguo
 */
public class ChatBean {

    private String content;// 消息内容
    private Integer type;// 消息类型 0:文本消息
    private String receiver;// 聊天消息接受者

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
