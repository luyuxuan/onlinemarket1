package com.hy.onlinemarket.bean;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class KindBean extends RealmObject {

    @PrimaryKey
    private long id;

    private String text;

    @Ignore
    private boolean isOpen;// 不需要存储到数据

    private long titleId; // 外键（并不创建关联）

    public KindBean() {
    }

    public KindBean(long id, String text, boolean isOpen, long titleId) {
        this.id = id;
        this.text = text;
        this.isOpen = isOpen;
        this.titleId = titleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }
}
