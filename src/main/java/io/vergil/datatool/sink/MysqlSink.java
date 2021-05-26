package io.vergil.datatool.sink;

import com.alibaba.druid.pool.DruidDataSource;
import io.vergil.datatool.data.BuildData;
import io.vergil.datatool.data.Car;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: wei.zhao
 * @create: 2020-05-27 11:04
 **/
public class MysqlSink {

    public static void main(String[] args) throws Exception {

        Calendar start = Calendar.getInstance();
        start.set(2019, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(2020, 0, 1);
        BuildData buildData = new BuildData(start.getTime(), end.getTime(), 200, 400, 50);
        List<Car> next = null;
        int total = 0;


        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://druid1:3306?characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        while ((next = buildData.next(1000)).size() != 0) {
            Object[][] params = new Object[next.size()][6];
            for (int i = 0; i < next.size(); i++) {
                Car car = next.get(i);
                params[i][0] = car.getId();
                params[i][1] = car.getDate_();
                params[i][2] = car.getHphm();
                params[i][3] = car.getCx();
                params[i][4] = car.getYs();
                params[i][5] = car.getLocation();
            }
            QueryRunner queryRunner = new QueryRunner(druidDataSource);
            queryRunner.insertBatch("insert into test.car3 values(?,?,?,?,?,?)", new ResultSetHandler<Object>() {
                public Object handle(ResultSet rs) throws SQLException {
                    return null;
                }
            }, params);
            total += params.length;
            System.out.println(new Date() + ",total = " + total);
        }
        druidDataSource.close();
        System.out.println("total = " + total);
    }


    private void init() {

    }
}
