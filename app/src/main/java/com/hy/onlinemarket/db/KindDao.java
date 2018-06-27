package com.hy.onlinemarket.db;

import com.hy.onlinemarket.bean.KindBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;



public class KindDao {
    public static List<KindBean> getKindTitles() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<KindBean> all = realm.where(KindBean.class).equalTo("titleId", -1).findAll();
        List<KindBean> kb = realm.copyFromRealm(all);
        realm.close();
        return kb;
    }

    public static List<KindBean> getKinds(long titleId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<KindBean> all = realm.where(KindBean.class).equalTo("titleId", titleId).findAll();
        List<KindBean> kb = realm.copyFromRealm(all);
        realm.close();
        return kb;
    }}

