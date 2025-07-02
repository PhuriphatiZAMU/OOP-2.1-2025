package Encapsulation;
class Time2 {
    
    // Instance Variables 
    private int hour;
    private int minute;
    private int second;
    
    // Constructor 
    
    public Time2() {  // Default constructor
    
//        hour = 0;
//        minute = 0;
//        second = 0;
        this(0,0,0);
    }
    
    public Time2 (int hour){ // สร้าง object ระบุ hour ได้ 
    
//        this.hour = hour;
        this(hour,0,0);
    }
    
    public Time2 (int hour, int minute){ // สร้าง object ระบุ hour,minute ได้ 
    
//        //this.hour = hour;
//        setHour(hour);
//        //this.minute = minute;
//        setMinute(minute);

            this(hour,minute,0);
    }
    
    public Time2 (int hour, int minute, int second) {
    
        //this.hour = hour;
        setHour(hour);
        //this.minute = minute;
        setMinute(minute);
        //this.second = second;
        setSecond(second);
    }
    
    public Time2 (Time2 time) {
    
        this(time.getHour(),time.getMinute(),time.getSecond());
    
    }
    
    // Setter - Mutator method
    
    public void setHour(int hour) {
    
        if (hour < 24 && hour >= 0)
        this.hour = hour;
    }
    
    public void setMinute(int minute) {
    
        if (minute < 60 && minute >= 0)
        this.minute = minute;
    }
    
    public void setSecond(int second){
    
        if (second < 60 && second >= 0) {
            
            this.second = second;
        }
        
    }
    
    // Getter - Accessor method
    
    public int getHour() {
    
        return hour;
    }
    
    public int getMinute(){
    
        return minute;
    }
    
    public int getSecond() {
    
        return second;
    }
    
    @Override
    public String toString() {
    
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }

}

public class Time2Test {
    
    
    public static void main(String[] args) {
        
        Time2 time = new Time2(); // default constructor hour = 0, minute = 0, second = 0
        System.out.println(time); // use the variable to avoid warning
        Time2 time2 = new Time2(9); // set hour = 9, minute = 0, second = 0 ok
        time2.setMinute(59);
        System.out.println(time2); // toString

        Time2 time3  = new Time2(10,4);// set hour = 10, minute = 2, second = 0 ok
        System.out.println(time3);

        Time2 time4 = new Time2(10,12,50);
        System.out.println(time4);

        Time2 time5 = new Time2(time4);
        System.out.println(time5); // pass by object

        Time2 time6 = new Time2(26,70,99);
        System.out.println(time6);
        
        Time2 time7 = new Time2(26,70);
        System.out.println(time7);
    }
    
}