package dbTools;

public class TimeConverter {
  public static int getTimeAsMinutes(String timingString) {
    String[] timingArray = timingString.split(("[/:-]"));
    int hourPart = Integer.parseInt(timingArray[0]);
    int minutePart = Integer.parseInt(timingArray[1]);
    return (hourPart*60) + minutePart;
  }

  public static String getTimeAsString(int timingInMinutes) {
    int hourPart = timingInMinutes/60;
    if(hourPart == 24){
      hourPart = 0;
    }
    int minutePart = timingInMinutes%60;
    String hourString = "";
    if(hourPart < 10){
      hourString = "0"+hourPart;
    } else {
      hourString = "" + hourPart;
    }
    String minuteString = "";
    if(minutePart < 10) {
      minuteString = "0" + minutePart;
    } else {
      minuteString = "" + minutePart;
    }
    String resultTimeString = hourString + " : " + minuteString;
    return resultTimeString;
  }
}
