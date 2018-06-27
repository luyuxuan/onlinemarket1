package com.hy.onlinemarket.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class UserBean extends RealmObject{

    @PrimaryKey
    private long id;
    private String ssid;
    private  String pwd;
    private String email;
    private String name;
    private  int sex;
    private  String address;
    private  String brithday;

    public UserBean(long id, String ssid, String pwd, String email, String name, int sex, String address, String brithday) {
        this.id = id;
        this.ssid = ssid;
        this.pwd = pwd;
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.brithday = brithday;
    }

    public UserBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }
}
