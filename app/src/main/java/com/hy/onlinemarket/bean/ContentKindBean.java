package com.hy.onlinemarket.bean;

import io.realm.annotations.PrimaryKey;



public class ContentKindBean {

    @PrimaryKey
    public  long id;
    public int imgId;
    public String name;
    public Float price;

    public ContentKindBean() {
    }

    public ContentKindBean(long id, int imgId, String name, Float price) {

        this.id = id;
        this.imgId = imgId;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
