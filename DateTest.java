class Date {

            private int day;
            private int month;
            private int year;

            public Date (int day , int month , int year) {

                setDay(day);
                setMonth(month);
                setYear(year);

            }
            public Date (Date date){

                this(date.getDay(), date.getMonth(), date.getYear());

            }
            public void setDay (int day){

                if (day < 31  && day >= 1 ) {
                    
                    this.day = day;

                }

            }
            public void setMonth (int month){

                if (month < 12 && month >= 1) {
                    this.month = month;
                }

            }
            public void setYear (int year){

                if (year < 9999 && year >= 1900) {
                    this.year = year;
                }

            }
            public int getDay() {

                return day;

            }
            public int getMonth() {

                return month;

            }
            public int getYear() {

                return year;

            }       

            @Override
            public String toString(){
                return String.format("%02d/%02d/%04d", day , month , year );
            }

}


public class DateTest {

        public static void main(String[] args) {
       
        Date date = new Date (32, 13, 10000);
        System.out.println(date.toString());
        
        }


}
