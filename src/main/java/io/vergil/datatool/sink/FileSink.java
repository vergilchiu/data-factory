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
public class FileSink {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);

        Calendar start = Calendar.getInstance();
        start.set(2019, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(2020, 0, 1);
        BuildData buildData = new BuildData(start.getTime(), end.getTime(), 200, 400, 50);
        List<Car> next = null;
        int total = 0;

        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        while ((next = buildData.next(1000)).size() != 0) {
            for (Car car : next) {
                fw.write(car.toString());
            }
        }
        fw.close();
        System.out.println("total = " + total);
    }
}
