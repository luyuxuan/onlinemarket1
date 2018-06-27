package com.hy.onlinemarket.db;

import com.hy.onlinemarket.bean.UserBean;

import io.realm.Realm;



public class UserDao {

    public static UserBean check(String ssid, String pwd) {
        UserBean bean = null;
        Realm realm = Realm.getDefaultInstance();
        bean = realm.where(UserBean.class).equalTo("ssid", ssid).equalTo("pwd", pwd).findFirst();
        if (bean != null)
            bean = realm.copyFromRealm(bean);
        realm.close();
        return bean;

    }

    public static boolean addUser(UserBean bean) {

        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            //模拟id的增长
            //检查数据库中用多少个bean
            long count = realm.where(UserBean.class).count();
            //设置id
            bean.setId(count);
            realm.insert(bean);
            realm.commitTransaction();
            realm.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updata(UserBean bean) {
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            long id=bean.getId();
//            String name = bean.getName();
//            int sex=bean.getSex();
//            String birthday=bean.getBrithday();
//            String address=bean.getAddress();
            bean=realm.where(UserBean.class).equalTo("id",id).findFirst();
//            bean.setName(name);
//            bean.setSex(sex);
//            bean.setBrithday(birthday);
//            bean.setAddress(address);
            realm.insertOrUpdate(bean);
            realm.commitTransaction();
            realm.close();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

}
