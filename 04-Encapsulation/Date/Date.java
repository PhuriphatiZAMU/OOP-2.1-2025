package Encapsulation.Date;
class Date {

            private int day;
            private int month;
            private int year;

            public Date (int day , int month , int year) {

                setDay(day);
                setMonth(month);
                setYear(year);

            }
            public boolean isVallidDay(int day, int month){
                if (month < 1 || month > 12) {
                    return false;
                }
                if (day < 1 || day > 31) {
                    return false;
                }
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    return day <= 30;
                }
                return true;
            }
   
            public Date (Date date){

                this(date.getDay(), date.getMonth(), date.getYear());

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
            



            @Override
            public String toString(){
                return String.format("%02d/%02d/%04d = ค่าเป็น %b", day , month , year,isVallidDay(day, month) );
            }

}




