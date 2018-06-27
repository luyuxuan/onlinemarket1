package com.hy.onlinemarket.db;

import com.hy.onlinemarket.bean.GoodsBean;

import java.util.List;

import io.realm.Realm;


public class GoodsDao {

    public static List<GoodsBean> getGoodsList() {
        Realm realm = Realm.getDefaultInstance();
        List<GoodsBean> list = realm.copyFromRealm(realm.where(GoodsBean.class).findAll());
        realm.close();
        return list;
    }

    public static boolean updateCount(long id, int count) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            GoodsBean bean = realm.where(GoodsBean.class).equalTo("id", id).findFirst();
            bean.setCount(count);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
            return false;
        } finally {
            realm.close();
        }
    }

    public static boolean delete(long id) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            GoodsBean bean = realm.where(GoodsBean.class).equalTo("id", id).findFirst();
            bean.deleteFromRealm();
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
            return false;
        } finally {
            realm.close();
        }
    }

    public static boolean delete(List<GoodsBean> list) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            for (GoodsBean g : list) {
                GoodsBean bean = realm.where(GoodsBean.class).equalTo("id", g.getId()).findFirst();
                if (bean != null)
                    bean.deleteFromRealm();
            }
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
            return false;
        } finally {
            realm.close();
        }
    }
}
