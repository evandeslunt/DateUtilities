
package lv.date.utilities;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Liz Ife Van Deslunt
 */
public class DateUtilities {
    
    public static enum TIME_UNIT {MILLIS, SECS, MINS, HOURS, DAYS};
    
    private static String INVALID_DATE_ERR = "Please provide a valid Date.";
    private static String INVALID_CAL_ERR = "Please provide a valid Calendar.";
    private static String STRING_FMT_ERR = "Please provide a valid String.";
    
    private static final int MILLIS_IN_SEC = 1000;
    private static final int MILLIS_IN_MIN = MILLIS_IN_SEC*60;
    private static final int MILLIS_IN_HOUR = MILLIS_IN_MIN*60;
    private static final int MILLIS_IN_DAY = MILLIS_IN_HOUR*24;
    
   
    
    /**
     * Formats a Date object, given a format string.
     * @param date - A Date object to be formatted.
     * @param format - The desired format.
     * @return A date formatted as a string.
     */
    public static String formatDate(Date date, String format){
        if(date == null){
            throw new NullPointerException(INVALID_DATE_ERR);
        }
        if(format == null){
            throw new NullPointerException(STRING_FMT_ERR);
        }
        if(format.length() == 0){
            throw new IllegalArgumentException(STRING_FMT_ERR);
        }
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        return dateFormatter.format(date);
    }
    
    /**
     * Creates a date from a formatted string.
     * @param formattedString - A formatted date as a string
     * @return A Date object created from the date in the formattedString.
     * @throws ParseException 
     */
    public static Date getDateFromString(String formattedString) throws ParseException{
        if(formattedString == null){
            throw new NullPointerException(STRING_FMT_ERR);
        }
        if(formattedString.length() == 0){
            throw new IllegalArgumentException(STRING_FMT_ERR);
        }
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        
        return dateFormatter.parse(formattedString);
        
    }
     
    /**
     * Formats a Calendar object, given a format string.
     * @param calendar - A Calendar object to be formatted.
     * @param format - The desired format.
     * @return A date formatted as a string.
     */
    public static String formatDate(Calendar calendar, String format){
        if(calendar == null){
            throw new NullPointerException(INVALID_CAL_ERR);
        }
        if(format == null){
            throw new NullPointerException(STRING_FMT_ERR);
        }
        if(format.length() == 0){
            throw new IllegalArgumentException(STRING_FMT_ERR);
        }
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        
        return dateFormatter.format(calendar.getTime());
    }
    
     /**
     * Creates a date from a formatted string.
     * @param formattedString - A formatted date as a string
     * @return A Calendar object created from the date in the formattedString.
     * @throws ParseException 
     */
    public static Calendar getCalendarFromString(String formattedString) throws ParseException{
        if(formattedString == null){
            throw new NullPointerException(STRING_FMT_ERR);
        }
        if(formattedString.length() == 0){
            throw new IllegalArgumentException(STRING_FMT_ERR);
        }
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formattedString);
        Date date = dateFormatter.parse(formattedString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return cal;
    }
    
    /**
     * Formats the specified calendar into a string with short date format (MM/dd/yyyy). 
     * For example, 01/01/2013.
     * @param cal - The calendar to format.
     * @return A String formatted into a short date (MM/dd/yyyy).
     */
    public static String formatToShortDate(Calendar cal){
        if(cal == null){
            throw new NullPointerException(INVALID_CAL_ERR);
        }
        return formatDate(cal, "MM/dd/yyyy");
    }
    
    /**
     * Formats the specified date into a string with short date format (MM/dd/yyyy). 
     * For example, 01/01/2013.
     * @param date - The date to format.
     * @return A String formatted into a short date (MM/dd/yyyy).
     */
    public static String formatToShortDate(Date date){
        if(date == null){
            throw new NullPointerException(INVALID_DATE_ERR);
        }
        return formatDate(date, "MM/dd/yyyy");
    }
    
    /**
     * Formats the specified calendar into a string with short time format
     * (HH:mm aa). For example, 09:00 AM.
     * @param cal - The calendar to format.
     * @return A String formatted into a short time.
     */
    public static String formatToShortTime(Calendar cal){
        if(cal == null){
            throw new NullPointerException(INVALID_CAL_ERR);
        }
        return formatDate(cal, "hh:mm aa");
    }

    /**
     * Formats the specified date into a string with short time format
     * (HH:mm aa). For example, 09:00 AM.
     * @param date - The date to format.
     * @return A String formatted into a short time.
     */
    public static String formatToShortTime(Date date){
        if(date == null){
            throw new NullPointerException(INVALID_DATE_ERR);
        }
        return formatDate(date, "hh:mm aa");
    }    
    
    
    /**
     * Returns the time difference between two calendars in the specified time
     * unit. If the first calendar's date occurs after the second calendar's, 
     * returns a negative.
     * @param cal1 - The first calendar.
     * @param cal2 - The second calendar.
     * @param timeUnit - The <code>TIME_UNIT</code> to return the difference in.
     * @return Time difference between the calendars. If cal1 is after cal2, 
     * returns a negative value.
     */
    public static long getDiff(Calendar cal1, Calendar cal2, 
            TIME_UNIT timeUnit){
        
        if(cal1 == null || cal2 == null || timeUnit == null){
            throw new NullPointerException(INVALID_DATE_ERR);
        }
        
        long timeDiffInMillis = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        
        switch(timeUnit){
            case MILLIS:
                return timeDiffInMillis;
            case SECS:
                return timeDiffInMillis/MILLIS_IN_SEC;
            case MINS:
                return timeDiffInMillis/MILLIS_IN_MIN;
            case HOURS:
                return timeDiffInMillis/MILLIS_IN_HOUR;
            case DAYS:
                return timeDiffInMillis/MILLIS_IN_DAY;
            default:
                return timeDiffInMillis;
        }
        
    }
    
    /**
     * Returns the time difference between two dates in the specified time
     * unit. If the first date occurs after the second date, returns a negative.
     * @param date1 - The first date.
     * @param date2 - The second date.
     * @param timeUnit - The <code>TIME_UNIT</code> to return the difference in.
     * @return Time difference between the calendars. If date1 is after date2, 
     * returns a negative value.
     */
    public static long getDiff(Date date1, Date date2, TIME_UNIT timeUnit){
       if(date1 == null || date2 == null || timeUnit == null){
           throw new NullPointerException();
       }
        
       Calendar cal1 = Calendar.getInstance();
       cal1.setTime(date1);
        
       Calendar cal2 = Calendar.getInstance();
       cal2.setTime(date2);
       
       return getDiff(cal1, cal2, timeUnit);
       
    }

}