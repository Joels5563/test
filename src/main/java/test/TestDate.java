package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ${DESCRIPTION}
 *
 * @author joels
 * @create 2017-06-29 16:54
 **/
public class TestDate {

    public static void main(String[] args) throws Exception {
        //北京时间毫秒数 2017-11-30 1511971200000  Asia/Shanghai
        //Australia/ACT
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d");

        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/ACT");
        calendar.setTimeZone(timeZone);
        TimeZone.setDefault(timeZone);// 设置时区
        calendar.setTime(new Date(1511971200000L));

        System.out.println("Calendar时区：：" + calendar.getTimeZone().getID());
        System.out.println(calendar.getTimeInMillis());
        System.out.println(calendar.getTime());


        TimeZone timeZone1 = TimeZone.getTimeZone("Asia/Shanghai");
        calendar.setTimeZone(timeZone1);
        TimeZone.setDefault(timeZone1);// 设置时区
        calendar.setTime(simpleDateFormat.parse("2017-11-30"));
        System.out.println("Calendar时区：：" + calendar.getTimeZone().getID());
        System.out.println(calendar.getTimeInMillis());
        System.out.println(calendar.getTime());

    }
}
