package com.jzsf.tuitor.rpcDomain.req;

import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
public class UserPreferenceReq implements Serializable {

    private static final long serialVersionUID = 2705194087687017597L;

    private String todoNotice;

    private String sysMessageNotice;

    private String otherUserMessageNotice;

    public String getTodoNotice() {
        return todoNotice;
    }

    public void setTodoNotice(String todoNotice) {
        this.todoNotice = todoNotice;
    }

    public String getSysMessageNotice() {
        return sysMessageNotice;
    }

    public void setSysMessageNotice(String sysMessageNotice) {
        this.sysMessageNotice = sysMessageNotice;
    }

    public String getOtherUserMessageNotice() {
        return otherUserMessageNotice;
    }

    public void setOtherUserMessageNotice(String otherUserMessageNotice) {
        this.otherUserMessageNotice = otherUserMessageNotice;
    }

}
