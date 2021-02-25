package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class CancerOrScorpio {
    MiscUtility ut = new MiscUtility(false);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        LocalDate date = LocalDate.from(formatter.parse(s));
        ut.printDebug(date, "date");
        date = date.plusWeeks(40);
        out.printf("%d %s %s\n", testNumber, formatter2.format(date), getZodiac(date));
    }

    private String getZodiac(LocalDate date) {
        if (!date.isBefore(LocalDate.of(date.getYear(), Month.JANUARY, 21))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.FEBRUARY, 19))) {
            return "aquarius";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.FEBRUARY, 20))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.MARCH, 20))) {
            return "pisces";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.MARCH, 21))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.APRIL, 20))) {
            return "aries";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.APRIL, 21))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.MAY, 21))) {
            return "taurus";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.MAY, 22))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.JUNE, 21))) {
            return "gemini";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.JUNE, 22))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.JULY, 22))) {
            return "cancer";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.JULY, 23))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.AUGUST, 21))) {
            return "leo";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.AUGUST, 22))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.SEPTEMBER, 23))) {
            return "virgo";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.SEPTEMBER, 24))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.OCTOBER, 23))) {
            return "libra";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.OCTOBER, 24))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.NOVEMBER, 22))) {
            return "scorpio";
        } else if (!date.isBefore(LocalDate.of(date.getYear(), Month.NOVEMBER, 23))
                && !date.isAfter(LocalDate.of(date.getYear(), Month.DECEMBER, 22))) {
            return "sagittarius";
        } else {
            return "capricorn";
        }
    }
}
