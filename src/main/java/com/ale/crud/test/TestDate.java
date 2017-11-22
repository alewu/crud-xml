package com.ale.crud.test;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TestDate {


    public  void test01() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime().getTime());
        Date date = new Date();
        System.out.println("当前日期：" + sdf.format(date));
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        System.out.println("增加月份后的日期：" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, -3);
        System.out.println("增加月份后减三天的日期：" + sdf.format(calendar.getTime()));


        try {
            Date date1 = sdf.parse("2017-2-28 13:24:16");
            calendar.setTime(date1);
            calendar.add(Calendar.MONTH, 1);
            System.out.println("sssss:" + sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------------------------");

        DateTime dateTime = new DateTime();
        DateTime to = dateTime.plusDays(2);


        DateTime toMonth = dateTime.minusMonths(7);
        System.out.println("前：" + toMonth);
        DateTime oo = toMonth.plusDays(1);
        System.out.println("中：" + oo);
        DateTime o = toMonth.plusMonths(1);
        System.out.println("后：" + o);

        System.out.println("————————————————————————————————");

        System.out.println("--------------------------------------");

        System.out.println(new DateTime());

        DateTime dt1 = new DateTime();
        DateTime dt2 = new DateTime(2017, 10, 16, 22, 12, 0);
        System.out.print("时间相差：");
        // 后一个参数减去前一个参数
        System.out.println(Days.daysBetween(dt1, dt2).getDays() + " 天 ");
        System.out.println(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " 小时 ");
        System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " 分钟 ");
        System.out.println(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " 秒.");

        System.out.println("--------------------------------------");
        System.out.println("获取毫秒数：" + dt1.getMillis());
    }

    @Test
    public void test(){

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //时间解析
        DateTime dateTime1 = DateTime.parse("2017-10-31 22:22:22", format);
        //时间格式化，输出==>
        String string = dateTime1.toString("yyyy/MM/dd HH:mm:ss EE");
        System.out.println("时间格式化，输出" + string);


        System.out.println("第一天日期：" + dateTime1.dayOfMonth().withMaximumValue());

        System.out.println("------------------------------------------------------------------");

        System.out.println("最后一天的日期：" + dateTime1.plusMonths(1).dayOfMonth().withMaximumValue());

        System.out.println("最后一天是几号：" + dateTime1.dayOfMonth().withMaximumValue().dayOfMonth().get());

    }


    @Test
    public void test00(){
        System.out.println(UUID.randomUUID());
        System.out.println(System.currentTimeMillis());
    }


}
