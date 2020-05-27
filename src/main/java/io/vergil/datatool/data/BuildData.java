package io.vergil.datatool.data;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

//id，日期,号牌号码，车型，颜色
public class BuildData {
    private static String[] color = new String[]{"红", "橙", "黄", "绿", "青", "蓝", "紫"};
    private static String[] brand = new String[]{"大众", "别克", "奥迪", "宝马", "奔驰", "马自达", "比亚迪", "长安", "吉利"};
    private static String[] num1 = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] num2 = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "0"};
    private static String[] prvc = new String[]{"京", "津", "沪", "渝", "冀", "晋", "辽", "吉", "黑", "苏", "浙", "皖", "闽", "赣",
            "鲁", "豫", "鄂", "湘", "粤", "琼", "川", "贵", "云", "陕", "甘", "青", "台", "蒙", "桂", "宁", "新", "藏", "港", "澳"};
    private Calendar start = Calendar.getInstance();
    private Calendar end = Calendar.getInstance();
    private int countOfCarInSecond;
    private int countOfCarInSecondHigh;
    private int countOfCarInSecondLow;

    public BuildData(Date start, Date end, int countOfCarInSecond, int countOfCarInSecondHigh, int countOfCarInSecondLow) {
        this.start.setTime(start);
        this.end.setTime(end);
        this.countOfCarInSecond = countOfCarInSecond;
        this.countOfCarInSecondHigh = countOfCarInSecondHigh;
        this.countOfCarInSecondLow = countOfCarInSecondLow;
    }

    public List<Car> next(int num) {
        List<Car> cars = new ArrayList<Car>();
        while (num > cars.size()) {
            List<Car> next = next();
            if (next == null) {
                return cars;
            }
            cars.addAll(next);
        }
        return cars;
    }

    public List<Car> next() {
        if (start.getTime().getTime() < end.getTime().getTime()) {
            return buildData();
        }
        return null;
    }

    // 生成数据
    private List<Car> buildData() {
        Date date = getDate();
        List<Car> cars = new ArrayList<Car>();
        int hours = date.getHours();
        int countOfCar = 0;
        if (hours >= 0 && hours <= 8) {
            countOfCar = countOfCarInSecondLow;
        } else if (hours >= 9 && hours <= 13) {
            countOfCar = countOfCarInSecondHigh;
        } else if (hours >= 14 && hours <= 17) {
            countOfCar = countOfCarInSecond;
        } else if (hours >= 18 && hours <= 21) {
            countOfCar = countOfCarInSecondHigh;
        } else if (hours >= 22 && hours <= 23) {
            countOfCar = countOfCarInSecond;
        }
        for (int i = 0; i < getCountOfCar(countOfCar); i++) {
            Car carVo = new Car();
            carVo.setId(getId());
            carVo.setDate(date);
            carVo.setHphm(getHphm());
            carVo.setCx(getModel());
            carVo.setYs(getColor());
            carVo.setLocation(getLocation());
            cars.add(carVo);
        }
        return cars;
    }

    // id
    private String getId() {
        String string = UUID.randomUUID().toString().replaceAll("-", "");
        return string;
    }

    private int getCountOfCar(int countOfCar) {
        int count = (int) (countOfCar * 0.9);
        return RandomUtils.nextInt(count, countOfCar);
    }

    // 日期
    private Date getDate() {
        start.setTime(DateUtils.addSeconds(start.getTime(), 1));
        return new Date(start.getTime().getTime());
    }

    // 车牌 苏E3G02D
    private String getHphm() {
        StringBuilder sb = new StringBuilder();
        sb.append(prvc[RandomUtils.nextInt(0, prvc.length)]);
        sb.append(num1[RandomUtils.nextInt(0, num1.length)]);
        for (int i = 0; i < 5; i++) {
            sb.append(num2[RandomUtils.nextInt(0, num2.length)]);
        }
        return sb.toString();
    }

    // 车型
    private String getModel() {
        int nextInt = RandomUtils.nextInt(0, brand.length);
        return brand[nextInt];
    }

    // 颜色
    private String getColor() {
        int nextInt = RandomUtils.nextInt(0, color.length);
        return color[nextInt];
    }

    // 卡口
    private String getLocation() {
        int nextInt = RandomUtils.nextInt(0, 100);
        return "l" + nextInt;
    }

    public static void main(String[] args) {
        Calendar start = Calendar.getInstance();
        start.set(2019, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(2020, 0, 1);
        BuildData buildData = new BuildData(start.getTime(), end.getTime(), 200, 400, 50);
        List<Car> next = null;
        int total = 0;
        while ((next = buildData.next(1000)).size() != 0) {
            total += next.size();
        }
        System.out.println("total = " + total);
    }
}
