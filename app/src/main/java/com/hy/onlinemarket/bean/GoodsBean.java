package com.hy.onlinemarket.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;



public class GoodsBean extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;

    private byte[] img;// 不用id

    private int count;

    private float price;

    @Ignore
    private boolean isSelected;

    @Ignore
    private boolean isModifying;// 数据库：文件、表格、数据；修改：alter、modify、update

    /**
     * JPEG图片转换为byte数组
     *
     * @param ctx
     * @param imgId
     * @return
     */
    public static byte[] imgIdToBys(Context ctx, int imgId) {
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), imgId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * 字节数组转Bitmap
     *
     * @param bys
     * @return
     */
    public static Bitmap bysToBitmap(byte[] bys) {
        return BitmapFactory.decodeByteArray(bys, 0, bys.length);
    }


    public GoodsBean() {
    }

    public GoodsBean(long id, String name, byte[] img, int count, float price, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.count = count;
        this.price = price;
        this.isSelected = isSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isModifying() {
        return isModifying;
    }

    public void setModifying(boolean modifying) {
        isModifying = modifying;
    }

}
