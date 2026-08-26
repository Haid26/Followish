package utils;

public class DateTimeConvertor {
    public static String convertDate(String date){
        if(date == null )
            return null;
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        return day+'.'+month+'.'+year;
    }
}
