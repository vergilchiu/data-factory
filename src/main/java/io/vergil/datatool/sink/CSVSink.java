package io.vergil.datatool.sink;

import io.vergil.datatool.data.BuildData;
import io.vergil.datatool.data.Car;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.List;

/**
 * @author: wei.zhao
 * @create: 2020-05-27 11:04
 **/
public class CSVSink {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);

        Calendar start = Calendar.getInstance();
        start.set(2019, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(2019, 2, 1);
        BuildData buildData = new BuildData(start.getTime(), end.getTime(), 200, 400, 50);
        List<Car> next = null;
        int total = 0;

        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        while ((next = buildData.next(1000)).size() != 0) {
            for (Car car : next) {
                StringBuilder sb = new StringBuilder();
                sb.append(car.getId()).append(",");
                sb.append(car.getDate_()).append(",");
                sb.append(car.getHphm()).append(",");
                sb.append(car.getCx()).append(",");
                sb.append(car.getYs()).append(",");
                sb.append(car.getLocation()).append("\r\n");
                fw.write(sb.toString());
            }
        }
        fw.close();
        System.out.println("total = " + total);
    }
}
