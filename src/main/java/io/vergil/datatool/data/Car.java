package io.vergil.datatool.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {

    private String id;
    private Date date;
    private String hphm;
    private String cx;
    private String ys;
    private String location;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        Car.sdf = sdf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",");
        sb.append(sdf.format(date)).append(",");
        sb.append(hphm).append(",");
        sb.append(cx).append(",");
        sb.append(ys).append(",");
        sb.append(location).append("\n");
        return sb.toString();
    }
}
