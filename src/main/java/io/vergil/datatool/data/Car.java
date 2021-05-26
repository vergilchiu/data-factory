package io.vergil.datatool.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {

    private String id;
    private Date date_;
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

    public Date getDate_() {
        return date_;
    }

    public void setDate_(Date date_) {
        this.date_ = date_;
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
        sb.append(sdf.format(date_)).append(",");
        sb.append(hphm).append(",");
        sb.append(cx).append(",");
        sb.append(ys).append(",");
        sb.append(location).append("\n");
        return sb.toString();
    }

    public String toSql() {
        StringBuilder sb = new StringBuilder("insert into test.car1 values(");
        sb.append("\"").append(id).append("\"").append(",");
        sb.append("\"").append(sdf.format(date_)).append("\"").append(",");
        sb.append("\"").append(hphm).append("\"").append(",");
        sb.append("\"").append(cx).append("\"").append(",");
        sb.append("\"").append(ys).append("\"").append(",");
        sb.append("\"").append(location).append("\"");
        sb.append(")");
        return sb.toString();
    }
}
