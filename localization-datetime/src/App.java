import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) throws Exception {
        // current time 
        printSeparator("current time");
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        printDateAndTime(currentDateTime, currentTime);

        // selected time
        printSeparator("selected time");
        LocalDateTime someDateTime = LocalDateTime.of(1985,11,9,0,22,5);
        LocalTime someTime = someDateTime.toLocalTime();
        printDateAndTime(someDateTime, someTime);

        // format date time
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String formatDateTime = someDateTime.format(dateTimeFormatter);
        System.out.println("Formatted: " + formatDateTime);

        // handling Zones
        printSeparator("handling time zones");

        TimeZone localTimeZone = TimeZone.getDefault();
        System.out.println("TimeZone: " + localTimeZone.getDisplayName(Locale.getDefault()));

        Locale.setDefault(Locale.FRANCE);
        TimeZone frLocalTimeZone = TimeZone.getDefault();
        System.out.println("TimeZone in FR: " + frLocalTimeZone.getDisplayName(Locale.getDefault()));


        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Riga"));
        printSeparator("ZonedDateTime Europe/Riga");
        printDateAndTime(zonedDateTime.toLocalDateTime(), zonedDateTime.toLocalTime());
        System.out.println("ZonedDateTime: " + zonedDateTime.toString());

        // time stamps (sql)
        printSeparator("time stamps (sql)");
        Timestamp ts = Timestamp.valueOf(currentDateTime);
        System.out.println("Timestamp: " + ts.toString());

    }

    private static void printSeparator(String title) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
    }

    private static void printDateAndTime(LocalDateTime dateTime, LocalTime time) {
        System.out.println("DayOfMonth: " + dateTime.getDayOfMonth());
        System.out.println("Month: " + dateTime.getMonth());
        System.out.println("DayOfWeek: " + dateTime.getDayOfWeek());
        System.out.println("Hour from LocalDateTime: " + dateTime.getHour());
        System.out.println("Hour from LocalTime: " + time.getHour());
    }
}
